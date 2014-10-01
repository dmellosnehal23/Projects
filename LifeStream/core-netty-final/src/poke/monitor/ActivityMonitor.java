package poke.monitor;



import java.util.Iterator;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import poke.server.conf.ServerConf;


//SNE : Implemented ActivityMonitor to keep track of all heartmonitors of slave servers
public class ActivityMonitor {
	protected static Logger logger = LoggerFactory.getLogger("server");
	//SNE : Server status
	public static final int ACTIVE = 1;
	public static final int INACTIVE = 0;
	
	private static ActivityMonitor instance;
	//SNE : Lookup to store active slaves 
	private static volatile ConcurrentHashMap<String, HeartMonitor> activeMonitors = new ConcurrentHashMap<String, HeartMonitor>();
	//SNE : Lookup to store inactive slaves
	private static volatile ConcurrentHashMap<String, HeartMonitor> inactiveMonitors = new ConcurrentHashMap<String, HeartMonitor>();
	//SNE : Slave node.id to port mapping
	private static volatile ConcurrentHashMap<String, Integer> idToPort = new ConcurrentHashMap<String, Integer>();	
	
	
	private ServerConf sConf;
	
	private ActivityMonitor () {
	}
	
	public void initialize(ServerConf conf) {
		this.sConf = conf;
		//SNE : For every slave in server.sonf start a monitor to track heartbeat
		if(this.sConf.getSlaves() != null) {
			Iterator<ServerConf.SlaveConf> it = this.sConf.getSlaves().iterator();
			while(it.hasNext()) {
				ServerConf.SlaveConf slave = (ServerConf.SlaveConf)it.next();
				logger.info("SLAVE : " + slave.getNodeId() + " " + slave.getPort() + " " + slave.getMgmtPort());
				
				//SNE : add slaves to lookup
				idToPort.put(slave.getNodeId(), Integer.parseInt(slave.getPort()));
				
				//SNE : start heart monitor for each slaves
				this.addMonitor(slave.getNodeId(), "localhost", Integer.parseInt(slave.getMgmtPort()));
			}
		}
	}
		
	//SNE : Singleton to be shared across all components
	public static ActivityMonitor getInstance() {
		if(instance == null) {
			instance = new ActivityMonitor();
		}
		
		return instance;
	}
		
	//SNE : Create a monitor and add to active lookup
	public void addMonitor(String id, String host, int mport) {
		//associate heart monitor to the child server mgt port
		HeartMonitor hm = new HeartMonitor(host, mport);
		hm.start();
		
		inactiveMonitors.put(id, hm);
	}
	
	public void setStatus(String nodeId, int status) {
		if(status == ActivityMonitor.INACTIVE) {
			//SNE : if slave becomes inactive, move slave monitor from active map to inactive map
			inactiveMonitors.put(nodeId, activeMonitors.remove(nodeId));
		}else {
			if(inactiveMonitors.containsKey(nodeId)) {
				//SNE : if slave becomes active and is in inactive map, bring back to active map.
				activeMonitors.put(nodeId, inactiveMonitors.get(nodeId));
			}
		}
	}
	
	//SNE : For the list of active slaves chose one at random. 
	public ServerConf.SlaveConf getActiveSlave() {
		if(activeMonitors.size() < 1) return null;
		
		//make copy of the public ports to local array for lookup.
		Object [] nodes = activeMonitors.keySet().toArray();
		String [] nodeIds = Arrays.copyOf(nodes, nodes.length, String[].class);
		Integer idx = (int)Math.random() % (nodeIds.length);
		
		//SNE : return the slave associated with the slave node.id
		return this.sConf.slaveById(nodeIds[idx]);
	}
	
	//SNE : Return Server conf
	public ServerConf getServerConf() {
		return this.sConf;		
	}
}

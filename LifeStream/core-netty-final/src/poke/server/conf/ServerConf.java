/*
 * copyright 2012, gash
 * 
 * Gash licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package poke.server.conf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import poke.server.resources.ResourceFactory;

/**
 * Routing information for the server - internal use only
 * 
 * TODO refactor StorageEntry to be neutral for cache, file, and db
 * 
 * @author gash
 * 
 */
@XmlRootElement(name = "conf")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServerConf {
	private GeneralConf server;
	private List<ResourceConf> routing;
	//SNE : Added list of Slaves
	private List<SlaveConf> slaveServers;

	private volatile HashMap<Integer, ResourceConf> idToRsc;
	private volatile HashMap<String, SlaveConf> idToCon;

	private HashMap<Integer, ResourceConf> asMap() {
		if (idToRsc != null)
			return idToRsc;

		if (idToRsc == null) {
			synchronized (this) {
				if (idToRsc == null) {
					idToRsc = new HashMap<Integer, ResourceConf>();
					if (routing != null) {
						for (ResourceConf entry : routing) {
							idToRsc.put(entry.id, entry);
						}
					}
				}
			}
		}

		return idToRsc;
	}
	
	public void addGeneral(String name, String value) {
		if (server == null)
			server = new GeneralConf();

		server.add(name, value);
	}

	public GeneralConf getServer() {
		return server;
	}

	public void setServer(GeneralConf server) {
		this.server = server;
	}

	public void addResource(ResourceConf entry) {
		if (entry == null)
			return;
		else if (routing == null)
			routing = new ArrayList<ResourceConf>();

		routing.add(entry);
	}
	
	public void addResource(SlaveConf connected) {
		if (connected == null)
			return;
		else if (slaveServers == null)
			slaveServers = new ArrayList<SlaveConf>();

		slaveServers.add(connected);
	}	

	public ResourceConf findById(int id) {
		return asMap().get(id);
	}
	
	public List<ResourceConf> getRouting() {
		return routing;
	}
	
	public void setRouting(List<ResourceConf> conf) {
		this.routing = conf;
	}
	
	
	//SNE : Methods to access slave data
	private HashMap<String, SlaveConf> slaveLookup() {
		if (idToCon != null)
			return idToCon;

		if (idToCon == null) {
			synchronized (this) {
				if (idToCon == null) {
					idToCon = new HashMap<String, SlaveConf>();
					if (slaveServers != null) {
						for (SlaveConf connected : slaveServers) {
							idToCon.put(connected.nodeId, connected);
						}
					}
				}
			}
		}

		return idToCon;
	}

	public SlaveConf slaveById(String nodeId) {
		return slaveLookup().get(nodeId);
	}
	
	public List<SlaveConf> getSlaves() {
		return slaveServers;
	}
	
	public void setSlaves(List<SlaveConf> conf) {
		this.slaveServers = conf;
	}

	/**
	 * storage setup and configuration
	 * 
	 * @author gash1
	 * 
	 */
	@XmlRootElement(name = "general")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static final class GeneralConf {
		private TreeMap<String, String> general;

		public String getProperty(String name) {
			return general.get(name);
		}

		public void add(String name, String value) {
			if (name == null)
				return;
			else if (general == null)
				general = new TreeMap<String, String>();

			general.put(name, value);
		}

		public TreeMap<String, String> getGeneral() {
			return general;
		}

		public void setGeneral(TreeMap<String, String> general) {
			this.general = general;
		}
	}

	/**
	 * command (request) delegation
	 * 
	 * @author gash1
	 * 
	 */
	@XmlRootElement(name = "entry")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static final class ResourceConf {
		private int id;
		private String name;
		private String clazz;
		private boolean enabled;

		public ResourceConf() {
		}

		public ResourceConf(int id, String name, String clazz) {
			this.id = id;
			this.name = name;
			this.clazz = clazz;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getClazz() {
			return clazz;
		}

		public void setClazz(String clazz) {
			this.clazz = clazz;
		}

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
	}
	
	//SNE : Added SlaveConf to populate slave config
	@XmlRootElement(name = "slaves")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static final class SlaveConf {
		private String nodeId;
		private String port;
		private String mgmtPort;
		
		public SlaveConf(){
		}
		
		public SlaveConf(String nodeId, String port, String mgmtPort) {
			this.nodeId = nodeId;
			this.port = port;
			this.mgmtPort = mgmtPort;
		}

		public String getNodeId() {
			return nodeId;
		}

		public void setNodeId(String nodeId) {
			this.nodeId = nodeId;
		}

		public String getPort() {
			return port;
		}

		public void setPort(String port) {
			this.port = port;
		}

		public String getMgmtPort() {
			return mgmtPort;
		}

		public void setMgmtPort(String mgmtPort) {
			this.mgmtPort = mgmtPort;
		}
	}
}

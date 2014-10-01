/**
 * 
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.sjsu.cmpe283.CMPEInfoCollector.common.CommonConstants;
import com.sjsu.cmpe283.CMPEInfoCollector.threads.VHostMHealthUpdateThread;
import com.sjsu.cmpe283.CMPEInfoCollector.vo.VMDetails;
import com.vmware.vim25.mo.ServiceInstance;

/**
 * @author Kiran
 *
 */
public class InfoCollectorMain {

	private static ArrayList<VMDetails> vmlist = new ArrayList<VMDetails>();
	private static ArrayList<String> vmIPlist = new ArrayList<String>();
	private static HashMap<String,ServiceInstance> mapOfVHostServiceInst = new HashMap<String,ServiceInstance>();
	//public static String [] VHostList = {"130.65.133.191","130.65.133.192"};
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to CMPE-283");
		// Check if the VHosts are powered On and get the list of VMs

		// Ping VMs and Collect the Health InfoMation
		//System.out.println("hello in main");
/*		Thread lp = new Thread(new LogProcess());
		lp.start();
*/
		ExecutorService es = Executors.newFixedThreadPool(100);
		List<Callable<Object>> monitorJobList = new ArrayList<Callable<Object>>(100);
		//System.out.println("hello in main");
		try {
			
			for (String vHostLink : CommonConstants.VHostList) {
				
			monitorJobList.add(Executors.callable(new VHostMHealthUpdateThread(vHostLink)));
			
		}
			
			es.invokeAll(monitorJobList);
			List<Future<Object>> futureObjList = es.invokeAll(monitorJobList);
			es.shutdown();

//			Iterator futureItr = futureObjList.iterator();
//
//			while (futureItr.hasNext()) {
//				Future<Object> fu = (Future<Object>) futureItr.next();
//				fu.get();//
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

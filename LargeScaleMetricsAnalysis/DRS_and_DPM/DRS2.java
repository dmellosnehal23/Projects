
import java.net.MalformedURLException;
import java.net.URL;

//import CONFIG.SJSULAB;


//import CONFIG.SJSULAB;
import com.sjsu.cmpe283.CMPEInfoCollector.common.CommonConstants;
import com.vmware.vim25.Description;
import com.vmware.vim25.HostVMotionCompatibility;
import com.vmware.vim25.InvalidProperty;
import com.vmware.vim25.RuntimeFault;
import com.vmware.vim25.TaskInfo;
import com.vmware.vim25.VirtualDeviceConfigSpecFileOperation;
import com.vmware.vim25.VirtualDeviceConfigSpecOperation;
import com.vmware.vim25.VirtualDisk;
import com.vmware.vim25.VirtualDiskFlatVer2BackingInfo;
import com.vmware.vim25.VirtualEthernetCard;
import com.vmware.vim25.VirtualEthernetCardNetworkBackingInfo;
import com.vmware.vim25.VirtualLsiLogicController;
import com.vmware.vim25.VirtualMachineConfigSpec;
import com.vmware.vim25.VirtualMachineFileInfo;
import com.vmware.vim25.VirtualMachineMovePriority;
import com.vmware.vim25.VirtualMachinePowerState;
import com.vmware.vim25.VirtualPCNet32;
import com.vmware.vim25.VirtualSCSISharing;
import com.vmware.vim25.mo.ComputeResource;
import com.vmware.vim25.mo.Datacenter;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.HostSystem;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ResourcePool;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.Task;
import com.vmware.vim25.mo.VirtualMachine;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DRS2 {
	public static String vHostName;
	// Create VM
	static final String SERVER_NAME = "130.65.132.190";
	
	static final String vHost_Name3 = "130.65.133.193";
	static final String vHost_Name2 = "130.65.133.192";
	static final String vHost_Name1 = "130.65.133.191";
	static final String vHost_userName = "root";
	static final String USER_NAME = "administrator"; // root
	static final String PASSWORD = "12!@qwQW";
	private VirtualMachine vm_1;
	/*
	 * private String ip; private ServiceInstance serviceInst; private static
	 * final int SELECTED_COUNTER_ID = 6; // Active (mem) in KB
	 */
	static String url = "https://" + SERVER_NAME + "/sdk";
	
	static String dcName = "nfs1team07"; // ha-datacenter
	static String vmName = "Team07_NewVM";
	static long memorySizeMB = 500;
	static int cupCount = 1;
	static String guestOsId = "sles10Guest";
	static long diskSizeKB = 1000000;
	// mode: persistent|independent_persistent,
	// independent_nonpersistent
	static String diskMode = "persistent";
	static String datastoreName = "nfs1team07"; // storage1 (2)
	static String netName = "VM Network";
	static String nicName = "Network Adapter 1";

	// VM Stats
	private static Connection conn;
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/mongo_mysql";
	private static final String USERNAME = "root";
	private static final String CONN_PASSWORD = "root";
	private static final String dbCONN_PASSWORD = "mohare23";
	private static boolean valueSet = true;
	private static HashMap<String, Double> low_cpu = new HashMap<String, Double>();
	private static HashMap<String,Double > high_cpu = new HashMap<String,Double>();
	private static HashMap<Double,String > vHost = new HashMap<Double,String>();
	private static HashMap<Double,String > vHost2 = new HashMap<Double,String>();
	//private static HashMap<String,Double> h1h = new HashMap<String,Double>();
	private ServiceInstance si;
	private CommonConstants cm;

	// ADDING MIGRATION CODE
	// *************************************************************************************************

	public boolean migrateToAnotherHost(String vmname1, String newHostName1)
			throws InvalidProperty, RuntimeFault, RemoteException,
			MalformedURLException, InterruptedException {
		if (vmname1 == null || newHostName1== null) {
			System.out.println("Usage: java MigrateVM <url> "
					+ "<username> <password> <vmname> <newhost>");
			System.exit(0);
		}

		valueSet = false;
		System.out.println("The value is false so it will not ping untill it migrate and starts...");

		String vmname = vmname1;
		// String vmname = vmname1;
		// String newHostName = newHostName1;
		String newHostName = newHostName1;
		ServiceInstance si = new ServiceInstance(new URL(url), USER_NAME,PASSWORD, true);
		Folder rootFolder = si.getRootFolder();
		VirtualMachine vm = (VirtualMachine) new InventoryNavigator(rootFolder)
				.searchManagedEntity("VirtualMachine", vmname);
		HostSystem newHost = (HostSystem) new InventoryNavigator(rootFolder)
				.searchManagedEntity("HostSystem", newHostName);
		ComputeResource cr = (ComputeResource) newHost.getParent();

		String[] checks = new String[] { "cpu", "software" };
		
		//vm.getParent();
		Task task = vm.powerOnVM_Task(null);
		if (task.waitForTask() == Task.SUCCESS) {
			System.out.println(vmname + " is powered on");
		}
		// added 
		HostVMotionCompatibility[] vmcs = si.queryVMotionCompatibility(vm,
				new HostSystem[] { newHost }, checks);

		String[] comps = vmcs[0].getCompatibility();
		if (checks.length != comps.length) {
			System.out.println("CPU/software NOT compatible. Exit.");
			si.getServerConnection().logout();
			return false;
		}

		task = vm.migrateVM_Task(cr.getResourcePool(), newHost,
				VirtualMachineMovePriority.highPriority,
				VirtualMachinePowerState.poweredOn);

		if (task.waitForMe() == Task.SUCCESS) {
			System.out.println("VMotioned!");
		} else {
			System.out.println("VMotion failed!");
			TaskInfo info = task.getTaskInfo();
			System.out.println(info.getError().getFault());
		}
		si.getServerConnection().logout();
		return true;
	}

	public static void main(String args[]) {
		try {

		
			
			double first = CPUUsage.getHostCPUUsage("130.65.133.191");
			double second = CPUUsage.getHostCPUUsage("130.65.133.192");
			double third = CPUUsage.getHostCPUUsage("130.65.133.193");

			System.out.println("Before Load Balancing vHost List: "+"\n");
			System.out.println("130.65.133.191: "+first+"\n");
			System.out.println("130.65.133.192: "+second+"\n");
			System.out.println("130.65.133.193: "+third+"\n");
			
			vHost.put(first, "130.65.133.191");
			vHost.put(second, "130.65.133.192");
			vHost.put(third, "130.65.132.193");
			//h1h.put(key, value)
			Double less = null;
			Double highest = null;
			
			
				if (first < second && first < third)
					less = first;
				else if (second < first && second < third)
					less = second;
				else
					less = third;
				
				
				if (first > second && first > third)
					highest = first;
				else if (second > first && second > third)
					highest = second;
				else
					highest = third;
				
				
			ServiceInstance si = new ServiceInstance(new URL(
                    "https://130.65.132.190/sdk"), "administrator",
                    "12!@qwQW", true);
			
			HostSystem less_host = (HostSystem) new InventoryNavigator(
                    si.getRootFolder()).searchManagedEntity(
                    "HostSystem", vHost.get(less));
			//Double i=(double) 0;
			
			//String lessVm = "lessvm";
			//System.out.println("printing the values of high cpu"+"\n");
			for (VirtualMachine v : less_host.getVms())
			{
								//Double first_v+i = CPUUsage.getHostCPUUsage(v.getName());
				//i+1;
				System.out.println("printing the values of high cpu"+"\n");
				low_cpu.put(v.getName(),CPUUsage.getVMCPUUsage(v.getName()) );
				System.out.println("VM: " + v.getName() + " :" + CPUUsage.getVMCPUUsage(v.getName()));
				
				
							}
			
			System.out.println("printing the values of high cpu"+"\n");
			
			HostSystem high_host = (HostSystem) new InventoryNavigator(
                    si.getRootFolder()).searchManagedEntity(
                    "HostSystem", vHost.get(highest));
			int j=1;
			String highVm = null;
			for (VirtualMachine v : high_host.getVms())
			{
								//Double first_v+i = CPUUsage.getHostCPUUsage(v.getName());
				//i+1;
				if(!(CPUUsage.getVMCPUUsage(v.getName())==null))
				{
					high_cpu.put(v.getName(),CPUUsage.getVMCPUUsage(v.getName()) );
				System.out.println("VM: " + v.getName() + " :" + CPUUsage.getVMCPUUsage(v.getName()));
				}
				
				j++;
			}
			
			
			Iterator iterator = high_cpu.entrySet().iterator();
			Map.Entry pairs = (Map.Entry) iterator.next();
			System.out.println(high_cpu.isEmpty());
			System.out.println("highest:"+ highest);
			
			System.out.println("(double) pairs.getValue()"+ (double) pairs.getValue());
			
			double vmLow =  highest-((double) pairs.getValue()+less);
			
			String vmNameLow = pairs.getKey().toString();
	        while (iterator.hasNext())  
	        {  
	           pairs = (Map.Entry) iterator.next();  
	          
	            if(vmLow >(highest-((double) pairs.getValue()+less)))
	            {
	            	  vmLow = highest-((double) pairs.getValue()+less);
	            	  vmNameLow = pairs.getKey().toString(); 
	            }
	        }
	        
	    	DRS2 drs = new DRS2();
	     //   if(drs.migrateToAnotherHost(vmNameLow,vHost.get(less)));
	        if(drs.migrateToAnotherHost(vmNameLow,vHost.get(less)));
				System.out.println("successfully migrated");
				
				first = CPUUsage.getHostCPUUsage("130.65.133.191");
				second = CPUUsage.getHostCPUUsage("130.65.133.192");
				third = CPUUsage.getHostCPUUsage("130.65.133.193");

				System.out.println("After Load Balancing vHost List: "+"\n");
				System.out.println("130.65.133.191: "+first+"\n");
				System.out.println("130.65.133.192: "+second+"\n");
				System.out.println("130.65.133.193: "+third+"\n");

			
		} catch (Exception e) {
			System.out.println("Exception caught");
			e.printStackTrace();
		}
	}

	

}
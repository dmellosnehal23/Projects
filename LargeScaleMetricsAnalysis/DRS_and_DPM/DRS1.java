import java.net.URL;

import com.vmware.vim25.Description;
import com.vmware.vim25.VirtualDeviceConfigSpec;
import com.vmware.vim25.VirtualDeviceConfigSpecFileOperation;
import com.vmware.vim25.VirtualDeviceConfigSpecOperation;
import com.vmware.vim25.VirtualDisk;
import com.vmware.vim25.VirtualDiskFlatVer2BackingInfo;
import com.vmware.vim25.VirtualEthernetCard;
import com.vmware.vim25.VirtualEthernetCardNetworkBackingInfo;
import com.vmware.vim25.VirtualLsiLogicController;
import com.vmware.vim25.VirtualMachineConfigSpec;
import com.vmware.vim25.VirtualMachineFileInfo;
import com.vmware.vim25.VirtualPCNet32;
import com.vmware.vim25.VirtualSCSISharing;
import com.vmware.vim25.mo.Datacenter;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ResourcePool;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DRS1 
{
	//Create VM
	static final String SERVER_NAME = "130.65.132.190";
	static final String USER_NAME = "administrator"; //root
	static final String PASSWORD = "12!@qwQW";
	/*
	private String ip;
	private ServiceInstance serviceInst;
	private static final int SELECTED_COUNTER_ID = 6; // Active (mem) in KB
	*/
	static String url = "https://" + SERVER_NAME + "/sdk";
    static String dcName = "nfs1team07"; //ha-datacenter
    static String vmName = "Team07_NewVM";
    static long memorySizeMB = 500;
    static int cupCount = 1;
    static String guestOsId = "sles10Guest";
    static long diskSizeKB = 1000000;
    // mode: persistent|independent_persistent,
    // independent_nonpersistent
    static String diskMode = "persistent";
    static String datastoreName = "nfs1team07"; //storage1 (2)
    static String netName = "VM Network";
    static String nicName = "Network Adapter 1";
    
    //VM Stats
    private static Connection conn;
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/mongo_mysql";
	private static final String USERNAME = "root";
	private static final String CONN_PASSWORD = "root";
	//private static final String dbUSERNAME = "root";
	private static final String dbCONN_PASSWORD = "mohare23";
	
	
	private static HashMap<String, Double> h1 = new HashMap<String, Double>();
    
	public static void main(String args[])
	{
		try
		{
			//execute_mySQL();
			
			
			
			double first = CPUUsage.getHostCPUUsage("130.65.133.191");
			
			double second = CPUUsage.getHostCPUUsage("130.65.133.192");
			System.out.println("Size of each Host's are :");
			System.out.println("first = " +first + " " + "Second = " +second);
			ServiceInstance si = new ServiceInstance(new URL(url),
					USER_NAME, PASSWORD, true);
		
		    Folder rootFolder = si.getRootFolder();
		    Datacenter dc = (Datacenter) new InventoryNavigator(rootFolder).searchManagedEntity("Datacenter", dcName);

		    ResourcePool rp;
			if(first < second)
			{
				//System.out.println("hello in main1");
				//change number here for which vHost to use [0 or 1]
			    rp = (ResourcePool) new InventoryNavigator(dc).searchManagedEntities("ResourcePool")[0]; 
			    //System.out.println("hello in main2");
			}
			else
			{
			    //change number here for which vHost to use [0 or 1]
			    rp = (ResourcePool) new InventoryNavigator(dc).searchManagedEntities("ResourcePool")[1];
			    //System.out.println("hello in main");
			}
			
		    Folder vmFolder = dc.getVmFolder();
		
		    // create vm config spec
		    VirtualMachineConfigSpec vmSpec = new VirtualMachineConfigSpec();
		    
		       
		    vmSpec.setName(vmName);
		    vmSpec.setAnnotation("VirtualMachine Annotation");
		    vmSpec.setMemoryMB(memorySizeMB);
		    vmSpec.setNumCPUs(cupCount);
		    vmSpec.setGuestId(guestOsId);
		
		    // create virtual devices
		    int cKey = 1000;
		    VirtualDeviceConfigSpec scsiSpec = createScsiSpec(cKey);
		    VirtualDeviceConfigSpec diskSpec = createDiskSpec(datastoreName, cKey, diskSizeKB, diskMode);
		    VirtualDeviceConfigSpec nicSpec = createNicSpec(netName, nicName);
		
		    vmSpec.setDeviceChange(new VirtualDeviceConfigSpec[]{scsiSpec, diskSpec, nicSpec});
		    
		    // create vm file info for the vmx file
		    VirtualMachineFileInfo vmfi = new VirtualMachineFileInfo();
		    vmfi.setVmPathName("["+ datastoreName +"]");
		    vmSpec.setFiles(vmfi);
		
		    // call the createVM_Task method on the vm folder
		    Task task = vmFolder.createVM_Task(vmSpec, rp, null);
		    String result = task.waitForMe();       
		    
		    if(result == Task.SUCCESS) 
		    {
		      System.out.println("VM 'Team07_NewVM' Created Sucessfully");
		      
		    }
		    else 
		    {
		      System.out.println("VM could not be created. ");
		    }
		}
		catch(Exception e)
		{
			System.out.println("Exception caught");
			e.printStackTrace();
		}
  }

	public static void execute_mySQL() 
	{
		if (conn == null) 
		{
			try 
			{
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USERNAME, dbCONN_PASSWORD);
				ResultSet rs=conn.prepareStatement("select vmname, cpu from mongo_mysql.vmlog where time = "
						+ "(select max(time) from mongo_mysql.vmlog)").executeQuery();
				while(rs.next())
				{
					String name = rs.getString(1);
					Double cpu = rs.getDouble(2);
					System.out.println("name,cpu"+name+","+cpu);
					h1.put(name, cpu);
				}
				//conn.pr
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		//return conn;
	}    
	
	static VirtualDeviceConfigSpec createScsiSpec(int cKey)
	{
		VirtualDeviceConfigSpec scsiSpec = new VirtualDeviceConfigSpec();
		scsiSpec.setOperation(VirtualDeviceConfigSpecOperation.add);
		VirtualLsiLogicController scsiCtrl = new VirtualLsiLogicController();
		scsiCtrl.setKey(cKey);
		scsiCtrl.setBusNumber(0);
		scsiCtrl.setSharedBus(VirtualSCSISharing.noSharing);
		scsiSpec.setDevice(scsiCtrl);
		return scsiSpec;
	}
  
	static VirtualDeviceConfigSpec createDiskSpec(String dsName, int cKey, long diskSizeKB, String diskMode)
	{
		VirtualDeviceConfigSpec diskSpec = new VirtualDeviceConfigSpec();
		diskSpec.setOperation(VirtualDeviceConfigSpecOperation.add);
		diskSpec.setFileOperation(VirtualDeviceConfigSpecFileOperation.create);
		
		VirtualDisk vd = new VirtualDisk();
		vd.setCapacityInKB(diskSizeKB);
		diskSpec.setDevice(vd);
		vd.setKey(0);
		vd.setUnitNumber(0);
		vd.setControllerKey(cKey);
		
		VirtualDiskFlatVer2BackingInfo diskfileBacking = new VirtualDiskFlatVer2BackingInfo();
		String fileName = "["+ dsName +"]";
		diskfileBacking.setFileName(fileName);
		diskfileBacking.setDiskMode(diskMode);
		diskfileBacking.setThinProvisioned(true);
		vd.setBacking(diskfileBacking);
		return diskSpec;
	}
  
	static VirtualDeviceConfigSpec createNicSpec(String netName, String nicName) throws Exception
	{
		VirtualDeviceConfigSpec nicSpec = new VirtualDeviceConfigSpec();
		nicSpec.setOperation(VirtualDeviceConfigSpecOperation.add);
		
		VirtualEthernetCard nic =  new VirtualPCNet32();
		VirtualEthernetCardNetworkBackingInfo nicBacking = new VirtualEthernetCardNetworkBackingInfo();
		nicBacking.setDeviceName(netName);
		
		Description info = new Description();
		info.setLabel(nicName);
		info.setSummary(netName);
		nic.setDeviceInfo(info);
		
		// type: "generated", "manual", "assigned" by VC
		nic.setAddressType("generated");
	    nic.setBacking(nicBacking);
	    nic.setKey(0);
	   
	    nicSpec.setDevice(nic);
	    return nicSpec;
	}
}	
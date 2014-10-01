import java.io.FileWriter;
import java.io.IOException; 
import java.io.PrintWriter;
import java.net.InetAddress; 
import java.net.URL; 
import java.rmi.RemoteException; 
import java.util.ArrayList; 
import java.util.Date; 
  
import CONFIG.SJSULAB;

import com.vmware.vim25.AlarmSpec; 
import com.vmware.vim25.AuthorizationPrivilege; 
import com.vmware.vim25.CustomizationFault; 
import com.vmware.vim25.DatastoreInfo; 
import com.vmware.vim25.DatastoreSummary; 
import com.vmware.vim25.FileFault; 
import com.vmware.vim25.GuestInfo; 
import com.vmware.vim25.GuestNicInfo; 
import com.vmware.vim25.InsufficientResourcesFault; 
import com.vmware.vim25.InvalidDatastore; 
import com.vmware.vim25.InvalidName; 
import com.vmware.vim25.InvalidProperty; 
import com.vmware.vim25.InvalidState; 
import com.vmware.vim25.ManagedObjectReference; 
import com.vmware.vim25.MigrationFault; 
import com.vmware.vim25.RuntimeFault; 
import com.vmware.vim25.SnapshotFault; 
import com.vmware.vim25.StateAlarmExpression; 
import com.vmware.vim25.StateAlarmOperator; 
import com.vmware.vim25.TaskInProgress; 
import com.vmware.vim25.TaskInfoState; 
import com.vmware.vim25.Timedout; 
import com.vmware.vim25.VimFault; 
import com.vmware.vim25.VirtualHardware; 
import com.vmware.vim25.VirtualMachineCloneSpec; 
import com.vmware.vim25.VirtualMachineConfigInfo; 
import com.vmware.vim25.VirtualMachineMovePriority; 
import com.vmware.vim25.VirtualMachinePowerState; 
import com.vmware.vim25.VirtualMachineQuickStats; 
import com.vmware.vim25.VirtualMachineRelocateSpec; 
import com.vmware.vim25.VirtualMachineRuntimeInfo; 
import com.vmware.vim25.VirtualMachineSnapshotInfo; 
import com.vmware.vim25.VirtualMachineSnapshotTree; 
import com.vmware.vim25.VirtualMachineSummary; 
import com.vmware.vim25.VmConfigFault; 
import com.vmware.vim25.mo.AlarmManager; 
import com.vmware.vim25.mo.AuthorizationManager; 
import com.vmware.vim25.mo.ClusterComputeResource; 
import com.vmware.vim25.mo.ComputeResource; 
import com.vmware.vim25.mo.Datastore; 
import com.vmware.vim25.mo.Folder; 
import com.vmware.vim25.mo.HostSystem; 
import com.vmware.vim25.mo.InventoryNavigator; 
import com.vmware.vim25.mo.ManagedEntity; 
import com.vmware.vim25.mo.ResourcePool; 
import com.vmware.vim25.mo.ServiceInstance; 
import com.vmware.vim25.mo.Task; 
import com.vmware.vim25.mo.VirtualMachine; 

public class Statistics {
	VirtualMachine vm;
	ServiceInstance si;
	Folder rootFolder;
	private ManagedEntity vCenter;
	//private HostSystem host;
	//private HostSystem host2;
	String vmname;
	
	public Statistics(String vmname) {
		try {
			
		this.vmname = vmname;
		this.si = new ServiceInstance(new URL(SJSULAB.getVmwareHostURL1()), SJSULAB.getVmwareLogin1(), SJSULAB.getVmwarePassword1(), true);
		this.rootFolder = si.getRootFolder();
		this.vm = (VirtualMachine) new InventoryNavigator(rootFolder).searchManagedEntity("VirtualMachine", this.vmname);
        
        this.vCenter = new InventoryNavigator(rootFolder).searchManagedEntities("Datacenter")[0];
        
        //this.host = (HostSystem) new InventoryNavigator(rootFolder).searchManagedEntities("HostSystem")[1];
        //this.host2 = (HostSystem) new InventoryNavigator(rootFolder).searchManagedEntities("HostSystem")[0];
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	/*
	  void getStats(VirtualMachine vm, int k) throws InvalidProperty, RuntimeFault, RemoteException, NullPointerException { 
         
	        VirtualMachineConfigInfo vmc = vm.getConfig(); 
	        VirtualMachineRuntimeInfo vmri = vm.getRuntime(); 
	        ResourcePool rp = vm.getResourcePool(); 
	        String rp1 = rp.getName(); 
	        ComputeResource rp2 = rp.getOwner(); 
	        HostSystem[] rp3 = rp2.getHosts(); 
	          
	        out.println("VM"+(k+1)+"->"+vm.getName()); 
	        out.println("OS->"+vmc.getGuestFullName()); 
	        out.println("Status->"+vmri.getPowerState()); 
	        out.println("Host->"+rp3[0].getName()); 
	        out.println("Resource Pool->"+rp1); 
	        out.println(); 
	          
	        VirtualMachineSummary vms = vm.getSummary(); 
	        VirtualMachineQuickStats vmqs = vms.getQuickStats(); 
	        VirtualHardware vmh =   vmc.getHardware(); 
	          
	        out.println("Number of CPU->"+vmh.getNumCPU()); 
	        out.println("CPU Speed->"+vmri.getMaxCpuUsage()+" MHz"); 
	        out.println("CPU Usage->"+vmqs.getOverallCpuUsage()+" MHz"); 
	        out.println(); 
	          
	          
	        out.println("Total RAM->"+vmh.getMemoryMB()+" MB"); 
	        out.println("RAM Usage->"+vmqs.getHostMemoryUsage()+" MB"); 
	        out.println(); 
	          
	        GuestInfo guestInfo = vm.getGuest(); 
	        GuestNicInfo[] nic = guestInfo.getNet(); 
	          
	        out.println("IP->"+vm.getGuest().ipAddress); 
	        if(nic!=null) { 
	        if(nic.length>0 && nic[0]!=null) 
	        out.println("Network->"+nic[0].getNetwork());} 
	        out.println(); 
	          
	        Datastore[] vmn = vm.getDatastores(); 
	        for(int i=0;i<vmn.length;i++)  
	        {    
	        out.println("Datastore->"+vmn[i].getName()); 
	          
	        DatastoreInfo a = vmn[i].getInfo(); 
	        out.println("Location->"+a.getUrl()); 
	          
	        DatastoreSummary b = vmn[i].getSummary(); 
	        out.println("Total Size->"+(b.getCapacity()/(1024*1024*1024))+" GB"); 
	        out.println("Free space->"+(a.getFreeSpace()/(1024*1024*1024))+" GB"); 
	        } 
	        out.println("------------------------"); 
	    } */
	
	public void getStats()
    {
    	//AvailabilityManager myVM = vm;
        
    	try
    	{
    		PrintWriter out = new PrintWriter(new FileWriter("D:\\outputfile.txt")); 
    		
    		out.println("\nStatistics about the VM, Host and DataCenter");
        	out.println("\nDataCenter   : " + this.vCenter.getName());
        	//out.println("vHost 1      : " + this.host.getName());
        	//out.println("vHost 2      : " + this.host2.getName());
        	out.println("Guest OS     : " + this.vm.getGuest().guestFullName);
        	out.println("VM Version   : " + this.vm.getConfig().version);
        	Datastore[] ds = this.vm.getDatastores();
        	for(int i = 0; i < ds.length ; i++)
        		out.println("Datastore    : " + ds[i].getName());
        	out.println("Tools Status : " + this.vm.getGuest().getToolsVersionStatus());
        	out.println("DNS name     : " + this.vm.getGuest().hostName);
        	out.println("Host IP      : " + this.vm.getRuntime());
        	out.println("State        : " + this.vm.getGuest().guestState);
//        	for(int i = 0; i < hostList.length ; i++ )
//        		out.println("V-host in this V-center" + hostList[i].getName() ); 
        	
        	out.close();  
    	}
    	catch (Exception e)
    	{ System.out.println( e.toString() ) ; }

    }

}

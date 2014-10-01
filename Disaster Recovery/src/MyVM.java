import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.rmi.RemoteException;

import com.vmware.vim25.InvalidProperty;
import com.vmware.vim25.RuntimeFault;
import com.vmware.vim25.VirtualMachineRuntimeInfo;
import com.vmware.vim25.mo.Alarm;
import com.vmware.vim25.mo.AlarmManager;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.Task;
import com.vmware.vim25.mo.VirtualMachine;
import com.vmware.vim25.mo.samples.alarm.CreateVmAlarm;

import CONFIG.*;

/**
 * Write a description of class MyVM here.
 * 
 * @author SNEHAL D'MELLO
 * @version (a version number or a date)
 */
public class MyVM {
	// instance variables
	private String vmname;
	private ServiceInstance si;
	private VirtualMachine vm;
	public static boolean valueSet=true;
	CreateVmAlarm createVmAlarm;

	/**
	 * Constructor for objects of class MyVM
	 */
	public MyVM(String vmname) {
		// initialise instance variables
		try {
			
			this.vmname = vmname;
			this.si = new ServiceInstance(new URL(SJSULAB.getVmwareHostURL()),
					SJSULAB.getVmwareLogin(), SJSULAB.getVmwarePassword(), true);
			Folder rootFolder = si.getRootFolder();
			this.vm = (VirtualMachine) new InventoryNavigator(rootFolder)
					.searchManagedEntity("VirtualMachine", this.vmname);
			createVmAlarm = new CreateVmAlarm(vmname);
			
			// your code here
			//this.parenthostname = this.vm.....
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		if (this.vm == null) {
			System.out.println("No VM " + vmname + " found");
			if (this.si != null) {
				//this.si.getServerConnection().logout();
			}
		}

	}

	/**
	 * Destructor for objects of class MyVM
	 */
	protected void finalize() throws Throwable {
		// this.si.getServerConnection().logout(); // do finalization here
		super.finalize(); // not necessary if extending Object.
	}

	/**
	 * Power On the Virtual Machine
	 */
	public boolean powerOn() {
		boolean state = false;
		try {
			System.out.println("command: powered on");
			Task task = vm.powerOnVM_Task(null);
			if (task.waitForTask() == Task.SUCCESS) {
				System.out.println(vmname + " powered on"); 
				state= true;
				return state;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} 
		return state;
	}

	/**
	 * Power Off the Virtual Machine
	 */
	public void powerOff() {
		try {
			System.out.println("command: powered off");
			Task task = vm.powerOffVM_Task();
			if (task.waitForTask() == Task.SUCCESS) {
				System.out.println(vmname + " powered off");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * Reset the Virtual Machine
	 */

	public void reset() {
		try {
			System.out.println("command: reset");
			Task task = vm.resetVM_Task();
			if (task.waitForTask() == Task.SUCCESS) {
				System.out.println(vmname + " reset");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * Suspend the Virtual Machine
	 */

	public void suspend() {
		try {
			System.out.println("command: suspend");
			Task task = vm.suspendVM_Task();
			if (task.waitForTask() == Task.SUCCESS) {
				System.out.println(vmname + " suspended");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public void standBy() 
    {
        try {
            System.out.println("command: stand by");
            vm.standbyGuest();
            System.out.println(vmname + " guest OS stoodby");
        } catch ( Exception e ) 
        { System.out.println( e.toString() ) ; }
    }
	
	public void setAlarm()
	{
		//System.out.println("In MyVM setAlarm()");
		try {
			vmname=this.vmname;
			//System.out.println("Vmname : "+vmname);
			createVmAlarm.createAlarm(vmname);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void getAlarm()
	{
		//vmname=this.vmname;
		InventoryNavigator inv = new InventoryNavigator(
		        si.getRootFolder());
		    VirtualMachine vm;
			try {
				vm = (VirtualMachine)inv.searchManagedEntity(
				        "VirtualMachine", vmname);
		   
		    if(vm==null)
		    {
		      System.out.println("Cannot find the VM " + vmname + "\nExisting...");
		     // si.getServerConnection().logout();
		      return;
		    }
		    
		    AlarmManager alarmMgr = si.getAlarmManager();
		    Alarm alarms[]=alarmMgr.getAlarm(vm);
		   
		    for(int i=0;i<alarms.length;i++)
		    {
		    	System.out.println("Alarm Name "+i+" "+alarms[i].getAlarmInfo().getName());
		    	
		    	//System.out.println(alarms[i].getAlarmInfo().getExpression());
		    }
		  
			} catch (InvalidProperty e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RuntimeFault e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public String getVMState()
	{
		
		VirtualMachineRuntimeInfo vmri=vm.getRuntime(); //Taken from package "com.....samples.vm", from file VmCdOp.java, VmNicOp.java, VMReconfig.java
		String state=vmri.getPowerState().toString(); // Taken from package "com.....samples.vm", from file GetUpdates.java, VMPowerOff.java
		
		return state;
	}
	
	synchronized public Boolean pingVM() throws IOException
    {
		String hostIp;
		Folder rootFolder=si.getRootFolder();
		
		VirtualMachine vm = (VirtualMachine) new InventoryNavigator(
		        rootFolder).searchManagedEntity(
		            "VirtualMachine", this.vmname);
		
		//TODO get parent info vm.
		//String ParentHost = vm.getParent().getMOR().toString();
		//System.out.println("ParentHost"+ParentHost);
		hostIp=vm.getGuest().getIpAddress();
		System.out.println("VM "+this.vmname+" : " +hostIp);
		
		String pingResult = " ";
		Boolean reachable = false;
		String pingCmd = "ping " + hostIp;

		/*while (!valueSet) {
			try {
				wait();
				} catch (InterruptedException e) {
			
					e.printStackTrace();
				}

			}*/
		
			Runtime r = Runtime.getRuntime();
			Process p = r.exec(pingCmd);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				//System.out.println(inputLine);
				pingResult += inputLine;
			}
			in.close();
		if (hostIp != null) {
			if (pingResult.contains("Destination host unreachable")) {
				System.out.println("Host Not Found/ Destination unreachable");
				reachable = false;
				valueSet = false;
			} else {
				System.out.println("VM is live. Pinging on: " + hostIp);
				reachable = true;
				notifyAll();
			}
		}
		else{
			System.out.println("VM IP is Null:"+hostIp);
			reachable=false;
			valueSet=false;
		}
		return reachable;
    }
	
	public Boolean pingHost() throws Exception {
		ServiceInstance sitemp = new ServiceInstance(new URL(SJSULAB.getVmwareHostURL1()), SJSULAB.getVmwareLogin1(), SJSULAB.getVmwarePassword1(), true);
		Folder rf = sitemp.getRootFolder();
		ManagedEntity[] vms = new InventoryNavigator(rf).searchManagedEntities(
				new String[][] { {"HostSystem", "name" }, }, true);
		
		String hostIp = SJSULAB.getVmwareHostIP();
		System.out.println("HostIp of my VHost : "+hostIp);
		String pingResult = " ";
		Boolean reach=false;
		String pingCmd = "ping " + hostIp;

		
		Runtime r = Runtime.getRuntime();
		System.out.println("Pinging Vhost "+hostIp+"...");
		Process p = r.exec(pingCmd);

		BufferedReader in = new BufferedReader(new
		InputStreamReader(p.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
		//System.out.println(inputLine);
		pingResult += inputLine;
		}		
		in.close();
		if(pingResult.contains("Destination host unreachable") || pingResult.contains("Request timed out"))
		{
			System.out.println("Host Not Found/ Destination unreachable");
			reach=false;
		}
		else
		{
			//System.out.println("Host is live. Pinging on: "+hostIp);	
			System.out.println("VHost "+hostIp+" is live.");
			reach=true;
		}
		return reach;
	}
	
	//public ManagedEntity[] getHosts()
	public Boolean pingSecondHost() throws IOException
    {
		Folder rootFolder=si.getRootFolder();
		String hostIp;
		ManagedEntity[] hosts = new InventoryNavigator(rootFolder).searchManagedEntities(
				new String[][] { {"HostSystem", "name" }, }, true);
		
		//return hosts
		
		for(int i=0;i<hosts.length;i++) {
			//System.out.println("Host"+i+": "+hosts[i].getName());
		}
		
		hostIp=hosts[0].getName();
		
		//public Boolean pingHost(hostIp)
		String newHostUrl="https://"+hosts[0].getName()+"/sdk";
		//System.out.println("NewHostURL : "+newHostUrl);
		
		ServiceInstance sitemp = new ServiceInstance(new URL(newHostUrl), SJSULAB.getVmwareLogin2(), SJSULAB.getVmwarePassword2(), true);
		Folder rf = sitemp.getRootFolder();
		ManagedEntity[] vms = new InventoryNavigator(rf).searchManagedEntities(
				new String[][] { {"VirtualMachine", "name" }, }, true);
		for(int i=0; i<vms.length; i++)
		{
			//System.out.println("vm["+i+"]=" + vms[i].getName());
			//System.out.println(vms[i].getParent());
			//System.out.println("");
			if(vms[i].getName().equalsIgnoreCase(vmname))
			{
				hostIp=hosts[1].getName();
				break;
			}
			
		}
		
		//
		String pingResult = " ";
		Boolean reach=false;
		String pingCmd = "ping " + hostIp;

		
		Runtime r = Runtime.getRuntime();
		Process p = r.exec(pingCmd);

		BufferedReader in = new BufferedReader(new
		InputStreamReader(p.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
		//System.out.println(inputLine);
		pingResult += inputLine;
		}		
		in.close();
		if(pingResult.contains("Destination host unreachable") || pingResult.contains("Request timed out"))
		{
			System.out.println("Alternate Host Not Found/ Destination unreachable");
			reach=false;
		}
		else
		{
			//System.out.println("Second Host is live. Pinging on: "+hostIp);	
			System.out.println("Alternate Host "+hostIp +" is alive");
			reach=true;
		}
		
		return reach;
    }

}

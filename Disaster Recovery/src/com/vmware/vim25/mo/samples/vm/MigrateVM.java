package com.vmware.vim25.mo.samples.vm;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import CONFIG.SJSULAB;

import com.vmware.vim25.HostVMotionCompatibility;
import com.vmware.vim25.TaskInfo;
import com.vmware.vim25.VirtualMachineMovePriority;
import com.vmware.vim25.VirtualMachinePowerState;
import com.vmware.vim25.mo.ComputeResource;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.HostSystem;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.Task;
import com.vmware.vim25.mo.VirtualMachine;

public class MigrateVM {
	String vmname;
	//String hostname;
	boolean flag;
	
	public MigrateVM() {

	}

	public boolean migrateVMToAnotherHost(String vmname1, String newHostName)
			throws Exception, MalformedURLException {
		System.out.println("Migrating "+vmname1+" to another host "+newHostName); 
		
		this.vmname = vmname1;
		ServiceInstance si = new ServiceInstance(new URL(
				SJSULAB.getVmwareHostURL()), SJSULAB.getVmwareLogin(),
				SJSULAB.getVmwarePassword(), true);

		 Folder rootFolder = si.getRootFolder();
		    VirtualMachine vm = (VirtualMachine) new InventoryNavigator(
		        rootFolder).searchManagedEntity(
		            "VirtualMachine", vmname);
		    HostSystem newHost = (HostSystem) new InventoryNavigator(
		        rootFolder).searchManagedEntity(
		            "HostSystem", newHostName);
		    ComputeResource cr = (ComputeResource) newHost.getParent();
		    
		    String[] checks = new String[] {"cpu", "software"};
		    HostVMotionCompatibility[] vmcs =
		      si.queryVMotionCompatibility(vm, new HostSystem[] 
		         {newHost},checks );
		    
		    String[] comps = vmcs[0].getCompatibility();
		    if(checks.length != comps.length)
		    {
		      System.out.println("CPU/software NOT compatible. Exit.");
		      si.getServerConnection().logout();
		      return false ;
		    }
		    
		    Task task = vm.migrateVM_Task(cr.getResourcePool(), newHost,
		        VirtualMachineMovePriority.highPriority, 
		        VirtualMachinePowerState.poweredOff);
		  
		    if(task.waitForTask()==Task.SUCCESS)
		    {
		      System.out.println("Migrated !!!");
		      flag = true;
		    }
		    else
		    {
		      System.out.println("VMotion failed!");
		      TaskInfo info = task.getTaskInfo();
		      System.out.println(info.getError().getFault());
		      flag = false;
		    }
		    si.getServerConnection().logout();
		    return flag;
	}

}

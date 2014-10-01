package com.vmware.vim25.mo.samples.vm;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import CONFIG.SJSULAB;

import com.vmware.vim25.HostVMotionCompatibility;
import com.vmware.vim25.InvalidProperty;
import com.vmware.vim25.RuntimeFault;
import com.vmware.vim25.TaskInfo;
import com.vmware.vim25.VirtualMachineCloneSpec;
import com.vmware.vim25.VirtualMachineMovePriority;
import com.vmware.vim25.VirtualMachinePowerState;
import com.vmware.vim25.VirtualMachineRelocateSpec;
import com.vmware.vim25.mo.ComputeResource;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.HostSystem;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.Task;
import com.vmware.vim25.mo.VirtualMachine;

public class CloneVM {
	String vmname;
	Boolean valueSet;
	private ServiceInstance si;
	
	public CloneVM() {
		try {
			this.si = new ServiceInstance(new URL(SJSULAB.getVmwareHostURL()),
					SJSULAB.getVmwareLogin(), SJSULAB.getVmwarePassword(), true);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	synchronized public boolean cloneFromSnapshot(String vmname1, String clonename1) throws Exception {
		valueSet = false; // this will stop ping and also create snapshots
		vmname1 = this.vmname;

		String cloneName = vmname + "_SS";
		
		System.out.println("CloneName : "+cloneName);

		Folder rootFolder = si.getRootFolder();
		VirtualMachine vm = (VirtualMachine) new InventoryNavigator(rootFolder)
				.searchManagedEntity("VirtualMachine", vmname1);

		if (vm == null) {
			System.out.println("No VM " + vmname1 + " found");
			// si.getServerConnection().logout();
			return false;
		}

		VirtualMachineCloneSpec cloneSpec = new VirtualMachineCloneSpec();
		cloneSpec.setLocation(new VirtualMachineRelocateSpec());
		cloneSpec.setPowerOn(false);
		cloneSpec.setTemplate(false);

		cloneSpec.snapshot = vm.getCurrentSnapShot().getMOR(); // SNE: added
																// this line

		Task task = vm.cloneVM_Task((Folder) vm.getParent(), cloneName,
				cloneSpec);
		System.out.println("Launching the VM clone task from SNAPSHOT.... "
				+ "Please wait ...");
		
		String status = task.waitForTask();
	    if(status==Task.SUCCESS)
	    {
	      System.out.println("VM got cloned successfully FROM SNAPSHOT....");
	      boolean migrateResult=migrateToAnotherHost(cloneName, "");
	      if(migrateResult)
	      {
	    	 notify();
	     	 return true;
	      }
	    }
	    else
	    {
	      System.out.println("Failure -: VM cannot be cloned FROM SNAPSHOT...");
	      
	    }
	    return false;

	}
	
	//method migrate to another host
		 public boolean migrateToAnotherHost(String vmname1, String newHostName1) {
			 System.out.println("In migrateToAnotherHost()");
			if (vmname1.equals(null) || newHostName1.equals(null)) {
				System.out.println("Usage: java MigrateVM <url> "
						+ "<username> <password> <vmname> <newhost>");
				System.exit(0);
			}
			
			valueSet=false;
			System.out.println("The value is false so it will not ping untill it migrate and starts...");
			
			String vmname=vmname1;
			//String vmname = vmname1;
			//String newHostName = newHostName1;
			String newHostName = "";
			try {
				Folder rootFolder = si.getRootFolder();
			
				VirtualMachine vm = (VirtualMachine) new InventoryNavigator(
						rootFolder).searchManagedEntity("VirtualMachine", vmname);
				
				
				//Start: To get another host IP
				
				ManagedEntity[] hosts = new InventoryNavigator(rootFolder).searchManagedEntities(
						new String[][] { {"HostSystem", "name" }, }, true);
				for(int i=0; i<hosts.length; i++)
				{
					System.out.println("host["+i+"]=" + hosts[i].getName());
				}
				
				
				newHostName=hosts[0].getName();
				System.out.println("newHostName : "+newHostName);
				String newHostUrl= "https://"+hosts[0].getName()+"/sdk";
				
				//ServiceInstance sitemp = new ServiceInstance(new URL(newHostUrl), SJSULAB.getVmwareLogin(), SJSULAB.getVmwarePassword(), true);
				ServiceInstance sitemp = new ServiceInstance(new URL(
						SJSULAB.getVmwareHostURL()), SJSULAB.getVmwareLogin(),
						SJSULAB.getVmwarePassword(), true);
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
						newHostName=hosts[1].getName();
						break;
					}
					
				}
				
				//End
				
				if(newHostName.equals(null)||newHostName.equalsIgnoreCase(""))
				{
					System.out.println("New Host is invalid OR Null");
					System.exit(0);
				}
				HostSystem newHost = (HostSystem) new InventoryNavigator(rootFolder).searchManagedEntity("HostSystem",newHostName);
				ComputeResource cr = (ComputeResource) newHost.getParent();
				
				String[] checks = new String[] { "cpu", "software" };
				HostVMotionCompatibility[] vmcs = si.queryVMotionCompatibility(vm,
						new HostSystem[] { newHost }, checks);

				String[] comps = vmcs[0].getCompatibility();
				if (checks.length != comps.length) {
					System.out.println("CPU/software NOT compatible. Exit.");
					//si.getServerConnection().logout();
					return false;
				}

				Task task = vm.migrateVM_Task(cr.getResourcePool(),newHost,
						VirtualMachineMovePriority.highPriority,
						VirtualMachinePowerState.poweredOff);
				

				if (task.waitForTask() == Task.SUCCESS) {
					System.out.println("VMotioned Migrated..!");
					
					//Before rename delete previous one
					VirtualMachine oldvm = (VirtualMachine) new InventoryNavigator(rootFolder)
					.searchManagedEntity("VirtualMachine", this.vmname);
					Task offoldvm=oldvm.powerOffVM_Task();
					if(offoldvm.waitForTask()==Task.SUCCESS)
					{
					Task removeOld = oldvm.destroy_Task();
					if(removeOld.waitForTask()==Task.SUCCESS)
					{
						System.out.println("Old Vm is deleted successfully...");
					}

					Task task1 = vm.rename_Task(this.vmname);
					if (task1.waitForTask() == Task.SUCCESS) {

						Task task2 = vm.powerOnVM_Task(newHost);
						if (task2.waitForTask() == Task.SUCCESS) {
							System.out.println("VM On! ");
							valueSet = true;
							return true;
							
						} else {
							System.out.println("Power On failed!");
						}
					} else {
						System.out.println("Not renamed..So no power on...");
					}
				} else {
					System.out.println("VMotion failed!");
					TaskInfo info = task.getTaskInfo();
					System.out.println(info.getError().getFault());
					
				}
				}
				else{
					System.out.println("Old Vm is not poweroff..So no Delete/ not renamed new one..");
				}
				System.out.println("In migrateToAnotherHost() end");
			} catch (InvalidProperty e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RuntimeFault e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
}
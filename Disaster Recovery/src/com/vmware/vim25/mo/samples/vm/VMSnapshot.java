package com.vmware.vim25.mo.samples.vm;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import CONFIG.SJSULAB;

import com.vmware.vim25.ManagedObjectReference;
import com.vmware.vim25.RuntimeFault;
import com.vmware.vim25.TaskInProgress;
import com.vmware.vim25.VirtualMachineSnapshotInfo;
import com.vmware.vim25.VirtualMachineSnapshotTree;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.Task;
import com.vmware.vim25.mo.VirtualMachine;
import com.vmware.vim25.mo.VirtualMachineSnapshot;
import java.util.Date;

public class VMSnapshot {

	private String vmname;
	private ServiceInstance si;
	static String snapName;
	Date date;
	int cCount = 0;
	int rCount = 0;

	public VMSnapshot() {
		try {
			this.si = new ServiceInstance(new URL(SJSULAB.getVmwareHostURL()),
					SJSULAB.getVmwareLogin(), SJSULAB.getVmwarePassword(), true);
			this.date = new Date();
		} catch (RemoteException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 synchronized public void snapShot(String vmname1, String op1)
			throws Exception {
		ServiceInstance si = new ServiceInstance(new URL(SJSULAB.getVmwareHostURL()),
				SJSULAB.getVmwareLogin(), SJSULAB.getVmwarePassword(), true);
		//System.out.println("In VMSnapshot/snapshot()");
		if (vmname1.equals(null) || op1.equals(null)) {
			System.out
					.println("VMname or Operation is null. Please provide the parameters.");
			System.exit(0);
		}

		this.vmname = vmname1;
		String op = op1;

		//System.out.println("In VMSnapshot snapshot 1");

		// please change the following three depending your op
		String snapshotname = "test";
		String desc = "A description for sample snapshot";
		boolean removechild = true;

		//System.out.println("In VMSnapshot snapshot 2");

		Folder rootFolder = si.getRootFolder();
		VirtualMachine vm = (VirtualMachine) new InventoryNavigator(rootFolder)
				.searchManagedEntity("VirtualMachine", vmname);

		if (vm == null) {
			System.out.println("No VM " + vmname + " found");
			//si.getServerConnection().logout();
			return;
		}

		if ("create".equalsIgnoreCase(op)) {
			System.out.println("Creating a Snapshot...");
			Task task = vm
					.createSnapshot_Task(snapshotname, desc, false, false);
			cCount++;
			if (task.waitForTask() == Task.SUCCESS) {
				System.out.println("Snapshot "+cCount+" was created at "+date.toString());
			}
		} else if ("list".equalsIgnoreCase(op)) {
			listSnapshots(vm);
		} else if (op.equalsIgnoreCase("revert")) {
			System.out.println("Trying to revert using old snapshot");
			VirtualMachineSnapshot vmsnap = getSnapshotInTree(vm, snapshotname);
			if (vmsnap != null) {
				Task task = vmsnap.revertToSnapshot_Task(null);
				if (task.waitForTask() == Task.SUCCESS) {
					System.out.println("Reverted to snapshot:" + snapshotname);
				}
			}
		} else if (op.equalsIgnoreCase("removeall")) {
			Task task = vm.removeAllSnapshots_Task();
			if (task.waitForTask() == Task.SUCCESS) {
				System.out.println("Removed all snapshots");
			}
		} else if (op.equalsIgnoreCase("remove")) {
			VirtualMachineSnapshot vmsnap = getSnapshotInTree(vm, snapshotname);
			if (vmsnap != null) {
				Task task = vmsnap.removeSnapshot_Task(removechild);
				rCount++;
				if (task.waitForTask() == Task.SUCCESS) {
					System.out.println("Removed snapshot: "+rCount + " " +snapshotname);
				}
			}
		} else {
			System.out.println("Invalid operation");
			return;
		}
		//si.getServerConnection().logout();

	}

	static VirtualMachineSnapshot getSnapshotInTree(VirtualMachine vm,
			String snapName) {
		if (vm == null || snapName == null) {
			return null;
		}

		VirtualMachineSnapshotTree[] snapTree = vm.getSnapshot()
				.getRootSnapshotList();
		if (snapTree != null) {
			ManagedObjectReference mor = findSnapshotInTree(snapTree, snapName);
			if (mor != null) {
				return new VirtualMachineSnapshot(vm.getServerConnection(), mor);
			}
		}
		return null;
	}

	static ManagedObjectReference findSnapshotInTree(
			VirtualMachineSnapshotTree[] snapTree, String snapName) {
		for (int i = 0; i < snapTree.length; i++) {
			VirtualMachineSnapshotTree node = snapTree[i];
			if (snapName.equals(node.getName())) {
				return node.getSnapshot();
			} else {
				VirtualMachineSnapshotTree[] childTree = node
						.getChildSnapshotList();
				if (childTree != null) {
					ManagedObjectReference mor = findSnapshotInTree(childTree,
							snapName);
					if (mor != null) {
						return mor;
					}
				}
			}
		}
		return null;
	}

	static void listSnapshots(VirtualMachine vm) {
		if (vm == null) {
			return;
		}
		VirtualMachineSnapshotInfo snapInfo = vm.getSnapshot();
		VirtualMachineSnapshotTree[] snapTree = snapInfo.getRootSnapshotList();
		printSnapshots(snapTree);
	}

	static void printSnapshots(VirtualMachineSnapshotTree[] snapTree) {
		for (int i = 0; snapTree != null && i < snapTree.length; i++) {
			VirtualMachineSnapshotTree node = snapTree[i];
			System.out.println("Snapshot Name : " + node.getName());
			VirtualMachineSnapshotTree[] childTree = node
					.getChildSnapshotList();
			if (childTree != null) {
				printSnapshots(childTree);
			}
		}
	}
	
	static void deletePreviousSnap(VirtualMachine vm,String snapName)
	{
		if (vm == null ) 
	    {
	      //
			System.out.println("In delete PreSnap: Cannot delete snapshot because no VM found..");
	    }

	    VirtualMachineSnapshotTree[] snapTree = 
	        vm.getSnapshot().getRootSnapshotList();
	    if(snapTree!=null)
	    {
	      ManagedObjectReference mor = findSnapshotInTree(
		          snapTree, snapName);
		      
		      if(mor!=null)
		      {
			       VirtualMachineSnapshot vsnap=new VirtualMachineSnapshot(vm.getServerConnection(), mor);
			       try {
					vsnap.removeSnapshot_Task(false);
				} catch (TaskInProgress e) {
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
	    }
	    else{
	    	 System.out.println("SnapTree is null So cannot delete Snapshot..");
	    }
	}

}

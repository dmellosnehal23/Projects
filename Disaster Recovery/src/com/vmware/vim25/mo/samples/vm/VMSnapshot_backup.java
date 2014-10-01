package com.vmware.vim25.mo.samples.vm;

import CONFIG.SJSULAB;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import com.vmware.vim25.InvalidProperty;
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


public class VMSnapshot_backup {
	private String vmname;
	private ServiceInstance si;
	static String snapName;
	
	public VMSnapshot_backup() {
		try {
			this.si = new ServiceInstance(new URL(SJSULAB.getVmwareHostURL()),
					SJSULAB.getVmwareLogin(), SJSULAB.getVmwarePassword(), true);
		} catch (RemoteException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	synchronized public void snapShot(String vmname1, String op1) throws Exception {
		System.out.println("In VMSnapshot snapshot");
		if (vmname1.equals(null) || op1.equals(null)) {
			System.out.println("Usage: java VMSnapshot <url> "
					+ "<username> <password> <vmname> <op>");
			System.out.println("op - list, create, remove, "
					+ "removeall, revert");
			System.exit(0);
		}
		
		 this.vmname= vmname1;
		//String vmname = vmname1;
		System.out.println("VMname : "+this.vmname);
		String op = op1;
		
		System.out.println("In VMSnapshot snapshot 1");
		
		//please change the following three depending your op
	    String snapshotname = "test1";
	    String desc = "A description for sample snapshot";
	    boolean removechild = true;
	    
	    System.out.println("In VMSnapshot snapshot 2");
	    
	    Folder rootFolder = si.getRootFolder();
	    VirtualMachine vm = (VirtualMachine) new InventoryNavigator(
	      rootFolder).searchManagedEntity("VirtualMachine", vmname);
	    
	    System.out.println("In VMSnapshot snapshot 3"); 
	    
	    try {

			if (vm == null) {
				System.out.println("No VM " + vmname + " found");
				//si.getServerConnection().logout();
				return;	
			}
			
			System.out.println("In VMSnapshot snapshot 4");
			
			if("create".equalsIgnoreCase(op)) {
				System.out.println("In VMSnapshot snapshot 5");
				/* I commented this one
				if(snapName.equalsIgnoreCase("test2"))
				{
					System.out.println("In VMSnapshot snapshot 6");
					snapName="test1";
				}
				else if(snapName.equalsIgnoreCase("test1"))
				{
					snapName="test2";
				}
				Task task = vm.createSnapshot_Task(snapName, desc, false,false);
				*/
				Task task = vm.createSnapshot_Task(snapshotname, desc, false,false);
				System.out.println("In VMSnapshot snapshot 7");
				if (task.waitForTask() == Task.SUCCESS) {
					System.out.println("Snapshot was created.");
					
					try {
						if(snapName.equalsIgnoreCase("test1"))
						{
							deletePreviousSnap(vm,"test2");	
						}
						else{
							deletePreviousSnap(vm,"test1");
						}
						//delete previous snapshot
						//cloneFromSnapshot(vmname, vmname+"_fromSS");	//code for snapshot to vm 
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//valueSet=true;  // SNE: Check this code again
					notify();
				}				
			}else if ("list".equalsIgnoreCase(op)) {
				listSnapshots(vm);
			} else if (op.equalsIgnoreCase("revert")) {
				VirtualMachineSnapshot vmsnap = getSnapshotInTree(vm,
						snapshotname);
				if (vmsnap != null) {
					Task task = vmsnap.revertToSnapshot_Task(null);
					if (task.waitForTask() == Task.SUCCESS) {
						System.out.println("Reverted to snapshot:"
								+ snapshotname);	
					}
				//Code to migrate	
					//migrateToAnotherHost(vmname, "");		
				}
			} else if (op.equalsIgnoreCase("removeall")) {
				Task task = vm.removeAllSnapshots_Task();
				if (task.waitForTask() == Task.SUCCESS) {
					System.out.println("Removed all snapshots");
				}
			} else if (op.equalsIgnoreCase("remove")) {
				VirtualMachineSnapshot vmsnap = getSnapshotInTree(vm,
						snapshotname);
				if (vmsnap != null) {
					Task task = vmsnap.removeSnapshot_Task(removechild);
					if (task.waitForTask() == Task.SUCCESS) {
						System.out.println("Removed snapshot:" + snapshotname);
					}
				}
			}else if (op.equalsIgnoreCase("deleteP")) {
				deletePreviousSnap(vm,snapshotname);
			} 
			else {
				System.out.println("Invalid operation");
				return;
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
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
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
	
	static VirtualMachineSnapshot getSnapshotInTree(VirtualMachine vm, String snapName)
	  {
	    if (vm == null || snapName == null) 
	    {
	      return null;
	    }

	    VirtualMachineSnapshotTree[] snapTree = 
	        vm.getSnapshot().getRootSnapshotList();
	    if(snapTree!=null)
	    {
	      ManagedObjectReference mor = findSnapshotInTree(
	          snapTree, snapName);
	      
	      if(mor!=null)
	      {
	        return new VirtualMachineSnapshot(
	            vm.getServerConnection(), mor);
	      }
	    }
	    return null;
	  }
	
	 static ManagedObjectReference findSnapshotInTree(
		      VirtualMachineSnapshotTree[] snapTree, String snapName)
		  {
		    for(int i=0; i <snapTree.length; i++) 
		    {
		      VirtualMachineSnapshotTree node = snapTree[i];
		      if(snapName.equals(node.getName()))
		      {
		        return node.getSnapshot();
		        
		      } 
		      else 
		      {
		        VirtualMachineSnapshotTree[] childTree = 
		            node.getChildSnapshotList();
		        if(childTree!=null)
		        {
		          ManagedObjectReference mor = findSnapshotInTree(
		              childTree, snapName);
		          if(mor!=null)
		          {
		            return mor;
		          }
		        }
		      }
		    }
		    return null;
		  }
	 
	 static void listSnapshots(VirtualMachine vm)
	  {
	    if(vm==null)
	    {
	      return;
	    }
	    VirtualMachineSnapshotInfo snapInfo = vm.getSnapshot();
	    VirtualMachineSnapshotTree[] snapTree = 
	      snapInfo.getRootSnapshotList();
	    printSnapshots(snapTree);
	  }
	 
	 static void printSnapshots(
		      VirtualMachineSnapshotTree[] snapTree)
		  {
		    for (int i = 0; snapTree!=null && i < snapTree.length; i++) 
		    {
		      VirtualMachineSnapshotTree node = snapTree[i];
		      System.out.println("Snapshot Name : " + node.getName());           
		      VirtualMachineSnapshotTree[] childTree = 
		        node.getChildSnapshotList();
		      if(childTree!=null)
		      {
		        printSnapshots(childTree);
		      }
		    }
		  }
}
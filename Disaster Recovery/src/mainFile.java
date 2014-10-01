import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;

import CONFIG.SJSULAB;

import com.vmware.vim25.InvalidProperty;
import com.vmware.vim25.RuntimeFault;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;
import com.vmware.vim25.mo.samples.vm.CloneVM;
import com.vmware.vim25.mo.samples.vm.MigrateVM;
import com.vmware.vim25.mo.samples.vm.VMSnapshot;
import com.vmware.vim25.mo.samples.vm.VMSnapshot_backup;

public class mainFile {

	VMSnapshot vmSnap;
	VirtualMachine vm;
	Statistics stats;
	ServiceInstance si;

	Folder rootFolder = si.getRootFolder();

	public mainFile() {
		// vmSnap = new VMSnapshot();
		try {
			this.vm = (VirtualMachine) new InventoryNavigator(rootFolder)
					.searchManagedEntity("VirtualMachine", "Team07_Snehal");

			this.si = new ServiceInstance(new URL(SJSULAB.getVmwareHostURL()),
					SJSULAB.getVmwareLogin(), SJSULAB.getVmwarePassword(), true);
			
			//this.stats = new Statistics();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void getStatistics() {
		try {
			//stats.getStats(vm, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	public void foo() {
		System.out.println("Hi");
		final MyVM myVM = new MyVM("Team07_Snehal");
		// VMSnapshot_backup vmSnap = new VMSnapshot_backup();
		VMSnapshot vmSnap = new VMSnapshot();
		CloneVM cloneVM = new CloneVM();
		MigrateVM mVM = new MigrateVM();

		// myVM.setAlarm();
		// myVM.powerOff();

		/*
		  Thread t1 = new Thread() { 
			  VMSnapshot vmSnap = new VMSnapshot();
			  public void run() { 
				  int count = 0; 
				  System.out.println("In run()");
				  while(true){ 
					  try { 
						  vmSnap.snapShot("Team07_Snehal", "create");
						  count++; 
						  Thread.sleep(1000 * 60 * 10); 
						  if(count == 2) {
							  vmSnap.snapShot("Team07_Snehal", "remove");
							  System.out.println("Snapshot removed");
						  } 
					  } catch (Exception e) { 
						  e.printStackTrace();
					  } 
				} 
			} 
		};
		  
		  t1.start();*/
		 

		try {
			// boolean result = myVM.pingVM();
			// myVM.pingVM_Snehal();
			// myVM.setAlarm();
			// myVM.pingVM();
			boolean pingHost = myVM.pingHost();

			if (pingHost == true) {
				System.out.println("VHost is pinging..");
				// }else {
				// cloneVM.migrateToAnotherHost("Team07_Snehal",
				// "130.65.132.191");
				//cloneVM.cloneFromSnapshot("Team07_Snehal","Team07_Snehal_Clone");
				 mVM.migrateVMToAnotherHost("Team07_Snehal","130.65.132.191");
				//getStatistics();
			}
			// myVM.pingSecondHost();
			// String state = myVM.getVMState();
			// System.out.println("State = "+state);

			// boolean hresult = myVM.pingSecondHost();
			/*
			 * for(int i=0; i<10;i++) { vmSnap.snapShot("Team07_Snehal",
			 * "create"); }
			 */
			// vmSnap.snapShot("Team07_Snehal", "list");
			// cloneVM.cloneFromSnapshot("T07-Snehal", "T07-Snehal_Clone");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void main(String args[]) throws Exception {
		//Statistics stats = new Statistics();
		mainFile mf = new mainFile();
		mf.foo();	
	}
}

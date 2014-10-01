import java.io.IOException;
import java.net.MalformedURLException;

import com.vmware.vim25.mo.samples.alarm.CreateVmAlarm;
import com.vmware.vim25.mo.samples.vm.CloneVM;
import com.vmware.vim25.mo.samples.vm.MigrateVM;
import com.vmware.vim25.mo.samples.vm.VMSnapshot;
import com.vmware.vim25.mo.samples.vm.VMSnapshot_backup;


public class AvailabilityManager {
	VMSnapshot vmSnapshot;
	//VMSnapshot vmSnap;
	CreateVmAlarm createAlarm;
	MyVM myVM;
	CloneVM cloneVM;
	MigrateVM migrateVM;
	Statistics stats;
	

	public AvailabilityManager() throws MalformedURLException, Exception {
		vmSnapshot = new VMSnapshot();
		//vmSnap = new VMSnapshot();
		createAlarm = new CreateVmAlarm("Team07_Snehal");
		myVM = new MyVM("Team07_Snehal");
		cloneVM = new CloneVM();
		migrateVM = new MigrateVM();
		stats = new Statistics("Team07_Snehal");
	}
	
	public static void main(String[] args) throws MalformedURLException, Exception {

		AvailabilityManager am = new AvailabilityManager();
		am.startAM();	
	}
	
	private void startAM() {
		System.out.println("Creating a text file for displaying statistics");
		stats = new Statistics("Team07_Snehal");
		stats.getStats();
		// start a snapshot thread
		try{
		 Thread t1 = new Thread() { 
			  VMSnapshot vmSnap = new VMSnapshot();
			  public void run() { 
				  int count = 0; 
				  //System.out.println("In run()");
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
		
		t1.start();
		
		//Thread.sleep(15000);
	 	// Setup alarm on poweroff
	 	myVM.setAlarm();
	 	
	 	//Primary host 
	 	while(true) {
	 		System.out.println("*******************");
	 		try{
	 			boolean pingVH = myVM.pingHost(); //pass primary host
	 			
	 			if(pingVH == true) {
	 				System.out.println("VHOST IS PINGING.");
	 				Thread.sleep(5000);
	 				
	 				boolean pingVM = myVM.pingVM(); 
	 				if(pingVM == true) {
	 					System.out.println("VM is PINGING.");
	 					Thread.sleep(5000);
	 					//add sleep
	 				}
	 				else {
	 					String state = myVM.getVMState();
	 					System.out.println("State of VM: "+state);
	 					if(state.equalsIgnoreCase("poweredoff")) {
	 						System.out.println("VM IS POWERED OFF BY USER...TRYING TO POWER ON..");
	 						boolean poweron = myVM.powerOn();
	 						if(poweron == true)
	 							System.out.println("VM IS POWERED ON.");
	 						else{
	 							System.out.println("VM CANNOT BE POWERED ON..SO IT IS A FAILURE.");
	 							vmSnapshot.snapShot("Team07_Snehal", "revert");
	 						}
	 					}
	 					else {
	 						System.out.println("STATE IS POWERED ON...SO IT IS A FAILURE");
	 						// Write code for clone from snapshot in d same host.
	 					}
	 				}
	 			}
	 			else {
	 			//if(myVM.pingSecondHost() == true){
	 				// Get hosts and choose 0th available and primary host = hosts[0]
	 				boolean pingSecondVM = myVM.pingSecondHost(); // Call ping Host and pass new Ip.
	 				if(pingSecondVM == true) {
	 					System.out.println("VHOST IS DOWN...SO MIGRATING VIRTUAL MACHINE.");
	 					//System.out.println("ALTERNATE HOST ALIVE...MIGRATING VM to 130.65.132.192");
	 					migrateVM.migrateVMToAnotherHost("Team07_Snehal", "130.65.132.192");
	 				} 
	 				//print else and break
	 			}
	 			
	 		}catch(Exception e){
	 			e.printStackTrace();
	 		}
	 	}
	} catch(Exception e) 
		{e.printStackTrace();}
	}
}

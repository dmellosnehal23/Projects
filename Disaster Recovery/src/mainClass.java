// @author: SNEHAL D'MELLO

import java.io.IOException;

import com.vmware.vim25.mo.samples.alarm.CreateVmAlarm;
import com.vmware.vim25.mo.samples.vm.CloneVM;
import com.vmware.vim25.mo.samples.vm.VMSnapshot_backup;

public class mainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		final MyVM myVM = new MyVM("Team07_Snehal");
		myVM.setAlarm();
		
		final VMSnapshot_backup vmSnapshot = new VMSnapshot_backup();
		final CloneVM cloneVM = new CloneVM();
		
		Thread t1 = new Thread() {
			public void run() {
				while(true) {
					try{
						boolean result = myVM.pingVM();
						if (result == true) {
					
							System.out.println("VM is pinging..");
						} else {
							String state = myVM.getVMState();
							if (state.equalsIgnoreCase("poweredoff")) {
								System.out.println("VM is Powered off by user");
							} 
							else {
								System.out.println("State is Powered On...So its a failure");
								boolean hresult = myVM.pingSecondHost();
								if (hresult == true) {
									System.out.println("Another Host is working....Now Cloning and Migrating will be done ");
									try {
										cloneVM.cloneFromSnapshot("", "");    // clone from snapshot in same host
					

								} catch (Exception e) {
									e.printStackTrace();
								}
								
								} else {
									System.out.println("Second Host is not working..Wait till it resumes..");
								}
							}
						}
						
					} catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		Thread t2 = new Thread() {
			public void run(){
	 			while(true)
	 			{
	 				
	 				try {
	 					vmSnapshot.snapShot("", "create");
						Thread.sleep(30000);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	 			}
	 		}
		};
		myVM.getAlarm();	//get Alarm and start monitoring
	 	t1.start();
	 	t2.start();

	}

}

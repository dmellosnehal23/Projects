import com.vmware.vim25.mo.samples.vm.MigrateVM;
import com.vmware.vim25.mo.samples.vm.VMSnapshot;


public class ABC {


	public static void main(String[] args) {
		System.out.println("Hi");
		
		try{
			MyVM myVM = new MyVM("Team07_Snehal");
			MigrateVM mVM = new MigrateVM();
			VMSnapshot vmSnapshot = new VMSnapshot();
			Statistics stats = new Statistics("Team07_Snehal");
			//vmSnapshot.snapShot("Team07_Snehal", "create");
			//vmSnapshot.snapShot("Team07_Snehal", "remove");
			String state = myVM.getVMState();
			System.out.println(state);
			System.out.println("Displaying stats: ");
			stats.getStats();
			//mVM.migrateVMToAnotherHost("Team07_Snehal","130.65.132.192");
			
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}

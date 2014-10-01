import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.vmware.vim25.Action;
import com.vmware.vim25.AlarmAction;
import com.vmware.vim25.AlarmSetting;
import com.vmware.vim25.AlarmSpec;
import com.vmware.vim25.AlarmState;
import com.vmware.vim25.AlarmTriggeringAction;
import com.vmware.vim25.HostSystemPowerState;
import com.vmware.vim25.InvalidProperty;
import com.vmware.vim25.ManagedEntityStatus;
import com.vmware.vim25.MethodAction;
import com.vmware.vim25.MethodActionArgument;
import com.vmware.vim25.RuntimeFault;
import com.vmware.vim25.SendEmailAction;
import com.vmware.vim25.StateAlarmExpression;
import com.vmware.vim25.StateAlarmOperator;
import com.vmware.vim25.mo.AlarmManager;
import com.vmware.vim25.mo.HostSystem;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;

/**
 * http://vijava.sf.net
 * 
 * @author Kiran
 */

public class DPM implements Runnable {
	private static HashMap<HostSystem, Boolean> host_list = new HashMap<HostSystem, Boolean>();
	private static HashMap<HostSystem, Integer> vmNumber = new HashMap<HostSystem, Integer>();
	private static HashMap<String, Boolean> hostPoweredOff = new HashMap<String, Boolean>(); // Sting =hostname
	
	CPUUsage cpuUsage;

	public static void main(String[] args) throws InvalidProperty, RuntimeFault, RemoteException, MalformedURLException {
		cpuUsage = new CPUUsage();
		ManagedEntity[] hosts;
		try {
			hosts = cpuUsage.getHosts();
			System.out.println("Hello");
			for (ManagedEntity h : hosts) {
				System.out.println("Hello11");
				HostSystem h1 = (HostSystem) h;
				hostPoweredOff.put(h1.getName(), false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		new Thread(new DPM()).start();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			try {

				ManagedEntity[] hosts = cpuUsage.getHosts();

				Set<String> offset = hostPoweredOff.keySet();
				int count = 0;
				for (String name : offset) {

					if (!hostPoweredOff.get(name)) {

						count++;
					}

				}

				if (count == 1) {

					System.out.println("Only One host Left.Turning off DPM");

					return;
				}
				host_list.clear();
				//System.out.println("Hello");
				for (ManagedEntity h : hosts) {
					HostSystem h1 = (HostSystem) h;

					try {
						host_list.put(h1, CPUUsage.isLessThan30(h1.getName()));
						vmNumber.put(h1, h1.getVms().length);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				Set<HostSystem> host_set = host_list.keySet();

				HostSystem toremove = null;
				for (HostSystem s1 : host_set) {

					if (s1.getRuntime().getPowerState().compareTo(
							HostSystemPowerState.poweredOff) != 0) {

						if (!hostPoweredOff.get(s1.getName())
								&& host_list.get(s1)) {

							System.out.println("The Host " + s1.getName()
									+ " is using less than 30%");
							migrateVMS(s1);
							toremove = s1;
							break;
							/*
							 * try {
							 * System.out.println("Thread Sleeping for some time"
							 * ); Thread.sleep(1 * 60 * 1000);
							 * 
							 * } catch (InterruptedException e) { // TODO
							 * Auto-generated catch block e.printStackTrace(); }
							 * continue;
							 */

						}
					}

				}

				if (null != toremove)
					hostPoweredOff.put(toremove.getName(), true);

			} catch (InvalidProperty e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RuntimeFault e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				System.out.println("Thread Sleeping for some time");
				Thread.sleep(1 * 60 * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void migrateVMS(HostSystem from) throws InvalidProperty,
			RuntimeFault, RemoteException, MalformedURLException,
			InterruptedException {
		// TODO Auto-generated method stub

		Set<HostSystem> vms = vmNumber.keySet();
		HostSystem to = null;
		System.out.println("Choosing the to host to the one with lesser VMS");
		for (HostSystem h : vms) {

			if (!hostPoweredOff.get(h.getName())
					&& !h.getName().equalsIgnoreCase(from.getName())) {

				if (to == null)
					to = h;
				if (vmNumber.get(to) > vmNumber.get(h))
					to = h;
			}

		}

		for (VirtualMachine v : from.getVms()) {

			DRS2 drs = new DRS2();

			System.out.println("Migrating VM:" + v.getName() + "  from "
					+ from.getName() + " to " + to.getName());

			drs.migrateToAnotherHost(v.getName(), to.getName());
			System.out.println("Successfully migrated VM:" + v.getName()
					+ "  from " + from.getName() + " to " + to.getName());

		}
		System.out.println("Powering off VHOST:" + from.getName());
		CPUUsage.powerOffVhost(from.getName());

	}

}
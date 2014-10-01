package com.vmware.vim25.mo.samples.alarm;

import CONFIG.SJSULAB;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import com.vmware.vim25.Action;
import com.vmware.vim25.AlarmAction;
import com.vmware.vim25.AlarmSetting;
import com.vmware.vim25.AlarmSpec;
import com.vmware.vim25.AlarmTriggeringAction;
import com.vmware.vim25.GroupAlarmAction;
import com.vmware.vim25.MethodAction;
import com.vmware.vim25.MethodActionArgument;
import com.vmware.vim25.RuntimeFault;
import com.vmware.vim25.SendEmailAction;
import com.vmware.vim25.StateAlarmExpression;
import com.vmware.vim25.StateAlarmOperator;
import com.vmware.vim25.mo.Alarm;
import com.vmware.vim25.mo.AlarmManager;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;

public class CreateVmAlarm {
	ServiceInstance si;
	VirtualMachine vm;
	InventoryNavigator inv;
	Alarm myAlarm;

	public CreateVmAlarm(String vmName) throws Exception, MalformedURLException {
		this.si = new ServiceInstance(new URL(SJSULAB.getVmwareHostURL()),
				SJSULAB.getVmwareLogin(), SJSULAB.getVmwarePassword(), true);

		inv = new InventoryNavigator(si.getRootFolder());

		vm = (VirtualMachine) inv.searchManagedEntity("VirtualMachine", vmName);
	}

	public static void createAlarm(String vmName) throws Exception {
		System.out.println("Setting up an alarm...");
		ServiceInstance si = new ServiceInstance(new URL(
				SJSULAB.getVmwareHostURL()), SJSULAB.getVmwareLogin(),
				SJSULAB.getVmwarePassword(), true);

		InventoryNavigator inv = new InventoryNavigator(si.getRootFolder());
		VirtualMachine vm = (VirtualMachine) inv.searchManagedEntity(
				"VirtualMachine", vmName);

		if (vm == null) {
			System.out
					.println("Cannot find the VM " + vmName + "\nExisting...");
			// si.getServerConnection().logout();
			return;
		}

		AlarmManager alarmMgr = si.getAlarmManager();

		// /* Added new Code
		// This will remove alarm if it exists...

		Alarm alarms[] = alarmMgr.getAlarm(vm);
	
			for (int i = 0; i < alarms.length; i++) {
				if (alarms[i].getAlarmInfo().getName()
						.equalsIgnoreCase("VmPowerStateAlarm")) {
					alarms[i].removeAlarm();
				}
			}
		// Added new Code */

		AlarmSpec spec = new AlarmSpec();

		StateAlarmExpression expression = createStateAlarmExpression();
		AlarmAction emailAction = createAlarmTriggerAction(Action());
		// AlarmAction methodAction =
		// createAlarmTriggerAction(createPowerOnAction());
		// GroupAlarmAction gaa = new GroupAlarmAction();

		// gaa.setAction(new AlarmAction[]{emailAction, methodAction});
		// spec.setAction(gaa);

		/* Original Code */
		AlarmAction methodAction = createAlarmTriggerAction(createPowerOnAction());
		GroupAlarmAction gaa = new GroupAlarmAction();

		gaa.setAction(new AlarmAction[] { emailAction, methodAction });
		spec.setAction(gaa);
		/* Original Code */

		// spec.setAction(emailAction);// I added this line
		spec.setExpression(expression);
		spec.setName("VmPowerStateAlarm");
		spec.setDescription("Monitor VM state and send email "
				+ "and power it on if VM powers off");
		spec.setEnabled(true);

		AlarmSetting as = new AlarmSetting();
		as.setReportingFrequency(0); // as often as possible
		as.setToleranceRange(0);

		spec.setSetting(as);
		// System.out.println(vm.getName());
		alarmMgr.createAlarm(vm, spec);
		System.out.println("Alarm created.");
		// si.getServerConnection().logout();
	}
/*
	public static void remove() { 
		AlarmManager alarmMgr = this.si.getAlarmManager(); Alarm alarms[] = alarmMgr.getAlarm(vm); 
		for(int i = 0; i < alarms.length; i++) { 
			if (alarms[i].getAlarmInfo().getName()
	 .equalsIgnoreCase("VmPowerStateAlarm")) { alarms[i].removeAlarm(); } } }*/
	
	 public void disableAlarm() throws RuntimeFault, RemoteException{
		  System.out.println("disable");  
		  AlarmSpec spec = new AlarmSpec();
		    
		    StateAlarmExpression expression = 
		      createStateAlarmExpression();
		    AlarmAction emailAction = createAlarmTriggerAction(Action());
		    AlarmAction methodAction = createAlarmTriggerAction(
		        createPowerOnAction());
		    GroupAlarmAction gaa = new GroupAlarmAction();

		    gaa.setAction(new AlarmAction[]{emailAction, methodAction});
		    spec.setAction(gaa);
		    spec.setExpression(expression);
		    spec.setName("VmPowerAlarm");
		    spec.setDescription("Monitor VM state and send email " +
		    		"and power it on if VM powers off");
		    spec.setEnabled(false);    
		    
		    AlarmSetting as = new AlarmSetting();
		    as.setReportingFrequency(0); //as often as possible
		    as.setToleranceRange(0);
		    
		    spec.setSetting(as);
		    this.myAlarm.reconfigureAlarm(spec);		    
	  }

	static StateAlarmExpression createStateAlarmExpression() {
		StateAlarmExpression expression = new StateAlarmExpression();
		expression.setType("VirtualMachine");
		expression.setStatePath("runtime.powerState");
		expression.setOperator(StateAlarmOperator.isEqual);
		expression.setRed("poweredOff");
		return expression;
	}

	static MethodAction createPowerOnAction() {
		// System.out.println("In CreateVmAlarm createPowerOnAction()");
		MethodAction action = new MethodAction();
		action.setName("PowerOnVM_Task");
		MethodActionArgument argument = new MethodActionArgument();
		argument.setValue(null);
		action.setArgument(new MethodActionArgument[] { argument });
		return action;
	}

	static SendEmailAction Action() {
		// System.out.println("In CreateVmAlarm Action()");
		SendEmailAction action = new SendEmailAction();
		action.setToList("dmellosnehal23@gmail.com");
		action.setCcList("snehal.v.dmello@gmail.com");
		action.setSubject("Alarm - {alarmName} on {targetName}\n");
		action.setBody("Description:{eventDescription}\n"
				+ "TriggeringSummary:{triggeringSummary}\n"
				+ "newStatus:{newStatus}\n" + "oldStatus:{oldStatus}\n"
				+ "target:{target}");
		return action;
	}

	static AlarmTriggeringAction createAlarmTriggerAction(Action action) {
		AlarmTriggeringAction alarmAction = new AlarmTriggeringAction();
		alarmAction.setYellow2red(true);
		alarmAction.setAction(action);
		return alarmAction;
	}

}
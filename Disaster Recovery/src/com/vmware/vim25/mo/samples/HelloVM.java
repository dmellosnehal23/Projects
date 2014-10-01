/*================================================================================
Copyright (c) 2008 VMware, Inc. All Rights Reserved.

Redistribution and use in source and binary forms, with or without modification, 
are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, 
this list of conditions and the following disclaimer.

 * Redistributions in binary form must reproduce the above copyright notice, 
this list of conditions and the following disclaimer in the documentation 
and/or other materials provided with the distribution.

 * Neither the name of VMware, Inc. nor the names of its contributors may be used
to endorse or promote products derived from this software without specific prior 
written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL VMWARE, INC. OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, 
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT 
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.
================================================================================*/

package com.vmware.vim25.mo.samples;

import java.net.URL;
import com.vmware.vim25.*;
import com.vmware.vim25.mo.*;

public class HelloVM {
	// public static void main(String[] args) throws Exception {

	public void helloVM() {
		
		try{
		long start = System.currentTimeMillis();
		URL url = new URL("https://130.65.132.190/sdk");
		ServiceInstance si = new ServiceInstance(url, "administrator",
				"12!@qwQW", true);
		long end = System.currentTimeMillis();
		System.out.println("time taken:" + (end - start));
		Folder rootFolder = si.getRootFolder();
		String name = rootFolder.getName();
		System.out.println("root:" + name);
		ManagedEntity[] mes = new InventoryNavigator(rootFolder)
				.searchManagedEntities("VirtualMachine");
		
		if (mes == null || mes.length == 0) {
			return;
		}
		
		for (int i = 0; i < mes.length; i++) {

			VirtualMachine vm = (VirtualMachine) mes[i];

			VirtualMachineConfigInfo vminfo = vm.getConfig();
			VirtualMachineCapability vmc = vm.getCapability();

			System.out.println("Resource Pool: " + vm.getResourcePool());
			// /* Added code here
			System.out.println("Resource pool Owner: "
					+ vm.getResourcePool().getOwner());
			System.out.println("Resource pool Parent: "
					+ vm.getResourcePool().getParent());
			// System.out.println("Resource pool resource pools: "
			// +vm.getResourcePool().getResourcePools().toString());
			System.out.println("Resource pool Values: "
					+ vm.getResourcePool().getValues());
			System.out.println("Resource pool VM: "
					+ vm.getResourcePool().getVMs().toString());
			// System.out.println("Resource pool Summary: "
			// +vm.getResourcePool().getSummary().toString());
			System.out.println("");
			// Added code till here*/

			System.out.println("Hello " + vm.getName());
			// /* Added code here
			System.out.println("VM Datastores: "
					+ vm.getDatastores().toString());
			System.out.println("VM Config: " + vm.getConfig().toString());
			System.out.println("VM Guest: " + vm.getGuest().getIpAddress());
			System.out.println("VM Parent: " + vm.getParent());
			System.out.println("VM resouce pool: " + vm.getResourcePool());
			System.out.println("VM Runtime: " + vm.getRuntime().toString());
			System.out.println("VM Storage: " + vm.getStorage().toString());
			System.out.println("VM Values: " + vm.getValues());
			// Added code till here*/

			System.out.println("GuestOS: " + vminfo.getGuestFullName());
			// /* Added code here
			System.out.println("GuestID: " + vminfo.getGuestId());
			System.out.println("GuestName: " + vminfo.getName());
			System.out.println("GuestDataStore URL: "
					+ vminfo.getDatastoreUrl());
			System.out.println("");
			// Added code till here*/

			System.out.println("Multiple snapshot supported: "
					+ vmc.isMultipleSnapshotsSupported());

			//si.getServerConnection().logout();
		}
		}catch(Exception e){e.printStackTrace();}
	}
}

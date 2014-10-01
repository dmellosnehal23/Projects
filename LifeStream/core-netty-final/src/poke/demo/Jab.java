/*
 * copyright 2012, gash
 * 
 * Gash licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package poke.demo;

import poke.client.ClientConnection;
import java.util.Scanner;

public class Jab {
	private String tag;
	private int count;
	//SNE : Added port
    private int port; 
    String emailId, name, pathname;

	public Jab(String tag, int port) { //SNE :  added parameter port
		this.tag = tag;
        this.port = port;
	}
	
	public void input()
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter EmailId : ");
		emailId = in.nextLine();
		
		System.out.print("Please enter Image Name (Optional) : ");
		name = in.nextLine();
		
		System.out.print("Please enter file pathname : ");
		pathname = in.nextLine();	
	}

	public void run() {
		
		ClientConnection cc = ClientConnection
				.initConnection("localhost", this.port); 
		
		//this.input();
        emailId = "user@sjsu.edu";
        name="img1";
        pathname="dummy";
		cc.uplodImage(emailId, name, 1, 2, pathname);
	}

	public static void main(String[] args) {
		try {
			//SNE : Check input args
            if(args.length != 2)
            {
                System.err.println("Usage: java Jab.class tag port");
                System.exit(1);
            }

			Jab jab = new Jab(args[0], Integer.parseInt(args[1]));//SNEHAL - removed the existing parameter "jab" and added two new paameters.
			jab.run();

			Thread.sleep(5000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

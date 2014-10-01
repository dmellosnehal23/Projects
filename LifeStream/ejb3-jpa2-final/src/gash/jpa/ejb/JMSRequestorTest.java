package gash.jpa.ejb;

import org.junit.Test;

public class JMSRequestorTest {
		
	@Test
	public void test() {
		byte [] data = "Hello World".getBytes();
		String uid = "test@sjsu.edu";
		
		JMSRequestor iClient = new JMSRequestor();
		
		iClient.uploadImage(uid, 1, 2, data);
	}

}

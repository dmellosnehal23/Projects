/**
 * 
 */
package gash.jpa.ejb;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import java.util.Properties;

public class JMSRequestor {
		
	private Context remoteContext;
	private QueueSession session;
	private QueueSender sender;
	private String host = "localhost";
	private MessageProducer producer = null;

	public void setHost(String host) {
		this.host = host;
	}

	public boolean uploadImage(String uid, float latitude, float longitude, byte [] data) {
		try {
			if(! this.startSession())
				throw new RuntimeException("Unable to conntect to service");

			MapMessage m = session.createMapMessage();
			m.setString("uid", uid);
			m.setFloat("latitude", latitude);
			m.setFloat("longitude", longitude);
			m.setBytes("data", data);
			
			producer.send(m);
			session.commit();
			
			this.endSession();
			
			return true;
		} catch (Exception e) {
			System.out.println("Failed to upload image: "
					+ e.getClass().getName() + " - ");
			e.printStackTrace();
		}

		// should never get to this point
		return false;
	}

	@SuppressWarnings("unchecked")
	private boolean startSession() throws Exception {

		Connection connection;
        try {
        	final Properties jndiProperties = new Properties();
    		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,
    				"org.jboss.naming.remote.client.InitialContextFactory");
    		jndiProperties.put(InitialContext.PROVIDER_URL,
    				"remote://localhost:4447");
    		jndiProperties.put("jboss.naming.client.ejb.context", true);
    		jndiProperties.put(Context.URL_PKG_PREFIXES,
    				"org.jboss.ejb.client.naming");

    		/**
    		 * YOU MUST: add a user for remote access. Use			//application user. add to guest role 
    		 * $JBOSS_HOME/bin/add-user.sh
    		 */
    		jndiProperties.put(Context.SECURITY_PRINCIPAL, "test3");	//cmpe275
    		jndiProperties.put(Context.SECURITY_CREDENTIALS, "pass3");		//cmpe275user
    		
            remoteContext = new InitialContext(jndiProperties);
 
            ConnectionFactory factory =
                (ConnectionFactory)remoteContext.
                        lookup("jms/RemoteConnectionFactory");
            Queue queue = (Queue) remoteContext.
                    lookup("jms/queue/requestqueue");
            connection = factory.createConnection("test3", "pass3");
            session = (QueueSession)connection.createSession(false, QueueSession.AUTO_ACKNOWLEDGE);
 
            
            producer = session.createProducer(queue);
 
            return true;
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

		return false;
	}
	
	private void endSession() {
		try {
			session.close();
		} catch (JMSException e) {
            e.printStackTrace();
        }
	}
 
}

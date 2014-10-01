package gash.jpa.ejb;


import gash.jpa.entities.ImageData;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/requestqueue"),
		@ActivationConfigProperty(propertyName = "maxSession", propertyValue = "10") })
public class JMSReplier implements MessageListener {
	@Resource
	private MessageDrivenContext mctx;

	@PersistenceContext(unitName = "ImageUtility")
	private EntityManager em;

	/**
	 * messages forwarded to this method are assumed to be validated
	 * 
	 * @param msg
	 */
	public void onMessage(Message msg) {
		try {
			javax.jms.MapMessage om = (MapMessage) msg;

			ImageData image = new ImageData();
			image.setUid(om.getString("uid"));
			image.setData(om.getBytes("data"));
			float lat = om.getFloat("latitude");
			float lon = om.getFloat("longitude");
			
			System.out.println("Adding image for uid " + image.getUid() + " at location(" + lat + ", " + lon + ")");

			em.persist(image);
	
			// iff we want to see a delay in adding and viewing
			// Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
package jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.google.gson.JsonObject;

import entities.Product;

@MessageDriven(mappedName = "jms/dat250/Topic", activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class DweetListener implements MessageListener{
	@Override
	public void onMessage(Message message) {
		try {
			Product product = message.getBody(Product.class);
			JsonObject json = new JsonObject();
			json.addProperty("productId", product.getId());
			json.addProperty("productName", product.getProductName());
			json.addProperty("bidderId", product.getBid().getAccount().getId());
			json.addProperty("bidderName", product.getBid().getAccount().getName());
			json.addProperty("bidAmount", product.getBid().getBidAmount());
			DweetConnection dc = new DweetConnection();
			dc.publish(json);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
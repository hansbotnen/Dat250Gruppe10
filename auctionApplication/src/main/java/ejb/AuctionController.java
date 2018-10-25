package ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Product;
import entities.Products;

@Named(value = "auctionController")
public class AuctionController {
	
	@PersistenceContext(unitName="auctionApplication")
    private EntityManager em;
	@EJB
	AuctionDao auctionDao;
	@Resource
	TimerService timerService;
	
	ArrayList<Integer> addedProductIds = new ArrayList<>();
	
	@Schedule(hour="*",minute="*",second="*/20")
	public void addTimers() {
		Products products = auctionDao.getAllProducts();
		for(Product p:products) {
			if(p.isPublished() && !addedProductIds.contains(p.getId())) {
				addedProductIds.add(p.getId());
				timerService.createTimer(p.getAuctionTime()*1000, p);
			}
		}
	}
	
	private Date createEndTime(int seconds) {
		Date endTime = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(endTime);
		cal.add(Calendar.SECOND, seconds);
		endTime = cal.getTime();
		return endTime;
	}
	
	@Timeout
	public void timeout(Timer timer) throws NamingException, JMSException {
		Product product = (Product) timer.getInfo();
		addedProductIds.remove(product.getId());
		if(product.getBid()!=null)
			auctionDao.testDweet(product);
	}
	
}

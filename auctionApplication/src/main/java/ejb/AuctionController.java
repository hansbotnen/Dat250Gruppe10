package ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Account;
import entities.Bid;
import entities.Product;
import entities.Products;

@Named(value = "auctionController")
@Singleton
@Startup
@DependsOn("LoadData")
public class AuctionController {
	
	@PersistenceContext(unitName="auctionApplication")
    private EntityManager em;
	@EJB
	AuctionDao auctionDao;
	@Resource
	TimerService timerService;
	
	ArrayList<Integer> addedProductIds = new ArrayList<>();
	
	@PostConstruct
	@Schedule(hour="*",minute="*",second="*")
	public void addTimers() {
		Products products = auctionDao.getAllProducts();
		for(Product p:products) {
			if(p.isPublished() && !addedProductIds.contains(p.getProductId())) {
				addedProductIds.add(p.getProductId());
				timerService.createTimer(p.getAuctionTime()*1000, p.getProductId());
			}
		}
	}
	
	@Timeout
	public void timeout(Timer timer) throws NamingException, JMSException {
		int productId = (int) timer.getInfo();
		Product product = auctionDao.getProduct(""+productId);
		product.setCompleted(true);
		product.setPublished(false);		
		
		if(product.getBid()!=null)
			auctionDao.testDweet(product);
	}
	
}

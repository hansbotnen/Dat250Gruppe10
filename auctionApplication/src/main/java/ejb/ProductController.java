package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.naming.NamingException;

import entities.Accounts;
import entities.Product;
import entities.Products;
import entities.Bid;

@Named(value = "productController")
@RequestScoped
public class ProductController {

	@EJB
	private AuctionDao auctionDao;
	
	private Product product; 
	
	private Bid bid;
	
	public Products getProducts() {	
		return auctionDao.getAllProducts();
	}
	
	public void createProduct() throws NamingException, JMSException {
		this.auctionDao.createBidonProduct(bid, product);
	}

	public Product getProduct() {
		if (this.product == null) {
			product = new Product();
		}
		return product;
	}
	
	public Bid getBid() {
		if (this.bid == null) {
			bid = new Bid();
		}
		return bid;
	}
}

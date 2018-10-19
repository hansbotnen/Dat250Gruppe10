package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import entities.Product;
import entities.Products;

@ManagedBean(value = "auctionController")
@SessionScoped
public class AuctionController {

	@EJB
	private AuctionDao auctionDao;
	
	private Product product; 
	
	public Products getProducts() {
		/*
		Products p = auctionDao.getAllProducts();
		System.out.println(p.size());
		*/
		
		Product p = new Product();
		p.setProductName("Test");
		Products ps = new Products();
		ps.add(p);
		return ps;
	}
	
	public Product getProduct() {
		if (this.product == null) {
			product = new Product();
		}
		return product;
	}
}

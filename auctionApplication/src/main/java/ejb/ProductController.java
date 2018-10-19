package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import entities.Accounts;
import entities.Product;
import entities.Products;

@Named(value = "productController")
@RequestScoped
public class ProductController {

	@EJB
	private AuctionDao auctionDao;
	
	private Product product; 
	
	public Products getProducts() {	
		return auctionDao.getAllProducts();
	}

	public Product getProduct() {
		if (this.product == null) {
			product = new Product();
		}
		return product;
	}
}

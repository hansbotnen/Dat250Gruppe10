package soap;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ejb.AuctionDao;
import entities.Account;
import entities.Accounts;
import entities.Bid;
import entities.Bids;
import entities.Product;
import entities.Products;

@WebService
public class SoapService {
	
	@PersistenceContext(unitName="auctionApplication")
    private EntityManager em; 

	@EJB
	AuctionDao dao = new AuctionDao();
	
	/********************************************************
	 * 														*
	 *				SOAPservices for Account				*
	 *														*
	 ********************************************************/
	
	public void createAccount(Account account) {
		if (account == null)
			throw new BadRequestException();
		dao.createAccount(account);
	}
	
	public Accounts getAccounts() {
		return dao.getAllAccounts();
	}
	
	public Account getAccountById(@PathParam("id") String id) {
		Account account = dao.getAccount(id);
		if (account == null)
			throw new NotFoundException();
		return account;
	}
	
	/********************************************************
	 * 														*
	 *				SOAPservices for Product 				*
	 *														*
	 ********************************************************/
	
	public void createProduct(Product product) {
		if (product == null)
			throw new BadRequestException();
		dao.createProduct(product);
	}

	public Products getProducts() {
		return dao.getAllProducts();
	}
	
	public Product getProduct(@PathParam("id") String id) {
		Product product = dao.getProduct(id);
		if (product == null)
			throw new NotFoundException();
		return product;
	}
	

	/********************************************************
	 * 														*
	 *				SOAPservices for Bids	 				*
	 *														*
	 ********************************************************/

	public void createBid(Bid bid) {
		if (bid == null)
			throw new BadRequestException();
		dao.createBid(bid);
	}
	
	public Bid getBid(@PathParam("id") String id) {
		Bid bid = dao.getBid(id);
		if (bid == null)
			throw new NotFoundException();
		return bid;
	}
	
	public Bids getBids() {
		return dao.getBids();
	} 
}
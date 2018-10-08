package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSSessionMode;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Account;
import entities.Bid;
import entities.Product;
import entities.ProductCatalog;

/**
 * 
 * @author Philip
 * 
 *         Data Acces Object connecting the Database with the business logic
 */
@Stateless
public class AuctionDao {
	@PersistenceContext(unitName = "auctionApplication")
	private EntityManager em;

	@Inject
	@JMSConnectionFactory("jms/auctionApplication/ConnectionFactory")
	@JMSSessionMode(JMSContext.AUTO_ACKNOWLEDGE)
	private JMSContext context;

	public String getHelloWorld() {
		return "Hello World!";
	}
	
	/********************************************************
	 * 														*
	 *					EJB for Account						*
	 *														*
	 ********************************************************/

	public Account createAccount(Account account) {
		em.persist(account);
		return account;
	}

	public List<Account> getAllAccounts() {
		Query query = em.createQuery("SELECT a FROM Acccount a");
		List<Account> accounts = new ArrayList<Account>();
		accounts = query.getResultList();
		return accounts;
	}

	public Account getAccountById(int id) {
		return em.find(Account.class, id);
	}
	
	/********************************************************
	 * 														*
	 *					EJB for Product						*
	 *														*
	 ********************************************************/
	
	public Product createProduct(Product product) {
		em.persist(product);
		return product;
	}

	public List<Product> getAllProduct() {
		Query query = em.createQuery("SELECT p FROM Product p");
		List<Product> products = new ArrayList<Product>();
		products = query.getResultList();
		return products;
	}

	public Product getProductById(int id) {
		return em.find(Product.class, id);
	}
	
	/********************************************************
	 * 														*
	 *					EJB for Bid							*
	 *														*
	 ********************************************************/
	
	public Bid createBid(Bid bid) {
		em.persist(bid);
		return bid;
	}

	public List<Bid> getAllBids() {
		Query query = em.createQuery("SELECT b FROM Bid b");
		List<Bid> bids = new ArrayList<Bid>();
		bids = query.getResultList();
		return bids;
	}

	public Bid getBidById(int id) {
		return em.find(Bid.class, id);
	}
	
	/********************************************************
	 * 														*
	 *				EJB for ProductCatalog					*
	 *														*
	 ********************************************************/
	
	public ProductCatalog createProductCatalog(ProductCatalog productCatalog) {
		em.persist(productCatalog);
		return productCatalog;
	}

	public List<ProductCatalog> getAllProductCatalog() {
		Query query = em.createQuery("SELECT pc FROM ProductCatalog pc");
		List<ProductCatalog> productCatalogs = new ArrayList<ProductCatalog>();
		productCatalogs = query.getResultList();
		return productCatalogs;
	}

	public ProductCatalog getProductCatalogById(int id) {
		return em.find(ProductCatalog.class, id);
	}

}

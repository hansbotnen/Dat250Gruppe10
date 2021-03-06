package ejb;


import java.net.URI;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSSessionMode;
import javax.jms.Topic;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import entities.Account;
import entities.Accounts;
import entities.Bid;
import entities.Bids;
import entities.Product;
import entities.ProductCatalog;
import entities.ProductCatalogs;
import entities.Products;

@Stateless
public class AuctionDao {
	@Context
	private UriInfo uriInfo;
	@PersistenceContext(unitName="auctionApplication")
    private EntityManager em;
	
	@Inject
	@JMSConnectionFactory("jms/dat250/ConnectionFactory")
	@JMSSessionMode(JMSContext.AUTO_ACKNOWLEDGE)
	private JMSContext context;
	
	@Resource(lookup = "jms/dat250/Topic")
	private Topic topic;
	
	public void testDweet(Product product) throws NamingException, JMSException {
		context.createProducer().send(topic,product);
	}
	
	public URI createAccountRest(Account account) {
		account.getProductCatalog().setCatalogName(account.getName()+"'s Catalog");
		em.persist(account);		
		URI accountUri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(account.getAccountId())).build();
		return accountUri;
	}
	
	public void createAccountSoap(Account account) {
		em.persist(account);
	}
	
	public URI createProductCatalog(ProductCatalog catalog) {
		em.persist(catalog);
		URI pcURI = uriInfo.getAbsolutePathBuilder().path(Integer.toString(catalog.getId())).build();
		return pcURI;
	}
	
	public ProductCatalogs getAllProductCatalogs() {
		TypedQuery<ProductCatalog> query = em.createNamedQuery(ProductCatalog.FIND_ALL, ProductCatalog.class);
		ProductCatalogs pc = new ProductCatalogs(query.getResultList());
		return pc;
	}
	
	public Accounts getAllAccounts() {
		TypedQuery<Account> query = em.createNamedQuery(Account.FIND_ALL, Account.class);
		Accounts accounts = new Accounts(query.getResultList());
		return accounts;
	}
	
	public Account getAccount (String id) {
		int idInt = Integer.parseInt(id);
		Account account = em.find(Account.class, idInt);
		return account;
	}
	
	public URI createProduct(String id, Product product) {
		//Add product straight into an accounts product catalog
		Account account = getAccount(id);
		ProductCatalog catalog = account.getProductCatalog();
		catalog.addProduct(product);
//		product.setProductCatalog(catalog);
		
		em.persist(catalog);
		em.persist(product);
		URI productUri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(product.getProductId())).build();
		return productUri;
	}
	
	public void createProductSoap(Product product) {
		em.persist(product);
	}
	
	public Products getAllProducts() {
		TypedQuery<Product> query = em.createNamedQuery(Product.FIND_ALL, Product.class);
		Products products = new Products(query.getResultList());
        return products;
    }
	
	public Product getProduct(String id) {
		int idInt = Integer.parseInt(id);
		Product product = em.find(Product.class, idInt);
		return product;
	}
	
	public URI createBidRest(Bid bid) {
		em.persist(bid);
		URI bidUri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(bid.getId())).build();
		return bidUri;
	}
	
	public void createBidSoap(Bid bid) {
		bid.getProduct().setBid(bid);
		em.persist(bid);
	}
	
	public void updateBid(Bid bid, int bidAmount) {
		bid.setBidAmount(bidAmount);
        em.flush();
	}
	
	public Bid getBid(String id) {
		int idInt = Integer.parseInt(id);
		Bid bid = em.find(Bid.class, idInt);
		return bid;
	}
	
	public Bids getBids() {
		TypedQuery<Bid> query = em.createNamedQuery(Bid.FIND_ALL, Bid.class);
		Bids bids = new Bids(query.getResultList());
		return bids;
	}
	
	public void bidOnProduct(int bidAmount, Product product) {
        if (product.getBid() == null) {
            Bid bid = new Bid();
            product.setBid(bid);
            bid.setProduct(product);
        }
        product.getBid().setBidAmount(bidAmount);
    }
	
	public void createBidonProduct(Bid bid, Product product) {
        bid.setProduct(product);
        product.setBid(bid);
        em.persist(bid);
        em.persist(product);
    }
	
}
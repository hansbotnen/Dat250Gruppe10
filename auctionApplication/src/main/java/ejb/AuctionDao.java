package ejb;


import java.net.URI;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSSessionMode;
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
	
	public URI createAccountRest(Account account) {
		account.getProductCatalog().setCatalogName(account.getName()+"'s Catalog");
		em.persist(account);		
		URI accountUri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(account.getId())).build();
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
		URI productUri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(product.getId())).build();
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
		em.persist(bid);
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
	
	
}
package ejb;


import java.net.URI;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.eclipse.persistence.internal.oxm.schema.model.List;

import entities.Account;
import entities.Accounts;
import entities.Bid;
import entities.Product;
import entities.Products;

@Stateless
public class AuctionDao {
	@Context
	private UriInfo uriInfo;
	@PersistenceContext(unitName="auctionApplication")
    private EntityManager em;
	
	public URI createAccount(Account account) {
		em.persist(account);
		URI accountUri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(account.getId())).build();
		return accountUri;
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
	
	public URI createProduct(Product product) {
		em.persist(product);
		URI productUri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(product.getId())).build();
		return productUri;
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
	
	public URI createBid(Bid bid) {
		em.persist(bid);
		URI bidUri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(bid.getId())).build();
		return bidUri;
	}
	
	public Bid getBid(String id) {
		int idInt = Integer.parseInt(id);
		Bid bid = em.find(Bid.class, idInt);
		return bid;
	}

}

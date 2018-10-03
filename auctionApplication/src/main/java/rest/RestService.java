package rest;

import java.net.URI;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import entities.Account;
import entities.Accounts;
import entities.Bid;
import entities.Product;
import entities.Products;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/auction")
@Stateless
public class RestService {
	
	@Context
	private UriInfo uriInfo;
	@PersistenceContext(unitName = "auctionApplication")
	EntityManager em;
	//EntityManagerFactory emf = Persistence.createEntityManagerFactory("auctionApplication");
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("/ok")
	public Response getOk() {
		return Response.ok("getOk()").build();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("/hello")
	public Response getWorld() {
		return Response.ok("Hello World!").build();
	}
	
	/********************************************************
	 * 														*
	 *				REST services for Account				*
	 *														*
	 ********************************************************/
	
	@GET
	@Path("/accounts")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Accounts getAccounts() {
		TypedQuery<Account> query = em.createNamedQuery(Account.FIND_ALL, Account.class);
		Accounts accounts = new Accounts(query.getResultList());
		return accounts;
	}
	
	@POST
	@Path("/accounts")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response createAccount(Account account) {
		if (account == null)
			throw new BadRequestException();
		em.persist(account);
		URI accountUri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(account.getId())).build();
		return Response.created(accountUri).build();
	}
	
	@GET
	@Path("/accounts/{id}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getAccoundId(@PathParam("id") String id) {
		int idInt = Integer.parseInt(id);
		Account account = em.find(Account.class, idInt);
		if (account == null)
			throw new NotFoundException();
		return Response.ok(account).build();
	}
	
	/********************************************************
	 * 														*
	 *				REST services for Product 				*
	 *														*
	 ********************************************************/
	

	@GET
	@Path("/products")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Products getProducts() {
		TypedQuery<Product> query = em.createNamedQuery(Product.FIND_ALL, Product.class);
		Products products = new Products(query.getResultList());
		return products;
	}
	
	@POST
	@Path("/products")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response createProduct(Product product) {
		if (product == null)
			throw new BadRequestException();
		em.persist(product);
		URI productUri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(product.getId())).build();
		return Response.created(productUri).build();
	}
	
	@GET
	@Path("/products/{id}")
	public Response getProduct(@PathParam("id") String id) {
		int idInt = Integer.parseInt(id);
		Product product = em.find(Product.class, idInt);
		if (product == null)
			throw new NotFoundException();
		return Response.ok(product).build();
	}
	
	/********************************************************
	 * 														*
	 *				REST services for Bids	 				*
	 *														*
	 ********************************************************/

	@POST
	@Path("/bids")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response createBid(Bid bid) {
		if (bid == null)
			throw new BadRequestException();
		em.persist(bid);
		URI bidUri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(bid.getId())).build();
		return Response.created(bidUri).build();
	}
	
	@GET
	@Path("/bids/{id}")
	public Response getBid(@PathParam("id") String id) {
		int idInt = Integer.parseInt(id);
		Bid bid = em.find(Bid.class, idInt);
		if (bid == null)
			throw new NotFoundException();
		return Response.ok(bid).build();
	} 
	
//	/*TODO
//	@POST
//	@Consumes(MediaType.APPLICATION_XML)
//	@Path("{id}/bids")
//	public Response createBid(Bid bid) {
//		if (bid == null)
//			throw new BadRequestException();
//		em.persist(bid);
//		URI bidUri = uriInfo.getAbsolutePathBuilder().path(bid.getId).build();
//		return Response.created(bidUri).build();
//	}*/
	

}

package rest;

import java.net.URI;
import java.util.List;

import javax.ejb.EJB;
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

import ejb.AuctionDao;
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
	@EJB
	AuctionDao dao = new AuctionDao();
	
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
		
	@POST
	@Path("/accounts")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response createAccount(Account account) {
		if (account == null)
			throw new BadRequestException();
		return Response.created(dao.createAccount(account)).build();
	}
	
	@GET
	@Path("/accounts")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getAccounts() {
		return Response.ok(dao.getAllAccounts()).build();
	}
	
	@GET
	@Path("/accounts/{id}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getAccoundId(@PathParam("id") String id) {
		Account account = dao.getAccount(id);
		if (account == null)
			throw new NotFoundException();
		return Response.ok(account).build();
	}
	
	/********************************************************
	 * 														*
	 *				REST services for Product 				*
	 *														*
	 ********************************************************/
	
	@POST
	@Path("/products")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response createProduct(Product product) {
		if (product == null)
			throw new BadRequestException();
		return Response.created(dao.createProduct(product)).build();
	}

	@GET
	@Path("/products")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Products getProducts() {
		return dao.getAllProducts();
	}
	
	@GET
	@Path("/products/{id}")
	public Response getProduct(@PathParam("id") String id) {
		Product product = dao.getProduct(id);
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
		return Response.created(dao.createBid(bid)).build();
	}
	
	@GET
	@Path("/bids/{id}")
	public Response getBid(@PathParam("id") String id) {
		Bid bid = dao.getBid(id);
		if (bid == null)
			throw new NotFoundException();
		return Response.ok(bid).build();
	} 
}

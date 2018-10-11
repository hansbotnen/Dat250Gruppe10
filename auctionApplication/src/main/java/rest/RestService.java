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
import entities.Bids;
import entities.Product;
import entities.ProductCatalog;
import entities.ProductCatalogs;
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
	@Produces(MediaType.APPLICATION_XML)
	@Path("/hello/json")
	public Response getWorldXML() {
		return Response.ok("Hello World!").build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/hello")
	public Response getWorldJson() {
		return Response.ok("Hello World!").build();
	}
	
	/********************************************************
	 * 														*
	 *				RESTservices for Account				*
	 *														*
	 ********************************************************/
	
	@POST
	@Path("/accounts")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response createAccount(Account account) {
		if (account == null)
			throw new BadRequestException();
		return Response.created(dao.createAccountRest(account)).build();
	}
	
	@GET
	@Path("/accounts")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getAccounts() {
		return Response.ok(dao.getAllAccounts()).build();
	}
	
	@GET
	@Path("/accounts/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getAccoundId(@PathParam("id") String id) {
		Account account = dao.getAccount(id);
		if (account == null)
			throw new NotFoundException();
		return Response.ok(account).build();
	}
	
	/********************************************************
	 * 														*
	 *				RESTservices for Product 				*
	 *														*
	 ********************************************************/
	
	@POST
//	@Path("/products") Before
	@Path("/accounts/{id}/productcatalog") //After
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response createProduct(@PathParam("id") String id, Product product) {
	
		if (product == null)
			throw new BadRequestException();

		return Response.created(dao.createProduct(id, product)).build();
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
	
	@GET
	@Path("/products/{pid}/bids/{bid}")
	public Response getBidFromProduct(@PathParam("pid") String pid, @PathParam("bid") String bid) {
		int pidInt = Integer.parseInt(pid);
		int bidInt = Integer.parseInt(bid);
		
		Product product = em.find(Product.class, pidInt);
		if (product == null)
			throw new NotFoundException();
		return null;
		
	}
	
	/********************************************************
	 * 														*
	 *				RESTservices for Bids	 				*
	 *														*
	 ********************************************************/

	@POST
	@Path("/bids")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response createBid(Bid bid) {
		if (bid == null)
			throw new BadRequestException();
		return Response.created(dao.createBidRest(bid)).build();
	}

	@GET
	@Path("/bids")
	public Bids getBids() {
		return dao.getBids();
	}
	
	@GET
	@Path("/bids/{id}")
	public Response getBid(@PathParam("id") String id) {
		Bid bid = dao.getBid(id);
		if (bid == null)
			throw new NotFoundException();
		return Response.ok(bid).build();
	} 
	
	/********************************************************
	 * 														*
	 *			REST services for ProductCatalog			*
	 *														*
	 ********************************************************/
	
	@GET
	@Path("/productcatalog")
	public ProductCatalogs getAllProductCatalog() {
		return dao.getAllProductCatalogs();
	} 
	
	@GET
	@Path("/accounts/{id}/productcatalog") //After
	public Response getProductCatalog(@PathParam("id") String id) {
		Account account = dao.getAccount(id);
		ProductCatalog pc = account.getProductCatalog();
		if (pc == null)
			throw new NotFoundException();
		return Response.ok(pc).build();
	}
	
	
//	@POST
//	@Path("/bids/{id}/{amount}")
//	public Response bidProduct

	
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
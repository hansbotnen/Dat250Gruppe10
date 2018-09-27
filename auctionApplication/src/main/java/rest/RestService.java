package rest;

import java.net.URI;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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

import entities.Bid;
import entities.Product;
import entities.User;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("products")
@Stateless
public class RestService {
	
	/*
	 * TODO: 
	 * 	GET: products/{id}/bids
	 * 	GET: products/{pid}
	 * 
	 * DOING:
	 * 	POST: product/{id}/bids
	 * 
	 * DONE:
	 * 	GET: products
	 * 	GET: products/{id}
	 * 
	 * 
	 * Must also support both XML and JSOM formats as representation
	 */
	
	@Context
	private UriInfo uriInfo;
	@PersistenceContext(unitName = "auctionApplication")
	private EntityManager em;
	
	//<app-path>/rest/products
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getProducts() {
		TypedQuery<Product> query = em.createNamedQuery(Product.FIND_ALL, Product.class);
		
		List<Product> products = query.getResultList();
		return Response.ok(products).build();
	}
	
	//<app-path>/res/products/{id}
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getProduct(@PathParam("id") String id) {
		int idInt = Integer.parseInt(id);
		Product product = em.find(Product.class, idInt);
		if (product == null)
			throw new NotFoundException();
		return Response.ok(product).build();
	}
	
	//<app-path>/res/products/{id}/bids
	//Denne henter kun en ting i bid atm
	@GET
	@Path("{id}/bids")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getBid(@PathParam("id") String id) {
		int idInt = Integer.parseInt(id);
		Bid bid = em.find(Bid.class, idInt);
		if (bid == null)
			throw new NotFoundException();
		return Response.ok(bid).build();
	} 
	
	/*TODO
	//<app-path>/res/products/{aid}/bids/{bid}
	pid = product id 
	@GET
	@PATH("product/{pid}/bids{bid})
	public Response getBidFromProduct() {
	
	}
	 */
	
	//<app-path>/res/products/{id}/bids
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{id}/bids")
	public Response createBid(Bid bid) {
		if (bid == null)
			throw new BadRequestException();
		em.persist(bid);
		URI bidUri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(bid.getId())).build();
		return Response.created(bidUri).build();
	}
	
}

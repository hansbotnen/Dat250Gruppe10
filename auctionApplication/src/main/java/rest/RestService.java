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

@Path("rest/products")
@Stateless
public class RestService {
	
	@Context
	private UriInfo uriInfo;
	@PersistenceContext(unitName = "auctionApplication")
	private EntityManager em;
	/*TODO
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getProduct() {
		TypedQuery<Product> query = em.createNamedQuery(Product.FIND_ALL, Product.class);
		List<Product> product = query.getResultList();
		return Response.ok(product).build();
	}*/
	
	@GET
	@Path("{id}")
	public Response getProduct(@PathParam("id") String id) {
		int idInt = Integer.parseInt(id);
		Product product = em.find(Product.class, idInt);
		if (product == null)
			throw new NotFoundException();
		return Response.ok(product).build();
	}
	
	@GET
	@Path("{id}/bids")
	public Response getBid(@PathParam("id") String id) {
		int idInt = Integer.parseInt(id);
		Bid bid = em.find(Bid.class, idInt);
		if (bid == null)
			throw new NotFoundException();
		return Response.ok(bid).build();
	} 
	/*TODO
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Path("{id}/bids")
	public Response createBid(Bid bid) {
		if (bid == null)
			throw new BadRequestException();
		em.persist(bid);
		URI bidUri = uriInfo.getAbsolutePathBuilder().path(bid.getId).build();
		return Response.created(bidUri).build();
	}*/
	
}

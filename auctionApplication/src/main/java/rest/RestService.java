/*
import java.net.URI;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import entities.Bid;
import entities.User;

import javax.ws.rs.Path;

@Path("product")
@Stateless
public class RestService {
	
	@Context
	private UriInfo uriInfo;
	@PersistenceContext(unitName = "auction")
	private EntityManager em;
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public User getUser() {
		TypedQuery<User> query = em.createNamedQuery(User.FIND_ALL, User.class);
		User user = new User(query.getResultList());
		return user;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response createBid(Bid bid) {
		em.persist(bid);
		URI bidUri = uriInfo.getAbsolutePathBuilder().path(bid.getId().toString()).build();
		return Response.created(bidUri).build();
	}
	
}
*/
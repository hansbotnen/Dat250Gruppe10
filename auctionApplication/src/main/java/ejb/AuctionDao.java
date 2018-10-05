package ejb;


import java.net.URI;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.UriInfo;

import org.eclipse.persistence.internal.oxm.schema.model.List;

import entities.Product;

@Stateless
public class AuctionDao {
	@PersistenceContext(unitName="AuctionApplication")
    private static EntityManager em;
	private static UriInfo uriInfo;
	
	public static URI getAllProducts(Product product) {
		if (product == null)
			throw new BadRequestException();
		em.persist(product);
		URI productUri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(product.getId())).build();
        return productUri;
    }

}

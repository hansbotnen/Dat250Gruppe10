package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.User;

public class AuctionDAO {

	public static void main(String[] args) {
		User user1 = new User("Philip", "12345678", 5, "philhvl.no");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AuctionPU");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();

		tx.begin();
		em.persist(user1);
		tx.commit();
		
		Query query = em.createQuery("SELECT t from User t");
		int i = query.getFirstResult();
		System.out.println(i);
		
		em.close();
		emf.close();
	}

}

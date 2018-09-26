package ejb;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Account;

public class AuctionDAO {

	public static void main(String[] args) {
		Account user1 = new Account("Philip", "12345678", 5, "philhvl.no");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("auctionApplication");
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

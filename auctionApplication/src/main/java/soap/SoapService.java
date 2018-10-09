package soap;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ejb.AuctionDao;
import entities.Accounts;

@WebService
public class SoapService {
	
	@PersistenceContext(unitName="auctionApplication")
    private EntityManager entityManager; 

	@EJB
	AuctionDao dao = new AuctionDao();
	
	public Accounts getAccounts() {
		return dao.getAllAccounts();
	}
}

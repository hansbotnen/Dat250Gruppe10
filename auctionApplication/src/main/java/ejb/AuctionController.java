package ejb;

import javax.inject.Named;

import entities.Account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

@Named(value = "auctionController")
@RequestScoped
public class AuctionController implements Serializable {
	private static final long serialVersionUID = 1L;

	//Injected DAO EJB
	@EJB
	private AuctionDao auctionDao;
	
	private Account account;
	
	public List<Account> getAccounts() {
		List<Account> accountList = new ArrayList<Account>();
		accountList.addAll(this.auctionDao.getAllAccounts());
		return accountList;
	}
	
}

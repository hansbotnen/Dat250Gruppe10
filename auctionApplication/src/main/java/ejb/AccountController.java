package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.naming.NamingException;

import entities.Account;
import entities.Accounts;

@Named(value = "accountController")
@RequestScoped
public class AccountController {

	@EJB
	private AuctionDao auctionDao;
	
	private Account account; 
	
	public Accounts getAccounts() {	
		return auctionDao.getAllAccounts();
	}
	
	public void createAccount() throws NamingException, JMSException {
		this.auctionDao.createAccountSoap(account);
	}
	

	public Account getAccount() {
		if (this.account== null) {
			account = new Account();
		}
		return account;
	}
}

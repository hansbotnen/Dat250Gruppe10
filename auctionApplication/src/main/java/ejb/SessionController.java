package ejb;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import entities.Account;
import entities.Bid;

/**
 * 
 * @author Alejandro Rodriguez
 * Dat250 course
 *
 *Session Controller for validate an user 
 */

@Named(value = "sessionController")
@SessionScoped
public class SessionController implements Serializable {
	
	@PersistenceContext(unitName = "auctionApplication")
	EntityManager em;

	@EJB
	AuctionDao dao = new AuctionDao();
	
	private static final long serialVersionUID = 1L;

	private String password;

	private String username;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

	public String validateUsernamePassword() {
		HttpSession session = SessionUtils.getSession();
		Query query = em.createQuery("SELECT a FROM Account a WHERE a.email = '" + this.username + "'");
		Account account = (Account) query.getSingleResult();
		if(this.password.equals(account.getPassword())) {
			session.setAttribute(Constants.USERNAME, this.username);
			return Constants.INDEX;
		}
		return account.getPhone();
	}

	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return Constants.LOGIN;
	}
	
	public String redirect() throws IOException {
		HttpSession session = SessionUtils.getSession();
		if (session.getAttribute(Constants.USERNAME)==null) {
			SessionUtils.getResponse().sendRedirect(Constants.LOGIN + ".xhtml");
		}
		return Constants.INDEX;
	}

}

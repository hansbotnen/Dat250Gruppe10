package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(Account.class)
public class Accounts extends ArrayList<Account> {

	private static final long serialVersionUID = 1L;

	public Accounts() {
		super();
	}

	public Accounts(Collection<? extends Account> c) {
		super(c);
	}

	@XmlElement(name = "account")
	public List<Account> getAccounts() {
		return this;
	}

	public void setAccounts(List<Account> Account) {
		this.addAll(Account);
	}

}

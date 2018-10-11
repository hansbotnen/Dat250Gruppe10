package entities;
import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Bid.findAll", query = "SELECT b From Bid b")
})
@Table(name = "bid")
public class Bid implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bidId; 
	
	private int bidAmount = 0; 
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@XmlTransient
	private Product product;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@XmlTransient
	private Account account;
	
	public static final String FIND_ALL = "Bid.findAll";
	
	public Bid() {}
	
	public Bid(int bidAmount, Product product, Account account) {
		this.bidAmount = bidAmount;
		this.product = product;
		this.account = account;
//		product.setBid(this);
	}
	
	public int getId() {
		return bidId;
	}
	
	public int getBidAmount() {
		return bidAmount;
	}
	
	public void setBidAmount(int bidAmount) {
		if (this.bidAmount < bidAmount)
			this.bidAmount = bidAmount;
		else 
			throw new IllegalArgumentException("Cannot bid lower");
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public Account getAccount() {
		return account;
	}

//	public int getAccountId() {
//		return account.getId();
//	}
	
}

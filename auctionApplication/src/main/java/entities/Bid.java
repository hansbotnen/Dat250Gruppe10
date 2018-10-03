package entities;
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
public class Bid {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; 
	
	private int bidAmount; 
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@XmlTransient
	private Product product;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private Account account;
	

//	@OneToOne
//	@XmlTransient 
//	private Account account; 
	
	public Bid() {}
	
	public int getId() {
		return id;
	}
	
	public int getBidAmount() {
		return bidAmount;
	}
	
	public void setBidAmount(int bidAmount) {
		this.bidAmount = bidAmount;
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

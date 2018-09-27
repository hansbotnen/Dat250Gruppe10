package entities;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@NamedQueries({
	@NamedQuery(name = "Bid.findAll", query = "SELECT b From Bid b")
})
@Table(name = "bid")
public class Bid {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; 
	
	private int bidAmount; 
	
	@ManyToOne
	private @XmlTransient Product products;
	
	@OneToOne
	private @XmlTransient Account account; 
	
	public Bid() {}
	
	public int getId() {
		return id;
	}
	
	public int getBidAmount() {
		return bidAmount;
	}
	
	public void setBidAmount() {
		this.bidAmount = bidAmount;
	}

	public int getAccountId() {
		return account.getId();
	}
	
}

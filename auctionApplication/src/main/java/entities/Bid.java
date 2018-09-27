package entities;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@NamedQueries({
	@NamedQuery(name = "Bid.findAll", query = "SELECT b From Bid b")
})
@Table(name = "bid")
public class Bid {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; 
	
	public Bid() {}
	
	public int getId() {
		return id;
	}
	

}

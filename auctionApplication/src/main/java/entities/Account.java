package entities;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Account.findAll", query = "SELECT b From Account b") })
@Table(name = "account")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement(required = true)
	private int accountId;

	@NotNull
	@Column(name = "name")
	private String name;

	@Column(name = "phone")
	@Size(min = 8, max = 8)
	private String phone;

	@Column(name = "rating")
	private float rating;

	@Column(name = "email")
	private String email;

//	@JoinColumn(name = "feedback")
//	@OneToMany(mappedBy = "account")
//	private ArrayList<Feedback> feedbacks;
	
	@OneToOne(
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private ProductCatalog catalog;

	public static final String FIND_ALL = "Account.findAll";

	public Account(String name, String phone, float rating, String email) {
		this.name = name;
		this.phone = phone;
		this.rating = rating;
		this.email = email;
		catalog=new ProductCatalog(this);
	}

	public Account() {catalog=new ProductCatalog(this);
	}

	public int getId() {
		return accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setProductCatalog(ProductCatalog catalog) {
		this.catalog = catalog;
	}
	
	
	public ProductCatalog getProductCatalog() {
		return catalog;
	}

//	public void addFeedback(Feedback feedback) {
//		feedbacks.add(feedback);
//		if (!feedback.getAccount().equals(this))
//			feedback.setAccount(this);
//
//	}
//
//	public ArrayList<Feedback> getFeedbacks() {
//		return feedbacks;
//	}
//
//	public void setFeedbacks(ArrayList<Feedback> feedbacks) {
//		this.feedbacks = feedbacks;
//		feedbacks.forEach(f -> f.setAccount(this));
//	}

}

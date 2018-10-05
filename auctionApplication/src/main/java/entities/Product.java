package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Product.findAll", query = "SELECT b From Product b") })
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;

	private String productName;
	private String picture; // Changes to type Image
	private String features; // Description
	private Boolean published = false;

//	@OneToOne
//	@JoinColumn(name = "feedback_fk")
//	private Feedback feedback;

//	@Temporal(TemporalType.TIMESTAMP)
//	private Date endTime; // java.util.Date || java.util.Calendar

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "bid_fk")
	private Bid bid;

	public static final String FIND_ALL = "Product.findAll";

	public Product() {}
	
	public Product(String productName, String picture, String features) {
		this.productName = productName;
		this.picture = picture;
		this.features = features; 
	}
	
	public Product(String productName, String picture, String features, Bid bid) {
		this.productName = productName;
		this.picture = picture;
		this.features = features; 
		this.bid = bid;
	}
	
	public int getId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	// Must change to type Image
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public Boolean isPublished() {
		return published;
	}

	public void setPublish(Boolean publish) {
		this.published = publish;
	}
	
	public Bid getBid() {
		return bid;
	}
	
	public void setBid(Bid bid) { 
		this.bid = bid;
	}

//	public void setFeedback(Feedback feedback) {
//		this.feedback = feedback;
//	}
//
//	public Feedback getFeedback() {
//		return feedback;
//	}

}

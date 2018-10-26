package entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import ejb.AuctionController;
import ejb.AuctionDao;

@Entity
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Product.findAll", query = "SELECT b From Product b where b.published = true") })
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement(required = true)
	private int productId;

	private String productName;
	private String image; // Changes to type Image
	private String features; // Description
	private Boolean published = false;
	private boolean completed =  false;
 
	private int auctionTime=-1; // java.util.Date || java.util.Calendar
	
	
	
	@ManyToOne
	private ProductCatalog catalog;

	@OneToOne(cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Bid bid;

	public static final String FIND_ALL = "Product.findAll";

	public Product() {}
	
	public Product(String productName, String image, String features) {
		this.productName = productName;
		this.image = image;
		this.features = features; 
		//catalog.addProduct(this);
	}
	
	public Product(String productName, String image, String features, Bid bid, ProductCatalog catalog) {
		this.productName = productName;
		this.image = image;
		this.features = features; 
		this.bid = bid;
		this.catalog = catalog;
		bid.setProduct(this);
		catalog.addProduct(this);
	}
	
	@Timeout
	public void timeout() {
		completed = true;
		published = false;
	}
	
	public int getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	// Must change to type Image
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public void setPublished(Boolean publish) {
		this.published = publish;
	}
	
	public Bid getBid() {
		return bid;
	}
	
	public void setBid(Bid bid) { 
		this.bid = bid;
	}
	
	public void setProductCatalog(ProductCatalog catalog) {
		this.catalog = catalog;
	}
	
	public ProductCatalog getProductCatalog() {
		return catalog;
	}
	
	public void setAuctionTime(int endTime) {
		this.auctionTime=endTime;
		published = true; // published after adding auctionTime ok?
		completed = false;
	} 
	
	public int getAuctionTime() {
		return auctionTime;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public boolean getCompleted() {
		return completed;
	}
}

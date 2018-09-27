package entities;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "product")
@NamedQueries({
	@NamedQuery(name = "Product.findAll", query = "SELECT b From Product b")
})
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String productName;
  private String picture; //Changes to type Image
  private String features; //Description
  private Float productRating;
  
  @OneToOne
  @JoinColumn(name = "feedback_fk")
  private Feedback feedback;

  @Temporal(TemporalType.TIMESTAMP)
  private Date endTime; //java.util.Date || java.util.Calendar

  private Boolean published;
  
  public static final String FIND_ALL = "Product.findAll";

  public Product() {}

  public String getProductName() {
    return productName;
  }

  public void setProductName() {
    this.productName = productName;
  }

  //Must change to type Image
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

  private Float getProductRating() {
    return productRating;
  }

  public void setProductRating(Float productRating) {
    this.productRating = productRating;
  }
  
  public Boolean isPublished() {
	  return published;
  }
  
  public void setPublish(Boolean publish) {
	  this.published = publish; 
  }
  
  public void setFeedback(Feedback feedback) {
	  this.feedback = feedback;
  }
  
  public Feedback getFeedback() {
	  return feedback;
  }
  
}

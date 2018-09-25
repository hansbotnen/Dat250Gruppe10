package entities;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
	@NamedQuery(name = "findUser", query = "SELECT b From user b")
})
@Table(name="user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int userId;

  @NotNull
  private String name;
  @Size(min = 8, max = 8)
  private String phone;
  private float rating;
  private String email;
  
  @OneToMany(mappedBy = "user")
  private List<Feedback> feedbacks;

  //Assume that address is its own table
  //private Address address;
  //Product product //A list of product

  //Otherwise
  private String address;
  private String city;
  private String zipcode;

  public User(String name, String phone, float rating, String email) {
    this.name = name;
    this.phone = phone;
    this.rating = rating;
    this.email = email;
    feedbacks = new ArrayList<>();
    
    
    //this.address = address;
    //this.city = city;
    //this.zipcode = zipcode; 
  }

  public int getId() {
    return userId;
  }

  public void setId(int id) {
    this.userId = id;
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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCity() {
    return city;
  }
  
  public List<Feedback> getFeedbacks(){
	  return feedbacks;
  }
  
  public void setFeedbacks(List<Feedback> feedbacks) {
	  this.feedbacks=feedbacks;
  }
  
  public void addFeedback(Feedback feedback) {
	  this.feedbacks.add(feedback);
	  if(feedback.getUser() != this)
		  feedback.setUser(this);
  }

  //If table Address is not used
  // public void setCity(String city) {
  //   this.city = city;
  // }
  //
  // public String getZipcode() {
  //   return zipcode;
  // }
  //
  // public void setZipcode(String zipcode) {
  //   this.zipcode = zipcode;
  // }

}


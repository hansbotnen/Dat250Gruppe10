package entities;
import java.util.ArrayList;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "findUser", query = "SELECT b From User b")
})
@Table(name="account")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int userId;

  @NotNull
  @Column(name="name")
  private String name;
  
  @Column(name="phone")
  @Size(min = 8, max = 8)
  private String phone;

  @Column(name="rating")
  private float rating;
  
  @Column(name="email")
  private String email;
  
  /*@JoinColumn(name="feedback")
  @OneToMany(mappedBy = "user")
  private ArrayList<Feedback> feedbacks;*/

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
    
    
    //this.address = address;
    //this.city = city;
    //this.zipcode = zipcode; 
  }
  
  public User() {}

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

	/*public void addFeedback(Feedback feedback) {
		feedbacks.add(feedback);
		if(!feedback.getUser().equals(this))
			feedback.setUser(this);
		
	}
	
	public ArrayList<Feedback> getFeedbacks(){
		return feedbacks;
	}
	
	public void setFeedbacks(ArrayList<Feedback> feedbacks) {
		this.feedbacks=feedbacks;
		feedbacks.forEach(f->f.setUser(this));
	}*/
	



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


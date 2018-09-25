package entities;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
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


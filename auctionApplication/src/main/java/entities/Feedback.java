package entities;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Feedback {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private int feedbackId;


	  private String feedback;

	  //Foreignkey
	  //@OneToMany
	  //User userId

	  //@OneToMany
	  //Product productId

	  public Feedback() {

	  }

	  public String getFeedback() {
	    return feedback;
	  }

	  public void setFeedback() {
	    this.feedback = feedback;
	  }
	}

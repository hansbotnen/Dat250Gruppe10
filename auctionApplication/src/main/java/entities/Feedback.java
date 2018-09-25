package entities;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Feedback {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private int feedbackId;


	  private String feedback;

	  @OneToOne
	  @JoinColumn(name = "user_fk")
	  private User user;

	  @OneToOne
	  @JoinColumn(name = "product_fk")
	  private Product product;

	  /**
	   * 
	   * @param user user that the feedback belongs to
	   * @param product product that the feedback is for
	   * @param feedback 
	   */
	  public Feedback(User user, Product product, String feedback) {
		  this.user=user;
		  this.product=product;
		  this.feedback=feedback;
	  }

	  public String getFeedback() {
	    return feedback;
	  }

	  public void setFeedback(String feedback) {
	    this.feedback = feedback;
	  }
	}

package entities;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Feedback {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private int feedbackId;


	  private String feedback;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "userId")
	  private User user;
	  
	  @OneToOne(mappedBy = "feedback")
	  @JoinColumn(name = "product")
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

	  public Feedback() {}
	  
	  public String getFeedback() {
	    return feedback;
	  }

	  public void setFeedback(String feedback) {
	    this.feedback = feedback;
	  }
	  
	  public User getUser() {
		  return user;
	  }
	  
	  public void setUser(User user) {
		  this.user=user;
		  if(!user.getFeedbacks().contains(this))
			  user.addFeedback(this);
			  
	  }
	}

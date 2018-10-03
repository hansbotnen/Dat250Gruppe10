package entities;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="feedback")
public class Feedback {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private int feedbackId;

	  @Column(name="feedback")
	  private String feedback;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "accountId")
	  private Account account;
	  
//	  @OneToOne(mappedBy = "feedback")
//	  @JoinColumn(name = "product")
//	  private Product product;

	  /**
	   * 
	   * @param user user that the feedback belongs to
	   * @param product product that the feedback is for
	   * @param feedback 
	   */
	  public Feedback(Account account, Product product, String feedback) {
		  this.account=account;
//		  this.product=product;
		  this.feedback=feedback;
	  }

	  public Feedback() {}
	  
	  public String getFeedback() {
	    return feedback;
	  }

	  public void setFeedback(String feedback) {
	    this.feedback = feedback;
	  }
	  
	  public Account getAccount() {
		  return account;
	  }
	  
//	  public void setAccount(Account account) {
//		  this.account=account;
//		  if(!account.getFeedbacks().contains(this))
//			  account.addFeedback(this);
//			  
//	  }
	}

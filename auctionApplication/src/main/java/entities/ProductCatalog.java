package entities;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class ProductCatalog{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int catalogId;

  //Foreignkey: userId

  
  
  private String catalogName;
  
  
}


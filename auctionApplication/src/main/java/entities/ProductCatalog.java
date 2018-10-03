package entities;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class ProductCatalog implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int catalogId;

	private String catalogName;
	
	@OneToOne
	private Account account; 
	
//	@OneToMany
//	private ArrayList<Product> catalog;
	
	public ProductCatalog() {}
	
	public ProductCatalog(String catalogName, Account account) {
		this.catalogName = catalogName;
		this.account = account;
	}
	
	public int getCatalogId() {
		return catalogId;
	}
	
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName; 
	}
	
	public String getCatalogName() {
		return catalogName; 
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public Account getAccount() {
		return account;
	}
	
//	public void addProduct(Product product) {
//		catalog.add(product);
//	}
//	
//	public ArrayList<Product> getCatalog() {
//		return catalog;
//	}
	
}

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
	
	@OneToMany(
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private ArrayList<Product> catalog = new ArrayList<Product>();
	
	public ProductCatalog() {}
	
	public ProductCatalog(String catalogName, Account account) {
		this.catalogName = catalogName;
		this.account = account;
	}
	
	public int getId() {
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
	
	public void addProduct(Product product) {
		catalog.add(product);
	}
	
	public void removeProduct(int id) {
		if (catalog.get(id) != null)
			catalog.remove(id);
	}
	
	public Integer getSize() {
		return catalog.size();
	}
	
	public Product getProductFromCatalog(int id) {
		Product product = null;
		for (Product p : catalog) {
			if (p.getId() == id) 
				product = p;
			else 
				throw new NullPointerException();
		}
		
		return product;
	}
	
	
}

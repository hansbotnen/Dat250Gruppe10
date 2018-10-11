package entities;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "ProductCatalog.findAll", query = "SELECT b From ProductCatalog b") })
@Table(name = "productcatalog")
public class ProductCatalog implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String FIND_ALL = "ProductCatalog.findAll";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int catalogId;

	private String catalogName;
	
	@OneToOne
	private Account account; 
	
	@OneToMany(
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private ArrayList<Product> productList = new ArrayList<Product>();
	
	public ProductCatalog() {}
	
	public ProductCatalog(String catalogName, Account account) {
		this.catalogName = catalogName;
		this.account = account;
		account.setProductCatalog(this);
	}
	
	public ProductCatalog(Account account) {
		this.catalogName = account.getName() + "'s Catalog";
		this.account = account;
		account.setProductCatalog(this);
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
		productList.add(product);
	}
	
	public void removeProduct(int id) {
		if (productList.get(id) != null)
			productList.remove(id);
	}
	
	public Integer getSize() {
		return productList.size();
	}
	
	public Product getProductFromCatalog(int id) {
		Product product = null;
		for (Product p : productList) {
			if (p.getId() == id) 
				product = p;
			else 
				throw new NullPointerException();
		}
		
		return product;
	}
	
	
}

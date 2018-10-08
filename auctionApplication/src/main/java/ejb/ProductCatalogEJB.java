package ejb;

import javax.ejb.Stateful;

import entities.Product;
import entities.ProductCatalog;

@Stateful
public class ProductCatalogEJB {
	
	private ProductCatalog pc = new ProductCatalog();
	
	public void addProduct(Product product) {
		if (pc.getProductFromCatalog(product.getId()) == null)
			pc.addProduct(product);
	}
	
	public void removeProduct(int id) {
		if(pc.getProductFromCatalog(id) != null) 
			pc.removeProduct(id);
	}
	
	public Integer getNumberOfProducts() {
		return pc.getSize();
	}
	
	
}

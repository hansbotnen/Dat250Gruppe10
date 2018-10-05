package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(ProductCatalog.class)
public class ProductCatalogs extends ArrayList<ProductCatalog> {

	private static final long serialVersionUID = 1L;

	public ProductCatalogs() {
		super();
	}

	public ProductCatalogs(Collection<? extends ProductCatalog> c) {
		super(c);
	}

	@XmlElement(name = "productcatalog")
	public List<ProductCatalog> getProductCatalog() {
		return this;
	}

	public void setProductCatalog(List<ProductCatalog> productCatalog) {
		this.addAll(productCatalog);
	}

}

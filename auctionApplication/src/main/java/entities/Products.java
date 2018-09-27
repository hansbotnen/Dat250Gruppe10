package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(Product.class)
public class Products extends ArrayList<Product> {

	private static final long serialVersionUID = 1L;

	public Products() {
		super();
	}

	public Products(Collection<? extends Product> c) {
		super(c);
	}

	@XmlElement(name = "product")
	public List<Product> getProduct() {
		return this;
	}

	public void setProduct(List<Product> Product) {
		this.addAll(Product);
	}

}
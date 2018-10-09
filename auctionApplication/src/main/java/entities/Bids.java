package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(Bid.class)
public class Bids extends ArrayList<Bid> {

	private static final long serialVersionUID = 1L;

	public Bids() {
		super();
	}

	public Bids(Collection<? extends Bid> c) {
		super(c);
	}

	@XmlElement(name = "bid")
	public List<Bid> getBids() {
		return this;
	}

	public void setBids(List<Bid> Bid) {
		this.addAll(Bid);
	}

}

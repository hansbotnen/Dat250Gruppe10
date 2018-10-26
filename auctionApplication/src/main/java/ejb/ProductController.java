package ejb;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.naming.NamingException;
import entities.Accounts;
import entities.Product;
import entities.Products;
import entities.Bid;
@Named(value = "productController")
@RequestScoped
public class ProductController {
    @EJB
    private AuctionDao auctionDao;
    private Product product;
    private Bid bid;
    private String productId;
    
    private String amount;
    public String parametersAction(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        productId = params.get("productId");
        return "product.xhtml";
    }
    public String getAmount() {
        return amount;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    // Also change account in bid
    //Doesnt work
    public String bidOnProduct(String pid) {
        product = auctionDao.getProduct(pid);
        if (product == null)
            return "productlist.xhtml";
        int bid = Integer.parseInt(amount);
        auctionDao.bidOnProduct(bid, product);
        return "productlist.xhml";
    }
    
    public String updateBid() {
    	Product product = auctionDao.getProduct("4");
    	Bid bid = product.getBid();
        int bidInt = Integer.parseInt(amount);
    	auctionDao.updateBid(bid, bidInt);
        return "productlist.xhml";
    }
    
    public String getProductId() {
        return productId;
    }
    public Product getProductById(String id) {
        return auctionDao.getProduct(id);
    }
    public Products getProducts() {
        return auctionDao.getAllProducts();
    }
    public String createProduct() throws NamingException, JMSException {
        this.auctionDao.createBidonProduct(bid, product);
        return "productlist.xhtml";
    }
    public Product getProduct() {
        if (this.product == null) {
            product = new Product();
        }
        return product;
    }
    public Bid getBid() {
        if (this.bid == null) {
            bid = new Bid();
        }
        return bid;
    }
    
}
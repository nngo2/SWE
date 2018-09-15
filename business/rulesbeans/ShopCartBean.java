package business.rulesbeans;

import business.externalinterfaces.DynamicBean;
import business.externalinterfaces.IAddress;
import business.externalinterfaces.ICartItem;
import business.externalinterfaces.ICreditCard;
import business.externalinterfaces.IShoppingCart;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * @author  nngo2
 */
public class ShopCartBean implements DynamicBean {
	//private static Logger log = Logger.getLogger(null);
    
	private IShoppingCart shopCart;
	public ShopCartBean(IShoppingCart sc){		
		shopCart = sc;
	}
	
	
	///////bean interface for shopping cart
	public boolean getIsEmpty() {
		return shopCart.isEmpty();
	}
	public IAddress getShippingAddress() {
		return shopCart.getShippingAddress();
	}
    public IAddress getBillingAddress(){
		return shopCart.getBillingAddress();
	}
    public ICreditCard getPaymentInfo(){
		return shopCart.getPaymentInfo();
	}
    public List<ICartItem> getCartItems(){
		return shopCart.getCartItems();
	}
    
	///////////property change listener code
    private PropertyChangeSupport pcs = 
    	new PropertyChangeSupport(this);
    public void addPropertyChangeListener(PropertyChangeListener pcl){
	 	pcs.addPropertyChangeListener(pcl);
	}
	public void removePropertyChangeListener(PropertyChangeListener pcl){	
    	pcs.removePropertyChangeListener(pcl);
    }	
}

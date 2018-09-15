package business.rulesbeans;

import business.externalinterfaces.DynamicBean;
import business.externalinterfaces.IAddress;
import business.externalinterfaces.ICreditCard;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * @author  nngo2
 */
public class PaymentBean implements DynamicBean{
	private ICreditCard cc;
	private IAddress addr;
	public PaymentBean(IAddress addr, ICreditCard cc) {
		this.addr = addr;
		this.cc = cc;
	}
	//////////// bean interface for address and credit card
	public String getCity() {
        return addr.getCity();
    }
 
    public String getState() {
        return addr.getState();
    }
 
     public String getStreet() {
        return addr.getStreet1();
    }
 
    public String getZip() {
        return addr.getZip();
    }
    public String getCardNum(){
    	
    	return cc.getCardNum();
    }
    public String getExpirationDate(){
    	return cc.getExpirationDate();
    }
    public String getNameOnCard() {
    	return cc.getNameOnCard();
    }
    
    public String getCardType() {
    	return cc.getCardType();
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

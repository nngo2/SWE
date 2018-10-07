package business.shoppingcartsubsystem;

import business.externalinterfaces.IAddress;
import business.externalinterfaces.ICartItem;
import business.externalinterfaces.ICreditCard;
import business.externalinterfaces.IShoppingCart;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * @author  
 */
class ShoppingCart implements IShoppingCart {
    private String cartId;
    private List<ICartItem> cartItems;
    private IAddress shipAddress;
    private IAddress billAddress;
    private ICreditCard creditCard;
    
    ShoppingCart(List<ICartItem> cartItems) {
        this.cartItems = cartItems;
    }
    ShoppingCart(){
    	cartItems = new ArrayList<ICartItem>();
    }
    /**
	 * @param cartId  the cartId to set
	 * @uml.property  name="cartId"
	 */
    void setCartId(String cartId){
        this.cartId=cartId;
    }
    
    String getCartId() {
    	return cartId;
    }
    
    public boolean isEmpty(){
    	return cartItems == null || cartItems.isEmpty();
    }
    
    void addItem(CartItem item){
        if(cartItems == null){
            cartItems = new LinkedList<ICartItem>();
        }
        cartItems.add(item);
    }
    
    void insertItem(int pos, CartItem item){
        if(cartItems == null || pos >= cartItems.size()){
        	addItem(item);
            
        }
        cartItems.add(pos, item);
    }
    
    /**
	 * @return  the cartItems
	 * @uml.property  name="cartItems"
	 */
    public List<ICartItem>getCartItems(){
        return cartItems;
    }
    
    /**
	 * @param shipAddress  the shipAddress to set
	 * @uml.property  name="shipAddress"
	 */
    void setShipAddress(IAddress addr){
        shipAddress = addr;
    }
    /**
	 * @param billAddress  the billAddress to set
	 * @uml.property  name="billAddress"
	 */
    void setBillAddress(IAddress addr){
        billAddress = addr;
    }
    
    void setPaymentInfo(ICreditCard cc) {
        creditCard = cc;
    }

    public IAddress getShippingAddress() {        
        return shipAddress;
    }

    public IAddress getBillingAddress() {
        return billAddress;
    }
 
    public ICreditCard getPaymentInfo() {
        return creditCard;
    }
    
    public boolean deleteCartItem(int pos) {
    	Object ob = cartItems.remove(pos);
    	return (ob != null);
    }
    
    public boolean deleteCartItem(String name) { 	
    	Object ob = cartItems.remove(name);
    	return (ob != null);
	}
    
    public void clearCart() {
    	cartItems.clear();
    }
	
	public double getTotalPrice(){
    	double sum= 0.00;
    	
    	Iterator<ICartItem> itr = cartItems.iterator();
        while(itr.hasNext()){
        	ICartItem item =itr.next();
        	sum += Double.parseDouble(item.getTotalprice());
        }
    	return sum;
    }
    
    
 
}

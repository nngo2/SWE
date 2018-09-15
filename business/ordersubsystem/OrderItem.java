package business.ordersubsystem;

import business.externalinterfaces.IOrderItem;


/**
 * @author  nngo2
 */
public class OrderItem implements IOrderItem {
    Integer lineitemid;
    Integer productid;
    Integer orderid;
    String quantity;
    String totalPrice;
    
    /** Used for reading data from database */
    public OrderItem(Integer lineitemid, Integer productid, Integer orderid, String quantity, String totalPrice){
        this.lineitemid = lineitemid;
        this.productid = productid;
        this.orderid = orderid;
        this.quantity = quantity;
        this.totalPrice= totalPrice;
    }
    
    /** Used for creating order item to send to dbase */
    public OrderItem(Integer productid, Integer orderid, String quantity, String totalPrice){
        
        this.productid = productid;
        this.orderid = orderid;
        this.quantity = quantity;
        this.totalPrice= totalPrice;
    } 
    
    public String toString(){
        StringBuffer buf = new StringBuffer();
        buf.append("lineitemid: <"+lineitemid+">,");
        buf.append("productid: <"+productid+">,");
        buf.append("orderid: <"+orderid+">,");
        buf.append("quantity: <"+quantity+">,");
        buf.append("totalPrice: <"+totalPrice+">");
        return buf.toString();
    }
    public void setLineItemId(Integer lid){
        lineitemid = lid;
    }
    
    /**
	 * When submitting an order, orderid is not known initially; after order level is submitted,  orderid can be read and inserted into orderitems
	 * @uml.property  name="orderid"
	 */
    public void setOrderid(Integer orderid) {
    	this.orderid = orderid;
    }
    
    /**
	 * @return  the lineitemid
	 * @uml.property  name="lineitemid"
	 */
    public Integer getLineitemid() {
        return lineitemid;
    }
    
    /**
	 * @return  the productid
	 * @uml.property  name="productid"
	 */
    public Integer getProductid() {    
        return productid;
    }
    
    /**
	 * @return  the orderid
	 * @uml.property  name="orderid"
	 */
    public Integer getOrderid() {       
        return orderid;
    }
    /**
	 * @return  the quantity
	 * @uml.property  name="quantity"
	 */
    public String getQuantity() {      
        return quantity;
    }
    
    /**
	 * @return  the totalPrice
	 * @uml.property  name="totalPrice"
	 */
    public String getTotalPrice() {
        return totalPrice;
    }
}

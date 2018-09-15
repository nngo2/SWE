
package business.ordersubsystem;

import business.externalinterfaces.IAddress;
import business.externalinterfaces.ICreditCard;
import business.externalinterfaces.IOrder;
import business.externalinterfaces.IOrderItem;
import java.util.List;


/**
 * @author  nngo2
 */
class Order implements IOrder{
    private Integer orderId;
    private String orderDate;
    private String totalPrice;
    private List<IOrderItem> orderItems;
	private IAddress shipAddress;
	private IAddress billAddress;
	private ICreditCard creditCard;    	
    
    Order(Integer orderId,String orderDate,String totalPrice){
        
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }
    /**
	 * @param orderItems  the orderItems to set
	 * @uml.property  name="orderItems"
	 */
    public void setOrderItems(List<IOrderItem> orderItems){
    	this.orderItems = orderItems;
    }
    /**
	 * @return  the orderItems
	 * @uml.property  name="orderItems"
	 */
    public List<IOrderItem> getOrderItems(){
        return orderItems;
    }

	/**
	 * @return  the orderDate
	 * @uml.property  name="orderDate"
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * @return  the orderId
	 * @uml.property  name="orderId"
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * @return  the totalPrice
	 * @uml.property  name="totalPrice"
	 */
	public String getTotalPrice() {
		return totalPrice;
	}

    /**
	 * @return  Returns the billAddress.
	 * @uml.property  name="billAddress"
	 */
    public IAddress getBillAddress() {
        return billAddress;
    }
    /**
	 * @param billAddress  The billAddress to set.
	 * @uml.property  name="billAddress"
	 */
    public void setBillAddress(IAddress billAddress) {
        this.billAddress = billAddress;
    }
 
    /**
	 * @param creditCard  The creditCard to set.
	 * @uml.property  name="creditCard"
	 */
    public void setCreditCard(ICreditCard creditCard) {
        this.creditCard = creditCard;
    }
    /**
	 * @return  Returns the shipAddress.
	 * @uml.property  name="shipAddress"
	 */
    public IAddress getShipAddress() {
        return shipAddress;
    }
    /**
	 * @param shipAddress  The shipAddress to set.
	 * @uml.property  name="shipAddress"
	 */
    public void setShipAddress(IAddress shipAddress) {
        this.shipAddress = shipAddress;
    }

    public ICreditCard getPaymentInfo() {
 
        return creditCard;
    }

}

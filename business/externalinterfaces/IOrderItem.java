
package business.externalinterfaces;


/**
 * @author  
 */
public interface IOrderItem {
    public Integer getLineitemid();
    public Integer getProductid();
    /**
	 * @return
	 * @uml.property  name="orderid"
	 */
    public Integer getOrderid();
    public String getQuantity();
    public String getTotalPrice();
    /**
	 * @param orderid
	 * @uml.property  name="orderid"
	 */
    public void setOrderid(Integer orderid);
    public void setLineItemId(Integer id);


}

package business.ordersubsystem;

import business.externalinterfaces.ICartItem;
import business.externalinterfaces.ICustomerProfile;
import business.externalinterfaces.IOrder;
import business.externalinterfaces.IOrderItem;
import business.externalinterfaces.IOrderSubsystem;
import business.externalinterfaces.IShoppingCart;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import middleware.DatabaseException;

/**
 * @author  
 */
public class OrderSubsystemFacade implements IOrderSubsystem {
	private static final Logger LOG = Logger
			.getLogger(OrderSubsystemFacade.class.getPackage().getName());
	ICustomerProfile custProfile;

	public OrderSubsystemFacade(ICustomerProfile custProfile) {
		this.custProfile = custProfile;
	}

	// /////////// Interface methods

	// /////////// Convenience methods internal to the Order Subsystem
	List<String> getAllOrderIds() throws DatabaseException {
		DbClassOrder dbClass = new DbClassOrder();
		return dbClass.getAllOrderIds(custProfile);
	}

	List<IOrderItem> getOrderItems(String orderId) throws DatabaseException {
		DbClassOrder dbClass = new DbClassOrder();
		return dbClass.getOrderItems(orderId);
	}

	Order getOrderData(String orderId) throws DatabaseException {
		DbClassOrder dbClass = new DbClassOrder();
		return dbClass.getOrderData(orderId);
	}

	@Override
	public List<IOrder> getOrderHistory() throws DatabaseException {
		DbClassOrder dbClass = new DbClassOrder();
		return dbClass.getOrderHistory(custProfile);
	}

	@Override
	public IOrder createOrder(Integer id, String dateOfOrder, String totalPrice) {
		return new Order(id, dateOfOrder, totalPrice);
	}

	@Override
	public void submitOrder(IShoppingCart shopCart) throws DatabaseException {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Order order = new Order(null, dateFormat.format(new Date()), new Double(shopCart.getTotalPrice()).toString());
		order.setShipAddress(shopCart.getShippingAddress());
		order.setBillAddress(shopCart.getBillingAddress());
		order.setCreditCard(shopCart.getPaymentInfo());
		order.setCustId(custProfile.getCustId());	
		
		DbClassOrder dbClass = new DbClassOrder();
		Integer key = dbClass.addOder(order);
		order.setOrderId(key);
		
		for (ICartItem item : shopCart.getCartItems()) {
			OrderItem orderItem = new OrderItem(item.getProductid(), key, item.getQuantity(), item.getTotalprice());
			dbClass.addOderItem(orderItem);
		}
	}

	@Override
	public IOrderItem createOrderItem(Integer prodId, Integer orderId,
			String quantityReq, String totalPrice) {
		return new OrderItem(prodId, orderId, quantityReq, totalPrice);
	}
}

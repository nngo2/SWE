package business.ordersubsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import middleware.DatabaseException;
import business.externalinterfaces.ICustomerProfile;
import business.externalinterfaces.IOrder;
import business.externalinterfaces.IOrderItem;
import business.externalinterfaces.IOrderSubsystem;
import business.externalinterfaces.IShoppingCart;

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
		// need to implement
		return new ArrayList<IOrderItem>();
	}

	Order getOrderData(String orderId) throws DatabaseException {
		// need to implement
		return new Order(1, "11/20/2011", "20.20");
	}

	@Override
	public List<IOrder> getOrderHistory() throws DatabaseException {
		Order order = new Order(1, "11/20/2011", "20.20");
		List<IOrder> list = new ArrayList<IOrder>();
		OrderItem orderItem = new OrderItem(1, 1, 1, "1", "20.20");
		List<IOrderItem> orderItems = new ArrayList<IOrderItem>();
		orderItems.add(orderItem);
		order.setOrderItems(orderItems);
		list.add(order);
		return list;
	}

	@Override
	public IOrder createOrder(Integer id, String dateOfOrder, String totalPrice) {
		return new Order(id, dateOfOrder, totalPrice);
	}

	@Override
	public void submitOrder(IShoppingCart shopCart) throws DatabaseException {
		// TODO Auto-generated method stub
	}

	@Override
	public IOrderItem createOrderItem(Integer prodId, Integer orderId,
			String quantityReq, String totalPrice) {
		// TODO Auto-generated method stub
		return null;
	}
}
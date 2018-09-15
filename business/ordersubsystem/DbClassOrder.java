package business.ordersubsystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import middleware.DatabaseException;
import middleware.DbConfigProperties;
import middleware.dataaccess.DataAccessSubsystemFacade;
import middleware.externalinterfaces.DbConfigKey;
import middleware.externalinterfaces.IDataAccessSubsystem;
import middleware.externalinterfaces.IDbClass;
import business.externalinterfaces.ICustomerProfile;
import business.externalinterfaces.IOrder;
import business.externalinterfaces.IOrderItem;

/**
 * @author  nngo2
 */
class DbClassOrder implements IDbClass {
	private static final Logger LOG = Logger.getLogger(DbClassOrder.class
			.getPackage().getName());
	private IDataAccessSubsystem dataAccessSS = new DataAccessSubsystemFacade();
	private String query;
	private String queryType;
	private final String GET_ORDER_ITEMS = "GetOrderItems";
	private final String GET_ORDER_IDS = "GetOrderIds";
	private final String GET_ORDER_DATA = "GetOrderData";
	private final String GET_ORDER_HISTORY = "GetOrderHistory";
	private ICustomerProfile customerProfile;
	private String orderId;
	private List<String> orderIds;
	private List<IOrderItem> orderItems;
	private Order orderData;
	private List<IOrder> orderHistory;

	public List<String> getAllOrderIds(ICustomerProfile customerProfile) throws DatabaseException {
		this.customerProfile = customerProfile;
		queryType = GET_ORDER_IDS;
		dataAccessSS.atomicRead(this);
		return orderIds;
	}

	public Order getOrderData(String orderId) throws DatabaseException {
		this.orderId = orderId;
		queryType = GET_ORDER_DATA;
		dataAccessSS.atomicRead(this);
		return orderData;
	}

	public List<IOrderItem> getOrderItems(String orderId) throws DatabaseException {
		this.orderId = orderId;
		queryType = GET_ORDER_ITEMS;
		dataAccessSS.atomicRead(this);
		return orderItems;
	}
	
	public List<IOrder> getOrderHistory(ICustomerProfile customerProfile) throws DatabaseException {
		this.customerProfile = customerProfile;
		queryType = GET_ORDER_HISTORY;
		dataAccessSS.atomicRead(this);
		return orderHistory;
	}

	public void buildQuery() {
		if (queryType.equals(GET_ORDER_ITEMS)) {
			buildGetOrderItemsQuery();
		} else if (queryType.equals(GET_ORDER_IDS)) {
			buildGetOrderIdsQuery();
		} else if (queryType.equals(GET_ORDER_DATA)) {
			buildGetOrderDataQuery();
		} else if (queryType.equals(GET_ORDER_HISTORY)) {
			buildGetOrderHistoryQuery();
		}
	}

	private void buildGetOrderDataQuery() {
		query = "SELECT orderdate, totalpriceamount FROM Ord WHERE orderid = '"
				+ orderId + "'";
	}

	private void buildGetOrderIdsQuery() {
		query = "SELECT orderid FROM Ord WHERE custid = '"
				+ customerProfile.getCustId() + "'";
	}

	private void buildGetOrderItemsQuery() {
		query = "SELECT * FROM OrderItem WHERE orderid = '" + orderId + "'";
	}
	
	private void buildGetOrderHistoryQuery() {
		query = "SELECT * FROM Ord WHERE custid = '" +  customerProfile.getCustId() + "'";
	}

	private void populateOrderItems(ResultSet rs) throws DatabaseException {
		orderItems = new LinkedList<IOrderItem>();
		if (rs != null) {
			try {
				while (rs.next()) {
					IOrderItem item = new OrderItem(
							rs.getInt("orderitemid"),
							rs.getInt("productid"),
							rs.getInt("orderid"),
							rs.getString("quantity"),
							rs.getString("totalprice"));
					orderItems.add(item);
				}
			} catch (SQLException e) {
				throw new DatabaseException(e);
			}
		}
	}

	private void populateOrderIds(ResultSet rs) throws DatabaseException {
		orderIds = new LinkedList<String>();
		if (rs != null) {
			try {
				while (rs.next()) {
					orderIds.add(rs.getString("orderid"));
				}
			} catch (SQLException e) {
				throw new DatabaseException(e);
			}
		}
	}

	private void populateOrderData(ResultSet rs) throws DatabaseException {
		orderData = null;
		if (rs != null) {
			try {
				orderData = new Order(rs.getInt("orderid"), 
						rs.getString("orderdate"), rs.getString("totalpriceamount"));
			} catch (SQLException e) {
				throw new DatabaseException(e);
			}
		}
	}
	
	private void populateOrderHistory(ResultSet rs)
		throws DatabaseException {
		orderHistory = new LinkedList<IOrder>();
		if (rs != null) {
			try {
				while (rs.next()) {
					IOrder order = new Order(rs.getInt("orderid"), 
							rs.getString("orderdate"), rs.getString("totalpriceamount"));
					orderHistory.add(order);
				}
			} catch (SQLException e) {
				throw new DatabaseException(e);
			}
		}
	}

	public void populateEntity(ResultSet resultSet) throws DatabaseException {
		if (queryType.equals(GET_ORDER_ITEMS)) {
			populateOrderItems(resultSet);
		} else if (queryType.equals(GET_ORDER_IDS)) {
			populateOrderIds(resultSet);
		} else if (queryType.equals(GET_ORDER_DATA)) {
			populateOrderData(resultSet);
		} else if (queryType.equals(GET_ORDER_HISTORY)) {
			populateOrderHistory(resultSet);
		}
	}

	public String getDbUrl() {
		DbConfigProperties props = new DbConfigProperties();
		return props.getProperty(DbConfigKey.ACCOUNT_DB_URL.getVal());

	}

	/**
	 * @return  the query
	 * @uml.property  name="query"
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * @param orderId  the orderId to set
	 * @uml.property  name="orderId"
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;

	}
}

package business.shoppingcartsubsystem;

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
import business.externalinterfaces.ICartItem;
import business.externalinterfaces.ICustomerProfile;

/**
 * @author  
 */
public class DbClassShoppingCart implements IDbClass {
	private static final Logger LOG = Logger
			.getLogger(DbClassShoppingCart.class.getPackage().getName());
	private IDataAccessSubsystem dataAccessSS = new DataAccessSubsystemFacade();
	IDataAccessSubsystem dataAccess;
	ShoppingCart cart;
	ICartItem cartItem;
	List<ICartItem> cartItemsList;
	ICustomerProfile custProfile;
	Integer cartId;
	String query;
	final String GET_ID = "GetId";
	final String GET_SAVED_ITEMS = "GetSavedItems";
	final String SAVE_CART_ITEM = "SaveCartItem";
	final String SAVE_CART_INFO = "SaveCartInfo";	
	String queryType;

	public void buildQuery() {
		if (queryType.equals(GET_ID)) {
			buildGetIdQuery();
		} else if (queryType.equals(GET_SAVED_ITEMS)) {
			buildGetSavedItemsQuery();
		} else if (queryType.equals(SAVE_CART_INFO)) {
			buildSaveCartInfoQuery();
		} else if (queryType.equals(SAVE_CART_ITEM)) {
			buildSaveCartItemQuery();
		}
	}

	private void buildSaveCartItemQuery() {	
		query =  "INSERT into ShopCartItem (" 
			+ " shopcartid, productid, quantity, totalprice, shipmentcost, taxamount) VALUES("
			+ cart.getCartId() + ", " 
			+ cartItem.getProductid() + ", " 
			+ cartItem.getQuantity() + ", " 
			+ cartItem.getTotalprice() + ", 0, 0); "; 
	}

	private void buildSaveCartInfoQuery() {
		query =  "INSERT into ShopCartTbl (" 
		+ " custid) VALUES("
		+ custProfile.getCustId() + ")"; 
	}

	private void buildGetIdQuery() {
		query = "SELECT shopcartid " + "FROM ShopCartTbl " + "WHERE custid = "
				+ custProfile.getCustId();
	}

	private void buildGetSavedItemsQuery() {
		query = "SELECT * FROM ShopCartItem WHERE shopcartid = " + cartId;
	}
	
	public Integer saveCartItem(ShoppingCart cart, ICartItem item)
			throws DatabaseException {
		this.cart = cart;
		this.cartItem = item;
		queryType = SAVE_CART_ITEM;
		return dataAccessSS.saveWithinTransaction(this);
	}	
	
	public Integer saveCartInfo(ICustomerProfile custProfile, ShoppingCart cart)
		throws DatabaseException {
		this.custProfile = custProfile;
		this.cart = cart;
		queryType = SAVE_CART_INFO;
		return dataAccessSS.saveWithinTransaction(this);
	}

	public Integer getShoppingCartId(ICustomerProfile custProfile)
			throws DatabaseException {
		this.custProfile = custProfile;
		queryType = GET_ID;
		dataAccessSS.atomicRead(this);
		return cartId;
	}

	public List<ICartItem> getSavedCartItems(Integer cartId)
			throws DatabaseException {
		this.cartId = cartId;
		queryType = GET_SAVED_ITEMS;
		dataAccessSS.atomicRead(this);
		return cartItemsList;

	}

	public void populateEntity(ResultSet resultSet) throws DatabaseException {
		if (queryType.equals(GET_ID)) {
			populateShopCartId(resultSet);
		} else if (queryType.equals(GET_SAVED_ITEMS)) {
			populateCartItemsList(resultSet);
		}
	}

	private void populateShopCartId(ResultSet rs) {
		try {
			if (rs.next()) {
				cartId = rs.getInt("shopcartid");
			}
		} catch (SQLException e) {
			//do nothing
		}
	}

	private void populateCartItemsList(ResultSet rs) throws DatabaseException {
		cartItemsList = new LinkedList<ICartItem>();
		if (rs != null) {
			try {
				while (rs.next()) {
					ICartItem cartItem = new CartItem(
							rs.getInt("shopcartid"),
							rs.getInt("productid"),
							rs.getInt("cartitemid"),
							rs.getString("quantity"),
							rs.getString("totalprice"),
							true
					);
					cartItemsList.add(cartItem);
				}
			} catch (SQLException e) {
				throw new DatabaseException(e);
			}
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
}

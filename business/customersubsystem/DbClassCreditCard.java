package business.customersubsystem;

import business.externalinterfaces.ICreditCard;
import business.externalinterfaces.ICustomerProfile;
import java.sql.ResultSet;
import java.sql.SQLException;
import middleware.DatabaseException;
import middleware.DbConfigProperties;
import middleware.dataaccess.DataAccessSubsystemFacade;
import middleware.externalinterfaces.DbConfigKey;
import middleware.externalinterfaces.IDataAccessSubsystem;
import middleware.externalinterfaces.IDbClass;

/**
 * @author  nngo2
 */
class DbClassCreditCard implements IDbClass {
	private IDataAccessSubsystem dataAccess = new DataAccessSubsystemFacade();
	private final String READ_DEFAULT_PAYMENT = "ReadDefaultPayment";
	private ICustomerProfile custProfile;
	private String query;
	private String queryType;
	private CreditCard defaultPayment;
	
	/**
	 * @return  the defaultPayment
	 * @uml.property  name="defaultPayment"
	 */
	CreditCard getDefaultPayment() {
		return defaultPayment;
	}

	public void buildQuery() {
		if (queryType.equals(READ_DEFAULT_PAYMENT)) {
			buildReadDefaultPaymentQuery();
		}
	}

	private void buildReadDefaultPaymentQuery() {
		query = "SELECT nameoncard, expdate, cardtype, cardnum "
				+ "FROM Customer " + "WHERE custid = "
				+ custProfile.getCustId();
	}

	public void populateEntity(ResultSet resultSet) throws DatabaseException {
		if (queryType.equals(READ_DEFAULT_PAYMENT)) {
			populateDefaultPayment(resultSet);
		}
	}

	private void populateDefaultPayment(ResultSet resultSet) throws DatabaseException{
		try {
			if (resultSet.next()) {
				defaultPayment = new CreditCard(
						resultSet.getString("nameoncard"),
						resultSet.getString("expdate"), 
						resultSet.getString("cardnum"),
						resultSet.getString("cardtype"));
			}
		} catch (SQLException e) {
			throw new DatabaseException(e);
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

	void readDefaultPayment(ICustomerProfile custProfile)
			throws DatabaseException {
		this.custProfile = custProfile;
		queryType = READ_DEFAULT_PAYMENT;
		dataAccess.atomicRead(this);
	}

}

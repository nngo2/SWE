package business.customersubsystem;

import java.sql.ResultSet;
import java.sql.SQLException;

import middleware.DatabaseException;
import middleware.DbConfigProperties;
import middleware.dataaccess.DataAccessSubsystemFacade;
import middleware.externalinterfaces.DbConfigKey;
import middleware.externalinterfaces.IDataAccessSubsystem;
import middleware.externalinterfaces.IDbClass;

public class DbClassCustomerProfile implements IDbClass {
	private IDataAccessSubsystem dataAccess = new DataAccessSubsystemFacade();
	private final String READ = "Read";
	private Integer custId;
	private String query;
	private String queryType;
	private CustomerProfile custProfile;
	
	@Override
	public void buildQuery() throws DatabaseException {
		query = "SELECT custid, fname, lname "
			+ "FROM Customer " + "WHERE custid = "
			+ custId;
	}

	@Override
	public String getDbUrl() {
		DbConfigProperties props = new DbConfigProperties();
		return props.getProperty(DbConfigKey.ACCOUNT_DB_URL.getVal());
	}

	@Override
	public String getQuery() {
		return query;
	}

	@Override
	public void populateEntity(ResultSet resultSet) throws DatabaseException {
		if (queryType.equals(READ)) {
			populateCustomerProdile(resultSet);
		}
	}
	
	CustomerProfile getCustomerProfile() {
		return custProfile;
	}
	
	void readCustomerProfile(Integer custId) throws DatabaseException {
		this.custId = custId;
		queryType = READ;
		dataAccess.atomicRead(this);
	}
	
	private void populateCustomerProdile(ResultSet resultSet) throws DatabaseException{
		try {
			if (resultSet.next()) {
				custProfile = new CustomerProfile(
						resultSet.getInt("custid"),
						resultSet.getString("fname"), 
						resultSet.getString("lname"));
			}
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}

	}

}

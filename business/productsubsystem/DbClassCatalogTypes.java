package business.productsubsystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import middleware.DatabaseException;
import middleware.DbConfigProperties;
import middleware.dataaccess.DataAccessSubsystemFacade;
import middleware.externalinterfaces.DbConfigKey;
import middleware.externalinterfaces.IDataAccessSubsystem;
import middleware.externalinterfaces.IDbClass;

/**
 * @author  pcorazza  <p>  Class Description:
 */
public class DbClassCatalogTypes implements IDbClass {
	private IDataAccessSubsystem dataAccessSS = new DataAccessSubsystemFacade();
	private String query;
	private String queryType;
	final String GET_TYPES = "GetTypes";
	private CatalogTypes types;

	public void buildQuery() {
		if (queryType.equals(GET_TYPES)) {
			buildGetTypesQuery();
		}
	}

	void buildGetTypesQuery() {
		query = "SELECT * FROM CatalogType";
	}

	/**
	 * This is activated when getting all catalog types.
	 */
	public void populateEntity(ResultSet rs) throws DatabaseException {
		types = new CatalogTypes();
		try {
			while (rs.next()) {
				types.addCatalog(rs.getInt("catalogid"), rs.getString("catalogname"));
			}
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	public String getDbUrl() {
		DbConfigProperties props = new DbConfigProperties();
		return props.getProperty(DbConfigKey.PRODUCT_DB_URL.getVal());
	}

	/**
	 * @return  the query
	 * @uml.property  name="query"
	 */
	public String getQuery() {
		return query;
	}
	

	public CatalogTypes getCatalogTypes() throws DatabaseException {
		queryType = GET_TYPES;
		dataAccessSS.atomicRead(this);
		return types;
	}

}

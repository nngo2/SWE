package unittests.middleware.dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import junit.framework.TestCase;
import middleware.DatabaseException;
import middleware.DbConfigProperties;
import middleware.dataaccess.DataAccessUtil;
import middleware.dataaccess.SimpleConnectionPool;
import middleware.externalinterfaces.DbConfigKey;
import alltests.AllTests;
import dbsetup.DbQueries;

public class DataAccessUtilTest extends TestCase {
	static Logger log = Logger.getLogger(DataAccessUtil.class.getName());
	static {
		AllTests.initializeProperties();
	}
	DbConfigProperties props = new DbConfigProperties();
	final String ACCOUNT_DBURL = props.getProperty(DbConfigKey.ACCOUNT_DB_URL
			.getVal());

	public DataAccessUtilTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		log.info("  Running " + getName());
	}

	protected void tearDown() throws Exception {
		log.info("  Testing " + getName() + " completed");
	}

	public void testGetPoolReturnSimpleConnectionPool() {
		try {
			SimpleConnectionPool pool = DataAccessUtil.getPool();
			assertNotNull(pool);
		} catch (DatabaseException ex) {
			fail("ERROR: Error occurred trying to read table: "
					+ ex.getClass().getName() + " Message: " + ex.getMessage());
		}
	}

	public void testRunQuery() {
		// setup data
		String[] custVals = DbQueries.insertCustomerRow();

		// verify
		String custId = custVals[1];
		try {
			String query = "select * from customer where custid = " + custId;
			SimpleConnectionPool pool = DataAccessUtil.getPool();
			ResultSet resultSet = DataAccessUtil.runQuery(pool, ACCOUNT_DBURL,
					query);
			int count = 0;
			while (resultSet.next()) {
				count++;
			}
			assertEquals(count, 1);
		} catch (DatabaseException ex) {
			fail("ERROR: Error occurred trying to read table: "
					+ ex.getClass().getName() + " Message: " + ex.getMessage());
		} catch (SQLException ex) {
			fail("ERROR: Error occurred trying to read table: "
					+ ex.getClass().getName() + " Message: " + ex.getMessage());
		} finally {
			DbQueries.deleteCustomerRow(Integer.parseInt(custId));
		}
	}

	public void testRunInsert() {
		// setup data		
		String fname = "testf1";
		String lname = "testl1";
		String insertQuery = "INSERT into Customer " + "(custid,fname,lname) "
				+ "VALUES(NULL,'" + fname + "','" + lname + "')";

		String selectQuery = "select custid,fname,lname from customer";

		// verify
		int custId = 0;
		try {
			SimpleConnectionPool pool = DataAccessUtil.getPool();
			custId = DataAccessUtil.runUpdate(pool, ACCOUNT_DBURL, insertQuery);

			int idFound = 0;
			String fNameFound = null;
			String lNameFound = null;
			ResultSet result = DataAccessUtil.runQuery(pool, ACCOUNT_DBURL,
					selectQuery);
			while (result.next()) {
				idFound = Integer.parseInt(result.getString("custid"));
				if (idFound == custId) {
					fNameFound = result.getString("fname");
					lNameFound = result.getString("lname");
				}
			}
			
			assertEquals(fNameFound, fname);
			assertEquals(lNameFound, lname);

		} catch (DatabaseException ex) {
			fail("ERROR: Error occurred trying to read table: "
					+ ex.getClass().getName() + " Message: " + ex.getMessage());
		} catch (SQLException ex) {
			fail("ERROR: Error occurred trying to read table: "
					+ ex.getClass().getName() + " Message: " + ex.getMessage());
		} finally {
			DbQueries.deleteCustomerRow(custId);
		}
	}
	
	public void testRunUpdate() {
		// setup data		
		String fname = "testf1";
		String lname = "testl1";
		String[] custVals = DbQueries.insertCustomerRow();
		int custId = Integer.parseInt(custVals[1]);

		String updateQuery = "UPDATE Customer set fname = '" + fname + "', lName = '" + lname + "' where custid = " + custId; 
		String selectQuery = "select custid,fname,lname from customer";

		// verify
		try {
			SimpleConnectionPool pool = DataAccessUtil.getPool();
			int rows = DataAccessUtil.runUpdate(pool, ACCOUNT_DBURL, updateQuery);

			int idFound = 0;
			String fNameFound = null;
			String lNameFound = null;
			ResultSet result = DataAccessUtil.runQuery(pool, ACCOUNT_DBURL,
					selectQuery);
			while (result.next()) {
				idFound = Integer.parseInt(result.getString("custid"));
				if (idFound == custId) {
					fNameFound = result.getString("fname");
					lNameFound = result.getString("lname");
				}
			}
			
			assertEquals(rows, 1);
			assertEquals(fNameFound, fname);
			assertEquals(lNameFound, lname);

		} catch (DatabaseException ex) {
			fail("ERROR: Error occurred trying to read table: "
					+ ex.getClass().getName() + " Message: " + ex.getMessage());
		} catch (SQLException ex) {
			fail("ERROR: Error occurred trying to read table: "
					+ ex.getClass().getName() + " Message: " + ex.getMessage());
		} finally {
			DbQueries.deleteCustomerRow(custId);
		}
	}
	
	public void testRunDelete() {
		// setup data
		String[] custVals = DbQueries.insertCustomerRow();

		// verify
		String custId = custVals[1];
		try {
			SimpleConnectionPool pool = DataAccessUtil.getPool();
			String deleteQuery = "delete from customer where custid = " + custId;
			DataAccessUtil.runUpdate(pool, ACCOUNT_DBURL,
					deleteQuery);
					
			String selectQuery = "select * from customer where custid = " + custId;
			ResultSet resultSet = DataAccessUtil.runQuery(pool, ACCOUNT_DBURL,
					selectQuery);
			int count = 0;
			while (resultSet.next()) {
				count++;
			}
			assertEquals(count, 0);
		} catch (DatabaseException ex) {
			fail("ERROR: Error occurred trying to read table: "
					+ ex.getClass().getName() + " Message: " + ex.getMessage());
		} catch (SQLException ex) {
			fail("ERROR: Error occurred trying to read table: "
					+ ex.getClass().getName() + " Message: " + ex.getMessage());
		} finally {
			DbQueries.deleteCustomerRow(Integer.parseInt(custId));
		}
	}
}

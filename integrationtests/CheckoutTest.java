package integrationtests;

import java.util.logging.Logger;

import junit.framework.TestCase;
import middleware.DatabaseException;
import middleware.DbConfigProperties;
import middleware.dataaccess.DataAccessUtil;
import middleware.dataaccess.SimpleConnectionPool;
import middleware.externalinterfaces.DbConfigKey;
import alltests.AllTests;
import business.customersubsystem.CustomerSubsystemFacade;
import business.externalinterfaces.ICustomerSubsystem;
import dbsetup.DbQueries;

public class CheckoutTest extends TestCase {
	static String name = "Checkout Test";
	static Logger log = Logger.getLogger(CheckoutTest.class.getName());
	
	DbConfigProperties props = new DbConfigProperties();
	final String ACCOUNT_DBURL = props.getProperty(DbConfigKey.ACCOUNT_DB_URL
			.getVal());
	
	static {
		AllTests.initializeProperties();
	}
	
	private String[] insertCustomer() {
		// setup data		
		String[] vals = new String[3];
		String shipaddress1 = "test-ship-address-1";
		String billaddress1 = "test-bill-address-1";
		String insertQuery = "INSERT into Customer " + "(custid,shipaddress1,billaddress1) "
				+ "VALUES(NULL,'" + shipaddress1 + "','" + billaddress1 + "')";
		
		int custId = 0;
		try {
			// set up customer addresses
			SimpleConnectionPool pool = DataAccessUtil.getPool();
			custId = DataAccessUtil.runUpdate(pool, ACCOUNT_DBURL, insertQuery);
			
		} catch (DatabaseException ex) {
			fail("ERROR: Error occurred trying to read table: "
					+ ex.getClass().getName() + " Message: " + ex.getMessage());
		}
		
		vals[0] = custId + "";
		vals[1] = shipaddress1;
		vals[2] = billaddress1;
		
		return vals;
	}
	
	public void testDefaultShipAndBillAddressesLoaded() {
		// setup data
		String[] custVals = insertCustomer();

		// verify
		String custId = custVals[0];
		try {
			ICustomerSubsystem custSys = new CustomerSubsystemFacade();
			custSys.initializeCustomer(Integer.parseInt(custId));
			
			assertEquals(custSys.getDefaultShippingAddress().getStreet1(), custVals[1]);
			assertEquals(custSys.getDefaultBillingAddress().getStreet1(), custVals[2]);
			
		} catch (DatabaseException ex) {
			fail("ERROR: Error occurred trying to read table: "
					+ ex.getClass().getName() + " Message: " + ex.getMessage());
		} finally {
			DbQueries.deleteCustomerRow(Integer.parseInt(custId));
		}
	}
}

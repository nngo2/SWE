package middleware.externalinterfaces;

/**
 * @author  
 */
public enum DbConfigKey {
	PRODUCT_DB_URL("product_dburl"), 
	ACCOUNT_DB_URL("account_dburl"),
	MAX_CONNECTIONS("max_connections"),
	DB_USER("dbuser"),
	DB_PASSWORD("dbpassword"),
	DRIVER("driver");
	
	private String val;
	private DbConfigKey(String val) {
		this.val = val;
	}
	/**
	 * @return  the val
	 * @uml.property  name="val"
	 */
	public String getVal() {
		return val;
	}
}

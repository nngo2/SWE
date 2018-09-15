package business;

/**
 * Stored at the business level since it's business data. Not stored in product subsystem because contains gui data
 */
public class Quantity {
	private String quantityRequested;
	private String quantityAvailable;
	public Quantity(String quantityRequested) {
		this.quantityRequested = quantityRequested;
	}
	/**
	 * @return  the quantityRequested
	 * @uml.property  name="quantityRequested"
	 */
	public String getQuantityRequested() {
		return quantityRequested;
	}
	/**
	 * @return  the quantityAvailable
	 * @uml.property  name="quantityAvailable"
	 */
	public String getQuantityAvailable() {
		return quantityAvailable;
	}
	/**
	 * @param quantityAvailable  the quantityAvailable to set
	 * @uml.property  name="quantityAvailable"
	 */
	public void setQuantityAvailable(String q) {
		quantityAvailable = q;
	}
	
}

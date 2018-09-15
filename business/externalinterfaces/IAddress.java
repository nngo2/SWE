
package business.externalinterfaces;



/**
 * @author  pcorazza
 * @since  Nov 16, 2004  Class Description:
 */
public interface IAddress {
    /**
	 * @return
	 * @uml.property  name="street1"
	 */
    public String getStreet1();
    /**
	 * @return
	 * @uml.property  name="street2"
	 */
    public String getStreet2();
    /**
	 * @return
	 * @uml.property  name="city"
	 */
    public String getCity();
    /**
	 * @return
	 * @uml.property  name="state"
	 */
    public String getState();
    /**
	 * @return
	 * @uml.property  name="zip"
	 */
    public String getZip();
    /**
	 * @param s
	 * @uml.property  name="street1"
	 */
    public void setStreet1(String s);
    /**
	 * @param s
	 * @uml.property  name="street2"
	 */
    public void setStreet2(String s);
    /**
	 * @param s
	 * @uml.property  name="city"
	 */
    public void setCity(String s);
    /**
	 * @param s
	 * @uml.property  name="state"
	 */
    public void setState(String s);
    /**
	 * @param s
	 * @uml.property  name="zip"
	 */
    public void setZip(String s);
    
}

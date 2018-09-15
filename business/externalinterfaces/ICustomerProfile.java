
package business.externalinterfaces;


/**
 * @author  nngo2
 */
public interface ICustomerProfile {
    /**
	 * @return
	 * @uml.property  name="firstName"
	 */
    public String getFirstName();
    /**
	 * @return
	 * @uml.property  name="lastName"
	 */
    public String getLastName();
    /**
	 * @return
	 * @uml.property  name="custId"
	 */
    public Integer getCustId();
    /**
	 * @param fn
	 * @uml.property  name="firstName"
	 */
    public void setFirstName(String fn);
    /**
	 * @param ln
	 * @uml.property  name="lastName"
	 */
    public void setLastName(String ln);
    /**
	 * @param id
	 * @uml.property  name="custId"
	 */
    public void setCustId(Integer id);
}

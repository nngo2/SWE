package business.customersubsystem;

import business.externalinterfaces.ICustomerProfile;

class CustomerProfile implements ICustomerProfile{
	private String firstName;
	private String lastName;
	private Integer custId;
	//CustomerProfile(){}
	CustomerProfile(Integer custid,String firstName,String lastName){
		this.custId = custid;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	/**
	 * @return  the firstName
	 * @uml.property  name="firstName"
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName  the firstName to set
	 * @uml.property  name="firstName"
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return  the lastName
	 * @uml.property  name="lastName"
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName  the lastName to set
	 * @uml.property  name="lastName"
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return  the custId
	 * @uml.property  name="custId"
	 */
	public Integer getCustId() {
		return custId;
	}
	/**
	 * @param custId  the custId to set
	 * @uml.property  name="custId"
	 */
	public void setCustId(Integer id) {
		custId = id;
	}
}

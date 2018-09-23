
package performancetests.rulesstubs;


import business.externalinterfaces.IAddress;


/**
 * @author pcorazza
 * @since Nov 4, 2004
 * Class Description:
 * 
 * 
 */
public class Address implements IAddress{
    
    public Address(String street, String city, String state, String zip){
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    public Address(String[] fields) {
    	this.street=fields[0];
    	this.city = fields[1];
    	this.state = fields[2];
    	this.zip = fields[3];
    }
    private String street;   
    private String city;
    private String state;
    private String zip;
 
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

 
    public String getState() {
        return state;
    }
 
    public void setState(String state) {
        this.state = state;
    }
 
  
 
 
 

 

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
 
    public String toString() {
        String n = System.getProperty("line.separator");
        StringBuffer sb = new StringBuffer();
        sb.append("Street: "+street+n);        
        sb.append("City: "+city+n);
        sb.append("State: "+state+n);
        sb.append("Zip: "+zip+n);
        return sb.toString();
    }

	public String getStreet1() {
		// TODO Auto-generated method stub
		return street;
	}

	public String getStreet2() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setStreet1(String s) {
		street = s;
		
	}

	public void setStreet2(String s) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
		System.out.println(System.getProperty("user.dir") + ".." + "\\MainProjectSolnCrVerif\\resources\\dbconfig.properties");
	}
}


package business;


/**
 * @author  nngo2
 */
public class Login {

    private Integer custId;
    private String password;
    
    public Login(Integer custId, String password){
        this.custId= custId;
        this.password = password;
    }
    
    /**
	 * @return  the custId
	 * @uml.property  name="custId"
	 */
    public Integer getCustId(){
        return custId;
    }
    /**
	 * @return  the password
	 * @uml.property  name="password"
	 */
    public String getPassword() {
        return password;
    }
}

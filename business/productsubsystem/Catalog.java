package business.productsubsystem;

import business.externalinterfaces.ICatalog;

/**
 * @author  
 */
public class Catalog implements ICatalog {
	private String catId;
	private String name;
	public Catalog(String id, String name) {
		this.catId = id;
		this.name = name;
	}
	public String getId() {
		return catId;
	}

	/**
	 * @return  the name
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}

	public void setId(String id) {
		catId = id;
		
	}

	/**
	 * @param name  the name to set
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
		
	}
}

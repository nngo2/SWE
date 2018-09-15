package business.externalinterfaces;

/**
 * @author  nngo2
 */
public interface ICatalog {
	/**
	 * @return
	 * @uml.property  name="id"
	 */
	String getId();
	/**
	 * @return
	 * @uml.property  name="name"
	 */
	String getName();
	/**
	 * @param id
	 * @uml.property  name="id"
	 */
	void setId(String id);
	/**
	 * @param name
	 * @uml.property  name="name"
	 */
	void setName(String name);
}

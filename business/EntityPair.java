package business;

/**
 * stores old values and new values for an entity class used by rules
 * @author  pcorazza
 */
public class EntityPair {
	private Object entityNewVals;
	private Object entityOldVals;
	public EntityPair(Object entityNew,Object entityOld){
		entityNewVals = entityNew;
		entityOldVals = entityOld;
	}


	/**
	 * @return  the entityNewVals
	 * @uml.property  name="entityNewVals"
	 */
	public Object getEntityNewVals() {
		return entityNewVals;
	}
	/**
	 * @return  the entityOldVals
	 * @uml.property  name="entityOldVals"
	 */
	public Object getEntityOldVals() {
		return entityOldVals;
	}
}

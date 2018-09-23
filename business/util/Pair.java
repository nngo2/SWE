package business.util;

/**
 * @author  
 */
public class Pair {
	private Integer first;
	private Integer second;
	public Pair(Integer first, Integer second) {
		this.first = first;
		this.second = second;
		
	}
	
	public String toString() {
		return "(" + first.toString() + ", " + second.toString() + ")";
	}

	/**
	 * @return  the first
	 * @uml.property  name="first"
	 */
	public Integer getFirst() {
		return first;
	}

	/**
	 * @param first  the first to set
	 * @uml.property  name="first"
	 */
	public void setFirst(Integer first) {
		this.first = first;
	}

	/**
	 * @return  the second
	 * @uml.property  name="second"
	 */
	public Integer getSecond() {
		return second;
	}

	/**
	 * @param second  the second to set
	 * @uml.property  name="second"
	 */
	public void setSecond(Integer second) {
		this.second = second;
	}
	
}

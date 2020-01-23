/**
 * @author shadi
 *
 */
package json;

public final class JSONElement {
	private String attribute;
	private String value;
	private String[] array;

	protected JSONElement(String attribute, String value) {
		this.attribute = attribute;
		this.value = value;
		this.array = null;
	}

	protected JSONElement(String attribute, String[] array) {
		this.attribute = attribute;
		this.value = null;
		this.array = array;
	}

	/**
	 * @return the attribute
	 */
	public String getAttribute() {
		return attribute;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/***
	 * @return The array of values
	 */
	public String[] getArray() {
		return this.array.clone();
	}

	/***
	 * @return Returns true if this object has an array
	 */
	public boolean hasArray() {
		return array != null;
	}

	/**
	 * @return Returns true if this object has a value
	 */
	public boolean hasValue() {
		return value != null;
	}

}

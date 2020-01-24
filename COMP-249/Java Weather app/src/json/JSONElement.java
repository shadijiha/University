/**
 * @author shadi
 *
 */
package json;

import java.util.ArrayList;
import java.util.List;

public final class JSONElement {
	private String attribute;
	private List<String> value;
	private JSONElement parent;

	public JSONElement(String attribute, String value, JSONElement parent) {
		this.value = new ArrayList<String>();
		this.attribute = attribute;
		this.value.add(value);
	}

	public JSONElement(String attribute, String value) {
		this(attribute, value, null);
	}

	/**
	 * Adds another value to the attribute
	 * 
	 * @param value The value to add
	 */
	public void addValue(String value) {
		this.value.add(value);
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
	public String[] getValue() {
		return (String[]) value.toArray();
	}

	/**
	 * @return Returns true if this object has a value
	 */
	public boolean hasValue() {
		return value.size() > 0;
	}

	/***
	 * @return Return true if this object has a parent
	 */
	public boolean hasParent() {
		return parent != null;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		str.append(attribute + "\t:\t");

		if (value.size() <= 1) {
			str.append(value.get(0));
		} else {
			str.append("[");
			for (var temp : value) {
				str.append(temp + ", ");
			}
			str.append("]");
		}

		return str.toString();
	}
}

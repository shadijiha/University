/**
 * @author shadi
 *
 */
package json;

import java.util.ArrayList;
import java.util.List;

public abstract class JSONElement {
	protected String attribute;
	protected JSONElement parent;

	public JSONElement(String attribute, JSONElement parent) {
		this.attribute = attribute;
		this.parent = parent;
	}

	public JSONElement(final JSONElement element) {
		this(element.attribute, element.parent);
	}

	/**
	 * Returns the value of the JSONElement
	 */
	public abstract Object getValue();

	/**
	 * @return the attribute
	 */
	public String getAttribute() {
		return attribute;
	}

	/**
	 * @return Returns if the element has an attribute
	 */
	public boolean hasAttribute()	{
		return this.attribute != null;
	}

	/**
	 * @return Returns true if this object has a value
	 */
	public abstract boolean hasValue();

	/***
	 * @return Return true if this object has a parent
	 */
	public boolean hasParent() {
		return parent != null;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
//
//		str.append(attribute + "\t:\t");
//
//		if (value.size() <= 1) {
//			str.append(value.get(0));
//		} else {
//			str.append("[");
//			for (var temp : value) {
//				str.append(temp + ", ");
//			}
//			str.append("]");
//		}

		return str.toString();
	}
}

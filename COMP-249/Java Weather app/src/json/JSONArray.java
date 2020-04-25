/**
 *
 */

package json;

import java.util.*;

public class JSONArray extends JSONElement {

	private List<JSONElement> value;

	public JSONArray(String attribute, JSONElement[] value, JSONElement parent)	{
		super(attribute, parent);
		this.value = Arrays.asList(value);
	}

	public JSONArray(String attribute, JSONElement parent)	{
		super(attribute, parent);
		this.value = new ArrayList<JSONElement>();
	}

	/**
	 * Adds a JSON element to the JSON array
	 * @param element The element to add
	 */
	public void add(JSONElement element)	{
		value.add(element);
	}

	/**
	 * Returns the value of the JSONElement
	 */
	@Override
	public List<JSONElement> getValue() {
		return value;
	}

	/**
	 * @return Returns true if this object has a value
	 */
	@Override
	public boolean hasValue() {
		return value.size() <= 0;
	}
}

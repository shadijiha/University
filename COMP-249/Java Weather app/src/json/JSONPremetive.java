/**
 *
 */

package json;

public class JSONPremetive extends JSONElement {

	private String value;

	public JSONPremetive(String attribute, String value, JSONElement parent)	{
		super(attribute, parent);
	}

	/**
	 * Returns the value of the JSONElement
	 */
	@Override
	public String getValue() {
		return this.value;
	}

	/**
	 * @return Returns true if this object has a value
	 */
	@Override
	public boolean hasValue() {
		return this.value != null;
	}
}

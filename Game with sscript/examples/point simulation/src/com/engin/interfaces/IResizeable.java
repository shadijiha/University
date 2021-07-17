/**
 *
 */
package com.engin.interfaces;

import com.engin.math.*;

public interface IResizeable {

	/**
	 * @return Gets the width and the height on the component
	 */
	public ImmutableVec2f getDimensions();

	/**
	 * Changes the width and the height of the component
	 * @param dimension The new width and height
	 */
	public void setDimensions(ICoordinates2F dimension);

}

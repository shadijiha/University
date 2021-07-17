/**
 *
 */
package com.engin.interfaces;

import com.engin.math.*;

public interface IMovable {

	/**
	 * @return Gets the width and the height on the component
	 */
	public ImmutableVec2f getPosition();

	/**
	 * Changes the width and the height of the component
	 * @param position The new width and height
	 */
	public void setPosition(Coordinates2f position);

}

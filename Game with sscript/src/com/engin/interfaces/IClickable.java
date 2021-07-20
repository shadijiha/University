/**
 *
 */

package com.engin.interfaces;

public interface IClickable {

	/**
	 * Sets the function to be called then the shape is clicked
	 * @param clickFunc A function that takes in parameter the click position
	 */
	public void onClick(IClickEvent clickFunc);
}

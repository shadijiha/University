/**
 *
 *
 */
package com;

public interface Shape {

	default public String name()	{
		return this.getClass().getSimpleName();
	}

	public double perimeter();

	public double area();

}

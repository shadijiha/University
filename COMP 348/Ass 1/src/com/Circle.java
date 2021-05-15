/**
 *
 */
package com;

public class Circle implements Shape {

	private double radius;

	public Circle(double radius) {
		this.radius = radius;
	}

	public static Circle parse(String data)	{
		try	{
			String[] tokens = data.split(",");
			return new Circle(Double.parseDouble(tokens[1]));
		} catch (Exception e)	{
			throw new RuntimeException("Invalid parse format (" + data + ")");
		}
	}

	@Override
	public String name()	{
		return Shape.super.name().toUpperCase();
	}

	@Override
	public String toString()	{
		return Shape.super.name() + ", " + radius;
	}

	@Override
	public double perimeter() {
		return 2 * Math.PI * radius;
	}

	@Override
	public double area() {
		return Math.PI * radius * radius;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
}

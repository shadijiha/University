/**
 *
 */
package com;

public class Rectangle implements Shape {

	private double width;
	private double height;

	public Rectangle(double width, double height) {
		this.height = height;
		this.width = width;
	}

	public static Rectangle parse(String data)	{
		try	{
			String[] tokens = data.split(",");
			return new Rectangle(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));
		} catch (Exception e)	{
			throw new RuntimeException("Invalid rectangle format (" + data + ")");
		}
	}

	@Override
	public double perimeter() {
		return height + height + width + width;
	}

	@Override
	public double area() {
		return width * height;
	}

	@Override
	public String toString()	{
		return Shape.super.name() + "," + width + "," + height;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}
}

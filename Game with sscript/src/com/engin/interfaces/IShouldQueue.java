/**
 * Represent a job that can be added to a queue to be executed later
 */
package com.engin.interfaces;

public interface IShouldQueue {

	/**
	 * The function to run when the job is executed
	 */
	public void handle() throws Throwable;

	default public String name() {
		return "(No name set)";
	}
}
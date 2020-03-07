/**
 * 
 */
package com.exceptions;

import java.io.Serializable;

/**
 * @author shadi
 *
 */
public class FileInvalidException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public FileInvalidException(String s) {
		// TODO Auto-generated constructor stub
		super(s);
	}

	public FileInvalidException() {
		super("Error:  Input  file  cannot  be  parsed  due  to  missing  information (i.e. month={}, title={}, etc.)");
	}

}

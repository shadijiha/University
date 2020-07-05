/**
 *
 */
package com.main;

public class Account {

	private String holderName;
	private long id;

	public Account(String holderName) {
		this.holderName = holderName;
		this.id = (long) (Math.random() * Long.MAX_VALUE);
	}

	public String getHolderName() {
		return holderName;
	}

	public long getId() {
		return id;
	}

	public String toString() {
		return String.format("$s#$d", holderName, id);
	}
}

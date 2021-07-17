/**
 *
 */
package com.engin.interfaces;

import com.engin.*;

import java.io.*;
import java.util.*;

public abstract class UUID implements Serializable {

	private static final List<UUID> allUUIDObjects = new ArrayList<>();

	public static final long serialVersionUID = 54745456474142L;
	private final static short HASH_SIZE = 64;

	protected final long id;
	protected final String hash;

	public UUID() {
		id = (long) (Math.random() * Long.MAX_VALUE);
		hash = Util.randomString(HASH_SIZE, true);

		allUUIDObjects.add(this);
	}

	/**
	 * Gets and returns a Object if the ID matches
	 * @param id The id of the object
	 * @return
	 */
	public static UUID getObject(long id) {
		return allUUIDObjects.stream().filter(e -> e.id == id).findFirst().orElse(null);
	}

	/**
	 * Gets and returns a Object if the Hash matches
	 * @param hash The hash of the object
	 * @return
	 */
	public static UUID getObject(String hash) {
		return allUUIDObjects.stream().filter(e -> e.hash.equals(hash)).findFirst().orElse(null);
	}

	/**
	 * @return Returns the ID of scene
	 */
	public final long getId() {
		return id;
	}

	public final String getHash() {
		return hash;
	}
}

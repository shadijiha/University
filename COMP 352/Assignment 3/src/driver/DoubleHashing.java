/**
 *
 */

package driver;

import java.util.*;

public class DoubleHashing<K, V> implements Map<K, V> {

	private Entry<K, V>[] entries;
	private boolean resizeable;
	private int capacity;

	private static final int PRIME = 7;
	private static int TABLE_SIZE;

	public static int CLUSTER;

	private int collisions;

	public DoubleHashing(int capacity, boolean resizeable) {
		this.resizeable = resizeable;
		this.capacity = capacity;
		TABLE_SIZE = capacity;

		entries = new Entry[this.capacity];
	}

	public DoubleHashing() {
		this(10, true);
	}

	/**
	 * Returns the number of key-value mappings in this map.  If the
	 * map contains more than {@code Integer.MAX_VALUE} elements, returns
	 * {@code Integer.MAX_VALUE}.
	 *
	 * @return the number of key-value mappings in this map
	 */
	@Override

	public int size() {

		int count = 0;
		for (var e : entries)
			if (e != null)
				count++;

		return count;
	}

	/**
	 * Returns {@code true} if this map contains no key-value mappings.
	 *
	 * @return {@code true} if this map contains no key-value mappings
	 */
	@Override
	public boolean isEmpty() {
		return false;
	}

	/**
	 * Returns {@code true} if this map contains a mapping for the specified
	 * key.  More formally, returns {@code true} if and only if
	 * this map contains a mapping for a key {@code k} such that
	 * {@code Objects.equals(key, k)}.  (There can be
	 * at most one such mapping.)
	 *
	 * @param key key whose presence in this map is to be tested
	 * @return {@code true} if this map contains a mapping for the specified
	 * key
	 * @throws ClassCastException   if the key is of an inappropriate type for
	 *                              this map
	 *                              (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if the specified key is null and this map
	 *                              does not permit null keys
	 *                              (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
	 */
	@Override
	public boolean containsKey(Object key) {
		return false;
	}

	/**
	 * Returns {@code true} if this map maps one or more keys to the
	 * specified value.  More formally, returns {@code true} if and only if
	 * this map contains at least one mapping to a value {@code v} such that
	 * {@code Objects.equals(value, v)}.  This operation
	 * will probably require time linear in the map size for most
	 * implementations of the {@code Map} interface.
	 *
	 * @param value value whose presence in this map is to be tested
	 * @return {@code true} if this map maps one or more keys to the
	 * specified value
	 * @throws ClassCastException   if the value is of an inappropriate type for
	 *                              this map
	 *                              (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if the specified value is null and this
	 *                              map does not permit null values
	 *                              (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
	 */
	@Override
	public boolean containsValue(Object value) {
		return false;
	}

	/**
	 * Returns the value to which the specified key is mapped,
	 * or {@code null} if this map contains no mapping for the key.
	 *
	 * <p>More formally, if this map contains a mapping from a key
	 * {@code k} to a value {@code v} such that
	 * {@code Objects.equals(key, k)},
	 * then this method returns {@code v}; otherwise
	 * it returns {@code null}.  (There can be at most one such mapping.)
	 *
	 * <p>If this map permits null values, then a return value of
	 * {@code null} does not <i>necessarily</i> indicate that the map
	 * contains no mapping for the key; it's also possible that the map
	 * explicitly maps the key to {@code null}.  The {@link #containsKey
	 * containsKey} operation may be used to distinguish these two cases.
	 *
	 * @param key the key whose associated value is to be returned
	 * @return the value to which the specified key is mapped, or
	 * {@code null} if this map contains no mapping for the key
	 * @throws ClassCastException   if the key is of an inappropriate type for
	 *                              this map
	 *                              (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if the specified key is null and this map
	 *                              does not permit null keys
	 *                              (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
	 */
	@Override
	public V get(Object key) {

		K updated_key;
		try {
			updated_key = (K) key;
		} catch (Exception e) {
			throw new ClassCastException("Invalid key");
		}

		var index = hash1(updated_key);
		var globalIndex = index;

		if (entries[index] != null) {
			int index2 = hash2(updated_key);
			int i = 1;
			while (true) {
				int newIndex = (index + i * index2) % TABLE_SIZE;

				if (entries[newIndex] == null) {
					globalIndex = newIndex;
					break;
				}

				i++;
			}
		}

		return entries[globalIndex].getValue();
	}

	/**
	 * Associates the specified value with the specified key in this map
	 * (optional operation).  If the map previously contained a mapping for
	 * the key, the old value is replaced by the specified value.  (A map
	 * {@code m} is said to contain a mapping for a key {@code k} if and only
	 * if {@link #containsKey(Object) m.containsKey(k)} would return
	 * {@code true}.)
	 *
	 * @param key   key with which the specified value is to be associated
	 * @param value value to be associated with the specified key
	 * @return the previous value associated with {@code key}, or
	 * {@code null} if there was no mapping for {@code key}.
	 * (A {@code null} return can also indicate that the map
	 * previously associated {@code null} with {@code key},
	 * if the implementation supports {@code null} values.)
	 * @throws UnsupportedOperationException if the {@code put} operation
	 *                                       is not supported by this map
	 * @throws ClassCastException            if the class of the specified key or value
	 *                                       prevents it from being stored in this map
	 * @throws NullPointerException          if the specified key or value is null
	 *                                       and this map does not permit null keys or values
	 * @throws IllegalArgumentException      if some property of the specified key
	 *                                       or value prevents it from being stored in this map
	 */
	@Override
	public V put(K key, V value) {

		// Check for place
		if (size() + 1 >= entries.length) {
			if (!resizeable)
				throw new IllegalStateException("Hash Table is full and flagged as none resizable");
			else
				resize();
		}

		// Add the element
		var index = hash1(key);
		var globalIndex = index;

		if (entries[index] != null) {
			int index2 = hash2(key);
			int i = 1;
			while (true) {
				int newIndex = (index + i * index2) % TABLE_SIZE;

				if (entries[newIndex] == null) {
					globalIndex = newIndex;
					break;
				}

				i++;
				collisions++;
			}

			if (i > CLUSTER)
				CLUSTER = i;
		}

		entries[globalIndex] = new Pair(key, value);

		return value;
	}

	/**
	 * Removes the mapping for a key from this map if it is present
	 * (optional operation).   More formally, if this map contains a mapping
	 * from key {@code k} to value {@code v} such that
	 * {@code Objects.equals(key, k)}, that mapping
	 * is removed.  (The map can contain at most one such mapping.)
	 *
	 * <p>Returns the value to which this map previously associated the key,
	 * or {@code null} if the map contained no mapping for the key.
	 *
	 * <p>If this map permits null values, then a return value of
	 * {@code null} does not <i>necessarily</i> indicate that the map
	 * contained no mapping for the key; it's also possible that the map
	 * explicitly mapped the key to {@code null}.
	 *
	 * <p>The map will not contain a mapping for the specified key once the
	 * call returns.
	 *
	 * @param key key whose mapping is to be removed from the map
	 * @return the previous value associated with {@code key}, or
	 * {@code null} if there was no mapping for {@code key}.
	 * @throws UnsupportedOperationException if the {@code remove} operation
	 *                                       is not supported by this map
	 * @throws ClassCastException            if the key is of an inappropriate type for
	 *                                       this map
	 *                                       (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException          if the specified key is null and this
	 *                                       map does not permit null keys
	 *                                       (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
	 */
	@Override
	public V remove(Object key) {

		K updated_key;
		try {
			updated_key = (K) key;
		} catch (Exception e) {
			throw new ClassCastException("Invalid key");
		}

		// Add the element
		var index = hash1(updated_key);
		var globalIndex = index;

		if (entries[index] != null) {
			int index2 = hash2(updated_key);
			int i = 1;
			while (true) {
				int newIndex = (index + i * index2) % TABLE_SIZE;

				if (entries[newIndex] == null) {
					globalIndex = newIndex;
					break;
				}

				i++;
			}
		}

		V temp = entries[globalIndex].getValue();
		entries[globalIndex] = null;

		return temp;
	}

	/**
	 * Copies all of the mappings from the specified map to this map
	 * (optional operation).  The effect of this call is equivalent to that
	 * of calling {@link #put(Object, Object) put(k, v)} on this map once
	 * for each mapping from key {@code k} to value {@code v} in the
	 * specified map.  The behavior of this operation is undefined if the
	 * specified map is modified while the operation is in progress.
	 *
	 * @param m mappings to be stored in this map
	 * @throws UnsupportedOperationException if the {@code putAll} operation
	 *                                       is not supported by this map
	 * @throws ClassCastException            if the class of a key or value in the
	 *                                       specified map prevents it from being stored in this map
	 * @throws NullPointerException          if the specified map is null, or if
	 *                                       this map does not permit null keys or values, and the
	 *                                       specified map contains null keys or values
	 * @throws IllegalArgumentException      if some property of a key or value in
	 *                                       the specified map prevents it from being stored in this map
	 */
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for (var e : m.entrySet()) {
			put(e.getKey(), e.getValue());
		}
	}

	/**
	 * Removes all of the mappings from this map (optional operation).
	 * The map will be empty after this call returns.
	 *
	 * @throws UnsupportedOperationException if the {@code clear} operation
	 *                                       is not supported by this map
	 */
	@Override
	public void clear() {

		for (int i = 0; i < entries.length; i++) {
			entries[i] = null;
		}

	}

	/**
	 * Returns a {@link Set} view of the keys contained in this map.
	 * The set is backed by the map, so changes to the map are
	 * reflected in the set, and vice-versa.  If the map is modified
	 * while an iteration over the set is in progress (except through
	 * the iterator's own {@code remove} operation), the results of
	 * the iteration are undefined.  The set supports element removal,
	 * which removes the corresponding mapping from the map, via the
	 * {@code Iterator.remove}, {@code Set.remove},
	 * {@code removeAll}, {@code retainAll}, and {@code clear}
	 * operations.  It does not support the {@code add} or {@code addAll}
	 * operations.
	 *
	 * @return a set view of the keys contained in this map
	 */
	@Override
	public Set<K> keySet() {
		Set<K> result = new HashSet<>();

		for (var entry : entries) {
			if (entry == null)
				continue;
			result.add(entry.getKey());
		}

		return result;
	}

	/**
	 * Returns a {@link Collection} view of the values contained in this map.
	 * The collection is backed by the map, so changes to the map are
	 * reflected in the collection, and vice-versa.  If the map is
	 * modified while an iteration over the collection is in progress
	 * (except through the iterator's own {@code remove} operation),
	 * the results of the iteration are undefined.  The collection
	 * supports element removal, which removes the corresponding
	 * mapping from the map, via the {@code Iterator.remove},
	 * {@code Collection.remove}, {@code removeAll},
	 * {@code retainAll} and {@code clear} operations.  It does not
	 * support the {@code add} or {@code addAll} operations.
	 *
	 * @return a collection view of the values contained in this map
	 */
	@Override
	public Collection<V> values() {

		Collection<V> result = new ArrayList<>();

		for (var entry : entries) {
			if (entry == null)
				continue;
			result.add(entry.getValue());
		}
		return result;
	}

	/**
	 * Returns a {@link Set} view of the mappings contained in this map.
	 * The set is backed by the map, so changes to the map are
	 * reflected in the set, and vice-versa.  If the map is modified
	 * while an iteration over the set is in progress (except through
	 * the iterator's own {@code remove} operation, or through the
	 * {@code setValue} operation on a map entry returned by the
	 * iterator) the results of the iteration are undefined.  The set
	 * supports element removal, which removes the corresponding
	 * mapping from the map, via the {@code Iterator.remove},
	 * {@code Set.remove}, {@code removeAll}, {@code retainAll} and
	 * {@code clear} operations.  It does not support the
	 * {@code add} or {@code addAll} operations.
	 *
	 * @return a set view of the mappings contained in this map
	 */
	@Override
	public Set<Entry<K, V>> entrySet() {

		Set<Entry<K, V>> result = new HashSet<>();

		for (var entry : entries) {

			if (entry == null)
				continue;
			result.add(entry);
		}

		return result;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (var entry : entries) {
			if (entry != null)
				//builder.append(entry.toString()).append(", ");
				builder.append(entry.getKey().toString()).append(", ");
		}

		return builder.toString();
	}

	public int getCollisions() {
		return collisions;
	}

	// function to calculate first hash
	private int hash1(K key) {
		return (key.hashCode() % TABLE_SIZE);
	}

	// function to calculate second hash
	private int hash2(K key) {
		return (PRIME - (key.hashCode() % PRIME));
	}

	private void resize() {

		Entry<K, V>[] copy = new Entry[entries.length * 2];
		System.arraycopy(entries, 0, copy, 0, entries.length);

		entries = copy;
	}

	private class Pair implements Entry<K, V> {
		private K key;
		private V value;

		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return this.key;
		}

		@Override
		public V getValue() {
			return this.value;
		}

		@Override
		public V setValue(V value) {
			V old_value = this.value;
			this.value = value;
			return old_value;
		}

		public String toString() {
			return key.toString() + "=" + value.toString();
		}
	}
}

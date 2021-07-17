/**
 *
 */
package simulation;

public class Record<T, E> {

	private final T key;
	private final E value;

	public Record(T key, E value) {
		this.key = key;
		this.value = value;
	}

	public static <T, E> Record<T, E> from(T key, E value) {
		return new Record<T, E>(key, value);
	}

	public final T getKey() {
		return key;
	}

	public final E getValue() {
		return value;
	}
}

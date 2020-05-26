package calculator;

public class Variant<T, E> {

	private T type_1;
	private E type_2;

	public Variant(T element1, E element2) {
		set(element1, element2);
	}

	public void set(T element1, E element2) {
		if (element1 != null && element2 != null)
			throw new IllegalArgumentException("At least 1 of the variant arguments must be null");

		if (element1 == null && element2 == null)
			throw new IllegalArgumentException("At least 1 of the variant arguments must be a NONE null object");


		this.type_1 = element1;
		this.type_2 = element2;
	}

	public Class type() {
		return type_1 == null ? type_2.getClass() : type_1.getClass();
	}

	public Object get() {
		return type_1 == null ? type_2 : type_1;
	}
}

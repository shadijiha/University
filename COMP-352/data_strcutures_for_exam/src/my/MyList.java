/**
 *
 */
package my;

public interface MyList<T> extends Iterable<T> {

	public void add(T element);

	public void addAt(int index, T element);

	public T get(int index);

	public void set(int index, T val);

	public T removeAt(int index);

	public void remove(T object);

	public int indexOf(Object object);

	public int size();

	public boolean isEmpty();

	public String toString();
}

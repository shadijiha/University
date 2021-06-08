/**
 *
 */
package my;

public interface MyList<T> {

	public void add(T element);

	public void addAt(int index, T element);

	public T get(int index);

	public T removeAt(int index);

	public void remove(T object);

	public int indexOf(Object object);

	public int size();

	public String toString();
}

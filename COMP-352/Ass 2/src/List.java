/**
 *
 *
 */
public interface List<E> {


	public Boolean add(E e);

	public void add(int index, E element);

	public void clear();

	public E remove(int index);

	public Boolean remove(Object o);

	public String toString();

	public int size();
}

/**
 *
 */
package my;

public interface Queue<T> {

	public void enqueue(T obj);

	public T dequeue();

	public T front();

	public void addFirst(T e);

	public void addLast(T e);

	public T removeFirst();

	public T removeLast();

	public T getFirst();

	public T getLast();

	public int size();

	public boolean isEmpty();

}

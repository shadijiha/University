/**
 *
 */
package my;

import java.util.function.Consumer;

public class BinaryTreeArray<T extends Comparable<T>> implements Tree<T> {

	protected transient T[] data;
	protected int root;

	public BinaryTreeArray() {
		data = (T[]) new Object[10];
	}

	@Override
	public void add(T element) {

	}

	@Override
	public T remove(T element) {
		return null;
	}

	@Override
	public TreeNode<T> root() {
		return new TreeNode<>(data[root]);
	}

	private void setLeft(T element, int root) {
		int t = (root * 2) + 1;

		if (data[root] == null) {
			throw new RuntimeException("Cannot add left to null");
		} else {
			data[t] = element;
		}
	}

	private void setRight(T element, int root) {
		int t = (root * 2) + 2;
		if (data[root] == null) {
			throw new RuntimeException("Cannot add right to null");
		} else {
			data[t] = element;
		}
	}

	@Override
	public TreeNode<T> parent(TreeNode<T> node) {
		return null;
	}

	@Override
	public MyList<TreeNode<T>> children(TreeNode<T> node) {
		return null;
	}

	@Override
	public void preOrder(Consumer<T> func) {

	}

	@Override
	public void postOrder(Consumer<T> func) {

	}

	@Override
	public void inOrder(Consumer<T> func) {

	}

	@Override
	public Iterable<T> positions() {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	public T[] rawArray() {
		Object arra[] = new Object[data.length];
		System.arraycopy(data, 0, arra, 0, data.length);
		return (T[]) arra;
	}

	private void resize() {
		T temp[] = (T[]) new Object[data.length * 2];
		System.arraycopy(data, 0, temp, 0, data.length);

		data = temp;
	}
}

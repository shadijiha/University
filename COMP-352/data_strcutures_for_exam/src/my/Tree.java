/**
 *
 */
package my;

import java.util.*;
import java.util.function.*;

public interface Tree<T extends Comparable<T>> {

	public void add(T element);

	public T remove(T element);

	public TreeNode<T> root();

	public TreeNode<T> parent(TreeNode<T> node);

	public TreeNode<T>[] children(TreeNode<T> node);

	public void preOrder(Consumer<T> func);

	public void postOrder(Consumer<T> func);

	public void inOrder(Consumer<T> func);

	default public Iterator<T> iterator() {
		return null;
	}

	public Iterable<T> positions();

	public int size();

	public boolean isEmpty();

	public class TreeNode<T> {
		public T data;
		public TreeNode<T> left;
		public TreeNode<T> right;

		public TreeNode(T data) {
			this.data = data;
		}
	}
}

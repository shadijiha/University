package structures;

public abstract class AbstractMap {

	protected Node[] data;
	protected int collisions;
	protected boolean resizable;

	protected AbstractMap(int intial_capacity) {
		data = new Node[intial_capacity];
		resizable = false;
	}

	public int getCollisions() {
		return collisions;
	}

	public abstract void put(int key, int value);

	public abstract Integer get(int key);

	public abstract Integer remove(int key);

	public abstract boolean contains(int key);

	public abstract int size();

	protected abstract int hash(int key);

	protected abstract int double_hash(int key);

	protected void resize() {
		throw new RuntimeException("Don't use this shit");
	}

	public abstract String toString();

	protected class Node {
		private int key;
		private int value;

		public Node(int key, int value) {
			this.key = key;
			this.value = value;
		}

		public String toString() {
			return key + "=" + value;
		}
	}
}

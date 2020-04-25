package Lab11;

/**
 *
 */

public class LinkedList {

	private Node head;
	private int size = 0;

	public static class Node {
		private Player player;
		private Node next;

		public Node(Player player, Node next) {
			this.player = player;
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node\t{" +
					"player = \t" + player.toString() +
					",\tnext = " + next +
					'}';
		}

		public Player getPlayer() {
			return player;
		}

		public void setPlayer(Player player) {
			this.player = player;
		}

		public boolean hasNext() {
			return next != null;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
	}

	public void insert(Player p) {

		Node current;
		Node new_node = new Node(new Player(p), null);

		/* Special case for head node */
		if (head == null || head.getPlayer().getHealth() < new_node.getPlayer().getHealth()) {
			new_node.next = head;
			head = new_node;
		} else {

			/* Locate the node before point of insertion. */
			current = head;

			while (current.next != null &&
					current.next.getPlayer().getHealth() >= new_node.getPlayer().getHealth())
				current = current.next;

			new_node.next = current.next;
			current.next = new_node;
		}

		size++;
	}

	public void remove(Player p) {

		// Find which node has the play
		Node current = head;
		Node previous = null;
		while (current != null) {
			if (current.getPlayer().equals(p)) {

				// it is first element in the list
				if (previous == null) {
					head = current.getNext();
				} else {
					previous.setNext(current.getNext());
				}

			}
			previous = current;
			current = current.getNext();
		}

		size--;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Lab11.LinkedList has ").append(size).append(" nodes:\n");

		Node temp = head;
		while (temp != null) {
			builder.append("\t").append(temp.getPlayer().toString()).append("\n");
			temp = temp.getNext();
		}
		return builder.toString();
	}

	public int size() {
		return size;
	}
}

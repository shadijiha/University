//  -----------------------------------------------------
// Assignment #4
// Question: 2
// Written by: Shadi Jiha #40131284
//  -----------------------------------------------------

package part2;

import java.util.*;

public class CellList {

	private CellNode head;
	private uint32_t size;

	public static final uint32_t LAST_ITERATION_COUNT = new uint32_t(0);

	public CellList() {
		head = null;
		size = new uint32_t(0);
	}

	public CellList(final CellList other) {
		head = other.head;
		size = new uint32_t(other.size);
	}

	/**
	 * Adds an item to the start of the list
	 *
	 * @param item The item to add
	 */
	public void addToStart(CellPhone item) {
		head = new CellNode(item, head);
		size.increment(1);
	}

	/**
	 * Deletes the first node
	 */
	public void deleteFromStart() {
		if (head != null) {
			head = head.next();
			size.increment(-1);
		}
	}

	/**
	 * Inserts an item in a specific index
	 *
	 * @param phone The phone to insert
	 * @param index The index where to insert
	 */
	public void insertAtIndex(CellPhone phone, int index) {
		if (index < 0 || index > size() - 1) {
			throw new NoSuchElementException();
		} else {

			// Here I am using uint32_t because I don't want the index to be negative
			uint32_t i = new uint32_t(index);
			CellNode previous = get(new uint32_t(i.value() - 1).value());
			CellNode suivant = get(new uint32_t(i.value() + 1).value());
			CellNode current = new CellNode(phone, suivant);

			previous.setNext(current);
		}
	}

	/**
	 * Deletes an item from a specific index
	 *
	 * @param index The index where to delete
	 */
	public void deleteFromIndex(int index) {
		if (index < 0 || index > size() - 1) {
			throw new NoSuchElementException();
		} else {
			// Here I am using uint32_t because I don't want the index to be negative
			uint32_t i = new uint32_t(index);

			// Link the previous node's next to the current one next's
			CellNode previous = get(i.value());
			CellNode current = get(i.value());

			previous.setNext(current.next());
		}
	}

	public void replaceAtIndex(CellPhone new_phone, int index) {
		if (index < 0 || index > size() - 1) {
			return;
		} else {
			CellNode current = get(index);
			current.setCellPhone(new CellPhone(new_phone));
		}
	}

	/**
	 * Checks if the list contains a cellphone with the specified serial number
	 *
	 * @param phone_SN The phone serial number
	 * @return Return true if the phone is contained, false otherwise
	 */
	public boolean contains(long phone_SN) {
		return find(phone_SN) != null;
	}

	/**
	 * Finds the first occurence of the phone that has the target serial number
	 *
	 * @param target The target phone serial number
	 * @return Return the Cellphone that matches or NULL if there's no match
	 */
	public CellNode find(long target) {
		CellNode position = head;
		CellPhone itemAtPosition;

		LAST_ITERATION_COUNT.set(0);

		while (position != null) {
			itemAtPosition = position.getCellPhone();
			if (itemAtPosition.getSerialNum() == target)
				return position;
			position = position.next();

			// Keep track of how many iterations where necessary to find
			LAST_ITERATION_COUNT.increment(1);
		}
		return null;
	}

	public CellNode get(int index) throws NoSuchElementException {
		if (index < 0 || index > size() - 1) {
			throw new NoSuchElementException();
		} else {
			CellNode position = head;
			uint32_t iterations = new uint32_t(0);

			while (position != null) {
				if (iterations.value() == index)
					return position;
				position = position.next();

				// Keep track of how many iterations where necessary to find
				iterations.increment(1);
			}
			return null;
		}
	}

	public void showContents() {
		CellNode position = head;
		uint32_t count = new uint32_t(1);

		while (position != null) {
			System.out.print(position.getCellPhone() + "\t---->\t");

			if (count.divides(3)) {
				System.out.println();
			}
			position = position.next();
			count.increment();
		}
	}

	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		else {
			CellList otherList = (CellList) o;

			if (size() != otherList.size())
				return false;

			CellNode position = head;
			CellNode otherPos = otherList.head;

			while (position != null) {
				if (!position.getCellPhone().equals(otherPos.getCellPhone()))
					return false;
				position = position.next();
				otherPos = otherPos.next();
			}
			return true;
		}
	}

	public int size() {
		uint32_t count = new uint32_t(0);

		CellNode node = head;

		while (node != null) {
			count.increment(1);
			node = node.next();
		}

		return count.value();
	}

	public static class CellNode {
		private CellPhone cellPhone;
		private CellNode next;

		public CellNode(CellPhone cellPhone, CellNode next) {
			this.cellPhone = cellPhone;
			this.next = next;
		}

		public CellNode(CellPhone cellPhone) {
			this.cellPhone = cellPhone;
			this.next = null;
		}

		public CellNode(CellNode other) {
			this.cellPhone = other.cellPhone;
			this.next = other.next;
		}

		public CellNode() {
			this.cellPhone = null;
			this.next = null;
		}

		@Override
		public Object clone() throws CloneNotSupportedException {
			try {
				CellNode node = (CellNode) super.clone();
				node.cellPhone = new CellPhone(cellPhone, cellPhone.getSerialNum());
				node.next = next;
				return node;
			} catch (CloneNotSupportedException e) {
				return null;
			}

		}

		public boolean hasNext() {
			return next != null;
		}

		public CellPhone getCellPhone() {
			return cellPhone;
		}

		public void setCellPhone(CellPhone cellPhone) {
			this.cellPhone = cellPhone;
		}

		public CellNode next() {
			return next;
		}

		public void setNext(CellNode next) {
			this.next = next;
		}
	}
}

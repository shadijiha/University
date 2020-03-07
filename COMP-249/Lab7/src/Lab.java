/**
 * 
 * @author shadi
 *
 */

public class Lab {

	private String section;
	private int capacity;
	private boolean full;

	public Lab(String section, int capacity, boolean full) {
		super();
		this.section = section;
		this.capacity = capacity;
		this.full = full;
	}

	@Override
	public String toString() {
		return String.format("Section: %s, Capacity: %d, full? %b", section, capacity, full);
	}

	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the full
	 */
	public boolean isFull() {
		return full;
	}

	/**
	 * @param full the full to set
	 */
	public void setFull(boolean full) {
		this.full = full;
	}

}

/**
 *
 */

package Lecture2;

public class Dictionnary extends Book {

	private int definitions;

	public void setDefinitions(int i) {
		this.definitions = i;
	}

	// Getters
	public int getDefinitions() {
		return this.definitions;
	}

	// Setters
	public double computeRatio() {
		return (double) this.definitions / (double) this.getPages();
	}
}

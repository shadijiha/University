/**
 *
 */

package driver;

public class RecusiveMethodFinished extends Exception {

	private String msg;

	public RecusiveMethodFinished(String str) {
		super(str);
		msg = str;
	}

	public RecusiveMethodFinished() {
		super("The recursive function has finished execution.");
		msg = "The recursive function has finished execution.";
	}

	public String getMessage() {
		return msg;
	}
}

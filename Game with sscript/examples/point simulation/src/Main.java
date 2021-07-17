import com.engin.*;
import editor.*;

public class Main {


	public static void main(String[] args) {
		// write your code here

		var editor = new Renderer(1280, 720);
		editor.start();

		editor.submit(new Editor());
		//editor.submit(new SimulationRenderer());
	}
}

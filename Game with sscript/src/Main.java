import com.engin.*;

public class Main {

	public static void main(String[] args) {
		// write your code here

		var renderer = new Renderer(1280, 720);
		renderer.start();

		MainScene scene = new MainScene();
		renderer.submit(scene);
		renderer.submit(new SettingsScene(scene));
	}
}

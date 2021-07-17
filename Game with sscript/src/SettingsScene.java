import com.engin.*;
import com.engin.io.*;
import com.engin.shapes.Rectangle;

import java.awt.*;
import java.io.*;


/**
 *
 */

public class SettingsScene extends Scene {

	// Play script button
	Rectangle playBtn = new Rectangle(400, 0, 60, 60);
	MainScene mainScene;

	public SettingsScene(MainScene main) {
		mainScene = main;
	}

	private void playScript() throws IOException {
		String scriptFile = "C:\\Users\\shadi\\Desktop\\Game with sscript\\assets\\script\\main.sscript";
		String exeFile = "D:\\Code\\Projects\\Compiler\\Cs Compile test\\bin\\Release\\netcoreapp3.1\\CsCompiletest.exe";
		String command = String.format("\"%s\" --filepath \"%s\"", exeFile, scriptFile);
		File dir = new File("D:\\Code\\Projects\\Compiler\\Cs Compile test\\bin\\Release\\netcoreapp3.1");

		ProcessBuilder pb =
				new ProcessBuilder(command);
		pb.directory(dir);
		pb.redirectErrorStream(true);
		Process p = pb.start();

		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while (true) {
			line = r.readLine();
			if (line == null) {
				break;
			}
			processLine(line);
		}
	}

	private void processLine(String line) {
		var player = mainScene.getPlayer().position;
		line = line.trim();
		switch (line) {
			case "right":
				player.x += Tile.DIMENSION;
				break;
			case "left":
				player.x -= Tile.DIMENSION;
				break;
			case "top":
				player.y -= Tile.DIMENSION;
				break;
			case "bottom":
				player.y += Tile.DIMENSION;
				break;
			default:
				System.out.println(line);
		}
	}

	@Override
	public void init(Renderer renderer) {
		playBtn.setTexture("assets/gameicons/PNG/Black/2x/right.png");
	}

	@Override
	public void update(float dt) {
		if (isClicked(playBtn)) {
			try {
				playScript();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		playBtn.draw(g);

		Font f = g.getFont();
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString("Play script", 450, 30);
		g.setFont(f);
	}

	private boolean isClicked(Rectangle r) {
		var position = r.getPosition();
		var dimension = r.getDimensions();
		return (position.x < Input.getMouseX() + 1 &&
				position.x + dimension.x > Input.getMouseX() &&
				position.y < Input.getMouseY() + 1 &&
				position.y + dimension.y > Input.getMouseY()) && Input.mouseIsPressed();
	}
}

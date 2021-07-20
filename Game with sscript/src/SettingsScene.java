import com.engin.Renderer;
import com.engin.Scene;
import com.engin.shapes.Rectangle;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


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
		String scriptFile = Env.get("sscript_file");
		String exeFile = Env.get("sscript_compiler") + "\\" + Env.get("sscript_exe");
		String command = String.format("\"%s\" --filepath \"%s\"", exeFile, scriptFile);
		File dir = new File(Env.get("sscript_compiler"));


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

		System.out.println(line);

		if (line.startsWith("x:")) {
			var temp = line.replace("x:", "");
			player.x = Integer.parseInt(temp) * Tile.DIMENSION;
			return;
		} else if (line.startsWith("y:")) {
			var temp = line.replace("y:", "");
			player.y = Integer.parseInt(temp) * Tile.DIMENSION;
			return;
		}
	}

	@Override
	public void init(Renderer renderer) {
		playBtn.setTexture("assets/gameicons/PNG/Black/2x/right.png");
		playBtn.onClick((pos, dt) -> {
			System.out.println("Clicked!");
//			try {
//				playScript();
//			} catch (IOException e) {
//				Log.error(e);
//			}
		});
	}

	@Override
	public void update(float dt) {
		playBtn.update(dt);
	}

	@Override
	public void draw(Graphics g) {
		playBtn.draw(g);

		Font f = g.getFont();
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString("Play script", 450, 30);
		g.setFont(f);
	}
}

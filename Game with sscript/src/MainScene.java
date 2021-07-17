import com.engin.Renderer;
import com.engin.Scene;
import com.engin.Util;
import com.engin.logger.Log;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainScene extends Scene {

	private Tile player;
	private int mapWidth;
	private int mapHeight = 1;
	private final List<Tile> tiles = new ArrayList<>();

	@Override
	public void init(Renderer renderer) {

		// Load the map
		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileInputStream("assets/map.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int x = 0, y = 0;
		while (scanner.hasNextLine()) {
			String[] line = scanner.nextLine().trim().split(" ");
			mapWidth = Math.max(mapWidth, line.length);

			for (var s : line) {
				tiles.add(new Tile(x, y, s));
				x += Tile.DIMENSION;
			}

			x = 0;
			y += Tile.DIMENSION;
			mapHeight++;
		}

		// Init player
		int xP = Util.random(0, mapWidth - 2);
		int yP = Util.random(0, mapHeight - 2);
		player = new Tile(xP * Tile.DIMENSION, yP * Tile.DIMENSION, "P");

		// Write this info in data file so ssscript can get it
		try {
			PrintWriter writer = new PrintWriter(new FileOutputStream("assets/script/data"));
			writer.println(xP + " " + yP);
			writer.close();
		} catch (FileNotFoundException e) {
			Log.error(e);
		}
	}

	@Override
	public void update(float dt) {

		// Keep player within frame
		if (player.position.x < 0)
			player.position.x = 0;
		if (player.position.y < 0)
			player.position.y = 0;
		if (player.position.x > mapWidth * Tile.DIMENSION)
			player.position.x = (mapWidth - 2) * Tile.DIMENSION;
		if (player.position.y > mapHeight * Tile.DIMENSION)
			player.position.y = (mapHeight - 2) * Tile.DIMENSION;
	}

	@Override
	public void draw(Graphics g) {
		for (Tile tile : tiles)
			tile.draw(g);

		player.draw(g);
	}

	public Tile getPlayer() {
		return player;
	}
}



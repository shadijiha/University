import com.engin.*;

import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.*;

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
		player = new Tile(Util.random(0, mapWidth - 2) * Tile.DIMENSION, Util.random(0, mapHeight - 2) * Tile.DIMENSION, "P");
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



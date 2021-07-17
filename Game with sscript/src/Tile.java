import com.engin.math.*;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

/**
 *
 */

public class Tile {

	public static final int DIMENSION = 50;

	public final Vector2f position;
	public final Vector2f dimension;
	private BufferedImage texture;
	public final String code;

	public Tile(int x, int y, String code) {
		this.position = new Vector2f(x, y);
		this.dimension = new Vector2f(DIMENSION, DIMENSION);
		this.code = code;

		try {
			texture = ImageIO.read(new File(codeToPath(code)));
			texture = resize(texture, (int) dimension.x, (int) dimension.y);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics g) {
		if (code.startsWith("W"))
			new Tile((int) position.x, (int) position.y, "G").draw(g);
		g.drawImage(texture, (int) position.x, (int) position.y, null);
	}

	private String codeToPath(String code) {
		String base = "assets/kenney_rpgurbanpack/Tiles/";
		switch (code) {
			case "G":
				return base + "tile_0028.png";
			case "GTL":
				return base + "tile_0000.png";
			case "GBL":
				return base + "tile_0054.png";
			case "GL":
				return base + "tile_0027.png";
			case "GR":
				return base + "tile_0029.png";
			case "GTR":
				return base + "tile_0002.png";
			case "GBR":
				return base + "tile_0056.png";
			case "GT":
				return base + "tile_0001.png";
			case "GB":
				return base + "tile_0055.png";

			case "W":
				return base + "tile_0198.png";
			case "WTR":
				return base + "tile_0172.png";
			case "WBR":
				return base + "tile_0226.png";
			case "WR":
				return base + "tile_0199.png";
			case "WL":
				return base + "tile_0197.png";
			case "WTL":
				return base + "tile_0170.png";
			case "WBL":
				return base + "tile_0224.png";
			case "WT":
				return base + "tile_0171.png";
			case "WB":
				return base + "tile_0225.png";
			case "P":
				return base + "tile_0024.png";
		}

		throw new RuntimeException("Error: Code " + code + " is not a valid map code");
	}

	private static BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}
}

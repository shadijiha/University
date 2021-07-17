package com.engin.shapes;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class Texture {

	private final String path;
	private final BufferedImage image;
	private final int originalWidth;
	private final int originalHeight;

	public Texture(String path, int desiredwidth, int desiredHeight) {
		this.path = path;

		int originalHeight1;
		int originalWidth1;
		BufferedImage image1;
		try {
			image1 = ImageIO.read(new File(path));
			originalWidth1 = image1.getWidth(null);
			originalHeight1 = image1.getHeight(null);

			// Resize the image
			image1 = resize(image1, desiredwidth, desiredHeight);
		} catch (IOException e) {
			e.printStackTrace();
			image1 = null;
			originalWidth1 = 0;
			originalHeight1 = 0;
		}
		originalHeight = originalHeight1;
		originalWidth = originalWidth1;
		image = image1;
	}

	public Texture(String path) {
		this.path = path;

		int originalHeight1;
		int originalWidth1;
		BufferedImage image1;
		try {
			image1 = ImageIO.read(new File(path));
			originalWidth1 = image1.getWidth(null);
			originalHeight1 = image1.getHeight(null);
		} catch (IOException e) {
			e.printStackTrace();
			image1 = null;
			originalWidth1 = 0;
			originalHeight1 = 0;
		}
		originalHeight = originalHeight1;
		originalWidth = originalWidth1;
		image = image1;
	}

	Texture(BufferedImage image) {
		this.path = null;
		this.image = image;
		originalWidth = image.getWidth();
		originalHeight = image.getHeight();
	}

	static BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}

	public BufferedImage getImage() {
		return this.image;
	}

	public int getWidth() {
		return image.getWidth(null);
	}

	public int getHeight() {
		return image.getHeight(null);
	}

	public String getPath() {
		return path;
	}
}
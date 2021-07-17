/**
 *
 */
package simulation;

import com.engin.*;
import com.engin.io.*;
import com.engin.logger.*;
import com.engin.math.*;
import com.engin.shapes.Rectangle;
import com.engin.shapes.Shape;
import com.engin.shapes.*;

import java.awt.*;
import java.util.List;
import java.util.*;

public abstract class Simulation extends Scene {

	public enum Collision {
		GRASS, SAND, MOUNTAIN, WATER
	}

	protected final List<Shape> waterHitbox;
	protected final List<Shape> mountainHitbox;
	protected final List<Shape> sandHitbox;
	protected Renderer renderer;

	protected ICoordinates2F closestToWater = new Vector2f(0, 0);
	protected Collision currentCollision = Collision.GRASS;

	protected long collisionCalcNS = -1;
	protected long closestWaterPointNS = -1;

	protected Simulation(Renderer renderer) {
		this.renderer = renderer;
		waterHitbox = mapFileLoader("assets/water.map");
		mountainHitbox = mapFileLoader("assets/mount.map");
		sandHitbox = mapFileLoader("assets/sand.map");
	}

	protected abstract Simulation.Collision getCollisionOfPoint(int x, int y);

	protected abstract ImmutableVec2f closestWaterPointTo(int x, int y);

	public Collision getCurrentCollision() {
		return currentCollision;
	}

	public ICoordinates2F getClosestPointToWater() {
		return closestToWater;
	}

	public long getCollisionTimer() {
		return collisionCalcNS;
	}

	public long getClosestWaterPointTimer() {
		return closestWaterPointNS;
	}

	@Override
	public void init(Renderer renderer) {
		this.renderer = renderer;
	}

	@Override
	public void update(float dt) {
		var t1 = System.nanoTime();
		currentCollision = getCollisionOfPoint(Input.getMouseX(), Input.getMouseY());
		collisionCalcNS = System.nanoTime() - t1;


		t1 = System.nanoTime();
		closestToWater = closestWaterPointTo(Input.getMouseX(), Input.getMouseY());
		closestWaterPointNS = System.nanoTime() - t1;
	}

	@Override
	public void draw(Graphics g) {

	}

	private List<Shape> mapFileLoader(String path) {
		List<Shape> result = new ArrayList<>();
		String file = FileUtil.fileToString(path);

		if (file == null) {
			Log.error("Cannot load file content of %s", path);
		}

		String[] lines = file.split("\n");

		try {
			for (String line : lines) {
				if (line.isEmpty())
					continue;

				String tokens[] = line.split(" ");

				switch (tokens[0]) {
					case "RECT":
						result.add(new Rectangle(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4])));
						break;
					case "CIRC":
						result.add(new Circle(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4])));
						break;
					default:
						Log.error("Unknown shape --> %s", tokens[0]);
						break;
				}
			}
		} catch (Exception e) {
			Log.error(e);
		}

		return result;
	}
}

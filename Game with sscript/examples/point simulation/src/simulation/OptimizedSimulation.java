package simulation;

import com.engin.*;
import com.engin.math.*;
import com.engin.shapes.Rectangle;
import com.engin.shapes.Shape;

import java.awt.*;
import java.util.List;
import java.util.*;

public class OptimizedSimulation extends Simulation {

	List<Integer> visitedPoints = Collections.synchronizedList(new ArrayList<>());

	protected OptimizedSimulation(Renderer renderer) {
		super(renderer);
	}

	private boolean collidesWithWater(int x, int y) {
		Shape temp = new Rectangle(x, y, 1, 1);
		for (var hitbox : waterHitbox)
			if (Physics.collisionBetween(hitbox, temp)) {
				return true;
			}
		return false;
	}

	protected Simulation.Collision getCollisionOfPoint(int x, int y) {

		com.engin.shapes.Shape temp = new Rectangle(x, y, 1, 1);

		// See if there's a collision with water
		boolean collisionRegistered = false;
		Simulation.Collision currentCollision = Simulation.Collision.GRASS;

		for (var hitbox : waterHitbox)
			if (Physics.collisionBetween(hitbox, temp)) {
				currentCollision = Simulation.Collision.WATER;
				collisionRegistered = true;
			}


		// See if there's a collision with sand
		if (!collisionRegistered)
			for (var hitbox : sandHitbox)
				if (Physics.collisionBetween(hitbox, temp)) {
					currentCollision = Simulation.Collision.SAND;
					collisionRegistered = true;
				}

		// See if there's a collision with water
		if (!collisionRegistered)
			for (var hitbox : mountainHitbox)
				if (Physics.collisionBetween(hitbox, temp)) {
					currentCollision = Simulation.Collision.MOUNTAIN;
					collisionRegistered = true;
				}

		return currentCollision;
	}

	protected ImmutableVec2f closestWaterPointTo(int inputx, int inputy) {
		//visitedPoints.clear();

		float x = 0f;
		float y = 0f;
		double bestDistance = Double.MAX_VALUE;

		float x_upper_bound = inputx > renderer.getWidth() / 2 ? (float) renderer.getWidth() / 2 : 0.0f;
		float x_lower_bound = inputx > renderer.getWidth() / 2 ? (float) renderer.getWidth() : (float) renderer.getWidth() / 2;

		float y_upper_bound = inputy > renderer.getHeight() / 2 ? (float) renderer.getHeight() / 2 : 0.0f;
		float y_lower_bound = inputy > renderer.getHeight() / 2 ? (float) renderer.getHeight() : (float) renderer.getHeight() / 2;

		int triesSinceBestDistance = 0;
		for (float j = y_upper_bound; j < y_lower_bound; j += 1.0f) {
			for (float i = x_upper_bound; i < x_lower_bound; i += 1.0f) {
				double distance = (i - inputx) * (i - inputx) + (j - inputy) * (j - inputy);
				//visitedPoints.add((int) i);
				//visitedPoints.add((int) j);
				if (distance < bestDistance && collidesWithWater((int) i, (int) j)) {
					bestDistance = distance;
					x = i;
					y = j;
				}
			}
		}

		return new ImmutableVec2f(x, y);
	}

	@Override
	public void draw(Graphics g) {
		/*var color = g.getColor();
		g.setColor(new Color(200, 0, 100, 50));
		for (int i = 0; i < visitedPoints.size() - 1; i += 2) {
			try {
				g.drawRect(visitedPoints.get(i), visitedPoints.get(i + 1), 1, 1);
			} catch (Exception ignored) {
			}

		}
		g.setColor(color);*/
	}
}

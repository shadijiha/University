package simulation;

import com.engin.*;
import com.engin.math.*;
import com.engin.shapes.*;

public class BruteForcedSimulation extends Simulation {

	protected BruteForcedSimulation(Renderer renderer) {
		super(renderer);
	}


	@Override
	protected ImmutableVec2f closestWaterPointTo(int inputx, int inputy) {
		float x = 0f;
		float y = 0f;
		double bestDistance = Double.MAX_VALUE;

		for (float j = 0f; j < renderer.getHeight(); j += 1.0f) {
			for (float i = 0f; i < renderer.getWidth(); i += 1.0f) {
				double distance = Vector2f.distance(new Vector2f(inputx, inputy), new Vector2f(i, j));
				if (distance < bestDistance && getCollisionOfPoint((int) i, (int) j) == Collision.WATER) {
					bestDistance = distance;
					x = i;
					y = j;
				}
			}
		}

		return new ImmutableVec2f(x, y);
	}

	@Override
	protected Collision getCollisionOfPoint(int x, int y) {

		com.engin.shapes.Shape temp = new Rectangle(x, y, 1, 1);

		// See if there's a collision with water
		boolean collisionRegistered = false;
		Collision currentCollision = Collision.GRASS;

		for (var hitbox : waterHitbox)
			if (Physics.collisionBetween(hitbox, temp)) {
				currentCollision = Collision.WATER;
				collisionRegistered = true;
			}


		// See if there's a collision with sand
		if (!collisionRegistered)
			for (var hitbox : sandHitbox)
				if (Physics.collisionBetween(hitbox, temp)) {
					currentCollision = Collision.SAND;
					collisionRegistered = true;
				}

		// See if there's a collision with water
		if (!collisionRegistered)
			for (var hitbox : mountainHitbox)
				if (Physics.collisionBetween(hitbox, temp)) {
					currentCollision = Collision.MOUNTAIN;
					collisionRegistered = true;
				}

		return currentCollision;
	}
}

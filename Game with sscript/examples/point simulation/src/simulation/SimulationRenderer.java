/**
 *
 */
package simulation;

import com.engin.*;
import com.engin.io.*;
import com.engin.math.*;
import com.engin.shapes.Rectangle;
import com.engin.shapes.Shape;

import java.awt.*;

public class SimulationRenderer extends Scene {

	public final static ImmutableVec2f airportA = new ImmutableVec2f(65, 45);
	public final static ImmutableVec2f airportB = new ImmutableVec2f(1126, 445);

	private Shape background;
	Simulation simulation;


	Renderer renderer;
	private Renderer statsWindow;

	@Override
	public void init(Renderer renderer) {
		this.renderer = renderer;
		background = new Rectangle(0, 0, renderer.getWidth(), renderer.getHeight());
		background.setTexture("assets/map.png");

		simulation = new OptimizedSimulation(renderer);
		simulation.init(renderer);

		statsWindow = new Renderer(400, renderer.getWindow().getHeight());

		statsWindow.submit(new StatsScene(this));
		statsWindow.start();
	}

	@Override
	public void update(float dt) {
		simulation.update(dt);
	}

	@Override
	public void draw(Graphics g) {

		background.draw(g);

		var color = g.getColor();
		g.setColor(Color.RED);
		g.fillOval((int) simulation.getClosestPointToWater().getX(), (int) simulation.getClosestPointToWater().getY(), 10, 10);
		g.setColor(Color.BLACK);
		g.drawLine((int) simulation.getClosestPointToWater().getX(), (int) simulation.getClosestPointToWater().getY(), Input.getMouseX(), Input.getMouseY());
		g.setColor(color);

		g.drawString(simulation.getClass().getSimpleName(), 10, 30);


		simulation.draw(g);
	}
}

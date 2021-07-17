package simulation;

import com.engin.*;
import com.engin.math.*;
import com.engin.ui.Button;

import java.awt.*;
import java.util.List;
import java.util.*;

public class StatsScene extends Scene {

	private final int OFFSET_Y = 20;
	private final List<String> statsTable = new ArrayList<>();
	private double timeSinceStart = 0.0D;

	private final SimulationRenderer simulationRenderer;

	private final Button button = new Button("Optimize simulation");

	public StatsScene(SimulationRenderer simulationRenderer) {
		super("Stats panel");
		this.simulationRenderer = simulationRenderer;
	}

	@Override
	public void init(Renderer statsRenderer) {
		var parentRenderer = simulationRenderer.renderer;

		statsRenderer.getWindow().setLocation(
				parentRenderer.getWindow().getLocationOnScreen().x + parentRenderer.getWindow().getWidth() + 40,
				parentRenderer.getWindow().getLocationOnScreen().y);
		statsRenderer.getWindow().setTitle("Stats panel");

		Util.setInterval(() -> timeSinceStart += 0.01, 10);

		button.onClick(() -> {

			if (simulationRenderer.simulation.getClass() == OptimizedSimulation.class) {
				button.setText("Brute force Sim.");
				simulationRenderer.simulation = new BruteForcedSimulation(simulationRenderer.renderer);
				simulationRenderer.simulation.init(simulationRenderer.renderer);
			} else {
				button.setText("Optimize simulation");
				simulationRenderer.simulation = new OptimizedSimulation(simulationRenderer.renderer);
				simulationRenderer.simulation.init(simulationRenderer.renderer);
			}
		});
	}

	@Override
	public void update(float dt) {
		// See if there's a collision with water
		statsTable.clear();

		var simulation = simulationRenderer.simulation;

		statsTable.add(String.format("Time since start: %.2f s", timeSinceStart));
		statsTable.add(String.format("Collision calc.: %.4f ms", simulation.getCollisionTimer() * 0.000001));
		statsTable.add(String.format("Closest water calc.: %.4f ms", simulation.getClosestWaterPointTimer() * 0.000001));
		statsTable.add(null);
		statsTable.add(String.format("Current collision: %s", simulation.getCurrentCollision().toString()));
	}

	@Override
	public void draw(Graphics g) {
		g.setFont(new Font("Arial", Font.PLAIN, 18));

		int y = 20;
		for (var record : statsTable) {
			try {
				g.drawString(record == null ? "\n" : record, 10, y);
			} catch (Exception ignored) {
			}

			y += OFFSET_Y;
		}

		button.setPosition(ICoordinates2F.from(20, y));
		button.setDimensions(ICoordinates2F.from(300, button.getDimensions().y));
		button.render((Graphics2D) g);
	}
}

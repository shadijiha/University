/**
 *
 */
package editor;

import com.engin.Renderer;
import com.engin.*;
import com.engin.io.*;
import com.engin.logger.*;
import com.engin.math.*;
import com.engin.shapes.Rectangle;
import com.engin.shapes.Shape;
import com.engin.shapes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.*;

public class Editor extends Scene {

	private enum DrawingMode {
		RECTANGLE, CIRCLE
	}

	private boolean editMode;
	private boolean isDrawing;
	private DrawingMode mode;
	private List<Shape> shapes;
	private ImmutableVec2f firstClick;
	private ImmutableVec2f secondClick;
	private Renderer renderer;
	private String lastSavedFilename;
	private JMenuBar menuBar;

	private Rectangle background;

	@Override
	public void init(Renderer renderer) {
		Log.setLogLevel(Log.Level.INFO);

		this.renderer = renderer;
		editMode = true;
		shapes = new ArrayList<>();
		mode = DrawingMode.RECTANGLE;

		background = new Rectangle(0, 0, renderer.getWidth(), renderer.getHeight());
		background.setTexture("assets/map.png");


		menuBar = new JMenuBar();
		setupFileMenu();
		setupEditorMenu();
		renderer.getWindow().setJMenuBar(menuBar);
		renderer.getWindow().pack();
	}

	private void setupFileMenu() {
		JMenu menu, submenu;
		JMenuItem menuItem;
		JRadioButtonMenuItem rbMenuItem;
		JCheckBoxMenuItem cbMenuItem;


		//Build the first menu.
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
				"The only menu in this program that has menu items");
		menuBar.add(menu);

		//a group of JMenuItems
		menuItem = new JMenuItem("Save",
				KeyEvent.VK_S);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Saves the current editor layout to a MAP file");

		menuItem.addActionListener(e -> this.save());
		menu.add(menuItem);

		var menuItem2 = new JMenuItem("Load",
				KeyEvent.VK_L);
		menuItem2.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		menuItem2.getAccessibleContext().setAccessibleDescription(
				"Loads a MAP file to the editor");

		menuItem2.addActionListener(e -> this.load());
		menu.add(menuItem2);
	}

	private void setupEditorMenu() {
		JMenu menu, submenu;
		JMenuItem menuItem;
		JRadioButtonMenuItem rbMenuItem;
		JCheckBoxMenuItem cbMenuItem;

		//Build the first menu.
		menu = new JMenu("Editor");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
				"The only menu in this program that has menu items");
		menuBar.add(menu);

		//a group of JMenuItems
		menuItem = new JMenuItem("Clear",
				KeyEvent.VK_S);
		//menuItem.setAccelerator(KeyStroke.getKeyStroke(
		//KeyEvent.VK_S, ActionEvent.CTRL_MASK));

		menuItem.addActionListener(e -> shapes.clear());
		menu.add(menuItem);
	}

	@Override
	public void update(float dt) {
	}

	@Override
	public void draw(Graphics g) {

		background.draw(g);
		g.setFont(new Font("Arial", Font.BOLD, 16));
		g.drawString("(E) Edit mode (" + mode.toString() + ")", 10, 40);
		g.drawString("(M) Switch mode", 10, 60);

		if (editMode) {
			g.drawString("Edit mode", 10, 20);

			if (firstClick != null) {
				isDrawing = true;

				if (mode == DrawingMode.RECTANGLE)
					g.drawRect((int) firstClick.x, (int) firstClick.y, Input.getMouseX() - (int) firstClick.x, Input.getMouseY() - (int) firstClick.y);
				else
					g.drawOval((int) firstClick.x, (int) firstClick.y, Input.getMouseX() - (int) firstClick.x, Input.getMouseY() - (int) firstClick.y);

				if (secondClick != null) {
					isDrawing = false;

					if (mode == DrawingMode.CIRCLE)
						shapes.add(new Circle((int) firstClick.x, (int) firstClick.y, (int) secondClick.x - (int) firstClick.x, (int) secondClick.y - (int) firstClick.y));
					else
						shapes.add(new Rectangle((int) firstClick.x, (int) firstClick.y, (int) secondClick.x - (int) firstClick.x, (int) secondClick.y - (int) firstClick.y));
					firstClick = null;
					secondClick = null;
				}
			}
		}

		for (var shape : shapes)
			shape.draw(g);
	}

	private void save() {

		String filename = JOptionPane.showInputDialog("Enter file name (default extension *.map):", "test.map");

		if (filename == null)
			return;

		if (filename.isEmpty()) {
			JOptionPane.showMessageDialog(renderer, "Filename cannot be empty", "Error!", JOptionPane.ERROR_MESSAGE);
			save();
		}

		if (!filename.contains("."))
			filename += ".map";

		StringBuilder builder = new StringBuilder();

		for (final Shape shape : shapes) {
			if (shape instanceof Rectangle)
				builder.append("RECT ");
			else if (shape instanceof Circle)
				builder.append("CIRC ");

			builder.append((int) shape.getPosition().x).append(" ").append((int) shape.getPosition().y).append(" ");
			builder.append((int) shape.getDimensions().x).append(" ").append((int) shape.getDimensions().y).append("\n");
		}

		FileUtil.writeToFile(filename, builder);
		lastSavedFilename = filename;
	}

	private void load() {
		String filename = JOptionPane.showInputDialog("Enter file name (default extension *.map):", lastSavedFilename);

		if (filename == null)
			return;

		if (filename.isEmpty()) {
			JOptionPane.showMessageDialog(renderer, "Filename cannot be empty", "Error!", JOptionPane.ERROR_MESSAGE);
			load();
		}

		if (!filename.contains("."))
			filename += ".map";

		shapes.clear();

		String file = FileUtil.fileToString(filename);

		if (file == null) {
			JOptionPane.showMessageDialog(renderer, "File not found", "Error!", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String[] lines = file.split("\n");

		try {
			for (String line : lines) {
				if (line.isEmpty())
					continue;

				String tokens[] = line.split(" ");

				switch (tokens[0]) {
					case "RECT":
						shapes.add(new Rectangle(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4])));
						break;
					case "CIRC":
						shapes.add(new Circle(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4])));
						break;
					default:
						Log.error("Unknown shape --> %s", tokens[0]);
						break;
				}
			}
		} catch (Exception e) {
			Log.error(e);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (firstClick == null)
				firstClick = new ImmutableVec2f(e.getX(), e.getY());
			else
				secondClick = new ImmutableVec2f(e.getX(), e.getY());
		} else {
			firstClick = null;
			secondClick = null;
			isDrawing = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_E)
			editMode = !editMode;
		else if (e.getKeyCode() == KeyEvent.VK_S)
			save();
		else if (e.getKeyCode() == KeyEvent.VK_L)
			load();
		else if (e.getKeyCode() == KeyEvent.VK_M)
			mode = mode == DrawingMode.CIRCLE ? DrawingMode.RECTANGLE : DrawingMode.CIRCLE;
	}
}

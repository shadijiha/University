package driver;// Java program to print DFS traversal from a given given graph

// This class represents a directed graph using adjacency list
// representation

import javax.swing.*;
import java.awt.*;

public class Main {

	// Driver method
	public static void main(String args[]) {


		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				JFrame frame = new JFrame();
				JPanel panel = new DrawingComponent(1280, 720);

				frame.setPreferredSize(new Dimension(1280, 720));
				frame.add(panel);

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);

			}
		});

	}
}

/***
 * Driver class
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		// To avoid crashes
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame frame = new JFrame("Hehexd");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setPreferredSize(new Dimension(400, 400));
				frame.setLayout(new FlowLayout());

				List<JLabel> labels = new ArrayList<JLabel>();
				labels.add(createLabel("This is a test"));
				labels.add(createLabel("Test2"));
				
				// Add all JLables to window
				for (var temp : labels) {
					frame.add(temp);
				}

				// Create buttons
				JButton b = new JButton("Click me!");
				formatJButton(b);
				b.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						System.exit(0);
					}

				});

				frame.add(b);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}

		});

	}

	public static JLabel createLabel(String text) {
		return createLabel(text, new Font("Times New Roman", Font.PLAIN, 14));
	}

	public static JLabel createLabel(String text, Font font) {
		JLabel label = new JLabel("<html>" + text + "<br></html>");
		label.setFont(font);

		return label;
	}

	public static void formatJButton(JButton b) {
		b.setBackground(new Color(59, 89, 182));
		b.setForeground(Color.WHITE);
		b.setFocusPainted(false);
		b.setFont(new Font("Tahoma", Font.BOLD, 12));
	}
}
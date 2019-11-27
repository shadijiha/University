/***
 * 
 * Driver class
 * */

package app;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.awt.Color;
import java.awt.geom.*;
import java.awt.Font;

public class DrawingComponent extends JComponent    {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4659119803554492770L;

	public void paintComponent(Graphics g)  {
        Graphics2D g2 = (Graphics2D)g;

        // Draw stuff        
        Rectangle rect = new Rectangle(5, 5, 100, 200);
        g2.setColor(new Color(255, 0, 0));
        g2.fill(rect);

        Ellipse2D.Double ellipse = new Ellipse2D.Double(100, 100, 50, 20);
        g2.setColor(new Color(0, 255, 0));
        g2.fill(ellipse);

        Line2D.Double line1 = new Line2D.Double(150, 500, 100, 50);
        g2.setColor(new Color(0, 0, 255));
        g2.draw(line1);

        g2.setFont(new Font("Times new Roman", Font.BOLD, 14));
        g2.drawString("hehexd", 50, 50);

    }
}

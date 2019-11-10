/**
 * java Graphics test
 */
package app;

import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        
        JFrame window = new JFrame();
        window.setSize(640, 480);
        window.setTitle("Java GUI test");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        DrawingComponent DC = new DrawingComponent();
        window.add(DC);

    }
}
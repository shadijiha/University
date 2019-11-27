/***
 * 
 * Driver class
 * */

package app;

public abstract class Main {

	public static final void main(String[] args) {

		// TODO:
//		JFrame window = new JFrame();
//		window.setSize(640, 480);
//		window.setTitle("Java GUI test");
//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		window.setLocationRelativeTo(null);
//		window.setVisible(true);
//
//		DrawingComponent DC = new DrawingComponent();
//		window.add(DC);
//		Engin3D.Mesh test = new Engin3D.Mesh();
//
//		SetInterval(function() {}, 1000);
//		new Timer().scheduleAtFixedRate(new TimerTask() {
//			@Override
//			public void run() {
//				print("?XD");
//			}
//		}, 0, 1000);

	}

	public static <T> void print(T... args) {
		for (T arg : args)
			System.out.print(arg + " ");
		System.out.println();
	}
}

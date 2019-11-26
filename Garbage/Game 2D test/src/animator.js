/***
 * This file contais the render function that will go
 * to the game loop
 *
 */

// Setup main canvas
const canvas = new Canvas(0, 0, window.innerWidth, window.innerHeight);
canvas.setBackground("lightblue"); // Render is implicitly called

// Test
const circy = new Circle(500, 500, 50);
circy.setFill("red");

// For game Loop see "index.js"
function render() {
	// Clear canvas
	canvas.clear();

	// Show FPS
	new Text((1000 / Time.deltaTime).toFixed(2), 100, 100, { size: 70 }).render(
		canvas
	);

	// SHow pause/Resume Text
	const pauseText = new Text("Abort", 400, 100, { size: 20 });
	pauseText.render(canvas);

	if (pauseText.hover(canvas)) {
		pauseText.text = "Game loop exited";
		pause();
	}

	// Draw stuff
	circy.render(canvas);
	circy.move(new Vector(0.1, 0));
}

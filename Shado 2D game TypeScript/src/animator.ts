/***
 * This file contais the render function that will go
 * to the game loop
 *
 */

// Setup main canvas
const canvas = new Canvas(0, 0, window.innerWidth, window.innerHeight);
canvas.setBackground("#191970"); // Render is implicitly called

// Test
let snow = [];
for (let i = 0; i < 100; i++) {
	const temp = new Circle(
		random(0, canvas.width),
		random(0, canvas.height),
		random(1, 50)
	);
	temp.enableCollision();
	temp.dx = random(0.01, 0.05);
	temp.dy = random(0, 0.02);
	temp.setFill("white");
	snow.push(temp);
}

// For game Loop see "index.js"
function render() {
	// Clear canvas
	canvas.clear();

	// Show FPS
	new ShadoText((1000 / Time.deltaTime).toFixed(2), 100, 100, {
		size: 70,
		color: "white"
	}).render(canvas);

	// SHow pause/Resume Text
	const pauseText = new ShadoText("Abort", 400, 100, {
		size: 20,
		background: "black",
		color: "white"
	});
	pauseText.render(canvas);

	if (pauseText.hover(canvas)) {
		pauseText.text = "Game loop exited";
		pause();
	}

	// Draw stuff
	for (let temp of snow) {
		// Move the circles
		temp.move(temp.dx, temp.dy);

		// If they go outside the canvas return them to the beginning
		if (temp.x + temp.r > canvas.width) {
			temp.x = -random(100);
		}
		if (temp.y > canvas.height) {
			temp.y = -random(100);
		}

		// Detect collision if cicle if insdie canvas
		if (
			temp.x > -temp.r &&
			temp.x < canvas.width &&
			temp.y > -temp.r &&
			temp.y < canvas.height
		) {
			for (let other of snow) {
				// Avoid to detect collision with itself
				if (temp != other) {
					if (temp.collides(other)) {
						// Set a random color for the circle only if it doesn't
						// have 1. (To avoid flicker)
						if (!temp.storedColor) {
							temp.storedColor = randomColor();
						}
						temp.setFill(temp.storedColor);
						break;
					}
				}
			}

			// Redner the circle
			temp.render(canvas);

			// Reset its color
			temp.setFill("white");
		}
	}
}

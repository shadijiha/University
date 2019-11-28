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
for (let i = 0; i < 2; i++) {
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
	new Text((1000 / Time.deltaTime).toFixed(2), 100, 100, {
		size: 70,
		color: "white"
	}).render(canvas);

	// SHow pause/Resume Text
	const pauseText = new Text("Abort", 400, 100, {
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
		temp.move(temp.dx, temp.dy);
		if (temp.x + temp.r > canvas.width) {
			temp.x = -random(100);
		}
		if (temp.y > canvas.height) {
			temp.y = -random(100);
		}
		if (
			temp.x > -temp.r &&
			temp.x < canvas.width &&
			temp.y > -temp.r &&
			temp.y < canvas.height
		) {
			for (let other of snow) {
				if (temp.collides(other)) {
					temp.setFill("green");
					break;
				}
			}
			temp.render(canvas);
			temp.setFill("white");
		}
	}
}

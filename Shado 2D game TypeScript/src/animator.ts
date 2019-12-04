/***
 * This file contais the render function that will go
 * to the game loop
 *
 */
// Setup main canvas
const canvas = new Canvas(0, 0, window.innerWidth, window.innerHeight);
canvas.setBackground("rgb(0, 0, 100)"); // Render is implicitly called

// Main Stuff
// const player = new Player(100, "66%", 50, 100);
// new Ground(0, "66%", "100%", "34%", Ground.default);
let objectArray: Circle[] = [];
for (let i = 0; i < 200; i++) {
	const temp = new Circle(
		floor(random(canvas.width)),
		floor(random(canvas.height)),
		floor(random(5, 10))
	);
	temp.dx = random(-0.1, 0.1);
	temp.dy = random(-0.1, 0.1);
	temp.setFill(randomColor());
	temp.enableCollision();
	objectArray.push(temp);
}

// For game Loop see "index.js"
function render() {
	// Clear canvas
	canvas.clear(0, 0, canvas.width, canvas.height);

	// Show FPS
	// new ShadoText((1000 / Time.deltaTime).toFixed(2), 100, 100, {
	// 	size: 70,
	// 	color: "white"
	// }).render(canvas);

	// SHow pause/Resume Text
	// const pauseText = new ShadoText("Abort", 400, 100, {
	// 	size: 20,
	// 	background: "black",
	// 	color: "white"
	// });
	// pauseText.render(canvas);

	// if (pauseText.hover(canvas)) {
	// 	pauseText.text = "Game loop exited";
	// 	pause();
	// }

	/*****************************
	 ********* DRAW ALL ***********
	 *****************************/
	// Draw grounds
	/*Ground.allGrounds.forEach(g => {
		if (g.display) g.render(canvas);
	});*/

	for (const circ of objectArray) {
		circ.render(canvas);
		circ.move(circ.dx, circ.dy);

		// Collision
		if (circ.x + circ.r >= canvas.width || circ.x - circ.r <= 0) {
			circ.dx *= -1;
		}

		if (circ.y + circ.r >= canvas.height || circ.y - circ.r <= 0) {
			circ.dy *= -1;
		}

		for (const other of objectArray) {
			if (!other.equals(circ)) {
				if (circ.collides(other)) {
					circ.dx *= -1;
					circ.dy *= -1;
					other.dx *= -1;
					other.dy *= -1;
				}
			}
		}
	}

	// Draw player
	//player.draw(canvas);
}

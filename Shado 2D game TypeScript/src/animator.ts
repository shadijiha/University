/***
 * This file contais the render function that will go
 * to the game loop
 *
 */
// Setup main canvas
const canvas = new Canvas(0, 0, window.innerWidth, window.innerHeight);
canvas.setBackground("#87CEEB"); // Render is implicitly called

// Main Stuff
const player = new Player(100, "66%", 50, 100);
new Ground(0, "66%", "100%", "34%", Ground.default);

// For game Loop see "index.js"
function render() {
	// Clear canvas
	canvas.clear(0, 0, canvas.width, canvas.height);

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

	/*****************************
	 ********* DRAW ALL ***********
	 *****************************/
	// Draw grounds
	Ground.allGrounds.forEach(g => {
		if (g.display) g.render(canvas);
	});

	// Draw player
	player.draw(canvas);
}

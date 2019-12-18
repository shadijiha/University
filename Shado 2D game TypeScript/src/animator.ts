import { TIMEOUT } from "dns";

/***
 * This file contais the render function that will go
 * to the game loop
 *
 */
// Setup main canvas
const canvas = new Canvas(
	0,
	0,
	window.innerWidth,
	window.innerHeight,
	"absolute"
);
canvas.setBackground("rgb(0, 0, 100)"); // Render is implicitly called
//EnginGlobal.winCanvas.render(); // For some reason I need to call this here too

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
	for (const terrain of Terrain.allTerrain) {
		terrain.draw(canvas);

		if (terrain.shape.collides(new Vertex(mouse.x, mouse.y))) {
			terrain.shape.setFill("green");
		} else {
			terrain.shape.setFill("brown");
		}
	}
}

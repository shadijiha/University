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
canvas.setBackground("rgb(0, 0, 70)"); // Render is implicitly called

const win = new ShadoWindow(500, 500, 600, 800, "HitBox data");
win.generate();
win.CENTER_X();
win.CENTER_Y();
win.setContent(
	"<h2>Copy this hitBox collection and append it to youe free-shape</h2><br />"
);
win.addContent("[");

///////////////////////////////////
///////////////////////////////////
////// PUT YOUR SHAPE HERE ////////
///////////////////////////////////
///////////////////////////////////
const MAIN_SHAPE = 	new Shape([new Vertex(0, 0), new Vertex(600, 0), new Vertex(0, 600)], {
		fill: "brown"
	});
let tempRect: Rectangle;

// For game Loop see "index.js"
function render() {
	// Clear canvas
	canvas.clear(0, 0, canvas.width, canvas.height);

	new ShadoText("Hold shift to add hitBox", 500, 200, {
		size: 30,
		color: "white"
	}).draw(canvas);

	/*****************************
	 ********* DRAW ALL ***********
	 *****************************/
	win.open();
	MAIN_SHAPE.draw(canvas);

	if (mouse.mode == "edit") {
		let modX = mouse.lastClicked.x;
		let modY = mouse.lastClicked.y;
		let width = mouse.x - mouse.lastClicked.x;
		let height = mouse.y - mouse.lastClicked.y;

		if (width < 0) {
			width = Math.abs(width);
			modX = modX - width;
		}

		if (height < 0) {
			height = Math.abs(height);
			modY = modY - height;
		}

		tempRect = new Rectangle(modX, modY, width, height);
		tempRect.setFill("rgba(255, 255, 255, 0.5)");
		tempRect.draw(canvas);
	}
}

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
	"absolute",
	$("body")
);
canvas.setBackground("rgb(0, 0, 100)"); // Render is implicitly called

// Create dom
const game = new Board([
	[7, 8, 0, 4, 0, 0, 1, 2, 0],
	[6, 0, 0, 0, 7, 5, 0, 0, 9],
	[0, 0, 0, 6, 0, 1, 0, 7, 8],
	[0, 0, 7, 0, 4, 0, 2, 6, 0],
	[0, 0, 1, 0, 5, 0, 9, 3, 0],
	[9, 0, 4, 0, 6, 0, 0, 0, 5],
	[0, 7, 0, 3, 0, 0, 0, 1, 2],
	[1, 2, 0, 0, 0, 7, 4, 0, 0],
	[0, 4, 9, 2, 0, 6, 0, 0, 7]
]);
const solved = new Board([
	[7, 8, 0, 4, 0, 0, 1, 2, 0],
	[6, 0, 0, 0, 7, 5, 0, 0, 9],
	[0, 0, 0, 6, 0, 1, 0, 7, 8],
	[0, 0, 7, 0, 4, 0, 2, 6, 0],
	[0, 0, 1, 0, 5, 0, 9, 3, 0],
	[9, 0, 4, 0, 6, 0, 0, 0, 5],
	[0, 7, 0, 3, 0, 0, 0, 1, 2],
	[1, 2, 0, 0, 0, 7, 4, 0, 0],
	[0, 4, 9, 2, 0, 6, 0, 0, 7]
]);
game.solve();

// $("body").style.backgroundColor = "black";

// const dom = createElement("div", $("body"));
// dom.style.fontSize = "20pt";
// dom.style.fontFamily = "Arial";
// dom.style.color = "white";
// dom.innerHTML = game.toString();

// game.solve();

// dom.innerHTML += "<br /><br />Solved! <br />";
// dom.innerHTML += game.toString();

// For game Loop see "index.js"
render = () => {
	//Clear canvas
	canvas.clear(0, 0, canvas.width, canvas.height);
	/*****************************
	 ********* DRAW ALL ***********
	 *****************************/
	game.render(canvas);
	solved.setPosition(new Vertex(800, 0));
	solved.render(canvas);
};

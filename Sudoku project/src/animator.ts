/***
 * This file contais the render function that will go
 * to the game loop
 *
 */
// Setup main canvas
// const canvas = new Canvas(
// 	0,
// 	0,
// 	window.innerWidth,
// 	window.innerHeight,
// 	"absolute",
// 	$("body")
// );
// canvas.setBackground("rgb(0, 0, 100)"); // Render is implicitly called

// Create dom
const game: number[][] = [
	[7, 8, 0, 4, 0, 0, 1, 2, 0],
	[6, 0, 0, 0, 7, 5, 0, 0, 9],
	[0, 0, 0, 6, 0, 1, 0, 7, 8],
	[0, 0, 7, 0, 4, 0, 2, 6, 0],
	[0, 0, 1, 0, 5, 0, 9, 3, 0],
	[9, 0, 4, 0, 6, 0, 0, 0, 5],
	[0, 7, 0, 3, 0, 0, 0, 1, 2],
	[1, 2, 0, 0, 0, 7, 4, 0, 0],
	[0, 4, 9, 2, 0, 6, 0, 0, 7]
];

const dom = createElement("div", $("body"));
dom.style.fontSize = "40pt";
dom.style.fontFamily = "Arial";
dom.innerHTML = printBoard(game);

// For game Loop see "index.js"
render = () => {
	//Clear canvas
	//canvas.clear(0, 0, canvas.width, canvas.height);
	/*****************************
	 ********* DRAW ALL ***********
	 *****************************/
};

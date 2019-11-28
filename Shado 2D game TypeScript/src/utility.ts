/***
 *
 * Utility functions
 */

// Call this function like so:
// await sleep(time in ms);
function sleep(ms) {
	return new Promise(resolve => setTimeout(resolve, ms));
}

function random(min, max) {
	if (max == undefined || max == null) {
		max = min;
		min = 0;
	}
	return Math.random() * (max - min) + min;
}

function distance(x1, y1, x2, y2) {
	return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
}

function pause() {
	EnginGlobal.oldFPS = EnginGlobal.FPS;
	EnginGlobal.FPS = 0;
	EnginGlobal.PAUSED = true;
}

function resume() {
	EnginGlobal.FPS = EnginGlobal.oldFPS;
	EnginGlobal.PAUSED = false;
	gameLoop();
}

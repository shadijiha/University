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
	return Math.floor(Math.random() * (max - min) + min);
}

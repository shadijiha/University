/***
 *
 * This file contains all the global variables used in the game
 * This file contains @Gameobject super class and @Time and @global namespaces
 *
 */

class Namespace {
	constructor(name) {
		this.name = name;
		this.id = random(0, 1e6);
	}
}

// Setup global Variables
const global = new Namespace("global");
global.globalBuffer = {};
global.FPS = 60;
global.setFPS = newFPS => {
	global.FPS = newFPS;
};

const Time = new Namespace("time");
Time.deltaTime = 1000 / global.FPS;

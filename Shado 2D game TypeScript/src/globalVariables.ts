/***
 *
 * This file contains all the EnginGlobal variables used in the game
 * This file contains @Gameobject super class and @Time and @EnginGlobal namespaces
 *
 */

class Namespace {
	public name: string;
	public id: number;
	public FPS: number;
	public EnginGlobalBuffer: Object;
	public collisionObjects: any[];
	public deltaTime: number;
	public setFPS: Function;
	public PAUSED: boolean;
	public oldFPS: number;

	constructor(name: string) {
		this.name = name;
		this.id = random(0, 1e6);
	}
}

// Setup EnginGlobal Variables
const EnginGlobal = new Namespace("EnginEnginGlobal");
EnginGlobal.EnginGlobalBuffer = {};
EnginGlobal.FPS = 60;
EnginGlobal.setFPS = newFPS => {
	EnginGlobal.FPS = newFPS;
};
EnginGlobal.collisionObjects = [];

const Time = new Namespace("time");
Time.deltaTime = 1000 / EnginGlobal.FPS;

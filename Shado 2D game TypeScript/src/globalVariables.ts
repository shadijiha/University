import { timingSafeEqual } from "crypto";

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

class Logger {
	private logLevel: number;
	public static LOG_LEVEL_ERROR: number = 1;
	public static LOG_LEVEL_WARNNING: number = 2;
	public static LOG_LEVEL_INFO: number = 3;
	private buffer: string[] = [];

	public constructor(level?: number) {
		this.logLevel = level || Logger.LOG_LEVEL_WARNNING;
	}

	public setLevel(newLevel: number) {
		this.logLevel = newLevel;
	}

	public error(msg: string): void {
		msg = "%cERROR:	%c" + msg;
		this.buffer.push(msg);

		if (this.logLevel >= Logger.LOG_LEVEL_ERROR)
			console.log(msg, "color: red; font-weight: bold;", "");
	}

	public warn(msg: string): void {
		msg = "%cWARNNING:	%c" + msg;
		this.buffer.push(msg);

		if (this.logLevel >= Logger.LOG_LEVEL_WARNNING)
			console.log(msg, "color: yellow; font-weight: bold;", "");
	}

	public info(msg: string): void {
		msg = "%cINFO:	%c" + msg;
		this.buffer.push(msg);

		if (this.logLevel >= Logger.LOG_LEVEL_INFO)
			console.log(msg, "color: green; font-weight: bold;", "");
	}

	public log(msg: any): void {
		if (msg instanceof Array) {
			console.table(msg);
		} else if (msg instanceof Object) {
			console.log(msg);
		} else {
			this.info(msg);
		}
	}

	public history(): void {
		this.buffer.forEach(e =>
			console.log(e, "color:orange; font-weight:bold;", "")
		);
	}
}

// Setup EnginGlobal Variables
const EnginGlobal = new Namespace("EnginEnginGlobal");
EnginGlobal.EnginGlobalBuffer = {};
EnginGlobal.FPS = 60;
EnginGlobal.setFPS = (newFPS: number) => {
	EnginGlobal.FPS = newFPS;
};
EnginGlobal.collisionObjects = [];

const Time = new Namespace("time");
Time.deltaTime = 1000 / EnginGlobal.FPS;

// Setup Logger
const debug = new Logger(Logger.LOG_LEVEL_INFO);

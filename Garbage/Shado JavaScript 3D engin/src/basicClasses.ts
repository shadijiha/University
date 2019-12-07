/***
 *
 * This file contains the GAME class
 *
 */

//https://stackoverflow.com/questions/44849831/responsive-canvas-on-window-resize-event

class Engin {
	protected name: string;
	protected id: string;
	protected buffer: any;

	public constructor(name: string) {
		this.name = name;
		this.id = "object_" + Math.floor(random(0, 1e8));
		this.buffer = {};
	}

	/**
	 * Renders a gameobject to a target canvas.
	 * @abstract
	 * @return {void}
	 */
	render(targetCanvas: Canvas): void {
		throw new Error("must be implemented by subclass!");
	}

	draw(targetCanvas: Canvas): void {
		this.render(targetCanvas);
	}

	enableCollision(): void {
		// If the object is an instance of canvas, don't add it
		if (this instanceof Canvas) {
			throw new Error("Cannnot tag CANVAS objects as collidable.");
		}
		this.collision = true;

		// See if the object exits already int the EnginGlobal.collisionObjects array
		let exist = false;
		for (let element of EnginGlobal.collisionObjects) {
			if (element.id == this.id) {
				exist = true;
				break;
			}
		}

		// Add object if it doesn't exist
		if (!exist) EnginGlobal.collisionObjects.push(this);
	}

	disableCollision(): void {
		this.collision = false;

		// See if the object exits already int the EnginGlobal.collisionObjects array
		let exist = false;
		for (let element of EnginGlobal.collisionObjects) {
			if (element.id == this.id) {
				exist = true;
				break;
			}
		}

		// Remove the object if it exists
		if (exist) {
			EnginGlobal.collisionObjects.splice(
				EnginGlobal.collisionObjects.indexOf(this),
				1
			);
		}
	}

	equals(other: Engin): boolean {
		return this.id == other.id;
	}

	protected parseToWidth(percentage: any): number {
		if (isNaN(percentage)) {
			percentage = percentage.split("");
			percentage.pop();
			percentage = percentage.join("");
			percentage = Number(percentage / 100) * window.innerWidth;
			return percentage;
		} else {
			return percentage;
		}
	}

	protected parseToHeight(percentage: any): number {
		if (isNaN(percentage)) {
			percentage = percentage.split("");
			percentage.pop();
			percentage = percentage.join("");
			percentage = Number(percentage / 100) * window.innerHeight;
			return percentage;
		} else {
			return percentage;
		}
	}
}

class Canvas extends Engin {
	public x: number;
	public y: number;
	public w: any;
	public h: any;
	public width: any;
	public height: any;
	public background: string;
	public canvas: any;
	public ctx: any;
	public overwrite: boolean;
	public parent: HTMLElement;
	public static: boolean;

	public constructor(
		posX: number,
		posY: number,
		width: any,
		height: any,
		parent?: HTMLElement
	) {
		super("canvas");
		this.x = posX;
		this.y = posY;
		this.w = width;
		this.h = height;
		this.width = width;
		this.height = height;
		this.background = "transparent";
		this.canvas = null;
		this.ctx = null;
		this.overwrite = false;
		this.parent = parent || document.querySelector("body");
		this.static = false;
	}

	render(): void {
		// Detect if width or height is passed as a precentage. It must have the following
		// form: "75%"
		if (isNaN(this.w)) {
			this.w = this.w.split("");
			this.w.pop();
			this.w = this.w.join("");
			this.w = Number(this.w / 100) * window.innerWidth;
		}

		if (isNaN(this.h)) {
			this.h = this.h.split("");
			this.h.pop();
			this.h = this.h.join("");
			this.h = Number(this.h / 100) * window.innerHeight;
		}

		let DOM = `<canvas id="${this.id}" style="position: absolute; top: ${this.y}; left: ${this.x}; background: ${this.background};" width="${this.w}" height="${this.h}">`;

		// if element doesn't exist add it to the page
		if (!document.getElementById(this.id)) {
			this.parent.innerHTML += DOM;
		} else {
			if (this.overwrite) {
				this.parent.removeChild(document.getElementById(this.id));
				this.parent.innerHTML += DOM;
			}
		}

		// Set context variables
		this.canvas = document.getElementById(this.id);
		this.ctx = this.canvas.getContext("2d");
	}

	clear(fromX?: number, fromY?: number, toX?: number, toY?: number): void {
		fromX = fromX || 0;
		fromY = fromY || 0;
		toX = toX || this.w;
		toY = toY || this.h;

		this.ctx.clearRect(fromX, fromY, toX, toY);
	}

	scale(x: number, y: number) {
		this.ctx.scale(x, y);
	}

	setPosition(newX: number, newY: number): void {
		this.x = newX || this.x;
		this.y = newY || this.y;
		this.render();
	}

	setBackground(color: string): void {
		this.background = color;
		this.render();
	}

	setWidth(newWidth: any): void {
		this.w = newWidth;
		this.width = newWidth;
		this.render();
	}

	setHeight(newHeight: any): void {
		this.h = newHeight;
		this.height = newHeight;
		this.render();
	}

	toggleOverwrite(booleanValue: boolean): void {
		if (booleanValue == undefined) this.overwrite = !this.overwrite;
		else this.overwrite = booleanValue;
	}
}

class Triangle extends Engin {
	public p: Vector[] = [];

	public constructor(vec1?: Vector | Triangle, vec2?: Vector, vec3?: Vector) {
		super("triangle");

		if (vec1 instanceof Triangle) {
			this.p = vec1.p;
		} else {
			this.p = new Array(3);
			if (vec1 && vec2 && vec3) {
				this.p[0] = vec1;
				this.p[1] = vec2;
				this.p[2] = vec3;
			}
		}
	}

	render(targetCanvas: Canvas) {
		targetCanvas.ctx.beginPath();
		targetCanvas.ctx.strokeStyle = "white";
		targetCanvas.ctx.moveTo(this.p[0].x, this.p[0].y);
		targetCanvas.ctx.lineTo(this.p[1].x, this.p[1].y);
		targetCanvas.ctx.lineTo(this.p[2].x, this.p[2].y);
		targetCanvas.ctx.lineTo(this.p[0].x, this.p[0].y);
		targetCanvas.ctx.stroke();
	}
}

class Mesh extends Engin {
	public tris: Triangle[] = [];

	public constructor() {
		super("mesh");
		this.tris = [];
	}
}

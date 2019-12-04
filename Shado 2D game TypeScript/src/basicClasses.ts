/***
 *
 * This file contains the GAME class
 *
 */

//https://stackoverflow.com/questions/44849831/responsive-canvas-on-window-resize-event

class GameObject {
	protected name: string;
	protected id: string;
	protected buffer: any;
	protected collision: boolean;
	public static: boolean;
	public x: number;
	public y: number;
	public dx: number;
	public dy: number;
	public r: number;

	public constructor(name: string) {
		this.name = name;
		this.id = "object_" + Math.floor(random(0, 1e8));
		this.buffer = {};

		this.collision = false;
		this.static = true;
	}

	/**
	 * Renders a gameobject to a target canvas.
	 * @abstract
	 * @return {void}
	 */
	render(targetCanvas: Canvas): void {
		throw new Error("must be implemented by subclass!");
	}
	/**
	 * move is an abstract function. It moves the object
	 * a specific amount of X and Y each frame modified
	 * with FPS (Time.deltaTime)
	 * @param {number, Vector} amountX: the amount of x to move the object
	 * @param {number} amountY: the amount of y to move the object
	 * @returns {void}
	 */
	move(amountX: any, amountY: number): void {
		// If the argument passed is a vector
		if (amountX instanceof Vector) {
			const tempVector = amountX;
			amountX = tempVector.x;
			amountY = tempVector.y;
		}

		if (this.static) {
			this.x += amountX * Time.deltaTime;
			this.y += amountY * Time.deltaTime;
		} else {
			throw new Error(
				`Cannot move ${this.name} (id: ${this.id}) because object is imstatic`
			);
		}
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

	collides(other: GameObject): boolean {
		if (!this.collision || !other.collision) {
			// Warn user that he is trying to use collision while turned off on objects
			for (const element of Logger.allLoggers) {
				if (Logger.collisionWarn && Logger.maxCollisionWarn <= 10) {
					element.warn(
						"Attemting to evaluat collision on disableCollision objects. Use Logger.disableCollisionWarn() if you wish to hide this message"
					);
					Logger.maxCollisionWarn += 1;
				}
			}

			if (Logger.allLoggers.length == 0) {
				throw new Error("No valid instance of Logger was found");
			}
			return;
		}

		if (this instanceof Circle && other instanceof Circle) {
			return distance(this.x, this.y, other.x, other.y) <= this.r + other.r;
		} else if (this instanceof Circle && other instanceof Rectangle) {
			const hitbox = new Rectangle(this.x, this.y, this.r * 2, this.r * 2);

			return (
				hitbox.x + hitbox.w >= other.x &&
				hitbox.x <= other.x + other.w &&
				hitbox.y + hitbox.h >= other.y &&
				hitbox.y <= other.y + other.h
			);
		} else if (this instanceof Rectangle && other instanceof Circle) {
			const hitbox = new Rectangle(other.x, other.y, other.r * 2, other.r * 2);

			return (
				this.x + this.w >= hitbox.x &&
				this.x <= hitbox.x + hitbox.w &&
				this.y + this.h >= hitbox.y &&
				this.y <= hitbox.y + hitbox.h
			);
		} else if (this instanceof Rectangle && other instanceof Rectangle) {
			return (
				this.x + this.w >= other.x &&
				this.x <= other.x + other.w &&
				this.y + this.h >= other.y &&
				this.y <= other.y + other.h
			);
		} else if (this instanceof Circle && other instanceof Vertex) {
			return distance(this.x, this.y, other.x, other.y) <= this.r;
		} else if (this instanceof Rectangle && other instanceof Vertex) {
			return (
				other.x > this.x &&
				other.x < this.x + this.w &&
				other.y > this.y &&
				other.y < this.y + this.h
			);
		}
	}

	equals(other: GameObject) {
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

class Canvas extends GameObject {
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

class Circle extends GameObject {
	public x: number;
	public y: number;
	public r: any;
	public fill: string;
	public stroke: string;
	public lineWidth: number;
	public static: boolean;

	public constructor(x: number, y: number, r: any) {
		super("circle");
		this.x = x;
		this.y = y;
		this.r = r;
		this.fill = "white";
		this.stroke = "black";
		this.lineWidth = 1;
		this.static = true;
	}

	render(targetCanvas: Canvas): void {
		// Handle no Canvas error
		if (!targetCanvas) {
			throw new Error(
				"Connot render " +
					this.name +
					" on a non specific Canvas. Must provide Canvas object in argument of Object.render()"
			);
		}

		// Handle no Corrdinates error
		if (
			this.x == undefined ||
			this.x == null ||
			isNaN(this.x) ||
			this.y == undefined ||
			this.y == null ||
			isNaN(this.y)
		) {
			throw new Error(
				`${this.name} (${this.id}) X and/or coordinate is/are either undefined, null or NaN.`
			);
		}

		targetCanvas.ctx.beginPath();
		targetCanvas.ctx.arc(this.x, this.y, this.r, 0, Math.PI * 2, false);
		targetCanvas.ctx.fillStyle = this.fill;
		targetCanvas.ctx.strokeStyle = this.stroke;
		targetCanvas.ctx.lineWidth = this.lineWidth;
		targetCanvas.ctx.fill();
		targetCanvas.ctx.stroke();
	}

	hover(): boolean {
		let d = Math.sqrt(
			Math.pow(mouse.x - this.x, 2) + Math.pow(mouse.y - this.y, 2)
		);
		if (d <= this.r) {
			return true;
		}
	}

	clicked(): boolean {
		/*let d = distance(this.x, this.y, lastClick.x, lastClick.y);
		if (d <= this.r) {
			return true;
		}*/
		throw new Error("LastClick is not coded yet!");
	}

	area(): number {
		return Math.PI * Math.pow(this.r, 2);
	}

	setFill(newFill: string): void {
		this.fill = newFill;
	}

	setStroke(newStroke: string): void {
		this.stroke = newStroke;
	}

	setLineWidth(newLineWidth: number): void {
		this.lineWidth = newLineWidth;
	}
}

class Rectangle extends GameObject {
	public x: number;
	public y: number;
	public w: any;
	public h: any;
	public fill: string;
	public stroke: string;
	public lineWidth: number;
	public staitc: boolean;

	public constructor(x: number, y: number, w: any, h: any) {
		super("rectangle");
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.fill = "white";
		this.stroke = "black";
		this.lineWidth = 1;
		this.static = true;
	}

	render(targetCanvas: Canvas): void {
		// Handle no Canvas error
		if (!targetCanvas) {
			throw new Error(
				"Connot render " +
					this.name +
					" on a non specific Canvas. Must provide Canvas object in argument of Object.render()"
			);
		}

		// Handle no Corrdinates error
		if (
			this.x == undefined ||
			this.x == null ||
			isNaN(this.x) ||
			this.y == undefined ||
			this.y == null ||
			isNaN(this.y)
		) {
			throw new Error(
				`${this.name} (${this.id}) X and/or coordinate is/are either undefined, null or NaN.`
			);
		}

		targetCanvas.ctx.beginPath();
		targetCanvas.ctx.rect(this.x, this.y, this.w, this.h);
		targetCanvas.ctx.fillStyle = this.fill;
		targetCanvas.ctx.strokeStyle = this.stroke;
		targetCanvas.ctx.lineWidth = this.lineWidth;
		targetCanvas.ctx.fill();
		targetCanvas.ctx.stroke();
	}

	hover(): boolean {
		// is mouse to right of the left-side of the rectangle
		// is mouse to left of the right-side of the rectangle
		// is mouse below the top of the rectangle
		// is mouse above the bottom of the rectangle
		if (
			mouse.x > this.x &&
			mouse.x < this.x + this.w &&
			mouse.y > this.y &&
			mouse.y < this.y + this.h
		) {
			return true;
		}
	}

	clicked(): boolean {
		/*if (
			lastClick.x > this.x &&
			lastClick.x < this.x + this.w &&
			lastClick.y > this.y &&
			lastClick.y < this.y + this.h
		) {
			return true;
		}*/
		throw new Error("LastClick has not been coded yet! @ Rectangle");
	}

	area(): number {
		return this.w * this.h;
	}

	setFill(newFill: string): void {
		this.fill = newFill;
	}

	setStroke(newStroke: string): void {
		this.stroke = newStroke;
	}

	setLineWidth(newLineWidth: number): void {
		this.lineWidth = newLineWidth;
	}
}

class Vertex extends GameObject {
	public x: number;
	public y: number;

	public constructor(x: number, y: number) {
		super("vertex");
		this.x = x;
		this.y = y;
	}
}

class ShadoText extends GameObject {
	public text: string;
	public x: number;
	public y: number;
	public font: string;
	public size: number;
	public color: string;
	public stroke: string;
	public background: string;
	public fullStyle: string;
	public hitBox: Rectangle;

	public constructor(
		text: string,
		x: number,
		y: number,
		{
			font,
			size,
			color,
			stroke,
			background
		}: {
			font?: string;
			size?: number;
			color?: string;
			stroke?: string;
			background?: string;
		}
	) {
		super("text");
		this.text = text;
		this.x = x;
		this.y = y;
		this.font = font || "sans-serif";
		this.size = size || 14;
		this.color = color || "black";
		this.stroke = stroke || this.color;
		this.background = background || "transparent";
		this.static = true;
		this.fullStyle = `${this.size}px ${this.font}`;
		this.hitBox = null;
	}

	render(targetCanvas: Canvas): void {
		// Handle no canvas error
		if (!targetCanvas) {
			throw new Error(
				"Connot render " +
					this.name +
					" on a non specific Canvas. Must provide Canvas object in argument of Object.render()"
			);
		}

		// Handle no Corrdinates error
		if (
			this.x == undefined ||
			this.x == null ||
			isNaN(this.x) ||
			this.y == undefined ||
			this.y == null ||
			isNaN(this.y)
		) {
			throw new Error(
				`${this.name} (${this.id}) X and/or coordinate is/are either undefined, null or NaN.`
			);
		}

		// Draw text
		targetCanvas.ctx.font = `${this.size}px ${this.font}`;

		// Draw background
		// Draw BG from -10% to +20%
		this.buildHitBox(targetCanvas);

		targetCanvas.ctx.fillStyle = this.color;
		targetCanvas.ctx.strokeStyle = this.stroke;
		targetCanvas.ctx.fillText(this.text, this.x, this.y);
		targetCanvas.ctx.strokeText(this.text, this.x, this.y);
	}

	buildHitBox(targetCanvas: Canvas): void {
		// Hitbox from -10% to +20%
		this.hitBox = new Rectangle(
			this.x - this.width(targetCanvas) * 0.1,
			this.y - this.height(targetCanvas) / 2,
			this.width(targetCanvas) * 1.2,
			this.height(targetCanvas)
		);
		this.hitBox.setFill(this.background);
		this.hitBox.setStroke("transparent");
		this.hitBox.render(targetCanvas);
	}

	width(targetCanvas: Canvas): number {
		targetCanvas.ctx.font = this.fullStyle;
		return targetCanvas.ctx.measureText(this.text).width;
	}

	height(targetCanvas: Canvas): number {
		//return this.size - this.size / 5;
		targetCanvas.ctx.font = this.fullStyle;
		let height = parseInt(targetCanvas.ctx.font.match(/\d+/), 10) * 2;
		return height;
	}

	hover(targetCanvas: Canvas): boolean {
		if (this.hitBox == null) {
			this.buildHitBox(targetCanvas);
		}
		return this.hitBox.hover();
	}

	clicked(targetCanvas: Canvas): boolean {
		if (this.hitBox == null) {
			this.buildHitBox(targetCanvas);
		}
		return this.hitBox.clicked();
	}
}

class ShadoImage extends GameObject {
	public src: string;
	public x: number;
	public y: number;
	public w: any;
	public h: any;
	public id: string;
	public hitBox: Rectangle;
	public showHitBox: boolean;

	public constructor(
		src: string,
		x: number,
		y: number,
		w: any,
		h: any,
		id: string,
		showHitBox: boolean
	) {
		super("image");
		this.src = src;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.id = id;
		this.showHitBox = showHitBox;

		let allImgs = document.getElementById(this.id);

		if (allImgs == undefined || allImgs == null) {
			let body = document.querySelector("body");
			let img = document.createElement("img");
			img.setAttribute("id", this.id);
			img.setAttribute("src", this.src);
			img.setAttribute("style", "display: none");
			body.appendChild(img);
		}

		this.hitBox = new Rectangle(this.x, this.y, this.w, this.h);
		this.hitBox.setStroke("red");
		this.hitBox.setFill("transparent");
	}

	render(targetCanvas: Canvas): void {
		// Handle no Canvas error
		if (!targetCanvas) {
			throw new Error(
				"Connot render " +
					this.name +
					" on a non specific Canvas. Must provide Canvas object in argument of Object.render()"
			);
		}

		// Handle no Corrdinates error
		if (
			this.x == undefined ||
			this.x == null ||
			isNaN(this.x) ||
			this.y == undefined ||
			this.y == null ||
			isNaN(this.y)
		) {
			throw new Error(
				`${this.name} (${this.id}) X and/or coordinate is/are either undefined, null or NaN.`
			);
		}

		this.hitBox.x = this.x;
		this.hitBox.y = this.y;

		if (this.x + this.w >= 0 && this.x <= targetCanvas.width) {
			let myImage = <HTMLImageElement>document.getElementById(this.id);
			myImage.src = this.src;

			targetCanvas.ctx.drawImage(myImage, this.x, this.y, this.w, this.h);

			if (this.showHitBox) {
				this.hitBox.render(targetCanvas);
			}
		}
	}

	hover(): boolean {
		return this.hitBox.hover();
	}

	clicked(): boolean {
		/*if (lastClick.x > this.x && lastClick.x < (this.x + this.width) && lastClick.y < this.y && lastClick.y > (this.y - this.height))	{
			return true;
		}*/
		/*if (this.hitBox.clicked()) {
			lastClick = { x: undefined, y: undefined };
			return true;
		} else {
			return false;
		}*/
		throw new Error("lastClick has not been defined yet @ Image");
	}

	updateDimensions(newW: any, newH: any): void {
		this.w = newW;
		this.h = newH;
		this.hitBox.w = newW;
		this.hitBox.h = newH;
	}
}

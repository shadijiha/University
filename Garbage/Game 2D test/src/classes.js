/***
 *
 * This file contains the GAME class
 *
 */

class GameObject {
	constructor(name) {
		this.name = name;
		this.id = "object_" + random(0, 1e8);
		this.buffer = {};
	}

	/**
	 * Renders a gameobject to a target canvas.
	 * @abstract
	 * @return {void}
	 */
	render(targetCanvas) {
		throw new Error("must be implemented by subclass!");
	}
	/**
	 * move is an abstract function. It moves the object
	 * a specific amount of X and Y each frame modified
	 * with FPS (Time.deltaTime)
	 * @param {number} amountX: the amount of x to move the object
	 * @param {number} amountY: the amount of y to move the object
	 * @returns {void}
	 */
	move(amountX, amountY) {
		if (this.movable) {
			this.x += amountX * Time.deltaTime;
			this.y += amountY * Time.deltaTime;
		} else {
			throw new Error(
				`Cannot move ${this.name} (id: ${this.id}) because object is immovable`
			);
		}
	}
}

class Canvas extends GameObject {
	constructor(posX, posY, width, height, parent) {
		super("canvas");
		this.x = posX;
		this.y = posY;
		this.w = width;
		this.h = height;
		this.background = "transparent";
		this.canvas = null;
		this.ctx = null;
		this.overwrite = false;
		this.parent = parent || document.querySelector("body");
		this.movable = false;
	}

	render() {
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

	clear(fromX, fromY, toX, toY) {
		fromX = fromX || 0;
		fromY = fromY || 0;
		toX = toX || this.w;
		toY = toY || this.h;

		this.ctx.clearRect(fromX, fromY, toX, toY);
	}

	setPosition(newX, newY) {
		this.x = newX || this.x;
		this.y = newY || this.y;
		this.render();
	}

	setBackground(color) {
		this.background = color;
		this.render();
	}

	setWidth(newWidth) {
		this.w = newWidth;
		this.render();
	}

	setHeight(newHeight) {
		this.h = newHeight;
		this.render();
	}

	toggleOverwrite(booleanValue) {
		if (booleanValue == undefined) this.overwrite = !this.overwrite;
		else this.overwrite = booleanValue;
	}
}

class Circle extends GameObject {
	constructor(x, y, r) {
		super("circle");
		this.x = x;
		this.y = y;
		this.r = r;
		this.fill = "white";
		this.stroke = "black";
		this.lineWidth = 1;
		this.movable = true;
		this.collision = false;
	}

	render(targetCanvas) {
		targetCanvas.ctx.beginPath();
		targetCanvas.ctx.arc(this.x, this.y, this.r, 0, Math.PI * 2, false);
		targetCanvas.ctx.fillStyle = this.fill;
		targetCanvas.ctx.strokeStyle = this.stroke;
		targetCanvas.ctx.lineWidth = this.lineWidth;
		targetCanvas.ctx.fill();
		targetCanvas.ctx.stroke();
	}

	enableCollision() {
		this.collision = true;

		// See if the object exits already int the global.collisionObjects array
		let exist = false;
		for (let element of global.collisionObjects) {
			if (element.id == this.id) {
				exist = true;
				break;
			}
		}

		// Add object if it doesn't exist
		if (!exist) global.collisionObjects.push(this);
	}

	disableCollision() {
		this.collision = false;

		// See if the object exits already int the global.collisionObjects array
		let exist = false;
		for (let element of global.collisionObjects) {
			if (element.id == this.id) {
				exist = true;
				break;
			}
		}

		// Remove the object if it exists
		if (exist) {
			global.collisionObjects.splice(global.collisionObjects.indexOf(this), 1);
		}
	}

	setFill(newFill) {
		this.fill = newFill;
	}

	setStroke(newStroke) {
		this.stroke = newStroke;
	}

	setLineWidth(newLineWidth) {
		this.lineWidth = newLineWidth;
	}
}

class Text extends GameObject {
	constructor(text, x, y, { font, size, color, stroke }) {
		super("text");
		this.text = text;
		this.x = x;
		this.y = y;
		this.font = font || "sans-serif";
		this.size = size || 14;
		this.color = color || "black";
		this.stroke = stroke || this.color;
		this.movable = true;
	}

	render(targetCanvas) {
		targetCanvas.ctx.font = `${this.size}px ${this.font}`;
		targetCanvas.ctx.fillStyle = this.color;
		targetCanvas.ctx.strokeStyle = this.stroke;
		targetCanvas.ctx.fillText(this.text, this.x, this.y);
		targetCanvas.ctx.strokeText(this.text, this.x, this.y);
	}

	enableCollision() {
		this.collision = true;

		// See if the object exits already int the global.collisionObjects array
		let exist = false;
		for (let element of global.collisionObjects) {
			if (element.id == this.id) {
				exist = true;
				break;
			}
		}

		// Add object if it doesn't exist
		if (!exist) global.collisionObjects.push(this);
	}

	disableCollision() {
		this.collision = false;

		// See if the object exits already int the global.collisionObjects array
		let exist = false;
		for (let element of global.collisionObjects) {
			if (element.id == this.id) {
				exist = true;
				break;
			}
		}

		// Remove the object if it exists
		if (exist) {
			global.collisionObjects.splice(global.collisionObjects.indexOf(this), 1);
		}
	}
}

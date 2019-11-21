/***
 *
 * This file contains the GAME class
 *
 */

//https://stackoverflow.com/questions/44849831/responsive-canvas-on-window-resize-event

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
	 * @param {number, Vector} amountX: the amount of x to move the object
	 * @param {number} amountY: the amount of y to move the object
	 * @returns {void}
	 */
	move(amountX, amountY) {
		// If the argument passed is a vector
		if (amountX instanceof Vector) {
			const tempVector = amountX;
			amountX = tempVector.x;
			amountY = tempVector.y;
		}

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
		this.collision = true;
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

	hover() {
		let d = distance(this.x, this.y, mouse.x, mouse.y);
		if (d <= this.r) {
			return true;
		}
	}

	clicked() {
		/*let d = distance(this.x, this.y, lastClick.x, lastClick.y);
		if (d <= this.r) {
			return true;
		}*/
		throw new Error("LastClick is not coded yet!");
	}

	area() {
		return Math.PI * Math.pow(this.r, 2);
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

class Rectangle extends GameObject {
	constructor(x, y, w, h) {
		super("rectangle");
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.fill = "white";
		this.stroke = "black";
		this.lineWidth = 1;
		this.movable = true;
		this.collision = true;
	}

	render(targetCanvas) {
		targetCanvas.ctx.beginPath();
		targetCanvas.ctx.rect(this.x, this.y, this.w, this.h);
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

	hover() {
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

	clicked() {
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

	area() {
		return this.w * this.h;
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
		this.fullStyle = `${this.size}px ${this.font}`;
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

	width(targetCanvas) {
		targetCanvas.ctx.font = this.fullStyle;
		return targetCanvas.ctx.measureText(this.text).width;
	}

	height(targetCanvas) {
		//return this.size - this.size / 5;
		targetCanvas.ctx.font = this.fullStyle;
		let height = parseInt(targetCanvas.ctx.font.match(/\d+/), 10) * 2;
		return height;
	}

	hover(targetCanvas) {
		const hitBox = new Rectangle(
			this.x,
			this.y,
			this.width(targetCanvas),
			this.height(targetCanvas)
		);
		return hitBox.hover();
	}

	clicked(targetCanvas) {
		const hitBox = new Rectangle(
			this.x,
			this.y,
			this.width(targetCanvas),
			this.height(targetCanvas)
		);
		return hitBox.clicked();
	}
}

class Image extends GameObject {
	constructor(src, x, y, w, h, id, showHitBox) {
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

		this.hitBox = new Rectangle(this.x, this.y, this.w, this.h, {
			stroke: "red",
			fill: "transparent"
		});
	}

	draw() {
		this.hitBox.x = this.x;
		this.hitBox.y = this.y;

		if (this.x + this.w >= 0 && this.x <= canvas.width) {
			let myImage = document.getElementById(this.id);
			myImage.src = this.src;

			c.drawImage(myImage, this.x, this.y, this.w, this.h);

			if (this.showHitBox) {
				this.hitBox.draw();
			}
		}
	}

	hover() {
		return this.hitBox.hover();
	}

	clicked() {
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

	updateDimensions(newW, newH) {
		this.w = newW;
		this.h = newH;
		this.hitBox.w = newW;
		this.hitBox.h = newH;
	}
}

/***
 *
 * This file contains the GAME class
 *
 */

//https://stackoverflow.com/questions/44849831/responsive-canvas-on-window-resize-event

class GameObject {
	constructor(name) {
		this.name = name;
		this.id = "object_" + Math.floor(random(0, 1e8));
		this.buffer = {};

		this.collision = false;
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

		if (this.static) {
			this.x += amountX * Time.deltaTime;
			this.y += amountY * Time.deltaTime;
		} else {
			throw new Error(
				`Cannot move ${this.name} (id: ${this.id}) because object is imstatic`
			);
		}
	}

	draw(targetCanvas) {
		this.render(targetCanvas);
	}

	enableCollision() {
		// If the object is an instance of canvas, don't add it
		if (this instanceof Canvas) {
			throw new Error("Cannnot tag CANVAS objects as collidable.");
		}
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

	collides(other) {
		if (this instanceof Circle && other instanceof Circle) {
			let dist = distance(this.x, this.y, other.x, other.y);
			if (dist == 0) {
				console.log(this);
				console.log(other);
				//throw new Error("");
			}
			return dist <= this.r + other.r;
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
		this.width = width;
		this.height = height;
		this.background = "transparent";
		this.canvas = null;
		this.ctx = null;
		this.overwrite = false;
		this.parent = parent || document.querySelector("body");
		this.static = false;
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
		this.width = newWidth;
		this.render();
	}

	setHeight(newHeight) {
		this.h = newHeight;
		this.height = newHeight;
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
		this.static = true;
	}

	render(targetCanvas) {
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

	hover() {
		let d = Math.sqrt(
			Math.pow(mouse.x - this.x, 2) + Math.pow(mouse.y - this.y, 2)
		);
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
		this.static = true;
	}

	render(targetCanvas) {
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
	constructor(text, x, y, { font, size, color, stroke, background }) {
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

	render(targetCanvas) {
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

	buildHitBox(targetCanvas) {
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
		if (this.hitBox == null) {
			buildHitBox(targetCanvas);
		}
		return this.hitBox.hover();
	}

	clicked(targetCanvas) {
		if (this.hitBox == null) {
			buildHitBox(targetCanvas);
		}
		return this.hitBox.clicked();
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

	render() {
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
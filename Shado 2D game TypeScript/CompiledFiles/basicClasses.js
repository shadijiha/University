/***
 *
 * This file contains the GAME class
 *
 */
var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
//https://stackoverflow.com/questions/44849831/responsive-canvas-on-window-resize-event
var GameObject = /** @class */ (function () {
    function GameObject(name) {
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
    GameObject.prototype.render = function (targetCanvas) {
        throw new Error("must be implemented by subclass!");
    };
    /**
     * move is an abstract function. It moves the object
     * a specific amount of X and Y each frame modified
     * with FPS (Time.deltaTime)
     * @param {number, Vector} amountX: the amount of x to move the object
     * @param {number} amountY: the amount of y to move the object
     * @returns {void}
     */
    GameObject.prototype.move = function (amountX, amountY) {
        // If the argument passed is a vector
        if (amountX instanceof Vector) {
            var tempVector = amountX;
            amountX = tempVector.x;
            amountY = tempVector.y;
        }
        if (this.static) {
            this.x += amountX * Time.deltaTime;
            this.y += amountY * Time.deltaTime;
        }
        else {
            throw new Error("Cannot move " + this.name + " (id: " + this.id + ") because object is imstatic");
        }
    };
    GameObject.prototype.draw = function (targetCanvas) {
        this.render(targetCanvas);
    };
    GameObject.prototype.enableCollision = function () {
        // If the object is an instance of canvas, don't add it
        if (this instanceof Canvas) {
            throw new Error("Cannnot tag CANVAS objects as collidable.");
        }
        this.collision = true;
        // See if the object exits already int the EnginGlobal.collisionObjects array
        var exist = false;
        for (var _i = 0, _a = EnginGlobal.collisionObjects; _i < _a.length; _i++) {
            var element = _a[_i];
            if (element.id == this.id) {
                exist = true;
                break;
            }
        }
        // Add object if it doesn't exist
        if (!exist)
            EnginGlobal.collisionObjects.push(this);
    };
    GameObject.prototype.disableCollision = function () {
        this.collision = false;
        // See if the object exits already int the EnginGlobal.collisionObjects array
        var exist = false;
        for (var _i = 0, _a = EnginGlobal.collisionObjects; _i < _a.length; _i++) {
            var element = _a[_i];
            if (element.id == this.id) {
                exist = true;
                break;
            }
        }
        // Remove the object if it exists
        if (exist) {
            EnginGlobal.collisionObjects.splice(EnginGlobal.collisionObjects.indexOf(this), 1);
        }
    };
    GameObject.prototype.collides = function (other) {
        if (this instanceof Circle && other instanceof Circle) {
            var dist = distance(this.x, this.y, other.x, other.y);
            if (dist == 0) {
                console.log(this);
                console.log(other);
                //throw new Error("");
            }
            return dist <= this.r + other.r;
        }
    };
    return GameObject;
}());
var Canvas = /** @class */ (function (_super) {
    __extends(Canvas, _super);
    function Canvas(posX, posY, width, height, parent) {
        var _this = _super.call(this, "canvas") || this;
        _this.x = posX;
        _this.y = posY;
        _this.w = width;
        _this.h = height;
        _this.width = width;
        _this.height = height;
        _this.background = "transparent";
        _this.canvas = null;
        _this.ctx = null;
        _this.overwrite = false;
        _this.parent = parent || document.querySelector("body");
        _this.static = false;
        return _this;
    }
    Canvas.prototype.render = function () {
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
        var DOM = "<canvas id=\"" + this.id + "\" style=\"position: absolute; top: " + this.y + "; left: " + this.x + "; background: " + this.background + ";\" width=\"" + this.w + "\" height=\"" + this.h + "\">";
        // if element doesn't exist add it to the page
        if (!document.getElementById(this.id)) {
            this.parent.innerHTML += DOM;
        }
        else {
            if (this.overwrite) {
                this.parent.removeChild(document.getElementById(this.id));
                this.parent.innerHTML += DOM;
            }
        }
        // Set context variables
        this.canvas = document.getElementById(this.id);
        this.ctx = this.canvas.getContext("2d");
    };
    Canvas.prototype.clear = function (fromX, fromY, toX, toY) {
        fromX = fromX || 0;
        fromY = fromY || 0;
        toX = toX || this.w;
        toY = toY || this.h;
        this.ctx.clearRect(fromX, fromY, toX, toY);
    };
    Canvas.prototype.setPosition = function (newX, newY) {
        this.x = newX || this.x;
        this.y = newY || this.y;
        this.render();
    };
    Canvas.prototype.setBackground = function (color) {
        this.background = color;
        this.render();
    };
    Canvas.prototype.setWidth = function (newWidth) {
        this.w = newWidth;
        this.width = newWidth;
        this.render();
    };
    Canvas.prototype.setHeight = function (newHeight) {
        this.h = newHeight;
        this.height = newHeight;
        this.render();
    };
    Canvas.prototype.toggleOverwrite = function (booleanValue) {
        if (booleanValue == undefined)
            this.overwrite = !this.overwrite;
        else
            this.overwrite = booleanValue;
    };
    return Canvas;
}(GameObject));
var Circle = /** @class */ (function (_super) {
    __extends(Circle, _super);
    function Circle(x, y, r) {
        var _this = _super.call(this, "circle") || this;
        _this.x = x;
        _this.y = y;
        _this.r = r;
        _this.fill = "white";
        _this.stroke = "black";
        _this.lineWidth = 1;
        _this.static = true;
        return _this;
    }
    Circle.prototype.render = function (targetCanvas) {
        // Handle no Canvas error
        if (!targetCanvas) {
            throw new Error("Connot render " +
                this.name +
                " on a non specific Canvas. Must provide Canvas object in argument of Object.render()");
        }
        // Handle no Corrdinates error
        if (this.x == undefined ||
            this.x == null ||
            isNaN(this.x) ||
            this.y == undefined ||
            this.y == null ||
            isNaN(this.y)) {
            throw new Error(this.name + " (" + this.id + ") X and/or coordinate is/are either undefined, null or NaN.");
        }
        targetCanvas.ctx.beginPath();
        targetCanvas.ctx.arc(this.x, this.y, this.r, 0, Math.PI * 2, false);
        targetCanvas.ctx.fillStyle = this.fill;
        targetCanvas.ctx.strokeStyle = this.stroke;
        targetCanvas.ctx.lineWidth = this.lineWidth;
        targetCanvas.ctx.fill();
        targetCanvas.ctx.stroke();
    };
    Circle.prototype.hover = function () {
        var d = Math.sqrt(Math.pow(mouse.x - this.x, 2) + Math.pow(mouse.y - this.y, 2));
        if (d <= this.r) {
            return true;
        }
    };
    Circle.prototype.clicked = function () {
        /*let d = distance(this.x, this.y, lastClick.x, lastClick.y);
        if (d <= this.r) {
            return true;
        }*/
        throw new Error("LastClick is not coded yet!");
    };
    Circle.prototype.area = function () {
        return Math.PI * Math.pow(this.r, 2);
    };
    Circle.prototype.setFill = function (newFill) {
        this.fill = newFill;
    };
    Circle.prototype.setStroke = function (newStroke) {
        this.stroke = newStroke;
    };
    Circle.prototype.setLineWidth = function (newLineWidth) {
        this.lineWidth = newLineWidth;
    };
    return Circle;
}(GameObject));
var Rectangle = /** @class */ (function (_super) {
    __extends(Rectangle, _super);
    function Rectangle(x, y, w, h) {
        var _this = _super.call(this, "rectangle") || this;
        _this.x = x;
        _this.y = y;
        _this.w = w;
        _this.h = h;
        _this.fill = "white";
        _this.stroke = "black";
        _this.lineWidth = 1;
        _this.static = true;
        return _this;
    }
    Rectangle.prototype.render = function (targetCanvas) {
        // Handle no Canvas error
        if (!targetCanvas) {
            throw new Error("Connot render " +
                this.name +
                " on a non specific Canvas. Must provide Canvas object in argument of Object.render()");
        }
        // Handle no Corrdinates error
        if (this.x == undefined ||
            this.x == null ||
            isNaN(this.x) ||
            this.y == undefined ||
            this.y == null ||
            isNaN(this.y)) {
            throw new Error(this.name + " (" + this.id + ") X and/or coordinate is/are either undefined, null or NaN.");
        }
        targetCanvas.ctx.beginPath();
        targetCanvas.ctx.rect(this.x, this.y, this.w, this.h);
        targetCanvas.ctx.fillStyle = this.fill;
        targetCanvas.ctx.strokeStyle = this.stroke;
        targetCanvas.ctx.lineWidth = this.lineWidth;
        targetCanvas.ctx.fill();
        targetCanvas.ctx.stroke();
    };
    Rectangle.prototype.hover = function () {
        // is mouse to right of the left-side of the rectangle
        // is mouse to left of the right-side of the rectangle
        // is mouse below the top of the rectangle
        // is mouse above the bottom of the rectangle
        if (mouse.x > this.x &&
            mouse.x < this.x + this.w &&
            mouse.y > this.y &&
            mouse.y < this.y + this.h) {
            return true;
        }
    };
    Rectangle.prototype.clicked = function () {
        /*if (
            lastClick.x > this.x &&
            lastClick.x < this.x + this.w &&
            lastClick.y > this.y &&
            lastClick.y < this.y + this.h
        ) {
            return true;
        }*/
        throw new Error("LastClick has not been coded yet! @ Rectangle");
    };
    Rectangle.prototype.area = function () {
        return this.w * this.h;
    };
    Rectangle.prototype.setFill = function (newFill) {
        this.fill = newFill;
    };
    Rectangle.prototype.setStroke = function (newStroke) {
        this.stroke = newStroke;
    };
    Rectangle.prototype.setLineWidth = function (newLineWidth) {
        this.lineWidth = newLineWidth;
    };
    return Rectangle;
}(GameObject));
var Text = /** @class */ (function (_super) {
    __extends(Text, _super);
    function Text(text, x, y, _a) {
        var font = _a.font, size = _a.size, color = _a.color, stroke = _a.stroke, background = _a.background;
        var _this = _super.call(this, "text") || this;
        _this.text = text;
        _this.x = x;
        _this.y = y;
        _this.font = font || "sans-serif";
        _this.size = size || 14;
        _this.color = color || "black";
        _this.stroke = stroke || _this.color;
        _this.background = background || "transparent";
        _this.static = true;
        _this.fullStyle = _this.size + "px " + _this.font;
        _this.hitBox = null;
        return _this;
    }
    Text.prototype.render = function (targetCanvas) {
        // Handle no canvas error
        if (!targetCanvas) {
            throw new Error("Connot render " +
                this.name +
                " on a non specific Canvas. Must provide Canvas object in argument of Object.render()");
        }
        // Handle no Corrdinates error
        if (this.x == undefined ||
            this.x == null ||
            isNaN(this.x) ||
            this.y == undefined ||
            this.y == null ||
            isNaN(this.y)) {
            throw new Error(this.name + " (" + this.id + ") X and/or coordinate is/are either undefined, null or NaN.");
        }
        // Draw text
        targetCanvas.ctx.font = this.size + "px " + this.font;
        // Draw background
        // Draw BG from -10% to +20%
        this.buildHitBox(targetCanvas);
        targetCanvas.ctx.fillStyle = this.color;
        targetCanvas.ctx.strokeStyle = this.stroke;
        targetCanvas.ctx.fillText(this.text, this.x, this.y);
        targetCanvas.ctx.strokeText(this.text, this.x, this.y);
    };
    Text.prototype.buildHitBox = function (targetCanvas) {
        // Hitbox from -10% to +20%
        this.hitBox = new Rectangle(this.x - this.width(targetCanvas) * 0.1, this.y - this.height(targetCanvas) / 2, this.width(targetCanvas) * 1.2, this.height(targetCanvas));
        this.hitBox.setFill(this.background);
        this.hitBox.setStroke("transparent");
        this.hitBox.render(targetCanvas);
    };
    Text.prototype.width = function (targetCanvas) {
        targetCanvas.ctx.font = this.fullStyle;
        return targetCanvas.ctx.measureText(this.text).width;
    };
    Text.prototype.height = function (targetCanvas) {
        //return this.size - this.size / 5;
        targetCanvas.ctx.font = this.fullStyle;
        var height = parseInt(targetCanvas.ctx.font.match(/\d+/), 10) * 2;
        return height;
    };
    Text.prototype.hover = function (targetCanvas) {
        if (this.hitBox == null) {
            buildHitBox(targetCanvas);
        }
        return this.hitBox.hover();
    };
    Text.prototype.clicked = function (targetCanvas) {
        if (this.hitBox == null) {
            buildHitBox(targetCanvas);
        }
        return this.hitBox.clicked();
    };
    return Text;
}(GameObject));
var Image = /** @class */ (function (_super) {
    __extends(Image, _super);
    function Image(src, x, y, w, h, id, showHitBox) {
        var _this = _super.call(this, "image") || this;
        _this.src = src;
        _this.x = x;
        _this.y = y;
        _this.w = w;
        _this.h = h;
        _this.id = id;
        _this.showHitBox = showHitBox;
        var allImgs = document.getElementById(_this.id);
        if (allImgs == undefined || allImgs == null) {
            var body = document.querySelector("body");
            var img = document.createElement("img");
            img.setAttribute("id", _this.id);
            img.setAttribute("src", _this.src);
            img.setAttribute("style", "display: none");
            body.appendChild(img);
        }
        _this.hitBox = new Rectangle(_this.x, _this.y, _this.w, _this.h, {
            stroke: "red",
            fill: "transparent"
        });
        return _this;
    }
    Image.prototype.render = function () {
        // Handle no Canvas error
        if (!targetCanvas) {
            throw new Error("Connot render " +
                this.name +
                " on a non specific Canvas. Must provide Canvas object in argument of Object.render()");
        }
        // Handle no Corrdinates error
        if (this.x == undefined ||
            this.x == null ||
            isNaN(this.x) ||
            this.y == undefined ||
            this.y == null ||
            isNaN(this.y)) {
            throw new Error(this.name + " (" + this.id + ") X and/or coordinate is/are either undefined, null or NaN.");
        }
        this.hitBox.x = this.x;
        this.hitBox.y = this.y;
        if (this.x + this.w >= 0 && this.x <= canvas.width) {
            var myImage = document.getElementById(this.id);
            myImage.src = this.src;
            c.drawImage(myImage, this.x, this.y, this.w, this.h);
            if (this.showHitBox) {
                this.hitBox.draw();
            }
        }
    };
    Image.prototype.hover = function () {
        return this.hitBox.hover();
    };
    Image.prototype.clicked = function () {
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
    };
    Image.prototype.updateDimensions = function (newW, newH) {
        this.w = newW;
        this.h = newH;
        this.hitBox.w = newW;
        this.hitBox.h = newH;
    };
    return Image;
}(GameObject));

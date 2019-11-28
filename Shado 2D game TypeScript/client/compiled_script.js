/***
 *
 * Utility functions
 */
// Call this function like so:
// await sleep(time in ms);
function sleep(ms) {
    return new Promise(function (resolve) { return setTimeout(resolve, ms); });
}
function random(min, max) {
    if (max == undefined || max == null) {
        max = min;
        min = 0;
    }
    return Math.random() * (max - min) + min;
}
function distance(x1, y1, x2, y2) {
    return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
}
function pause() {
    global.oldFPS = global.FPS;
    global.FPS = 0;
    global.PAUSED = true;
}
function resume() {
    global.FPS = global.oldFPS;
    global.PAUSED = false;
    gameLoop();
}
/**
 *
 * Events
 *
 */
var mouse = {
    x: undefined,
    y: undefined
};
window.addEventListener("mousemove", function (event) {
    mouse.x = event.x;
    mouse.y = event.y;
});
"use strict";
/***
 *
 * Shado MATRIX and VECTOR Library
 *
 */
var Matrix = /** @class */ (function () {
    function Matrix(rows, cols) {
        this.rows = rows || 2;
        this.cols = cols || this.rows;
        this.data = [];
        // Initialize matrix with 0s
        for (var i = 0; i < this.rows; i++) {
            this.data[i] = [];
            for (var j = 0; j < this.cols; j++) {
                this.data[i][j] = 0;
            }
        }
    }
    Matrix.prototype.setCell = function (row, col, value) {
        this.data[row][col] = value;
    };
    Matrix.prototype.setMatrix = function (array) {
        this.data = array;
        this.rows = array.length;
        this.cols = array[0].length;
    };
    Matrix.prototype.getData = function (row, col) {
        return this.data[row][col];
    };
    Matrix.prototype.randomize = function (max) {
        max = Number(max) || 10;
        // Fill matrix with random digits
        for (var i = 0; i < this.rows; i++) {
            for (var j = 0; j < this.cols; j++) {
                this.data[i][j] = Math.floor(Math.random() * max);
            }
        }
    };
    Matrix.prototype.identity = function () {
        if (this.rows != this.cols) {
            console.log("Error! Connot transform current matrix to Identity matrix because rows and colums count doesn't match.");
            return;
        }
        for (var i = 0; i < this.rows; i++) {
            for (var j = 0; j < this.cols; j++) {
                if (i == j) {
                    this.data[i][j] = 1;
                }
                else {
                    this.data[i][j] = 0;
                }
            }
        }
    };
    Matrix.prototype.add = function (n, overwrite) {
        overwrite = overwrite || true;
        if (n instanceof Matrix) {
            // Detect errors
            if (this.cols != n.cols || this.rows != n.rows) {
                console.log("Error! Connot proform Matrix sum operation on matrices with diffrent rows and/or colums count.");
                return;
            }
            // Sum
            var temp = new Matrix(this.rows, this.cols);
            for (var i = 0; i < temp.rows; i++) {
                for (var j = 0; j < temp.cols; j++) {
                    temp.data[i][j] = this.data[i][j] + n.data[i][j];
                }
            }
            // Update current Matrix or return new one
            if (overwrite) {
                this.data = temp.data;
            }
            else {
                return temp;
            }
        }
        else if (!isNaN(Number(n))) {
            for (var i = 0; i < this.rows; i++) {
                for (var j = 0; j < this.cols; j++) {
                    this.data[i][j] += n;
                }
            }
        }
        else {
            console.log("Error! Connot proform Matrix sum because passed argument is neither a %c number %c nor a %c matrix.", "color: red; font-weight: bold", "", "color: blue; font-weight: bold");
            return;
        }
    };
    Matrix.prototype.multiply = function (n, overwrite) {
        overwrite = overwrite || true;
        if (n instanceof Matrix) {
            // Detect error
            if (this.cols != n.rows) {
                console.log("Error! Connot proform Matrix multiplication operation on matrices because Matrix A colums is not equal to Matrix B rows.");
                return;
            }
            var temp = new Matrix(this.rows, n.cols);
            for (var i = 0; i < temp.rows; i++) {
                for (var j = 0; j < temp.cols; j++) {
                    var sum = 0;
                    for (var k = 0; k < this.cols; k++) {
                        sum += this.data[i][k] * n.data[k][j];
                    }
                    temp.data[i][j] = sum;
                }
            }
            // Update current Matrix
            if (overwrite) {
                this.data = temp.data;
            }
            else {
                return temp;
            }
        }
        else if (!isNaN(Number(n))) {
            for (var i = 0; i < this.rows; i++) {
                for (var j = 0; j < this.cols; j++) {
                    this.data[i][j] *= n;
                }
            }
        }
        else {
            console.log("Error! Connot proform Matrix multiplication because passed argument is neither a %c number %c nor a %c matrix.", "color: red; font-weight: bold", "", "color: blue; font-weight: bold");
            return;
        }
    };
    Matrix.prototype.transpose = function () {
        var temp = new Matrix(this.cols, this.rows);
        for (var i = 0; i < temp.rows; i++) {
            for (var j = 0; j < temp.cols; j++) {
                temp.data[i][j] = this.data[j][i];
            }
        }
        this.data = temp.data;
    };
    Matrix.prototype.LUdecomposition = function () {
        var U = new Matrix(this.rows, this.cols);
        var L = new Matrix(this.rows, this.cols);
        for (var i = 0; i < this.rows; i++) {
            for (var j = 0; j < this.cols; j++) {
                var sum = 0;
                for (var k = 0; k <= i - 1; k++) {
                    sum = sum + L.data[j][k] * U.data[k][i];
                }
                if (i === j) {
                    U.data[i][j] = 1;
                }
                if (i <= j) {
                    L.data[j][i] = this.data[j][i] - sum;
                }
                else {
                    U.data[j][i] = (1 / L.data[j][j]) * (this.data[j][i] - sum);
                }
            }
        }
        return [L, U];
    };
    Matrix.prototype.determinant = function () {
        // Detect Error
        if (this.rows != this.cols) {
            console.log("Error! This matrix has diffrent row and colums count. Thus, the determinant cannot be calculated.");
            return;
        }
        var decomposition = this.LUdecomposition();
        var temp = decomposition[0];
        var mult = 1;
        for (var i = 0; i < temp.rows; i++) {
            for (var j = 0; j < temp.cols; j++) {
                if (i == j) {
                    mult *= temp.data[i][j];
                }
            }
        }
        return Math.floor(mult);
    };
    Matrix.prototype.log = function () {
        console.table(this.data);
    };
    return Matrix;
}());
var Vector = /** @class */ (function () {
    function Vector(x, y, z) {
        this.x = x;
        this.y = y;
        this.z = z || 0;
    }
    Vector.prototype.random2D = function () {
        this.x = Math.random();
        this.y = Math.random();
    };
    Vector.prototype.random3D = function () {
        this.random2D();
        this.z = Math.random();
    };
    Vector.prototype.getX = function () {
        return this.x;
    };
    Vector.prototype.getY = function () {
        return this.y;
    };
    Vector.prototype.getZ = function () {
        return this.z;
    };
    Vector.prototype.add = function (objVector) {
        this.x += objVector.x;
        this.y += objVector.y;
        this.z += objVector.z;
    };
    Vector.prototype.substract = function (objVector) {
        this.x -= objVector.x;
        this.y -= objVector.y;
        this.z -= objVector.z;
    };
    Vector.prototype.multiply = function (k) {
        this.x *= k;
        this.y *= k;
        this.z *= k;
    };
    Vector.prototype.scale = function (k) {
        this.multiply(k);
    };
    Vector.prototype.mag = function () {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    };
    Vector.prototype.normalize = function () {
        var TEMP = this.mag();
        this.x = this.x / TEMP;
        this.y = this.y / TEMP;
        this.z = this.z / TEMP;
    };
    Vector.prototype.project = function (objVector) {
        var CONSTANT = this.dotProduct(objVector) / Math.pow(objVector.mag(), 2);
        var result = new Vector(objVector.x, objVector.y, objVector.z);
        result.multiply(CONSTANT);
        return result;
    };
    Vector.prototype.dotProduct = function (objVector) {
        return this.x * objVector.x + this.y * objVector.y + this.z * objVector.z;
    };
    Vector.prototype.crossProduct = function (objVector) {
        var i = this.y * objVector.z - this.z * objVector.y;
        var j = -(this.x * objVector.z - this.z * objVector.x);
        var k = this.x * objVector.y - this.y * objVector.x;
        return new Vector(i, j, k);
    };
    return Vector;
}());
var Complex = /** @class */ (function () {
    function Complex(a, b) {
        this.a = a || 0;
        this.b = b || 0;
        this.phi = Math.atan(b / a);
        this.r = Math.sqrt(a * a + b * b);
    }
    Complex.prototype.fromPolar = function (r, phi) {
        this.phi = phi;
        this.r = r;
        this.a = r * Math.cos(phi);
        this.b = r * Math.sin(phi);
    };
    Complex.prototype.equals = function (complexNum) {
        if (this.a == complexNum.a && this.b == complexNum.b) {
            return true;
        }
        else {
            return false;
        }
    };
    Complex.prototype.render = function (arg) {
        if (arg != undefined && arg.toLowerCase() == "polar") {
            return this.r + " * ( cos(" + this.phi + ") + i sin(" + this.phi + ") )";
        }
        else {
            return this.a + " + " + this.b + " i";
        }
    };
    Complex.prototype.sum = function (numB) {
        return new Complex(this.a + numB.a, this.b + numB.b);
    };
    Complex.prototype.substract = function (numB) {
        return new Complex(this.a - numB.a, this.b - numB.b);
    };
    Complex.prototype.multiply = function (numB) {
        var result = new Complex();
        result.fromPolar(this.r * numB.r, this.phi + numB.phi);
        return result;
    };
    Complex.prototype.divide = function (numB) {
        var result = new Complex();
        result.fromPolar(this.r / numB.r, this.phi - numB.phi);
        return result;
    };
    Complex.prototype.conjugate = function () {
        return new Complex(this.a, -1 * this.b);
    };
    Complex.prototype.power = function (exposant) {
        var result = new Complex();
        result.fromPolar(Math.pow(this.r, exposant), this.phi * exposant);
        return result;
    };
    Complex.prototype.root = function (degree) {
        var result = [];
        var tempR = Math.pow(this.r, 1 / degree);
        for (var i = 0; i < degree; i++) {
            var tempPhi = (this.phi + 2 * Math.PI * i) / degree;
            var temp = new Complex();
            temp.fromPolar(tempR, tempPhi);
            result.push(temp);
        }
        return result;
    };
    Complex.prototype.renderRoot = function (degree) {
        var roots = this.root(degree);
        var str = "";
        for (var _i = 0, roots_1 = roots; _i < roots_1.length; _i++) {
            var root = roots_1[_i];
            str += root.render();
            str += "\n";
        }
        return str;
    };
    Complex.prototype.realPart = function () {
        return this.a;
    };
    Complex.prototype.imaginaryPart = function () {
        return this.b;
    };
    Complex.prototype.mag = function () {
        return this.r;
    };
    Complex.prototype.log = function (arg) {
        console.log(this.render(arg));
    };
    return Complex;
}());
/***
 *
 * This file contains all the EnginGlobal variables used in the game
 * This file contains @Gameobject super class and @Time and @EnginGlobal namespaces
 *
 */
var Namespace = /** @class */ (function () {
    function Namespace(name) {
        this.name = name;
        this.id = random(0, 1e6);
    }
    return Namespace;
}());
// Setup EnginGlobal Variables
var EnginGlobal = new Namespace("EnginEnginGlobal");
EnginGlobal.EnginGlobalBuffer = {};
EnginGlobal.FPS = 244;
EnginGlobal.setFPS = function (newFPS) {
    EnginGlobal.FPS = newFPS;
};
EnginGlobal.collisionObjects = [];
var Time = new Namespace("time");
Time.deltaTime = 1000 / EnginGlobal.FPS;
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
/***
 * This file contais the render function that will go
 * to the game loop
 *
 */
// Setup main canvas
var canvas = new Canvas(0, 0, window.innerWidth, window.innerHeight);
canvas.setBackground("#191970"); // Render is implicitly called
// Test
var snow = [];
for (var i = 0; i < 2; i++) {
    var temp = new Circle(random(0, canvas.width), random(0, canvas.height), random(1, 50));
    temp.enableCollision();
    temp.dx = random(0.01, 0.05);
    temp.dy = random(0, 0.02);
    temp.setFill("white");
    snow.push(temp);
}
// For game Loop see "index.js"
function render() {
    // Clear canvas
    canvas.clear();
    // Show FPS
    new Text((1000 / Time.deltaTime).toFixed(2), 100, 100, {
        size: 70,
        color: "white"
    }).render(canvas);
    // SHow pause/Resume Text
    var pauseText = new Text("Abort", 400, 100, {
        size: 20,
        background: "black",
        color: "white"
    });
    pauseText.render(canvas);
    if (pauseText.hover(canvas)) {
        pauseText.text = "Game loop exited";
        pause();
    }
    // Draw stuff
    for (var _i = 0, snow_1 = snow; _i < snow_1.length; _i++) {
        var temp = snow_1[_i];
        temp.move(temp.dx, temp.dy);
        if (temp.x + temp.r > canvas.width) {
            temp.x = -random(100);
        }
        if (temp.y > canvas.height) {
            temp.y = -random(100);
        }
        if (temp.x > -temp.r &&
            temp.x < canvas.width &&
            temp.y > -temp.r &&
            temp.y < canvas.height) {
            for (var _a = 0, snow_2 = snow; _a < snow_2.length; _a++) {
                var other = snow_2[_a];
                if (temp.collides(other)) {
                    temp.setFill("green");
                    break;
                }
            }
            temp.render(canvas);
            temp.setFill("white");
        }
    }
}
/***
 *
 * Main JS file all starts from here
 *
 */
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (_) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
/***********************
 ****** GAME LOOP ******
 **********************/
function gameLoop() {
    return __awaiter(this, void 0, void 0, function () {
        var t1, t2;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    t1 = new Date().getTime();
                    _a.label = 1;
                case 1:
                    if (!(1 == 1)) return [3 /*break*/, 3];
                    // Render everything here
                    render();
                    t2 = new Date().getTime();
                    Time.deltaTime = t2 - t1;
                    t1 = new Date().getTime();
                    // Sleep for the target FPS
                    // Detect if FPS = 0
                    if (EnginGlobal.FPS == 0) {
                        console.log("Game loop has been exited");
                        return [3 /*break*/, 3];
                    }
                    return [4 /*yield*/, sleep(1000 / EnginGlobal.FPS)];
                case 2:
                    _a.sent();
                    return [3 /*break*/, 1];
                case 3: return [2 /*return*/];
            }
        });
    });
}
gameLoop();

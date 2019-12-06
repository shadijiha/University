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
function floor(number) {
    return Math.floor(number);
}
function randomColor() {
    return "rgb(" + floor(random(0, 255)) + ", " + floor(random(0, 255)) + ", " + floor(random(0, 255)) + ")";
}
function distance(x1, y1, x2, y2) {
    return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
}
function pause() {
    EnginGlobal.oldFPS = EnginGlobal.FPS;
    EnginGlobal.FPS = 0;
    EnginGlobal.PAUSED = true;
}
function resume() {
    EnginGlobal.FPS = EnginGlobal.oldFPS;
    EnginGlobal.PAUSED = false;
    gameLoop();
}
var Matrix = (function () {
    function Matrix(rows, cols) {
        this.rows = rows || 2;
        this.cols = cols || this.rows;
        this.data = [];
        for (var i = 0; i < this.rows; i++) {
            this.data[i] = [];
            for (var j = 0; j < this.cols; j++) {
                this.data[i][j] = 0;
            }
        }
    }
    Matrix.prototype.setData = function (row, col, value) {
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
            if (this.cols != n.cols || this.rows != n.rows) {
                console.log("Error! Connot proform Matrix sum operation on matrices with diffrent rows and/or colums count.");
                return;
            }
            var temp = new Matrix(this.rows, this.cols);
            for (var i = 0; i < temp.rows; i++) {
                for (var j = 0; j < temp.cols; j++) {
                    temp.data[i][j] = this.data[i][j] + n.data[i][j];
                }
            }
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
    Matrix.mult4x4WithVector = function (i, m) {
        var outputX = i.x * m.data[0][0] +
            i.y * m.data[1][0] +
            i.z * m.data[2][0] +
            m.data[3][0];
        var outputY = i.x * m.data[0][1] +
            i.y * m.data[1][1] +
            i.z * m.data[2][1] +
            m.data[3][1];
        var outputZ = i.x * m.data[0][2] +
            i.y * m.data[1][2] +
            i.z * m.data[2][2] +
            m.data[3][2];
        var w = i.x * m.data[0][3] +
            i.y * m.data[1][3] +
            i.z * m.data[2][3] +
            m.data[3][3];
        if (w != 0.0) {
            return new Vector(outputX / w, outputY / w, outputZ / w);
        }
        else {
            return new Vector(0, 0, 0);
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
var Vector = (function () {
    function Vector(x, y, z) {
        this.x = x;
        this.y = y;
        this.z = z || 0;
    }
    Vector.prototype.random2D = function (max) {
        max = max || 1;
        this.x = Math.random() * max;
        this.y = Math.random() * max;
    };
    Vector.prototype.random3D = function (max) {
        max = max || 1;
        this.random2D(max);
        this.z = Math.random() * max;
    };
    Vector.prototype.floor = function () {
        this.x = Math.floor(this.x);
        this.y = Math.floor(this.y);
        this.z = Math.floor(this.z);
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
        return new Vector(this.x + objVector.x, this.y + objVector.y, this.z + objVector.z);
    };
    Vector.prototype.substract = function (objVector) {
        return new Vector(this.x - objVector.x, this.y - objVector.y, this.z - objVector.z);
    };
    Vector.prototype.multiply = function (k) {
        if (k instanceof Vector) {
            return this.crossProduct(k);
        }
        else if (!isNaN(k)) {
            var copy = new Vector(this.x, this.y, this.x);
            copy.scale(k);
            return copy;
        }
    };
    Vector.prototype.scale = function (k) {
        this.x *= k;
        this.y *= k;
        this.z *= k;
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
var Complex = (function () {
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
"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var Namespace = (function () {
    function Namespace(name) {
        this.name = name;
        this.id = random(0, 1e6);
    }
    return Namespace;
}());
var Logger = (function () {
    function Logger(level) {
        var _this = this;
        this.buffer = [];
        this.logLevel = level || Logger.LOG_LEVEL_WARNNING;
        var exits = false;
        Logger.allLoggers.forEach(function (temp) {
            exits = temp == _this ? true : false;
        });
        if (!exits)
            Logger.allLoggers.push(this);
    }
    Logger.prototype.setLevel = function (newLevel) {
        this.logLevel = newLevel;
    };
    Logger.prototype.error = function () {
        var messages = [];
        for (var _i = 0; _i < arguments.length; _i++) {
            messages[_i] = arguments[_i];
        }
        for (var _a = 0, messages_1 = messages; _a < messages_1.length; _a++) {
            var temp = messages_1[_a];
            var msg = "%cERROR:	%c" + temp;
            this.buffer.push(msg);
            if (this.logLevel >= Logger.LOG_LEVEL_ERROR)
                console.log(msg, "color: red; font-weight: bold;", "");
        }
    };
    Logger.prototype.warn = function () {
        var messages = [];
        for (var _i = 0; _i < arguments.length; _i++) {
            messages[_i] = arguments[_i];
        }
        for (var _a = 0, messages_2 = messages; _a < messages_2.length; _a++) {
            var temp = messages_2[_a];
            var msg = "%cWARNNING:	%c" + temp;
            this.buffer.push(msg);
            if (this.logLevel >= Logger.LOG_LEVEL_WARNNING)
                console.log(msg, "color: yellow; font-weight: bold;", "");
        }
    };
    Logger.prototype.info = function () {
        var messages = [];
        for (var _i = 0; _i < arguments.length; _i++) {
            messages[_i] = arguments[_i];
        }
        for (var _a = 0, messages_3 = messages; _a < messages_3.length; _a++) {
            var temp = messages_3[_a];
            var msg = "%cINFO:	%c" + temp;
            this.buffer.push(msg);
            if (this.logLevel >= Logger.LOG_LEVEL_INFO)
                console.log(msg, "color: green; font-weight: bold;", "");
        }
    };
    Logger.prototype.log = function () {
        var messages = [];
        for (var _i = 0; _i < arguments.length; _i++) {
            messages[_i] = arguments[_i];
        }
        for (var _a = 0, messages_4 = messages; _a < messages_4.length; _a++) {
            var msg = messages_4[_a];
            if (msg instanceof Array) {
                console.table(msg);
            }
            else if (msg instanceof Object) {
                console.log(msg);
            }
            else {
                this.info(msg);
            }
        }
    };
    Logger.prototype.history = function () {
        this.buffer.forEach(function (e) {
            return console.log(e, "color:orange; font-weight:bold;", "");
        });
    };
    Logger.disableCollisionWarn = function () {
        Logger.collisionWarn = !Logger.collisionWarn;
    };
    Logger.allLoggers = [];
    Logger.collisionWarn = true;
    Logger.maxCollisionWarn = 0;
    Logger.LOG_LEVEL_ERROR = 1;
    Logger.LOG_LEVEL_WARNNING = 2;
    Logger.LOG_LEVEL_INFO = 3;
    return Logger;
}());
var EnginGlobal = new Namespace("EnginEnginGlobal");
EnginGlobal.EnginGlobalBuffer = {};
EnginGlobal.FPS = 60;
EnginGlobal.setFPS = function (newFPS) {
    EnginGlobal.FPS = newFPS;
};
EnginGlobal.collisionObjects = [];
var Time = new Namespace("time");
Time.deltaTime = 1000 / EnginGlobal.FPS;
var debug = new Logger(Logger.LOG_LEVEL_INFO);
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
var Engin = (function () {
    function Engin(name) {
        this.name = name;
        this.id = "object_" + Math.floor(random(0, 1e8));
        this.buffer = {};
    }
    Engin.prototype.render = function (targetCanvas) {
        throw new Error("must be implemented by subclass!");
    };
    Engin.prototype.draw = function (targetCanvas) {
        this.render(targetCanvas);
    };
    Engin.prototype.enableCollision = function () {
        if (this instanceof Canvas) {
            throw new Error("Cannnot tag CANVAS objects as collidable.");
        }
        this.collision = true;
        var exist = false;
        for (var _i = 0, _a = EnginGlobal.collisionObjects; _i < _a.length; _i++) {
            var element = _a[_i];
            if (element.id == this.id) {
                exist = true;
                break;
            }
        }
        if (!exist)
            EnginGlobal.collisionObjects.push(this);
    };
    Engin.prototype.disableCollision = function () {
        this.collision = false;
        var exist = false;
        for (var _i = 0, _a = EnginGlobal.collisionObjects; _i < _a.length; _i++) {
            var element = _a[_i];
            if (element.id == this.id) {
                exist = true;
                break;
            }
        }
        if (exist) {
            EnginGlobal.collisionObjects.splice(EnginGlobal.collisionObjects.indexOf(this), 1);
        }
    };
    Engin.prototype.equals = function (other) {
        return this.id == other.id;
    };
    Engin.prototype.parseToWidth = function (percentage) {
        if (isNaN(percentage)) {
            percentage = percentage.split("");
            percentage.pop();
            percentage = percentage.join("");
            percentage = Number(percentage / 100) * window.innerWidth;
            return percentage;
        }
        else {
            return percentage;
        }
    };
    Engin.prototype.parseToHeight = function (percentage) {
        if (isNaN(percentage)) {
            percentage = percentage.split("");
            percentage.pop();
            percentage = percentage.join("");
            percentage = Number(percentage / 100) * window.innerHeight;
            return percentage;
        }
        else {
            return percentage;
        }
    };
    return Engin;
}());
var Canvas = (function (_super) {
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
        if (!document.getElementById(this.id)) {
            this.parent.innerHTML += DOM;
        }
        else {
            if (this.overwrite) {
                this.parent.removeChild(document.getElementById(this.id));
                this.parent.innerHTML += DOM;
            }
        }
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
    Canvas.prototype.scale = function (x, y) {
        this.ctx.scale(x, y);
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
}(Engin));
var Triangle = (function (_super) {
    __extends(Triangle, _super);
    function Triangle(vec1, vec2, vec3) {
        var _this = _super.call(this, "triangle") || this;
        _this.p = [];
        if (vec1 instanceof Triangle) {
            _this.p = vec1.p;
        }
        else {
            _this.p = new Array(3);
            if (vec1 && vec2 && vec3) {
                _this.p[0] = vec1;
                _this.p[1] = vec2;
                _this.p[2] = vec3;
            }
        }
        return _this;
    }
    Triangle.prototype.render = function (targetCanvas) {
        targetCanvas.ctx.beginPath();
        targetCanvas.ctx.strokeStyle = "white";
        targetCanvas.ctx.moveTo(this.p[0].x, this.p[0].y);
        targetCanvas.ctx.lineTo(this.p[1].x, this.p[1].y);
        targetCanvas.ctx.lineTo(this.p[2].x, this.p[2].y);
        targetCanvas.ctx.lineTo(this.p[0].x, this.p[0].y);
        targetCanvas.ctx.stroke();
    };
    return Triangle;
}(Engin));
var Mesh = (function (_super) {
    __extends(Mesh, _super);
    function Mesh() {
        var _this = _super.call(this, "mesh") || this;
        _this.tris = [];
        _this.tris = [];
        return _this;
    }
    return Mesh;
}(Engin));
var canvas = new Canvas(0, 0, window.innerWidth, window.innerHeight);
canvas.setBackground("black");
var meshCube = new Mesh();
meshCube.tris = [
    new Triangle(new Vector(0, 0, 0), new Vector(0, 1, 0), new Vector(1, 1, 0)),
    new Triangle(new Vector(0, 0, 0), new Vector(1, 1, 0), new Vector(1, 0, 0)),
    new Triangle(new Vector(1, 0, 0), new Vector(1, 1, 0), new Vector(1, 1, 1)),
    new Triangle(new Vector(1, 0, 0), new Vector(1, 1, 1), new Vector(1, 0, 1)),
    new Triangle(new Vector(1, 0, 1), new Vector(1, 1, 1), new Vector(0, 1, 1)),
    new Triangle(new Vector(1, 0, 1), new Vector(0, 1, 1), new Vector(0, 0, 1)),
    new Triangle(new Vector(0, 0, 1), new Vector(0, 1, 1), new Vector(0, 1, 0)),
    new Triangle(new Vector(0, 0, 1), new Vector(0, 1, 0), new Vector(0, 0, 0)),
    new Triangle(new Vector(0, 1, 0), new Vector(0, 1, 1), new Vector(1, 1, 1)),
    new Triangle(new Vector(0, 1, 0), new Vector(1, 1, 1), new Vector(1, 1, 0)),
    new Triangle(new Vector(1, 0, 1), new Vector(0, 0, 1), new Vector(0, 0, 0)),
    new Triangle(new Vector(1, 0, 1), new Vector(0, 0, 0), new Vector(1, 0, 0))
];
var fNear = 0.1;
var fFar = 1000.0;
var fFov = 90.0;
var fAspectRatio = canvas.height / canvas.width;
var fFovRad = 1.0 / Math.tan(((fFov * 0.5) / 180.0) * Math.PI);
var matProj = new Matrix(4, 4);
matProj.setData(0, 0, fAspectRatio * fFovRad);
matProj.setData(1, 1, fFovRad);
matProj.setData(2, 2, fFar / (fFar - fNear));
matProj.setData(3, 2, (-fFar * fNear) / (fFar - fNear));
matProj.setData(2, 3, 1.0);
matProj.setData(3, 3, 0.0);
function render() {
    canvas.clear(0, 0, canvas.width, canvas.height);
    for (var _i = 0, _a = meshCube.tris; _i < _a.length; _i++) {
        var tri = _a[_i];
        var triTranslated = new Triangle(tri);
        for (var i = 0; i < triTranslated.p.length; i++) {
            triTranslated.p[i].z = tri.p[i].z;
        }
        var triProjected = new Triangle();
        for (var i = 0; i < triProjected.p.length; i++) {
            triProjected.p[i] = Matrix.mult4x4WithVector(triTranslated.p[i], matProj);
        }
        for (var i = 0; i < triProjected.p.length; i++) {
            triProjected.p[i].x += 1.0;
            triProjected.p[i].y += 1.0;
            triProjected.p[i].x *= 0.5 * canvas.width;
            triProjected.p[i].y *= 0.5 * canvas.height;
        }
        triProjected.render(canvas);
    }
}
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
function gameLoop() {
    return __awaiter(this, void 0, void 0, function () {
        var t1, t2;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    t1 = new Date().getTime();
                    _a.label = 1;
                case 1:
                    if (!(1 == 1)) return [3, 3];
                    render();
                    t2 = new Date().getTime();
                    Time.deltaTime = t2 - t1;
                    t1 = new Date().getTime();
                    if (EnginGlobal.FPS == 0) {
                        console.log("Game loop has been exited");
                        return [3, 3];
                    }
                    return [4, sleep(1000 / EnginGlobal.FPS)];
                case 2:
                    _a.sent();
                    return [3, 1];
                case 3: return [2];
            }
        });
    });
}
gameLoop();

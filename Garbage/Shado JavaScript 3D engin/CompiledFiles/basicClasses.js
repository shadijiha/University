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

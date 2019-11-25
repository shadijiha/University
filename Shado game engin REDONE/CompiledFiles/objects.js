"use strict";
/***
 *
 * Shado objects file with %
 *
 */
Object.defineProperty(exports, "__esModule", { value: true });
const Renderer = require("./core");
class Rectangle {
    constructor(percentX, percentY, percentW, percentH, styles) {
        this.percentX = percentX > 1 ? (percentX / 100) : percentX;
        this.percentY = percentY > 1 ? (percentY / 100) : percentY;
        this.percentW = percentW > 1 ? (percentW / 100) : percentW;
        this.percentH = percentH > 1 ? (percentH / 100) : percentH;
        this.x = this.percentX * Renderer.canvas.width;
        this.y = this.percentY * Renderer.canvas.height;
        this.w = this.percentW * Renderer.canvas.width;
        this.h = this.percentH * Renderer.canvas.height;
        this.autoUpdate = true;
        // Styles
        this.styles = styles || { fill: "white", stroke: "black", lineWidth: 1 };
    }
    updatePercentages() {
        this.x = this.percentX * Renderer.canvas.width;
        this.y = this.percentY * Renderer.canvas.height;
        this.w = this.percentW * Renderer.canvas.width;
        this.h = this.percentH * Renderer.canvas.height;
    }
    draw(context) {
        // if context is undefined default to "c"
        context = context || Renderer.c;
        // Draw rectangle
        Renderer.c.beginPath();
        Renderer.c.strokeStyle = this.styles.stroke;
        Renderer.c.fillStyle = this.styles.fill;
        Renderer.c.lineWidth = this.styles.lineWidth;
        Renderer.c.rect(this.x, this.y, this.w, this.h);
        Renderer.c.stroke();
        Renderer.c.fill();
        // Auto update
        if (this.autoUpdate) {
            this.updatePercentages();
        }
    }
    // Other functions
    hover(mousePosition) {
        if (mousePosition.x > this.x && mousePosition.x < (this.x + this.w) && mousePosition.y > this.y && mousePosition.y < (this.y + this.h)) {
            return true;
        }
        else {
            return false;
        }
    }
    clicked(lastClickPos) {
        if (lastClickPos.x > this.x && lastClickPos.x < (this.x + this.w) && lastClickPos.y > this.y && lastClickPos.y < (this.y + this.h)) {
            return true;
        }
    }
    move(percentDX, percentDY) {
        const tempX = percentDX > 1 ? (percentDX / 100) : percentDX;
        const tempY = percentDY > 1 ? (percentDY / 100) : percentDY;
        this.x += Renderer.canvas.width * tempX;
        this.y += Renderer.canvas.height * tempY;
    }
    // Setters
    stroke(color) {
        this.styles.stroke = color;
    }
    fill(color) {
        this.styles.fill = color;
    }
    lineWidth(size) {
        this.styles.lineWidth = size;
    }
    toggleAutoUpdate() {
        if (this.autoUpdate) {
            this.autoUpdate = false;
        }
        else {
            this.autoUpdate = true;
        }
    }
    // getters
    getCoordinates() {
        return { x: this.x, y: this.y };
    }
    getDimensions() {
        return { w: this.w, h: this.h };
    }
    getArea() {
        return this.w * this.h;
    }
    getPerimetre() {
        return (this.w + this.h) * 2;
    }
}
exports.Rectangle = Rectangle;
class Circle {
    constructor(percentX, percentY, percentR, styles) {
        this.percentX = percentX > 1 ? (percentX / 100) : percentX;
        this.percentY = percentY > 1 ? (percentY / 100) : percentY;
        this.percentR = percentR > 1 ? (percentR / 100) : percentR;
        this.x = this.percentX * Renderer.canvas.width;
        this.y = this.percentY * Renderer.canvas.height;
        this.r = this.percentR * Renderer.canvas.width;
        this.autoUpdate = true;
        // Styles
        this.styles = styles || { fill: "white", stroke: "black", lineWidth: 1 };
    }
    updatePercentages() {
        this.x = this.percentX * Renderer.canvas.width;
        this.y = this.percentY * Renderer.canvas.height;
        this.r = this.percentR * Renderer.canvas.width;
    }
    draw(context) {
        // if context is undefined default to "c"
        context = context || Renderer.c;
        // Draw rectangle
        Renderer.c.beginPath();
        Renderer.c.strokeStyle = this.styles.stroke;
        Renderer.c.fillStyle = this.styles.fill;
        Renderer.c.lineWidth = this.styles.lineWidth;
        Renderer.c.arc(this.x, this.y, this.r, 0, Math.PI * 2, false);
        Renderer.c.stroke();
        Renderer.c.fill();
        // Auto update
        if (this.autoUpdate) {
            this.updatePercentages();
        }
    }
    // Other functions
    hover(mousePosition) {
        let d = Math.sqrt(Math.pow(mousePosition.x - this.x, 2) + Math.pow(mousePosition.y - this.y, 2));
        if (d <= this.r) {
            return true;
        }
        else {
            return false;
        }
    }
    clicked(lastClickPos) {
        let d = Math.sqrt(Math.pow(lastClickPos.x - this.x, 2) + Math.pow(lastClickPos.y - this.y, 2));
        if (d <= this.r) {
            return true;
        }
        else {
            return false;
        }
    }
    move(percentDX, percentDY) {
        const tempX = percentDX > 1 ? (percentDX / 100) : percentDX;
        const tempY = percentDY > 1 ? (percentDY / 100) : percentDY;
        this.x += Renderer.canvas.width * tempX;
        this.y += Renderer.canvas.height * tempY;
    }
    // Setters
    stroke(color) {
        this.styles.stroke = color;
    }
    fill(color) {
        this.styles.fill = color;
    }
    lineWidth(size) {
        this.styles.lineWidth = size;
    }
    toggleAutoUpdate() {
        if (this.autoUpdate) {
            this.autoUpdate = false;
        }
        else {
            this.autoUpdate = true;
        }
    }
    // getters
    getCoordinates() {
        return { x: this.x, y: this.y };
    }
    getRadius() {
        return this.r;
    }
    getArea() {
        return Math.PI * this.r * this.r;
    }
    getPerimetre() {
        return 2 * Math.PI * this.r;
    }
}
exports.Circle = Circle;
//# sourceMappingURL=objects.js.map
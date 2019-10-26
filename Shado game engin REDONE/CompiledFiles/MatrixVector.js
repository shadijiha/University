"use strict";
/***
 *
 * Shado MATRIX and VECTOR Library
 *
 */
Object.defineProperty(exports, "__esModule", { value: true });
class Matrix {
    constructor(rows, cols) {
        this.rows = rows || 2;
        this.cols = cols || this.rows;
        this.data = [];
        // Initialize matrix with 0s
        for (let i = 0; i < this.rows; i++) {
            this.data[i] = [];
            for (let j = 0; j < this.cols; j++) {
                this.data[i][j] = 0;
            }
        }
    }
    setCell(row, col, value) {
        this.data[row][col] = value;
    }
    setMatrix(array) {
        this.data = array;
        this.rows = array.length;
        this.cols = array[0].length;
    }
    getData(row, col) {
        return this.data[row][col];
    }
    randomize(max) {
        max = Number(max) || 10;
        // Fill matrix with random digits
        for (let i = 0; i < this.rows; i++) {
            for (let j = 0; j < this.cols; j++) {
                this.data[i][j] = Math.floor(Math.random() * max);
            }
        }
    }
    identity() {
        if (this.rows != this.cols) {
            console.log("Error! Connot transform current matrix to Identity matrix because rows and colums count doesn\'t match.");
            return;
        }
        for (let i = 0; i < this.rows; i++) {
            for (let j = 0; j < this.cols; j++) {
                if (i == j) {
                    this.data[i][j] = 1;
                }
                else {
                    this.data[i][j] = 0;
                }
            }
        }
    }
    add(n, overwrite) {
        overwrite = overwrite || true;
        if (n instanceof Matrix) {
            // Detect errors
            if (this.cols != n.cols || this.rows != n.rows) {
                console.log("Error! Connot proform Matrix sum operation on matrices with diffrent rows and/or colums count.");
                return;
            }
            // Sum
            var temp = new Matrix(this.rows, this.cols);
            for (let i = 0; i < temp.rows; i++) {
                for (let j = 0; j < temp.cols; j++) {
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
            for (let i = 0; i < this.rows; i++) {
                for (let j = 0; j < this.cols; j++) {
                    this.data[i][j] += n;
                }
            }
        }
        else {
            console.log("Error! Connot proform Matrix sum because passed argument is neither a %c number %c nor a %c matrix.", "color: red; font-weight: bold", "", "color: blue; font-weight: bold");
            return;
        }
    }
    multiply(n, overwrite) {
        overwrite = overwrite || true;
        if (n instanceof Matrix) {
            // Detect error
            if (this.cols != n.rows) {
                console.log("Error! Connot proform Matrix multiplication operation on matrices because Matrix A colums is not equal to Matrix B rows.");
                return;
            }
            var temp = new Matrix(this.rows, n.cols);
            for (let i = 0; i < temp.rows; i++) {
                for (let j = 0; j < temp.cols; j++) {
                    var sum = 0;
                    for (let k = 0; k < this.cols; k++) {
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
            for (let i = 0; i < this.rows; i++) {
                for (let j = 0; j < this.cols; j++) {
                    this.data[i][j] *= n;
                }
            }
        }
        else {
            console.log("Error! Connot proform Matrix multiplication because passed argument is neither a %c number %c nor a %c matrix.", "color: red; font-weight: bold", "", "color: blue; font-weight: bold");
            return;
        }
    }
    transpose() {
        let temp = new Matrix(this.cols, this.rows);
        for (let i = 0; i < temp.rows; i++) {
            for (let j = 0; j < temp.cols; j++) {
                temp.data[i][j] = this.data[j][i];
            }
        }
        this.data = temp.data;
    }
    LUdecomposition() {
        let U = new Matrix(this.rows, this.cols);
        let L = new Matrix(this.rows, this.cols);
        for (let i = 0; i < this.rows; i++) {
            for (let j = 0; j < this.cols; j++) {
                let sum = 0;
                for (let k = 0; k <= i - 1; k++) {
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
    }
    determinant() {
        // Detect Error
        if (this.rows != this.cols) {
            console.log("Error! This matrix has diffrent row and colums count. Thus, the determinant cannot be calculated.");
            return;
        }
        var decomposition = this.LUdecomposition();
        var temp = decomposition[0];
        var mult = 1;
        for (let i = 0; i < temp.rows; i++) {
            for (let j = 0; j < temp.cols; j++) {
                if (i == j) {
                    mult *= temp.data[i][j];
                }
            }
        }
        return Math.floor(mult);
    }
    log() {
        console.table(this.data);
    }
}
exports.Matrix = Matrix;
class Vector {
    constructor(x, y, z) {
        this.x = x;
        this.y = y;
        this.z = z || 0;
    }
    random2D() {
        this.x = Math.random();
        this.y = Math.random();
    }
    random3D() {
        this.random2D();
        this.z = Math.random();
    }
    getX() {
        return this.x;
    }
    getY() {
        return this.y;
    }
    getZ() {
        return this.z;
    }
    add(objVector) {
        this.x += objVector.x;
        this.y += objVector.y;
        this.z += objVector.z;
    }
    substract(objVector) {
        this.x -= objVector.x;
        this.y -= objVector.y;
        this.z -= objVector.z;
    }
    multiply(k) {
        this.x *= k;
        this.y *= k;
        this.z *= k;
    }
    scale(k) {
        this.multiply(k);
    }
    mag() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }
    normalize() {
        const TEMP = this.mag();
        this.x = this.x / TEMP;
        this.y = this.y / TEMP;
        this.z = this.z / TEMP;
    }
    project(objVector) {
        const CONSTANT = this.dotProduct(objVector) / Math.pow(objVector.mag(), 2);
        let result = new Vector(objVector.x, objVector.y, objVector.z);
        result.multiply(CONSTANT);
        return result;
    }
    dotProduct(objVector) {
        return (this.x * objVector.x) + (this.y * objVector.y) + (this.z * objVector.z);
    }
    crossProduct(objVector) {
        let i = (this.y * objVector.z) - (this.z * objVector.y);
        let j = -((this.x * objVector.z) - (this.z * objVector.x));
        let k = (this.x * objVector.y) - (this.y * objVector.x);
        return new Vector(i, j, k);
    }
}
exports.Vector = Vector;
//# sourceMappingURL=MatrixVector.js.map
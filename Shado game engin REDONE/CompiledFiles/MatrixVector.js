"use strict";
/***
 *
 * Shado MATRIX and VECTOR Library
 *
 */
Object.defineProperty(exports, "__esModule", { value: true });
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
            console.log("Error! Connot transform current matrix to Identity matrix because rows and colums count doesn\'t match.");
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
exports.Matrix = Matrix;
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
        return (this.x * objVector.x) + (this.y * objVector.y) + (this.z * objVector.z);
    };
    Vector.prototype.crossProduct = function (objVector) {
        var i = (this.y * objVector.z) - (this.z * objVector.y);
        var j = -((this.x * objVector.z) - (this.z * objVector.x));
        var k = (this.x * objVector.y) - (this.y * objVector.x);
        return new Vector(i, j, k);
    };
    return Vector;
}());
exports.Vector = Vector;
//# sourceMappingURL=MatrixVector.js.map
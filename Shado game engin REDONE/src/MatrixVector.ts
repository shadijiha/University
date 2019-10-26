/***
 *
 * Shado MATRIX and VECTOR Library
 *
 */

import * as Renderer from "./core";

export class Matrix {

    private rows: number;
    private cols: number;
    private data: any[];

    public constructor(rows: number, cols: number) {
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

    public setCell(row: number, col: number, value: any): void {
        this.data[row][col] = value;
    }

    public setMatrix(array: any[]): void {
        this.data = array;
        this.rows = array.length;
        this.cols = array[0].length;
    }

    public getData(row: number, col: number): any {
        return this.data[row][col];
    }

    public randomize(max?: number): void {
        max = Number(max) || 10;

        // Fill matrix with random digits
        for (let i = 0; i < this.rows; i++) {
            for (let j = 0; j < this.cols; j++) {
                this.data[i][j] = Math.floor(Math.random() * max);
            }
        }

    }

    public identity(): void {

        if (this.rows != this.cols) {
            console.log("Error! Connot transform current matrix to Identity matrix because rows and colums count doesn\'t match.");
            return;
        }

        for (let i = 0; i < this.rows; i++) {
            for (let j = 0; j < this.cols; j++) {
                if (i == j) {
                    this.data[i][j] = 1;
                } else {
                    this.data[i][j] = 0;
                }
            }
        }
    }

    public add(n: any, overwrite: boolean): any {

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
            } else  {
                return temp;
            }

        } else if (!isNaN(Number(n))) {

            for (let i = 0; i < this.rows; i++) {
                for (let j = 0; j < this.cols; j++) {
                    this.data[i][j] += n;
                }
            }
        } else {
            console.log("Error! Connot proform Matrix sum because passed argument is neither a %c number %c nor a %c matrix.", "color: red; font-weight: bold", "", "color: blue; font-weight: bold");
            return;
        }

    }

    public multiply(n: any, overwrite: boolean): any {

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
            } else  {
                return temp;
            }            

        } else if (!isNaN(Number(n))) {
            for (let i = 0; i < this.rows; i++) {
                for (let j = 0; j < this.cols; j++) {
                    this.data[i][j] *= n;
                }
            }
        } else {
            console.log("Error! Connot proform Matrix multiplication because passed argument is neither a %c number %c nor a %c matrix.", "color: red; font-weight: bold", "", "color: blue; font-weight: bold");
            return;
        }

    }

    public transpose(): void {
        let temp = new Matrix(this.cols, this.rows);

        for (let i = 0; i < temp.rows; i++) {
            for (let j = 0; j < temp.cols; j++) {
                temp.data[i][j] = this.data[j][i];
            }
        }

        this.data = temp.data;
    }

    private LUdecomposition(): Matrix[] {
        let U = new Matrix(this.rows, this.cols)
        let L = new Matrix(this.rows, this.cols)
        for (let i = 0; i < this.rows; i++) {

            for (let j = 0; j < this.cols; j++) {
                let sum = 0;

                for (let k = 0; k <= i - 1; k++) {
                    sum = sum + L.data[j][k] * U.data[k][i]
                }

                if (i === j) {
                    U.data[i][j] = 1;
                }
                if (i <= j) {
                    L.data[j][i] = this.data[j][i] - sum;
                } else {
                    U.data[j][i] = (1 / L.data[j][j]) * (this.data[j][i] - sum);
                }

            }
        }

        return [L, U];
    }

    public determinant(): number {

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

    public log(): void {
        console.table(this.data);
    }
}

export class Vector {

    private x: number;
    private y: number;
    private z: number;

    public constructor(x: number, y: number, z?: number) {
        this.x = x;
        this.y = y;
        this.z = z || 0;
    }

    public random2D(): void {
        this.x = Math.random();
        this.y = Math.random();
    }

    public random3D(): void {
        this.random2D();
        this.z = Math.random();
    }

    public getX(): number {
        return this.x;
    }

    public getY(): number {
        return this.y;
    }

    public getZ(): number {
        return this.z;
    }

    public add(objVector: Vector): void {
        this.x += objVector.x;
        this.y += objVector.y;
        this.z += objVector.z;
    }

    public substract(objVector: Vector): void {
        this.x -= objVector.x;
        this.y -= objVector.y;
        this.z -= objVector.z;
    }

    public multiply(k: number) {
        this.x *= k;
        this.y *= k;
        this.z *= k;
    }

    public scale(k: number) {
        this.multiply(k);
    }

    public mag(): number {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }

    public normalize(): void {

        const TEMP = this.mag();

        this.x = this.x / TEMP;
        this.y = this.y / TEMP;
        this.z = this.z / TEMP;
    }

    public project(objVector: Vector): Vector {
        const CONSTANT = this.dotProduct(objVector) / Math.pow(objVector.mag(), 2);
        let result = new Vector(objVector.x, objVector.y, objVector.z);
        result.multiply(CONSTANT);

        return result;
    }

    public dotProduct(objVector: Vector): number {
        return (this.x * objVector.x) + (this.y * objVector.y) + (this.z * objVector.z);
    }

    public crossProduct(objVector: Vector): Vector {
        let i = (this.y * objVector.z) - (this.z * objVector.y);
        let j = -((this.x * objVector.z) - (this.z * objVector.x));
        let k = (this.x * objVector.y) - (this.y * objVector.x);

        return new Vector(i, j, k);
    }

}

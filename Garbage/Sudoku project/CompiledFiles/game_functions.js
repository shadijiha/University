var __spreadArrays = (this && this.__spreadArrays) || function () {
    for (var s = 0, i = 0, il = arguments.length; i < il; i++) s += arguments[i].length;
    for (var r = Array(s), k = 0, i = 0; i < il; i++)
        for (var a = arguments[i], j = 0, jl = a.length; j < jl; j++, k++)
            r[k] = a[j];
    return r;
};
var Board = (function () {
    function Board(init) {
        this.positionX = 0;
        this.positionY = 0;
        if (init instanceof Board) {
            this.data = __spreadArrays(init.data);
            this.original = __spreadArrays(init.original);
            this.positionX = init.positionX;
            this.positionY = init.positionY;
        }
        else {
            this.data = init;
            this.original = this.data;
        }
    }
    Board.prototype.render = function (target) {
        var tileSize = 70;
        var offset = { x: this.positionX, y: this.positionY };
        for (var y = 0; y < this.data.length; y++) {
            for (var x = 0; x < this.data[y].length; x++) {
                var tempRect = new Rectangle(x * tileSize + offset.x, y * tileSize + offset.y, tileSize, tileSize);
                tempRect.draw(target);
                if (this.data[y][x] != 0) {
                    var txt = new ShadoText(this.data[y][x].toString(), tempRect.x + tempRect.w * 0.36, tempRect.y + tempRect.h * 0.57, {
                        font: "Arial",
                        size: 30,
                    });
                    if (this.original[y][x] == 0) {
                        txt.color = "red";
                    }
                    txt.draw(target);
                }
                if (x % 3 == 0 && x != 0) {
                    var temp = new Rectangle(x * tileSize + offset.x, offset.y, 1, this.data.length * tileSize);
                    temp.setLineWidth(5);
                    temp.draw(target);
                }
            }
            if (y % 3 == 0 && y != 0) {
                var temp = new Rectangle(offset.x, y * tileSize + offset.y, this.data[y].length * tileSize, 1);
                temp.setLineWidth(5);
                temp.draw(target);
            }
        }
    };
    Board.prototype.setPosition = function (pos) {
        this.positionX = pos.x;
        this.positionY = pos.y;
    };
    Board.prototype.toString = function () {
        var str = "";
        for (var i = 0; i < this.data.length; i++) {
            if (i % 3 == 0 && i != 0) {
                str += "<br />- - - - - - - - - - - - - - - <br />";
            }
            for (var j = 0; j < this.data[i].length; j++) {
                if (j % 3 == 0 && j != 0) {
                    str += " | ";
                }
                if (j == 8) {
                    str += this.data[i][j] + "<br />";
                }
                else {
                    str += this.data[i][j] + "&nbsp;";
                }
            }
        }
        return str;
    };
    Board.prototype.log = function () {
        var str = this.toString();
        str = str.replace("<br />", "\n");
        str = str.replace("&nbsp;", " ");
        console.log(str);
    };
    Board.prototype.findEmpty = function () {
        for (var i = 0; i < this.data.length; i++) {
            for (var j = 0; j < this.data[i].length; j++) {
                if (this.data[i][j] == 0) {
                    return { y: i, x: j };
                }
            }
        }
        return null;
    };
    Board.prototype.valid = function (num, pos) {
        for (var i = 0; i < this.data[0].length; i++) {
            if (this.data[pos.y][i] == num && pos.x != i) {
                return false;
            }
        }
        for (var i = 0; i < this.data.length; i++) {
            if (this.data[i][pos.x] == num && pos.y != i) {
                return false;
            }
        }
        var box_x = Math.floor(pos.x / 3);
        var box_y = Math.floor(pos.y / 3);
        for (var i = box_y * 3; i < box_y * 3 + 3; i++) {
            for (var j = box_x * 3; j < box_x * 3 + 3; j++) {
                if (this.data[i][j] == num && pos.y != i && pos.x != j) {
                    return false;
                }
            }
        }
        return true;
    };
    Board.prototype.solve = function () {
        var find = this.findEmpty();
        if (find == null) {
            return true;
        }
        else {
            var row = find.y;
            var col = find.x;
            for (var i = 1; i < 10; i++) {
                if (this.valid(i, find)) {
                    this.data[row][col] = i;
                    if (this.solve()) {
                        return true;
                    }
                    this.data[row][col] = 0;
                }
            }
        }
        return false;
    };
    Board.prototype.getOriginal = function () {
        return __spreadArrays(this.original);
    };
    return Board;
}());

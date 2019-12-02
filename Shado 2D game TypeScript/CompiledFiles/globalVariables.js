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
        this.buffer = [];
        this.logLevel = level || Logger.LOG_LEVEL_WARNNING;
    }
    Logger.prototype.setLevel = function (newLevel) {
        this.logLevel = newLevel;
    };
    Logger.prototype.error = function (msg) {
        msg = "%cERROR:	%c" + msg;
        this.buffer.push(msg);
        if (this.logLevel >= Logger.LOG_LEVEL_ERROR)
            console.log(msg, "color: red; font-weight: bold;", "");
    };
    Logger.prototype.warn = function (msg) {
        msg = "%cWARNNING:	%c" + msg;
        this.buffer.push(msg);
        if (this.logLevel >= Logger.LOG_LEVEL_WARNNING)
            console.log(msg, "color: yellow; font-weight: bold;", "");
    };
    Logger.prototype.info = function (msg) {
        msg = "%cINFO:	%c" + msg;
        this.buffer.push(msg);
        if (this.logLevel >= Logger.LOG_LEVEL_INFO)
            console.log(msg, "color: green; font-weight: bold;", "");
    };
    Logger.prototype.log = function (msg) {
        if (msg instanceof Array) {
            console.table(msg);
        }
        else if (msg instanceof Object) {
            console.log(msg);
        }
        else {
            this.info(msg);
        }
    };
    Logger.prototype.history = function () {
        this.buffer.forEach(function (e) {
            return console.log(e, "color:orange; font-weight:bold;", "");
        });
    };
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

var Namespace = (function () {
    function Namespace(name) {
        this.name = name;
        this.id = random(0, 1e6);
    }
    return Namespace;
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

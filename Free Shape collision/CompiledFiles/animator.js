var canvas = new Canvas(0, 0, window.innerWidth, window.innerHeight, "absolute");
canvas.setBackground("rgb(0, 0, 70)");
var win = new ShadoWindow(500, 500, 600, 800, "HitBox data");
win.generate();
win.CENTER_X();
win.CENTER_Y();
win.setContent("<h2>Copy this hitBox collection and append it to youe free-shape</h2><br />");
win.addContent("[");
var MAIN_SHAPE = new Shape([new Vertex(0, 0), new Vertex(600, 0), new Vertex(0, 600)], {
    fill: "brown"
});
var tempRect;
function render() {
    canvas.clear(0, 0, canvas.width, canvas.height);
    new ShadoText("Hold shift to add hitBox", 500, 200, {
        size: 30,
        color: "white"
    }).draw(canvas);
    win.open();
    MAIN_SHAPE.draw(canvas);
    if (mouse.mode == "edit") {
        var modX = mouse.lastClicked.x;
        var modY = mouse.lastClicked.y;
        var width = mouse.x - mouse.lastClicked.x;
        var height = mouse.y - mouse.lastClicked.y;
        if (width < 0) {
            width = Math.abs(width);
            modX = modX - width;
        }
        if (height < 0) {
            height = Math.abs(height);
            modY = modY - height;
        }
        tempRect = new Rectangle(modX, modY, width, height);
        tempRect.setFill("rgba(255, 255, 255, 0.5)");
        tempRect.draw(canvas);
    }
}

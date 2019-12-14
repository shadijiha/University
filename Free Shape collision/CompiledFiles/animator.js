var canvas = new Canvas(0, 0, window.innerWidth, window.innerHeight, "absolute");
canvas.setBackground("rgb(0, 0, 70)");
var testShape = new Shape([
    new Vertex(100, 200),
    new Vertex(210, 250),
    new Vertex(190, 400),
    new Vertex(100, 390),
    new Vertex(100, 200)
], { fill: "lightblue" });
var tempRect;
var win = new ShadoWindow(500, 500, 600, 800, "HitBox data");
win.generate();
win.CENTER_X();
win.CENTER_Y();
win.setContent("<h2>Copy this hitBox collection and append it to youe free-shape</h2><br />[");
function render() {
    canvas.clear(0, 0, canvas.width, canvas.height);
    new ShadoText("Hold shift to add hitBox", 500, 200, {
        size: 30,
        color: "white"
    }).draw(canvas);
    win.open();
    testShape.draw(canvas);
    if (mouse.mode == "edit") {
        tempRect = new Rectangle(mouse.lastClicked.x, mouse.lastClicked.y, mouse.x - mouse.lastClicked.x, mouse.y - mouse.lastClicked.y);
        tempRect.setFill("rgba(255, 255, 255, 0.5)");
        tempRect.draw(canvas);
    }
}

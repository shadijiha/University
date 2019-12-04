var canvas = new Canvas(0, 0, window.innerWidth, window.innerHeight);
canvas.setBackground("rgb(0, 0, 100)");
var objectArray = [];
for (var i = 0; i < 200; i++) {
    var temp = new Circle(floor(random(canvas.width)), floor(random(canvas.height)), floor(random(5, 10)));
    temp.dx = random(-0.1, 0.1);
    temp.dy = random(-0.1, 0.1);
    temp.setFill(randomColor());
    temp.enableCollision();
    objectArray.push(temp);
}
function render() {
    canvas.clear(0, 0, canvas.width, canvas.height);
    for (var _i = 0, objectArray_1 = objectArray; _i < objectArray_1.length; _i++) {
        var circ = objectArray_1[_i];
        circ.render(canvas);
        circ.move(circ.dx, circ.dy);
        if (circ.x + circ.r >= canvas.width || circ.x - circ.r <= 0) {
            circ.dx *= -1;
        }
        if (circ.y + circ.r >= canvas.height || circ.y - circ.r <= 0) {
            circ.dy *= -1;
        }
        for (var _a = 0, objectArray_2 = objectArray; _a < objectArray_2.length; _a++) {
            var other = objectArray_2[_a];
            if (!other.equals(circ)) {
                if (circ.collides(other)) {
                    circ.dx *= -1;
                    circ.dy *= -1;
                    other.dx *= -1;
                    other.dy *= -1;
                }
            }
        }
    }
}

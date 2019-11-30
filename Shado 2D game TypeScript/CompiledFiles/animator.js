var canvas = new Canvas(0, 0, window.innerWidth, window.innerHeight);
canvas.setBackground("#191970");
var snow = [];
for (var i = 0; i < 200; i++) {
    var d = random(1, 30);
    var temp = void 0;
    if (random(0, 1) > 0.5) {
        temp = new Rectangle(random(0, canvas.width), random(0, canvas.height), d, d);
    }
    else {
        temp = new Circle(random(0, canvas.width), random(0, canvas.height), d);
    }
    temp.enableCollision();
    temp.dx = random(0.01, 0.05);
    temp.dy = random(0, 0.02);
    temp.setFill("white");
    snow.push(temp);
}
var SCALER = {
    x: 0.75,
    y: 0.75
};
canvas.scale(SCALER.x, SCALER.y);
function render() {
    canvas.clear(0, 0, canvas.width * (1 + (1 - SCALER.x)), canvas.height * (1 + (1 - SCALER.y)));
    new ShadoText((1000 / Time.deltaTime).toFixed(2), 100, 100, {
        size: 70,
        color: "white"
    }).render(canvas);
    var pauseText = new ShadoText("Abort", 400, 100, {
        size: 20,
        background: "black",
        color: "white"
    });
    pauseText.render(canvas);
    if (pauseText.hover(canvas)) {
        pauseText.text = "Game loop exited";
        pause();
    }
    for (var _i = 0, snow_1 = snow; _i < snow_1.length; _i++) {
        var temp = snow_1[_i];
        if (temp instanceof Rectangle) {
            temp.r = temp.w;
        }
        temp.move(temp.dx, temp.dy);
        if (temp.x + temp.r > canvas.width) {
            temp.x = -random(100);
        }
        if (temp.y > canvas.height) {
            temp.y = -random(100);
        }
        if (temp.x > -temp.r &&
            temp.x < canvas.width &&
            temp.y > -temp.r &&
            temp.y < canvas.height) {
            for (var _a = 0, snow_2 = snow; _a < snow_2.length; _a++) {
                var other = snow_2[_a];
                if (temp != other) {
                    if (temp.collides(other)) {
                        if (!temp.storedColor) {
                            temp.storedColor = randomColor();
                        }
                        temp.setFill(temp.storedColor);
                        break;
                    }
                }
            }
            temp.render(canvas);
            temp.setFill("white");
        }
    }
}

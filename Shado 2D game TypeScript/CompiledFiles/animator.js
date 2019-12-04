var canvas = new Canvas(0, 0, window.innerWidth, window.innerHeight);
canvas.setBackground("rgb(0, 0, 100)");
function render() {
    canvas.clear(0, 0, canvas.width, canvas.height);
    testShape.render(canvas);
    for (var _i = 0, testSplit_1 = testSplit; _i < testSplit_1.length; _i++) {
        var l = testSplit_1[_i];
        l.render(canvas);
    }
    testSplit[0].move(0, 0.1);
    testSplit[1].move(0, -0.1);
}

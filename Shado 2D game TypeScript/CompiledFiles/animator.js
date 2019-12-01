var canvas = new Canvas(0, 0, window.innerWidth, window.innerHeight);
canvas.setBackground("#87CEEB");
var player = new Player(100, "66%", 50, 100);
new Ground(0, "66%", "100%", "34%", Ground.default);
function render() {
    canvas.clear(0, 0, canvas.width, canvas.height);
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
    Ground.allGrounds.forEach(function (g) {
        if (g.display)
            g.render(canvas);
    });
    player.draw(canvas);
}

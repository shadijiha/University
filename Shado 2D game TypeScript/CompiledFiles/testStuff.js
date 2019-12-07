var testShape = new Shape([
    new Vertex(100, 200),
    new Vertex(210, 250),
    new Vertex(190, 400),
    new Vertex(100, 390),
    new Vertex(100, 200)
], { fill: "lightblue" });
var testSplit = new Line(100, 100, 500, 500).split("50%");

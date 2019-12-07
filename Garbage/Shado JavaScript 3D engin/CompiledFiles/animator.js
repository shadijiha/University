var canvas = new Canvas(0, 0, window.innerWidth, window.innerHeight);
canvas.setBackground("black");
var meshCube = new Mesh();
meshCube.tris = [
    new Triangle(new Vector(0, 0, 0), new Vector(0, 1, 0), new Vector(1, 1, 0)),
    new Triangle(new Vector(0, 0, 0), new Vector(1, 1, 0), new Vector(1, 0, 0)),
    new Triangle(new Vector(1, 0, 0), new Vector(1, 1, 0), new Vector(1, 1, 1)),
    new Triangle(new Vector(1, 0, 0), new Vector(1, 1, 1), new Vector(1, 0, 1)),
    new Triangle(new Vector(1, 0, 1), new Vector(1, 1, 1), new Vector(0, 1, 1)),
    new Triangle(new Vector(1, 0, 1), new Vector(0, 1, 1), new Vector(0, 0, 1)),
    new Triangle(new Vector(0, 0, 1), new Vector(0, 1, 1), new Vector(0, 1, 0)),
    new Triangle(new Vector(0, 0, 1), new Vector(0, 1, 0), new Vector(0, 0, 0)),
    new Triangle(new Vector(0, 1, 0), new Vector(0, 1, 1), new Vector(1, 1, 1)),
    new Triangle(new Vector(0, 1, 0), new Vector(1, 1, 1), new Vector(1, 1, 0)),
    new Triangle(new Vector(1, 0, 1), new Vector(0, 0, 1), new Vector(0, 0, 0)),
    new Triangle(new Vector(1, 0, 1), new Vector(0, 0, 0), new Vector(1, 0, 0))
];
var fNear = 0.1;
var fFar = 1000.0;
var fFov = 90.0;
var fAspectRatio = canvas.height / canvas.width;
var fFovRad = 1.0 / Math.tan(((fFov * 0.5) / 180.0) * Math.PI);
var matProj = new Matrix(4, 4);
matProj.setData(0, 0, fAspectRatio * fFovRad);
matProj.setData(1, 1, fFovRad);
matProj.setData(2, 2, fFar / (fFar - fNear));
matProj.setData(3, 2, (-fFar * fNear) / (fFar - fNear));
matProj.setData(2, 3, 1.0);
matProj.setData(3, 3, 0.0);
function render() {
    canvas.clear(0, 0, canvas.width, canvas.height);
    for (var _i = 0, _a = meshCube.tris; _i < _a.length; _i++) {
        var tri = _a[_i];
        var triTranslated = new Triangle(tri);
        for (var i = 0; i < triTranslated.p.length; i++) {
            triTranslated.p[i].z = tri.p[i].z;
        }
        var triProjected = new Triangle();
        for (var i = 0; i < triProjected.p.length; i++) {
            triProjected.p[i] = Matrix.mult4x4WithVector(triTranslated.p[i], matProj);
        }
        for (var i = 0; i < triProjected.p.length; i++) {
            triProjected.p[i].x += 1.0;
            triProjected.p[i].y += 1.0;
            triProjected.p[i].x *= 0.5 * canvas.width;
            triProjected.p[i].y *= 0.5 * canvas.height;
        }
        triProjected.render(canvas);
    }
}

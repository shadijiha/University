/***
 * This file contais the render function that will go
 * to the game loop
 *
 */
// Setup main canvas
const canvas = new Canvas(0, 0, window.innerWidth, window.innerHeight);
canvas.setBackground("black"); // Render is implicitly called

// Declear variables
const meshCube = new Mesh();
meshCube.tris = [
    // SOUTH
    new Triangle(new Vector(0, 0, 0), new Vector(0, 1, 0), new Vector(1, 1, 0)),
    new Triangle(new Vector(0, 0, 0), new Vector(1, 1, 0), new Vector(1, 0, 0)),

    // EAST
    new Triangle(new Vector(1, 0, 0), new Vector(1, 1, 0), new Vector(1, 1, 1)),
    new Triangle(new Vector(1, 0, 0), new Vector(1, 1, 1), new Vector(1, 0, 1)),

    // NORTH
    new Triangle(new Vector(1, 0, 1), new Vector(1, 1, 1), new Vector(0, 1, 1)),
    new Triangle(new Vector(1, 0, 1), new Vector(0, 1, 1), new Vector(0, 0, 1)),

    // WEST
    new Triangle(new Vector(0, 0, 1), new Vector(0, 1, 1), new Vector(0, 1, 0)),
    new Triangle(new Vector(0, 0, 1), new Vector(0, 1, 0), new Vector(0, 0, 0)),

    // TOP
    new Triangle(new Vector(0, 1, 0), new Vector(0, 1, 1), new Vector(1, 1, 1)),
    new Triangle(new Vector(0, 1, 0), new Vector(1, 1, 1), new Vector(1, 1, 0)),

    // BOTTOM
    new Triangle(new Vector(1, 0, 1), new Vector(0, 0, 1), new Vector(0, 0, 0)),
    new Triangle(new Vector(1, 0, 1), new Vector(0, 0, 0), new Vector(1, 0, 0))
];

const fNear = 0.1;
const fFar = 1000.0;
const fFov = 90.0;
const fAspectRatio = canvas.height / canvas.width;
const fFovRad = 1.0 / Math.tan(((fFov * 0.5) / 180.0) * Math.PI);
const matProj = new Matrix(4, 4);
matProj.setData(0, 0, fAspectRatio * fFovRad);
matProj.setData(1, 1, fFovRad);
matProj.setData(2, 2, fFar / (fFar - fNear));
matProj.setData(3, 2, (-fFar * fNear) / (fFar - fNear));
matProj.setData(2, 3, 1.0);
matProj.setData(3, 3, 0.0);

let fTheta = 0;

// For game Loop see "index.js"
function render() {
    // Clear canvas
    canvas.clear(0, 0, canvas.width, canvas.height);

    //Show FPS
    // new ShadoText((1000 / Time.deltaTime).toFixed(2), 100, 100, {
    // 	size: 70,
    // 	color: "white"
    // }).render(canvas);

    // Set up rotation matrices
    const matRotZ = new Matrix(4, 4);
    const matRotX = new Matrix(4, 4);
    fTheta += 1 * Time.deltaTime;

    // Rotation Z
    matRotZ.setData(0, 0, Math.cos(fTheta));
    matRotZ.setData(0, 1, Math.sin(fTheta));
    matRotZ.setData(1, 0, -Math.sin(fTheta));
    matRotZ.setData(1, 1, Math.cos(fTheta));
    matRotZ.setData(2, 2, 1);
    matRotZ.setData(3, 3, 1);

    // Rotation X
    matRotX.setData(0, 0, 1);
    matRotX.setData(1, 1, Math.cos(fTheta * 0.5));
    matRotX.setData(1, 2, Math.sin(fTheta * 0.5));
    matRotX.setData(2, 1, -Math.sin(fTheta * 0.5));
    matRotX.setData(2, 2, Math.cos(fTheta * 0.5));
    matRotX.setData(3, 3, 1);


    /*****************************
     ********* DRAW ALL ***********
     *****************************/
    for (const tri of meshCube.tris) {

        // Rotate in Z-Axis
        const triRotatedZ = new Triangle(Vector.unitVector, Vector.unitVector, Vector.unitVector);
        for (let i = 0; i < triRotatedZ.p.length; i++) {
            triRotatedZ.p[i] = Matrix.mult4x4WithVector(tri.p[i], matRotZ);
        }

        // Rotate in X-Axis
        /*const triRotatedZX = new Triangle(new Vector(), new Vector(), new Vector());
        for (let i = 0; i < triRotatedZ.p.length; i++)	{
            triRotatedZ.p[i] = Matrix.mult4x4WithVector(triRotatedZ.p[i], matRotX);
        }*/

        // Translate triangle
        const triTranslated = triRotatedZ;
        const OFFSET = 3.0;
        for (let i = 0; i < triTranslated.p.length; i++) {
            triTranslated.p[i].z = tri.p[i].z + OFFSET; // TODO: <-------- ADD 3 HERE
        }

        // Project
        const triProjected = new Triangle();
        for (let i = 0; i < triProjected.p.length; i++) {
            triProjected.p[i] = Matrix.mult4x4WithVector(triTranslated.p[i], matProj);
            tri.p[i].z -= OFFSET;						// <------------ for some reason had to add this
        }

        // Scale into view
        for (let i = 0; i < triProjected.p.length; i++) {
            triProjected.p[i].x += 1.0;
            triProjected.p[i].y += 1.0;

            triProjected.p[i].x *= 0.5 * canvas.width;
            triProjected.p[i].y *= 0.5 * canvas.height;
        }

        triProjected.render(canvas);
    }
}

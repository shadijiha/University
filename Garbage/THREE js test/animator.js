/**
 *
 * Playing around with THREE js hehexd ¯\_(ツ)_/¯
 */

// Init mandetory variables

// Draw stuff
const geometry = new THREE.BoxGeometry(1, 1, 1);
const material = new THREE.MeshLambertMaterial({ color: 0xf7f7f7 });
//const mesh = new THREE.Mesh(geometry, material);

//scene.add(mesh);
/*for (let i = 0; i < 15; i++) {
	const mesh = new THREE.Mesh(geometry, material);
	mesh.position.x = (Math.random() - 0.5) * 10;
	mesh.position.y = (Math.random() - 0.5) * 10;
	mesh.position.z = (Math.random() - 0.5) * 10;
	scene.add(mesh);
}*/

// add terrain
const xddx = new Terrain(0, 0, 0, "assets/model3d1.glb");
xddx.draw();

// Render
const Time = { deltaTime: 1 / 144 };
const clock = new THREE.Clock();
clock.start();

function render() {
	requestAnimationFrame(render);

	// Draw stuff here

	// Update renderer
	renderer.render(scene, camera);

	// Get the time between 2 frames
	Time.deltaTime = clock.getDelta() * 1000;
}
render();

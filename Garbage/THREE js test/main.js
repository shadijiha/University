/**
 *
 * Playing around with THREE js hehexd ¯\_(ツ)_/¯
 */

// Init mandetory variables
const scene = new THREE.Scene();
const raycaster = new THREE.Raycaster();
const mouse = new THREE.Vector2();
const camera = new THREE.PerspectiveCamera(
	75,
	window.innerWidth / window.innerHeight,
	0.1,
	1000
);
camera.position.z = 5;
const renderer = new THREE.WebGLRenderer({ antialians: true });
renderer.setClearColor("#e5e5e5");
renderer.setSize(window.innerWidth, window.innerHeight);
document.body.appendChild(renderer.domElement);

// Event listener
window.addEventListener("resize", () => {
	renderer.setSize(window.innerWidth, window.innerHeight);
	camera.aspect = window.innerWidth / window.innerHeight;
	camera.updateProjectionMatrix();
});

window.addEventListener("mousemove", onMouseMove);

function onMouseMove(event) {
	event.preventDefault();
	mouse.x = (event.clientX / window.innerWidth) * 2 - 1;
	mouse.y = -(event.clientY / window.innerHeight) * 2 + 1;

	raycaster.setFromCamera(mouse, camera);

	const intersects = raycaster.intersectObjects(scene.children, true);
	for (const element of intersects) {
		// Animation
		const tl = new TimelineMax();
		tl.to(element.object.scale, 1, { x: 2, ease: Expo.easeOut });
		tl.to(element.object.scale, 0.5, { x: 0.5, ease: Expo.easeOut });
		tl.to(element.object.position, 0.5, { x: 2, ease: Expo.easeOut });
		tl.to(
			element.object.rotation,
			0.5,
			{
				y: Math.PI * 0.5,
				ease: Expo.easeOut
			},
			"=-1.5"
		);
	}
}

// Add light
const light = new THREE.PointLight(0xffffff, 1, 1000);
light.position.set(0, 0, 0);
scene.add(light);

const light2 = new THREE.PointLight(0xffffff, 2, 1000);
light2.position.set(0, 0, 25);
scene.add(light2);

// Draw stuff
const geometry = new THREE.BoxGeometry(1, 1, 1);
const material = new THREE.MeshLambertMaterial({ color: 0xf7f7f7 });
//const mesh = new THREE.Mesh(geometry, material);

//scene.add(mesh);
for (let i = 0; i < 15; i++) {
	const mesh = new THREE.Mesh(geometry, material);
	mesh.position.x = (Math.random() - 0.5) * 10;
	mesh.position.y = (Math.random() - 0.5) * 10;
	mesh.position.z = (Math.random() - 0.5) * 10;
	scene.add(mesh);
}

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

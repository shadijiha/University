/***
 *
 * Init Camera and lights
 *
 */

const scene = new THREE.Scene();
const raycaster = new THREE.Raycaster();
const loader = new THREE.GLTFLoader();
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

// Add light
const light = new THREE.PointLight(0xffffff, 1, 1000);
light.position.set(0, 0, 0);
scene.add(light);

const light2 = new THREE.PointLight(0xffffff, 2, 1000);
light2.position.set(0, 0, 25);
scene.add(light2);

/***
 *
 * Event listeners
 */

const mouse = new THREE.Vector2();

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

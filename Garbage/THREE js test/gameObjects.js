/***
 *
 * All game classes
 */

class GameObject {
	constructor(name) {
		this.name = toString(name);
		this.id = this.name + Math.floor(Math.random() * 1e6);
		this.static = true;
		this.collision = false;
	}

	render() {
		throw new Error("GameObject render() must be overrided");
	}

	draw() {
		this.render();
	}

	enableCollision() {
		this.collision = true;
	}

	disableCollision() {
		this.collision = false;
	}
}

class Terrain extends GameObject {
	static allTerrain = [];
	constructor(x, y, z, src) {
		super("terrain");
		this.x = x;
		this.y = y;
		this.src = src;
		this.collision = true;

		// Push to allTerrain[] if not there
		let exists = false;
		for (const temp of Terrain.allTerrain) {
			if (temp == this) {
				exists = true;
				break;
			}
		}
		if (!exists) {
			Terrain.allTerrain.push(this);
		}
	}

	render() {
		var self = this;
		loader.load(
			self.src,
			function(gltf) {
				const temp = gltf.scene.children[0];
				temp.position.set(self.x, self.y, self.z);
				scene.add(gltf.scene);
			},
			undefined,
			function(error) {
				console.error(error);
			}
		);
	}
}

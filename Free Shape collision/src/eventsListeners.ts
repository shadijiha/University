/**
 *
 * Events
 *
 */

const mouse: {
	x: number;
	y: number;
	lastClicked: { x: number; y: number };
	isDown: boolean;
	mode: string;
} = {
	x: undefined,
	y: undefined,
	lastClicked: { x: undefined, y: undefined },
	isDown: false,
	mode: "view"
};
let SHIFT_PRESSED = false;

window.addEventListener("click", function(event) {
	mouse.lastClicked = { x: event.x, y: event.y };

	// Switch mode
	if (mouse.mode == "view" && SHIFT_PRESSED) {
		mouse.mode = "edit";
	} else if (tempRect != null) {
		const str = `new Rectangle(${tempRect.x}, ${tempRect.y}, ${tempRect.w}, ${tempRect.h}),`;
		testShape.stringHitBox.push(str);
		win.addContent(str + "<br />");
		testShape.addHitBox(tempRect);
		mouse.mode = "view";
		tempRect = null;
	}
});

window.addEventListener("keydown", function(event) {
	if (event.keyCode == 16) {
		SHIFT_PRESSED = true;
	}
});

window.addEventListener("keyup", function(event) {
	if (event.keyCode == 16) {
		SHIFT_PRESSED = false;
	}
});

window.addEventListener("mousemove", function(event) {
	mouse.x = event.x;
	mouse.y = event.y;
});

window.addEventListener("mousedown", function() {
	mouse.isDown = true;
});

window.addEventListener("mouseup", function() {
	mouse.isDown = false;
});

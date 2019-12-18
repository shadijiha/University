var mouse = {
    x: undefined,
    y: undefined,
    lastClicked: { x: undefined, y: undefined },
    isDown: false,
    mode: "view"
};
var SHIFT_PRESSED = false;
window.addEventListener("click", function (event) {
    mouse.lastClicked = { x: event.x, y: event.y };
    if (mouse.mode == "view" && SHIFT_PRESSED) {
        mouse.mode = "edit";
    }
    else if (tempRect != null) {
        var str = "new Rectangle(" + tempRect.x + ", " + tempRect.y + ", " + tempRect.w + ", " + tempRect.h + "),";
        MAIN_SHAPE.stringHitBox.push(str);
        win.addContent(str + "<br />");
        MAIN_SHAPE.addHitBox(tempRect);
        mouse.mode = "view";
        tempRect = null;
    }
});
window.addEventListener("keydown", function (event) {
    if (event.keyCode == 16) {
        SHIFT_PRESSED = true;
    }
});
window.addEventListener("keyup", function (event) {
    if (event.keyCode == 16) {
        SHIFT_PRESSED = false;
    }
});
window.addEventListener("mousemove", function (event) {
    mouse.x = event.x;
    mouse.y = event.y;
});
window.addEventListener("mousedown", function () {
    mouse.isDown = true;
});
window.addEventListener("mouseup", function () {
    mouse.isDown = false;
});

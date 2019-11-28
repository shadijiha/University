/**
 *
 * Events
 *
 */
var mouse = {
    x: undefined,
    y: undefined
};
window.addEventListener("mousemove", function (event) {
    mouse.x = event.x;
    mouse.y = event.y;
});

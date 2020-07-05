/***
 *
 *
 */

class Sales {
	constructor() {
		if (new.target == Sales) {
			throw new Error("Cannot initialize an abstract class");
		}
	}

	static void_processSales(ItemArray_items) {
		// Verify that all items are on sale indeed
		let onsale_items = [];
		for (const item of ItemArray_items) {
			if (item.onSale == true || onSale.onSale == 1) {
				onsale_items.push(item);
			}
		}

		console.log("public void processSales(Item[] items) was called");

		// Start the animation
		let index = 0;
		Sales.private_void_updateSlidShow(onsale_items, index);

		setInterval(function () {
			// Update slide show
			index = (index + 1) % onsale_items.length;
			Sales.private_void_updateSlidShow(onsale_items, index);
		}, 5 * 1000);
	}

	static private_void_updateSlidShow(ItemArray_items, int_index) {
		const imgObj = document.getElementById("slidshow");
		const info = document.getElementById("slidshow_price");

		// Make the transition animation
		imgObj.style.opacity = 0; // Hide the element

		// After 1 second show the element
		setTimeout(function () {
			imgObj.style.backgroundImage = `url('../assets/images/${ItemArray_items[int_index].image}')`;
			info.innerHTML = "$" + ItemArray_items[int_index].getCost();
			imgObj.style.opacity = 1;
		}, 1000);
	}
}

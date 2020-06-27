/***
 *
 *
 */

import { IAbstractComponent } from "./AbstractComponent";
import { Cart } from "./Cart";
import { Item } from "./Item";

window.onload = function () {
	// Initialize Objects
	const cart = new Cart();

	cart.void_add(new Item("Apples", 5.0, 2));
	cart.void_add(new Item("Snakes", 1.45, 1));
};

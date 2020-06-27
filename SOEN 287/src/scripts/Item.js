/**
 *
 */

export class Item {
	constructor(string_name, double_cost, int_quantity) {
		this.name = string_name;
		this.cost = double_cost;
		this.quantity = int_quantity;
	}

	getName() {
		return this.name;
	}

	getCost() {
		return this.cost;
	}

	getQuantity() {
		return this.quantity;
	}
}

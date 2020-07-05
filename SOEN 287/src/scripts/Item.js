/**
 *
 */

class Item {
	constructor(
		int_id,
		string_name,
		string_category,
		string_image,
		double_cost,
		int_quantity,
		bool_onSale
	) {
		this.id = int_id;
		this.name = string_name;
		this.cost = double_cost;
		this.quantity = int_quantity;
		this.image = string_image;
		this.onSale = bool_onSale;

		if (string_category instanceof Array) {
			this.category = string_category.split(",").forEach((e) => {
				e.trim();
			});
		} else {
			this.category = string_category;
		}
	}

	getCost() {
		return Number(this.cost);
	}
}

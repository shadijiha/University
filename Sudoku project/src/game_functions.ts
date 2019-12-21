/***
 * Game functions
 */

class Board {
	public data: number[][];
	private original: number[][];
	private positionX: number = 0;
	private positionY: number = 0;

	public constructor(init: number[][]) {
		this.data = init;
		this.original = this.data;
	}

	render(target: Canvas) {
		const tileSize = 70;
		const offset = { x: this.positionX, y: this.positionY };
		// Cells
		for (let y = 0; y < this.data.length; y++) {
			for (let x = 0; x < this.data[y].length; x++) {
				const tempRect = new Rectangle(
					x * tileSize + offset.x,
					y * tileSize + offset.y,
					tileSize,
					tileSize
				);
				tempRect.draw(target);

				// Draw number
				if (this.data[y][x] != 0) {
					const txt = new ShadoText(
						this.data[y][x].toString(),
						tempRect.x + tempRect.w * 0.36,
						tempRect.y + tempRect.h * 0.57,
						{
							font: "Arial",
							size: 30
						}
					);

					// For solved numbers put them in red
					if (this.original[y][x] == 0) {
						txt.color = "red";
					}

					txt.draw(target);
				}

				// Draw vertical lines
				if (x % 3 == 0 && x != 0) {
					const temp = new Rectangle(
						x * tileSize + offset.x,
						offset.y,
						1,
						this.data.length * tileSize
					);
					temp.setLineWidth(5);
					temp.draw(target);
				}
			}

			// Draw horizontal lines
			if (y % 3 == 0 && y != 0) {
				const temp = new Rectangle(
					offset.x,
					y * tileSize + offset.y,
					this.data[y].length * tileSize,
					1
				);
				temp.setLineWidth(5);
				temp.draw(target);
			}
		}
	}

	public setPosition(pos: Vertex): void {
		this.positionX = pos.x;
		this.positionY = pos.y;
	}

	public toString(): string {
		let str: string = "";
		for (let i = 0; i < this.data.length; i++) {
			if (i % 3 == 0 && i != 0) {
				str += "<br />- - - - - - - - - - - - - - - <br />";
			}

			for (let j = 0; j < this.data[i].length; j++) {
				if (j % 3 == 0 && j != 0) {
					str += " | ";
				}
				if (j == 8) {
					str += this.data[i][j] + "<br />";
				} else {
					str += this.data[i][j] + "&nbsp;";
				}
			}
		}

		return str;
	}

	public log(): void {
		let str = this.toString();
		str = str.replace("<br />", "\n");
		str = str.replace("&nbsp;", " ");
		console.log(str);
	}

	private findEmpty(): { x: number; y: number } {
		for (let i = 0; i < this.data.length; i++) {
			for (let j = 0; j < this.data[i].length; j++) {
				if (this.data[i][j] == 0) {
					return { y: i, x: j };
				}
			}
		}
		return null;
	}

	private valid(num: number, pos: { x: number; y: number }): boolean {
		// Check row
		for (let i = 0; i < this.data[0].length; i++) {
			if (this.data[pos.y][i] == num && pos.x != i) {
				return false;
			}
		}

		// Check cols
		for (let i = 0; i < this.data.length; i++) {
			if (this.data[i][pos.x] == num && pos.y != i) {
				return false;
			}
		}

		// Check cubes
		const box_x = Math.floor(pos.x / 3);
		const box_y = Math.floor(pos.y / 3);

		for (let i = box_y * 3; i < box_y * 3 + 3; i++) {
			for (let j = box_x * 3; j < box_x * 3 + 3; j++) {
				if (this.data[i][j] == num && pos.y != i && pos.x != j) {
					return false;
				}
			}
		}
		return true;
	}

	public solve(): boolean {
		const find = this.findEmpty();
		if (find == null) {
			return true;
		} else {
			const row = find.y;
			const col = find.x;

			for (let i = 1; i < 10; i++) {
				if (this.valid(i, find)) {
					this.data[row][col] = i;

					if (this.solve()) {
						return true;
					}
					this.data[row][col] = 0;
				}
			}
		}
		return false;
	}
}

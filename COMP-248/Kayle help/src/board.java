public class board {
	static final int MIN_LEVEL = 3; // The minimum amount of levels there can be for a custom board
	static final int MIN_SIZE = 3; // The minimum amount each side of the levels can be
	public int level; // An integer for the level number
	public int size; // An integer for the size of a level
	private int sum; //
	int[][][] board; //
	public int l;
	public int x;
	public int y;

	public board() {
		this.level = 3;
		this.size = 4;
		createBoard(this.level, this.size);
	}

	public board(int l, int x) {
		this.level = l;
		this.size = x;
		this.createBoard(l, x);

	}

	private int[][][] createBoard(int levelB, int sizeB) {

		board = new int[levelB][sizeB][sizeB];
		for (l = 0; l < levelB; l++) {
			for (x = 0; x < sizeB; x++) {
				for (y = 0; y < sizeB; y++) {
					if (l == 0 && x == 0 && y == 0) {
						board[l][x][y] = 0;
						continue;
					}
					sum = l + x + y;
					if (sum % 3 == 0) {
						board[l][x][y] = -3;
						continue;
					} else if (sum % 5 == 0) {
						board[l][x][y] = -2;
						continue;
					} else if (sum % 7 == 0) {
						board[l][x][y] = 2;
						continue;
					} else {
						board[l][x][y] = 0;
					}
				}
			}
		}
		return board;

	}

	public int getLevel() {
		return this.level;
	}

	public int getSize() {
		return this.size;
	}

	public int getEnergyAdj(int l, int x, int y) {
		int energyAdj = this.board[l][x][y];
		return energyAdj;
	}

	public String toString() {
		String print = "";
		for (l = 0; l < level; l++) {
			print += ("\nLevel " + l + "\n");
			for (x = 0; x < size; x++) {
				print += "\n";
				for (y = 0; y < size; y++) {
					print += (board[l][x][y] + "\t");
				}
			}
			print += "\n";
		}
		return print;
	}
}

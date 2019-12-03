
public class board {
	static final int MIN_LEVEL = 3;
	static final int MIN_SIZE = 3;
	public int level;
	public int size;
	private int sum;
	int[][][] board;
	int levelR;
	int sizeR;
	int energyAdj;
	public int l;
	public int x;
	public int y;

	public board() {
		this.level = 3;
		this.size = 4;
		this.createBoard(this.level, this.size);
	}

	public board(int l, int x) {
		this.level = l;
		this.size = x;
		this.createBoard(l, x);

	}

	private int[][][] createBoard(int levelB, int sizeB) {

		board = new int[levelB][sizeB][sizeB];
		int l = 0;
		int x = 0;
		int y = 0;
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

	public void level() {
		int[][][] b = this.board;
		levelR = b.length;
	}

	public void size() {
		int[][][] b = this.board;
		sizeR = b[b.length].length;
	}

	public int getLevel() {
		return levelR;
	}

	public int getSize() {
		return sizeR;
	}

	public int getEnergyAdj(int l, int x, int y) {
		energyAdj = board[l][x][y];
		return energyAdj;
	}

	public String toString() {
		String print = "";
		for (l = 0; l < levelR; l++) {
			print += ("\nLevel " + l + "\n");
			for (x = 0; x < sizeR; x++) {
				print += "\n";
				for (y = 0; y < sizeR; y++) {
					print += (board[l][x][y] + "\t");
				}
			}
			print += "\n";
		}
		return print;
	}
}

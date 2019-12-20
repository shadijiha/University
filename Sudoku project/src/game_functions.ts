/***
 * Game functions
 */

function printBoard(board: number[][]): string {
	let str: string = "";
	for (let i = 0; i < board.length; i++) {
		if (i % 3 == 0 && i != 0) {
			str += "<br />- - - - - - - - - - - - - - - <br />";
		}

		for (let j = 0; j < board[i].length; j++) {
			if (j % 3 == 0 && j != 0) {
				str += " | ";
			}
			if (j == 8) {
				str += board[i][j] + "<br />";
			} else {
				str += board[i][j] + "&nbsp;";
			}
		}
	}

	return str;
}

function findEmpty(board: number[][]): { x: number; y: number } {
	for (let i = 0; i < board.length; i++) {
		for (let j = 0; j < board[i].length; j++) {
			if (board[i][j] == 0) {
				return { y: i, x: j };
			}
		}
	}
}

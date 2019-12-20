function printBoard(board) {
    var str = "";
    for (var i = 0; i < board.length; i++) {
        if (i % 3 == 0 && i != 0) {
            str += "<br />- - - - - - - - - - - - - - - <br />";
        }
        for (var j = 0; j < board[i].length; j++) {
            if (j % 3 == 0 && j != 0) {
                str += " | ";
            }
            if (j == 8) {
                str += board[i][j] + "<br />";
            }
            else {
                str += board[i][j] + "&nbsp;";
            }
        }
    }
    return str;
}


public class Board {

    private int[][][] board;
    public static final int MIN_LEVEL = 3;
    public static final int MIN_SIZE = 3;
    private int level;
    private int size;

    public Board() {
        this.level = 3;
        this.size = 4;
        createBoard(this.level, this.size);
    }

    public Board(int l, int x) {
        this.level = l;
        this.size = x;
        createBoard(l, x);
    }

    private void createBoard(int _level, int _size)  {
    	
    	this.board = new int[_level][_size][_size];

        for (int i = 0; i < _level; i++)    {
            for (int j = 0; j < _size; j++) {
                for (int k = 0; k < _size; k++)  {

                    int tempSum = i + j + k;

                    if (tempSum % 3 == 0 && tempSum != 0)
                        this.board[i][j][k] = -3;
                    else if (tempSum % 5 == 0 && tempSum != 0)
                        this.board[i][j][k] = -2;
                    else if (tempSum % 7 == 0 && tempSum != 0)
                        this.board[i][j][k] = 2;
                    else
                        this.board[i][j][k] = 0;                    
                }
            }
        }

    }

    public int getLevel()   {   return this.level;}
    public int getSize()    {   return this.size;}

    public int getEnergyAdj(int l, int x, int y)    {
        return this.board[l][x][y];
    }
    
    public String toString()    {

        String master = "";

        for (int i = 0; i < this.board.length; i++)    {
            master += "\nLevel " + i + "\n";
            master += "----------\n";

            for (int j = 0; j < this.board[i].length; j++) {
                for (int k = 0; k < this.board[i][j].length; k++)  {
                    master += this.board[i][j][k] + "\t";
                }
                master += "\n";
            }
        }
        
        master += "\n";

        return master;
    }

	public String toStringWithPlayers(Player[] players)	{
		String master = "";

        for (int i = 0; i < this.board.length; i++)    {
            master += "\nLevel " + i + "\n";
            master += "----------\n";

            for (int j = 0; j < this.board[i].length; j++) {
                for (int k = 0; k < this.board[i][j].length; k++)  {

					boolean playerExistsHere = false;
					String playerExistsName = "";

					for (Player temp : players)	{
						if (temp.isAtLocation(i, j, k))	{
							playerExistsHere = true;
							playerExistsName = temp.getName();
						}
					}

					if (playerExistsHere)	{
						master += playerExistsName + "\t";
					} else	{
						master += this.board[i][j][k] + "\t";
					}
                    
                }
                master += "\n";
            }
        }
        
        master += "\n";

        return master;		
	}

}

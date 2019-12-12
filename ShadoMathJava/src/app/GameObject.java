
package app;

import nancy3D.*;

public class GameObject {

    protected String name;
    protected int id;

    private Board board;
    private Dice dice;
    private Player[] players = new Player[2];

    public GameObject(String _name) {
        this.name = _name;
        this.id = (int) Math.floor(Math.random() * 100000);
    }

    public Board initBoard(int numberLevel, int size) {
        this.board = new Board(numberLevel, size);
        return this.board;
    }

    public Dice initDice() {
        this.dice = new Dice();
        return this.dice;
    }

    public Player[] initPlayers(String playerNames[]) {
        for (int i = 0; i < this.players.length; i++)
            this.players[i] = new Player(playerNames[i]);

        return this.players;
    }

    public Board getBoard() {
        return this.board;
    }

    public Dice getDice() {
        return this.dice;
    }

    public Player[] getPlayers() {
        return this.players;
    }

}

package nancy3D;

import java.util.Random;
import app.GameObject;

public class Dice extends GameObject {

    private int die1 = 0;
    private int die2 = 0;

    public Dice() {
        super("dice");
    }

    public int getDie1() {
        return this.die1;
    }

    public int getDie2() {
        return this.die2;
    }

    public int rollDice() {

        Random random = new Random();

        this.die1 = random.nextInt(6) + 1;
        this.die2 = random.nextInt(6) + 1;

        return this.die1 + this.die2;
    }

    public int sumOfDice() {
        return this.die1 + this.die2;
    }

    public boolean isDouble() {
        return this.die1 == this.die2;
    }

    public String toString() {
        return "Die1: " + this.die1 + " Die2: " + this.die2;
    }

}

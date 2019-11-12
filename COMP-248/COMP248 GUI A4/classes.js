/***
 * 
 * 
 */

 const TILE_SIZE = 150;

class Dice  {
    constructor()  {
        this.die1 = 0;
        this.die2 = 0;
    }

    getDie1()    {   return this.die1;}
    getDie2()    {   return this.die2;}

    rollDice()  {

        const random = (min, max) =>    {   return Math.round(Math.random() * (max - min) + min)};

        this.die1 = random(1, 6);
        this.die2 = random(1, 6);

        return this.die1 + this.die2;
	}
	
	sumOfDice()	{
		return this.die1 + this.die2;
	}

    isDouble()   {
        return this.die1 == this.die2;
    }

    toString()    {
        return "Die1: " + this.die1 + " Die2: " + this.die2;
    }

    draw(x, y)  {

        let DICE_W = 100;

        // For Die1
        push();
        translate(-width / 2 + x, -height / 2 + y);
        fill(255);
        box(DICE_W, DICE_W);

        // Text
        fill(0);
        translate(0, 0, DICE_W - 40);
        text(this.die1, 0, 0);

        pop();

        // For Die2
        push();
        let offset = DICE_W + 10;
        translate(-width / 2 + x + offset, -height / 2 + y);
        fill(255);
        box(DICE_W, DICE_W);

        // Text
        fill(0);
        translate(0, 0, DICE_W - 40);
        text(this.die2, 0, 0);

        pop();

    }

}

class Tile  {
    constructor(x, y, w, h)   {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.d = 30;
        this.energyAdj = "?";
    }

    draw(playersArray, levelID)  {

        // Detect if current level has player to determine the ALPHA
        let levelHasPlayer = false;
        for (let temp of playersArray)  {
            if (levelID == temp.level)  {
                levelHasPlayer = true;
            }
        }        

        push();
        translate(this.x, this.y);

        // Draw tile box
        if (!levelHasPlayer)    {
            fill('rgba(0, 0, 0, 0.1)');
            stroke('rgba(0, 0, 0, 0.1)');
        }
        box(this.w, this.h, this.d);

        // Energy text
        if (!levelHasPlayer)    {
            fill('rgba(0, 0, 0, 0.5)');
            stroke('rgba(0, 0, 0, 0.5)');
        } else  {
            fill(0, 0, 0);
        }        
        translate(0, 0, this.d / 2 + 10);
        text(this.energyAdj, 0, 0);

        // Draw player
        for (let temp of playersArray)  {
            if (this.x == temp.x && this.y == temp.y && levelID == temp.level)  {
                console.log(temp);
                temp.draw(temp.x * TILE_SIZE, temp.y * TILE_SIZE);               
            }
        }

        pop();
    }
}

class Level {
    constructor(size, id)   {
        this.size = size;
        this.data = [];
        this.levelID = id;
        this.tileSize = TILE_SIZE;
    }

    generate()  {

        this.data = [];

        for (let i = 0; i < this.size; i++) {
            this.data[i] = []
            for (let j = 0; j < this.size; j++) {
                let temp = new Tile(i * this.tileSize, j * this.tileSize, this.tileSize, this.tileSize);
                this.data[i][j] = temp;
            }
        }
    }

    draw(playerArray)  {

        if (this.data.length == 0)  {
            this.generate();
        }

        for (let i = 0; i < this.data.length; i++) {
            for (let j = 0; j < this.data[i].length; j++) {
                this.data[i][j].draw(playerArray, this.levelID);
            }
        }
    }
}

class Board {
    constructor(level, size)    {
        this.level = level;
        this.size = size;
        this.data = [];
    }

    generateBoard() {

        this.data = [];

        for (let i = 0; i < this.level; i++)    {
            this.data.push(new Level(this.size, i));
            this.data[i].generate();
            
            // Determine energy
            for (let j = 0; j < this.size; j++)   {
                for (let k = 0; k < this.size; k++)   {
                    let sum = i + j + k;

                    if (sum % 3 == 0 && sum != 0)   {
                        this.data[i].data[j][k].energyAdj = -3;                        
                    } else if (sum % 5 == 0 && sum != 0)    {
                        this.data[i].data[j][k].energyAdj = -2;
                    } else if (sum % 7 == 0 && sum != 0)    {
                        this.data[i].data[j][k].energyAdj = 2;
                    } else  {
                        this.data[i].data[j][k].energyAdj = 0;
                    }

                } 
            }
        }
    }

    draw(playerArray)  {

        if (this.data.length == 0)  {
            this.generateBoard();
        }

        for (let i = 0; i < this.data.length; i++)  {
            push();
            let offset = 200
            translate(i * 100 - offset, i * 100 - offset, i * - offset / 1.5);
            this.data[i].draw(playerArray);
            pop();
         
        }

    }

    getEnergyAdj(l, x, y)   {
        return this.data[l].data[x][y].energyAdj;
    }

    getSize()   {   return this.size;}
    getLevel()  {   return this.level;}
}

class Player    {
    constructor(name, color)   {
        this.name = name;
        this.level = 0;
        this.x = 0;
        this.y = 0;
        this.color = color || {r: 150, g: 50, b: 50};
        this.energy = 10;
    }

    draw(x, y)  {
        push();
        translate(x, y);
        fill(this.color.r, this.color.g, this.color.b);
        rotateX(Math.PI / 2);
        cylinder(30, 50);
        pop();
    }

    copy()  {
        let tempCopy = new Player(this.name);
        tempCopy.x = this.x;
        tempCopy.y = this.y;
        tempCopy.level = this.level;
        tempCopy.energy = this.energy;
        return tempCopy;
    }

    getX()  {   return this.x;  }
    getY()  {   return this.y;  }
    getEnergy() {   return this.energy;}
    getLevel()  {   return this.level;}

    setX(x) {   return this.x = x;}
    setY(y) {   return this.y = y;}
    setEnergy()   {   return this.energy;}
    setLevel()  {return this.level;}

    equals(other)    {
        return (this.level == other.level && this.x == other.x && this.y == other.y);
    }

    moveTo(temp)    {
        this.level = temp.level;
        this.x = temp.x;
        this.y = temp.y;
    }
}
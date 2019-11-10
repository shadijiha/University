/***
 * 
 * Nancy's 3D warrior game GUI
 */

let testBoard;
let inconsolata;
let dice;
let players = [];

function preload() {
    inconsolata = loadFont('WalkwayBold.ttf');
}

function setup()   {
    createCanvas(windowWidth, windowHeight, WEBGL);

    textFont(inconsolata);
    textSize(50);
    textAlign(CENTER, CENTER);

    // Set up variables
    testBoard = new Board(3, 4);
    players[0] = new Player("Shadi");
    players[1] = new Player("XD");
    dice = new Dice();
    dice.rollDice();
}

function draw()    {

    background(135,206,235);

    // Camera
    rotateX(Math.PI / 3);
    //rotateX(millis() * 0.0001);
    rotateZ(-2 * Math.PI / 9);

    // Render
    testBoard.draw(players);
    dice.draw(500, 500);
}

function movePlayer(p, b, d, opponent, scanner)	{
		
    if (!hasEnoughEnergy(p))	{
        return;
    } else	{
        
        let temp = p.copy();
        let allowMove = true;
        
        let roll = d.sumOfDice();
        
        // Modify TEMP player x and y
        let newX = temp.getX() + (roll / b.getSize());
        let newY = temp.getY() + (roll % b.getSize());

        if (newY >= b.getSize())	{
            newX = newX + newY / b.getSize();
            newY = newY % b.getSize();
        }

        if (newX >= b.getSize())	{
            newX = newX % b.getSize();
            temp.setLevel(temp.getLevel() + 1);
        }

        if ( temp.getLevel() < b.getLevel())	{
            allowMove = true;
        } else	{

            // MAX LEVEL cannot move
            temp.setEnergy(temp.getEnergy() - 2);
            allowMove = false;
            //println("!!! Sorry you need to stay where you are - That throws you off the grid and you lose 2 units of energy");

            p.setEnergy(temp.getEnergy());
            return;
        }

        if (allowMove)	{

            temp.setX(newX);
            temp.setY(newY);

            // Challenge
            if (temp.equals(opponent))	{

                // the new position lead to a position occupied by the opponent
                // Prompt the user to get his choice					
                let action = "-1";

                //println("Player " + opponent.getName() + " is at your new location");
                //println("What do you want to do?");
                //println("\t0 - Challenge and risk loosing 50% of your energy units if you loose or move to new location and get 50% of ther players energy units");
                //println("\t1 - to move down one lovel or move to (0,0) if at level 0 and lose 2 energy units");

                do	{
                    action = scanner.next();

                    if (!action.equalsIgnoreCase("0") && !action.equalsIgnoreCase("1"))	{
                        println("Sorry but " + action + " is not a legal choice.");
                    }

                } while(!action.equalsIgnoreCase("0") && !action.equalsIgnoreCase("1"));

                if (action.equalsIgnoreCase("1"))	{
                    
                    /* ******** Code to forfeit ******** */
                    
                    if (temp.getLevel() == 0)	{
                        // Move player to (0, 0)
                        temp.setX(0);
                        temp.setY(0);
                    } else	{
                        // Decrease level
                        temp.setLevel(temp.getLevel() - 1);
                    }
                    
                } else if (action.equalsIgnoreCase("0"))	{
                    
                    /* ******** Code to challenge ******** */
                    let challengeResult = new Random().nextInt(11);
                    
                    if (challengeResult < 6)	{
                        // A has lost
                        temp.setEnergy(temp.getEnergy() / 2);
                        //println("Sorry, you lost the challenge. You lost " + temp.getEnergy() + " energy :(");

                    } else	{
                        // Swap A and B
                        println("Bravo!! You won the challenge.");

                        let energyToTransfer = opponent.getEnergy() / 2;

                        temp.setX(opponent.getX());
                        temp.setX(opponent.getY());
                        temp.setEnergy(temp.getEnergy() + energyToTransfer);
                        
                        opponent.setX(p.getX());
                        opponent.setY(p.getY());
                        opponent.setEnergy(opponent.getEnergy() - energyToTransfer);					
                    }
                    
                }					
            }

            p.moveTo(temp);		// Move player then exit function

            // Adjust player energy
            let energyOnCurrentPos = b.getEnergyAdj(temp.getLevel(), temp.getX(), temp.getY());
            temp.setEnergy(temp.getEnergy() + energyOnCurrentPos);
        
            //println(String.format("	Your energy is adjusted by %d for landing at (%d,%d) at level %d", energyOnCurrentPos, temp.getX(), temp.getY(), temp.getLevel()));

            p.setEnergy(temp.getEnergy());

            return;
        }
        
    }		
}

function nextTurn(currentTurn)	{
    return Math.abs(currentTurn - 1);
}

function hasEnoughEnergy(p)	{
    return p.getEnergy() > 0;
}

function adjustEnergy(p, d)	{
		
    let tempCopy = p.copy();
    
    if (!hasEnoughEnergy(tempCopy))	{
        // roll the dice 3 times
        let tempEnergyGained = 0;
        let REROLLS_ALLOWED = 3;
        for (let i = 0; i < REROLLS_ALLOWED; i++)	{
            d.rollDice();
            if (d.isDouble())	{
                tempCopy.setEnergy(tempCopy.getEnergy() + 2);
                tempEnergyGained += 2;
            }
        }
        //println(String.format("%s did not have enough energy to move. He gains a chance to get 2 bonus energy units if he rolls a double. This process is done 3 times for up to %d energy units. %s has gained %d bonus energy units.\n", tempCopy.getName(), REROLLS_ALLOWED * 2, tempCopy.getName(), tempEnergyGained));

    } else	{
        // Add 2 energy if the roll is a double
        if (d.isDouble())	{
            tempCopy.setEnergy(tempCopy.getEnergy() + 2);
            //println("	Congratulations! you rolled double " + d.getDie1() + ". Your energy went up by 2 units");
        }
    }
    
    return tempCopy.getEnergy();
}

function print(x)	{
    console.log(x);
}

function println(x)	{
    console.log(x);
}

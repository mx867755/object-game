public class Player {
    int row;
    int col;
    String tempBlock;
    int health;
    int level;
    int potions;
    int result;

    public Player(int row, int col){
        this.row = row;
        this.col = col;
        this.tempBlock = " ";
        this.health = 100;
        this.level = 1;
        this.potions = 1;
        this.result = 0;
    }

    public void setHealth(int health){this.health = health;}
    public void setLevel(int level){this.level = level;}
    public void setPotions(int potions){this.potions = potions;}

    public int getRow(){return row;}
    public int getCol(){return col;}
    public int getHealth(){return this.health;}
    public int getLevel(){return this.level;}
    public int getPotions(){return this.potions;}
    public int getResult(){return this.result;}

    public void spawn(String[][] world){
        world[row][col] = "+";
    }

    public int updatePos(String input, String[][] world) {
        result = 0;
        if (input.equals("a")) {
            col--;
            //Check if new position is within array bounds
            if (col >= 0 && col < world.length) {
                if (!world[row][col].equals("#")) {
                    world[row][col + 1] = tempBlock;
                    tempBlock = world[row][col];
                    world[row][col] = "+";
                    result = 1;
                }
                else {
                    System.out.println("Invalid move.");
                    col++;
                }
            } else {
                System.out.println("Out of bounds");
                col++;
            }
        } else if (input.equals("d")) {
            col++;
            //Check if new position is within array bounds
            if (col >= 0 && col < world.length) {
                if (!world[row][col].equals("#")) {
                    world[row][col - 1] = tempBlock;
                    tempBlock = world[row][col];
                    world[row][col] = "+";
                    result = 1;
                }
                else {
                    System.out.println("Invalid move.");
                    col--;
                }
            } else {
                System.out.println("Out of bounds");
                col--;
            }
        } else if (input.equals("w")) {
            row--;
            //Check if new position is within array bounds
            if (row >= 0 && row < world.length) {
                if (!world[row][col].equals("#")) {
                    world[row + 1][col] = tempBlock;
                    tempBlock = world[row][col];
                    world[row][col] = "+";
                    result = 1;
                }
                else {
                    System.out.println("Invalid move.");
                    row++;
                }
            } else {
                System.out.println("Out of bounds");
                row++;
            }
        } else if (input.equals("s")) {
            row++;
            //Check if new position is within array bounds
            if (row >= 0 && row < world.length) {
                if (!world[row][col].equals("#")) {
                    world[row - 1][col] = tempBlock;
                    tempBlock = world[row][col];
                    world[row][col] = "+";
                    result = 1;
                }
                else {
                    System.out.println("Invalid move.");
                    row--;
                }
            } else {
                System.out.println("Out of bounds");
                row--;
            }
        }
        if (tempBlock.equals("*")){
            world[row][col] = "x";
            tempBlock = " ";
            result = 2;
        }
        else if (tempBlock.equals("E")){
            world[row][col] = "x";
            result = 3;
        }
        else if (tempBlock.equals("T")){
            world[row][col] = "x";
            potions++;
            tempBlock = " ";
            result = 4;
        }
        else if (tempBlock.equals(":")){
            world[row][col] = "x";
            health -= 10;
        }
        return result;
    }

    public void heal(){
        if (potions > 0){
            double temp = 100 + (level-1) * 10;
            this.health = (int) temp;
            this.potions--;
            System.out.println("Your health is now " + health);
        }
        else {
            System.out.println("You don't have any potions.");
        }
    }
}
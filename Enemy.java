public class Enemy {
    int row;
    int col;
    String tempBlock;
    int health;
    int isBoss;

    public Enemy(int row, int col){
        this.row = row;
        this.col = col;
        this.tempBlock = " ";
        this.health = 50;
        this.isBoss = 0;
    }

    public int getRow(){return row;}
    public int getCol(){return col;}
    public int getHealth(){return health;}
    public int getIsBoss(){return isBoss;}

    public void setRow(int row){this.row = row;}
    public void setCol(int col){this.col = col;}
    public void setHealth(int health){this.health = health;}
    public void setIsBoss(int isBoss){this.isBoss = isBoss;}

    public void spawn(String[][] world){
        if (isBoss == 1){
            world[row][col] = "@";
        }
        else {
            world[row][col] = "*";
        }
    }

    public void updatePos(String[][] world){
        if (health > 0){
            int random = (int) (Math.random() * 101);
            if (random < 15){
                col--;
                //Check if new position is within array bounds
                if (col >= 0 && col < world.length){
                    if (world[row][col].equals(" ")){
                        world[row][col+1] = tempBlock;
                        tempBlock = world[row][col];
                        if (isBoss == 1){
                            world[row][col] = "@";
                        }
                        else {
                            world[row][col] = "*";
                        }
                    }
                    else {
                        //Invalid move
                        col++;
                    }
                }
                else {
                    //Out of bounds
                    col++;
                }
            }
            else if (random < 30) {
                col++;
                //Check if new position is within array bounds
                if (col >= 0 && col < world.length) {
                    if (world[row][col].equals(" ")) {
                        world[row][col-1] = tempBlock;
                        tempBlock = world[row][col];
                        if (isBoss == 1){
                            world[row][col] = "@";
                        }
                        else {
                            world[row][col] = "*";
                        }
                    }
                    else {
                        //Invalid move
                        col--;
                    }
                }
                else {
                    //Out of bounds
                    col--;
                }
            }
            else if (random < 45){
                row--;
                //Check if new position is within array bounds
                if (row >= 0 && row < world.length){
                    if (world[row][col].equals(" ")){
                        world[row+1][col] = tempBlock;
                        tempBlock = world[row][col];
                        if (isBoss == 1){
                            world[row][col] = "@";
                        }
                        else {
                            world[row][col] = "*";
                        }
                    }
                    else {
                        //Invalid move
                        row++;
                    }
                }
                else {
                    //Out of bounds
                    row++;
                }
            }
            else if (random < 60){
                row++;
                //Check if new position is within array bounds
                if (row >= 0 && row < world.length){
                    if (world[row][col].equals(" ")){
                        world[row-1][col] = tempBlock;
                        tempBlock = world[row][col];
                        if (isBoss == 1){
                            world[row][col] = "@";
                        }
                        else {
                            world[row][col] = "*";
                        }
                    }
                    else {
                        //Invalid move
                        row--;
                    }
                }
                else {
                    //Out of bounds
                    row--;
                }
            }
        }
    }
}
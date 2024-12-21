public abstract class Character {
    protected int row;
    protected int col;
    protected String icon;
    protected String tempBlock;
    protected int health;
    public Character (int row, int col, String icon, String tempBlock, int health){
        this.row = row;
        this.col = col;
        this.icon = icon;
        this.tempBlock = tempBlock;
        this.health = health;
    }

    public int getRow(){return this.row;}
    public int getCol(){return this.col;}
    public String getIcon() {return this.icon;}
    public String getTempBlock() {return this.tempBlock;}
    public int getHealth(){return health;}

    public void setRow(int row){this.row = row;}
    public void setCol(int col){this.col = col;}
    public void setIcon(String icon) {this.icon = icon;}
    public void setTempBlock(String tempBlock) {this.tempBlock = tempBlock;}
    public void setHealth(int health){this.health = health;}

    public void spawn(World world){
        world.getBoardArr()[row][col] = icon;
    }
    public void updatePos(World world){
        if (health > 0){
            int random = (int) (Math.random() * 101);
            if (random < 15){
                col--;
                //Check if new position is within array bounds
                if (col >= 0 && col < world.getBoardArr().length){
                    if (world.getBoardArr()[row][col].equals(" ")){
                        world.getBoardArr()[row][col+1] = tempBlock;
                        tempBlock = world.getBoardArr()[row][col];
                        world.getBoardArr()[row][col] = icon;
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
                if (col >= 0 && col < world.getBoardArr().length) {
                    if (world.getBoardArr()[row][col].equals(" ")) {
                        world.getBoardArr()[row][col-1] = tempBlock;
                        tempBlock = world.getBoardArr()[row][col];
                        world.getBoardArr()[row][col] = icon;
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
                if (row >= 0 && row < world.getBoardArr().length){
                    if (world.getBoardArr()[row][col].equals(" ")){
                        world.getBoardArr()[row+1][col] = tempBlock;
                        tempBlock = world.getBoardArr()[row][col];
                        world.getBoardArr()[row][col] = icon;
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
                if (row >= 0 && row < world.getBoardArr().length){
                    if (world.getBoardArr()[row][col].equals(" ")){
                        world.getBoardArr()[row-1][col] = tempBlock;
                        tempBlock = world.getBoardArr()[row][col];
                        world.getBoardArr()[row][col] = icon;
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

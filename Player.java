public class Player extends Character {
    private int level;
    private int potions;
    private int result;
    private int jabUpgrade;

    public Player(int row, int col){
        super(row, col, "+", " ", 100);
        this.level = 1;
        this.potions = 1;
        this.result = 0;
        this.jabUpgrade = 0;
    }

    public void setLevel(int level){this.level = level;}
    public void setPotions(int potions){this.potions = potions;}
    public void setResult(int result){this.result = result;}

    public int getLevel(){return this.level;}
    public int getPotions(){return this.potions;}
    public int getResult(){return this.result;}
    public int getJabUpgrade(){return this.jabUpgrade;}

    public void spawn(World world){
        super.spawn(world);
        this.tempBlock = " ";
    }

    public void takeHit(int modify){
        this.health -= 9 - modify;
    }

    public void levelUp(){
        this.level++;
    }

    public int updatePos(String input, World world) {
        String[][] worldArr = world.getBoardArr();
        result = 0;
        if (input.equals("a")) {
            col--;
            //Check if new position is within array bounds
            if (col >= 0 && col < worldArr.length) {
                if (!worldArr[row][col].equals("#")) {
                    worldArr[row][col + 1] = tempBlock;
                    tempBlock = worldArr[row][col];
                    worldArr[row][col] = "+";
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
            if (col >= 0 && col < worldArr.length) {
                if (!worldArr[row][col].equals("#")) {
                    worldArr[row][col - 1] = tempBlock;
                    tempBlock = worldArr[row][col];
                    worldArr[row][col] = "+";
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
            if (row >= 0 && row < worldArr.length) {
                if (!worldArr[row][col].equals("#")) {
                    worldArr[row + 1][col] = tempBlock;
                    tempBlock = worldArr[row][col];
                    worldArr[row][col] = "+";
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
            if (row >= 0 && row < worldArr.length) {
                if (!worldArr[row][col].equals("#")) {
                    worldArr[row - 1][col] = tempBlock;
                    tempBlock = worldArr[row][col];
                    worldArr[row][col] = "+";
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
            worldArr[row][col] = "x";
            tempBlock = " ";
            result = 2;
        }
        else if (tempBlock.equals("@")){
            worldArr[row][col] = "x";
            tempBlock = " ";
            result = 2;
        }
        else if (tempBlock.equals("E")){
            worldArr[row][col] = "x";
            result = 3;
        }
        else if (tempBlock.equals("T")){
            worldArr[row][col] = "x";
            potions++;
            tempBlock = " ";
            result = 4;
        }
        else if (tempBlock.equals("U")){
            worldArr[row][col] = "x";
            jabUpgrade++;
            tempBlock = " ";
            result = 5;
        }
        else if (tempBlock.equals(":")){
            worldArr[row][col] = "x";
            health -= 10;
        }
        return result;
    }

    public void heal(){
        if (potions > 0){
            double temp = 100 + (level-1) * 10;
            this.health = (int) temp;
            this.potions--;
            System.out.println("Health fully restored (Health:" + health + ").\n");
        }
        else {
            System.out.println("You don't have any potions.\n");
        }
    }
}
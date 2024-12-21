public class World {
    String[][] world;
    int row;
    int col;
    String tempBlock;

    public World(String[][] world, int row, int col){
        this.world = world;
        this.row = row;
        this.col = col;
        this.tempBlock = " ";
    }

    public boolean updatePos(String input){
        boolean isValid = false;
        if (input.equals("a")){
            col--;
            //Check if new position is within array bounds
            if (col >= 0 && col < world.length){
                if (world[row][col].equals(" ")){
                    world[row][col+1] = tempBlock;
                    tempBlock = world[row][col];
                    world[row][col] = "+";
                    isValid = true;
                }
                else if (world[row][col].equals("T") || world[row][col].equals("E")){
                    world[row][col+1] = tempBlock;
                    tempBlock = world[row][col];
                    world[row][col] = "x";
                    isValid = true;
                }
                else {
                    System.out.println("Invalid move.");
                    col++;
                }
            }
            else {
                System.out.println("Out of bounds");
                col++;
            }
        }
        else if (input.equals("d")) {
            col++;
            //Check if new position is within array bounds
            if (col >= 0 && col < world.length) {
                if (world[row][col].equals(" ")) {
                    world[row][col-1] = tempBlock;
                    tempBlock = world[row][col];
                    world[row][col] = "+";
                    isValid = true;
                }
                else if (world[row][col].equals("T") || world[row][col].equals("E")) {
                    world[row][col-1] = tempBlock;
                    tempBlock = world[row][col];
                    world[row][col] = "x";
                    isValid = true;
                }
                else {
                    System.out.println("Invalid move.");
                    col--;
                }
            }
            else {
                System.out.println("Out of bounds");
                col--;
            }
        }
        else if (input.equals("w")){
            row--;
            //Check if new position is within array bounds
            if (row >= 0 && row < world.length){
                if (world[row][col].equals(" ")){
                    world[row+1][col] = tempBlock;
                    tempBlock = world[row][col];
                    world[row][col] = "+";
                    isValid = true;
                }
                else if (world[row][col].equals("T") || world[row][col].equals("E")){
                    world[row+1][col] = tempBlock;
                    tempBlock = world[row][col];
                    world[row][col] = "x";
                    isValid = true;
                }
                else {
                    System.out.println("Invalid move.");
                    row++;
                }
            }
            else {
                System.out.println("Out of bounds");
                row++;
            }
        }
        else if (input.equals("s")){
            row++;
            //Check if new position is within array bounds
            if (row >= 0 && row < world.length){
                if (world[row][col].equals(" ")){
                    world[row-1][col] = tempBlock;
                    tempBlock = world[row][col];
                    world[row][col] = "+";
                    isValid = true;
                }
                else if (world[row][col].equals("T") || world[row][col].equals("E")){
                    world[row-1][col] = tempBlock;
                    tempBlock = world[row][col];
                    world[row][col] = "x";
                    isValid = true;
                }
                else {
                    System.out.println("Invalid move.");
                    row--;
                }
            }
            else {
                System.out.println("Out of bounds");
                row--;
            }
        }
        return isValid;
    }
}
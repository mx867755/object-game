import java.util.ArrayList;
import java.util.Scanner;

public class RunGame {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        ArrayList<World> worldArrayList = new ArrayList<>();
        worldArrayList.add(new World(new String[][] {
                {"#","#","#","#","#","#","#","#","#","#"},
                {"#"," "," ","#","T"," "," "," "," ","#"},
                {"#"," "," ","#","#"," "," ","#"," ","#"},
                {"#"," "," "," ","#","#"," "," "," ","#"},
                {"#"," ","#"," ",":"," "," ","#"," ","#"},
                {"#"," "," "," ","#"," "," ","#"," ","#"},
                {"#","#","#"," ","#"," ","#","#"," ","#"},
                {"#"," "," "," ",":"," "," "," "," ","#"},
                {"#","#","#"," ","#","#"," "," "," ","#"},
                {"#","#","#","#","#","#","E","#","#","#"}
        }));
        worldArrayList.add(new World(new String[][] {
                {"#","#","#","#","#","#","#","#","#","#"},
                {"#"," "," "," "," "," "," "," "," ","#"},
                {"#"," ","#",":","#"," "," "," ","#","#"},
                {"#","#","#",":","#","#"," ","#","#","#"},
                {"#"," ","#",":","#"," "," ",":","T","#"},
                {"#"," "," "," ","#","#"," ","#",":","#"},
                {"#","#"," "," ","#"," "," "," "," ","#"},
                {"E"," "," "," ",":"," "," "," "," ","#"},
                {"#","#","#","#","#","#"," "," ","#","#"},
                {"#","#","#","#","#","#","#","#","#","#"}
        }));
        worldArrayList.add(new World(new String[][] {
                {"#","#","#","#","#","#","#","#","#","#"},
                {"#"," "," "," "," "," ","#"," "," ","#"},
                {"#"," ","#","#",":"," "," "," "," ","#"},
                {"#"," ","#","#",":","#","#"," ","#","#"},
                {"#"," ","#"," ",":","E","#"," ","#","#"},
                {"#","U","#"," ","#","#","#"," ","#","#"},
                {"#",":","#"," ","#"," "," "," ","#","#"},
                {"#",":","#"," ",":"," ",":"," ","T","#"},
                {"#"," "," "," ","#"," "," "," ","#","#"},
                {"#","#","#","#","#","#","#","#","#","#"}
        }));
        worldArrayList.add(new World(new String[][] {
                {"#","#","#","#","#","#","#","#","#","#"},
                {"#"," ",":"," "," "," "," "," ","#","#"},
                {"#"," ","#"," "," "," "," ",":","#","#"},
                {"#","T","#"," ","#"," ","#",":","U","#"},
                {"#"," "," "," ","#"," ","#","#"," ","#"},
                {"#","#","#",":","#"," "," ","#"," ","#"},
                {"#"," "," "," ",":"," "," "," ",":","#"},
                {"#"," ",":"," ","#"," ","#"," "," ","#"},
                {"#"," "," "," ","#"," "," "," "," ","#"},
                {"#","#","E","#","#","#","#","#","#","#"}
        }));
        worldArrayList.add(new World(new String[][]{
                {"#","#","#","#","#","#","#","#","#","#"},
                {"#"," "," "," ","#","#","#","#","T","#"},
                {"#"," "," "," ",":"," "," ","#",":","#"},
                {"#"," "," "," ","#"," "," ","#",":","#"},
                {"#","#",":","#","#"," "," ","#",":","#"},
                {"#","#"," "," "," "," "," "," "," ","#"},
                {"#","#"," "," "," "," ","#","#",":","#"},
                {"#","#"," "," "," "," ","#"," "," ","#"},
                {"#"," "," "," "," "," ",":"," "," ","#"},
                {"#","#","#","#","#","#","#","#","#","#"}
        }));
        //wld0, wld1, wld2, wld3, wld4
        int[][][] xy = {{{1,1},{1,1},{1,8},{3,3},{8,8}}, //player xy
                {{8,7},{6,2},{6,7},{5,8},{3,6}}, //enemy1 xy
                {{2,6},{3,6},{8,2},{7,1},{8,3}}, //enemy2 xy
                {{7,1},{7,6},{3,1},{4,3},{5,4}}};//enemy3 xy

        int worldNum = 0;

        Player player = new Player(xy[0][worldNum][0], xy[0][worldNum][1]);
        player.spawn(worldArrayList.get(worldNum));

        Enemy enemy1 = new Enemy(xy[1][worldNum][0], xy[1][worldNum][1]);
        enemy1.spawn(worldArrayList.get(worldNum));

        Enemy enemy2 = new Enemy(xy[2][worldNum][0], xy[2][worldNum][1]);
        enemy2.spawn(worldArrayList.get(worldNum));

        Enemy enemy3 = new Enemy(xy[2][worldNum][0], xy[2][worldNum][1]);
        enemy3.setHealth(0);

        Enemy enemyBoss = new Enemy(2, 2);
        enemyBoss.setHealth(0);

        System.out.println("\nType 'w', 'a', 's', 'd' to move. Type 'heal' to use a potion. Type 'quit' to leave the game at any time.\n");
        //player.setHealth(67);
        //player.setLevel(6);
        //player.setPotions(2);
        printWorld(worldArrayList.get(worldNum));
        System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potion(s):" + player.getPotions() + "\n");
        int blockBuff = 0;
        int healFight = 1;
        double inputPenalty = 0;
        while(true){
            String input = in.next();
            inputPenalty += 0.2;
            if (input.equals("quit")){
                break;
            }
            else if (player.getResult() != 2){
                healFight = 1;
                if (input.equals("heal")){
                    player.heal();
                }
                else if (player.updatePos(input, worldArrayList.get(worldNum)) > 0){
                    if (player.getHealth() <= 0){
                        System.out.println("You died!");
                        break;
                    }
                    else if (player.getResult() != 2){
                        enemy1.updatePos(worldArrayList.get(worldNum));
                        enemy2.updatePos(worldArrayList.get(worldNum));
                        enemy3.updatePos(worldArrayList.get(worldNum));
                        enemyBoss.updatePos(worldArrayList.get(worldNum));
                        printWorld(worldArrayList.get(worldNum));
                        System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potions:" + player.getPotions() + "\n");
                        if (player.getResult() == 3){
                            System.out.println("Next stage! Enemies grow stronger!\n");
                            worldNum++;
                            //spawn player in next world
                            player.setRow(xy[0][worldNum][0]); player.setCol(xy[0][worldNum][1]);
                            player.spawn(worldArrayList.get(worldNum));
                            //respawn enemy1
                            enemy1.setRow(xy[1][worldNum][0]); enemy1.setCol(xy[1][worldNum][1]);
                            enemy1.setHealth(50 + 5 * worldNum);
                            enemy1.spawn(worldArrayList.get(worldNum));
                            //respawn enemy2
                            enemy2.setRow(xy[2][worldNum][0]); enemy2.setCol(xy[2][worldNum][1]);
                            enemy2.setHealth(50 + 5 * worldNum);
                            enemy2.spawn(worldArrayList.get(worldNum));
                            //spawn and respawn enemy3
                            enemy3.setRow(xy[3][worldNum][0]); enemy3.setCol(xy[3][worldNum][1]);
                            enemy3.setHealth(50 + 5 * worldNum);
                            enemy3.spawn(worldArrayList.get(worldNum));
                            //spawn final boss
                            if (worldNum == 4){
                                enemyBoss.setRow(2); enemyBoss.setCol(2);
                                enemyBoss.setHealth(125);
                                enemyBoss.setIsBoss(1);
                                enemyBoss.spawn(worldArrayList.get(worldNum));
                            }
                            //print next world
                            printWorld(worldArrayList.get(worldNum));
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potions:" + player.getPotions() + "\n");
                        }
                        else if (player.getResult() == 4){
                            System.out.println("You found a potion!\n");
                        }
                        else if (player.getResult() == 5){
                            System.out.println("You found an upgrade! (Fleeting jab, level " + player.getJabUpgrade() + ").\n");
                        }
                    }
                    else{
                        printWorld(worldArrayList.get(worldNum));
                        if (player.getRow() == enemyBoss.getRow() && player.getCol() == enemyBoss.getCol()){
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potion(s):" + player.getPotions() + "\nBoss health:" + enemyBoss.getHealth() + "\nFIGHT!\nType 'j' (jab), 'h' (heavy), 'b' (block), or 'heal' (only at start of fight).\n");
                        }
                        else {
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potion(s):" + player.getPotions() + "\nEnemy health:" + (50 + 5 * worldNum) + "\nFIGHT!\nType 'j' (jab), 'h' (heavy), 'b' (block), or 'heal' (only at start of fight).\n");
                        }
                    }
                }
            }
            else {
                if (input.equals("heal") && healFight == 1){
                    player.heal();
                }
                else if (input.equals("h")){
                    if (player.getRow() == enemy1.getRow() && player.getCol() == enemy1.getCol()){
                        if ((int) (Math.random() * 10) < 5){
                            enemy1.heavyHit(blockBuff);
                            System.out.println("Hit!\n");
                            blockBuff = 0;
                        }
                        else{
                            System.out.println("Miss!\n");
                            blockBuff = 0;
                        }
                        if (enemy1.getHealth() <= 0){
                            enemy1.setHealth(0);
                            player.levelUp();
                            player.setResult(0);
                            player.spawn(worldArrayList.get(worldNum));
                            printWorld(worldArrayList.get(worldNum));
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potion(s):" + player.getPotions() + "\nEnemy slain.\nLevel up! Max health increased!\n");
                        }
                        else {
                            player.takeHit(0);
                            if (player.getHealth() <= 0){
                                System.out.println("You died!");
                                break;
                            }
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nEnemy health:" + enemy1.getHealth() + "\n");
                        }

                    }
                    else if (player.getRow() == enemy2.getRow() && player.getCol() == enemy2.getCol()){
                        if ((int) (Math.random() * 10) < 5){
                            enemy2.heavyHit(blockBuff);
                            System.out.println("Hit!\n");
                            blockBuff = 0;
                        }
                        else{
                            System.out.println("Miss!\n");
                            blockBuff = 0;
                        }
                        if (enemy2.getHealth() <= 0){
                            enemy2.setHealth(0);
                            player.levelUp();
                            player.setResult(0);
                            player.spawn(worldArrayList.get(worldNum));
                            printWorld(worldArrayList.get(worldNum));
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potion(s):" + player.getPotions() + "\nEnemy slain.\nLevel up! Max health increased!\n");
                        }
                        else {
                            player.takeHit(0);
                            if (player.getHealth() <= 0){
                                System.out.println("You died!");
                                break;
                            }
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nEnemy health:" + enemy2.getHealth() + "\n");
                        }
                    }
                    else if (player.getRow() == enemy3.getRow() && player.getCol() == enemy3.getCol()){
                        if ((int) (Math.random() * 10) < 5){
                            enemy3.heavyHit(blockBuff);
                            System.out.println("Hit!\n");
                            blockBuff = 0;
                        }
                        else{
                            System.out.println("Miss!\n");
                            blockBuff = 0;
                        }
                        if (enemy3.getHealth() <= 0){
                            enemy3.setHealth(0);
                            player.levelUp();
                            player.setResult(0);
                            player.spawn(worldArrayList.get(worldNum));
                            printWorld(worldArrayList.get(worldNum));
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potion(s):" + player.getPotions() + "\nEnemy slain.\nLevel up! Max health increased!\n");
                        }
                        else {
                            player.takeHit(0);
                            if (player.getHealth() <= 0){
                                System.out.println("You died!");
                                break;
                            }
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nEnemy health:" + enemy3.getHealth() + "\n");
                        }
                    }
                    else if (player.getRow() == enemyBoss.getRow() && player.getCol() == enemyBoss.getCol()){
                        if ((int) (Math.random() * 10) < 5){
                            enemyBoss.heavyHit(blockBuff);
                            System.out.println("Hit!\n");
                            blockBuff = 0;
                        }
                        else{
                            System.out.println("Miss!\n");
                            blockBuff = 0;
                        }
                        if (enemyBoss.getHealth() <= 0){
                            enemyBoss.setHealth(0);
                            player.levelUp();
                            player.setResult(0);
                            player.spawn(worldArrayList.get(worldNum));
                            printWorld(worldArrayList.get(worldNum));
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potion(s):" + player.getPotions() + "\nBoss slain.\nYou've defeated the dungeon!\n");
                            System.out.println("Final score: " + (player.getLevel() * 10 + player.getPotions() * 5 + player.getHealth() - (int) inputPenalty));
                            break;
                        }
                        else {
                            player.takeHit(0);
                            if (player.getHealth() <= 0){
                                System.out.println("You died!");
                                break;
                            }
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nBoss health:" + enemyBoss.getHealth() + "\n");
                        }
                    }
                }
                else if (input.equals("j")){
                    if (player.getRow() == enemy1.getRow() && player.getCol() == enemy1.getCol()){
                        enemy1.jabHit(blockBuff);
                        System.out.println("Hit.\n");
                        blockBuff = 0;
                        if (enemy1.getHealth() <= 0){
                            enemy1.setHealth(0);
                            player.levelUp();
                            player.setResult(0);
                            player.spawn(worldArrayList.get(worldNum));
                            printWorld(worldArrayList.get(worldNum));
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potion(s):" + player.getPotions() + "\nEnemy slain.\nLevel up! Max health increased!\n");
                        }
                        else {
                            player.takeHit(player.getJabUpgrade());
                            if (player.getHealth() <= 0){
                                System.out.println("You died!");
                                break;
                            }
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nEnemy health:" + enemy1.getHealth() + "\n");
                        }
                    }
                    else if (player.getRow() == enemy2.getRow() && player.getCol() == enemy2.getCol()){
                        enemy2.jabHit(blockBuff);
                        System.out.println("Hit.\n");
                        blockBuff = 0;
                        if (enemy2.getHealth() <= 0){
                            enemy2.setHealth(0);
                            player.levelUp();
                            player.setResult(0);
                            player.spawn(worldArrayList.get(worldNum));
                            printWorld(worldArrayList.get(worldNum));
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potion(s):" + player.getPotions() + "\nEnemy slain.\nLevel up! Max health increased!\n");
                        }
                        else {
                            player.takeHit(player.getJabUpgrade());
                            if (player.getHealth() <= 0){
                                System.out.println("You died!");
                                break;
                            }
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nEnemy health:" + enemy2.getHealth() + "\n");
                        }
                    }
                    else if (player.getRow() == enemy3.getRow() && player.getCol() == enemy3.getCol()){
                        enemy3.jabHit(blockBuff);
                        System.out.println("Hit.\n");
                        blockBuff = 0;
                        if (enemy3.getHealth() <= 0){
                            enemy3.setHealth(0);
                            player.levelUp();
                            player.setResult(0);
                            player.spawn(worldArrayList.get(worldNum));
                            printWorld(worldArrayList.get(worldNum));
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potion(s):" + player.getPotions() + "\nEnemy slain.\nLevel up! Max health increased!\n");
                        }
                        else {
                            player.takeHit(player.getJabUpgrade());
                            if (player.getHealth() <= 0){
                                System.out.println("You died!");
                                break;
                            }
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nEnemy health:" + enemy3.getHealth() + "\n");
                        }
                    }
                    else if (player.getRow() == enemyBoss.getRow() && player.getCol() == enemyBoss.getCol()){
                        enemyBoss.jabHit(blockBuff);
                        System.out.println("Hit.\n");
                        blockBuff = 0;
                        if (enemyBoss.getHealth() <= 0){
                            enemyBoss.setHealth(0);
                            player.levelUp();
                            player.setResult(0);
                            player.spawn(worldArrayList.get(worldNum));
                            printWorld(worldArrayList.get(worldNum));
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potion(s):" + player.getPotions() + "\nBoss slain.\nYou've defeated the dungeon!\n");
                            System.out.println("Final score: " + (player.getLevel() * 10 + player.getPotions() * 5 + player.getHealth() - (int) inputPenalty));
                            break;
                        }
                        else {
                            player.takeHit(player.getJabUpgrade());
                            if (player.getHealth() <= 0){
                                System.out.println("You died!");
                                break;
                            }
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nBoss health:" + enemyBoss.getHealth() + "\n");
                        }
                    }
                }
                else if (input.equals("b")){
                    player.takeHit(6
                    );
                    if (player.getHealth() <= 0){
                        System.out.println("You died!");
                        break;
                    }
                    blockBuff = 1;
                    System.out.println("You block most incoming damage, your next hit deals 50% more damage.\n");
                    if (player.getRow() == enemy1.getRow() && player.getCol() == enemy1.getCol()){
                        System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nEnemy health:" + enemy1.getHealth() + "\n");
                    }
                    else if (player.getRow() == enemy2.getRow() && player.getCol() == enemy2.getCol()){
                        System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nEnemy health:" + enemy2.getHealth() + "\n");
                    }
                    else if (player.getRow() == enemy3.getRow() && player.getCol() == enemy3.getCol()){
                        System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nEnemy health:" + enemy3.getHealth() + "\n");
                    }
                    else if (player.getRow() == enemyBoss.getRow() && player.getCol() == enemyBoss.getCol()){
                        System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nBoss health:" + enemyBoss.getHealth() + "\n");
                    }
                }
                healFight = 0;
            }
        }
    }

    public static void printWorld(World world){
        for (String[] cool : world.getBoardArr()){
            for (String very : cool){
                System.out.print(very + " ");
            }
            System.out.println();
        }
    }
}
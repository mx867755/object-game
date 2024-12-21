import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        //3d array for multiple stages
        String[][][] worldArray = {{{"#","#","#","#","#","#","#","#","#","#"},
                {"#"," "," ","#","T"," "," "," "," ","#"},
                {"#"," "," ","#","#"," "," ","#"," ","#"},
                {"#"," "," "," ","#","#"," "," "," ","#"},
                {"#"," ","#"," ",":"," "," ","#"," ","#"},
                {"#"," "," "," ","#"," "," ","#"," ","#"},
                {"#","#","#"," ","#"," ","#","#"," ","#"},
                {"#"," "," "," ",":"," "," "," "," ","#"},
                {"#","#","#"," ","#","#"," "," "," ","#"},
                {"#","#","#","#","#","#","E","#","#","#"}},

                {{"#","#","#","#","#","#","#","#","#","#"},
                        {"#"," "," "," "," "," "," "," "," ","#"},
                        {"#"," ","#",":","#"," "," "," ","#","#"},
                        {"#","#","#",":","#","#"," ","#","#","#"},
                        {"#"," ","#",":","#"," "," ",":","T","#"},
                        {"#"," "," "," ","#","#"," ","#",":","#"},
                        {"#","#"," "," ","#"," "," "," "," ","#"},
                        {"E"," "," "," ",":"," "," "," "," ","#"},
                        {"#","#","#","#","#","#"," "," ","#","#"},
                        {"#","#","#","#","#","#","#","#","#","#"}},

                {{"#","#","#","#","#","#","#","#","#","#"},
                        {"#"," "," "," "," "," ","#"," "," ","#"},
                        {"#"," ","#","#",":"," "," "," "," ","#"},
                        {"#"," ","#","#",":","#","#"," ","#","#"},
                        {"#"," ","#"," ",":","E","#"," ","#","#"},
                        {"#","U","#"," ","#","#","#"," ","#","#"},
                        {"#",":","#"," ","#"," "," "," ","#","#"},
                        {"#",":","#"," ",":"," ",":"," ","T","#"},
                        {"#"," "," "," ","#"," "," "," ","#","#"},
                        {"#","#","#","#","#","#","#","#","#","#"}},

                {{"#","#","#","#","#","#","#","#","#","#"},
                        {"#"," ",":"," "," "," "," "," ","#","#"},
                        {"#"," ","#"," "," "," "," ",":","#","#"},
                        {"#","T","#"," ","#"," ","#",":","U","#"},
                        {"#"," "," "," ","#"," ","#","#"," ","#"},
                        {"#","#","#",":","#"," "," ","#"," ","#"},
                        {"#"," "," "," ",":"," "," "," ",":","#"},
                        {"#"," ",":"," ","#"," ","#"," "," ","#"},
                        {"#"," "," "," ","#"," "," "," "," ","#"},
                        {"#","#","E","#","#","#","#","#","#","#"}},

                {{"#","#","#","#","#","#","#","#","#","#"},
                        {"#"," "," "," ","#","#","#","#","T","#"},
                        {"#"," "," "," ",":"," "," ","#",":","#"},
                        {"#"," "," "," ","#"," "," ","#",":","#"},
                        {"#","#",":","#","#"," "," ","#",":","#"},
                        {"#","#"," "," "," "," "," "," "," ","#"},
                        {"#","#"," "," "," "," ","#","#",":","#"},
                        {"#","#"," "," "," "," ","#"," "," ","#"},
                        {"#"," "," "," "," "," ",":"," "," ","#"},
                        {"#","#","#","#","#","#","#","#","#","#"}}};
        //wld0, wld1, wld2, wld3, wld4
        int[][][] xy = {{{1,1},{1,1},{1,8},{3,3},{8,8}}, //player xy
                {{8,7},{6,2},{6,7},{5,8},{3,6}}, //enemy1 xy
                {{2,6},{3,6},{8,2},{7,1},{8,3}}, //enemy2 xy
                {{7,1},{7,6},{3,1},{4,3},{5,4}}};//enemy3 xy

        int worldNum = 0;

        Player player = new Player(xy[0][worldNum][0], xy[0][worldNum][1]);
        player.spawn(worldArray[worldNum]);

        Enemy enemy1 = new Enemy(xy[1][worldNum][0], xy[1][worldNum][1]);
        enemy1.spawn(worldArray[worldNum]);

        Enemy enemy2 = new Enemy(xy[2][worldNum][0], xy[2][worldNum][1]);
        enemy2.spawn(worldArray[worldNum]);

        Enemy enemy3 = new Enemy(xy[2][worldNum][0], xy[2][worldNum][1]);
        enemy3.setHealth(0);

        Enemy enemyBoss = new Enemy(2, 2);
        enemyBoss.setHealth(0);

        System.out.println("\nType 'w', 'a', 's', 'd' to move. Type 'heal' to use a potion. Type 'quit' to leave the game at any time.\n");
        //player.setHealth(67);
        //player.setLevel(3);
        printWorld(worldArray[worldNum]);
        System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potion(s):" + player.getPotions() + "\n");
        int blockBuff = 0;
        int healFight = 1;
        int jabUpgrade;
        double inputPenalty = 0;
        while(true){
            String input = in.next();
            inputPenalty += 0.2;
            if (input.equals("quit")){
                break;
            }
            else if (player.result != 2){
                healFight = 1;
                if (input.equals("heal")){
                    player.heal();
                }
                else if (player.updatePos(input, worldArray[worldNum]) > 0){
                    if (player.getHealth() <= 0){
                        System.out.println("You died!");
                        break;
                    }
                    else if (player.result != 2){
                        enemy1.updatePos(worldArray[worldNum]);
                        enemy2.updatePos(worldArray[worldNum]);
                        enemy3.updatePos(worldArray[worldNum]);
                        enemyBoss.updatePos(worldArray[worldNum]);
                        printWorld(worldArray[worldNum]);
                        System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potions:" + player.getPotions() + "\n");
                        if (player.result == 3){
                            System.out.println("Next stage! Enemies grow stronger!\n");
                            worldNum++;
                            //spawn player in next world
                            player.setRow(xy[0][worldNum][0]); player.setCol(xy[0][worldNum][1]);
                            player.spawn(worldArray[worldNum]);
                            //respawn enemy1
                            enemy1.setRow(xy[1][worldNum][0]); enemy1.setCol(xy[1][worldNum][1]);
                            enemy1.setHealth(50 + 5 * worldNum);
                            enemy1.spawn(worldArray[worldNum]);
                            //respawn enemy2
                            enemy2.setRow(xy[2][worldNum][0]); enemy2.setCol(xy[2][worldNum][1]);
                            enemy2.setHealth(50 + 5 * worldNum);
                            enemy2.spawn(worldArray[worldNum]);
                            //spawn and respawn enemy3
                            enemy3.setRow(xy[3][worldNum][0]); enemy3.setCol(xy[3][worldNum][1]);
                            enemy3.setHealth(50 + 5 * worldNum);
                            enemy3.spawn(worldArray[worldNum]);
                            //spawn final boss
                            if (worldNum == 4){
                                enemyBoss.setRow(2); enemyBoss.setCol(2);
                                enemyBoss.setHealth(125);
                                enemyBoss.setIsBoss(1);
                                enemyBoss.spawn(worldArray[worldNum]);
                            }
                            //print next world
                            printWorld(worldArray[worldNum]);
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potions:" + player.getPotions() + "\n");
                        }
                        else if (player.result == 4){
                            System.out.println("You found a potion!\n");
                        }
                        else if (player.result == 5){
                            System.out.println("You found an upgrade! (Fleeting jab, level " + player.getJabUpgrade() + ").\n");
                        }
                    }
                    else{
                        printWorld(worldArray[worldNum]);
                        if (player.row == enemyBoss.row && player.col == enemyBoss.col){
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
                    if (player.row == enemy1.row && player.col == enemy1.col){
                        if ((int) (Math.random() * 10) < 5){
                            enemy1.health -= 16 + 8 * blockBuff;
                            System.out.println("Hit!\n");
                            blockBuff = 0;
                        }
                        else{
                            System.out.println("Miss!\n");
                            blockBuff = 0;
                        }
                        if (enemy1.health <= 0){
                            enemy1.setHealth(0);
                            player.level++;
                            player.result = 0;
                            player.spawn(worldArray[worldNum]);
                            printWorld(worldArray[worldNum]);
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potion(s):" + player.getPotions() + "\nEnemy slain.\nLevel up! Max health increased!\n");
                        }
                        else {
                            player.health -= 9;
                            if (player.getHealth() <= 0){
                                System.out.println("You died!");
                                break;
                            }
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nEnemy health:" + enemy1.getHealth() + "\n");
                        }

                    }
                    else if (player.row == enemy2.row && player.col == enemy2.col){
                        if ((int) (Math.random() * 10) < 5){
                            enemy2.health -= 16 + 8 * blockBuff;
                            System.out.println("Hit!\n");
                            blockBuff = 0;
                        }
                        else{
                            System.out.println("Miss!\n");
                            blockBuff = 0;
                        }
                        if (enemy2.health <= 0){
                            enemy2.setHealth(0);
                            player.level++;
                            player.result = 0;
                            player.spawn(worldArray[worldNum]);
                            printWorld(worldArray[worldNum]);
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potion(s):" + player.getPotions() + "\nEnemy slain.\nLevel up! Max health increased!\n");
                        }
                        else {
                            player.health -= 9;
                            if (player.getHealth() <= 0){
                                System.out.println("You died!");
                                break;
                            }
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nEnemy health:" + enemy2.getHealth() + "\n");
                        }
                    }
                    else if (player.row == enemy3.row && player.col == enemy3.col){
                        if ((int) (Math.random() * 10) < 5){
                            enemy3.health -= 16 + 8 * blockBuff;
                            System.out.println("Hit!\n");
                            blockBuff = 0;
                        }
                        else{
                            System.out.println("Miss!\n");
                            blockBuff = 0;
                        }
                        if (enemy3.health <= 0){
                            enemy3.setHealth(0);
                            player.level++;
                            player.result = 0;
                            player.spawn(worldArray[worldNum]);
                            printWorld(worldArray[worldNum]);
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potion(s):" + player.getPotions() + "\nEnemy slain.\nLevel up! Max health increased!\n");
                        }
                        else {
                            player.health -= 9;
                            if (player.getHealth() <= 0){
                                System.out.println("You died!");
                                break;
                            }
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nEnemy health:" + enemy3.getHealth() + "\n");
                        }
                    }
                    else if (player.row == enemyBoss.row && player.col == enemyBoss.col){
                        if ((int) (Math.random() * 10) < 5){
                            enemyBoss.health -= 16 + 8 * blockBuff;
                            System.out.println("Hit!\n");
                            blockBuff = 0;
                        }
                        else{
                            System.out.println("Miss!\n");
                            blockBuff = 0;
                        }
                        if (enemyBoss.health <= 0){
                            enemyBoss.setHealth(0);
                            player.level++;
                            player.result = 0;
                            player.spawn(worldArray[worldNum]);
                            printWorld(worldArray[worldNum]);
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potion(s):" + player.getPotions() + "\nBoss slain.\nYou've defeated the dungeon!'\n");
                            System.out.println("Final score: " + (player.getLevel() * 10 + player.getPotions() * 5 + player.getHealth() - (int) inputPenalty));
                            break;
                        }
                        else {
                            player.health -= 9;
                            if (player.getHealth() <= 0){
                                System.out.println("You died!");
                                break;
                            }
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nBoss health:" + enemyBoss.getHealth() + "\n");
                        }
                    }
                }
                else if (input.equals("j")){
                    jabUpgrade = player.getJabUpgrade();
                    if (player.row == enemy1.row && player.col == enemy1.col){
                        enemy1.health -= 6 + 3 * blockBuff;
                        System.out.println("Hit.\n");
                        blockBuff = 0;
                        if (enemy1.health <= 0){
                            enemy1.setHealth(0);
                            player.level++;
                            player.result = 0;
                            player.spawn(worldArray[worldNum]);
                            printWorld(worldArray[worldNum]);
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potion(s):" + player.getPotions() + "\nEnemy slain.\nLevel up! Max health increased!\n");
                        }
                        else {
                            player.health -= 9 - jabUpgrade;
                            if (player.getHealth() <= 0){
                                System.out.println("You died!");
                                break;
                            }
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nEnemy health:" + enemy1.getHealth() + "\n");
                        }
                    }
                    else if (player.row == enemy2.row && player.col == enemy2.col){
                        enemy2.health -= 6 + 3 * blockBuff;
                        System.out.println("Hit.\n");
                        blockBuff = 0;
                        if (enemy2.health <= 0){
                            enemy2.setHealth(0);
                            player.level++;
                            player.result = 0;
                            player.spawn(worldArray[worldNum]);
                            printWorld(worldArray[worldNum]);
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potion(s):" + player.getPotions() + "\nEnemy slain.\nLevel up! Max health increased!\n");
                        }
                        else {
                            player.health -= 9 - jabUpgrade;
                            if (player.getHealth() <= 0){
                                System.out.println("You died!");
                                break;
                            }
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nEnemy health:" + enemy2.getHealth() + "\n");
                        }
                    }
                    else if (player.row == enemy3.row && player.col == enemy3.col){
                        enemy3.health -= 6 + 3 * blockBuff;
                        System.out.println("Hit.\n");
                        blockBuff = 0;
                        if (enemy3.health <= 0){
                            enemy3.setHealth(0);
                            player.level++;
                            player.result = 0;
                            player.spawn(worldArray[worldNum]);
                            printWorld(worldArray[worldNum]);
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potion(s):" + player.getPotions() + "\nEnemy slain.\nLevel up! Max health increased!\n");
                        }
                        else {
                            player.health -= 9 - jabUpgrade;
                            if (player.getHealth() <= 0){
                                System.out.println("You died!");
                                break;
                            }
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nEnemy health:" + enemy3.getHealth() + "\n");
                        }
                    }
                    else if (player.row == enemyBoss.row && player.col == enemyBoss.col){
                        enemyBoss.health -= 6 + 3 * blockBuff;
                        System.out.println("Hit.\n");
                        blockBuff = 0;
                        if (enemyBoss.health <= 0){
                            enemyBoss.setHealth(0);
                            player.level++;
                            player.result = 0;
                            player.spawn(worldArray[worldNum]);
                            printWorld(worldArray[worldNum]);
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potion(s):" + player.getPotions() + "\nBoss slain.\nYou've defeated the dungeon!'\n");
                            System.out.println("Final score: " + (player.getLevel() * 10 + player.getPotions() * 5 + player.getHealth() - (int) inputPenalty));
                            System.out.println(inputPenalty);
                            break;
                        }
                        else {
                            player.health -= 9 - jabUpgrade;
                            if (player.getHealth() <= 0){
                                System.out.println("You died!");
                                break;
                            }
                            System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nBoss health:" + enemyBoss.getHealth() + "\n");
                        }
                    }
                }
                else if (input.equals("b")){
                    player.health -= 3;
                    if (player.getHealth() <= 0){
                        System.out.println("You died!");
                        break;
                    }
                    blockBuff = 1;
                    System.out.println("You block most incoming damage, your next hit deals 50% more damage.\n");
                    if (player.row == enemy1.row && player.col == enemy1.col){
                        System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nEnemy health:" + enemy1.getHealth() + "\n");
                    }
                    else if (player.row == enemy2.row && player.col == enemy2.col){
                        System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nEnemy health:" + enemy2.getHealth() + "\n");
                    }
                    else if (player.row == enemy3.row && player.col == enemy3.col){
                        System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nEnemy health:" + enemy3.getHealth() + "\n");
                    }
                    else if (player.row == enemyBoss.row && player.col == enemyBoss.col){
                        System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nBoss health:" + enemyBoss.getHealth() + "\n");
                    }
                }
                healFight = 0;
            }
        }
    }

    public static void printWorld(String[][] worldArray){
        for (String[] cool : worldArray){
            for (String very : cool){
                System.out.print(very + " ");
            }
            System.out.println();
        }
    }
}
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        //3d array for multiple stages
        String[][] worldArray = {{"#","#","#","#","#","#","#","#","#","#"},
                {"#"," "," ","#","T"," "," "," "," ","#"},
                {"#"," "," ","#","#"," "," ","#"," ","#"},
                {"#"," "," "," ","#","#"," "," "," ","#"},
                {"#"," ","#"," ",":"," "," ","#"," ","#"},
                {"#"," "," "," ","#"," "," ","#"," ","#"},
                {"#","#","#"," ","#"," ","#","#"," ","#"},
                {"#"," "," "," ",":"," "," "," "," ","#"},
                {"#","#","#"," ","#","#"," "," "," ","#"},
                {"#","#","#","#","#","#","E","#","#","#"}};

        Player player = new Player(1, 1);
        player.spawn(worldArray);

        Enemy enemy1 = new Enemy(8, 7);
        enemy1.spawn(worldArray);

        Enemy enemy2 = new Enemy(2, 6);
        enemy2.spawn(worldArray);

        System.out.println("\nType 'w', 'a', 's', 'd' to move. Type 'heal' to use a potion. Type 'quit' to leave the game at any time.\n");
        //player.setHealth(67);
        //player.setLevel(3);
        printWorld(worldArray);
        System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potion(s):" + player.getPotions() + "\n");
        int blockBuff = 0;
        while(true){
            String input = in.next();
            if (input.equals("quit")){
                break;
            }
            else if (player.result != 2){
                if (input.equals("heal")){
                    player.heal();
                }
                else if (player.updatePos(input, worldArray) > 0){
                    if (player.getHealth() <= 0){
                        System.out.println("You died!");
                        break;
                    }
                    else if (player.result != 2){
                        enemy1.updatePos(worldArray);
                        enemy2.updatePos(worldArray);
                        printWorld(worldArray);
                        System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + " / Potions:" + player.getPotions() + "\n");
                        if (player.result == 3){
                            System.out.println("End, not done");
                        }
                        else if (player.result == 4){
                            System.out.println("You found a potion!");
                        }
                    }
                    else{
                        printWorld(worldArray);
                        System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nEnemy health:50\nFIGHT!\nType 'j' (jab), 'h' (heavy), or 'b' (block)\n");
                    }
                }
            }
            else {
                if (input.equals("h")){
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
                            player.spawn(worldArray);
                            printWorld(worldArray);
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
                            player.spawn(worldArray);
                            printWorld(worldArray);
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
                }
                else if (input.equals("j")){
                    if (player.row == enemy1.row && player.col == enemy1.col){
                        enemy1.health -= 6 + 3 * blockBuff;
                        System.out.println("Hit.\n");
                        blockBuff = 0;
                        if (enemy1.health <= 0){
                            enemy1.setHealth(0);
                            player.level++;
                            player.result = 0;
                            player.spawn(worldArray);
                            printWorld(worldArray);
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
                        enemy2.health -= 6 + 3 * blockBuff;
                        System.out.println("Hit.\n");
                        blockBuff = 0;
                        if (enemy2.health <= 0){
                            enemy2.setHealth(0);
                            player.level++;
                            player.result = 0;
                            player.spawn(worldArray);
                            printWorld(worldArray);
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
                }
                else if (input.equals("b")){
                    if (player.row == enemy1.row && player.col == enemy1.col){
                        player.health -= 3;
                        if (player.getHealth() <= 0){
                            System.out.println("You died!");
                            break;
                        }
                        blockBuff = 1;
                        System.out.println("You block most incoming damage, your next hit deals 50% more damage.\n");
                        System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nEnemy health:" + enemy1.getHealth() + "\n");
                    }
                    else if (player.row == enemy2.row && player.col == enemy2.col){
                        player.health -= 3;
                        if (player.getHealth() <= 0){
                            System.out.println("You died!");
                            break;
                        }
                        blockBuff = 1;
                        System.out.println("You block most incoming damage, your next hit deals 50% more damage.\n");
                        System.out.println("Health:" + player.getHealth() + " / Level:" + player.getLevel() + "\nEnemy health:" + enemy2.getHealth() + "\n");
                    }
                }
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

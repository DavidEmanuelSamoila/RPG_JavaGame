package com.mycompany;

import java.util.Scanner;

import java.util.Random;

public class MyMap {

    Random random = new Random();

    Character character = new Character();

    Enemy enemy = new Enemy();

    Crate crate = new Crate();

    Menu menu = new Menu();

    Scanner scan = new Scanner(System.in);

    char[][] map = new char[15][5];

    String lines = "";

    public boolean killedEnemy = false, touchedCrate = false;

    public MyMap()
    {

        menu.menuSelect();
        setHeroStats();

        while(!character.getKeyPressed().equals("exit"))//If the user types 'exit' at any point, the game ends
        {

            refreshMap();
            character.moveCharacter();//user controls hero's moves

        }

    }

    public int getWidth()//The width of the map
    {

        return map.length;

    }

    public int getHeight()//The height of the map
    {

        return map[0].length;

    }

    public void refreshMap()//The map that the user walks around at
    {

        if(character.getKeyPressed().equals("info"))//Checks if the user needs info about the game
        {
            while(!character.getKeyPressed().equals("ok"))
            {

                menu.infoMenu();//Until the user types 'ok', the info Menu from the Menu class will show up
                character.setKeyPressed(scan.next());

            }

        }

        for(int y = 0; y < map[0].length; y++)
        {

            //With a main class calling this class of course :)

            for(int x = 0; x < map.length; x++)
            {

                map[x][y] = ' ';
                map[x][0] = '-';//Making the top and bottom borders of the map
                map[x][getHeight()-1] = '-';
                map[0][y] = 'I';//Making the left and right borders of the map
                map[getWidth()-1][y] = 'I';

                map[(getWidth()-1)/2][(getHeight()-1)/2] = 'Y';//The center of the map that will spawn an enemy if stepped on

                map[character.getXCoords()][character.getYCoords()] = character.getAppearance();//The hero shows up at his coords

                spawnEnemy();//checks if the game should spawn an enemy

                if(enemy.getLife() && character.getLife())//if the enemy is given life (and the player still lives), the enemy shows up as an 'x'
                {

                    map[enemy.getXCoords()][enemy.getYCoords()] = enemy.getAppearance();

                }

                if(enemy.getYCoords() == character.getYCoords() && enemy.getXCoords() == character.getXCoords())
                {

                    refreshArena();//If you meet the enemy you enter the arena

                }

                spawnCrate();//Checks if the game has to spawn a crate

                checkCrate();//Checks if the hero touches a crate

                if(crate.getLife() && character.getLife())//If a crate is given life, it should appear on the map
                {

                    map[crate.getXCoords()][crate.getYCoords()] = crate.getAppearance();

                }

                lines = map[x][y] + lines;//saves the layout of the map

            }

            System.out.println(lines);//Prints the map

            lines = "";//map is reset

        }

    }

    public void refreshArena()
    {

        while(enemy.getLife() && !character.getKeyPressed().equals("exit"))//runs unless you type 'exit'
        {

            System.out.println("Health: " + enemy.getHealth());//Prints out the enemy's health

            for(int y = 0; y < map[0].length; y++)
            {

                //With a main class calling this class of course :)

                for(int x = 0; x < map.length; x++)
                {

                    map[x][y] = ' ';
                    map[x][0] = '-';
                    map[x][getHeight()-1] = '-';
                    map[0][y] = 'I';
                    map[getWidth()-1][y] = 'I';
                    map[getWidth()-4][getHeight()-2] = character.getAppearance();//the character appears at the bottom left corner of the screen
                    map[2][1] = enemy.getAppearance();//the enemy appears at the top right corner of the screen

                    lines = map[x][y] + lines;//saves the layout of the map/screen

                }

                System.out.println(lines);//prints out the screen/map

                lines = "";

            }
            
            System.out.println("Mana: " + character.getMana());//The characters mana is displayed
            System.out.println("Health: " + character.getHealth());//The characters health is displayed

            character.heroAttack(scan.next());//checks what kind of attack the user want to use next
            refreshMana();//checks if the game needs to reduce the hero's mana

            if(enemy.getHealth() <= 0)
            {

                enemy.setLife(false);
                killedEnemy = true;

            }

            if(enemy.getLife())
            {

                character.setHealth(character.getHealth()-enemy.enemyAttack());//the enemy attacks if it is still alive

            }

            if(character.getHealth() <= 0)//if the hero dies, it's game over
            {

                System.out.println("Game Over!");
                character.setKeyPressed("exit");//so that the game ends

            }

        }

    }

    public void spawnEnemy()
    {

        int randCoords = 0;//sets an int for the random placement of the enemy

        enemy.setHealth(10);//resets the enemy's health always to 10

        if(character.getXCoords() == (getWidth()-1)/2 && character.getYCoords() == (getHeight()-1)/2 && !enemy.getLife())//if the hero and enemy meet
        {

            randCoords = random.nextInt(4);//I got this from the internet, sets a random number from 0-3

            if(randCoords == 0)
            {

                enemy.setXCoords(character.getXCoords());//depending on the random number, the enemy's coords are set as the following:...
                enemy.setYCoords(character.getYCoords()-1);

            } else if(randCoords == 1)
            {

                enemy.setXCoords(character.getXCoords());
                enemy.setYCoords(character.getYCoords()+1);

            } else if(randCoords == 2)
            {

                enemy.setXCoords(character.getXCoords()+2);
                enemy.setYCoords(character.getYCoords());

            } else// (if(randCoords == 3)) I put 'else' just in case
            {

                enemy.setXCoords(character.getXCoords()-2);
                enemy.setYCoords(character.getYCoords());

            }

            enemy.setLife(true);//gives life to enemy
            crate.setLife(false);//kills any crates

        }

    }

    public void setHeroStats()
    {

        character.setHealth(menu.getStatHealth());
        character.setMana(menu.getStatMana());//sets the hero's status according to the Menu' class values given <----
        character.setDamage(menu.getStatDamage());

    }

    public void spawnCrate()
    {

        int randCoords = 0;

        if(killedEnemy && !crate.getLife())//checks if you killed an enemy and if there no crate already there
        {

            randCoords = random.nextInt(4);//I got this from the internet

            if(randCoords == 0)
            {

                crate.setXCoords(3);
                crate.setYCoords(2);//gives the crate coords to be placed

            } else if(randCoords == 1)
            {

                crate.setXCoords(3);
                crate.setYCoords(getHeight()-2);

            } else if(randCoords == 2)
            {

                crate.setXCoords(getWidth()-2);
                crate.setYCoords(2);

            } else //(if(randCoords == 3)) :)
            {

                crate.setXCoords(getWidth()-2);
                crate.setYCoords(getHeight()-2);

            }

            crate.setLife(true);//gives 'life' to the crate
            touchedCrate = false;//resets this boolean to not repeat any process

        }

    }

    public void checkCrate()
    {

        if(character.getXCoords() == crate.getXCoords() && character.getYCoords() == crate.getYCoords() && !touchedCrate)//if hero and crate meet
        {

            System.out.println("Crate Collected!");

            touchedCrate = true;

            if(character.getHealth() <= 5)//gives the hero 3 hp if the hero's health is 5 and under
            {

                character.setHealth(character.getHealth()+3);

            } else
            {

                character.setHealth(character.getHealth()+1);//otherwise you just get +1hp

            }

            if(character.getMana() <= 2)//kind of the same with the mana
            {

                character.setMana(character.getMana()+3);

            } else
            {

                character.setMana(character.getMana()+1);

            }

        }

    }

    public void refreshMana()
    {

        if(character.getMana() >= character.getRemoveMana())//you can continue if you have enough mana
        {

            character.setMana(character.getMana()-character.getRemoveMana());//hero loses mana needed
            enemy.setHealth(enemy.getHealth()-character.getPower());//hero hurts the enemy
            character.setPower(0);//resets value to run well
            character.setRemoveMana(0);//resets value to run well

        } else
        {

            System.out.println("No more mana!");

        }

    }

}


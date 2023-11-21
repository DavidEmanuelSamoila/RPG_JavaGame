
package com.mycompany;

import java.util.Random;

public class Enemy extends CommonFeatures{

    Random random = new Random();

    public Enemy()
    {

        setHealth(10);
        setAppearance('x');
        
    }

    public double enemyAttack()
    {

        int hit = 0;

        hit = random.nextInt(4);//gets a random number to attack

        if(hit == 0)
        {

            System.out.println("The Enemy Missed!");

        } else if(hit == 1 || hit == 2)
        {

            System.out.println("The Enemy hit you!");

        } else if(hit == 3)
        {

            System.out.println("Critical Hit!");

        }

        //hit is the damage the enemy will deal to the hero/character (I'm saying hero because typing character so many times is a bit exhausting :) )

        return hit;

    }
    
}

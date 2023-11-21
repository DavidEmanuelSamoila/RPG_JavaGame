
package com.mycompany;

import java.util.Scanner;

public class Character extends CommonFeatures{

    private String keyPressed = "";

    private double power = 0, removeMana = 0;

    public Character()
    {

        setXCoords(1);
        setYCoords(2);
        
        setAppearance('v');
        
        setLife(true);
        
    }

    public String getKeyPressed()
    {

        return keyPressed;

    }

    public void setKeyPressed(String keyIn)
    {

        keyPressed = keyIn;

    }

    public double getPower()
    {

        return power;

    }

    public void setPower(double p)
    {

        power = p;

    }

    public int getRemoveMana()
    {

        return (int)removeMana;

    }

    public void setRemoveMana(double reMana)
    {

        removeMana = reMana;

    }

    public void moveCharacter()
    {

        Scanner scan = new Scanner(System.in);

        if(!keyPressed.equals("exit"))
        {

            try
            {

                keyPressed = scan.next();//waits for the user to type something

            } catch (Exception exception)
            {

                System.out.println("NO! Try again...");

            }

        }

        if (keyPressed.equals("w"))//the user can move the hero by typing 'w,a,s,d'
        {

            setYCoords(getYCoords()-1);

        } else if (keyPressed.equals("s"))
        {

            setYCoords(getYCoords()+1);

        } else if (keyPressed.equals("a"))
        {

           setXCoords(getXCoords()+2);//moves by 2 so that it looks better and more uniform moving

        } else if (keyPressed.equals("d"))
        {

            setXCoords(getXCoords()-2);//moves by 2 so that it looks better and more uniform moving

        }
        
    }

    public void heroAttack(String attack)
    {

        setRemoveMana(0);//resets value to run well

        if(attack.equals("hit"))//user gets to choose an attack type
        {

            setPower(getDamage());

        } else if(attack.equals("special") && getMana() >= 3)
        {

            setRemoveMana(3);//sets a number to remove the mana from the 'character' from the 'MyMap' class
            setPower(getDamage()*2);

        } else if(attack.equals("heal") && getMana() >= 2)
        {

            setRemoveMana(2);
            setHealth(getHealth()+2);

        } else
        {

            System.out.println("Can't do that! You missed!");// if you do anything else during battle, you just miss

        }

    }



}

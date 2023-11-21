package com.mycompany;

import java.util.Scanner;

public class Menu{

    private boolean selected = false;

    Mage mage = new Mage();

    Knight knight = new Knight();

    Scanner scan = new Scanner(System.in);

    private double statHealth, statMana, statDamage;

    public Menu()
    {



    }

    public double getStatHealth()
    {

        return statHealth;

    }

    public void setStatHealth(double statH)
    {

        statHealth = statH;

    }

    public double getStatMana()
    {

        return statMana;

    }

    public void setStatMana(double statM)
    {

        statMana = statM;

    }

    public double getStatDamage()
    {

        return statDamage;

    }

    public void setStatDamage(double statD)
    {

        statDamage = statD;

    }

    public void selectCharacter(String hero)//the user gets the chance to chose a type of hero with different stats
    {

        if(hero.equals("mage"))
        {

            setStatHealth(mage.getHealth());
            setStatMana(mage.getMana());
            setStatDamage(mage.getDamage());
            selected = true;

        } else if(hero.equals("knight"))
        {

            setStatHealth(knight.getHealth());
            setStatMana(knight.getMana());
            setStatDamage(knight.getDamage());
            selected = true;

        } else
        {

            System.out.println("Please try again...");//If the user mistypes or something...

        }

    }

    public void menuSelect()
    {

        System.out.println("Welcome to this RPG Game made by David Samoila!");
        System.out.println("In this game you get to choose your character.");
        System.out.println("The 'Mage' has " + mage.getHealth() + " health points,");
        System.out.println(mage.getMana() + " mana points,");
        System.out.println(mage.getDamage() + " damage points.");
        System.out.println("The 'Knight' has " + knight.getHealth() + " health points,");
        System.out.println(knight.getMana() + " mana points,");
        System.out.println(knight.getDamage() + " damage points.");
        System.out.println("Choose one(ex. 'mage'): ");//this is just the intro

        while(!selected)
        {

            selectCharacter(scan.next());//runs until you actually select one

        }

        System.out.println("If you need information about the gameplay, type 'info'.");

    }

    public void infoMenu()
    {

        System.out.println("To walk around, type 'w,a,s,d' and hit <enter>.");
        System.out.println("Go to 'Y' to summon an enemy('x').");
        System.out.println("After you kill enemies you might get a crate('o').");
        System.out.println("When you fight, you can type 'hit'(does not use mana, low damage), 'heal'(uses 3 mana and heals 2 points),");
        System.out.println("Or use your hero's special by typing 'special'(does double the damage, uses 3 mana)");
        System.out.println("You get more mana and health from crates('o')");
        System.out.println("Type 'ok' to continue: ");

    }

}

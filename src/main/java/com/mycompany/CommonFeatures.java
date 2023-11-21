
package com.mycompany;

public class CommonFeatures{
    
    private double health, damage, mana;
    
    private int xCoords, yCoords;
    
    private char appearance;
    
    private boolean isAlive;

    public CommonFeatures()
    {
        
        //    :)
        
    }

    public double getMana()
    {

        return mana;

    }

    public void setMana(double mana)
    {

        this.mana = mana;

    }

    public double getHealth()
    {

        return health;

    }

    public void setHealth(double hp)
    {
        
        health = hp;
        
    }
    
    public void setDamage(double dmg) 
    {
        
        damage = dmg;
        
    }
    
    public double getDamage()
    {
        
        return damage;
    }
    
    public int getXCoords()
    {
        
        return xCoords;
        
    }

    public void setXCoords(int coords)
    {

        xCoords = coords;

    }
    
    public int getYCoords()
    {
        
        return yCoords;
        
    }

    public void setYCoords(int coords)
    {
        
        yCoords = coords;
        
    }
    
    public char getAppearance()
    {
        
        return appearance;
        
    }
    
    public void setAppearance(char look)
    {
        
        appearance = look;
        
    }
    
    public void setLife(boolean lives)
    {
        
        isAlive = lives;
        
    }
    
    public boolean getLife()
    {
        
        return isAlive;
        
    }

}

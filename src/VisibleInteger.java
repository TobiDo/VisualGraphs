// WARNING: This file is auto-generated and any changes to it will be overwritten
import java.util.*;
import greenfoot.*;
import java.awt.Color;

/**
 * The representation of an integer  .. not ready yet
 */
public class VisibleInteger extends Actor
{
    private int value;
    private int size = 40;

    /**
     * 
     */
    public VisibleInteger(int value)
    {
        this.value = value;
        drawMe();
    }

    /**
     * 
     */
    public Integer getValue()
    {
        return value;
    }

    /**
     * 
     */
    public void setValue(int value)
    {
        this.value = value;
        drawMe();
    }

    /**
     * 
     */
    public void drawMe()
    {
        GreenfootImage image =  new  GreenfootImage(size, size);
        image.drawString(String.valueOf(value), 10, size - 10);
        this.setImage(image);
    }

    /**
     * Act - do whatever the VisibleInteger wants to do. This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
    }
}

// WARNING: This file is auto-generated and any changes to it will be overwritten
import java.util.*;
import greenfoot.*;
import java.awt.Color;
import java.awt.Font;

/**
 * 
 */
public class Display extends Actor
{
    private String message;
    private GreenfootImage image;
    private static final Font font =  new  Font(Font.MONOSPACED, Font.PLAIN, 15);
    private static Display instance;
    public int width;
    public final int height = 25;

    /**
     * 
     */
    private Display()
    {
        this.message = "";
    }

    /**
     * 
     */
    public void addedToWorld(World world)
    {
        drawMe();
    }

    /**
     * 
     */
    public void drawMe()
    {
        this.image =  new  GreenfootImage(this.width, this.height);
        this.image.drawRect(1, 1, this.width - 2, this.height - 2);
        this.image.setFont(Display.font);
        this.image.drawString(this.message, 2, 20);
        this.setImage(this.image);
    }

    /**
     * 
     */
    static public Display getInstance()
    {
        if (instance == null) {
            instance =  new  Display();
        }
        return instance;
    }

    /**
     * 
     */
    public void setMessage(String message)
    {
        this.message = message;
        drawMe();
    }

    /**
     * 
     */
    public void clearMessage()
    {
        this.message = "";
        drawMe();
    }

    /**
     * 
     */
    public void setWidth(int width)
    {
        this.width = width;
    }

    /**
     * Act - do whatever the Display wants to do. This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
    }
}

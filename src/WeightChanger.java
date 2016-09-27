// WARNING: This file is auto-generated and any changes to it will be overwritten
import java.util.*;
import greenfoot.*;
import java.awt.Color;

/**
 * 
 */
public class WeightChanger extends Actor
{
    private Edge edge;
    private Graph graph;
    private int intermediateWeight = 0;
    private World world;
    private int x;
    private int y;
    private Switch switcher = Switch.getInstance();

    /**
     * 
     */
    public WeightChanger(Edge edge, Graph graph)
    {
        this.edge = edge;
        this.graph = graph;
    }

    /**
     * 
     */
    protected void addedToWorld(World world)
    {
        /* remember the world we have been added to and also where we are in the world*/
        this.world = world;
        this.x = this.getX();
        this.y = this.getY();
    }

    /**
     * 
     */
    public void addDigit(int digit)
    {
        /* assumes that 0 <= digit <=9*/
        intermediateWeight = intermediateWeight * 10 + digit;
        drawMe();
    }

    /**
     * 
     */
    public void removeLastDigit()
    {
        intermediateWeight = intermediateWeight / 10;
        drawMe();
    }

    /**
     * 
     */
    public void setEdge(Edge edge)
    {
        this.edge = edge;
        if (edge == null) {
            getWorld().removeObject(this);
        }
        else {
            world.addObject(this, x, y);
        }
    }

    /**
     * 
     */
    public void drawMe()
    {
        GreenfootImage image =  new  GreenfootImage(100, 20);
        image.drawString(String.valueOf(intermediateWeight), 0, 20);
        this.setImage(image);
    }

    /**
     * Act - do whatever the WeightChanger wants to do. This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        String key = Greenfoot.getKey();
        if (switcher.getState().equals("Change weight")) {
            if (key != null && this.edge != null) {
                if (key.equals("enter")) {
                    this.edge.setWeight(intermediateWeight);
                    this.edge.drawMe(Color.black);
                    this.intermediateWeight = 0;
                    this.drawMe();
                    this.setEdge(null);
                    return;
                }
                else if (key.equals("backspace")) {
                    removeLastDigit();
                }
                else {
                    int digit;
                    try {
                        digit = Integer.parseInt(key);
                    }
                    catch (NumberFormatException e) {
                        return;
                    }
                    addDigit(digit);
                }
            }
        }
    }
}

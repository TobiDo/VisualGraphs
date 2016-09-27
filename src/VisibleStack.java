// WARNING: This file is auto-generated and any changes to it will be overwritten
import java.util.*;
import greenfoot.*;
import java.awt.Color;

/**
 * 
 */
public class VisibleStack extends Actor
{
    private Stack<Vertex> stack;
    private Stack<Vertex> cloneStack;
    private int height;
    private int width;
    private int margin = 0;
    private int numberOfElements;
    private int elementSize = 40;

    /**
     * 
     */
    public VisibleStack()
    {
        stack =  new Stack < Vertex > ();
        cloneStack =  new Stack < Vertex > ();
        width = 40;
        height = 400;
        numberOfElements = 0;
    }

    /**
     * 
     */
    public int getTopY()
    {
        return getY() - height / 2;
    }

    /**
     * 
     */
    public int getBottomY()
    {
        return getY() + (height - elementSize) / 2;
    }

    /**
     * 
     */
    public int getCurrentY()
    {
        return getBottomY() - numberOfElements * (elementSize + margin);
    }

    /**
     * 
     */
    public Vertex pop()
    {
        Vertex displayNode = cloneStack.pop();
        Vertex node = stack.pop();
        int y = displayNode.getY();
        while (y > getTopY()) {
            Greenfoot.delay(1);
            y = y - 5;
            displayNode.setLocation(displayNode.getX(), y);
        }
        node.mark();
        node.highlight();
        Greenfoot.delay(20);
        getWorld().removeObject(displayNode);
        numberOfElements = numberOfElements - 1;
        return node;
    }

    /**
     * 
     */
    public void push(Vertex item)
    {
        Vertex itemClone = item.getVisu();
        cloneStack.push(itemClone);
        stack.push(item);
        getWorld().addObject(itemClone, getX(), getTopY());
        int y = getTopY();
        while (y < getCurrentY()) {
            Greenfoot.delay(1);
            y = y + 5;
            itemClone.setLocation(itemClone.getX(), y);
        }
        numberOfElements = numberOfElements + 1;
    }

    /**
     * 
     */
    protected void addedToWorld(World world)
    {
        super.addedToWorld(world);
        drawMe();
    }

    /**
     * 
     */
    public void drawMe()
    {
        GreenfootImage edgeImage =  new GreenfootImage(this.width, this.height);
        edgeImage.setColor(Color.black);
        edgeImage.drawLine(1, 1, 1, this.height - 1);
        edgeImage.drawLine(1, this.height - 1, this.width - 1, this.height - 1);
        edgeImage.drawLine(this.width - 1, 1, this.width - 1, this.height - 1);
        this.setImage(edgeImage);
    }

    /**
     * 
     */
    public boolean empty()
    {
        return numberOfElements == 0;
    }

    /**
     * Act - do whatever the visibleStack wants to do. This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
    }
}

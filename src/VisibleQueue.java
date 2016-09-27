// WARNING: This file is auto-generated and any changes to it will be overwritten
import java.util.*;
import greenfoot.*;
import java.awt.Color;

/**
 * 
 */
public class VisibleQueue extends Actor
{
    private Queue<Vertex> queue;
    private LinkedList<Vertex> visibleQueue;
    private int width;
    private int height;
    private int numberOfElements;
    private int sizeOfElement;

    /**
     * 
     */
    public VisibleQueue(int width, int sizeOfElement)
    {
        queue =  new LinkedList < Vertex > ();
        visibleQueue =  new LinkedList < Vertex > ();
        this.sizeOfElement = sizeOfElement;
        this.width = width;
        this.height = sizeOfElement + 10;
        numberOfElements = 0;
        drawMe();
    }

    /**
     * 
     */
    public void drawMe()
    {
        GreenfootImage image =  new GreenfootImage(width, height);
        image.drawLine(1, 1, width - 1, 1);
        image.drawLine(1, 1, 1, 5);
        image.drawLine(1, height - 5, 1, height - 1);
        image.drawLine(1, height - 1, width - 1, height - 1);
        image.drawLine(width - 1, 1, width - 1, 5);
        image.drawLine(width - 1, height - 5, width - 1, height - 1);
        this.setImage(image);
    }

    /**
     * 
     */
    public boolean isEmpty()
    {
        return queue.isEmpty();
    }

    /**
     * 
     */
    public int getLeftX()
    {
        return getX() - width / 2;
    }

    /**
     * 
     */
    public int getRightX()
    {
        return getX() + width / 2;
    }

    /**
     * 
     */
    public int getCurrentX()
    {
        return getRightX() - ((2 * numberOfElements - 1) * sizeOfElement) / 2;
    }

    /**
     * 
     */
    public boolean add(Vertex item)
    {
        queue.add(item);
        numberOfElements = numberOfElements + 1;
        Vertex visibleItem = item.getVisu();
        int x = getLeftX();
        getWorld().addObject(visibleItem, x, getY());
        while (x < getCurrentX()) {
            Greenfoot.delay(1);
            x = x + 5;
            visibleItem.setLocation(x, getY());
        }
        /* TODO: look up what add should return*/
        visibleQueue.add(visibleItem);
        return true;
    }

    /**
     * 
     */
    public Vertex poll()
    {
        Vertex item = queue.poll();
        Vertex visibleItem = visibleQueue.poll();
        int x = visibleItem.getX();
        int shift = 0;
        while (shift < sizeOfElement) {
            Greenfoot.delay(1);
            shift = shift + 1;
            x = x + 1;
            visibleItem.setLocation(x, getY());
        }
        visibleItem.mark();
        item.mark();
        Greenfoot.delay(20);
        getWorld().removeObject(visibleItem);
        int count = 0;
        while (count < sizeOfElement / 5) {
            for (final Vertex vertex : visibleQueue) {
                vertex.moveRight(5);
            }
            count = count + 1;
            Greenfoot.delay(1);
        }
        numberOfElements = numberOfElements - 1;
        return item;
    }

    /**
     * 
     */
    public Vertex peek()
    {
        return queue.peek();
    }

    /**
     * 
     */
    public Vertex element()
    {
        return queue.element();
    }

    /**
     * 
     */
    public boolean offer(Vertex item)
    {
        return queue.offer(item);
    }

    /**
     * 
     */
    public Vertex remove()
    {
        return queue.remove();
    }

    /**
     * 
     */
    public void removeFromWorld()
    {
        Vertex vertex = visibleQueue.poll();
        while (vertex != null) {
            getWorld().removeObject(vertex);
            vertex = visibleQueue.poll();
        }
        getWorld().removeObject(this);
    }

    /**
     * 
     */
    public void clear()
    {
        queue.clear();
    }

    /**
     * Act - do whatever the VisibleQueue wants to do. This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
    }
}

// WARNING: This file is auto-generated and any changes to it will be overwritten
import java.util.*;
import greenfoot.*;
import java.awt.Color;

/**
 * Ideally this would implement the Map interface
 */
public class VisibleTable extends Actor
{
    /* need a list because we want to keep the keys in a fxed order when visualizing the map*/
    private List<Vertex> keys;
    private HashMap<Vertex,VisibleInteger> map;
    /* width of entries*/
    private int size = 40;
    private int width;
    private int numberOfColumns;

    /**
     * 
     */
    public VisibleTable()
    {
        keys =  new  LinkedList < Vertex > ();
        map =  new  HashMap < Vertex, VisibleInteger > ();
        numberOfColumns = 10;
        width = numberOfColumns * size;
    }

    /**
     * 
     */
    public void addedToWorld(World world)
    {
        drawMe();
    }

    /**
     * Get x-Coordinate of center of element at position
     */
    public int getX(int position)
    {
        return getX() - width / 2 + size / 2 + position * size;
    }

    /**
     * 
     */
    public Integer put(Vertex key, Integer value)
    {
        VisibleInteger previous = map.get(key);
        Integer previousValue = null;
        if (previous != null) {
            previousValue = previous.getValue();
            previous.setValue(value);
            previous.drawMe();
        }
        else {
            map.put(key,  new  VisibleInteger(value));
            keys.add(key);
            int position = keys.indexOf(key);
            getWorld().addObject(key.getVisu(), getX(position), getY() - size / 2);
            getWorld().addObject(map.get(key), getX(position), getY() + size / 2);
        }
        return previousValue;
    }

    /**
     * 
     */
    public Set<Vertex> keySet()
    {
        return map.keySet();
    }

    /**
     * 
     */
    public Integer get(Vertex vertex)
    {
        VisibleInteger result = map.get(vertex);
        if (result == null) {
            return null;
        }
        return result.getValue();
    }

    /**
     * 
     */
    public void drawMe()
    {
        GreenfootImage image =  new  GreenfootImage(width + 1, 2 * size + 1);
        image.drawRect(0, 0, width, 2 * size);
        image.drawLine(0, size, width, size);
        int column = 1;
        while (column < numberOfColumns) {
            image.drawLine(column * size, 0, column * size, 2 * size);
            column = column + 1;
        }
        this.setImage(image);
    }

    /**
     * Act - do whatever the VisibleTable wants to do. This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
    }
}

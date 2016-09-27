// WARNING: This file is auto-generated and any changes to it will be overwritten
import java.util.*;
import greenfoot.*;
import java.awt.Color;

/**
 * <div>Icons made by <a href="http://www.flaticon.com/authors/pavel-kozlov" title="Pavel Kozlov">Pavel Kozlov</a> from <a href="http://www.flaticon.com" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
 */
public class EdgeDeleter extends Actor
{
    private Edge edge;
    private Graph graph;
    private World world;
    private int x;
    private int y;
    private static EdgeDeleter instance;

    /**
     * This class is a Singleton - that means there exists only one instance of it
     */
    private EdgeDeleter()
    {
    }

    /**
     * 
     */
    static public EdgeDeleter getInstance()
    {
        if (instance == null) {
            instance =  new EdgeDeleter();
        }
        return instance;
    }

    /**
     * 
     */
    public void setGraph(Graph graph)
    {
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
    public Edge getEdge()
    {
        return edge;
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
     * Act - do whatever the EdgeDeleter wants to do. This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            if (edge != null) {
                graph.deleteEdge(edge);
                getWorldOfType(MyWorld.class).unsetEdge();
                this.setEdge(null);
            }
        }
    }
}

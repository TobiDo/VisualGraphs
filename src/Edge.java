// WARNING: This file is auto-generated and any changes to it will be overwritten
import java.util.*;
import greenfoot.*;
import java.awt.Color;

/**
 * 
 */
public class Edge extends Actor
{
    private Vertex start;
    private Vertex end;
    private int width;
    private int height;
    /* Zur Demonstration sind ganzzahlige Werte ausreichend*/
    private int weight;

    /**
     * 
     */
    public Edge(Vertex start, Vertex end)
    {
        this.start = start;
        this.end = end;
        weight = 1;
    }

    /**
     * 
     */
    public int getWeight()
    {
        return this.weight;
    }

    /**
     * 
     */
    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    /**
     * 
     */
    public int getUpperLeftX()
    {
        return min(start.getX(), end.getX());
    }

    /**
     * 
     */
    public int getUpperLeftY()
    {
        return min(start.getY(), end.getY());
    }

    /**
     * 
     */
    public int getCenterX()
    {
        return (start.getX() + end.getX()) / 2;
    }

    /**
     * 
     */
    public int getCenterY()
    {
        return (start.getY() + end.getY()) / 2;
    }

    /**
     * 
     */
    public int max(int a, int b)
    {
        if (a >= b) {
            return a;
        }
        else {
            return b;
        }
    }

    /**
     * 
     */
    public int min(int a, int b)
    {
        return  - max( - a, - b);
    }

    /**
     * 
     */
    public void drawMe(Color color)
    {
        this.setLocation(this.getCenterX(), this.getCenterY());
        this.setImage(Images.edgeImage(this, color));
    }

    /**
     * 
     */
    public void mark()
    {
        this.drawMe(Color.red);
    }

    /**
     * 
     */
    public void unmark()
    {
        this.drawMe(Color.black);
    }

    /**
     * 
     */
    public boolean isAdjacent(Vertex vertex)
    {
        /* returns true if and only if vertex is start of edge OR end of edge*/
        return start.equals(vertex) || end.equals(vertex);
    }

    /**
     * 
     */
    public Vertex getStart()
    {
        return start;
    }

    /**
     * 
     */
    public Vertex getEnd()
    {
        return end;
    }

    /**
     * 
     */
    public Vertex getOther(Vertex vertex)
    {
        /* Assumes that argument vertex is adjacent to this edge. Returns the other edge.*/
        if (getStart().equals(vertex)) {
            return getEnd();
        }
        else {
            return getStart();
        }
    }

    /**
     * 
     */
    public Edge getInverted()
    {
        return  new Edge(this.getEnd(), this.getStart());
    }

    /**
     * 
     */
    public void remove()
    {
        this.getWorld().removeObject(this);
    }

    /**
     * 
     */
    public boolean equals(Object o)
    {
        if (o == null) {
            return false;
        }
        Edge edge = (Edge)o;
        return edge.getStart().equals(this.getStart()) && edge.getEnd().equals(this.getEnd());
    }

    /**
     * 
     */
    public Graph getGraph()
    {
        return start.getGraph();
    }

    /**
     * Act - do whatever the Edge wants to do. This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (getGraph().getSwitcherState().equals("Delete Edge") || getGraph().getSwitcherState().equals("Change weight")) {
            if (Greenfoot.mouseClicked(this)) {
                /* Die Idee ist, dass die Kante nur ausgewählt wird wenn sie wirklich angeklickt wird und nicht auch denn, wenn nur in das Rechteck, dessen Diagonale sie ist, geklickt wird. Funktioniert noch nicht richtig. Außerdem funktioniert es auch nicht, wenn sich die umgebenden Rechtecke überlagern, da Greenfoot den Mouse-Event immer nur am weitesten oben liegenden Actor "meldet".*/
                int mouseX = Greenfoot.getMouseInfo().getX();
                int mouseY = Greenfoot.getMouseInfo().getY();
                int connectionY = (mouseX - start.getX()) * (end.getY() - start.getY()) / (end.getX() - start.getX());
                if (Math.abs(connectionY - mouseY + start.getY()) <= 20) {
                    getWorldOfType(MyWorld.class).handle(this, getGraph().getSwitcherState());
                }
            }
        }
        else if (getGraph().getSwitcherState().equals("Change weight")) {
            String key = Greenfoot.getKey();
            if (key != null) {
                System.out.println(key);
            }
        }
    }
}

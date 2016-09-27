// WARNING: This file is auto-generated and any changes to it will be overwritten
import java.util.*;
import greenfoot.*;
import java.awt.Color;

/**
 * 
 */
public class Vertex extends Actor
{
    private String name;
    /* List of neighbours of the vertex. Name followers is an artefact of an earlier implementation for directed graphs*/
    private List<Vertex> followers;
    /* Diameter of the circle that represents the vertex*/
    private int width = Images.vertexSize;
    private int margin = Images.vertexMargin;
    /* The graph that this vertex belongs to if existent*/
    private Graph graph;
    /* Describes wether the edge is (visually) marked or not*/
    private boolean marked;

    /**
     * 
     */
    public Vertex(String name)
    {
        this.name = name;
        drawMe(Color.black);
        followers =  new LinkedList < Vertex > ();
        graph = null;
        marked = false;
    }

    /**
     * 
     */
    public Vertex(String name, Graph graph)
    {
         new Vertex(name);
        this.graph = graph;
    }

    /**
     * 
     */
    public Graph getGraph()
    {
        return graph;
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
    public void addFollower(Vertex newFollower)
    {
        followers.add(newFollower);
    }

    /**
     * 
     */
    public void removeFollower(Vertex follower)
    {
        followers.remove(follower);
    }

    /**
     * 
     */
    public List<Vertex> getFollowers()
    {
        return followers;
    }

    /**
     * 
     */
    public int getCenterX()
    {
        return width / 2;
    }

    /**
     * 
     */
    public int getCenterY()
    {
        return width / 2;
    }

    /**
     * Draws the image of the vertex.
     */
    public void drawMe(Color color)
    {
        this.setImage(Images.vertexImage(this, color));
    }

    /**
     * 
     */
    public void mark()
    {
        marked = true;
        this.drawMe(Color.gray);
    }

    /**
     * 
     */
    public void unmark()
    {
        marked = false;
        this.drawMe(Color.black);
    }

    /**
     * Highlights the vertex, works only if the vertex belongs to graph
     */
    public void highlight()
    {
        if (graph != null) {
            /* Delegated because we want to make sure that only one vertex of the graphs is highlighted at a time*/
            graph.highlightVertex(this);
        }
    }

    /**
     * 
     */
    public boolean isMarked()
    {
        return marked;
    }

    /**
     * 
     */
    public Vertex getVisu()
    {
        return  new Vertex(this.name);
    }

    /**
     * 
     */
    public void moveRight(int shift)
    {
        this.setLocation(getX() + shift, getY());
    }

    /**
     * Returns the set of edges that start/end in this vertex
     */
    public Set<Edge> getAdjacentEdges()
    {
        if (graph != null) {
            return graph.getAdjacentEdges(this);
        }
        else {
            return  new HashSet < Edge > ();
        }
    }

    /**
     * 
     */
    public String getName()
    {
        return name;
    }

    /**
     * 
     */
    public void remove()
    {
        getWorld().removeObject(this);
    }

    /**
     * Act - do whatever the Vertex wants to do. This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (graph != null) {
            if (graph.getSwitcherState().equals("Move Vertex")) {
                if (Greenfoot.mouseDragged(this)) {
                    this.setLocation(Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
                    for (final Edge edge : getAdjacentEdges()) {
                        /* redraw edges here doesn't work easily somehow*/
                        edge.drawMe(Color.black);
                    }
                }
            }
            else if (graph.getSwitcherState().equals("Add Edge") || graph.getSwitcherState().equals("Tiefensuche") || graph.getSwitcherState().equals("Breitensuche") || graph.getSwitcherState().equals("Dijkstra")) {
                if (Greenfoot.mouseClicked(this)) {
                    getWorldOfType(MyWorld.class).handle(this, graph.getSwitcherState());
                }
            }
        }
    }
}

// WARNING: This file is auto-generated and any changes to it will be overwritten
import java.util.*;
import greenfoot.*;
import java.awt.Color;

/**
 * Represents a simple (that means: not more than one edge bewtween two vertices) undirected Graph. 
 */
public class Graph extends Actor
{
    /* Width and height of the rectangle in which the Graph is visualized*/
    private int width;
    private int height;
    private Set<Vertex> vertices;
    private Set<Edge> edges;
    private Switch switcher;
    /* Exactly one vertex of the graph can be highlighted via hightlight(). NOTE: The method mark () can mark more than one vertex.*/
    private Vertex highlightedVertex;
    

    /**
     * 
     */
    public Graph(int width, int height)
    {
        this.width = width;
        this.height = height;
        vertices =  new  HashSet < Vertex > ();
        edges =  new  HashSet < Edge > ();
        switcher = Switch.getInstance();
        highlightedVertex = null;
    }

    /**
     * 
     */
    public void addVertex(Vertex vertex)
    {
        vertex.setGraph(this);
        vertices.add(vertex);
    }

    /**
     * 
     */
    public Vertex createVertex()
    {
        /* creates a new vertex with a name that is not already taken - so far no more than 26 vertices can be created in this way*/
        int i = 0;
        String name;
        while (i < 26) {
            name = String.valueOf((char)('A' + i));
            boolean contained = false;
            for (final Vertex vertex : vertices) {
                if (vertex.getName().equals(name)) {
                    contained = true;
                    break;
                }
            }
            if ( ! contained) {
                Vertex newVertex =  new  Vertex(name);
                this.addVertex(newVertex);
                return newVertex;
            }
            i = i + 1;
        }
        return null;
    }

    /**
     * 
     */
    public void addUndirectedEdge(Vertex vertexA, Vertex vertexB)
    {
        vertexA.addFollower(vertexB);
        vertexB.addFollower(vertexA);
        Edge edge =  new  Edge(vertexA, vertexB);
        getWorld().addObject(edge, edge.getUpperLeftX(), edge.getUpperLeftY());
        edge.drawMe(Color.black);
        edges.add(edge);
    }

    /**
     * 
     */
    public void addDirectedEdge(Vertex start, Vertex end)
    {
        /* don't use */
        start.addFollower(end);
        Edge edge =  new  Edge(start, end);
        getWorld().addObject(edge, edge.getUpperLeftX(), edge.getUpperLeftY());
        edge.drawMe(Color.black);
        edges.add(edge);
    }

    /**
     * 
     */
    public Set<Edge> getAdjacentEdges(Vertex vertex)
    {
        Set<Edge> adjacent =  new  HashSet < Edge > ();
        for (final Edge edge : edges) {
            if (edge.isAdjacent(vertex)) {
                adjacent.add(edge);
            }
        }
        return adjacent;
    }

    /**
     * 
     */
    public void deleteEdge(Edge edge)
    {
        /* TODO: this is a terrible hack -> improve!*/
        /* delete edges from world*/
        for (final Edge edge2 : edges) {
            if (edge2.equals(edge) || edge2.equals(edge.getInverted())) {
                edge2.remove();
            }
        }
        /* delete edges form graph*/
        edge.getStart().removeFollower(edge.getEnd());
        edge.getEnd().removeFollower(edge.getStart());
        edges.remove(edge);
        edges.remove(edge.getInverted());
    }

    /**
     * 
     */
    public String getSwitcherState()
    {
        return this.switcher.getState();
    }

    /**
     * 
     */
    public void highlightVertex(Vertex vertex)
    {
        /* highlights vertex; only one vertex of the graph can be highlighted at a time*/
        if (highlightedVertex != null) {
            if (highlightedVertex.isMarked()) {
                highlightedVertex.mark();
            }
            else {
                highlightedVertex.drawMe(Color.black);
            }
        }
        highlightedVertex = vertex;
        highlightedVertex.drawMe(Color.red);
    }

    /**
     * 
     */
    public void unhighlightVertex()
    {
        if (highlightedVertex != null) {
            highlightedVertex.drawMe(Color.black);
            highlightedVertex = null;
        }
    }

    /**
     * 
     */
    public void unmarkAll()
    {
        for (final Vertex vertex : vertices) {
            vertex.unmark();
        }
    }

    /**
     * 
     */
    public void bfsearch(Vertex start, Vertex target)
    {
        /* Breitensuche*/
        Display display = Display.getInstance();
        VisibleQueue visibleQueue =  new  VisibleQueue(this.width - 100, 30);
        getWorld().addObject(visibleQueue, this.getX() + 50, getWorld().getHeight() - 40);
        Set<Vertex> added =  new  HashSet < Vertex > ();
        visibleQueue.add(start);
        added.add(start);
        while ( ! visibleQueue.isEmpty()) {
            Greenfoot.delay(10);
            Vertex current = visibleQueue.poll();
            current.mark();
            display.setMessage("Visit vertex: " + current.getName());
            this.highlightVertex(current);
            if (current.equals(target)) {
                /* Der gesuchte Knoten wurde gefunden, markiere diesen grün. Im Anschluss könnte break stehen. Aus Demonstrationszwecken wird der Algorithmus aber weiterlaufen gelassen.*/
                current.drawMe(Color.green);
                display.setMessage("Found the vertex.");
                break;
            }
            Greenfoot.delay(50);
            List<Vertex> followers = current.getFollowers();
            for (final Vertex vertex : followers) {
                display.setMessage("Add neighbours of " + current.getName() + " to queue.");
                if ( ! added.contains(vertex)) {
                    visibleQueue.add(vertex);
                    added.add(vertex);
                }
            }
            display.clearMessage();
        }
        Greenfoot.delay(150);
        display.clearMessage();
        visibleQueue.removeFromWorld();
        unmarkAll();
    }

    /**
     * 
     */
    public void dfsearch(Vertex start, Vertex target)
    {
        Display display = Display.getInstance();
        /* ein graphisch sichtbarer Stapel, der nach dem Last-In-First-Out Prinzip funktioniert*/
        VisibleStack stack =  new  VisibleStack();
        getWorld().addObject(stack, getWorld().getWidth() - 60, 300);
        /* lege den Startknoten auf den (noch leeren) Stapel*/
        stack.push(start);
        Set<Vertex> besuchteKnoten =  new  HashSet < Vertex > ();
        /* Füge Startknoten der Menge der berücksichtigten Knoten hinzu*/
        besuchteKnoten.add(start);
        while ( ! stack.empty()) {
            Vertex node = stack.pop();
            /* hole den obersten Knoten vom Stapel*/
            if (node.equals(target)) {
                node.drawMe(Color.green);
                display.setMessage("Found the vertex.");
                break;
            }
            /* Besuche den aktuellen Knoten, hier dargestellt durch ein Hervorheben des Knotens.*/
            /*  TODO: Untersuche ob Knoten gefunden wurde. */
            for (final Vertex node2 : node.getFollowers()) {
                /* Durchlaufe die Nachbarn des aktuellen Knotens*/
                if ( ! besuchteKnoten.contains(node2)) {
                    /* Lege alle Nachbarn, die noch nicht berücksichtigt wurden auf den Stapel*/
                    display.setMessage("Put neighbours of " + node.getName() + " on the stack.");
                    stack.push(node2);
                    besuchteKnoten.add(node2);
                }
            }
            display.clearMessage();
        }
        Greenfoot.delay(150);
        getWorld().removeObject(stack);
        display.clearMessage();
        this.unmarkAll();
    }

    /**
     * 
     */
    public void playAround()
    {
        for (final Vertex vertex : vertices) {
            vertex.mark();
            Greenfoot.delay(20);
        }
        Greenfoot.delay(100);
        for (final Vertex vertex : vertices) {
            vertex.unmark();
        }
    }

    /**
     * 
     */
    public void drawMe()
    {
        GreenfootImage image =  new  GreenfootImage(this.width, this.height);
        image.drawRect(0, 0, this.width - 1, this.height - 1);
        this.setImage(image);
    }

    /**
     * Act - do whatever the Graph wants to do. This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            if (getSwitcherState().equals("Add Vertex")) {
                Vertex newVertex = this.createVertex();
                getWorld().addObject(newVertex, Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
            }
        }
    }
}

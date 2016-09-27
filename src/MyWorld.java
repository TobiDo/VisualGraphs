// WARNING: This file is auto-generated and any changes to it will be overwritten
import java.util.*;
import greenfoot.*;
import java.awt.Color;

/**
 * The MyWorld class takes care of positioning the graph, switches and display etc.
 */
public class MyWorld extends World
{
    /* some operations require to remember a vertex or edge*/
    private Vertex savedVertex;
    private Edge savedEdge = null;
    /*  the world can display exactly one graph so far*/
    private Graph graph;
    /* a helper class to confirm edge deletions*/
    private EdgeDeleter edgeDeleter;
    /* a helper class to change the weight of edges*/
    private WeightChanger weightChanger;
    private static final int worldWidth = 1000;
    private static final int worldHeight = 600;
    private static final int graphWidth = 700;
    private static final int graphHeight = 450;
    private static final int topMargin = 30;
    private static final int leftMargin = 5;

    /**
     * Constructor for objects of class MyWorld.
     */
    public MyWorld()
    {
        super(MyWorld.worldWidth, MyWorld.worldHeight, 1);
        setPaintOrder(Vertex.class, Edge.class, Graph.class);
        /* create example graph*/
        graph =  new  Graph(MyWorld.graphWidth, MyWorld.graphHeight);
        addObject(graph, MyWorld.leftMargin + MyWorld.graphWidth / 2, MyWorld.topMargin + MyWorld.graphHeight / 2);
        graph.drawMe();
        /* get the Switch-Singleton and add it to the world*/
        Switch switcher = Switch.getInstance();
        addObject(switcher, switcher.width / 2 + MyWorld.leftMargin, switcher.height / 2 + 2);
        /* get the Display-Singleton and add it to the world*/
        Display display = Display.getInstance();
        display.setWidth(MyWorld.graphWidth - switcher.width - MyWorld.leftMargin);
        addObject(display, switcher.width + 2 * MyWorld.leftMargin + display.width / 2, display.height / 2 + 2);
        /* */
        Vertex nodeA =  new  Vertex("A");
        Vertex nodeB =  new  Vertex("B");
        Vertex nodeC =  new  Vertex("C");
        Vertex nodeD =  new  Vertex("D");
        Vertex nodeE =  new  Vertex("E");
        Vertex nodeF =  new  Vertex("F");
        addObject(nodeA, 100, 100);
        addObject(nodeB, 300, 240);
        addObject(nodeC, 250, 50);
        addObject(nodeD, 60, 400);
        addObject(nodeE, 500, 250);
        addObject(nodeF, 350, 400);
        graph.addVertex(nodeA);
        graph.addVertex(nodeB);
        graph.addVertex(nodeC);
        graph.addVertex(nodeD);
        graph.addVertex(nodeE);
        graph.addVertex(nodeF);
        graph.addUndirectedEdge(nodeB, nodeD);
        graph.addUndirectedEdge(nodeB, nodeC);
        graph.addUndirectedEdge(nodeC, nodeE);
        graph.addUndirectedEdge(nodeD, nodeE);
        graph.addUndirectedEdge(nodeA, nodeB);
        graph.addUndirectedEdge(nodeD, nodeF);
        /* Get EdgeDeleter-Singleton and add it to world*/
        edgeDeleter = EdgeDeleter.getInstance();
        edgeDeleter.setGraph(graph);
        this.addObject(edgeDeleter, getWidth() - 50, 50);
        /* Setting the edge to null will remove the EdgeDeleter from the world, however it will remember "its place in the world"*/
        edgeDeleter.setEdge(null);
        /* Add weightChanger . This class will display the new weigth when changing weight of edges .*/
        this.weightChanger =  new  WeightChanger(null, graph);
        this.addObject(weightChanger, getWidth() - 100, 100);
    }

    /**
     * Gets called when user clicks on an edge in Delete Edge or Change Weight state
     * The edge gets highlighted (and previously selected edges get unhighlighted). Further action is delegated to responsible classes.
     */
    public void handle(Edge edge, String state)
    {
        if (savedEdge != null) {
            savedEdge.unmark();
        }
        savedEdge = edge;
        savedEdge.mark();
        if (state.equals("Delete Edge")) {
            edgeDeleter.setEdge(edge);
        }
        else if (state.equals("Change weight")) {
            weightChanger.setEdge(edge);
        }
    }

    /**
     * Gets called when user clicks on a vertex in certain states.
     * On the first click the vertex gets marked and saved. When another vertex is clicked, the corresponding actions are performed.
     * NOTE: As it is now, this is only useful for actions that require to vertices.
     */
    public void handle(Vertex vertex, String state)
    {
        if (savedVertex != null) {
            if (savedVertex != vertex) {
                if (state.equals("Add Edge")) {
                    graph.addUndirectedEdge(savedVertex, vertex);
                }
                else if (state.equals("Tiefensuche")) {
                    vertex.drawMe(Color.orange);
                    graph.dfsearch(savedVertex, vertex);
                }
                else if (state.equals("Breitensuche")) {
                    vertex.drawMe(Color.orange);
                    graph.bfsearch(savedVertex, vertex);
                }
                else if (state.equals("Dijkstra")) {
                    Dijkstra dijkstra =  new  Dijkstra();
                    this.addObject(dijkstra, 30, this.worldHeight - 20);
                    dijkstra.shortestPath(savedVertex, vertex);
                }
                resetVertex();
            }
        }
        else {
            savedVertex = vertex;
            savedVertex.mark();
        }
    }

    /**
     * Discard the saved edge. Used for example whenever we change the state.
     */
    public void unsetEdge()
    {
        savedEdge = null;
    }

    /**
     * Discard the saved vertex. Used for example whenever we change the state.
     */
    public void resetVertex()
    {
        if (savedVertex != null) {
            savedVertex.unmark();
            savedVertex = null;
        }
    }
}

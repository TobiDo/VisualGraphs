// WARNING: This file is auto-generated and any changes to it will be overwritten
import java.util.*;
import greenfoot.*;

/**
 * Write a description of class Dijkstra here.
 */
public class Dijkstra extends Actor
{
    private World world;

    /**
     * Constructor for objects of class Dijkstra
     */
    public Dijkstra()
    {
    }

    /**
     * 
     */
    public void shortestPath(Vertex start, Vertex target)
    {
        Display display = Display.getInstance();
        Set<Vertex> foundShortest =  new  HashSet < Vertex > ();
        VisibleTable distances =  new  VisibleTable();
        this.getWorld().addObject(distances, 300, 550);
        Vertex current = start;
        distances.put(current, 0);
        while (true) {
            current.highlight();
            int currentDistance = distances.get(current);
            foundShortest.add(current);
            display.setMessage("Der kürzeste Weg nach " + current.getName() + " hat Länge " + currentDistance + ".");
            Greenfoot.delay(100);
            for (final Edge edge : current.getAdjacentEdges()) {
                Vertex end = edge.getOther(current);
                Integer thisDist = distances.get(end);
                if (thisDist == null || (thisDist > edge.getWeight() + currentDistance)) {
                    distances.put(end, edge.getWeight() + currentDistance);
                    display.setMessage("Kürzeren Weg nach " + end.getName() + " gefunden.");
                    Greenfoot.delay(100);
                }
            }
            int min = -1;
            for (final Vertex vertex : distances.keySet()) {
                if ( ! foundShortest.contains(vertex) && (distances.get(vertex) < min || min == -1)) {
                    min = distances.get(vertex);
                    current = vertex;
                }
            }
            if (min == -1) {
                break;
            }
        }
    }
}

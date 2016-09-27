// WARNING: This file is auto-generated and any changes to it will be overwritten
import java.util.*;
import greenfoot.*;
import java.awt.Color;

/**
 * Write a description of class Images here.
 */
public class Images
{
    public static final int vertexSize = 40;
    public static final int vertexMargin = 5;

    /**
     * Constructor for objects of class Images
     */
    public Images()
    {
    }

    /**
     * 
     */
    static public GreenfootImage vertexImage(Vertex vertex, Color color)
    {
        GreenfootImage vertexImage =  new GreenfootImage(vertexSize, vertexSize);
        vertexImage.setColor(color);
        vertexImage.fillOval(vertexMargin, vertexMargin, vertexSize - 2 * vertexMargin, vertexSize - 2 * vertexMargin);
        vertexImage.setFont( new java.awt.Font("Courier New", 1, 20));
        vertexImage.setColor(Color.white);
        vertexImage.drawString(vertex.getName(), 13, 27);
        return vertexImage;
    }

    /**
     * Returns x-value where weight of edge is put
     */
    static public int getWeightX(int vecX, int vecY)
    {
        int perpX =  - vecY;
        int perpY = vecX;
        double length = Math.sqrt(perpX * perpX + perpY * perpY);
        return vecX / 2 + (int)(perpX / length * 15);
    }

    /**
     * 
     */
    static public int getWeightY(int vecX, int vecY)
    {
        int perpX =  - vecY;
        int perpY = vecX;
        double length = Math.sqrt(perpX * perpX + perpY * perpY);
        return vecY / 2 + (int)(perpY / length * 15);
    }

    /**
     * 
     */
    static public GreenfootImage edgeImage(Edge edge, Color color)
    {
        int startX = edge.getStart().getX();
        int startY = edge.getStart().getY();
        int endX = edge.getEnd().getX();
        int endY = edge.getEnd().getY();
        int startToEndX = endX - startX;
        int startToEndY = endY - startY;
        int width = Math.abs(startToEndX) + vertexSize;
        int height = Math.abs(startToEndY) + vertexSize;
        int startPosX;
        int startPosY;
        if (startToEndX > 0) {
            startPosX = vertexSize / 2;
        }
        else {
            startPosX = width - vertexSize / 2;
        }
        if (startToEndY > 0) {
            startPosY = vertexSize / 2;
        }
        else {
            startPosY = height - vertexSize / 2;
        }
        GreenfootImage edgeImage =  new GreenfootImage(width, height);
        edgeImage.setColor(color);
        edgeImage.drawLine(startPosX, startPosY, startPosX + startToEndX, startPosY + startToEndY);
        /* mark center of edge weight*/
        edgeImage.drawString(String.valueOf(edge.getWeight()), startPosX + getWeightX(startToEndX, startToEndY) - 2, startPosY + getWeightY(startToEndX, startToEndY) + 5);
        return edgeImage;
    }
}

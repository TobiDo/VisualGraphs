// WARNING: This file is auto-generated and any changes to it will be overwritten
import java.util.*;
import greenfoot.*;
import java.awt.*;

/**
 * 
 */
public class Switch extends Actor
{
    private int state;
    /* TODO: Change to enum*/
    private String[] states = {"Move Vertex", "Add Vertex", "Add Edge", "Delete Edge", "Change weight", "Tiefensuche", "Breitensuche", "Dijkstra"};
    private Map<String,Integer> keyBindings;
    private final int numberOfStates = states.length;
    private static Switch instance;
    public final int height = 25;
    public final int width;
    private Graphics graphics;
    private static FontMetrics fontMetrics = null;
    private static final Font font =  new  Font(Font.MONOSPACED, Font.PLAIN, 15);
    /* TODO: get this from font metrics*/
    private static final int charWidth = 9;

    /**
     * The switch is a Singleton
     */
    private Switch()
    {
        state = 0;
        /* Find maximal length of state-name*/
        int maxLength = 0;
        for (final String state : states) {
            if (state.length() > maxLength) {
                maxLength = state.length();
            }
        }
        this.width = maxLength * Switch.charWidth + 10;
        this.drawMe();
        /* set-up key bindings*/
        keyBindings =  new  HashMap < String, Integer > ();
        keyBindings.put("m", 0);
        keyBindings.put("v", 1);
        keyBindings.put("e", 2);
        keyBindings.put("d", 3);
        keyBindings.put("c", 4);
        keyBindings.put("t", 5);
        keyBindings.put("b", 6);
        keyBindings.put("s", 7);
    }

    /**
     * 
     */
    static public Switch getInstance()
    {
        if (instance == null) {
            instance =  new  Switch();
        }
        return instance;
    }

    /**
     * 
     */
    public void drawMe()
    {
        GreenfootImage image =  new  GreenfootImage(this.width, this.height);
        image.fillRect(0, 0, this.width, this.height);
        image.setColor(Color.white);
        image.setFont(Switch.font);
        image.drawString(states[state], 5, 20);
        this.setImage(image);
    }

    /**
     * 
     */
    public String[] getStates()
    {
        return states;
    }

    /**
     * 
     */
    public String getState()
    {
        return this.getStates()[state];
    }

    /**
     * Act - do whatever the Switch wants to do. This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            state = (state + 1) % numberOfStates;
        }
        else {
            for (final String key : keyBindings.keySet()) {
                if (Greenfoot.isKeyDown(key)) {
                    state = keyBindings.get(key).intValue();
                    break;
                }
            }
        }
        drawMe();
    }
}

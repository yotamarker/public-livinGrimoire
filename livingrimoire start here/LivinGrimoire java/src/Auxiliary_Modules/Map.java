package Auxiliary_Modules;


import java.awt.*;
import java.util.Hashtable;

public class Map {
    private Hashtable<String,String> pointDescription = new Hashtable<>();
    private Hashtable<String,String> descriptionPoint = new Hashtable<>();
    private LGPointInt currentPosition = new LGPointInt();
    public void reset(){
        // sleep location is considered (0,0) location
        currentPosition.reset();
    }
    public void moveBy(int x, int y){
        // shift current position
        currentPosition.moveBy(x,y);
    }
    public void moveTo(String location){
        // use this when the AI is returning home
        if(descriptionPoint.contains(location)){
            String value = descriptionPoint.get(location);
            Point p1 = RegexUtil.pointRegex(value);
            LGPointInt temp = new LGPointInt(p1);
            currentPosition.x = temp.x;
            currentPosition.y = temp.y;
        }
    }
    public void write(String description){
        // location name or item description will be
        // saved on the map on the current point position
        String pointStr = currentPosition.toString();
        pointDescription.put(pointStr, description);
        descriptionPoint.put(description,pointStr);
    }
    public String read(){
        // read place description
        return pointDescription.getOrDefault(currentPosition.toString(),"null");
    }
    public String read(LGPointInt p1){
        // used for predition of upcoming locations
        return pointDescription.getOrDefault(p1.toString(),"null");
    }
    public String read(String description){
        // get location coordinate
        return descriptionPoint.getOrDefault(description, "null");
    }
}

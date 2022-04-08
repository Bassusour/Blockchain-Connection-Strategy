import java.util.Calendar;

public class Strategy1 {
    private static final int WIFI = 0;
    private static final int DATA = 1;

    private Calendar startDate, endDate;
    private int priority, connectionType;
    private Location location;

    public Strategy1(){

    }

    public void setStartDate(int day, int month, int year) {
        this.startDate.set(year, month, day);
    }

    public Calendar getStartDate(){
        return this.startDate;
    }

    public void setEndDate(int day, int month, int year) {
        this.endDate.set(year, month, day);
    }

    public Calendar getEndDate(){
        return this.endDate;
    }

    public void setPriority(int priority){
        this.priority = priority;
    }

    public int getPriority(){
        return this.priority;
    }

    public void setConnectionType(int type){
        this.connectionType = type;
    }

    public int getConnectionType(){
        return this.connectionType;
    }

    public void setLocation(int x, int y, int radius){
        this.location.setCoordinates(x, y, radius);
    }

    public Location getLocation(){
        return this.location;
    }
}

class Location {
    private int x, y, radius;

    public void setCoordinates (int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getRadius() {
        return this.radius;
    }
}


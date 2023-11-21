/**
 * POI entity used for storing POI information.
 * 
 * @author Colin Brown
 * @author Sinthuja Jeevarajhan
 * @author Victoria Houde
 * @author Edson Jun Da Huang
 * @author Ronak Toprani
 */
public class POI {
    private String name;
    private String desc;
    private Boolean fav;
    private int mapID;
    private String floorName;
    private String layer;
    private int x;
    private int y;
    private Boolean vis;

    /** 
     * Constructor for a POI that will supply all info of a POI
     * 
     * @param name  A String of the name of the POI
     * @param desc  A String of the description of the POI
     * @param fav   A Boolean of whether the POI is marked as favourite or not
     * @param mapID An integer 0-11 that specifies which floor (Map) the POI belongs to
     * @param layer A String that specifies which of the 6 layers a POI belongs to
     * @param x     An int that represents the POIs horizontal position on the map
     * @param y     An int that represents the POIs vertical position on the map
     * @param vis   A Boolean of whether the POI is visible on the map or not
     */
    public POI(String name, String desc, Boolean fav, int mapID, String layer, int x, int y, Boolean vis){
        this.name = name;
        this.desc = desc;
        this.fav = fav;
        this.mapID = mapID;
        this.layer = layer;
        this.x = x;
        this.y = y;
        this.vis = vis;
        floorName = findFloorName();
    }

    /** 
     * Constructor for a POI that supplies the fav and vis Boolean's 
     * with a default false and true value
     * 
     * @param name  A String of the name of the POI
     * @param desc  A String of the description of the POI
     * @param mapID An integer 0-11 that specifies which floor (Map) the POI belongs to
     * @param layer A String that specifies which of the 6 layers a POI belongs to
     * @param x     An int that represents the POIs horizontal position on the map
     * @param y     An int that represents the POIs vertical position on the map
     */
    public POI(String name, String desc, int mapID, String layer, int x, int y){
        this.name = name;
        this.desc = desc;
        fav = false;
        this.mapID = mapID;
        this.layer = layer;
        this.x = x;
        this.y = y;
        vis = true;
        floorName = findFloorName();
    }

    /**
     * Get the POIs name
     * 
     * @return the name of the POI
     */
    public String getName(){
        return name;
    }

    /**
     * Get the description of the POI
     * 
     * @return the description of the POI
     */
    public String getDesc(){
        return desc;
    }

    /**
     * See the POI is set as favourite
     * 
     * @return the fav Boolean
     */
    public Boolean getFav(){
        return fav;
    }

    /**
     * Get the POIs map ID
     * 
     * @return the mapID of the POI
     */
    public int getMapID(){
        return mapID;
    }

    /**
     * Get the name of the POIs layer
     * 
     * @return the POIs layer as a String representation
     */
    public String getLayer(){
        return layer;
    }

    /**
     * Get the POIs x coordinate
     * 
     * @return the POIs x value
     */
    public int getX(){
        return x;
    }

    /**
     * Get the POIs y coordinate
     * 
     * @return the POIs y value
     */
    public int getY(){
        return y;
    }

    /**
     * See if the POIs is visible
     * 
     * @return
     */
    public Boolean getVis(){
        return vis;
    }

    /**
     * Get the name of the floor the POI is on
     * 
     * @return the name of the POIs floor
     */
    public String getFloorName() {
        return floorName;
    }

    /**
     * Set the name of the POI
     * 
     * @param name  The new name of the POI
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Set the description of the POI
     * 
     * @param desc  The new description of the POI
     */
    public void setDesc(String desc){
        this.desc = desc;
    }

    /**
     * Set the favourite value of the POI
     * 
     * @param fav   The new favourite value of the POI
     */
    public void setFav(Boolean fav){
        this.fav = fav;
    }

    /**
     * Set the layer the POI is on
     * 
     * @param layer The name of the layer the POI is now on
     */
    public void setLayer(String layer){
        this.layer = layer;
    }

    /**
     * Set the POIs x coordinate
     * 
     * @param x The new x coordinate of the POI
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Set the POIs y coordinate
     * 
     * @param y The new y coordinate of the POI
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * Set the POIs visability on the map
     * 
     * @param vis   The new visibility value of the POI
     */
    public void setVis(Boolean vis){
        this.vis = vis;
    }

    // Write POI out to console

    /**
     * Get a String representation of the POI in the form of the POIs name
     * 
     * @return A String representation of the POI
     */
    public String toString(){
        return name;
    }

    /**
     * Get a String representation of the POI with much more information than toString()
     * Includes all information of the POI with the mapID in a String representation of
     * floor's name
     * 
     * @return A detailed String representation of the POI
     */
    public String toStringInfo() {
        return "Name: " + name + "\nDescription: " + desc + "\nFloor: " + floorName + "\nLayer: " + layer + "\nPosition: " + x + ", " + y + "\nVisible: " + vis + "\nFavourite: " + fav;
    }

    // Check if a given search string exists in a POIs name or description

    /**
     * Check if the words (separated by spaces) of a given search String are contained
     * in the name, description, or floor name of this POI. All words of search string must 
     * exist in any of those three locations (name, desc, floor) for a value of true to be returned
     * 
     * @param search    A String whose words will be checked if they exist in a POIs information. 
     * @return          True if the information of the POI matches the search String sufficiently
     */
    public boolean contains(String search){
        String words[] = search.split(" "); // Separate words in search string so that they can be checked individually
        for (String word : words){
            if (!name.toLowerCase().contains(word.toLowerCase()) && !desc.toLowerCase().contains(word.toLowerCase()) && !floorName.toLowerCase().contains(word.toLowerCase())) return false;
            }
        return true;
    }

    private String findFloorName() {
        switch (mapID) {
            case (0):
                return "Middlesex College Ground Floor";
            case (1):
                return "Middlesex College First Floor";
            case (2):
                return "Middlesex College Second Floor";
            case (3):
                return "Middlesex College Third Floor";
            case (4):
                return "Middlesex College Fourth Floor";
            case (5):
                return "Health Sciences Building Ground Floor";
            case (6):
                return "Health Sciences Building Second Floor";
            case (7):
                return "Health Sciences Building Third Floor";
            case (8):
                return "Health Sciences Building Fourth Floor";
            case (9):
                return "Alumni Hall Ground Floor";
            case (10):
                return "Alumni Hall First Floor";
            case (11):
                return "Alumni Hall Second Floor";
        }
        return null;
    }
}

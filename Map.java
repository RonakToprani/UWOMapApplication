
import java.util.ArrayList;

/**
 * Map entity used for storing map information. 
 * <br><br>
 * 
 * @author Colin Brown
 * @author Victoria Houde
 * @author Sinthuja Jeevarajhan
 * @author Edson Jun Da Huang
 * @author Ronak Toprani
 */
public class Map {
    /**The name of the building the map is in.*/
    private String buildingName;
    /**The address for the metadata of the map. A JSON file.*/
    private String metadataAdd;
    /**Path to the image location.*/
    private String mapImage;
    /**The width of the image.*/
    private int sizeX;
    /**The height of the image.*/
    private int sizeY;
    /**The floor which the map represents in the building.*/
    private int floorNum;
    /**Unique integer representing each map.*/
    private int mapID;
    /**The list of layers contained within the map*/
    private ArrayList<Layer> layers;
    /**JSON for storing the map data*/
    private JSON json = new JSON();
    
    /**
     * Map constructor. Creates a new map object.
     * @param buildingName The name of the building the map is in.
     * @param metadataAdd The address for the metadata of the map. A JSON file.
     * @param mapImage Path to the image location.
     * @param sizeX The width of the image.
     * @param sizeY The height of the image.
     * @param mapID Unique integer representing each map.
     * @param floorNum The floor which the map represents in the building.
     * @param layers The list of layers contained within the map
     */

    public Map(String buildingName, String metadataAdd, String mapImage, int sizeX, int sizeY, int mapID, int floorNum, ArrayList<Layer> layers) {
        this.buildingName = buildingName;
        this.metadataAdd = metadataAdd;
        this.mapImage = mapImage;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.mapID = mapID;
        this.floorNum = floorNum;
        this.layers = layers;
    }

    /**
     * Gets the building name.
     * @return the name of the building
     */
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * Gets the address of the metadata file.
     * @return the address of the metadata file
     */
    public String getMetadata() {
        return metadataAdd;
    }
    
    /**
     * Gets the path location of the map image.
     * @return the path to the image
     */
    public String getMapImage() {
        return mapImage;
    }
    
    /**
     * gets the width of the map image
     * @return the width of the image
     */
    public int getSizeX() {
        return sizeX;
    }

    /**
     * gets the height of the map image
     * @return the height of the image
     */
    public int getSizeY() {
        return sizeY;
    }

    /**
     * gets the floor number for the floor which the map represents within the building
     * @return the floor number of the map
     */
    public int getFloor() {
        return floorNum;
    }

    /**
     * gets the unique identifier of the map
     * @return map identifier
     */
    public int getMapID() {
        return mapID;
    }

    /**
     * Gets an array list of all the layers
     * @return arrayList of all the layers
     */
    public ArrayList<Layer> getLayers() {
        return layers;
    }

    /**
     * Gets a list of all the POIs on the map
     * @return arrayList of all POIs on map
     */
    public ArrayList<POI> getPOIs() {
        ArrayList<POI> POIList = new ArrayList<>();
        for (Layer layer : layers) {
            for (POI poi : layer.getPOIs()) {
                POIList.add(poi);
            }
        }
        return POIList;
    }
    
    /**
     * Sets the floor number of the map
     * @param floor the floor number
     */
    public void setFloorNum(int floor) {
        floorNum = floor;
    }
    
    /**
     * Sets the width and height of the map image
     * @param x width
     * @param y height
     */
    public void setSize(int x, int y) {
        sizeX = x;
        sizeY = y;
    }
    
    /**
     * Sets the building name
     * @param building the building name
     */
    public void setBuildingName(String building) {
        buildingName = building;
    }
    
    /**
     * Add a poi to the correct layer object and update the json file of the map.
     * @param poi the POI to be added
     */
    public void addPOI(POI poi) {
        switch (poi.getLayer()) {
            case "Default":
                layers.get(0).addPOI(poi);
                break;
            case "Accessibility":
                layers.get(1).addPOI(poi);
                break;
            case "Classrooms":
                layers.get(2).addPOI(poi);
                break;
            case "Washrooms":
                layers.get(3).addPOI(poi);
                break;
            case "Eateries":
                layers.get(4).addPOI(poi);
                break;
            case "Custom":
                layers.get(5).addPOI(poi);
                break;
        }
        json.writeJSON(buildingName, metadataAdd, mapImage, sizeX, sizeY, floorNum, mapID, layers);
    }

    /**
     * Remove a POI from the proper layer object and update the json file of the map.
     * @param poi the POI to be removed
     */
    public void remPOI(POI poi) {
        switch (poi.getLayer()) {
            case "Default":
                layers.get(0).delPOI(poi);
                break;
            case "Accessibility":
                layers.get(1).delPOI(poi);
                break;
            case "Classrooms":
                layers.get(2).delPOI(poi);
                break;
            case "Washrooms":
                layers.get(3).delPOI(poi);
                break;
            case "Eateries":
                layers.get(4).delPOI(poi);
                break;
            case "Custom":
                layers.get(5).delPOI(poi);
                break;
        }
        json.writeJSON(buildingName, metadataAdd, mapImage, sizeX, sizeY, floorNum, mapID, layers);
    }
}


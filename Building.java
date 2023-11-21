
import java.util.ArrayList;

/**
 *Represents a building with a unique ID, a name, and a list of maps for each floor.
 * 
 * @author Colin Brown
 * @author Sinthuja Jeevarajhan
 * @author Victoria Houde
 * @author Edson Jun Da Huang
 * @author Ronak Toprani
 * 
 */
public class Building {

    private String id;
    private String buildingName;
    private ArrayList<Map> floors;

    /**
     * Constructs a new Building with the specified ID, name, and list of maps for each floor.
     *
     * @param id the unique ID of the building
     * @param buildingName the name of the building
     * @param floors the list of maps for each floor of the building
     */
    public Building(String id, String buildingName, ArrayList<Map> floors) {
        this.id = id;
        this.buildingName = buildingName;
        this.floors = floors;
    }

    /**
     * Returns the name of the building.
     *
     * @return the name of the building
     */
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * Returns the unique ID of the building.
     *
     * @return the unique ID of the building
     */
    public String getID() {
        return id;
    }

    /**
     * Returns the map for the specified floor of the building.
     *
     * @param floor the floor number of the map to be returned
     * @return the map for the specified floor of the building
     */
    public Map getMap(int floor) {
        return floors.get(floor);
    }

    /**
     * Returns the list of maps for each floor of the building.
     *
     * @return the list of maps for each floor of the building
     */
    public ArrayList<Map> getMaps() {
        return floors;
    }

    /**
     * Sets the name of the building.
     *
     * @param buildingName the new name of the building
     */
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
}

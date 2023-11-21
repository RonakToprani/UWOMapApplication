
import java.util.ArrayList;

/**
 * Class holds and controls the Layers of the Program
 * @version 1.0
 * 
 * @author Colin Brown
 * @author Sinthuja Jeevarajhan
 * @author Victoria Houde
 * @author Edson Jun Da Huang
 * @author Ronak Toprani
 * 
 * @see java.util.ArrayList
 */
public class Layer {

    
    private ArrayList<POI> POIList; //Holds the list of POIs
    private String name; //variable to store the POI Name

    /**
     * Constructor to create a list of POIs
     * @param name Layer name
     * @param POIList List of POIS in the specified layer
     */
    public Layer(String name, ArrayList<POI> POIList) {
        this.POIList = POIList;
        this.name = name;
    }

    /**
     * Constructor for a list of an empty layer, has no POIS
     * @param name holds the name of the Layer
     */
    public Layer(String name) {
        POIList = new ArrayList<>();
        this.name = name;
    }

    /**
     * Acessor Mcethod
     * @return name - Name of the Layer
     */
    public String getName() {
        return name;
    }
    /**
     * Accessor Method
     * @return list of POIS in the selected layer
     */
    public ArrayList<POI> getPOIs() {
        return POIList;
    }

    /**
     * add POI to list
     * @param poi POI object to add to layer
     */
    public void addPOI(POI poi) {
        POIList.add(poi);
    }

    /**
     * Delete POI from list
     * @param poi POI object to delete
     */
    public void delPOI(POI poi) {
        POIList.remove(poi);
    }

    /**
     * Show all the POIS in the selected Layer
     */
    public void showLayer() {
        for (POI poi : POIList) {
            poi.setVis(true);
        }
    }

    /**
     * Hide all the POIs in the selected layer
     */
    public void hideLayer() {
        for (POI poi : POIList) {
            poi.setVis(false);
        }
    }
    
    /**
     * String representation of a layer
     * @return name
     */
    public String toString() {
        try {
            if (!POIList.get(0).getVis()) {
                return name + " (Hidden)";
            }
        }
        catch (Exception e) {
        }
        if (POIList.isEmpty()) {
                return name + " (Empty)";
        }
        return name;
    }
}

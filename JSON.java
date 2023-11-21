
import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import org.apache.commons.io.FileUtils;
import org.json.simple.*;
import org.json.simple.parser.*;

/**
 * This class reads and writes the JSON metadata file
 * @version 1.0
 * 
 * @author Colin Brown
 * @author Sinthuja Jeevarajhan
 * @author Victoria Houde
 * @author Edson Jun Da Huang
 * @author Ronak Toprani
 */
public class JSON {

    /**Private instance variable parser of type JSONParser */
    private JSONParser parser = new JSONParser();

    /**
     * Empty constructor of class JSON
     */
    public JSON() {
    }

    /**
     * This method reads from the metadata file, builds Building class,
     * returns the map
     * @param fileURL, file path
     * @return Building object, null if error
     */
    public Building buildBuilding(URL fileURL) {
        ArrayList<Map> MapList = new ArrayList<>();
        String p = new File("").getAbsolutePath();
        File f = new File(p + "/src/resources");
        if(f.exists() && f.isDirectory() && f.listFiles().length != 1){
            try{
                String filePath = fileURL.toString();
                fileURL = new URL(p + "/src/resources/"+filePath);
            }catch (MalformedURLException e) {

            }

        }
            try {
                Object obj = parser.parse(new BufferedReader(new InputStreamReader(fileURL.openStream())));
                JSONObject jsonObject = (JSONObject) obj;

                String name = (String) jsonObject.get("name");
                String id = (String) jsonObject.get("id");

                Iterator it = ((JSONArray) jsonObject.get("maps")).iterator();
                while (it.hasNext()) {
                    JSONObject JSONObj = (JSONObject) it.next();
                    if(f.exists() && new File ("/src/resources/" + (String)JSONObj.get("address")).exists()){
                        String newAddress = "/src/resources/" + (String)JSONObj.get("address");
                        MapList.add(buildMap(newAddress));
                    }else{
                        MapList.add(buildMap((String) JSONObj.get("address")));
                    }

                }
                return new Building(id, name, MapList);

            } catch (IOException e) {
                System.out.println("Error building Building: File not found");
            } catch (ParseException e) {
                System.out.println("Error building Building: Parse exception");
            }
        
        return null;
    }

    /**
     * This method reads from the metadata file and builds Map class and ,
     * return the map
     * @param mapAddress, location of map
     * @return Map object, if error
     */
    private Map buildMap(String mapAddress) {
        URL fileURL = App.class.getClassLoader().getResource(mapAddress);
        String p = new File("").getAbsolutePath();
        File fileCheck = new File(p + "/src/resources/" + mapAddress);
        try{
            //System.out.println(fileCheck.getAbsolutePath());
            fileURL = new URL ("file:///" + fileCheck.getAbsolutePath());
        } catch (MalformedURLException e) {
            System.out.println(e);
        }
        ArrayList<Layer> LayerList = new ArrayList<>();

        try {
            Object obj = parser.parse(new BufferedReader(new InputStreamReader(fileURL.openStream())));
            JSONObject jsonObject = (JSONObject) obj;

            String mapName = (String) jsonObject.get("name");
            String imageAdd = (String) jsonObject.get("image");
            int sizeX = (int) (long) jsonObject.get("sizeX");
            int sizeY = (int) (long) jsonObject.get("sizeY");
            int id = (int) (long) jsonObject.get("mapID");
            int floorNum = (int) (long) jsonObject.get("floorNum");

            JSONObject JSONlayer = (JSONObject) jsonObject.get("Default");
            Iterator it = ((JSONArray) JSONlayer.get("POIList")).iterator();
            LayerList.add(new Layer("Default", buildPOIList(it)));

            JSONlayer = (JSONObject) jsonObject.get("Accessibility");
            it = ((JSONArray) JSONlayer.get("POIList")).iterator();
            LayerList.add(new Layer("Accessibility", buildPOIList(it)));

            JSONlayer = (JSONObject) jsonObject.get("Classrooms");
            it = ((JSONArray) JSONlayer.get("POIList")).iterator();
            LayerList.add(new Layer("Classrooms", buildPOIList(it)));

            JSONlayer = (JSONObject) jsonObject.get("Washrooms");
            it = ((JSONArray) JSONlayer.get("POIList")).iterator();
            LayerList.add(new Layer("Washrooms", buildPOIList(it)));

            JSONlayer = (JSONObject) jsonObject.get("Eateries");
            it = ((JSONArray) JSONlayer.get("POIList")).iterator();
            LayerList.add(new Layer("Eateries", buildPOIList(it)));

            JSONlayer = (JSONObject) jsonObject.get("Custom");
            it = ((JSONArray) JSONlayer.get("POIList")).iterator();
            LayerList.add(new Layer("Custom", buildPOIList(it)));

            return new Map(mapName, mapAddress, imageAdd, sizeX, sizeY, id, floorNum, LayerList);

        } catch (IOException e) {
            System.out.println("Error building Map: File not found");
        } catch (ParseException e) {
            System.out.println("Error building Map: Parse exception");
        }
        return null;
    }

    /**
     * Helper method for buildMap. Takes an Iterator of POI info from JSONArray 
     * POIList. Makes an ArrayList of POIs and returns
     * @param it, iterator
     * @return temp, ArrayList of POIs
     */
    private ArrayList<POI> buildPOIList(Iterator it) {
        ArrayList<POI> temp = new ArrayList<>();
        while (it.hasNext()) {
            JSONObject JSONObj = (JSONObject) it.next();
            String name = (String) JSONObj.get("name");
            String desc = (String) JSONObj.get("desc");
            Boolean fav = (Boolean) JSONObj.get("fav");
            int mapID = (int) (long) JSONObj.get("mapID");
            String layer = (String) JSONObj.get("layer");
            int x = (int) (long) JSONObj.get("x");
            int y = (int) (long) JSONObj.get("y");
            Boolean vis = (Boolean) JSONObj.get("vis");

            temp.add(new POI(name, desc, fav, mapID, layer, x, y, vis));
        }
        return temp;
    }

    /**
     * This method rewrites the JSON file of a given map
     * @param buildingName, the name of the current building
     * @param metadataAdd, add to the metadata
     * @param mapImage, image of the map
     * @param sizeX, size of map horizontally
     * @param sizeY, size of map vertically
     * @param floorNum, floor number
     * @param mapID, mapID
     * @param layers, ArrayList of layers of the current building
     */
    public void writeJSON(String buildingName, String metadataAdd, String mapImage, int sizeX, int sizeY, int floorNum, int mapID, ArrayList<Layer> layers) {

        String pa = new File("").getAbsolutePath();
        File fa = new File(pa + "/src/resources/" + metadataAdd);
        metadataAdd = fa.getAbsolutePath();
        //System.out.println("file at:" + metadataAdd);
        File fileObj = new File(metadataAdd);

        try {

            RandomAccessFile file = new RandomAccessFile(fileObj, "rw");
            file.setLength(0);
            file.write(("{\n  \"name\": \"" + buildingName + "\",\n  \"image\": \"" + mapImage + "\",\n  \"sizeX\": " + sizeX + ",\n  \"sizeY\": " + sizeY + ",\n  \"floorNum\": " + floorNum + ",\n  \"mapID\": " + mapID + ",\n  \"Default\":{\n    \"name\": \"Default\",\n    \"POIList\": [\n").getBytes());
            for (int i = 0; i <= 5; i++) {
                switch (i) {
                    case 1:
                        file.write(("  \"Accessibility\":{\n    \"name\": \"Accessibility\",\n    \"POIList\": [\n").getBytes());
                        break;
                    case 2:
                        file.write(("  \"Classrooms\":{\n    \"name\": \"Classrooms\",\n    \"POIList\": [\n").getBytes());
                        break;
                    case 3:
                        file.write(("  \"Washrooms\":{\n    \"name\": \"Washrooms\",\n    \"POIList\": [\n").getBytes());
                        break;
                    case 4:
                        file.write(("  \"Eateries\":{\n    \"name\": \"Eateries\",\n    \"POIList\": [\n").getBytes());
                        break;
                    case 5:
                        file.write(("  \"Custom\":{\n    \"name\": \"Custom\",\n    \"POIList\": [\n").getBytes());
                        break;
                }
                Iterator<POI> POIs = layers.get(i).getPOIs().iterator();
                while (POIs.hasNext()) {
                    POI poi = POIs.next();
                    file.write(("      " + buildJSONPOI(poi).toJSONString()).getBytes());
                    if (POIs.hasNext()) {
                        file.write((",\n").getBytes());
                    } else {
                        file.write('\n');
                    }
                }
                if (i == 5) {
                    file.write(("    ]\n  }\n}").getBytes());
                } else {
                    file.write(("    ]\n  },\n").getBytes());
                }
            }


            file.close();

        } catch (IOException e) {
            System.out.println("Error writing new JSON: File not found");
        }catch (SecurityException e){
            System.out.println("Security Exception");
        }
    }

    /**
     * Helper method for writeJSON. 
     * Takes a poi, makes a JSONObject and is returned
     * @param poi, POI of a layer
     * @return addObj, JSONObject to be written into JSON file
     */
    private JSONObject buildJSONPOI(POI poi) {
        JSONObject addObj = new JSONObject();
        addObj.put("name", poi.getName());
        addObj.put("desc", poi.getDesc());
        addObj.put("fav", poi.getFav());
        addObj.put("mapID", poi.getMapID());
        addObj.put("layer", poi.getLayer());
        addObj.put("x", poi.getX());
        addObj.put("y", poi.getY());
        addObj.put("vis", poi.getVis());

        return addObj;
    }
}

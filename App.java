
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import org.json.simple.parser.ParseException;
import java.net.URL;
/**
 * The main class of the Application. Initializes the buildings and the current map. Initiates the GUI.
 * 
 * @author Colin Brown
 * @author Victoria Houde
 * @author Sinthuja Jeevarajhan
 * @author Edson Jun Da Huang
 * @author Ronak Toprani
 */

public class App {

    static private JSON json = new JSON();
    /**Path to the JSON files for each building*/
    static public String[] mapAddresses = new String[]{"/src/resources/MiddlesexCollege/MC.json", "/src/resources/AlumniHall/AH.json", "/src/resources/HealthSci/HSB.json"};
    /**Array of the buildings in the application*/
    static public Building[] buildings = new Building[]{json.buildBuilding(App.class.getClassLoader().getResource("MiddlesexCollege/MC.json")), json.buildBuilding(App.class.getClassLoader().getResource("AlumniHall/AH.json")), json.buildBuilding(App.class.getClassLoader().getResource("HealthSci/HSB.json"))}; 
    /**The current map selected in the GUI*/
    static public Map curr = buildings[0].getMap(0);
    /**Boolean indicating if the application is in developer mode*/
    static public boolean Dev = false;
    /**The currently selected Point of Interest*/
    static public POI selected;
    /**The currently selected layer*/
    static public Layer selectedLayer;
    /**The password for entering developer mode*/
    static public String password = "cs2212";

    
    public static void main(String[] args) throws MalformedURLException {
        
        for(int i = 0; i<mapAddresses.length; i++){
            String filePath = new File("").getAbsolutePath();
            //System.out.println(filePath);
            File newMaps = new File(filePath + mapAddresses[i]);
            //System.out.println(newMaps.getAbsolutePath());
            buildings[i] = json.buildBuilding(new URL("file:///" + newMaps.getAbsolutePath()));        
        }
        curr = buildings[0].getMap(0);
            
       
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Map_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Map_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Map_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Map_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Map_GUI().setVisible(true);
            }
        });
    }

}

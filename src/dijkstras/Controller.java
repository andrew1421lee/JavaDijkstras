package dijkstras;

import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Controller Class
 * Methods that will be called by GUI elements
 * @author Anchu
 */
public class Controller {
    // Holds last directory browsed
    private static String last_directory = ".";
    // Path to selected item
    private static String file_path = ".";
    // Textfield that shows file name
    @FXML private TextField browseFileField;
    // Button to run slowly
    @FXML private Button runSlowButton;
    @FXML private TextArea logField;
    private PrintStream outstream;

    public void initialize(){
        outstream = new PrintStream(new Console(logField));
        System.setOut(outstream);
        System.out.println("READY");
    }

    /**
     * handleBrowseButtonAction Method
     * Is run when browse button is pressed, Opens file browser
     * @param event Event
     */
    @FXML protected void handleBrowseButtonAction(ActionEvent event){
        // declare and initialize filechooser
        FileChooser chooser = new FileChooser();
        // set title
        chooser.setTitle("Choose Graph File...");
        // set initial directory
        chooser.setInitialDirectory(new File(last_directory));
        // display and get user input
        File file = chooser.showOpenDialog(browseFileField.getScene().getWindow());
        // if user selected a file
        if(file != null) {
            // set textfield to filename
            browseFileField.setText(file.getName());
            // save path
            file_path = file.getPath();
            // save directory so it can be set for next time
            last_directory = file.getParent();
            // enable run button
            runSlowButton.setDisable(false);
            //runFastButton.setDisable(false);
        }else{
            runSlowButton.setDisable(true);
            //runFastButton.setDisable(true);
        }
    }

    /**
     * handleRunSlowButtonAction Method
     * Is run when Slow run button is pressed, loads data and runs dijkstras v^2
     * @param event Event
     */

    @FXML protected void handleRunSlowButtonAction(ActionEvent event){
        // read the file and load into filecontents
        Graph filecontents = FileReader.LoadGraphFile(file_path, false);
        if(filecontents == null){
            throw new NullPointerException("File open error");
        }
        // Print out the contents as a test
        /*for(Vertex v : filecontents.getVertices()){
            System.out.print(v.getId() + ":");
            for(int a : v.getEdges()){
                System.out.print(a);
            }
            System.out.println("\n");
        }
        for(Edge e : filecontents.getEdges()){
            System.out.println(e.getId());
        }*/
        Dijkstra.RunDijkstras(filecontents, 0);
    }
    
    public class Console extends OutputStream{
        private TextArea outfield;

        public Console(TextArea con){
            outfield = con;
        }

        public void appendOut(String s){
            Platform.runLater(() -> outfield.appendText(s));
        }

        public void write(int b) throws IOException{
            appendOut(String.valueOf((char)b));
        }
    }
}

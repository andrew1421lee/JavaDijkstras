package dijkstras;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {
    private static String last_directory = ".";
    public static String file_path = ".";
    @FXML private TextField browseFileField;
    @FXML private Button runSlowButton;

    @FXML protected void handleBrowseButtonAction(ActionEvent event){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose Graph File...");
        chooser.setInitialDirectory(new File(last_directory));
        File file = chooser.showOpenDialog(browseFileField.getScene().getWindow());
        if(file != null) {
            browseFileField.setText(file.getName());
            file_path = file.getPath();
            last_directory = file.getParent();
            runSlowButton.setDisable(false);
        }
    }

    @FXML protected void handleRunSlowButtonAction(ActionEvent event){
        Node[] filecontents = FileReader.LoadGraphFile(file_path);
        for(Node n : filecontents){
            System.out.print(n.getNumber());
            for(AdjNode a : n.getAdjacent()){
                System.out.print(a.getNumber());
            }
            System.out.println("\n");
        }
    }
}

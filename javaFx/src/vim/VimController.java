/**
 * Sample Skeleton for 'VimMain.fxml' Controller Class
 */

package vim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class VimController {

	Stage primaryStage;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="file_menu"
    private Menu file_menu; // Value injected by FXMLLoader

    @FXML // fx:id="file_new"
    private MenuItem file_new; // Value injected by FXMLLoader

    @FXML // fx:id="file_open"
    private MenuItem file_open; // Value injected by FXMLLoader

    @FXML // fx:id="file_save"
    private MenuItem file_save; // Value injected by FXMLLoader

    @FXML // fx:id="close"
    private MenuItem close; // Value injected by FXMLLoader

    @FXML // fx:id="textArea"
    private TextArea textArea; // Value injected by FXMLLoader

    @FXML
    void closeAction(ActionEvent event) {
//    	System.exit(0);
    	Platform.exit();
    }

    @FXML
    void fileMenuAction(ActionEvent event) {
    	
    }

    @FXML
    void newAction(ActionEvent event) {
    	textArea.clear();
    }

    @FXML
    void openAction(ActionEvent event) {

    	FileChooser fileChooser = new FileChooser();
    	
    	fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Text File", "*.txt"));
    	
    	File selectedFile = fileChooser.showOpenDialog(primaryStage);
    	
    	BufferedReader br = null;
    	try {
    		br = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile), "UTF-8"));
    		String line;
    		while((line = br.readLine()) != null) {
    			textArea.appendText(line);
    		}
    		
    	}catch(FileNotFoundException e) {
    		e.printStackTrace();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void saveAction(ActionEvent event) {
    		
		FileChooser fileChooser = new FileChooser();
		
		fileChooser.getExtensionFilters().add(
				new ExtensionFilter("Text File","*.txt"));
		
		fileChooser.setInitialFileName("NoName.txt");
		
		File selectedFile = fileChooser.showSaveDialog(primaryStage);
    		
		if(selectedFile != null) {
//			BufferedReader br = null;
	    	try {
//	    		br = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile)));
//	    		int c;
	    		FileWriter fw = new FileWriter(selectedFile);
	    		
    			fw.write(textArea.getText());
    			fw.close();
	    	}catch(FileNotFoundException e) {
	    		e.printStackTrace();
	    	}catch(IOException e) {
	    		e.printStackTrace();
	    	}
		}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert file_menu != null : "fx:id=\"file_menu\" was not injected: check your FXML file 'VimMain.fxml'.";
        assert file_new != null : "fx:id=\"file_new\" was not injected: check your FXML file 'VimMain.fxml'.";
        assert file_open != null : "fx:id=\"file_open\" was not injected: check your FXML file 'VimMain.fxml'.";
        assert file_save != null : "fx:id=\"file_save\" was not injected: check your FXML file 'VimMain.fxml'.";
        assert close != null : "fx:id=\"close\" was not injected: check your FXML file 'VimMain.fxml'.";
        assert textArea != null : "fx:id=\"textArea\" was not injected: check your FXML file 'VimMain.fxml'.";
        
    }
}


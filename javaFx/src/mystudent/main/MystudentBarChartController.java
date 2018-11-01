/**
 * Sample Skeleton for 'MystudentBarChart.fxml' Controller Class
 */

package mystudent.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MystudentBarChartController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="bar_chart"
    private BarChart<String, Number> bar_chart; // Value injected by FXMLLoader

    @FXML // fx:id="btn_close"
    private Button btn_close; // Value injected by FXMLLoader

    @FXML
    void closeAction(ActionEvent event) {
		Stage currentStage = (Stage)btn_close.getScene().getWindow();
    	
    	currentStage.close();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert bar_chart != null : "fx:id=\"bar_chart\" was not injected: check your FXML file 'MystudentBarChart.fxml'.";
        assert btn_close != null : "fx:id=\"btn_close\" was not injected: check your FXML file 'MystudentBarChart.fxml'.";

    }
}


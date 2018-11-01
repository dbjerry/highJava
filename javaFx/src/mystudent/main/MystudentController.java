/**
 * Sample Skeleton for 'MystudentMain.fxml' Controller Class
 */

package mystudent.main;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.ibatis.sqlmap.engine.mapping.statement.UpdateStatement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mystudent.service.IStudentService;
import mystudent.service.StudentService;
import mystudent.vo.StudentVO;

public class MystudentController {

	private IStudentService service;
	
    public MystudentController() {
		service = StudentService.getInstance();
	}
    
    StudentVO std_vo = new StudentVO();
    List<StudentVO> std_list = null;

    ObservableList<StudentVO> data = null;
    
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML // fx:id="table_view"
    private TableView<StudentVO> table_view; // Value injected by FXMLLoader
    
    @FXML // fx:id="table_name"
    private TableColumn<StudentVO, String> table_name; // Value injected by FXMLLoader

    @FXML // fx:id="table_kor"
    private TableColumn<StudentVO, Integer> table_kor; // Value injected by FXMLLoader

    @FXML // fx:id="table_mat"
    private TableColumn<StudentVO, Integer> table_mat; // Value injected by FXMLLoader

    @FXML // fx:id="table_eng"
    private TableColumn<StudentVO, Integer> table_eng; // Value injected by FXMLLoader
    
    @FXML // fx:id="btn_barGraph"
    private Button btn_barGraph; // Value injected by FXMLLoader

    @FXML // fx:id="btn_add"
    private Button btn_add; // Value injected by FXMLLoader

	Stage primaryStage;
	
	public void errorAlert(String header, String msg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("ERROR");
		alert.setHeaderText(header);
		alert.setContentText(msg);
		
		alert.showAndWait();
	}
	
	public void infoAlert(String header, String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("INFOMATION");
		alert.setHeaderText(header);
		alert.setContentText(msg);
		
		alert.showAndWait();
	}
	
    @FXML
    void addAction(ActionEvent event) {
    	try {
    		Stage dialog = new Stage(StageStyle.DECORATED);
    		
    		dialog.initModality(Modality.APPLICATION_MODAL);
    		dialog.initOwner(primaryStage);
    		
    		Parent childRoot = FXMLLoader.load(getClass().getResource("MystudentAdd.fxml"));
    		
    		Scene childScene = new Scene(childRoot);
    		
    		dialog.setScene(childScene);
    		dialog.setResizable(false);
    		dialog.showAndWait();
    		
    		std_list = service.getStdList();
    		data = FXCollections.observableArrayList(std_list);
    		table_view.setItems(data);
    		
    	}catch(IOException ioe) {
    		ioe.printStackTrace();
    	}
    }

    @FXML
    void barGraphAction(ActionEvent event) {

    	try {
    		Stage students = new Stage(StageStyle.DECORATED);
    		students.initModality(Modality.APPLICATION_MODAL);
    		students.initOwner(primaryStage);
    		
    		Parent barRoot = FXMLLoader.load(
    				getClass().getResource("MystudentBarChart.fxml"));
    		
    		BarChart bChart = (BarChart)barRoot.lookup("#bar_chart");
    		
    		XYChart.Series ser1 = new XYChart.Series<>();
			ser1.setName("kor");
			for(StudentVO stdVO : std_list) {
				ser1.getData().add(new XYChart.Data(stdVO.getStd_name(), stdVO.getStd_kor()));
			}

    		XYChart.Series ser2 = new XYChart.Series<>();
    		ser2.setName("mat");
    		for(StudentVO stdVO : std_list) {
    			ser2.getData().add(new XYChart.Data(stdVO.getStd_name(), stdVO.getStd_mat()));
    		}
    		
    		XYChart.Series ser3 = new XYChart.Series<>();
    		ser3.setName("eng");
    		for(StudentVO stdVO : std_list) {
    			ser3.getData().add(new XYChart.Data(stdVO.getStd_name(), stdVO.getStd_eng()));
    		}
    		
    		bChart.getData().addAll(ser1, ser2, ser3);
    		
    		Scene barScene = new Scene(barRoot);
    		
    		students.setScene(barScene);
    		students.show();
    		
    	}catch(IOException ioe) {
    		ioe.printStackTrace();
    	}
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	assert table_view != null : "fx:id=\"table_view\" was not injected: check your FXML file 'MystudentMain.fxml'.";
    	assert table_name != null : "fx:id=\"table_name\" was not injected: check your FXML file 'MystudentMain.fxml'.";
        assert table_kor != null : "fx:id=\"table_kor\" was not injected: check your FXML file 'MystudentMain.fxml'.";
        assert table_mat != null : "fx:id=\"table_mat\" was not injected: check your FXML file 'MystudentMain.fxml'.";
        assert table_eng != null : "fx:id=\"table_eng\" was not injected: check your FXML file 'MystudentMain.fxml'.";
    	assert btn_barGraph != null : "fx:id=\"btn_barGraph\" was not injected: check your FXML file 'MystudentMain.fxml'.";
        assert btn_add != null : "fx:id=\"btn_add\" was not injected: check your FXML file 'MystudentMain.fxml'.";
        
        table_name.setCellValueFactory(new PropertyValueFactory<StudentVO, String>("std_name"));
        table_kor.setCellValueFactory(new PropertyValueFactory<StudentVO, Integer>("std_kor"));
        table_mat.setCellValueFactory(new PropertyValueFactory<StudentVO, Integer>("std_mat"));
        table_eng.setCellValueFactory(new PropertyValueFactory<StudentVO, Integer>("std_eng"));
        
        std_list = service.getStdList();
        
        data = FXCollections.observableArrayList(std_list);
        
        table_view.setItems(data);
        
        table_view.setRowFactory(sc -> {
        	TableRow<StudentVO> row = new TableRow<StudentVO>();
        	row.setOnMouseClicked(e -> {
        		std_vo = table_view.getSelectionModel().getSelectedItem();
        		
        		if(e.getClickCount() == 2 && (! row.isEmpty())) {
        			
        			try {
        				
        				Stage score = new Stage(StageStyle.DECORATED);
        				
        				score.initModality(Modality.NONE);
        				score.initOwner(primaryStage);
        				
        				Parent pieRoot = FXMLLoader.load(
        					getClass().getResource("MystudentPieChart.fxml"));
        				
        				PieChart pChart = (PieChart)pieRoot.lookup("#pie_chart");
        				
        				ObservableList<PieChart.Data> pieChartData = 
        						FXCollections.observableArrayList(
        								new PieChart.Data(table_kor.getText(), std_vo.getStd_kor()),
        								new PieChart.Data(table_mat.getText(), std_vo.getStd_mat()),
        								new PieChart.Data(table_eng.getText(), std_vo.getStd_eng())
        						);
        				pChart.setData(pieChartData);
        				
        				Scene childScene = new Scene(pieRoot);
        				
        				score.setScene(childScene);
        				score.show();
        				
        			}catch(IOException ioe) {
        				ioe.printStackTrace();
        			}
        		}
        	});
        	return row;
        });
    }
}


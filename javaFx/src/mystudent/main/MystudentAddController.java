/**
 * Sample Skeleton for 'MystudentAdd.fxml' Controller Class
 */

package mystudent.main;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mystudent.service.IStudentService;
import mystudent.service.StudentService;
import mystudent.vo.StudentVO;
import javafx.scene.control.Alert.AlertType;

public class MystudentAddController {

	private IStudentService service;
	
    public MystudentAddController() {
		service = StudentService.getInstance();
	}
    
    StudentVO std_vo = new StudentVO();
    
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="label_name"
    private Label label_name; // Value injected by FXMLLoader
    
    @FXML // fx:id="field_name"
    private TextField field_name; // Value injected by FXMLLoader

    @FXML // fx:id="field_kor"
    private TextField field_kor; // Value injected by FXMLLoader

    @FXML // fx:id="field_mat"
    private TextField field_mat; // Value injected by FXMLLoader

    @FXML // fx:id="field_eng"
    private TextField field_eng; // Value injected by FXMLLoader

    @FXML // fx:id="btn_save"
    private Button btn_save; // Value injected by FXMLLoader

    @FXML // fx:id="btn_cancel"
    private Button btn_cancel; // Value injected by FXMLLoader
    
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
    void cancelAction(ActionEvent event) {
    	Stage currentStage = (Stage)btn_cancel.getScene().getWindow();
    	
    	currentStage.close();
    }

    @FXML
    void saveAction(ActionEvent event) throws IOException {
    	List<StudentVO> std_list =  service.getStdList();
    	ObservableList<StudentVO> data = FXCollections.observableArrayList(std_list);
    	
    	if(field_name.getText().isEmpty() ||
    			field_kor.getText().isEmpty() ||
    			field_mat.getText().isEmpty() ||
    			field_eng.getText().isEmpty()) {
    		errorAlert("input Error", "작성되지 않은 항목이 있습니다.");
    		return;
    	}
    	
    	int cnt = -1;
    	cnt = service.getStd(field_name.getText());
    	
    	Object obj = "";
    	
    	if(cnt == 0) {
    		std_vo = new StudentVO();
    		std_vo.setStd_name(field_name.getText());
    		std_vo.setStd_kor(Integer.parseInt(field_kor.getText()));
    		std_vo.setStd_mat(Integer.parseInt(field_mat.getText()));
    		std_vo.setStd_eng(Integer.parseInt(field_eng.getText()));
    		
    		obj = service.insertStd(std_vo);
    		data.add(std_vo);
    		
    		if(obj == null) {
    			
    			infoAlert("작업 결과", field_name.getText() + " 학생의 점수를 등록하였습니다.");
    			
    			Stage currentStage = (Stage)btn_cancel.getScene().getWindow();
    			
    			currentStage.close();
    		}else {
    			errorAlert("add Error", "이미 등록되어 있는 학생의 이름입니다.");
        		return;
    		}
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	assert label_name != null : "fx:id=\"label_name\" was not injected: check your FXML file 'MystudentAdd.fxml'.";
        assert field_name != null : "fx:id=\"field_name\" was not injected: check your FXML file 'MystudentAdd.fxml'.";
        assert field_kor != null : "fx:id=\"field_kor\" was not injected: check your FXML file 'MystudentAdd.fxml'.";
        assert field_mat != null : "fx:id=\"field_mat\" was not injected: check your FXML file 'MystudentAdd.fxml'.";
        assert field_eng != null : "fx:id=\"field_eng\" was not injected: check your FXML file 'MystudentAdd.fxml'.";
        assert btn_save != null : "fx:id=\"btn_save\" was not injected: check your FXML file 'MystudentAdd.fxml'.";
        assert btn_cancel != null : "fx:id=\"btn_cancel\" was not injected: check your FXML file 'MystudentAdd.fxml'.";

    }
}

/**
 * Sample Skeleton for 'MemberMVCMain.fxml' Controller Class
 */

package login.main;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import basic.TableViewTest.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import mvc.member.vo.MemberVO;
import mvc.member.dao.IMemberDao;
import mvc.member.dao.MemberDao;
import mvc.member.service.*;


public class MemberMVCController {

	private IMemberService service;
	
	public MemberMVCController() {
		service = MemberService.getInstance();
	}

	MemberVO mem_vo = new MemberVO();
	List<MemberVO> mem_list = null;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="field_id"
    private TextField field_id; // Value injected by FXMLLoader

    @FXML // fx:id="field_name"
    private TextField field_name; // Value injected by FXMLLoader

    @FXML // fx:id="field_phone"
    private TextField field_phone; // Value injected by FXMLLoader

    @FXML // fx:id="field_addr"
    private TextField field_addr; // Value injected by FXMLLoader

    @FXML // fx:id="btn_add"
    private Button btn_add; // Value injected by FXMLLoader

    @FXML // fx:id="btn_update"
    private Button btn_update; // Value injected by FXMLLoader

    @FXML // fx:id="btn_del"
    private Button btn_del; // Value injected by FXMLLoader

    @FXML // fx:id="btn_done"
    private Button btn_done; // Value injected by FXMLLoader

    @FXML // fx:id="btn_cancel"
    private Button btn_cancel; // Value injected by FXMLLoader

    @FXML // fx:id="tableView"
    private TableView<MemberVO> tableView; // Value injected by FXMLLoader

    @FXML // fx:id="table_id"
    private TableColumn<MemberVO, String> table_id; // Value injected by FXMLLoader

    @FXML // fx:id="table_name"
    private TableColumn<MemberVO, String> table_name; // Value injected by FXMLLoader

    @FXML // fx:id="table_phone"
    private TableColumn<MemberVO, String> table_phone; // Value injected by FXMLLoader

    @FXML // fx:id="table_addr"
    private TableColumn<MemberVO, String> table_addr; // Value injected by FXMLLoader

    @FXML
    void onSort(ActionEvent event) {

    }

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
	
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert field_id != null : "fx:id=\"field_id\" was not injected: check your FXML file 'MemberMVCMain.fxml'.";
        assert field_name != null : "fx:id=\"field_name\" was not injected: check your FXML file 'MemberMVCMain.fxml'.";
        assert field_phone != null : "fx:id=\"field_phone\" was not injected: check your FXML file 'MemberMVCMain.fxml'.";
        assert field_addr != null : "fx:id=\"field_addr\" was not injected: check your FXML file 'MemberMVCMain.fxml'.";
        assert btn_add != null : "fx:id=\"btn_add\" was not injected: check your FXML file 'MemberMVCMain.fxml'.";
        assert btn_update != null : "fx:id=\"btn_update\" was not injected: check your FXML file 'MemberMVCMain.fxml'.";
        assert btn_del != null : "fx:id=\"btn_del\" was not injected: check your FXML file 'MemberMVCMain.fxml'.";
        assert btn_done != null : "fx:id=\"btn_done\" was not injected: check your FXML file 'MemberMVCMain.fxml'.";
        assert btn_cancel != null : "fx:id=\"btn_cancel\" was not injected: check your FXML file 'MemberMVCMain.fxml'.";
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'MemberMVCMain.fxml'.";
        assert table_id != null : "fx:id=\"table_id\" was not injected: check your FXML file 'MemberMVCMain.fxml'.";
        assert table_name != null : "fx:id=\"table_name\" was not injected: check your FXML file 'MemberMVCMain.fxml'.";
        assert table_phone != null : "fx:id=\"table_phone\" was not injected: check your FXML file 'MemberMVCMain.fxml'.";
        assert table_addr != null : "fx:id=\"table_addr\" was not injected: check your FXML file 'MemberMVCMain.fxml'.";
        
        
        table_id.setCellValueFactory(new PropertyValueFactory<MemberVO, String>("mem_id"));
        table_name.setCellValueFactory(new PropertyValueFactory<MemberVO, String>("mem_name"));
        table_phone.setCellValueFactory(new PropertyValueFactory<MemberVO, String>("mem_tel"));
        table_addr.setCellValueFactory(new PropertyValueFactory<MemberVO, String>("mem_addr"));
        
        mem_list = service.getMemberList();
        
        ObservableList<MemberVO> data = FXCollections.observableArrayList(mem_list);
        
        tableView.setItems(data);
        
        tableView.setOnMouseClicked(new EventHandler<Event>() {
        	public void handle(Event event) {
        		mem_vo = tableView.getSelectionModel().getSelectedItem();
        		
        		if(mem_vo != null) {
        			field_id.setText(mem_vo.getMem_id());
        			field_name.setText(mem_vo.getMem_name());
        			field_phone.setText(mem_vo.getMem_tel());
        			field_addr.setText(mem_vo.getMem_addr());
        		}
        	};
		});
        
        
        
        /* 추가버튼 이벤트처리 */
        btn_add.setOnAction(e -> {
        	
        	field_id.setEditable(true);
        	field_name.setEditable(true);
        	field_phone.setEditable(true);
        	field_addr.setEditable(true);
        	
        	btn_add.setDisable(true);
        	btn_update.setDisable(true);
        	btn_del.setDisable(true);
        	
        	btn_done.setDisable(false);
        	btn_cancel.setDisable(false);
        	
        	tableView.setDisable(true);

        	field_id.clear();
        	field_name.clear();
        	field_phone.clear();
        	field_addr.clear();
        });
        
        
        /* 수정버튼 이벤트처리 */
        btn_update.setOnAction(e -> {

        	if(tableView.getSelectionModel().isEmpty()) {
        		errorAlert("Edit Error", "수정할 데이터를 선택한 후 진행하세요.");
        		return;
        	}
        	
        	field_name.setEditable(true);
        	field_phone.setEditable(true);
        	field_addr.setEditable(true);
        	
        	btn_add.setDisable(true);
        	btn_update.setDisable(true);
        	btn_del.setDisable(true);
        	
        	btn_done.setDisable(false);
        	btn_cancel.setDisable(false);
        	
        	tableView.setDisable(true);
        });
        
        
        /* 삭제버튼 이벤트처리 */
        btn_del.setOnAction(e -> {
        	if(tableView.getSelectionModel().isEmpty()) {
        		errorAlert("Delete Error", "삭제할 데이터를 선택한 후 시도하세요.");
        		return;
        	}
        	
        	String name = tableView.getSelectionModel().getSelectedItem().getMem_id();
        	
        	service.deleteMember(name);
        	
        	data.remove(tableView.getSelectionModel().getSelectedIndex());
        	
        	infoAlert("작업 결과", name + "님의 정보가 삭제되었습니다.");
        	
        	tableView.getSelectionModel().select(null);
        	
        	field_id.clear();
        	field_name.clear();
        	field_phone.clear();
        	field_addr.clear();
        });
        
        
        /* 확인버튼 이벤트 처리 */
        btn_done.setOnAction(e -> {
        	
        	if(field_id.getText().isEmpty() ||
        			field_name.getText().isEmpty() ||
        			field_phone.getText().isEmpty() ||
        			field_addr.getText().isEmpty()) {
        		errorAlert("input Error", "작성되지 않은 항목이 있습니다.");
        		return;
        	}
        	
        	mem_vo = new MemberVO();
        	mem_vo.setMem_id(field_id.getText());
        	mem_vo.setMem_name(field_name.getText());
        	mem_vo.setMem_tel(field_phone.getText());
        	mem_vo.setMem_addr(field_addr.getText());
        	
        	int cnt = -1;
        	cnt = service.getMember(field_id.getText());
        	if(cnt > 0) {
            	
        		data.set(tableView.getSelectionModel().getSelectedIndex(), mem_vo);
				service.updateMember(mem_vo);
				
				
				infoAlert("작업 결과", field_name.getText() + "님의 정보가 수정되었습니다.");
            	
            	field_id.clear();
            	field_name.clear();
            	field_phone.clear();
            	field_addr.clear();
            	
        	}else{
        		
        		
        		data.add(mem_vo);
        		service.insertMember(mem_vo);
        		
    			
    			infoAlert("작업결과", field_name.getText() + "님의 정보를 추가하였습니다.");
        		
        		field_id.clear();
        		field_name.clear();
        		field_phone.clear();
        		field_addr.clear();
        	}
        	
        	field_id.setEditable(false);
        	field_name.setEditable(false);
        	field_phone.setEditable(false);
        	field_addr.setEditable(false);
        	
        	btn_add.setDisable(false);
        	btn_update.setDisable(false);
        	btn_del.setDisable(false);
        	
        	btn_done.setDisable(true);
        	btn_cancel.setDisable(true);
        });
        
        
        btn_cancel.setOnAction(e -> {
        	field_id.setEditable(false);
        	field_name.setEditable(false);
        	field_phone.setEditable(false);
        	field_addr.setEditable(false);
        	
        	btn_add.setDisable(false);
        	btn_update.setDisable(false);
        	btn_del.setDisable(false);
        	
        	btn_done.setDisable(true);
        	btn_cancel.setDisable(true);
        	
        	field_id.clear();
        	field_name.clear();
        	field_phone.clear();
        	field_addr.clear();
        });
    }
}


/**
 * Sample Skeleton for 'ZipSearchMain.fxml' Controller Class
 */

package zip.client;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import zip.service.ZipSearchServiceInf;
import zip.vo.ZipVO;

public class ZipSearchMainController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmdSelect"
    private ComboBox<String> cmbSelect; // Value injected by FXMLLoader

    @FXML // fx:id="tfData"
    private TextField tfData; // Value injected by FXMLLoader

    @FXML // fx:id="btnSearch"
    private Button btnSearch; // Value injected by FXMLLoader

    @FXML // fx:id="zipTable"
    private TableView<ZipVO> zipTable; // Value injected by FXMLLoader

    @FXML // fx:id="zipCol"
    private TableColumn<?, ?> zipCol; // Value injected by FXMLLoader

    @FXML // fx:id="sidoCol"
    private TableColumn<?, ?> sidoCol; // Value injected by FXMLLoader

    @FXML // fx:id="gugunCol"
    private TableColumn<?, ?> gugunCol; // Value injected by FXMLLoader

    @FXML // fx:id="dongCol"
    private TableColumn<?, ?> dongCol; // Value injected by FXMLLoader

    @FXML // fx:id="riCol"
    private TableColumn<?, ?> riCol; // Value injected by FXMLLoader

    @FXML // fx:id="buildingCol"
    private TableColumn<?, ?> bldgCol; // Value injected by FXMLLoader

    @FXML // fx:id="bunjiCol"
    private TableColumn<?, ?> bunjiCol; // Value injected by FXMLLoader

    // 검색 버튼을 클릭했을 때 이벤트 처리
    public void zipSearch(ActionEvent event) {
    	if(tfData.getText().isEmpty()) {
    		alert("검색할 값을 입력하세요.");
    		return;
    	}
    	String searchData = tfData.getText();
    	
    	List<ZipVO> result = null;
    	
    	// 콤보박스의 선택 항목을 구분해서 검색처리
    	try {
    		if(cmbSelect.getValue().equals("동이름")) {
				result = service.zipSearchDong(searchData);
	    	}else if(cmbSelect.getValue().equals("우편번호")) {
	    		result = service.zipSearchZipCode(searchData);
	    	}
    	} catch (RemoteException e) {
    		e.printStackTrace();
    	}
    	
    	// 검색된 결과를 TableView에서 사용하는 List에 넣어주기
    	zipList.clear();
    	zipList.addAll(result);
    	
    }
    
    ZipSearchServiceInf service;	//	서버의 service객체가 저장될 변수
    
    // 검색한 데이터가 저장되고 테이블뷰에 셋팅할 List 생성
    ObservableList<ZipVO> zipList = FXCollections.observableArrayList();
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbSelect != null : "fx:id=\"cmdSelect\" was not injected: check your FXML file 'ZipSearchMain.fxml'.";
        assert tfData != null : "fx:id=\"tfData\" was not injected: check your FXML file 'ZipSearchMain.fxml'.";
        assert btnSearch != null : "fx:id=\"btnSearch\" was not injected: check your FXML file 'ZipSearchMain.fxml'.";
        assert zipTable != null : "fx:id=\"zipTable\" was not injected: check your FXML file 'ZipSearchMain.fxml'.";
        assert zipCol != null : "fx:id=\"zipCol\" was not injected: check your FXML file 'ZipSearchMain.fxml'.";
        assert sidoCol != null : "fx:id=\"sidoCol\" was not injected: check your FXML file 'ZipSearchMain.fxml'.";
        assert gugunCol != null : "fx:id=\"gugunCol\" was not injected: check your FXML file 'ZipSearchMain.fxml'.";
        assert dongCol != null : "fx:id=\"dongCol\" was not injected: check your FXML file 'ZipSearchMain.fxml'.";
        assert riCol != null : "fx:id=\"riCol\" was not injected: check your FXML file 'ZipSearchMain.fxml'.";
        assert bldgCol != null : "fx:id=\"buildingCol\" was not injected: check your FXML file 'ZipSearchMain.fxml'.";
        assert bunjiCol != null : "fx:id=\"bunjiCol\" was not injected: check your FXML file 'ZipSearchMain.fxml'.";

        // 검색 버튼 이벤트
        btnSearch.setOnAction(event -> zipSearch(event));
        
        // 서버의 service 객체 구하기
        try {
			Registry reg = LocateRegistry.getRegistry("localhost", 9988);
			service = (ZipSearchServiceInf) reg.lookup("zipSearch");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
        
        // 콤보박스 설정
        cmbSelect.getItems().addAll("동이름", "우편번호");
        cmbSelect.setValue("동이름");
        
        // TableView
        zipCol.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
        sidoCol.setCellValueFactory(new PropertyValueFactory<>("sido"));
        gugunCol.setCellValueFactory(new PropertyValueFactory<>("gugun"));
        dongCol.setCellValueFactory(new PropertyValueFactory<>("dong"));
        riCol.setCellValueFactory(new PropertyValueFactory<>("ri"));
        bldgCol.setCellValueFactory(new PropertyValueFactory<>("bldg"));
        bunjiCol.setCellValueFactory(new PropertyValueFactory<>("bunji"));
        
        // TableView에 데이터 셋팅
        zipTable.setItems(zipList);
    }
    
    // alert창
    public void alert(String msg) {
    	Alert alert = new Alert(AlertType.WARNING);
    	alert.setTitle("경고");
    	alert.setHeaderText("경고메시지");
    	alert.setContentText(msg);
    	alert.showAndWait();
    }
    
    // info창
    public void info(String msg) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("확인");
    	alert.setHeaderText("확인 메시지");
    	alert.setContentText(msg);
    	alert.showAndWait();
    }
}


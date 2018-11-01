package basic;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TableViewTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		ObservableList<Member> data = 
				FXCollections.observableArrayList(
					new Member("홍길동", "gildong", 30, "010-1111-1111", "대전"),
					new Member("홍길서", "gilseo", 50, "010-2222-2222", "옥천"),
					new Member("홍길남", "gilnam", 20, "010-3333-3333", "전주"),
					new Member("홍길북", "gilbook", 40, "010-4444-4444", "금산")
				);
		
		BorderPane root = new BorderPane();
		root.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		
		TableView<Member> table = new TableView<Member>(data);
//		table.setItems(data);	//	따로 세팅하거나 초기화시 적어서 ↑ 세팅해도 됨.
		
		// 각 컬럼 설정하기
		TableColumn<Member, String> nameCol = 
				new TableColumn<Member, String>("Name");
		
		TableColumn<Member, String> korNameCol = 
				new TableColumn<Member, String>("Kor Name");
		// 컬럼에 출력될 데이터 설정
		// VO클래스의 멤버변수와 컬럼을 연결한다.
		korNameCol.setCellValueFactory(
			new PropertyValueFactory<Member, String>("korName")
		);
		
		TableColumn<Member, String> engNameCol = 
				new TableColumn<Member, String>("Eng Name");
		engNameCol.setCellValueFactory(
				new PropertyValueFactory<Member, String>("engName")
			);
		
		nameCol.getColumns().addAll(korNameCol, engNameCol);
		
		TableColumn<Member,Integer> ageCol = 
				new TableColumn<Member, Integer>("age");
		ageCol.setCellValueFactory(new PropertyValueFactory<Member, Integer>("age"));
		
		TableColumn<Member, String> telCol =
			new TableColumn<Member, String>("Tel");
		telCol.setCellValueFactory(new PropertyValueFactory<Member, String>("tel"));
		
		TableColumn<Member,String> addrCol = 
			new TableColumn<Member, String>("Address");
		addrCol.setCellValueFactory(new PropertyValueFactory<Member, String>("addr"));
		
		table.getColumns().addAll(nameCol, ageCol, telCol, addrCol);
		
		////////////////////////////////////////////////////////////////////////////
		
		GridPane grid = new GridPane();
		Text text1 = new Text("한글이름");
		Text text2 = new Text("영문이름");
		Text text3 = new Text("나      이");
		Text text4 = new Text("전화번호");
		Text text5 = new Text("주      소");
		
		TextField tfKorName = new TextField();
		TextField tfEngName = new TextField();
		TextField tfAge = new TextField();
		TextField tfTel = new TextField();
		TextField tfAddr = new TextField();
		
		grid.add(text1, 1, 1);
		grid.add(text2, 2, 1);
		grid.add(text3, 3, 1);
		grid.add(text4, 4, 1);
		grid.add(text5, 5, 1);

		grid.add(tfKorName, 1, 2);
		grid.add(tfEngName, 2, 2);
		grid.add(tfAge, 3, 2);
		grid.add(tfTel, 4, 2);
		grid.add(tfAddr, 5, 2);
		
		grid.setVgap(10);
		grid.setHgap(10);
		
		////////////////////////////////////////////////////////////////
		
		// TableView를 클릭했을 때 처리
		table.setOnMouseClicked(new EventHandler<Event>() {
			public void handle(Event event) {
				// TableView에서 선택된 객체 구하기
				Member mem = table.getSelectionModel().getSelectedItem();
				
				if(mem != null) {
					tfKorName.setText(mem.getKorName());
					tfEngName.setText(mem.getEngName());
					tfAge.setText(String.valueOf("" + mem.getAge()));
	//				tfAge.setText(String.valueOf( mem.getAge()) );
	//				tfAge.setText("" + mem.getAge());
					tfTel.setText(mem.getTel());
					tfAddr.setText(mem.getAddr());
				}
				
				tfKorName.setEditable(false);	//	읽기전용(수정불가)
				
			}; {};
		});
		
		Button btnAdd = new Button("추가");
		btnAdd.setOnAction(e -> {
			if(tfKorName.getText().isEmpty() ||
					tfEngName.getText().isEmpty() ||
					tfAge.getText().isEmpty() ||
					tfTel.getText().isEmpty() ||
					tfAddr.getText().isEmpty()) {
				errorAlert("입력오류", "빈 항목이 있습니다.");
				return;
			}
			
			data.add(new Member(
				tfKorName.getText(),
				tfEngName.getText(),
				Integer.parseInt(tfAge.getText()),
				tfTel.getText(),
				tfAddr.getText()
			));		
			
			infoAlert("작업결과", tfKorName.getText() + "님의 정보를 추가하였습니다.");
			
			tfKorName.clear();
			tfEngName.clear();
			tfAge.clear();
			tfTel.clear();
			tfAddr.clear();
		});
		
		
		Button btnEdit = new Button("수정");
		btnEdit.setOnAction(e -> {
			if(table.getSelectionModel().isEmpty()) {
				errorAlert("수정작업오류", "수정할 데이터를 선택한 후 진행하세요.");
				return;
			}
			
			data.set(table.getSelectionModel().getSelectedIndex(), 
					new Member(
							tfKorName.getText(),
							tfEngName.getText(),
							Integer.parseInt(tfAge.getText()),
							tfTel.getText(),
							tfAddr.getText()));
			
			infoAlert("작업결과", tfKorName.getText() + "님의 정보가 수정되었습니다.");
			
			tfKorName.clear();
			tfEngName.clear();
			tfAge.clear();
			tfTel.clear();
			tfAddr.clear();
		});
		
		
		Button btnDel = new Button("삭제");
		btnDel.setOnAction(e -> {
			if(table.getSelectionModel().isEmpty()) {	//	선택항목이 있는지 검사
				errorAlert("삭제작업오류", "삭제할 데이터를 선택한 후 시도하세요.");
				return;
			}
			
			// 삭제될 사람의 이름 구하기
			String name = table.getSelectionModel().getSelectedItem().getKorName(); 
			
			data.remove(table.getSelectionModel().getSelectedIndex());
			
			infoAlert("작업결과", name + "님의 정보가 삭제되었습니다.");
			
			// 선택영역 없애기
			table.getSelectionModel().select(null);
			
			tfKorName.clear();
			tfEngName.clear();
			tfAge.clear();
			tfTel.clear();
			tfAddr.clear();
		});
		
		
		
		VBox vbox = new VBox(10);
		vbox.setPadding(new Insets(10));
		
		vbox.getChildren().addAll(btnAdd,btnEdit,btnDel);
		
		root.setCenter(table);
		root.setBottom(grid);
		root.setRight(vbox);
		
		root.setPadding(new Insets(10));
		root.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("TableView Test");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
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
	
	
	// 사용할 데이터 Class 정의 - VO역할 수행
	public class Member {
		private String korName;
		private String engName;
		private int age;
		private String tel;
		private String addr;
		
		/* 기본생성자 */
		public Member() {}
		
		/* 생성자 */
		public Member(String korName, String engName, int age, String tel, String addr) {
			super();
			this.korName = korName;
			this.engName = engName;
			this.age = age;
			this.tel = tel;
			this.addr = addr;
		}

		public String getKorName() {
			return korName;
		}

		public void setKorName(String korName) {
			this.korName = korName;
		}

		public String getEngName() {
			return engName;
		}

		public void setEngName(String engName) {
			this.engName = engName;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getTel() {
			return tel;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

		public String getAddr() {
			return addr;
		}

		public void setAddr(String addr) {
			this.addr = addr;
		}
	}
}


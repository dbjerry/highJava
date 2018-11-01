package basic;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ComboBoxTest2 extends Application {

	@Override
	public void start(Stage primaryStage) {
		VBox vbox = new VBox(10);
		vbox.setPadding(new Insets(10));
		
		ComboBox<MyFriend> combo = new ComboBox<MyFriend>();
		TextArea textArea = new TextArea();
		
		ObservableList<MyFriend> list = 
				FXCollections.observableArrayList(
					new MyFriend("aaa", "홍길동", "010-1111-1111", "대전"),
					new MyFriend("bbb", "일지매", "010-2222-2222", "광주"),
					new MyFriend("ccc", "성춘향", "010-3333-3333", "전주"),
					new MyFriend("ddd", "이순신", "010-4444-4444", "부산"),
					new MyFriend("eee", "이몽룡", "010-5555-5555", "대구"),
					new MyFriend("fff", "강감찬", "010-6666-6666", "포항")
				);
		combo.setItems(list);
		
		/*		
		 * 콤보박스의 *목록이 보여지는 곳의 내용 변경
		 * 즉, 화면에 나타는 셀의 내용을 변경한다.
		 */
		combo.setCellFactory(
			new Callback<ListView<MyFriend>, ListCell<MyFriend>>() {
				
				@Override
				public ListCell<MyFriend> call(ListView<MyFriend> param) {
					return new ListCell<MyFriend>() {
						protected void updateItem(MyFriend item, boolean empty) {
							super.updateItem(item, empty);
							if(item == null || empty) {
								setText(null);
							}else {
								setText(item.getName() + "(" 
										+ item.getId() + ")  " + item.getTel());
							}
						}; {};
					};
				}
			}
		);
		
		/*
		 * 콤보박스에서 항목을 선택하면 선택된 내용이 보여지는
		 * 부분을 *버튼 영역이라고 한다.
		 * 이 버튼 영역의 내용도 변경해주어야 한다.
		 */
		combo.setButtonCell(
			new ListCell<MyFriend>() {
				protected void updateItem(MyFriend item, boolean empty) {
					super.updateItem(item, empty);
					if(item == null || empty) {
						setText(null);
					}else {
						setText(item.getName() + "(" 
								+ item.getId() + ")  " + item.getTel());
					}
				}; {};
			}
		);
		
		// 콤보박스를 클릭했을 때 이벤트 처리
		combo.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// 현재 선택된 항목의 정보를 출력하기

				// 1) 현재 선택된 항목 정보 가져오기
				MyFriend selData = combo.getSelectionModel().getSelectedItem();
//				MyFriend selData = combo.getValue();
				
				// 2) 가져온 데이터 출력
				textArea.setText("ID \t\t: " + selData.getId() + "\n");
				textArea.appendText("이름\t\t: " + selData.getName() + "\n");
				textArea.appendText("연락처\t: " + selData.getTel() + "\n");
				textArea.appendText("주소\t\t: " + selData.getAddr() + "\n");
				
			}
		});
		
		vbox.getChildren().addAll(combo, textArea);
		
		Scene scene = new Scene(vbox, 300, 200);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("ComboBox Test");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * VO역할을 하는 클래스
	 * inner클래스로 작성
	 * @author 김지태
	 * 2018.08.21
	 */
	class MyFriend {
		private String id;
		private String name;
		private String tel;
		private String addr;
		
		/**
		 * 기본 생성자
		 */
		public MyFriend() {}
		
		/**
		 * @param id
		 * @param name
		 * @param tel
		 * @param addr
		 */
		public MyFriend(String id, String name, String tel, String addr) {
			super();
			this.id = id;
			this.name = name;
			this.tel = tel;
			this.addr = addr;
		}
		
		
		/**
		 * 각 컬럼들의 getter/setter
		 */
		public String getId() {
			return id;
		}


		public void setId(String id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
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

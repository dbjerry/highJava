package basic.pagination;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TablePaginationTest extends Application {
	// 전체 데이터 개수
	// (이 값은 나중에 DB에서 데이터 개수를 구해오면 된다.)
	private int dataSize = 123;
	
	private int rowsPerPage = 10;	//	한 화면에 보여줄 레코드 수
	
	private TableView<SampleVO> table;
	
	private Pagination pagination;
	
	// 실무에선 DB에서 데이터를 가져와 저장한다.
	// 지금은 연습용 데이터를 만들어서 저장한다.
	private List<SampleVO> data = createData();
	
	// 연습용 데이터를 만들어주는 메서드
	public List<SampleVO> createData(){
		List<SampleVO> sampleData = new ArrayList<>();
		
		for(int i = 0; i < dataSize; i++) {
			sampleData.add(new SampleVO(i, "foo" + i, "bar" + i));
		}
		return sampleData;
	}
	
	@Override
	public void start(Stage primaryStage) {
		table = new TableView<SampleVO>();
		
		TableColumn<SampleVO, Integer> numCol = new TableColumn<>("번호");
		numCol.setCellValueFactory(new PropertyValueFactory<>("num"));
		numCol.setPrefWidth(150);
		
		TableColumn<SampleVO, String> fooCol = new TableColumn<>("FOO");
		fooCol.setCellValueFactory(new PropertyValueFactory<>("foo"));
		fooCol.setPrefWidth(250);
		
		TableColumn<SampleVO, String> barCol = new TableColumn<>("BAR");
		barCol.setCellValueFactory(new PropertyValueFactory<>("bar"));
		barCol.setPrefWidth(250);
		
		table.getColumns().addAll(numCol, fooCol, barCol);
//		table.setItems(FXCollections.observableArrayList(data));
		
		// 전체 페이지수와 처음 보여줄 인덱스값으로 초기화한다.
		int totalPage = (data.size() / rowsPerPage) + (data.size() % rowsPerPage > 0 ? 1 : 0);
		pagination = new Pagination(totalPage, 0);	//	첫페이지를 보여준다.
//		pagination.setCurrentPageIndex(0);
		
		changeTableView(0);
		
		// Pagination 객체의 현재 인덱스값이 변경되면 처리되는 이벤트
		pagination.currentPageIndexProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue,
							Number newValue) {
						// TODO Auto-generated method stub
						changeTableView(newValue.intValue());
					}
				});
		
		BorderPane root = new BorderPane();
		root.setCenter(table);
		root.setBottom(pagination);
		
		Scene scene = new Scene(root, 800, 600);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("pagination test");
		primaryStage.show();
		
	}

	// 매개변수로  페이지 인덱스값을 받아서  데이터가
	// 저장된 리스트에서 해당 페이지에 출력할 데이터를
	// 구해서 TableView를 변경한다.
	public void changeTableView(int index) {
		int fromIndex = index * rowsPerPage;
		int toIndex = Math.min(fromIndex + rowsPerPage, data.size());
		
		table.setItems(FXCollections.observableArrayList(
				data.subList(fromIndex, toIndex)));
		data.subList(fromIndex, toIndex);	//	값을 from은 포함, to는 미포함
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	public class SampleVO{
		private int num;
		private String foo;
		private String bar;
		
		public SampleVO(int num, String foo, String bar) {
			super();
			this.num = num;
			this.foo = foo;
			this.bar = bar;
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

		public String getFoo() {
			return foo;
		}

		public void setFoo(String foo) {
			this.foo = foo;
		}

		public String getBar() {
			return bar;
		}

		public void setBar(String bar) {
			this.bar = bar;
		}
		
	}
	
}


package basic.menu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class MenuTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 300, 250);
		
		MenuBar menuBar = new MenuBar();
		
		// MenuBar의 가로크기를 항상 Stage의 가로크기와 같도록 한다.
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		root.setTop(menuBar);
		
		
		// File Menu -- new, save, exit
		Menu fileMenu = new Menu("File");
		MenuItem newMenuItem = new MenuItem("New");
		MenuItem saveMenuItem = new MenuItem("Save");
		MenuItem exitMenuItem = new MenuItem("Exit");
		
		// menu에 이벤트 설정하기
		exitMenuItem.setOnAction(e -> {
			Platform.exit();
		});
		
		fileMenu.getItems().addAll(newMenuItem, saveMenuItem, exitMenuItem);

		Menu webMenu = new Menu("Web");
		
		CheckMenuItem htmlMenuItem = new CheckMenuItem("HTML");
		htmlMenuItem.setSelected(true);	//	체크된 상태로 만들기
		
		CheckMenuItem cssMenuItem = new CheckMenuItem("CSS");
		
		webMenu.getItems().addAll(htmlMenuItem, cssMenuItem);
		/////////////////////////////////////////////////////
		
		Menu sqlMenu = new Menu("SQL");
		ToggleGroup tg = new ToggleGroup();
		
		RadioMenuItem mysqlMenuItem = new RadioMenuItem("MySQL");
		mysqlMenuItem.setToggleGroup(tg);
		
		RadioMenuItem oracleMenuItem = new RadioMenuItem("ORACLE");
		oracleMenuItem.setToggleGroup(tg);
		
		Menu tutorialMenu = new Menu("Tutorial");
		tutorialMenu.getItems().addAll(
			new CheckMenuItem("Basic Java"),
			new CheckMenuItem("High Java"),
			new CheckMenuItem("DataBase"),
			new CheckMenuItem("JavaScript")
		);
		
		
		sqlMenu.getItems().addAll(mysqlMenuItem, oracleMenuItem, new SeparatorMenuItem(), tutorialMenu);

		
		menuBar.getMenus().addAll(fileMenu, webMenu, sqlMenu);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("MenuBar Test");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}

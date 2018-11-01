
package basic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CalculatorTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		GridPane root = new GridPane();
		root.setPrefSize(439, 331);
		root.setPadding(new Insets(7));
		root.setHgap(10);
		root.setVgap(10);

		Label number = new Label("0");
		Label temp = new Label("                                      ");
		
		root.add(number, 0, 0, 10, 2);
		root.add(temp, 0, 2, 4, 1);
		root.add(new Button("MC"), 5, 2);
		root.add(new Button("MR"), 6, 2);
		root.add(new Button("MS"), 7, 2);
		root.add(new Button("M+"), 8, 2);
		root.add(new Button("M-"), 9, 2);
		root.add(new Button(""), 0, 3);
		root.add(new Button("Inv"), 1, 3);
		root.add(new Button("In"), 2, 3);
		root.add(new Button("("), 3, 3);
		root.add(new Button(")"), 4, 3);
		root.add(new Button("←"), 5, 3);
		root.add(new Button("CE"), 6, 3);
		root.add(new Button("C"), 7, 3);
		root.add(new Button("±"), 8, 3);
		root.add(new Button("√"), 9, 3);
		root.add(new Button("Int"), 0, 4);
		root.add(new Button("sinh"), 1, 4);
		root.add(new Button("sin"), 2, 4);
		root.add(new Button("x²"), 3, 4);
		root.add(new Button("n!"), 4, 4);
		root.add(new Button("7"), 5, 4);
		root.add(new Button("8"), 6, 4);
		root.add(new Button("9"), 7, 4);
		root.add(new Button("/"), 8, 4);
		root.add(new Button("%"), 9, 4);
		root.add(new Button("dms"), 0, 5);
		root.add(new Button("cosh"), 1, 5);
		root.add(new Button("cos"), 2, 5);
		root.add(new Button("x^y"), 3, 5);
		root.add(new Button("y√x"), 4, 5);
		root.add(new Button("4"), 5, 5);
		root.add(new Button("5"), 6, 5);
		root.add(new Button("6"), 7, 5);
		root.add(new Button("*"), 8, 5);
		root.add(new Button("1/x"), 9, 5);
		root.add(new Button("π"), 0, 6);
		root.add(new Button("tanh"), 1, 6);
		root.add(new Button("tan"), 2, 6);
		root.add(new Button("x³"), 3, 6);
		root.add(new Button("3√x"), 4, 6);
		root.add(new Button("1"), 5, 6);
		root.add(new Button("2"), 6, 6);
		root.add(new Button("3"), 7, 6);
		root.add(new Button("-"), 8, 6);
		root.add(new Button("="), 9, 6, 1, 2);
		root.add(new Button("F-E"), 0, 7);
		root.add(new Button("Exp"), 1, 7);
		root.add(new Button("Mod"), 2, 7);
		root.add(new Button("log"), 3, 7);
		root.add(new Button("10ⁿ"), 4, 7);
		root.add(new Button("0"), 5, 7, 2, 1);
		root.add(new Button("."), 7, 7);
		root.add(new Button("+"), 8, 7);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Calculator");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}

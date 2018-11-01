package basic.chart;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class BarChartTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		// X축, Y축 객체 만들기
		CategoryAxis xAxis = new CategoryAxis();	//	X축 객체
		NumberAxis yAxis = new NumberAxis();		//	Y축 객체
		
		// BarChart 객체 만들기(Number는 integer형, float형 등 숫자 자료형을 전부 포함한 자료형이다.)
		BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
		xAxis.setLabel("국가명");	//	X축 제목
		yAxis.setLabel("수출액");	//	Y축 제목
		
		bc.setTitle("나라별 수출액 현황");	//	차트 제목
		
		// 데이터 셋팅하기
		XYChart.Series ser1 = new XYChart.Series<>();
		ser1.setName("2016년");
		ser1.getData().add(new XYChart.Data("호주", 1234,3));
		ser1.getData().add(new XYChart.Data("미국", 2234,3));
		ser1.getData().add(new XYChart.Data("프랑스", 1734,3));
		ser1.getData().add(new XYChart.Data("영국", 3334,3));
	
		XYChart.Series ser2 = new XYChart.Series<>();
		ser2.setName("2017년");
		ser2.getData().add(new XYChart.Data("호주", 3234,3));
		ser2.getData().add(new XYChart.Data("미국", 2134,3));
		ser2.getData().add(new XYChart.Data("프랑스", 2734,3));
		ser2.getData().add(new XYChart.Data("영국", 1334,3));
		
		XYChart.Series ser3 = new XYChart.Series<>();
		ser3.setName("2018년");
		ser3.getData().add(new XYChart.Data("호주", 2234,3));
		ser3.getData().add(new XYChart.Data("미국", 2134,3));
		ser3.getData().add(new XYChart.Data("프랑스", 3734,3));
		ser3.getData().add(new XYChart.Data("영국", 4334,3));
		
		
		// 셋팅된 데이터를 차트에 추가하기
		bc.getData().addAll(ser1, ser2, ser3);
		
		Scene scene = new Scene(bc);
		 primaryStage.setScene(scene);
		 primaryStage.setTitle("Bar Chart");
		 primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}

package basic.chart;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

public class PieChartTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		ObservableList<PieChart.Data> pieChartData = 
				FXCollections.observableArrayList(
						new PieChart.Data("이순신", 90),
						new PieChart.Data("성춘향", 95),
						new PieChart.Data("이몽룡", 70),
						new PieChart.Data("변학도", 80),
						new PieChart.Data("강감찬", 85)
				);
		
		PieChart pChart = new PieChart(pieChartData);
		pChart.setTitle("성적 그래프");
		pChart.setLabelLineLength(10);		//	값 영역과 레이블 사이의 선 길이
		pChart.setLegendSide(Side.LEFT);	//	범례 위치
		
		Scene scene = new Scene(pChart);
		
		primaryStage.setScene(scene);
		primaryStage.setWidth(500);
		primaryStage.setHeight(500);
		primaryStage.setTitle("원형 그래프 연습");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}

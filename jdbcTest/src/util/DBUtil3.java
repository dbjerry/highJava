package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
/**
 * db.properties파일의 내용으로 세팅
 * 
 * - ResourceBundle 객체 이용하기
 */
public class DBUtil3 {
	
	static ResourceBundle bundle;	//	ResourceBundle 객체 변수 선언
	
	static{
		
		/* 파일을 읽어와 객체를 생성한다. */
		bundle = ResourceBundle.getBundle("db");
		
		
		try{
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(bundle.getString("driver"));
		}catch(ClassNotFoundException e){
			System.out.println("JDBC드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		try{
//			return DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521:xe",
//					"Jerry",
//					"java"
//					);
			return DriverManager.getConnection(
					bundle.getString("url"),
					bundle.getString("user"),
					bundle.getString("pass")
					);
		}catch (SQLException e){
			System.out.println("DB 연결 실패!");
			return null;
		}
	}
}

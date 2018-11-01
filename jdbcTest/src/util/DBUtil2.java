package util;
/**
 * db.properties파일의 내용을 읽어와 DBUtil에 세팅하기
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil2 {
	static Properties prop;	//	Properties객체 변수 선언
	
	static{
		prop = new Properties();	//	객체 생성
		File f = new File("res/db.properties");
		
		try{
			FileInputStream fin = new FileInputStream(f);
			prop.load(fin);
			
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(prop.getProperty("driver"));
			
			
			
		}catch(ClassNotFoundException e){
			System.out.println("JDBC드라이버 로딩 실패");
			e.printStackTrace();
		}catch(FileNotFoundException e){
			System.out.println("db.properties파일이 없습니다.");
			e.printStackTrace();
		}catch(IOException e){
			System.out.println("입출력 오류입니다.");
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
					prop.getProperty("url"),
					prop.getProperty("user"),
					prop.getProperty("pass")
					);
		}catch (SQLException e){
			System.out.println("DB 연결 실패!");
			return null;
		}
	}
}

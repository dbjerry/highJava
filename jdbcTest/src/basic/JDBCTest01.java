/**
 * JDBC	=>	Java DataBase Connectivity
 * 			Java에서 데이터베이스에 연결하여 작업하는 기술
 */
package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest01 {
	/*
	 * 데이터베이스 처리 순서
	 * 1) 드라이버 로딩
	 * 2) 해당 DB에 접속
	 * 3) 질의(SQL명령) 수행
	 * 4) 질의 결과를 받고 처리
	 * 5) 종료(사용했던 자원을 반납)
	 */
	public static void main(String[] args) {

		/* DB작업에 필요한 객체변수 선언 */
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null; // 쿼리문이 SELECT일때만 필요

		try {
			/**
			 * 1) 드라이버 로딩
			 * 	- Class.forName()을 이용
			 */
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			/**
			 * 2) 해당 DB에 접속
			 * 	- DriverManager.getConnection()을 이용하고
			 *	    접속이 성공하면 Connection객체가 생성
			 */
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "SYS as sysdba";	//	등록된 사용자 ID
			String pass = "java";	//	등록된 사용자 패스워드
			
			conn = DriverManager.getConnection(url, user, pass);
			
			/**
			 * 3) Statement객체 생성 (or PreparedStatement객체 생성)
			 * 	- 질의를 수행하고 결과를 받아오는 객체
			 * 	    이 객체의 생성은 Connection객체를 이용
			 */
			stmt = conn.createStatement();
			
			/**
			 * 	4)	Statement객체를 이용하여 질의를 수행하고
			 * 		결과를 받아서 ResultSet객체에 담는다.
			 * 		(단, ResultSet객체는 SELECT문일 때만 사용)
			 */
			String sql = "select * from lprod";	//	실행할 SQL명령

			/*
			 * 기타 INSERT, UPDATE, DELETE명령일 때는
			 * executeUpdate()를 사용
			 */
			rs = stmt.executeQuery(sql);	//	SQL명령이 SELECT일 경우
			
			/**
			 * 5) ResultSet객체에 저장되어 있는 자료를
			 * 	    반복문과 next()를 이용하여 차례로 읽고 처리
			 */
			System.out.println("실행 Query : " + sql);
			System.out.println(" == Query 실행 결과 == ");
			
			/*
			 * rs.next()	=>	ResultSet객체의 데이터를 가리키는 포인터를
			 * 					다음 레코드로 이동시키고 그 곳에 데이터가
			 * 					있으면 true, 없으면 false를 반환한다.
			 */
			
			while(rs.next()){
				/**
				 * 각 레코드 단위의 자료를 가져와 처리한다.
				 **
				 * 각 레코드 단위의 자료를 가져오는 방법
				 * 방법 1	:	rs.get자료형이름("컬럼명")
				 * 방법 2	:	rs.get자료형이름(컬럼의순번값)
				 * 		=>	컬럼의 순번값은 1번부터 시작한다.
				 */
				System.out.println("LPROD_ID : " + rs.getInt("LPROD_ID"));	//	컬럼명 이용
				System.out.println("LPROD_GU : " + rs.getString(2));		//	컬럼의 순번 이용
				System.out.println("LPROD_NM" + rs.getString("LPROD_NM"));
				System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			}
			System.out.println();
			System.out.println("출력 끝.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			
			/* 6) 사용한 자원을 반납 */
			if(rs != null){try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if(stmt != null){try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if(conn != null){try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}
			
		}
	}
}


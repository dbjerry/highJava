package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCTest02_2 {
	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Scanner scan = new Scanner(System.in);
		System.out.print("입력 > ");
		int lprod_id = scan.nextInt();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:xe",
						"SYS as sysdba",
						"java"
					);
			
			String sql = "select * from lprod where lprod_id > ?";
			
			/**
			 * 3. PreparedStatement객체 생성하기
			 *	=>	이 객체를 생성할 때 쿼리문을 넣어서 만든다.
			 *		쿼리문에는 물음표(?)가 있을 수 있는데
			 *		이 물음표자리에는 어떤 데이터가 들어갈 자리를
			 *		나타내는 표식이다.
			 */
			pstmt = conn.prepareStatement(sql);
			
			
			/**
			 * 3-1. PreparedStatement객체를 만들 때 사용한 쿼리문의
			 * 		물음표 자리에 들어갈 값을 셋팅해 준다.
			 * 		형식) pstmt.set자료형이름(물음표순번, 값);
			 * 			 물음표순번은 1부터 시작한다.
			 */
			pstmt.setInt(1, lprod_id);
			
			/* 4. */
			rs = pstmt.executeQuery();
			
			/* 5. */
			while(rs.next()){
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			
			/* 6) 사용한 자원을 반납 */
			if(rs   != null){try { rs.close();   } catch (SQLException e) { e.printStackTrace(); }}
			if(pstmt != null){try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if(conn != null){try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}
		}
		
		
		
	}
}

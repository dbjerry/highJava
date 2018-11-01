package basic;
/**
 * Statement를 이용한 처리
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * lprod_id: 101, lprod_gu: N101, lprod_nm: 농산물
 * lprod_id: 102, lprod_gu: N102, lprod_nm: 수산물
 * lprod_id: 103, lprod_gu: N103, lprod_nm: 축산물
 * 
 * 위 3개의 자료를 insert 하기
 */
public class JDBCTest04_1 {
	public static void main(String[] args) {
		/* SELECT문이 아닌 쿼리문에서는 ResultSet이 필요없다. */
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		int[] lprodId = new int[]{101, 102, 103};
		String[] lprodGu = new String[]{"N101", "N102", "N103"};
		String[] lprodNm = new String[]{"농산물", "수산물", "축산물"};
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:xe",
						"SYS as sysdba",
						"java"
					);
			
			/* Statement를 이용한 처리 */
			stmt = conn.createStatement();
			
			/*
			 * insert into lprod(lprod_id, lprod_gu, lprod_nm)
			 * values(101, 'N101', '농산물')
			 */
			for(int i = 0; i < lprodId.length; i++){
				String sql = "INSERT INTO lprod (lprod_id, lprod_gu, lprod_nm) "
							+ "values (" + lprodId[i] + ", '" + lprodGu[i] + "' , '" + lprodNm[i] + "')";
				
				// executeUpdate(쿼리문)메서드는 실행 후 성공한 레코드수를 반환한다.
				int cnt = stmt.executeUpdate(sql);
				
				System.out.println("반환값 : " + cnt);
			}
			System.out.println("작업끝");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			if(pstmt != null){try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if(stmt != null){try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if(conn != null){try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}
		}
	}
}

package basic;
/**
 * PreparedStatement를 이용한 처리
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
public class JDBCTest04_2 {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		/* SELECT문이 아닌 쿼리문에서는 ResultSet이 필요없다. */
		
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
			
			/* PreparedStatement를 이용한 처리 */
			String sql = "insert into lprod (lprod_id, lprod_gu, lprod_nm) "
					+ "values(?,?,?) ";
			
			pstmt = conn.prepareStatement(sql);
			
			/* 물음표 자리에 들어갈 데이터를 세팅한다. */
			for(int i = 0; i < lprodId.length; i++){
				pstmt.setInt(1, lprodId[i]);
				pstmt.setString(2, lprodGu[i]);
				pstmt.setString(3, lprodNm[i]);
				
				int cnt = pstmt.executeUpdate();
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

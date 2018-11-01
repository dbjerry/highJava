package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCTest05 {
	/**
	 * LPROD 테이블에 새로운 데이터를 추가하시오.
	 * - 추가할 자료는 직접 입력 받아서 처리한다
	 * - PK오류가 발생하지 않도록 처리한다.
	 * - PreparedStatement를 이용한다.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:xe",
						"SYS as sysdba",
						"java"
					);
			
			System.out.println("LPROD테이블에 추가할 사항을 입력해주세요.\n");
			System.out.println("[주의] ID는 숫자만 입력하여주세요.");
			System.out.print("LPROD_ID > ");
			int lprod_Id = scan.nextInt();
			
			String lprod_Gu;
			int count;
			do{
				System.out.print("LPROD_GU > ");
				lprod_Gu = scan.next();
				
				String sqlCnt = "SELECT COUNT(*) FROM lprod WHERE lprod_gu = ?";
				//String sqlCnt = "SELECT COUNT(*) cnt FROM lprod WHERE lprod_gu = ?"; <= cnt는 Alias.
				
				pstmt1 = conn.prepareStatement(sqlCnt);
				pstmt1.setString(1, lprod_Gu);
			
				rs = pstmt1.executeQuery();
				
				count = 0;
				if(rs.next()){
					//count = rs.getInt(1);
					count = rs.getInt("count(*)");
					//count = rs.getInt(cnt); <= 컬럼명 대신 Alias명을 사용.
				}
				
				if(count > 0){
					System.out.println("입력한 사품 분류 코드 " + lprod_Gu + "는 이미 사용중인 코드입니다.");
					System.out.println("다시 입력해주세요.");
				}
			}while(count > 0);
			
			System.out.print("LPROD_NM > ");
			String lprod_Nm = scan.next();
			System.out.println("Loading...");
			
			
			String sql = "INSERT INTO lprod (lprod_id, lprod_gu, lprod_nm) VALUES (?, ?, ?)";
			pstmt2 = conn.prepareStatement(sql);
			
			pstmt2.setInt(1, lprod_Id);
			pstmt2.setString(2, lprod_Gu);
			pstmt2.setString(3, lprod_Nm);
			
			int cnt = pstmt2.executeUpdate();
			
			if(cnt > 0){
				System.out.println("등록 성공");
			}else{
				System.out.println("등록 실패");
			}
			System.out.println("작업종료");
		
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			if(rs != null){try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if(pstmt2 != null){try { pstmt2.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if(pstmt1 != null){try { pstmt1.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if(conn != null){try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}
			scan.close();
		}
	}
}




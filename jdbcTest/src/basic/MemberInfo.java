package basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import util.DBUtil3;

/**
 * 회원을 관리하는 프로그램 작성.
 * 
 * 아래 메뉴의 기능을 모두 구현하시오. (CRUD 기능 구현하기)
 * 
 * - MYMEMBER테이블을 이용. - 삭제는 회원 ID를 입력받아서 처리.
 * 
 * - 자료 검색에서 회원ID는 완전히 일치하는 정보를 검색하고
 *   회원 이름, 전화번호, 주소는 일부분만 포함되어도 검색되도록 한다.
 *   (Query문에서 걸러서 가져온다.)
 * 
 * 메뉴예시) == 작업 선택 ==
 * 			1. 자료 입력 ㅡㅡㅡ(insert : c)
 * 			2. 자료 삭제 ㅡㅡㅡ(delete : d)
 * 			3. 자료 수정 ㅡㅡㅡ(update : u)
 * 			4. 전체 자료 출력ㅡㅡㅡ(select : r)
 * 			5. 자료 검색
 * 			0. 작업 끝
 * 			============
 * 
 * 검색할 항목을 선택하세요.
 * 1.회원ID | 2.회원이름 | 3.전화번호 | 4.주소
 * 항목 선택 >> 1
 * 
 * 검색할 회원ID >> a001
 */

public class MemberInfo {

	public static void main(String[] args) {
		/* View class 객체 생성 */
		View v = new View();
		/* start() in View class 호출 */
		v.start();
	}
}

/**
 * View class : MENU 및 시각적인 부분
 * (눈에 보여지는 파트)을 다룰 클래스
 * @author 김지태 2018-08-02
 */
class View {
	Scanner scan = new Scanner(System.in);
	JavaDataBaseConnectivity jdbc = new JavaDataBaseConnectivity();
	Search search = new Search();

	public void start() {
		while (true) {
			System.out.println();
			System.out.println("==== M E N U ====");
			System.out.println(" 1. 자료 입력");
			System.out.println(" 2. 자료 삭제");
			System.out.println(" 3. 자료 수정");
			System.out.println(" 4. 전체 자료 출력");
			System.out.println(" 5. 자료 검색");
			System.out.println(" 0. 작업 종료");
			System.out.println("=================");
			System.out.print("작업 선택 > ");
			int choice = -1;
			try {
				choice = scan.nextInt();
			} catch (Exception e) {
				System.out.println("잘못 입력되었습니다. 다시 입력해주세요.");
				continue;
			}

			switch (choice) {
			case 1:
				jdbc.create();
				break;
			case 2:
				jdbc.delete();
				break;
			case 3:
				jdbc.update();
				break;
			case 4:
				jdbc.read();
				break;
			case 5:
				search.search();
				break;
			case 0:
				System.out.println("\n\n작업을 종료합니다.\n\n");
				return;
			}
		}
	}
}

class JavaDataBaseConnectivity {
	DBUtil3 db = new DBUtil3();
	Scanner scan = new Scanner(System.in);

	/* 자료등록(입력) 메서드 */
	public void create() {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;

		System.out.println("\n=== 1. 자료입력 ===");
		System.out.println("Loading...\n");
		try {
			conn = db.getConnection();

			String mem_id;
			int count;
			do {
				System.out.print("mem_id > ");
				mem_id = scan.nextLine();

				String sql_select_id = "SELECT COUNT(*) FROM mymember WHERE mem_id = ?";
				pstmt1 = conn.prepareStatement(sql_select_id);
				pstmt1.setString(1, mem_id);

				rs = pstmt1.executeQuery();

				count = 0;
				if (rs.next()) {
					// count = rs.getInt(1);
					count = rs.getInt("count(*)");
				}
				if (count > 0) {
					System.out.println("입력한 mem_id 코드 [ " + mem_id + " ]는 이미 사용중인 코드입니다.");
					System.out.println("다시 입력해주세요.");
				}
			} while (count > 0);

			System.out.print("mem_name > ");
			String mem_name = scan.nextLine();
			System.out.print("mem_tel > ");
			String mem_tel = scan.nextLine();
			System.out.print("mem_addr > ");
			String mem_addr = scan.nextLine();

			String sql = "INSERT INTO mymember (mem_id, mem_name, mem_tel, mem_addr) VALUES (?, ?, ?, ?)";
			pstmt2 = conn.prepareStatement(sql);

			pstmt2.setString(1, mem_id);
			pstmt2.setString(2, mem_name);
			pstmt2.setString(3, mem_tel);
			pstmt2.setString(4, mem_addr);

			int cnt = pstmt2.executeUpdate();

			if (cnt > 0) {
				System.out.println("\n=== add success ===\n");
			} else {
				System.out.println("\n=== add losing ===\n");
			}
			System.out.println("\n=================\n");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) 	{ try {rs.close(); } 	 catch (SQLException e) { e.printStackTrace(); }}
			if (pstmt2 != null) { try {pstmt2.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if (pstmt1 != null) { try {pstmt1.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if (conn != null) 	{ try {conn.close(); } 	 catch (SQLException e) { e.printStackTrace(); }}
		}
	}


	/* 자료삭제 메서드 */
	public void delete() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		System.out.println("\n=== 2. 자료삭제 ===");
		System.out.println("Loading...\n");

		try {
			conn = DBUtil3.getConnection();

			System.out.print("삭제할 mem_id 입력 > ");
			String mem_id = scan.nextLine();

			pstmt = conn
					.prepareStatement("DELETE FROM mymember WHERE mem_id = ?");
			pstmt.setString(1, mem_id);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("\n=== delete success ===\n");
			} else {
				System.out.println("\n=== delete losing ===\n");
			}
			System.out.println("\n=================\n");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				if (rs != null)		{ try { rs.close(); }   catch (SQLException e) {e.printStackTrace();}}
				if (pstmt != null)	{ try { pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
				if (conn != null)	{ try { conn.close(); } catch (SQLException e) {e.printStackTrace();}}
		}
	}

	/* 자료수정 메서드 */
	public void update() {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rs_select = null;
		ResultSet rs = null;

		System.out.println("\n=== 3. 자료수정 ===");
		System.out.println("Loading...\n");

		try {

			conn = DBUtil3.getConnection();
			stmt = conn.createStatement();

			System.out.println("자료를 수정하시려면 ID를 먼저 입력해주세요.");
			System.out.print("mem_id 입력 > ");
			String mem_id = scan.nextLine();
			System.out.println();
			String sql = "SELECT mem_id, mem_name, mem_tel, mem_addr FROM mymember WHERE mem_id = "
					+ "'" + mem_id + "'";
			rs_select = stmt.executeQuery(sql);

			if (rs_select.next()) {
				System.out.println("mem_id : "	 + rs_select.getString("mem_id"));
				System.out.println("mem_name : " + rs_select.getString("mem_name"));
				System.out.println("mem_tel : "	 + rs_select.getString("mem_tel"));
				System.out.println("mem_addr : " + rs_select.getString("mem_addr"));
				System.out.println("─────────────────────────────────");
			}
			System.out.println("수정해주세요.\n수정을 원치 않는 컬럼은 \"c\"를 눌러주세요");
			System.out.print("mem_id > ");
			String mem_id2 = scan.nextLine();
			System.out.print("mem_name > ");
			String mem_name = scan.nextLine();
			System.out.print("mem_tel > ");
			String mem_tel = scan.nextLine();
			System.out.print("mem_addr > ");
			String mem_addr = scan.nextLine();

			/* mem_id 수정 if문 */
			if (mem_id2.equals("c")) {
				pstmt1 = conn.prepareStatement("UPDATE mymember SET mem_id=? WHERE mem_id=?");
				pstmt1.setString(1, rs_select.getString("mem_id"));
				pstmt1.setString(2, rs_select.getString("mem_id"));
			} else {
				pstmt1 = conn.prepareStatement("UPDATE mymember SET mem_id=? WHERE mem_id=?");
				pstmt1.setString(1, mem_id2);
				pstmt1.setString(2, rs_select.getString("mem_id"));
			}

			/* mem_name 수정 if문 */
			if (mem_name.equals("c")) {
				pstmt2 = conn.prepareStatement("UPDATE mymember SET mem_name=? WHERE mem_name=?");
				pstmt2.setString(1, rs_select.getString("mem_name"));
				pstmt2.setString(2, rs_select.getString("mem_name"));
			} else {
				pstmt2 = conn.prepareStatement("UPDATE mymember SET mem_name=? WHERE mem_name=?");
				pstmt2.setString(1, mem_name);
				pstmt2.setString(2, rs_select.getString("mem_name"));
			}

			/* mem_tel 수정 if문 */
			if (mem_name.equals("c")) {
				pstmt3 = conn.prepareStatement("UPDATE mymember SET mem_tel=? WHERE mem_tel=?");
				pstmt3.setString(1, rs_select.getString("mem_tel"));
				pstmt3.setString(2, rs_select.getString("mem_tel"));
			} else {
				pstmt3 = conn.prepareStatement("UPDATE mymember SET mem_tel=? WHERE mem_tel=?");
				pstmt3.setString(1, mem_tel);
				pstmt3.setString(2, rs_select.getString("mem_tel"));
			}

			/* mem_addr 수정 if문 */
			if (mem_name.equals("c")) {
				pstmt4 = conn.prepareStatement("UPDATE mymember SET mem_addr=? WHERE mem_addr=?");
				pstmt4.setString(1, rs_select.getString("mem_addr"));
				pstmt4.setString(2, rs_select.getString("mem_addr"));
			} else {
				pstmt4 = conn.prepareStatement("UPDATE mymember SET mem_addr=? WHERE mem_addr=?");
				pstmt4.setString(1, mem_addr);
				pstmt4.setString(2, rs_select.getString("mem_addr"));
			}

			int cnt1 = pstmt1.executeUpdate();
			int cnt2 = pstmt2.executeUpdate();
			int cnt3 = pstmt3.executeUpdate();
			int cnt4 = pstmt4.executeUpdate();

			if (cnt1 > 0 || cnt2 > 0 || cnt3 > 0 || cnt4 > 0) {
				System.out.println("\n=== update success ===\n");
			} else {
				System.out.println("\n=== update losing ===\n");
			}
			System.out.println("\n=================\n");

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (rs != null) 	{ try {rs.close(); } 	 catch (SQLException e) { e.printStackTrace(); }}
			if (pstmt4 != null) { try {pstmt4.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if (pstmt3 != null) { try {pstmt3.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if (pstmt2 != null) { try {pstmt2.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if (pstmt1 != null) { try {pstmt1.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if (conn != null) 	{ try {conn.close(); } 	 catch (SQLException e) { e.printStackTrace(); }}
		}
	}

	/* 자료 전체 출력 메서드 */
	public void read() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		System.out.println("\n=== 4. 전체출력 ===");
		System.out.println("Loading...\n");

		try {
			conn = DBUtil3.getConnection();

			stmt = conn.createStatement();
			String sql = "SELECT * FROM mymember";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println("mem_id	 : " + rs.getString("mem_id"));
				System.out.println("mem_name : " + rs.getString("mem_name"));
				System.out.println("mem_tel	 : " + rs.getString("mem_tel"));
				System.out.println("mem_addr : " + rs.getString("mem_addr"));
				System.out.println();
			}
			System.out.println("\n=== print success ===\n");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)		{ try { rs.close(); }   catch (SQLException e) {e.printStackTrace();}}
			if (stmt != null)	{ try { stmt.close(); } catch (SQLException e) {e.printStackTrace();}}
			if (conn != null)	{ try { conn.close(); } catch (SQLException e) {e.printStackTrace();}}
		}
	}
}


/* 자료검색 class */
class Search{
	Scanner scan = new Scanner(System.in);
	
	/* 자료검색 메서드 */
	public void search() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		while(true){
			System.out.println("검색할 항목을 선택하세요.");
			System.out.println("1.회원id | 2.회원이름 | 3.전화번호 | 4.주소");
			System.out.print("항목 선택  > ");
			int choice = -1;
			try{
				choice = scan.nextInt();
			}catch(Exception e){
				System.out.println("잘못 입력하셨습니다, 다시 입력해주세요.");
				continue;
			}
			
			switch(choice){
			case 1:	//	회원id로 검색
				scan.nextLine();
				System.out.print("\n검색할 ID 입력 > ");
				String mem_id = scan.nextLine();
				
				try {
					conn = DBUtil3.getConnection();
					String sql = "SELECT * FROM mymember WHERE mem_id=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, mem_id);
					rs = pstmt.executeQuery();

					while(rs.next()) {
						System.out.println("mem_id	 : " + rs.getString("mem_id"));
						System.out.println("mem_name : " + rs.getString("mem_name"));
						System.out.println("mem_tel	 : " + rs.getString("mem_tel"));
						System.out.println("mem_addr : " + rs.getString("mem_addr"));
						System.out.println();
					}
					System.out.println("\n=== print success ===\n");

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (rs != null)		{ try { rs.close();  }	catch (SQLException e) {e.printStackTrace();}}
					if (pstmt != null)	{ try { pstmt.close(); }catch (SQLException e) {e.printStackTrace();}}
					if (conn != null)	{ try { conn.close(); }	catch (SQLException e) {e.printStackTrace();}}
				}
				break;
				
			case 2:	//	회원이름으로 검색
				scan.nextLine();
				System.out.print("\n검색할 이름 입력 > ");
				String mem_name = scan.nextLine();
				
				try {
					conn = DBUtil3.getConnection();
					stmt = conn.createStatement();
					String sql = "SELECT * FROM mymember WHERE mem_name LIKE '%" + mem_name + "%'";
					rs = stmt.executeQuery(sql);

					while(rs.next()) {
						System.out.println("mem_id	 : " + rs.getString("mem_id"));
						System.out.println("mem_name : " + rs.getString("mem_name"));
						System.out.println("mem_tel	 : " + rs.getString("mem_tel"));
						System.out.println("mem_addr : " + rs.getString("mem_addr"));
						System.out.println();
					}
					System.out.println("\n=== print success ===\n");

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (rs != null)		{ try { rs.close();  }	catch (SQLException e) {e.printStackTrace();}}
					if (pstmt != null)	{ try { pstmt.close(); }catch (SQLException e) {e.printStackTrace();}}
					if (conn != null)	{ try { conn.close(); }	catch (SQLException e) {e.printStackTrace();}}
				}
				break;
				
			case 3:	//	전화번호로 검색
				scan.nextLine();
				System.out.print("\n검색할 번호 입력 > ");
				String mem_tel = scan.nextLine();
				
				try {
					conn = DBUtil3.getConnection();
					stmt = conn.createStatement();
					String sql = "SELECT * FROM mymember WHERE mem_name LIKE '%" + mem_tel + "%'";
					rs = stmt.executeQuery(sql);

					while(rs.next()) {
						System.out.println("mem_id	 : " + rs.getString("mem_id"));
						System.out.println("mem_name : " + rs.getString("mem_name"));
						System.out.println("mem_tel	 : " + rs.getString("mem_tel"));
						System.out.println("mem_addr : " + rs.getString("mem_addr"));
						System.out.println();
					}
					System.out.println("\n=== print success ===\n");

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (rs != null)		{ try { rs.close();  }	catch (SQLException e) {e.printStackTrace();}}
					if (pstmt != null)	{ try { pstmt.close(); }catch (SQLException e) {e.printStackTrace();}}
					if (conn != null)	{ try { conn.close(); }	catch (SQLException e) {e.printStackTrace();}}
				}
				break;
				
			case 4:	//	주소로 검색
				scan.nextLine();
				System.out.print("\n검색할 번호 입력 > ");
				String mem_addr = scan.nextLine();
				
				try {
					conn = DBUtil3.getConnection();
					stmt = conn.createStatement();
					String sql = "SELECT * FROM mymember WHERE mem_name LIKE '%" + mem_addr + "%'";
					rs = stmt.executeQuery(sql);

					while(rs.next()) {
						System.out.println("mem_id	 : " + rs.getString("mem_id"));
						System.out.println("mem_name : " + rs.getString("mem_name"));
						System.out.println("mem_tel	 : " + rs.getString("mem_tel"));
						System.out.println("mem_addr : " + rs.getString("mem_addr"));
						System.out.println();
					}
					System.out.println("\n=== print success ===\n");

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (rs != null)		{ try { rs.close();  }	catch (SQLException e) {e.printStackTrace();}}
					if (pstmt != null)	{ try { pstmt.close(); }catch (SQLException e) {e.printStackTrace();}}
					if (conn != null)	{ try { conn.close(); }	catch (SQLException e) {e.printStackTrace();}}
				}
				break;
			}
		}
	}
}

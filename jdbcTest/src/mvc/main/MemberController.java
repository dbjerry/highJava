package mvc.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import util.DBUtil;
import util.DBUtil3;
import mvc.service.IMemberService;
import mvc.service.MemberService;
import mvc.vo.MemberVO;

public class MemberController {
	Scanner scan = new Scanner(System.in);
	private IMemberService service;
	
	public MemberController(){
		service = MemberService.getInstance();
	}
	
	public static void main(String[] args) {
		new MemberController().startMember();
	}
	
	/* 메뉴를 출력하는 메서드 */
	public int displayMenu(){
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
		int num = scan.nextInt();
		return num;
	}
	
	/* 작업을 시작하는 메서드 */
	public void startMember(){
		int choice;
		
		do{
			choice = displayMenu();
			switch(choice){
				case 1 :  		// 자료 입력 
					insertMember();
					break;
				case 2 : 		// 자료 삭제
					deleteMember();
					break;
				case 3 : 		// 자료 수정
					updateMember();
					break;
				case 4 : 		// 전체 자료 출력
					displayAllMember();
					break;
				case 5 :		// 자료 검색
					searchMember();
					break;
				case 0 : 		// 종료.
					System.out.println("\n\n작업을 종료합니다.\n\n");
					break;
				default : 
					System.out.println("잘못 입력되었습니다. 다시 입력해주세요.");
					
			}
		}while(choice!=0);
		
	}
	
	
	/* 회원 정보를 추가하는 메서드 */
	public void insertMember(){
		System.out.println();
		System.out.println("\n=== 1. 자료입력 ===");
		System.out.println("추가할 회원 정보를 입력하세요.");
		
		String memId = null;
		int count;
		do{
			System.out.print("회원 ID > " );
			memId = scan.next();
			
			/* 해당 ID를 가진 회원이 있는지 확인하는 메서드 호출 */
			count = service.getMember(memId);
			
			if(count>0){
				System.out.println("입력한 mem_id 코드 [ " + memId + " ]는 이미 사용중인 코드입니다.");
				System.out.println("다른 ID로 다시 입력하세요.");
			}
			
		}while(count>0);
		
		System.out.print("회원 이름 > ");
		String memName = scan.next();
		
		System.out.print("회원 전화번호 > ");
		String memTel = scan.next();
		
		scan.nextLine();
		System.out.print("회원 주소 > ");
		String memAddr = scan.nextLine();
	
		/* 입력한 정보를 MemberVO객체에 담는다. */
		MemberVO memVO = new MemberVO();
		memVO.setMem_id(memId);
		memVO.setMem_name(memName);
		memVO.setMem_tel(memTel);
		memVO.setMem_addr(memAddr);
		
		int cnt = service.insertMember(memVO);
		
		if(cnt>0){
			System.out.println(memId + "(" + memName + ") add success.");
		}else{
			System.out.println("\n=== add losing ===\n");
		}
	}
	
	
	/* 회원 정보를 삭제하는 메서드 */
	public void deleteMember(){
		System.out.println();
		System.out.println("\n=== 2. 자료삭제 ===");
		System.out.println("삭제할 회원 정보를 입력하세요.");
		System.out.print("삭제할 회원 ID > ");
		String memId = scan.next();
		
		int cnt = service.deleteMember(memId);
		
		if(cnt>0){
			System.out.println(memId + " delete success.");
		}else{
			System.out.println("\n=== delete losing ===\n");
		}
	}
	
	
	/* 회원 정보를 수정하는 메서드 */
	public void updateMember(){
		System.out.println();
		System.out.println("\n=== 3. 자료수정 ===");
		System.out.println("수정할 회원 ID를 입력하세요.");
		System.out.print("회원 ID > ");
		String memId = scan.next();
		
		System.out.println();
		System.out.print("회원 이름을 변경할까요? (y/n) ");
		String ans = scan.next();
		
		String memName = null;
		if("Y".equals(ans) || "y".equals(ans)){
			System.out.print("새로운 회원 이름 > ");
			memName = scan.next();
		}
		
		System.out.println();
		System.out.print("전화 번호를 변경할까요? (y/n) ");
		ans = scan.next();
		
		String memTel = null;
		if("Y".equals(ans) || "y".equals(ans)){
			System.out.print("새로운 전화번호 > ");
			memTel = scan.next();
		}
		
		System.out.println();
		System.out.print("회원 주소를 변경할까요? (y/n) ");
		ans = scan.next();
		
		String memAddr = null;
		if("Y".equals(ans) || "y".equals(ans)){
			scan.nextLine();
			System.out.print("새로운 주소 > ");
			memAddr = scan.nextLine();
		}
		
		String temp = "";
		if(memName!=null){
			temp += "mem_name = ? ";
		}
		
		if(memTel!=null){
			if(!"".equals(temp)){
				temp += ", ";
			}
			temp += "mem_tel = ? ";
		}
		
		if(memAddr!=null){
			if(!"".equals(temp)){
				temp += ", ";
			}
			temp += "mem_addr = ? ";
		}
		
		MemberVO memVO = new MemberVO();
		memVO.setMem_id(memId);
		memVO.setMem_name(memName);
		memVO.setMem_tel(memTel);
		memVO.setMem_addr(memAddr);
		
		int cnt = service.updateMember(memVO);
		
		if(cnt>0){
			System.out.println(memId + " update success.");
		}else{
			System.out.println("\n=== update losing ===\n");
		}
	}
	
	
	/* 전체 회원 정보를 출력하는 메서드 */
	public void displayAllMember(){
		System.out.println();
		System.out.println("----------------------------------------");
		System.out.println("  ID    name     tel    addr");
		System.out.println("----------------------------------------");
		
		List<MemberVO> memList = null;
		
		/* 전체 데이터를 가져온다. */
		memList = service.getMemberList();
		
		for(MemberVO memVO : memList){
			
			String memId = memVO.getMem_id();
			String memName = memVO.getMem_name();
			String memTel = memVO.getMem_tel();
			String memAddr = memVO.getMem_addr();
			
			System.out.println(memId + "   " + memName + "   "
					+ memTel + "   " + memAddr);
		}
		System.out.println("----------------------------------------");
		System.out.println("print finish.");
	}
	
	
	/* 자료검색 메서드 */
	public void searchMember() {
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

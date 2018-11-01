package basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import util.DBUtil;

/*
	회원을 관리하는 프로그램 작성.
	
	아래 메뉴의 기능을 모두 구현하시오.
	(CRUD 기능 구현하기)
	
	- MYMEMBER테이블을 이용한다.
	- 삭제는 회원ID를 입력받아서 처리한다.
	
	메뉴예시)
	
	== 작업 선택 ==
	1. 자료 입력		(insert --> C)
	2. 자료 삭제		(delete --> D)
	3. 자료 수정		(update --> U)
	4. 전체 자료 출력   (select --> R)
	0. 작업 끝.
	=================



*/
public class MemberInfo_sem {
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private PreparedStatement pstmt2 = null;
	private ResultSet rs = null;
	
	Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		new MemberInfo_sem().startMember();
	}
	
	
	// 메뉴를 출력하는 메서드
	public int displayMenu(){
		System.out.println();
		System.out.println("=======================");
		System.out.println("    -- 작 업 선 택 --");
		System.out.println("     1. 자료 입력");
		System.out.println("     2. 자료 삭제");
		System.out.println("     3. 자료 수정");
		System.out.println("     4. 전체 자료 출력");
		System.out.println("     0. 작업 끝...");
		System.out.println("=======================");
		System.out.print("원하는 작업 선택 >> ");
		int num = scan.nextInt();
		return num;
	}
	
	// 작업을 시작하는 메서드
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
				case 0 : 		// 종료.
					System.out.println("작업을 마칩니다.");
					break;
				default : 
					System.out.println("작업 번호를 잘못 입력 했습니다.");
					System.out.println("다시 입력하세요.");
					
			}
		}while(choice!=0);
		
	}
	
	
	// 전체 회원 정보를 출력하는 메서드
	public void displayAllMember(){
		System.out.println();
		System.out.println("----------------------------------------");
		System.out.println("  ID    이 름     전화번호    주소");
		System.out.println("----------------------------------------");
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from mymember ";
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				System.out.println(memId + "   " + memName + "   "
						+ memTel + "   " + memAddr);
			}
			System.out.println("----------------------------------------");
			System.out.println("출력 작업 끝...");
			
		} catch (SQLException e) {
			System.out.println("자료를 가져오는데 실패했습니다.");
			e.printStackTrace();
		} finally{
			if(rs!=null)try{ rs.close(); }catch(SQLException e){}
			if(stmt!=null)try{ stmt.close(); }catch(SQLException e){}
			if(conn!=null)try{ conn.close(); }catch(SQLException e){}
		}
	}
	
	
	
	// 회원 정보를 수정하는 메서드
	public void updateMember(){
		try {
			System.out.println();
			System.out.println("수정할 회원 ID를 입력하세요.");
			System.out.print("회원 ID : ");
			String memId = scan.next();
			
			System.out.println();
			System.out.print("회원 이름을 변경할까요? (y/n) ");
			String ans = scan.next();
			
			String memName = null;
			if("Y".equals(ans) || "y".equals(ans)){
				System.out.print("새로운 회원 이름 : ");
				memName = scan.next();
			}
			
			System.out.println();
			System.out.print("전화 번호를 변경할까요? (y/n) ");
			ans = scan.next();
			
			String memTel = null;
			if("Y".equals(ans) || "y".equals(ans)){
				System.out.print("새로운 전화번호 : ");
				memTel = scan.next();
			}
			
			System.out.println();
			System.out.print("회원 주소를 변경할까요? (y/n) ");
			ans = scan.next();
			
			String memAddr = null;
			if("Y".equals(ans) || "y".equals(ans)){
				scan.nextLine();
				System.out.print("새로운 주소 : ");
				memAddr = scan.nextLine();
			}
			
			conn = DBUtil.getConnection();
			
//			String sql = "update mymember set mem_name=?, "
//					+ " mem_tel=?, mem_addr=? where mem_id=? ";
			
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
			
			String sql = "update mymember set " + temp + 
					" where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			int num = 1;
			if(memName!=null){
				pstmt.setString(num++, memName);
			}
			
			if(memTel!=null){
				pstmt.setString(num++, memTel);
			}
			
			if(memAddr!=null){
				pstmt.setString(num++, memAddr);
			}
			
			pstmt.setString(num, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0){
				System.out.println(memId + "회원 정보를 수정했습니다.");
			}else{
				System.out.println("회원 수정 작업 실패~~~");
			}
			
		} catch (SQLException e) {
			System.out.println("회원 수정 작업 실패~~~");
			e.printStackTrace();
		} finally{
			if(pstmt!=null)try{ pstmt.close(); }catch(SQLException e){}
			if(conn!=null)try{ conn.close(); }catch(SQLException e){}
		}
		
	}
	
	
	
	// 회원 정보를 삭제하는 메서드
	public void deleteMember(){
		try {
			System.out.println();
			System.out.println("삭제할 회원 정보를 입력하세요.");
			System.out.print("삭제할 회원 ID : ");
			String memId = scan.next();
			
			conn = DBUtil.getConnection();
			
			String sql = "delete from mymember where mem_id=? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0){
				System.out.println(memId + "회원 정보를 삭제했습니다.");
			}else{
				System.out.println("삭제 작업 실패~~");
			}
			
			
		} catch (SQLException e) {
			System.out.println("삭제 작업 실패~~");
			e.printStackTrace();
		} finally {
			if(pstmt!=null)try{ pstmt.close(); }catch(SQLException e){}
			if(conn!=null)try{ conn.close(); }catch(SQLException e){}
		}
	}
	
	
	
	
	// 회원 정보를 추가하는 메서드
	public void insertMember(){
		try {
			conn = DBUtil.getConnection();
			
			System.out.println();
			System.out.println("추가할 회원 정보를 입력하세요.");
			
			String memId = null;
			int count;
			do{
				System.out.print("회원 ID : " );
				memId = scan.next();
				
				String sql2 = "select count(*) cnt from mymember "
						+ " where mem_id=? ";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, memId);
				
				rs = pstmt2.executeQuery();
				count = 0;
				if(rs.next()){
					count = rs.getInt("cnt");
				}
				
				if(count>0){
					System.out.println(memId + "는 이미 등록된 ID입니다.");
					System.out.println("다른 ID로 다시 입력하세요.");
				}
				
			}while(count>0);
			
			System.out.print("회원 이름 : ");
			String memName = scan.next();
			
			System.out.print("회원 전화번호 : ");
			String memTel = scan.next();
			
			scan.nextLine();
			System.out.print("회원 주소 : ");
			String memAddr = scan.nextLine();
		
			String sql = "insert into mymember (mem_id, mem_name, "
					+ "mem_tel, mem_addr) values ( ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0){
				System.out.println(memId + "(" + memName + ")회원을 추가했습니다.");
			}else{
				System.out.println("추가 작업 실패~~");
			}
			
			
		} catch (SQLException e) {
			System.out.println("추가 작업 실패~~");
			e.printStackTrace();
		} finally{
			if(rs!=null)try{ rs.close(); }catch(SQLException e){}
			if(pstmt!=null)try{ pstmt.close(); }catch(SQLException e){}
			if(pstmt2!=null)try{ pstmt2.close(); }catch(SQLException e){}
			if(conn!=null)try{ conn.close(); }catch(SQLException e){}
		}
		
	}

	
	
	
	

}

















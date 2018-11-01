package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil3;
import mvc.vo.MemberVO;

public class MemberDao implements IMemberDao{
	
	/* 자기자신 참조값을 가질 변수 선언 */
	private static MemberDao dao = new MemberDao();
	
	/* 생성자를 private으로 설정 */
	private MemberDao() {}
	
	/* 자기자신 참조값을 반환하는 getInstance() */
	public static MemberDao getInstance(){
		if(dao == null){
			dao = new MemberDao();
		}
		return dao;
	}
	
	
	
	/* MemberVO객체에 담겨진 자료를 DB에 insert하는 메서드 */
	@Override
	public int insertMember(MemberVO memVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt = 0;
		
		try{
			conn = DBUtil3.getConnection();
			
			String sql = "insert into mymember "
					   + " (mem_id, mem_name, mem_tel, mem_addr) "
					   + " values(?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memVO.getMem_id());
			pstmt.setString(2, memVO.getMem_name());
			pstmt.setString(3, memVO.getMem_tel());
			pstmt.setString(4, memVO.getMem_addr());
			
			cnt = pstmt.executeUpdate();
			
		}catch(SQLException e){
			cnt = 0;
			e.printStackTrace();
		}finally{
			if(pstmt != null){try{pstmt.close();}catch(SQLException e){}}
			if(conn  != null){try{conn.close(); }catch(SQLException e){}}
		}
		return cnt;
	}

	/* 회원 ID를 매개변수로 받아서 해당 회원을 삭제하는 메서드 */
	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "delete from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}finally{
			if(pstmt != null){try{pstmt.close();}catch(SQLException e){}}
			if(conn  != null){try{conn.close(); }catch(SQLException e){}}
		}
		return cnt;
	}
	
	/*
	 * MemberVO객체에 담겨진 정보를 이용해 회원정보를
	 * update하는 메서드
	 */
	@Override
	public int updateMember(MemberVO memVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			String temp = "";
			if(memVO.getMem_name() != null){
				temp += "mem_name = ? ";
			}
			
			if(memVO.getMem_tel() != null){
				if(!"".equals(temp)){
					temp += ", ";
				}
				temp += "mem_tel = ? ";
			}
			
			if(memVO.getMem_addr() != null){
				if(!"".equals(temp)){
					temp += ", ";
				}
				temp += "mem_addr = ? ";
			}
			
			String sql = "update mymember set " + temp + 
					" where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			int num = 1;
			if(memVO.getMem_name() != null){
				pstmt.setString(num++, memVO.getMem_name());
			}
			
			if(memVO.getMem_tel() != null){
				pstmt.setString(num++, memVO.getMem_tel());
			}
			
			if(memVO.getMem_addr() != null){
				pstmt.setString(num++, memVO.getMem_addr());
			}
			
			pstmt.setString(num, memVO.getMem_id());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}finally{
			if(pstmt != null){try{pstmt.close();}catch(SQLException e){}}
			if(conn  != null){try{conn.close(); }catch(SQLException e){}}
		}
		return cnt;
	}
	
	/* 전체 회원 정보를 DB에서 가져와 List에 담아서 반환하는 메서드 */
	@Override
	public List<MemberVO> getMemberList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		/* 데이터를 저장할 List객체 생성 */
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "select * from mymember";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
				/* 각 레코드의 값을 MemberVO에 저장 */
				MemberVO memVO = new MemberVO();
				
				memVO.setMem_id(rs.getString("mem_id"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_tel(rs.getString("mem_tel"));
				memVO.setMem_addr(rs.getString("mem_addr"));
				
				/* 데이터가 담긴 MemberVO객체를 List에 저장 */
				memList.add(memVO);
			}
		} catch (Exception e) {
			memList = null;
			e.printStackTrace();
		}finally{
			if(rs != null)   {try{rs.close();}   catch(SQLException e){}}
			if(stmt != null) {try{stmt.close();} catch(SQLException e){}}
			if(conn  != null){try{conn.close(); }catch(SQLException e){}}
		}
		return memList;
	}

	/* 주어진 회원 ID가 있는지 여부를 나타내는 메서드 */
	@Override
	public int getMember(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "select count(*) cnt from mymember where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		}finally{
			if(rs != null)    {try{rs.close();}   catch(SQLException e){}}
			if(pstmt != null) {try{pstmt.close();}catch(SQLException e){}}
			if(conn  != null) {try{conn.close(); }catch(SQLException e){}}
		}
		return count;
	}
	
}

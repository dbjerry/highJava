package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import mvc.vo.MemberVO;
import util.DBUtil3;
import board.vo.BoardVO;

public class BoardDao implements IBoardDao {
	
	@Override
	public int insertBoard(BoardVO boardVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "insert into jdbc_board(board_no, board_title, board_writer, "
					   + " board_date, board_content) "
					   + " values(board_SQC.nextVal, ?, ?, sysdate, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_writer());
			pstmt.setString(3, boardVo.getBoard_content());
			
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

	@Override
	public int deleteBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "delete from jdbc_board where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
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

	@Override
	public int updateBoard(BoardVO boardVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			String temp = "";
			if(boardVo.getBoard_title() != null){
				temp += "board_title = ? ";
			}
			
			if(boardVo.getBoard_content() != null){
				if(!"".equals(temp)){
					temp += ", ";
				}
				temp += "board_content = ? ";
			}
			
			String sql = "update jdbc_board set " + temp + 
					" where board_no = ? ";
//			String sqldate = "update jdbc_board set board_date = sysdate";
			
			pstmt = conn.prepareStatement(sql);
//			pstmt = conn.prepareStatement(sqldate);
			
			int num = 1;
			if(boardVo.getBoard_title() != null){
				pstmt.setString(num++, boardVo.getBoard_title());
			}
			
			if(boardVo.getBoard_content() != null){
				pstmt.setString(num++, boardVo.getBoard_content());
			}
			
			pstmt.setInt(num, boardVo.getBoard_no());
			
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

	@Override
	public List<BoardVO> getBoardList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "select board_no, board_title, board_writer, "
					   + " to_char(board_date, 'yyyy-MM-dd') AS brd_date, board_content "
					   + " from JDBC_BOARD";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
				BoardVO boardVO = new BoardVO();
				
				boardVO.setBoard_no(rs.getInt("board_no"));
				boardVO.setBoard_writer(rs.getString("board_writer"));
				boardVO.setBoard_title(rs.getString("board_title"));
				boardVO.setBoard_date(rs.getString("brd_date"));
				
				boardList.add(boardVO);
			}
		} catch (Exception e) {
			boardList = null;
			e.printStackTrace();
		}finally{
			if(rs != null)   {try{rs.close();}   catch(SQLException e){}}
			if(stmt != null) {try{stmt.close();} catch(SQLException e){}}
			if(conn  != null){try{conn.close(); }catch(SQLException e){}}
		}
		return boardList;
	}

	@Override
	public BoardVO getBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BoardVO boardVo = new BoardVO();
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "select * from jdbc_board where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_content(rs.getString("board_content"));
				boardVo.setBoard_date(rs.getString("board_date"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null)    {try{rs.close();}   catch(SQLException e){}}
			if(pstmt != null) {try{pstmt.close();}catch(SQLException e){}}
			if(conn  != null) {try{conn.close(); }catch(SQLException e){}}
		}
		return boardVo;
	}
}

package board.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import board.vo.BoardVO;

public class BoardDao implements IBoardDao {
	
	/**
	 * "Singleton Pattern"
	 * 자기 자신 참조값을 가질 변수 선언
	 */
	private static BoardDao dao = new BoardDao();
	
	private SqlMapClient smc;
	
	/**
	 * "Singleton Pattern"
	 * 	- 생성자를 private으로 설정.
	 * 
	 * "ibatis"
	 * 	- Dao생성자에 ibatis 실행용 객체를 생성해준다.
	 */
	private BoardDao(){
		try {
			Reader rd = Resources.getResourceAsReader("sqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * "Singleton Pattern"
	 * 외부에서 new를 사용하지 않고 동일한 객체를 반환하는 패턴
	 * getInstance()를 만들어 생성자를 대체(public static으로 생성)
	 * @return (자기 자신)참조값을 반환
	 */
	public static BoardDao getInstance(){
		if(dao == null){
			dao = new BoardDao();
		}
		return dao;
	}
	
	@Override
	public Object insertBoard(BoardVO boardVo) {
		Object obj = "";
		
		try {
			obj = smc.insert("board.insertBoard", boardVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0;
		
		try {
			cnt = smc.delete("board.deleteBoard", boardNo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		int cnt = 0;
		try {
			cnt = smc.update("board.updateBoard", boardVo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<BoardVO> getBoardList() {
		List<BoardVO> boardList = null;
		
		try {
			boardList = smc.queryForList("board.selectAllBoard");
		} catch (Exception e) {
			boardList = null;
			e.printStackTrace();
		}
		return boardList;
	}

	@Override
	public BoardVO getBoard(int boardNo) {
		BoardVO boardVo = null;
		
		try {
			boardVo = (BoardVO)smc.queryForObject("board.getBoard", boardNo);
		}catch (SQLException e){
			boardVo = null;
			e.printStackTrace();
		}
		return boardVo;
	}
	
//	// 주어진 파라미터 값들을 이용하여 회원을 검색하는 메서드
//	@Override
//	public List<BoardVO> searchBoard(String col, String value, String op) {
//		// 검색 결과를 저장할 List형 객체 생성
//		List<BoardVO> boardList = null;
////		try {
////		} catch (SQLException e) {
////			memList = null;
////			e.printStackTrace();
////		} 
//		return boardList;
//	}
//	
//	// 주어진 Map형 파라미터 값을 이용하여 회원을 검색하는 메서드2
//	@Override
//	public List<BoardVO> searchBoard(Map<String, String> param) {
//		// 검색 결과를 저장할 List형 객체 생성
//		List<BoardVO> boardList = null;
//
//		try {
//			boardList = smc.queryForList("board.getFindboard", param);
//		} catch (SQLException e) {
//			boardList = null;
//			e.printStackTrace();
//		} 
//		return boardList;
//	}
}


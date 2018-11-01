package board.dao;

import java.util.List;
import java.util.Map;

import mvc.vo.MemberVO;
import board.vo.BoardVO;

public interface IBoardDao {
	/**
	 * 새 글을 저장하는 메서드
	 * @param boardVo 저장할 새 글 정보가 들어있는 BoardVO객체
	 * @return 성공하면 1, 실패하면 0
	 */
	public Object insertBoard(BoardVO boardVo);
	
	/**
	 * 주어진 글번호로 해당 게시글을 삭제하는 메서드
	 * @param 삭제할 글번호
	 * @return 성공하면 1, 실패하면 0
	 */
	public int deleteBoard(int boardNo);
	
	/**
	 * BoardVO객체에 담긴 정보로 수정하는 메서드
	 * @param 수정할 정보가 담긴 BoardVO객체
	 * @return 성공하면 1, 실패하면 0
	 */
	public int updateBoard(BoardVO boardVo);
	
	/**
	 * 게시글 전체를 가져오는 메서드
	 * @return 게시글 정보를 담고 있는 BoardVO객체를 List에 담아서 반환
	 */
	public List<BoardVO> getBoardList();
	
	/**
	 * 게시글의 상세정보를 가져오는 메서드
	 * @param boardVo 가져올 board_no값
	 * @return 검색된 BoardVO객체
	 */
	public BoardVO getBoard(int boardNo);
	
//	/**
//	 * 주어진 파라미터에 맞게 자료를 검색하는 메서드
//	 * @param col 검색할 컬럼명이 저장되는 변수
//	 * @param value 검색할 단어가 저장되는 변수
//	 * @param op 검색할 연산자가 저장되는 변수
//	 * @return 검색결과를 List에 담아서 반환한다.
//	 */
//	public List<BoardVO> searchBoard(String col, String value, String op);
//	
//	/**
//	 * 주어진 파라미터에 맞게 자료를 검색하는 메서드 2
//	 * @param param 검색할 컬럼명, 검색할 단어, 연산자를 저장하는 Map형 변수
//	 * @return 검색결과를 List에 담아서 반환한다.
//	 */
//	public List<BoardVO> searchBoard(Map<String, String> param);
	
}

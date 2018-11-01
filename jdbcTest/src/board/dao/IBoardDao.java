package board.dao;

import java.util.List;

import board.vo.BoardVO;

public interface IBoardDao {
	/**
	 * 새 글을 저장하는 메서드
	 * @param boardVo 저장할 새 글 정보가 들어있는 BoardVO객체
	 * @return 성공하면 1, 실패하면 0
	 */
	public int insertBoard(BoardVO boardVo);
	
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
	
}

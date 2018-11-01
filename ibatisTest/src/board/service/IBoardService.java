package board.service;

import java.util.List;

import board.vo.BoardVO;

public interface IBoardService {

	public Object insertBoard(BoardVO boardVo);
	
	public int deleteBoard(int boardNo);
	
	public int updateBoard(BoardVO boardVo);
	
	public List<BoardVO> getBoardList();
	
	public BoardVO getBoard(int boardNo);
	
}

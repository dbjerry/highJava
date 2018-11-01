package board.service;

import java.util.List;

import board.dao.BoardDao;
import board.dao.IBoardDao;
import board.vo.BoardVO;

public class BoardService implements IBoardService{

	IBoardDao boardDao;
	
	public BoardService(){
		boardDao = new BoardDao();
	}
	
	@Override
	public int insertBoard(BoardVO boardVo) {
		return boardDao.insertBoard(boardVo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return boardDao.deleteBoard(boardNo);
	}
	
	@Override
	public int updateBoard(BoardVO boardVo) {
		return boardDao.updateBoard(boardVo);
	}
	
	@Override
	public List<BoardVO> getBoardList() {
		return boardDao.getBoardList();
	}
	
	@Override
	public BoardVO getBoard(int boardNo) {
		return boardDao.getBoard(boardNo);
	}
	
}

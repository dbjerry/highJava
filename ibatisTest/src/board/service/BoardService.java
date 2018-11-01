package board.service;

import java.util.List;

import sun.security.jca.GetInstance;
import board.dao.BoardDao;
import board.dao.IBoardDao;
import board.vo.BoardVO;

public class BoardService implements IBoardService{
	IBoardDao boardDao;
	
	/* Singleton : 자기자신 참조값을 가질 변수 선언 */
	private static BoardService service = new BoardService();
	
	/* Singleton : 생성자를 private으로 설정 */
	private BoardService(){
		boardDao = BoardDao.getInstance();
	}
	
	/* Singleton : 참조값을 반환하는 getInstance() 작성 */
	public static BoardService getInstance(){
		if(service == null){
			service = new BoardService();
		}
		return service;
	}
	
	@Override
	public Object insertBoard(BoardVO boardVo) {
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

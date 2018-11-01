package board.vo;

public class BoardVO {
	
	/**
	 * Board테이블의 컬럼명을 멤버변수로 저장
	 */
	private int board_no;
	private String board_writer;
	private String board_title;
	private String board_date;	//	String으로 저장
	private String board_content;

	/**
	 * 각 변수별 getter, setter
	 */
	public int getBoard_no() {
		return board_no;
	}
	
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	
	public String getBoard_writer() {
		return board_writer;
	}
	
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	
	public String getBoard_title() {
		return board_title;
	}
	
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	
	public String getBoard_date() {
		return board_date;
	}
	
	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	
	public String getBoard_content() {
		return board_content;
	}
	
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	
}

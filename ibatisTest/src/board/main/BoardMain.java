package board.main;

import java.util.List;
import java.util.Scanner;

import mvc.vo.MemberVO;
import board.service.BoardService;
import board.service.IBoardService;
import board.vo.BoardVO;

/**
 * 다음 실행 예시에 맞는 게시판 프로그램을 작성하시오.
 * (만약 게시글이 하나도 없으면 '게시글 하나도 없습니다.'
 *  메시지가 출력되도록 한다.)
 *  
 * 추가시 '글번호'는 시퀀스를 이용하여 자동 증가 되도록한다.
 * 추가, 수정 작업시 날짜는 sysdate로 한다.
 * 수정 작업에서 변경 가능한 항목은 '제목'과 '내용'만 가능하다.
 * 이 프로그램은 MVC패턴으로 작성하시오.
 * 
 * 실행예시)
 * -----------------------------------------
 * 번호		작성자		제목			날짜
 * -----------------------------------------
 * 1		홍길동	질문입니다.	2018-08-07
 * 
 * -----------------------------------------
 * 1.새글작성	2.글보기	3.글수정	4.글삭제	5.종료
 * 
 * 작업선택 >> 1
 * 
 * 새로운 게시글 내용을 입력하세요.
 * 작성자 >> 아무개
 * 제   목 >> 답변입니다.
 * 내   용 >> 질문에 대한 답변입니다.
 * 
 * 새 글이 추가되었습니다.
 * 
 *  * 실행예시
 * -----------------------------------------
 * 번호		작성자		제목			날짜
 * -----------------------------------------
 * 1		홍길동	질문입니다.	2018-08-07
 * 
 * -----------------------------------------
 * 1.새글작성	2.글보기	3.글수정	4.글삭제	5.종료
 * 
 * 작업선택 >> 2
 * 
 * 보기를 원하는 글번호 입력 >> 1
 * 
 * 번   호 : 1
 * 작성자 : 홍길동
 * 제   목 : 질문입니다.
 * 내   용 : JDBC 질문입니다.
 * 날   짜 : 2018-08-07
 */
public class BoardMain {
	Scanner scan = new Scanner(System.in);
	private IBoardService service;
	
	public BoardMain(){
		service = BoardService.getInstance();
	}
	
	public static void main(String[] args) {
		new BoardMain().start();
	}

	private int displayMenu() {
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("  No.     Writer      Title         Date");
		System.out.println("----------------------------------------------");
		
		List<BoardVO> boardList = null;
		
		boardList = service.getBoardList();
		
		for(BoardVO boardVo : boardList){
			if(boardVo.getBoard_writer().equals(null)){
				System.out.println("게시글이 없습니다.");
			}
			int boardNo = boardVo.getBoard_no();
			String boardWriter = boardVo.getBoard_writer();
			String boardTitle = boardVo.getBoard_title();
			String boardDate = boardVo.getBoard_date();
			
			System.out.println("   " + boardNo + "\t" + boardWriter +
					"\t" + boardTitle + "\t" + boardDate);
		}
		System.out.println("----------------------------------------------");
		System.out.println("1.새글작성     2.글보기     3.글수정     4.글삭제     5.종료\n");
		System.out.print("작업 선택 >> ");
		int num = scan.nextInt();
		return num;
	}
	
	private void start() {
		int choice;
		
		do{
			choice = displayMenu();
			switch(choice){
				case 1 :  		// 새 글 작성 
					insertBoard();
					break;
				case 2 : 		// 글보기
					lookBoard();
					break;
				case 3 : 		// 글 수정
					updateBoard();
					break;
				case 4 : 		// 글 삭제
					deleteBoard();
					break;
				case 5 : 		// 종료.
					System.out.println("\n\n작업을 종료합니다.\n\n");
					return;
				default : 
					System.out.println("잘못 입력되었습니다. 다시 입력해주세요.");
					
			}
		}while(choice!=0);
		
	}

	private void insertBoard() {
		System.out.println();
		System.out.println("\n=== 1. 새 글 작성 ===");
		System.out.println("새로운 게시글 내용을 입력하세요.\n\n");
		
		System.out.print("작성자 >> ");
		String boardWriter = scan.next();
		System.out.print("제   목 >> ");
		scan.nextLine();
		String boardTitle = scan.nextLine();
		System.out.print("내   용 >> ");
		String boardContent = scan.nextLine();

		BoardVO boardVo = new BoardVO();
		boardVo.setBoard_writer(boardWriter);
		boardVo.setBoard_title(boardTitle);
		boardVo.setBoard_content(boardContent);
		
		Object obj = service.insertBoard(boardVo);
		
		if(obj == null){
			System.out.println("\n=== 글작성 완료. ===\n");
		}else{
			System.out.println("\n=== 글작성 실패. ===\n");
		}
		
	}

	private void lookBoard() {
		System.out.println();
		System.out.println("\n=== 2. 글보기 ===");
		System.out.println("열람할 게시글 정보를 입력하세요.");

		System.out.print("열람할 게시글 번호 >> ");
		int boardNo = scan.nextInt();
		
		BoardVO boardVo = service.getBoard(boardNo);
		
		System.out.println();
		System.out.println("번   호 : " + boardVo.getBoard_no());
		System.out.println("제   목 : " + boardVo.getBoard_title());
		System.out.println("작성자 : " + boardVo.getBoard_writer());
		System.out.println("내   용 : " + boardVo.getBoard_content());
		System.out.println("날   짜 : " + boardVo.getBoard_date());
		System.out.println();
	}

	private void updateBoard() {
		System.out.println();
		System.out.println("\n=== 3. 글 수정 ===");
		System.out.println("수정할 게시글의 정보를 입력하세요.");
		System.out.print("글 번호 >> ");
		int boardNo = scan.nextInt();
		
		System.out.println();
		System.out.print("게시글의 제목을 변경할까요? (y/n) ");
		String ans = scan.next();
		
		String boardTitle = null;
		if("Y".equals(ans) || "y".equals(ans)){
			System.out.print("새로운 글 제목 >> ");
			scan.nextLine();
			boardTitle = scan.nextLine();
		}
		
		System.out.println();
		System.out.print("게시글 내용을 변경할까요? (y/n) ");
		ans = scan.next();
		
		String boardContent = null;
		if("Y".equals(ans) || "y".equals(ans)){
			System.out.print("새로운 글 내용 >> ");
			scan.nextLine();
			boardContent = scan.nextLine();
		}
		System.out.println("어디까지왔니?");
		if(boardTitle == null && boardContent == null){
			System.out.println("\n수정할 항목이 없습니다.");
			return;
		}
		System.out.println("야야야야야");
		BoardVO boardVo = new BoardVO();
		boardVo.setBoard_no(boardNo);
		boardVo.setBoard_title(boardTitle);
		boardVo.setBoard_content(boardContent);
		
		System.out.println("hhhhh");
		int cnt = 0;
		cnt = service.updateBoard(boardVo);
		System.out.println(cnt);
		if(cnt>0){
			System.out.println("\n=== 수정 완료 ===\n");
		}else{
			System.out.println("\n=== 수정 실패 ===\n");
		}
	}
	
	private void deleteBoard() {
		System.out.println();
		System.out.println("\n=== 4. 글삭제 ===");
		System.out.println("삭제할 게시글 정보를 입력하세요.");
		System.out.print("삭제할 게시글 번호 >> ");
		int boardNo = scan.nextInt();
		
		int cnt = service.deleteBoard(boardNo);
		
		if(cnt>0){
			System.out.println(boardNo + " delete success.");
		}else{
			System.out.println("\n=== delete losing ===\n");
		}
		
	}

}

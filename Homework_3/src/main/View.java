package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import buyprod.service.BuyProdServiceImpl;
import prod.service.ProdServiceImpl;
import vo.BuyProdVO;
import vo.MemberVO;
import vo.ProdVO;
import member.service.MemberServiceImpl;

public class View {
	private MemberServiceImpl memService;
	private ProdServiceImpl prodService;
	private BuyProdServiceImpl bprodService;
	
	private Scanner sc;
	
	public View() {
		memService = MemberServiceImpl.getInstance();
		prodService = ProdServiceImpl.getInstance();
		bprodService = BuyProdServiceImpl.getInstance();
		
		sc = new Scanner(System.in);
	}
	
	/**
	 * 메인 메뉴
	 */
	public void start() {
		while (true) {
			System.out.println();
			System.out.println("──────────────────────────────");
			System.out.println("　　　1. 회원 관리");
			System.out.println("　　　2. 물품 관리");
			System.out.println("　　　3. 입고 관리");
			System.out.println("　　　4. 종료");
			System.out.println("──────────────────────────────");
			System.out.print("　번호 입력 : ");
			
			int selectNum = inputNum();
			
			switch (selectNum) {
			case 1: // 회원관리
				memberMain();
				break;
			case 2: // 물품관리
				prodMain();
				break;
			case 3: // 입고관리
				buyprodMain();
				break;
			case 4: // 종료
				System.out.println("──────────────────────────────");
				System.out.println("　프로그램 종료");
				return;
			default:
				System.out.println("　ERROR) 1~4번을 선택할 수 있습니다.");
			}
		}
	}
	
	/**
	 * 회원 관리 메뉴
	 */
	private void memberMain() {
		while (true) {
			System.out.println("──────────────────────────────");
			System.out.println("　　　1. 회원 가입");
			System.out.println("　　　2. 회원 전체 조회");
			System.out.println("　　　3. 회원 정보 수정");
			System.out.println("　　　4. 회원 검색");
			System.out.println("　　　5. 회원 탈퇴");
			System.out.println("　　　6. PDF로 저장");
			System.out.println("　　　7. 뒤로 가기");
			System.out.println("──────────────────────────────");
			System.out.print("　번호 입력 : ");
			
			int selectNum = inputNum();
			
			switch (selectNum) {
			case 1: // 회원 가입
				memberJoin();
				break;
			case 2: // 회원 전체 조회
				memberPrint();
				break;
			case 3: // 회원 정보 수정
				memberEdit();
				break;
			case 4: // 회원 검색
				memberSearch();
				break;
			case 5: // 회원 탈퇴
				memberLeave();
				break;
			case 6: // PDF로 저장
				memberSave();
				break;
			case 7: // 뒤로 가기
				start();
				break;
			default:
				System.out.println("　ERROR) 1~6번을 선택할 수 있습니다.");
			}
		}
	}
	
	/**
	 * 회원 가입 : 회원 정보를 추가하는 메서드
	 */
	private void memberJoin() {
		System.out.println();
		System.out.println("　추가할 회원의 정보를 입력하세요.");
		String mem_id = "";
		boolean chk = false;
		
		do {
			System.out.print("　회원 ID : ");
			mem_id = inputString();
			
			chk = memService.checkMem(mem_id);
			
			if(chk == true){
				System.out.println(mem_id + "은 이미 존재합니다.");
			}
		} while (chk == true);
		
		System.out.println();
		
		System.out.print("　회원 PW : ");
		String mem_pass = inputString();
		
		System.out.print("　회원명 : ");
		String mem_name = inputString();

		System.out.print("　주민번호 앞자리 : ");
		String mem_regno1 = inputString();
		
		System.out.print("　주민번호 뒷자리 : ");
		String mem_regno2 = inputString();
		
//		System.out.print("　생일 : ");
//		String mem_bir = inputString();
		
		System.out.print("　우편번호 : ");
		String mem_zip = inputString();
		
		sc.nextLine(); // 입력 버퍼 비우기
		System.out.print("　주소 : ");
		String mem_add1 = sc.nextLine();
		
		System.out.print("　상세 주소 : ");
		String mem_add2 = inputString();
		
		System.out.print("　전화번호 : ");
		String mem_hometel = inputString();
		
		System.out.print("　직장전화 : ");
		String mem_comtel = inputString();
		
//		System.out.print("　핸드폰 : ");
//		String mem_hp = inputString();
		
		System.out.print("　메일주소 : ");
		String mem_mail = inputString();
		
//		System.out.print("　직업 : ");
//		String mem_job = inputString();
//		
//		System.out.print("　취미 : ");
//		String mem_like = inputString();
//		
//		System.out.print("　기념일 : ");
//		String mem_memorial = inputString();
//		
//		System.out.print("　기념일날짜 : ");
//		String mem_memorialDay = inputString();
//		
//		System.out.print("　마일리지 : ");
//		String mem_mileage = inputString();
//		
//		System.out.print("　탈퇴구분 : ");
//		String mem_delete = inputString();
		
		// 입력받은 정보를 VO객체에 넣는다.
		MemberVO mv = new MemberVO();
		mv.setMem_id(mem_id);
		mv.setMem_pass(mem_pass);//비밀번호
		mv.setMem_name(mem_name);//이름
		mv.setMem_regno1(mem_regno1);
		mv.setMem_regno2(mem_regno2);
//		mv.setMem_bir(mem_bir);
		mv.setMem_zip(mem_zip);
		mv.setMem_add1(mem_add1);
		mv.setMem_add2(mem_add2);
		mv.setMem_hometel(mem_hometel);
		mv.setMem_comtel(mem_comtel);
//		mv.setMem_hp(mem_hp);
		mv.setMem_mail(mem_mail);
//		mv.setMem_job(mem_job);
//		mv.setMem_like(mem_like);
//		mv.setMem_memorial(mem_memorial);
//		mv.setMem_memorialday(mem_memorialDay);
//		mv.setMem_mileage(mem_mileage);
//		mv.setMem_delete(mem_delete);
		
		int cnt = memService.insertMem(mv);
		
		if (cnt > 0) {
			System.out.println(mem_id + "회원 추가 성공!!");
		} else {
			System.out.println(mem_id + "회원 추가 실패!!");
		}
	}
	
	/**
	 * 회원 전체 조회 : 회원 정보를 전체 출력하는 메서드
	 */
	private void memberPrint() {
		System.out.println();
		System.out.println("──────────────────────────────");
		
		List<MemberVO> memList = memService.selectMemAll();
		if (memList.size() == 0) {
			System.out.println("　정보가 존재하지 않습니다.");
		} else {
			for (MemberVO mvo : memList) {
				System.out.println("ID : " + mvo.getMem_id());
				System.out.println("PW : " + mvo.getMem_pass());
				System.out.println("이름 : " + mvo.getMem_name());
				System.out.println("주민번호 : " + mvo.getMem_regno1() + "-" + mvo.getMem_regno2());
//				System.out.println("생일 : " + mvo.getMem_bir());
				System.out.println("우편번호 : " + mvo.getMem_zip());
				System.out.println("주소 : " + mvo.getMem_add1());
				System.out.println("상세주소 : " + mvo.getMem_add2());
				System.out.println("전화번호 : " + mvo.getMem_hometel());
				System.out.println("직장전화 : " + mvo.getMem_comtel());
//				System.out.println("핸드폰 : " + mvo.getMem_hp());
				System.out.println("메일주소 : " + mvo.getMem_mail());
//				System.out.println("직업 : " + mvo.getMem_job());
//				System.out.println("취미 : " + mvo.getMem_like());
//				System.out.println("기념일 : " + mvo.getMem_memorial());
//				System.out.println("기념일날짜 : " + mvo.getMem_memorialday());
//				System.out.println("마일리지 : " + mvo.getMem_mileage());
//				System.out.println("탈퇴구분 : " + mvo.getMem_delete());
				System.out.println("──────────────────────────────");
			}
		}
		
		System.out.println("　조회 완료.");
	}
	
	/**
	 * 회원 정보 수정 : 회원 정보를 수정하는 메서드
	 */
	private void memberEdit() {
		System.out.println();
		String mem_id = "";
		boolean chk = true;
		
		do {
			System.out.print("　수정할 회원 ID : ");
			mem_id = inputString();
			
			chk = memService.checkMem(mem_id);
			
			if(chk == false){
				System.out.println(mem_id + "회원은 존재하지 않습니다.");
			}
		} while (chk == false);
		
		System.out.print("　PW : ");
		String mem_pass = inputString();
		
		System.out.print("　이름 : ");
		String mem_name = inputString();

		System.out.print("　우편번호 : ");
		String mem_zip = inputString();

		System.out.print("　주소 : ");
		String mem_add1 = inputString();

		System.out.print("　상세주소 : ");
		String mem_add2 = inputString();
		
		System.out.print("　전화번호 : ");
		String mem_hometel = inputString();
		
		System.out.print("　직장전화 : ");
		String mem_comtel = inputString();
		
//		System.out.print("　핸드폰 : ");
//		String mem_hp = inputString();
		
		System.out.print("　메일주소 : ");
		String mem_mail = inputString();
		
//		System.out.print("　직업 : ");
//		String mem_job = inputString();
//		
//		System.out.print("　취미 : ");
//		String mem_like = inputString();
//		
//		System.out.print("　기념일 : ");
//		String mem_memorial = inputString();
//		
//		System.out.print("　기념일날짜 : ");
//		String mem_memorialDay = inputString();
//		
//		System.out.print("　마일리지 : ");
//		String mem_mileage = inputString();
//		
//		System.out.print("　탈퇴구분 : ");
//		String mem_delete = inputString();
		
		MemberVO mv = new MemberVO();
		mv.setMem_id(mem_id);
		mv.setMem_pass(mem_pass);
		mv.setMem_name(mem_name);
		mv.setMem_zip(mem_zip);
		mv.setMem_add1(mem_add1);
		mv.setMem_add2(mem_add2);
		mv.setMem_hometel(mem_hometel);
		mv.setMem_comtel(mem_comtel);
//		mv.setMem_hp(mem_hp);
		mv.setMem_mail(mem_mail);
//		mv.setMem_job(mem_job);
//		mv.setMem_like(mem_like);
//		mv.setMem_memorial(mem_memorial);
//		mv.setMem_memorialday(mem_memorialDay);
//		mv.setMem_mileage(mem_mileage);
//		mv.setMem_delete(mem_delete);
		
		int cnt = memService.updateMem(mv);
		
		if (cnt > 0) {
			System.out.println(mem_id + "회원 정보 수정 성공!!");
		} else {
			System.out.println(mem_id + "회원 정보 수정 실패!!");
		}
	}
	
	/**
	 * 회원 검색
	 */
	private void memberSearch() {
		sc.nextLine(); // 입력 버퍼 지우기
		
		System.out.println();
		System.out.println("　검색할 정보를 입력하세요.");
		System.out.print("　ID : ");
		String mem_id = sc.nextLine().trim();
		
		System.out.print("　회원명 : ");
		String mem_name = sc.nextLine().trim();
		
		MemberVO mv = new MemberVO();
		mv.setMem_id(mem_id);
		mv.setMem_name(mem_name);
		
		List<MemberVO> memList = memService.selectMem(mv);
		
		System.out.println();
		System.out.println("──────────────────────────────");
		
		if(memList==null || memList.size()==0){
			System.out.println("검색한 자료가 하나도 없습니다.");
		} else {
			for (MemberVO mvo : memList) {
				System.out.println("ID : " + mvo.getMem_id());
				System.out.println("PW : " + mvo.getMem_pass());
				System.out.println("이름 : " + mvo.getMem_name());
				System.out.println("주민번호 : " + mvo.getMem_regno1() + "-" + mvo.getMem_regno2());
				System.out.println("우편번호 : " + mvo.getMem_zip());
				System.out.println("주소 : " + mvo.getMem_add1());
				System.out.println("상세주소 : " + mvo.getMem_add2());
				System.out.println("전화번호 : " + mvo.getMem_hometel());
				System.out.println("직장전화 : " + mvo.getMem_comtel());
				System.out.println("메일주소 : " + mvo.getMem_mail());
				System.out.println("──────────────────────────────");
			}
		}
		
		System.out.println("　검색 완료.");
	}
	
	/**
	 * 회원 탈퇴 : 회원 정보를 삭제하는 메서드
	 */
	private void memberLeave() {
		System.out.println();
		System.out.print("　삭제할 회원 ID : ");
		
		String mem_id = inputString();
		
		int cnt = memService.deleteMem(mem_id);
		
		if (cnt > 0) {
			System.out.println(mem_id + "회원 삭제 성공!!");
		} else {
			System.out.println(mem_id + "회원 삭제 실패!!");
		}
	}
	
	/**
	 * PDF로 저장 : 회원 정보를 PDF 파일로 출력
	 */
	private void memberSave() {
		List<MemberVO> memList = memService.selectMemAll();

		System.out.println();
		
		// 문서 생성 및 페이지 추가
		try (final PDDocument document = new PDDocument()) {
			final PDPage page = new PDPage();
			document.addPage(page);
			
			// Font 설정
			InputStream fontStream = new FileInputStream("c:/Windows/Fonts/malgun.TTF");
			PDType0Font font = PDType0Font.load(document, fontStream);
			
			// 컨텐츠 시작
			final PDPageContentStream content = new PDPageContentStream(document, page);
			
			content.beginText();
			
			content.setFont(font, 12);
			content.newLineAtOffset(40, 750);
			content.setLeading(15f);
			
			content.showText("──────────────────────────────");
			content.newLine();
			
			if (memList.size() == 0) {
				content.showText("　정보가 존재하지 않습니다.");
				content.newLine();
				content.showText("──────────────────────────────");
			} else {
				for (MemberVO mvo : memList) {
					content.showText("ID : " + mvo.getMem_id());
					content.newLine();
					content.showText("PW : " + mvo.getMem_pass());
					content.newLine();
					content.showText("이름 : " + mvo.getMem_name());
					content.newLine();
					content.showText("주민번호 : " + mvo.getMem_regno1() + "-" + mvo.getMem_regno2());
					content.newLine();
					content.showText("우편번호 : " + mvo.getMem_zip());
					content.newLine();
					content.showText("주소 : " + mvo.getMem_add1());
					content.newLine();
					content.showText("상세주소 : " + mvo.getMem_add2());
					content.newLine();
					content.showText("전화번호 : " + mvo.getMem_hometel());
					content.newLine();
					content.showText("직장전화 : " + mvo.getMem_comtel());
					content.newLine();
					content.showText("메일주소 : " + mvo.getMem_mail());
					content.newLine();
					content.showText("──────────────────────────────");
				}
			}
			
			content.endText();
			content.close();
			
			document.save("MemberInfo.pdf");
			
			System.out.println("저장 완료.");
			
		} catch (IOException e) {
			System.out.println("저장 실패!!");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 물품 관리 메뉴
	 */
	private void prodMain() {
		while (true) {
			System.out.println("──────────────────────────────");
			System.out.println("　　　1. 물품 등록");
			System.out.println("　　　2. 물품 전체 조회");
			System.out.println("　　　3. 물품 수정");
			System.out.println("　　　4. 물품 검색");
			System.out.println("　　　5. 물품 삭제");
			System.out.println("　　　6. 뒤로 가기");
			System.out.println("──────────────────────────────");
			System.out.print("　번호 입력 : ");
			
			int selectNum = inputNum();
			
			switch (selectNum) {
			case 1: // 물품 등록
				prodInsert();
				break;
			case 2: // 물품 전체 조회
				prodPrint();
				break;
			case 3: // 물품 수정
				prodEdit();
				break;
			case 4: // 물품 검색
				prodSearch();
				break;
			case 5: // 물품 삭제
				prodDelete();
				break;
			case 6: // 뒤로 가기
				start();
				break;
			default:
				System.out.println("　ERROR) 1~6번을 선택할 수 있습니다.");
			}
		}
	}
	
	/**
	 * 물품 등록 : 물품 정보를 추가하는 메서드
	 */
	private void prodInsert() {
		System.out.println();
		System.out.println("　추가할 물품의 정보를 입력하세요.");
		String prod_id = "";
		boolean chk = false;
		
		do {
			System.out.print("　물품 ID : ");
			prod_id = inputString();
			
			chk = prodService.checkProd(prod_id);
			
			if(chk == true){
				System.out.println(prod_id + "은 이미 존재합니다.");
			}
		} while (chk == true);
		
		sc.nextLine(); // 입력 버퍼 비우기
		System.out.print("　물품명 : ");
		String prod_name = sc.nextLine();

		System.out.print("　물품분류 ID : ");
		String prod_lgu = inputString();
		
		System.out.print("　거래처 ID : ");
		String prod_buyer = inputString();
		
		System.out.print("　구매단가 : ");
		String prod_cost = inputString();
		
		System.out.print("　소비자가 : ");
		String prod_price = inputString();
		
		System.out.print("　판매가 : ");
		String prod_sale = inputString();
		
		System.out.print("　OUTLINE : ");
		String prod_outline = inputString();
		
		System.out.print("　이미지 : ");
		String prod_img = inputString();
		
		System.out.print("　TOTAL : ");
		String prod_totalstock = inputString();
		
		System.out.print("　PROPER : ");
		String prod_properstock = inputString();
		
		// 입력받은 정보를 VO객체에 넣는다.
		ProdVO pv = new ProdVO();
		pv.setProd_id(prod_id);
		pv.setProd_name(prod_name);
		pv.setProd_lgu(prod_lgu);
		pv.setProd_buyer(prod_buyer);
		pv.setProd_cost(prod_cost);
		pv.setProd_price(prod_price);
		pv.setProd_sale(prod_sale);
		pv.setProd_outline(prod_outline);
		pv.setProd_img(prod_img);
		pv.setProd_totalstock(prod_totalstock);
		pv.setProd_properstock(prod_properstock);
		
		int cnt = prodService.insertProd(pv);
		
		if (cnt > 0) {
			System.out.println(prod_id + "회원 추가 성공!!");
		} else {
			System.out.println(prod_id + "회원 추가 실패!!");
		}
	}
	
	/**
	 * 물품 전체 조회 : 물품 정보를 전체 출력하는 메서드
	 */
	private void prodPrint() {
		System.out.println();
		System.out.println("──────────────────────────────");
		
		List<ProdVO> proList = prodService.selectProdAll();
		if (proList.size() == 0) {
			System.out.println("　정보가 존재하지 않습니다.");
		} else {
			for (ProdVO pvo : proList) {
				System.out.println("물품 ID : " + pvo.getProd_id());
				System.out.println("물품명 : " + pvo.getProd_name());
				System.out.println("물품분류 ID : " + pvo.getProd_lgu());
				System.out.println("거래처 ID : " + pvo.getProd_buyer());
				System.out.println("구매단가 : " + pvo.getProd_cost());
				System.out.println("소비자가 : " + pvo.getProd_price());
				System.out.println("판매가 : " + pvo.getProd_sale());
				System.out.println("OUTLINE : " + pvo.getProd_outline());
				System.out.println("이미지 : " + pvo.getProd_img());
				System.out.println("TOTAL : " + pvo.getProd_totalstock());
				System.out.println("PROPER : " + pvo.getProd_properstock());
				System.out.println("──────────────────────────────");
			}
		}
		
		System.out.println("　조회 완료.");
	}
	
	/**
	 * 물품 정보 수정 : 물품 정보를 수정하는 메서드
	 */
	private void prodEdit() {
		System.out.println();
		String prod_id = "";
		boolean chk = true;
		
		do {
			System.out.print("　수정할 물품 ID : ");
			prod_id = inputString();
			
			chk = prodService.checkProd(prod_id);
			
			if(chk == false){
				System.out.println(prod_id + "는 존재하지 않습니다.");
			}
		} while (chk == false);
		
		sc.nextLine(); // 입력 버퍼 비우기
		System.out.print("　물품명 : ");
		String prod_name = sc.nextLine();

		System.out.print("　물품분류 ID : ");
		String prod_lgu = inputString();
		
		System.out.print("　거래처 ID : ");
		String prod_buyer = inputString();
		
		System.out.print("　구매단가 : ");
		String prod_cost = inputString();
		
		System.out.print("　소비자가 : ");
		String prod_price = inputString();
		
		System.out.print("　판매가 : ");
		String prod_sale = inputString();
		
		System.out.print("　OUTLINE : ");
		String prod_outline = inputString();
		
		System.out.print("　이미지 : ");
		String prod_img = inputString();
		
		System.out.print("　TOTAL : ");
		String prod_totalstock = inputString();
		
		System.out.print("　PROPER : ");
		String prod_properstock = inputString();
		
		// 입력받은 정보를 VO객체에 넣는다.
		ProdVO pv = new ProdVO();
		pv.setProd_id(prod_id);
		pv.setProd_name(prod_name);
		pv.setProd_lgu(prod_lgu);
		pv.setProd_buyer(prod_buyer);
		pv.setProd_cost(prod_cost);
		pv.setProd_price(prod_price);
		pv.setProd_sale(prod_sale);
		pv.setProd_outline(prod_outline);
		pv.setProd_img(prod_img);
		pv.setProd_totalstock(prod_totalstock);
		pv.setProd_properstock(prod_properstock);
		
		int cnt = prodService.updateProd(pv);
		
		if (cnt > 0) {
			System.out.println(prod_id + "회원 정보 수정 성공!!");
		} else {
			System.out.println(prod_id + "회원 정보 수정 실패!!");
		}
	}
	
	/**
	 * 물품 검색
	 */
	private void prodSearch() {
		sc.nextLine(); // 입력 버퍼 지우기
		
		System.out.println();
		System.out.println("　검색할 정보를 입력하세요.");
		System.out.print("　ID : ");
		String prod_id = sc.nextLine().trim();
		
		System.out.print("　물품명 : ");
		String prod_name = sc.nextLine().trim();
		
		ProdVO pv = new ProdVO();
		pv.setProd_id(prod_id);
		pv.setProd_name(prod_name);
		
		List<ProdVO> proList = prodService.selectProd(pv);
		
		System.out.println();
		System.out.println("──────────────────────────────");
		
		if(proList==null || proList.size()==0){
			System.out.println("검색한 자료가 하나도 없습니다.");
		} else {
			for (ProdVO pvo : proList) {
				System.out.println("물품 ID : " + pvo.getProd_id());
				System.out.println("물품명 : " + pvo.getProd_name());
				System.out.println("물품분류 ID : " + pvo.getProd_lgu());
				System.out.println("거래처 ID : " + pvo.getProd_buyer());
				System.out.println("구매단가 : " + pvo.getProd_cost());
				System.out.println("소비자가 : " + pvo.getProd_price());
				System.out.println("판매가 : " + pvo.getProd_sale());
				System.out.println("OUTLINE : " + pvo.getProd_outline());
				System.out.println("이미지 : " + pvo.getProd_img());
				System.out.println("TOTAL : " + pvo.getProd_totalstock());
				System.out.println("PROPER : " + pvo.getProd_properstock());
				System.out.println("──────────────────────────────");
			}
		}
		
		System.out.println("　검색 완료.");
	}
	
	/**
	 * 물품 삭제 : 물품 정보를 삭제하는 메서드
	 */
	private void prodDelete() {
		System.out.println();
		System.out.print("　삭제할 물품 ID : ");
		
		String prod_id = inputString();
		
		int cnt = prodService.deleteProd(prod_id);
		
		if (cnt > 0) {
			System.out.println(prod_id + "삭제 성공!!");
		} else {
			System.out.println(prod_id + "삭제 실패!!");
		}
	}

	/**
	 * 입고 관리 메뉴
	 */
	private void buyprodMain() {

		while (true) {
			System.out.println("──────────────────────────────");
			System.out.println("　　　1. 입고 내역 등록");
			System.out.println("　　　2. 입고 내역 전체 조회");
			System.out.println("　　　3. 입고 내역 수정");
			System.out.println("　　　4. 입고 내역 검색");
			System.out.println("　　　5. 입고 내역 삭제");
			System.out.println("　　　6. 뒤로 가기");
			System.out.println("──────────────────────────────");
			System.out.print("　번호 입력 : ");
			
			int selectNum = inputNum();
			
			switch (selectNum) {
			case 1: // 입고 내역 등록
				enterInsert();
				break;
			case 2: // 입고 내역 전체 조회
				enterPrint();
				break;
			case 3: // 입고 내역 수정
				enterEdit();
				break;
			case 4: // 입고 내역 검색
				enterSearch();
				break;
			case 5: // 입고 내역 삭제
				enterDelete();
				break;
			case 6: // 뒤로 가기
				start();
				break;
			default:
				System.out.println("　ERROR) 1~6번을 선택할 수 있습니다.");
			}
		}
	}
	
	/**
	 * 입고 내역 등록
	 */
	private void enterInsert() {
		System.out.println();
		System.out.println("　추가할 정보를 입력하세요.");
		
		String buy_date = "";
		String buy_prod = "";
		
		boolean chk = false;
		
		do {
			System.out.print("　입고날짜 : ");
			buy_date = inputString();
			System.out.print("　물품 ID : ");
			buy_prod = inputString();
			
			chk = bprodService.checkBuyProd(buy_date, buy_prod);
			
			if(chk == true){
				System.out.println("해당 내역은 이미 존재합니다.");
			}
		} while (chk == true);
		
		System.out.println();
		
		System.out.print("　입고 수량 : ");
		String buy_qty = inputString();
		
		System.out.print("　구매 단가 : ");
		String buy_cost = inputString();
		
		// 입력받은 정보를 VO객체에 넣는다.
		BuyProdVO bpv = new BuyProdVO();
		bpv.setBuy_date(buy_date);
		bpv.setBuy_prod(buy_prod);
		bpv.setBuy_qty(buy_qty);
		bpv.setBuy_cost(buy_cost);
		
		int cnt = bprodService.insertBuyProd(bpv);
		
		if (cnt > 0) {
			System.out.println("등록 성공!!");
		} else {
			System.out.println("등록 실패!!");
		}
	}
	
	/**
	 * 입고 내역 전체 조회
	 */
	private void enterPrint() {
		System.out.println();
		System.out.println("──────────────────────────────");
		
		List<BuyProdVO> bpList = bprodService.selectBuyProdAll();
		if (bpList.size() == 0) {
			System.out.println("　정보가 존재하지 않습니다.");
		} else {
			for (BuyProdVO bpvo : bpList) {
				System.out.println("　입고날짜 : " + bpvo.getBuy_date());
				System.out.println("　물품 ID : " + bpvo.getBuy_prod());
				System.out.println("　입고 수량 : " + bpvo.getBuy_qty());
				System.out.println("　구매 단가 : " + bpvo.getBuy_cost());
				System.out.println("──────────────────────────────");
			}
		}
		
		System.out.println("　조회 완료.");
	}
	
	/**
	 * 입고 내역 수정
	 */
	private void enterEdit() {

		System.out.println();
		
		String buy_date = "";
		String buy_prod = "";
		
		boolean chk = true;
		
		do {
			System.out.print("　입고날짜 : ");
			buy_date = inputString();
			System.out.print("　물품 ID : ");
			buy_prod = inputString();
			
			chk = bprodService.checkBuyProd(buy_date, buy_prod);
			
			if(chk == false){
				System.out.println("해당 내역은 이미 존재합니다.");
			}
		} while (chk == false);
		
		System.out.println();
		
		System.out.print("　입고 수량 : ");
		String buy_qty = inputString();
		
		System.out.print("　구매 단가 : ");
		String buy_cost = inputString();
		
		// 입력받은 정보를 VO객체에 넣는다.
		BuyProdVO bpv = new BuyProdVO();
		bpv.setBuy_date(buy_date);
		bpv.setBuy_prod(buy_prod);
		bpv.setBuy_qty(buy_qty);
		bpv.setBuy_cost(buy_cost);
		
		int cnt = bprodService.updateBuyProd(bpv);
		
		if (cnt > 0) {
			System.out.println("등록 성공!!");
		} else {
			System.out.println("등록 실패!!");
		}
	}
	
	/**
	 * 입고 내역 검색
	 */
	private void enterSearch() {

		sc.nextLine(); // 입력 버퍼 지우기
		
		System.out.println();
		System.out.println("　검색할 정보를 입력하세요.");
		System.out.print("　입고 날짜 : ");
		String buy_date = sc.nextLine().trim();
		
		System.out.print("　물품 ID : ");
		String buy_prod = sc.nextLine().trim();
		
		BuyProdVO bpv = new BuyProdVO();
		bpv.setBuy_date(buy_date);
		bpv.setBuy_prod(buy_prod);
		
		List<BuyProdVO> bpList = bprodService.selectBuyProd(bpv);
		
		System.out.println();
		System.out.println("──────────────────────────────");
		
		if(bpList==null || bpList.size()==0){
			System.out.println("검색한 자료가 하나도 없습니다.");
		} else {
			for (BuyProdVO bpvo : bpList) {
				System.out.println("　입고날짜 : " + bpvo.getBuy_date());
				System.out.println("　물품 ID : " + bpvo.getBuy_prod());
				System.out.println("　입고 수량 : " + bpvo.getBuy_qty());
				System.out.println("　구매 단가 : " + bpvo.getBuy_cost());
				System.out.println("──────────────────────────────");
			}
		}
		
		System.out.println("　검색 완료.");
	}
	
	/**
	 * 입고 내역 삭제
	 */
	private void enterDelete() {
		System.out.println();
		System.out.println("　삭제할 입고 내역 정보를 입력해주세요. ");
		
		System.out.print("　입고날짜 : ");
		String buy_date = inputString();
		System.out.print("　물품 ID : ");
		String buy_prod = inputString();
		
		int cnt = bprodService.deleteBuyProd(buy_date, buy_prod);
		
		if (cnt > 0) {
			System.out.println("삭제 성공!!");
		} else {
			System.out.println("삭제 실패!!");
		}
	}

	/**
	 * 숫자 입력
	 * @return int
	 */
	public int inputNum() {
		int input = 0;

		while (true) {
			try {
				input = sc.nextInt();
				break;
			} catch (Exception e) {
				sc = new Scanner(System.in);
				System.out.println("　ERROR) 정수를 입력해주세요.");
				System.out.print("　다시 입력해주세요 : ");
				continue;
			}
		}
		return input;
	}

	/**
	 * 문자 입력
	 * @return String
	 */
	public String inputString() {
		String input = "";

		while (true) {
			try { 				
				input = sc.next();
				break;
			} catch (Exception e) {
				sc = new Scanner(System.in);
				System.out.println("　ERROR) 문자를 입력해주세요.");
				System.out.print("　다시 입력해주세요 : ");
				continue;
			}
		}
		return input;
	}
}

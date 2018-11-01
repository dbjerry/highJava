package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import buyprod.IBuyProdServiceImpl;
import prod.IProdServiceImpl;
import vo.BuyProdVO;
import vo.CartVO;
import vo.MemberVO;
import vo.ProdVO;
import member.IMemberServiceImpl;
import cart.ICartServiceImpl;

public class View {
	Scanner sc;
	ICartServiceImpl cartService;
	IMemberServiceImpl memService;
	IProdServiceImpl prodService;
	IBuyProdServiceImpl bprodService;
	
	private static View view;

	public static View getInstance(){
		if(view == null){
			view = new View();
		}
		return view;
	}
	
	private View(){
		cartService = ICartServiceImpl.getInstance();
		memService = IMemberServiceImpl.getInstance();
		prodService = IProdServiceImpl.getInstance();
		bprodService = IBuyProdServiceImpl.getInstance();
		sc = new Scanner(System.in);
	}
	
	/**
	 * 시작 메서드
	 */
	public void start() {
		String ch = null;
		while(true){
			System.out.println("db관리를 시작합니다.");
			System.out.println("1.판매 관리");
			System.out.println("2.회원 관리");
			System.out.println("3.상품 관리");
			System.out.println("4.입고 관리");
			System.out.println("5.종료");
			
			ch = sc.next();
			
			if(ch.equals("1")){
				goCart();
			}else if(ch.equals("2")){
				goMember();
			}else if(ch.equals("3")){
				goProd();
			}else if(ch.equals("4")){
				goBprod();
			}else if(ch.equals("5")){
				return;
			}else{
				System.out.println("없는 번호입니다.");
			}
		}
	}

	/**
	 * 입고관리 메서드
	 */
	private void goBprod() {
		String ch = null;
		while(true){
			System.out.println("입고내역을 관리합니다.");
			System.out.println("1.입고 내역 등록");
			System.out.println("2.입고 내역 삭제");
			System.out.println("3.입고 내역 수정");
			System.out.println("4.전체 입고 내역 조회");
			System.out.println("5.입고 내역 검색");
			System.out.println("6.뒤로");
			
			ch = sc.next();
			
			if(ch.equals("1")){
				insertBprod();
			}else if(ch.equals("2")){
				deleteBprod();
			}else if(ch.equals("3")){
				updateBprod();
			}else if(ch.equals("4")){
				showAllBprod();
			}else if(ch.equals("5")){
				searchBprod();
			}else if(ch.equals("6")){
				return;
			}else{
				System.out.println("없는 번호입니다.");
			}
		}
	}

	/**
	 * 입고내역 조회 메서드
	 */
	private void searchBprod() {
		BuyProdVO bpv = new BuyProdVO();
		BuyProdVO bpv1 = null;
		boolean chk = false;
		String buy_date;
		String buy_prod;
		showAllBprod();
		System.out.println("입고내역을 조회합니다.");
		System.out.println("조회할 내역의 입고날짜를 입력해주세요.");
		buy_date = sc.next();
		
		while(true){
			System.out.println("조회할 내역의 상품코드를 입력해주세요.");
			buy_prod = sc.next();
			chk = prodService.chkProd(buy_prod);
			if(!chk){
				System.out.println("존재하지 않는 상품 코드입니다.");
				continue;
			}else{
				break;
			}
		}
		
		bpv.setBuy_date(buy_date);
		bpv.setBuy_prod(buy_prod);
		bpv1 = bprodService.getBprod(bpv);
		
		showBprod(bpv1);
		
	}

	/**
	 * buyprod객체 내용을 출력해주는 메서드
	 * @param bpv1
	 */
	private void showBprod(BuyProdVO bpv1) {
		ProdVO pv = prodService.getProd(bpv1.getBuy_prod());
		changeDate(bpv1);
		System.out.println("============= 입고 내역 ==============");
		System.out.println("-------------------------------------");
		System.out.println("입고 상품 코드 : " + bpv1.getBuy_prod());
		System.out.println("입고 상품명 : " + pv.getProd_name());
		System.out.println("상품 입고 날짜 : " + bpv1.getBuy_date());
		System.out.println("상품 입고 수량 : " + bpv1.getBuy_qty());
		System.out.println("상품 입고 가격 : " + bpv1.getBuy_cost());
		System.out.println("-------------------------------------");
		
		goPdf(bpv1);
	}

	/**
	 * 입고내역 수정 메서드
	 */
	private void updateBprod() {
		BuyProdVO bpv = new BuyProdVO();
		boolean chk = false;
		String buy_date;
		String buy_prod;
		String buy_qty;
		String buy_cost;
		
		showAllBprod();
		System.out.println("입고내역 수정을 진행합니다.");
		
		System.out.println("수정할 내역의 날짜를 입력하세요");
		buy_date = sc.next();
		
		while(true){
			System.out.println("수정할 내역의 상품코드를 입력해주세요.");
			buy_prod = sc.next();
			chk = prodService.chkProd(buy_prod);
			if(!chk){
				System.out.println("존재하지 않는 상품 코드입니다.");
				continue;
			}else{
				break;
			}
		}
		
		System.out.println("수정을 진행합니다.");
		System.out.println("수량 ->");
		buy_qty = sc.next();
		System.out.println("입고 가격 ->");
		buy_cost = sc.next();
		
		bpv.setBuy_date(buy_date);
		bpv.setBuy_prod(buy_prod);
		bpv.setBuy_cost(buy_cost);
		bpv.setBuy_qty(buy_qty);
		
		int cnt = bprodService.updateBprod(bpv);
		if(cnt>0){
			System.out.println("내역 수정 성공");
		}else{
			System.out.println("내역 수정 실패");
		}
	}

	private void deleteBprod() {
		BuyProdVO bpv = new BuyProdVO();
		boolean chk = false;
		String buy_date;
		String buy_prod;
		
		System.out.println("입고내역 삭제를 진행합니다.");
		showAllBprod();
		while(true){
			System.out.println("삭제할 내역의 상품코드를 입력해주세요.");
			buy_prod = sc.next();
			chk = prodService.chkProd(buy_prod);
			if(!chk){
				System.out.println("존재하지 않는 상품 코드입니다.");
				continue;
			}else{
				break;
			}
		}
		
		System.out.println("삭제할 내역의 날짜를 입력해주세요.");
		buy_date = sc.next();
		
		bpv.setBuy_date(buy_date);
		bpv.setBuy_prod(buy_prod);
		int cnt = bprodService.deleteBprod(bpv);
		if(cnt >0){
			System.out.println("내역 삭제 성공");
		}else{
			System.out.println("내역 삭제 실패");
		}
		
	}

	/**
	 * 
	 */
	private void insertBprod() {
		BuyProdVO bpv = new BuyProdVO();
		boolean chk = false;
		String buy_cost; 	
		String buy_qty; 
		String buy_prod;	
		
		System.out.println("입고내역 등록을 시작합니다.");
		System.out.println("입고 수량을 입력해주세요.");
		buy_qty = sc.next();
		
		System.out.println("입고 가격을 입력해주세요.");
		buy_cost = sc.next(); 	
		
		while(true){
			System.out.println("상품코드를 입력해주세요");
			buy_prod = sc.next();
			chk = prodService.chkProd(buy_prod);
			if(!chk){
				System.out.println("존재하지 않는 상품 코드입니다.");
				continue;
			}else{
				break;
			}
		}
		bpv.setBuy_cost(buy_cost);
		bpv.setBuy_prod(buy_prod);
		bpv.setBuy_qty(buy_qty);
		
		int cnt = bprodService.insertBprod(bpv);
		if(cnt>0){
			System.out.println("입고 내역 등록 성공");
		}else{
			System.out.println("입고내역 등록 실패");
		}
	}

	/**
	 * 전체 입고내역 출력하는 메서드
	 */
	private void showAllBprod() {
		List<BuyProdVO> bprodList = bprodService.getAllBprod();
		List<ProdVO> prodList = prodService.getAllProd();
		ProdVO pv = null;
		System.out.println();
		System.out.println("============= 입고 내역 ==============");
		for(BuyProdVO bpv : bprodList){
			
			changeDate(bpv);
//			pv = prodService.getProd(bpv.getBuy_prod());
			for(ProdVO pv1 : prodList){
				if(bpv.getBuy_prod().equals(pv1.getProd_id())){
					pv = pv1;
					break;
				}
			}
			System.out.println("-------------------------------------");
			System.out.println("입고 상품 코드 : " + bpv.getBuy_prod());
			System.out.println("입고 상품명 : " + pv.getProd_name());
			System.out.println("상품 입고 날짜 : " + bpv.getBuy_date());
			System.out.println("상품 입고 수량 : " + bpv.getBuy_qty());
			System.out.println("상품 입고 가격 : " + bpv.getBuy_cost());
		}
		System.out.println("-------------------------------------");
	
		
	}

	/**
	 * 날짜 스트링 뒤에 잘라주는 메서드
	 * @param bpv
	 */
	private void changeDate(BuyProdVO bpv) {
		String buy_date = bpv.getBuy_date();
		bpv.setBuy_date(buy_date.substring(0, 10));
	}

	/**
	 * 상품관리 메서드
	 */
	private void goProd() {
		String ch = null;
		while(true){
			System.out.println("상품을 관리합니다.");
			System.out.println("1.상품 등록");
			System.out.println("2.상품 삭제");
			System.out.println("3.상품 수정");
			System.out.println("4.전체 상품 조회");
			System.out.println("5.상품 검색");
			System.out.println("6.뒤로");
			
			ch = sc.next();
			
			if(ch.equals("1")){
				insertProd();
			}else if(ch.equals("2")){
				deleteProd();
			}else if(ch.equals("3")){
				updateProd();
			}else if(ch.equals("4")){
				showAllProd();
			}else if(ch.equals("5")){
				searchProd();
			}else if(ch.equals("6")){
				return;
			}else{
				System.out.println("없는 번호입니다.");
			}
		}
	}

	/**
	 * 상품 하나 검색
	 */
	private void searchProd() {
		String prod_id;
		boolean chk;
		ProdVO pv = null;
		while(true){
			showAllProd();
			System.out.println("조회할 상품의 id를 입력해주세요.");
			prod_id = sc.next();
			chk = prodService.chkProd(prod_id);
			if(!chk){
				System.out.println("존재하지 않는 상품id 입니다.");
				continue;
			}else{
				break;
			}
		}
		
		pv = prodService.getProd(prod_id);
		System.out.println();
		System.out.println("================================");
		System.out.println("상품 id : "+pv.getProd_id());
		System.out.println("상품 이름 : "+pv.getProd_name());
		System.out.println("상품 분류 : "+pv.getProd_lgu());
		System.out.println("상품 판매가 : "+pv.getProd_sale());
		System.out.println("================================");
		
		goPdf(pv);
	}

	/**
	 * 상품정보 수정 메서드
	 */
	private void updateProd() {
		String prod_id; //pk
		String prod_name;
		String prod_lgu;
		String prod_buyer;
		int prod_cost;
		int prod_price;
		int prod_sale;
		String prod_outline;
		int prod_properstock;
		int prod_totalstock;
		String prod_img;
		
		boolean chk;
		
		while(true){
			showAllProd();
			System.out.println("수정할 상품 id를 입력해주세요.");
			prod_id = sc.next();
			chk = prodService.chkProd(prod_id);
			if(!chk){
				System.out.println("존재하지않는 상품 id입니다.");
				continue;
			}else{
				break;
			}
		}
		
		System.out.println("상품명을 입력하세요");
		prod_name = sc.next();
		
		System.out.println("상품 분류를 입력하세요");
		prod_lgu = sc.next();
		System.out.println("상품 거래처를 입력하세요");
		prod_buyer = sc.next();
		System.out.println("상품 매입가를 입력하세요");
		prod_cost = sc.nextInt();
		
		System.out.println("상품 정가를 입력하세요");
		prod_price = sc.nextInt();
		
		System.out.println("상품 판매가를 입력하세요");
		prod_sale = sc.nextInt();
		
		System.out.println("상품 문구를 입력하세요");
		prod_outline = sc.next();
		
		System.out.println("상품 재고를 입력하세요");
		prod_properstock = sc.nextInt();
		
		System.out.println("상품 총량을 입력하세요");
		prod_totalstock = sc.nextInt();
		
		System.out.println("상품 이미지 경로를 입력하세요");
		prod_img = sc.next();
		
		ProdVO pv = new ProdVO();
		pv.setProd_id(prod_id); //pk
		pv.setProd_name(prod_name);
		pv.setProd_lgu(prod_lgu);
		pv.setProd_buyer(prod_buyer);
		pv.setProd_cost(prod_cost);
		pv.setProd_price(prod_price);
		pv.setProd_sale(prod_sale);
		pv.setProd_outline(prod_outline);
		pv.setProd_properstock(prod_properstock);
		pv.setProd_totalstock(prod_totalstock);
		pv.setProd_img(prod_img);
		
		int cnt = prodService.updateProd(pv);
		if (cnt>0) {
			System.out.println("상품 수정 완료");
		}else{
			System.out.println("상품 수정 실패");
		}
		
	}

	/**
	 * 상품 삭제 메서드
	 */
	private void deleteProd() {
		System.out.println("상품삭제를 진행합니다.");
		showAllProd();
		String prod_id = null;
		boolean chk = false;
		
		while(true){
			System.out.println("상품 id를 입력해주세요.");
			
			prod_id = sc.next();
			chk = prodService.chkProd(prod_id);
			if(chk){
				System.out.println(prod_id + "상품을 삭제합니다.");
				break;
			}else{
				System.out.println("존재하지 않는 상품 id 입니다.");
				continue;
			}
		}
		int cnt = prodService.deleteProd(prod_id);
		if(cnt > 0){
			System.out.println("상품 삭제 완료");
		}else {
			System.out.println("상품 삭제 실패");
		}
	}

	private void showAllProd() {
		List<ProdVO> prodList = prodService.getAllProd();
		System.out.println();
		System.out.println("============= 상품 목록 ==============");
		for(int i =0; i<prodList.size();i++){
			System.out.println("상품 id: "+prodList.get(i).getProd_id());
			System.out.println("상품 이름: "+prodList.get(i).getProd_name());
			System.out.println("상품 분류: "+prodList.get(i).getProd_lgu());
			System.out.println("상품 판매가: "+prodList.get(i).getProd_sale());
			System.out.println("------------------------------------------------");
		}
	}

	/**
	 * 상품등록 메서드
	 */
	private void insertProd() {
		String prod_id; //pk
		String prod_name;
		String prod_lgu;
		String prod_buyer;
		int prod_cost;
		int prod_price;
		int prod_sale;
		String prod_outline;
		int prod_properstock;
		int prod_totalstock;
		String prod_img;
		
		boolean chk;
		
		while(true){
			System.out.println("상품 id를 입력해주세요.");
			prod_id = sc.next();
			chk = prodService.chkProd(prod_id);
			if(chk){
				System.out.println("이미 존재하는 id입니다.");
				continue;
			}else{
				break;
			}
		}
		
		System.out.println("상품명을 입력하세요");
		prod_name = sc.next();
		
		System.out.println("상품 분류를 입력하세요");
		prod_lgu = sc.next();
		System.out.println("상품 거래처를 입력하세요");
		sc.nextLine();
		prod_buyer = sc.nextLine();
		System.out.println("상품 매입가를 입력하세요");
		prod_cost = sc.nextInt();
		
		System.out.println("상품 정가를 입력하세요");
		prod_price = sc.nextInt();
		
		System.out.println("상품 판매가를 입력하세요");
		prod_sale = sc.nextInt();
		
		System.out.println("상품 문구를 입력하세요");
		sc.nextLine();
		prod_outline = sc.nextLine();
		
		System.out.println("상품 재고를 입력하세요");
		prod_properstock = sc.nextInt();
		
		System.out.println("상품 총량을 입력하세요");
		prod_totalstock = sc.nextInt();
		
		System.out.println("상품 이미지 경로를 입력하세요");
		prod_img = sc.next();
		
		ProdVO pv = new ProdVO();
		pv.setProd_id(prod_id); //pk
		pv.setProd_name(prod_name);
		pv.setProd_lgu(prod_lgu);
		pv.setProd_buyer(prod_buyer);
		pv.setProd_cost(prod_cost);
		pv.setProd_price(prod_price);
		pv.setProd_sale(prod_sale);
		pv.setProd_outline(prod_outline);
		pv.setProd_properstock(prod_properstock);
		pv.setProd_totalstock(prod_totalstock);
		pv.setProd_img(prod_img);
		int cnt = prodService.insertProd(pv);
		
		
		if(cnt > 0 ){
			System.out.println("상품 등록 성공");
		}else{
			System.out.println("상품 등록 실패");
		}
	}

	/**
	 * 회원 관리메서드
	 */
	private void goMember() {
		String ch = null;
		while(true){
			System.out.println("회원을 관리합니다.");
			System.out.println("1.회원 등록");
			System.out.println("2.회원 삭제");
			System.out.println("3.회원 수정");
			System.out.println("4.전체 회원 기록");
			System.out.println("5.회원 조회");
			System.out.println("6.뒤로");
			
			ch = sc.next();
			
			if(ch.equals("1")){
				insertMember();
			}else if(ch.equals("2")){
				deleteMember();
			}else if(ch.equals("3")){
				updateMember();
			}else if(ch.equals("4")){
				showAllMember();
			}else if(ch.equals("5")){
				searchMember();
			}else if(ch.equals("6")){
				return;
			}else{
				
			}
		}
	}

	private void searchMember() {
		String mem_id;
		boolean chk;
		MemberVO mv = null;
		while(true){
			showAllMember();
			System.out.println("조회할 회원의 id를 입력해주세요.");
			mem_id = sc.next();
			chk = memService.chkMember(mem_id);
			if(!chk){
				System.out.println("존재하지 않는 회원id 입니다.");
				continue;
			}else{
				break;
			}
		}
		
		mv = memService.getMember(mem_id);
		System.out.println();
		System.out.println("================================");
		System.out.println("회원 id: "+mv.getMem_id());
		System.out.println("회원 이름: "+mv.getMem_name());
		System.out.println("회원 주소: "+mv.getMem_add1());
		System.out.println("회원 메일: "+mv.getMem_mail());
		System.out.println("================================");
		
		goPdf(mv);
		
		
	}

	private void goPdf(Object obj) {
		System.out.println("pdf파일로 저장하시겠습니까?");
		System.out.println("1.예");
		System.out.println("2.아니오");
		String ch = sc.next();
		
		while(true){
			if(ch.equals("1")){
				System.out.println("pdf 저장중");
				savePdf(obj);
				break;
			}else if(ch.equals("2")){
				break;
			}else{
				System.out.println("잘못된 입력");
				continue;
			}
		}
	}

	/**
	 * 회원정보 수정 메서드
	 */
	private void updateMember() {
		String mem_id;
		boolean chk = false;
		String mem_pass;
		String mem_name;
		String mem_mail;
		String mem_comtel;
		String mem_hometel;
		String mem_add1;
		String mem_add2;
		String mem_regno1;
		String mem_regno2;
		String mem_zip;
		
		
		
		showAllMember();
		while(true){
			System.out.println("수정할 회원의 id를 입력해주세요.");
			mem_id = sc.next();
			chk = chk_member(mem_id);
			if(chk){
				System.out.println(mem_id+" 회원의 정보를 수정합니다.");
				break;
			}else{
				System.out.println("존재하지 않는 회원입니다.");
				continue;
			}
		}
		
		System.out.println("비밀번호를 입력해주세요.");
		mem_pass = sc.next();
		
		System.out.println("이름을 입력해주세요.");
		mem_name = sc.next();
		
		System.out.println("이메일을 입력해주세요.");
		mem_mail = sc.next();
		
		System.out.println("전화번호를 입력하세요.");
		mem_comtel = sc.next();
		mem_hometel = mem_comtel;
		
		System.out.println("주소를 입력하세요");
		mem_add1 = sc.next();
		System.out.println("상세주소를 입력하세요.");
		mem_add2 = sc.next();
		System.out.println("우편번호를 입력하세요");
		mem_zip = sc.next();
		System.out.println("주민등록번호 앞6자리를 입력하세요");
		mem_regno1 = sc.next();
		System.out.println("주민등록번호 뒤 7 자리를 입력하세요1");
		mem_regno2 = sc.next();
		
		MemberVO mv = new MemberVO();
		mv.setMem_id(mem_id);
		mv.setMem_pass(mem_pass);
		mv.setMem_name(mem_name);
		mv.setMem_mail(mem_mail);
		mv.setMem_comtel(mem_comtel);
		mv.setMem_hometel(mem_hometel);
		mv.setMem_add1(mem_add1);
		mv.setMem_add2(mem_add2);
		mv.setMem_zip(mem_zip);
		mv.setMem_regno1(mem_regno1);
		mv.setMem_regno2(mem_regno2);
		
		int cnt = memService.updateMember(mv);
		if (cnt > 0) {
			System.out.println("수정 성공");
		}else{
			System.out.println("수정 실패");
		}
		
		
	}

	/**
	 * 회원 삭제 메서드
	 */
	private void deleteMember() {
		System.out.println("회원삭제를 진행합니다.");
		showAllMember();
		String mem_id = null;
		boolean chk = false;
		
		while(true){
			System.out.println("회원 id를 입력해주세요.");
			
			mem_id = sc.next();
			chk = chk_member(mem_id);
			if(chk){
				System.out.println(mem_id + "회원을 삭제합니다.");
				break;
			}else{
				System.out.println("존재하지 않는 회원 id 입니다.");
				continue;
			}
		}
		int cnt = memService.deleteMember(mem_id);
		if(cnt > 0){
			System.out.println("회원 삭제 완료");
		}else {
			System.out.println("회원 삭제 실패");
		}
	}

	/**
	 * 회원 등록 메서드
	 */
	private void insertMember() {
		System.out.println("새로운 회원을 등록합니다.");
		boolean chk = false;
		String mem_id = null;
		String mem_pass;
		String mem_name;
		String mem_mail;
		String mem_comtel;
		String mem_hometel;
		String mem_add1;
		String mem_add2;
		String mem_regno1;
		String mem_regno2;
		String mem_zip;
		
		
		while(true){
			System.out.println("회원 id를 입력해주세요.");
			mem_id = sc.next();
			chk = chk_member(mem_id);
			if(chk){
				System.out.println("이미 존재하는 id입니다.");
				continue;
			}else{
				break;
			}
		}
		
		System.out.println("비밀번호를 입력해주세요.");
		mem_pass = sc.next();
		
		System.out.println("이름을 입력해주세요.");
		mem_name = sc.next();
		
		System.out.println("이메일을 입력해주세요.");
		mem_mail = sc.next();
		
		System.out.println("전화번호를 입력하세요.");
		mem_comtel = sc.next();
		mem_hometel = mem_comtel;
		
		System.out.println("주소를 입력하세요");
		mem_add1 = sc.next();
		System.out.println("상세주소를 입력하세요.");
		mem_add2 = sc.next();
		System.out.println("우편번호를 입력하세요");
		mem_zip = sc.next();
		System.out.println("주민등록번호 앞6자리를 입력하세요");
		mem_regno1 = sc.next();
		System.out.println("주민등록번호 뒤 7 자리를 입력하세요1");
		mem_regno2 = sc.next();
		
		MemberVO mv = new MemberVO();
		mv.setMem_id(mem_id);
		mv.setMem_pass(mem_pass);
		mv.setMem_name(mem_name);
		mv.setMem_mail(mem_mail);
		mv.setMem_comtel(mem_comtel);
		mv.setMem_hometel(mem_hometel);
		mv.setMem_add1(mem_add1);
		mv.setMem_add2(mem_add2);
		mv.setMem_zip(mem_zip);
		mv.setMem_regno1(mem_regno1);
		mv.setMem_regno2(mem_regno2);
		
		int cnt = memService.insertMember(mv);
		
		if(cnt > 0 ){
			System.out.println("회원 등록 성공");
		}else{
			System.out.println("회원 등록 실패");
		}
	}

	/**
	 * 회원 전체를 보여주는 메서드
	 */
	private void showAllMember() {
		List<MemberVO> memList = memService.getAllMember();
		System.out.println();
		System.out.println("============= 회원 목록 ==============");
		for(int i =0; i<memList.size();i++){
			System.out.println("회원 id: "+memList.get(i).getMem_id());
			System.out.println("회원 이름: "+memList.get(i).getMem_name());
			System.out.println("회원 주소: "+memList.get(i).getMem_add1());
			System.out.println("회원 메일: "+memList.get(i).getMem_mail());
			System.out.println("------------------------------------------------");
		}
	}

	/**
	 * memListpdf 출력하는 메서드
	 * @param memList 
	 */
	private void savePdf(Object obj) {
		// TODO Auto-generated method stub
		
		//문서를 만들고 페이지를 추가하는 작업
		PDDocument dc = new PDDocument();
		PDPage page = new PDPage();
		dc.addPage(page);
		
		//폰트를 설정
		try {
			InputStream fontStream = new FileInputStream("c:/Windows/Fonts/malgun.TTF");
			PDType0Font font = PDType0Font.load(dc, fontStream);
			
			// 시작을 위한 스트림 생성
			PDPageContentStream contentStream = new PDPageContentStream(dc, page);
			
			contentStream.beginText();
			contentStream.setFont(font, 14);
			contentStream.newLineAtOffset(100, 750);
			contentStream.setLeading(15f);
			
			if(obj instanceof MemberVO){
				MemberVO mv = (MemberVO)obj;
				contentStream.showText("============= 회원 조회 ==============");
				contentStream.newLine();
				contentStream.showText("회원 id: "+mv.getMem_id());
				contentStream.newLine();
				contentStream.showText("회원 이름: "+mv.getMem_name());
				contentStream.newLine();
				contentStream.showText("회원 주소: "+mv.getMem_add1());
				contentStream.newLine();
				contentStream.showText("회원 메일: "+mv.getMem_mail());
				contentStream.newLine();
				contentStream.showText("------------------------------------------------");
				contentStream.newLine();
				contentStream.endText();
				contentStream.close();
				
				dc.save("member.pdf");
				dc.close();
			}else if(obj instanceof ProdVO){
				ProdVO pv = (ProdVO)obj;
				contentStream.showText("============= 상품 조회 ==============");
				contentStream.newLine();
				contentStream.showText("상품 id: "+pv.getProd_id());
				contentStream.newLine();
				contentStream.showText("상품 이름: "+pv.getProd_name());
				contentStream.newLine();
				contentStream.showText("상품 분류: "+pv.getProd_lgu());
				contentStream.newLine();
				contentStream.showText("상품 판매가: "+pv.getProd_sale());
				contentStream.newLine();
				contentStream.showText("------------------------------------------------");
				contentStream.newLine();
				contentStream.endText();
				contentStream.close();
				
				dc.save("prod.pdf");
				dc.close();
				
			}else if(obj instanceof BuyProdVO){
				BuyProdVO bpv = (BuyProdVO)obj;
				ProdVO pv = prodService.getProd(bpv.getBuy_prod());
				changeDate(bpv);
				contentStream.showText("============= 입고 내역 ==============");
				contentStream.newLine();
				contentStream.showText("입고 상품 코드 : " + bpv.getBuy_prod());
				contentStream.newLine();
				contentStream.showText("입고 상품명 : " + pv.getProd_name());
				contentStream.newLine();
				contentStream.showText("상품 입고 날짜 : " + bpv.getBuy_date());
				contentStream.newLine();
				contentStream.showText("상품 입고 수량 : " + bpv.getBuy_qty());
				contentStream.newLine();
				contentStream.showText("상품 입고 가격 : " + bpv.getBuy_cost());
				contentStream.newLine();
				contentStream.showText("------------------------------------------------");
				contentStream.newLine();
				contentStream.endText();
				contentStream.close();
				
				dc.save("buyprod.pdf");
				dc.close();
				
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 판매관리 메서드
	 */
	private void goCart() {
		String ch = null;
		while(true){
			System.out.println("판매내역을 관리합니다.");
			System.out.println("1.판매 등록");
			System.out.println("2.판매 삭제");
			System.out.println("3.판매 수정");
			System.out.println("4.전체 판매 기록");
			System.out.println("5.판매기록 검색");
			System.out.println("6.뒤로");
			
			ch = sc.next();
			
			if(ch.equals("1")){
				insertCart();
			}else if(ch.equals("2")){
				
			}else if(ch.equals("3")){
				
			}else if(ch.equals("4")){
				showAllCart();
			}else if(ch.equals("5")){
				
			}else if(ch.equals("6")){
				return;
			}else{
				
			}
		}
	}

	/**
	 * cart내역 모두 보여주는 메서드
	 */
	private void showAllCart() {
		List<CartVO> cartList = cartService.getAllCart();
		System.out.println();
		System.out.println("============= 판매내역 ==============");
		for(int i =0; i<cartList.size();i++){
			System.out.println("회원 id: "+cartList.get(i).getCart_member());
			System.out.println("판매 코드: "+cartList.get(i).getCart_no());
			System.out.println("판매 상품: "+cartList.get(i).getCart_prod());
			System.out.println("판매 수량: "+cartList.get(i).getCart_qty());
			System.out.println("------------------------------------------------");
		}
	}

	/**
	 * 판매내역 등록하는 메서드
	 */
	private void insertCart() {
		String cart_member = null;
		String cart_no = null;
		String cart_prod = null;
		int cart_qty = 0;
		
		boolean chk = false;
		System.out.println("판매내역을 등록합니다.");
		while(true){
			System.out.println("회원 id를 입력해주세요.");
			cart_member = sc.next();
			chk = chk_member(cart_member);
			if(!chk){
				System.out.println("존재하지 않는 회원입니다.");
				continue;
			}else{
				break;
			}
		}
		
		
		
		System.out.println("cart_no를 입력하세요");
		cart_no = sc.next();
		
		cart_prod = input_prod_id();
		
		while(true){
			System.out.println("수량을 입력하세요.");
			try{
				cart_qty = sc.nextInt();
				break;
			}catch(RuntimeException e){
				System.out.println("잘못된 수량입니다.");
				continue;
			}
		}
		CartVO cv = new CartVO();
		cv.setCart_member(cart_member);
		cv.setCart_no(cart_no);
		cv.setCart_prod(cart_prod);
		cv.setCart_qty(cart_qty);
		
		int cnt = cartService.insertCart(cv);
		if(cnt == 1){
			System.out.println("판매내역 등록을 완료했습니다.");
		}else{
			System.out.println("판매내역 등록 실패");
		}
	}

	/**
	 * prod_id 입력받는 메서드
	 * @return
	 */
	private String input_prod_id() {
		String prod_id = null;
		boolean chk = false;
			
		while(true){
			System.out.println("상품코드를 입력하세요.");
			prod_id = sc.next();
			chk = prodService.chkProd(prod_id);
			if(!chk){
				System.out.println("존재하지 않는 상품코드입니다.");
				continue;
			}else{
				break;
			}
		}
		return prod_id;
	}

	/**
	 * mem_id 입력받는 메서드
	 * @return
	 */
	private boolean chk_member(String mem_id) {
		boolean chk = false;
		chk = memService.chkMember(mem_id);
		return chk;
	}

}

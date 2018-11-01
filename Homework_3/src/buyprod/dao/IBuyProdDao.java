package buyprod.dao;

import java.util.HashMap;
import java.util.List;

import vo.BuyProdVO;

/**
 * BuyProdVO의 SQL문 수행결과를 service에 전달하는 DAO interface
 * @author 김윤주
 * @since 2018.06.29
 * @version 1.0
 */
public interface IBuyProdDao {
	
	/**
	 * BUYPROD 테이블 전체 레코드를 List에 담아 반환하는 메서드
	 * @return List<BuyProdVO> buyprod 객체를 담고있는 List
	 */
	public List<BuyProdVO> selectBuyProdAll();
	
	/**
	 * BuyProdVO에 담긴 정보를 DB에 insert하는 메서드
	 * @param bpv insert할 정보가 담긴 buyprod 객체
	 * @return DB에 insert 성공하면 1 이상의 값이 반환, 실패하면 0이 반환
	 */
	public int insertBuyProd(BuyProdVO bpv);
	
	/**
	 * BuyProdVO에 담긴 buyprod 정보를 DB에 update하는 메서드
	 * @param pv update할 정보가 담긴 buyprod 객체
	 * @return DB에 update 성공하면 1 반환, 실패하면 0 반환
	 */
	public int updateBuyProd(BuyProdVO bpv);
	
	/**
	 * 입고날짜, 물품ID를 Map으로 입력 받아 해당하는 물품 객체를 delete하는 메서드
	 * @param bpMap
	 * @return
	 */
	public int deleteBuyProd(String buy_date, String buy_prod);
	
	/**
	 * 입고날짜, 물품ID를 Map으로 입력 받아 해당 입고 여부를 체크하는 메서드
	 * @param bpMap
	 * @return 입고 내역이 있으면 true, 없으면 false
	 */
	public boolean checkBuyProd(String buy_date, String buy_prod);
	
	/**
	 * ProdVO에 담긴 자료를 이용하여 물품을 검색하는 메서드
	 * @param pv 검색할 자료가 담긴 ProdVO 객체
	 * @return 검색된 결과가 담긴 List
	 */
	public List<BuyProdVO> selectBuyProd(BuyProdVO bpv);
	
}

package prod.service;

import java.util.List;

import vo.ProdVO;

public interface IProdService {

	/**
	 * PROD 테이블 전체 레코드를 List에 담아 반환하는 메서드
	 * @return List<ProdVO> 회원 객체를 담고있는 List
	 */
	public List<ProdVO> selectProdAll();
	
	/**
	 * ProdVO에 담긴 회원 정보를 DB에 insert하는 메서드
	 * @param pv insert할 정보가 담긴 회원 객체
	 * @return DB에 insert 성공하면 1 이상의 값이 반환, 실패하면 0이 반환
	 */
	public int insertProd(ProdVO pv);
	
	/**
	 * ProdVO에 담긴 회원 정보를 DB에 update하는 메서드
	 * @param pv update할 정보가 담긴 회원 객체
	 * @return DB에 update 성공하면 1 반환, 실패하면 0 반환
	 */
	public int updateProd(ProdVO pv);
	
	/**
	 * 물품 ID를 입력 받아 해당하는 물품 객체를 delete하는 메서드
	 * @param prod_id 물품 ID
	 * @return
	 */
	public int deleteProd(String prod_id);
	
	/**
	 * 물품 ID를 입력 받아 해당하는 물품 객체의 존재 여부를 체크하는 메서드
	 * @param prod_id 물품 ID
	 * @return 해당 회원이 있으면 true, 없으면 false
	 */
	public boolean checkProd(String prod_id);
	
	/**
	 * ProdVO에 담긴 자료를 이용하여 물품을 검색하는 메서드
	 * @param pv 검색할 자료가 담긴 ProdVO 객체
	 * @return 검색된 결과가 담긴 List
	 */
	public List<ProdVO> selectProd(ProdVO pv);
	
}

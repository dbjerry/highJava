package prod.service;

import java.util.List;

import prod.dao.ProdDaoImpl;
import vo.ProdVO;


public class ProdServiceImpl implements IProdService {
	// DAO를 객체변수로 선언
	private ProdDaoImpl prodDao;
	
	// 싱글톤 적용
	private static ProdServiceImpl service;
	
	private ProdServiceImpl() {
		prodDao = ProdDaoImpl.getInstance();
	}
	
	public static ProdServiceImpl getInstance() {
		if (service == null) {
			service = new ProdServiceImpl();
		}
		
		return service;
	}
	
	@Override
	public List<ProdVO> selectProdAll() {
		return prodDao.selectProdAll();
	}

	@Override
	public int insertProd(ProdVO pv) {
		return prodDao.insertProd(pv);
	}

	@Override
	public int updateProd(ProdVO pv) {
		return prodDao.updateProd(pv);
	}

	@Override
	public int deleteProd(String prod_id) {
		return prodDao.deleteProd(prod_id);
	}

	@Override
	public boolean checkProd(String prod_id) {
		return prodDao.checkProd(prod_id);
	}

	@Override
	public List<ProdVO> selectProd(ProdVO pv) {
		return prodDao.selectProd(pv);
	}

}

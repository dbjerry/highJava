package buyprod.service;

import java.util.List;

import buyprod.dao.BuyProdDaoImpl;
import vo.BuyProdVO;

public class BuyProdServiceImpl implements IBuyProdService {
	// DAO를 객체변수로 선언
	private BuyProdDaoImpl bpDao;
	
	// 싱글톤 적용
	private static BuyProdServiceImpl service;
	
	private BuyProdServiceImpl() {
		bpDao = BuyProdDaoImpl.getInstance();
	}
	
	public static BuyProdServiceImpl getInstance() {
		if (service == null) {
			service = new BuyProdServiceImpl();
		}
		
		return service;
	}
	
	@Override
	public List<BuyProdVO> selectBuyProdAll() {
		return bpDao.selectBuyProdAll();
	}

	@Override
	public int insertBuyProd(BuyProdVO bpv) {
		return bpDao.insertBuyProd(bpv);
	}

	@Override
	public int updateBuyProd(BuyProdVO bpv) {
		return bpDao.updateBuyProd(bpv);
	}

	@Override
	public int deleteBuyProd(String buy_date, String buy_prod) {
		return bpDao.deleteBuyProd(buy_date, buy_prod);
	}

	@Override
	public boolean checkBuyProd(String buy_date, String buy_prod) {
		return bpDao.checkBuyProd(buy_date, buy_prod);
	}

	@Override
	public List<BuyProdVO> selectBuyProd(BuyProdVO bpv) {
		return bpDao.selectBuyProd(bpv);
	}
	
	
}

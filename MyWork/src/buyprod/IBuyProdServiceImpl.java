package buyprod;

import java.util.List;

import vo.BuyProdVO;


public class IBuyProdServiceImpl implements IBuyProdService {
	private static IBuyProdServiceImpl service;
	private IBuyProdDaoImpl dao;
	
	
	private IBuyProdServiceImpl(){
		dao = IBuyProdDaoImpl.getInstance();
	}
	
	public static IBuyProdServiceImpl getInstance(){
		if(service == null){
			service = new IBuyProdServiceImpl();
		}
		return service;
	}

	@Override
	public int insertBprod(BuyProdVO bpv) {
		return dao.insertBprod(bpv);
	}

	@Override
	public int deleteBprod(BuyProdVO bpv) {
		return dao.deleteBprod(bpv);
	}

	@Override
	public int updateBprod(BuyProdVO bpv) {
		return dao.updateBprod(bpv);
	}

	@Override
	public List<BuyProdVO> getAllBprod() {
		return dao.getAllBprod();
	}

	@Override
	public boolean chkBprod(BuyProdVO bpv) {
		return dao.chkBprod(bpv);
	}

	@Override
	public BuyProdVO getBprod(BuyProdVO bpv) {
		return dao.getBprod(bpv);
	}
	
}

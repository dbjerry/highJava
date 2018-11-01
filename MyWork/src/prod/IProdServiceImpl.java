package prod;

import java.util.List;

import vo.ProdVO;

public class IProdServiceImpl implements IProdService {
	private static IProdServiceImpl prodService;
	private IProdDaoImpl prodDao;
	
	private IProdServiceImpl(){
		prodDao = IProdDaoImpl.getInstance();
	}
	
	public static IProdServiceImpl getInstance(){
		if(prodService == null){
			prodService = new IProdServiceImpl();
		}
		
		return prodService;
	}

	@Override
	public int insertProd(ProdVO pv) {
		return prodDao.insertProd(pv);
	}

	@Override
	public int deleteProd(String prod_id) {
		return prodDao.deleteProd(prod_id);
	}

	@Override
	public int updateProd(ProdVO pv) {
		return prodDao.updateProd(pv);
	}

	@Override
	public List<ProdVO> getAllProd() {
		return prodDao.getAllProd();
	}


	@Override
	public List<ProdVO> searchProd(ProdVO pv) {
		return prodDao.searchProd(pv);
	}

	@Override
	public boolean chkProd(String prod_id) {
		return prodDao.chkProd(prod_id);
	}

	@Override
	public ProdVO getProd(String prod_id) {
		return prodDao.getProd(prod_id);
	}
	
	
}

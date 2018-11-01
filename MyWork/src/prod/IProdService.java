package prod;

import java.util.List;

import vo.ProdVO;

public interface IProdService {

	public int insertProd(ProdVO pv);
	
	public int deleteProd(String prod_id);
	
	public int updateProd(ProdVO pv);
	
	public List<ProdVO> getAllProd();
	
	public boolean chkProd(String prod_id);

	public ProdVO getProd(String prod_id);
	
	public List<ProdVO> searchProd(ProdVO pv);
}

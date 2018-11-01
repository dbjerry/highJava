package buyprod;

import java.util.List;

import vo.BuyProdVO;



public interface IBuyProdDao {
	
	public int insertBprod(BuyProdVO bpv);
	
	public int deleteBprod(BuyProdVO bpv);
	
	public int updateBprod(BuyProdVO bpv);
	
	public List<BuyProdVO> getAllBprod();
	
	public boolean chkBprod(BuyProdVO bpv);
	
	public BuyProdVO getBprod(BuyProdVO bpv);
	
	
}

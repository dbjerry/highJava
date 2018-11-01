package buyprod;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.BuyProdVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;


public class IBuyProdDaoImpl implements IBuyProdDao {
	private SqlMapClient smc;

	private static IBuyProdDaoImpl dao;
	
	private IBuyProdDaoImpl() {
		Reader rd;
		try{
			rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
		}catch(IOException e){
			System.out.println("SqlMapClient객체 생성 실패!!!");
			e.printStackTrace();
		}
	}
	
	public static IBuyProdDaoImpl getInstance() {
		if(dao == null){
			dao = new IBuyProdDaoImpl();
		}
		return dao;
	}

	
	
	
	@Override
	public int insertBprod(BuyProdVO bpv) {
		Object obj = null;
		int cnt = 0;
		try {
			obj = smc.insert("buyprod.insertBprod", bpv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(obj == null){
			cnt = 1;
		}
		return cnt;
	}

	@Override
	public int deleteBprod(BuyProdVO bpv) {
		int cnt = 0;
		
		try {
			cnt = smc.delete("buyprod.deleteBprod", bpv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int updateBprod(BuyProdVO bpv) {
		int cnt =0;
		try {
			cnt = smc.update("buyprod.updateBprod", bpv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<BuyProdVO> getAllBprod() {
		List<BuyProdVO> bprodList = new ArrayList<BuyProdVO>();
		
		try {
			bprodList = smc.queryForList("buyprod.getBprodAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bprodList;
	}



	@Override
	public boolean chkBprod(BuyProdVO bpv) {
		boolean chk = false;
		try {
			BuyProdVO bpv1 = (BuyProdVO) smc.queryForObject("buyprod.getBprod", bpv);
			if(bpv1 != null){
				chk = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return chk;
	}

	@Override
	public BuyProdVO getBprod(BuyProdVO bpv) {
		BuyProdVO bpv1 = null;
		try {
			bpv1 = (BuyProdVO) smc.queryForObject("buyprod.getBprod", bpv);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bpv1;
	}
		

}	
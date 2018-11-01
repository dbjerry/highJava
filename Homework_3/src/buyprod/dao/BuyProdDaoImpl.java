package buyprod.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.BuyProdVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class BuyProdDaoImpl implements IBuyProdDao {
	
	private SqlMapClient smc;

	// 싱글톤 적용
	private static BuyProdDaoImpl dao;
	
	private BuyProdDaoImpl() {
		Reader rd;
		try{
			rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
		}catch(IOException e){
			System.out.println("SqlMapClient객체 생성 실패!!!");
			e.printStackTrace();
		}
	}
	
	public static BuyProdDaoImpl getInstance() {
		if(dao == null){
			dao = new BuyProdDaoImpl();
		}
		
		return dao;
	}

	@Override
	public List<BuyProdVO> selectBuyProdAll() {
		List<BuyProdVO> bpList = new ArrayList<BuyProdVO>();
		
		try {
			bpList = smc.queryForList("buyprod.selectBuyProdAll");
		} catch (SQLException e) {
			System.out.println("SQLException 발생");
			e.printStackTrace();
		}
		
		return bpList;
	}

	@Override
	public int insertBuyProd(BuyProdVO bpv) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("buyprod.insertBuyProd", bpv);
			
			if (obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			System.out.println("SQLException 발생");
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int updateBuyProd(BuyProdVO bpv) {
		int cnt = 0;
		
		try {
			cnt = smc.update("buyprod.updateBuyProd", bpv);
		} catch (SQLException e) {
			System.out.println("SQLException 발생");
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int deleteBuyProd(String buy_date, String buy_prod) {
		Map<String, String> bpMap = new HashMap<String, String>();
		bpMap.put("buy_date", buy_date);
		bpMap.put("buy_prod", buy_prod);
		
		int cnt = 0;
		
		try {
			cnt = smc.delete("buyprod.deleteBuyProd", bpMap);
		} catch (SQLException e) {
			System.out.println("SQLException 발생");
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public boolean checkBuyProd(String buy_date, String buy_prod) {
		Map<String, String> bpMap = new HashMap<String, String>();
		bpMap.put("buy_date", buy_date);
		bpMap.put("buy_prod", buy_prod);
		
		boolean chk = false;
		
		try {
			int count = (int) smc.queryForObject("buyprod.checkBuyProd", bpMap);
			
			if(count > 0){
				chk = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return chk;
	}

	@Override
	public List<BuyProdVO> selectBuyProd(BuyProdVO bpv) {
		List<BuyProdVO> bpList = new ArrayList<BuyProdVO>();
		
		try {
			bpList = smc.queryForList("buyprod.selectBuyProd", bpv);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return bpList;
	}
		

}	
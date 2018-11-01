package prod;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.MemberVO;
import vo.ProdVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class IProdDaoImpl implements IProdDao {
	private static IProdDaoImpl prodDao;
	private SqlMapClient smc;
	
	private IProdDaoImpl(){
		Reader rd;
		try {
			rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			
		} catch (IOException e) {
			System.out.println("SqlMapClient객체 생성 실패");
			e.printStackTrace();
		}
	}
	
	public static IProdDaoImpl getInstance(){
		if(prodDao == null){
			prodDao = new IProdDaoImpl();
		}
		return prodDao;
	}

	
	
	@Override
	public int insertProd(ProdVO pv) {
		Object obj = null;
		int cnt = 0;
		try {
			obj = smc.insert("prod.insertProd", pv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(obj == null){
			cnt = 1;
		}
		return cnt;
	}

	@Override
	public int deleteProd(String prod_id) {
		int cnt = 0;
		try {
			cnt = smc.delete("prod.deleteProd", prod_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int updateProd(ProdVO pv) {
		int cnt = 0;
		try {
			cnt = smc.update("prod.updateProd", pv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<ProdVO> getAllProd() {
		List<ProdVO> prodList = new ArrayList<ProdVO>();
		try {
			prodList = smc.queryForList("prod.getProdAll");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return prodList;
	}

	@Override
	public boolean chkProd(String prod_id) {
		boolean chk = false;
		Object obj = null;
		try {
			obj = smc.queryForObject("prod.getProd", prod_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(obj == null){
			chk = false;
		}else{
			chk = true;
		}
		return chk;
	}

	@Override
	public List<ProdVO> searchProd(ProdVO pv) {
		List<ProdVO> prodList = new ArrayList<ProdVO>();
		try {
			prodList = smc.queryForList("prod.searchProd", pv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prodList;
	}


	@Override
	public ProdVO getProd(String prod_id) {
		ProdVO pv = null;
		try {
			pv = (ProdVO) smc.queryForObject("prod.getProd", prod_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pv;
	}
		
	
	
	
	
	
	
}

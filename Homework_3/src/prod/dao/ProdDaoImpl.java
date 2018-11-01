package prod.dao;

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

public class ProdDaoImpl implements IProdDao {
	
	private SqlMapClient smc;

	// 싱글톤 적용
	private static ProdDaoImpl dao;
	
	private ProdDaoImpl() {
		Reader rd;
		try{
			rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
		}catch(IOException e){
			System.out.println("SqlMapClient객체 생성 실패!!!");
			e.printStackTrace();
		}
	}
	
	public static ProdDaoImpl getInstance() {
		if(dao == null){
			dao = new ProdDaoImpl();
		}
		
		return dao;
	}

	@Override
	public List<ProdVO> selectProdAll() {
		List<ProdVO> proList = new ArrayList<ProdVO>();
		
		try {
			proList = smc.queryForList("prod.selectProdAll");
		} catch (SQLException e) {
			System.out.println("SQLException 발생");
			e.printStackTrace();
		}
		
		return proList;
	}

	@Override
	public int insertProd(ProdVO pv) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("prod.insertProd", pv);
			
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
	public int updateProd(ProdVO pv) {
		int cnt = 0;
		
		try {
			cnt = smc.update("prod.updateProd", pv);
		} catch (SQLException e) {
			System.out.println("SQLException 발생");
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int deleteProd(String prod_id) {
		int cnt = 0;
		
		try {
			cnt = smc.delete("prod.deleteProd", prod_id);
		} catch (SQLException e) {
			System.out.println("SQLException 발생");
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public boolean checkProd(String prod_id) {
		boolean chk = false;
		
		try {
			int count = (int) smc.queryForObject("prod.checkProd", prod_id);
			
			if(count > 0){
				chk = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return chk;
	}

	@Override
	public List<ProdVO> selectProd(ProdVO pv) {
		List<ProdVO> proList = new ArrayList<ProdVO>();
		
		try {
			proList = smc.queryForList("prod.selectProd", pv);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return proList;
	}
}

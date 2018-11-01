package zip.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import zip.vo.ZipVO;

public class ZipSearchDao implements ZipSearchDaoInf{
	private static ZipSearchDao zipDao;
	
	private SqlMapClient smc;
	
	// 생성자
	private ZipSearchDao() {
		try {
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static ZipSearchDao getInstance() {
		if(zipDao == null) {
			zipDao = new ZipSearchDao();
		}
		return zipDao;
	}
	
	// 동이름을 매개변수로 받아서 해당 동이름이 포함된 우편번호를
	// 검색하여 리스트로 반환하는 메소드
	@Override
	public List<ZipVO> zipSearchDong(String dong) {
		List<ZipVO> zipList = null;
		
		try {
			zipList = smc.queryForList("zipsearch.getSeachDong", dong);
		} catch (SQLException e) {
			zipList = null;
			e.printStackTrace();
		}
		return zipList;
	}
	
	// 우편번호의 일부분을 매개변수로 받아서 해당 우편번호를
	// 포함하는 자료를 검색하여 List로 반환
	@Override
	public List<ZipVO> zipSearchZipCode(String zipcode) {
		List<ZipVO> zipList = null;
		
		try {
			zipList = smc.queryForList("zipsearch.getSeachCode", zipcode);
		} catch (SQLException e) {
			zipList = null;
			e.printStackTrace();
		}
		return zipList;
	}
	
}


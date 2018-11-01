package member.dao;

import java.io.IOException;
import java.io.Reader;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.MemberVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class MemberDaoImpl implements IMemberDao {
	
	private SqlMapClient smc;
	
	// 싱글톤 적용
	private static MemberDaoImpl dao;
	
	private MemberDaoImpl() {
		Reader rd;
		try{
			rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
		}catch(IOException e){
			System.out.println("SqlMapClient객체 생성 실패!!!");
			e.printStackTrace();
		}
	}
	
	public static MemberDaoImpl getInstance() {
		if(dao == null){
			dao = new MemberDaoImpl();
		}
		
		return dao;
	}

	@Override
	public List<MemberVO> selectMemAll() {
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			memList = smc.queryForList("member.selectMemAll");
		} catch (SQLException e) {
			System.out.println("SQLException 발생");
			e.printStackTrace();
		}
		
		return memList;
	}

	@Override
	public int insertMem(MemberVO mv) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("member.insertMem", mv);
			
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
	public int updateMem(MemberVO mv) {
		int cnt = 0;
		
		try {
			cnt = smc.update("member.updateMem", mv);
		} catch (SQLException e) {
			System.out.println("SQLException 발생");
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int deleteMem(String mem_id) {
		int cnt = 0;
		
		try {
			cnt = smc.delete("member.deleteMem", mem_id);
		} catch (SQLException e) {
			System.out.println("SQLException 발생");
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public boolean checkMem(String mem_id) {
		boolean chk = false;
		
		try {
			int count = (int) smc.queryForObject("member.checkMem", mem_id);
			
			if(count > 0){
				chk = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return chk;
	}

	@Override
	public List<MemberVO> selectMem(MemberVO mv) {
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			memList = smc.queryForList("member.selectMem", mv);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return memList;
	}
	
}

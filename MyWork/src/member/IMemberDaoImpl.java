package member;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.MemberVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class IMemberDaoImpl implements IMemberDao{
	private static IMemberDaoImpl memDao;
	private SqlMapClient smc;
	
	private IMemberDaoImpl(){
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
	
	public static IMemberDaoImpl getInstance(){
		if(memDao == null){
			memDao = new IMemberDaoImpl();
		}
		return memDao;
	}

	@Override
	public int insertMember(MemberVO mv) {
		Object obj = null;
		int cnt = 0;
		
		
		try {
			obj = smc.insert("member.insertMember", mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(obj == null){
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String mem_id) {
		int cnt = 0;
		try {
			cnt = smc.delete("member.deleteMember", mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;
		try {
			cnt = smc.update("member.updateMember", mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		List<MemberVO> memList = new ArrayList<MemberVO>();
		try {
			memList = smc.queryForList("member.getAllMember");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return memList;
	}

	@Override
	public boolean chkMember(String mem_id) {
		boolean chk = false;
		Object obj = null;
		try {
			obj = smc.queryForObject("member.getMember", mem_id);
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
	public List<MemberVO> searchMember(MemberVO mv) {
		List<MemberVO> memList = new ArrayList<MemberVO>();
		try {
			memList = smc.queryForList("member.searchMember", mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memList;
	}

	@Override
	public MemberVO getMember(String mem_id) {
		MemberVO mv = null;
		try {
			mv = (MemberVO) smc.queryForObject("member.getMember", mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	
	
	
	
	
	
}

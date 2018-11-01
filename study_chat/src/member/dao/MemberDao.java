package member.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import member.vo.MemberVO;

public class MemberDao implements IMemberDao {
	
	private static MemberDao dao = new MemberDao();
	
	private SqlMapClient smc;  // ibatis를 실행할 때 필요한 객체 변수
	
	
	// Dao생성자에서 ibatis 실행용 객체를 생성해 준다.
	private MemberDao(){
		
		try {
			Reader rd = 
				Resources.getResourceAsReader("sqlMapConfig.xml");
			
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close(); // Reader객체 닫기
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static MemberDao getInstance(){
		if(dao==null){
			dao = new MemberDao();
		}
		return dao;
	}
	

	// MemberVO객체에 담겨진 자료를 DB에 insert하는 메서드
	@Override
	public Object insertMember(MemberVO memVo) {
		Object obj = "";
		
		try {
			obj = smc.insert("chat_member.insertMember", memVo);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return obj;
	}
	
	// 회원ID를 매개변수로 받아서 해당 회원을 삭제하는 메서드
	@Override
	public int deleteMember(String memId) {
		
		int cnt = 0;
		try {
			cnt = smc.delete("chat_member.deleteMember", memId);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	// MemberVO객체에 담겨진 정보를 이용해 회원정보를 update하는 메서드
	@Override
	public int updateMember(MemberVO memVo) {
		int cnt = 0;
		
		try {
			//cnt = smc.update("mymember.updateMember", memVo);
			cnt = smc.update("chat_member.modifyMember", memVo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}


	// 전체 회원 정보를 DB에서 가져와 List에 담아서 반환하는 메서드
	@Override
	public List<MemberVO> getMemberList() {
		// 데이터를 저장할 List객체 변수 선언
		List<MemberVO> memList = null;
		
		try {
			memList = smc.queryForList("chat_member.selectAllMember");
		} catch (SQLException e) {
			memList = null;
			e.printStackTrace();
		} 
		return memList;
	}

	// 주어진 회원ID가 있는지 여부를 나타내는 메서드
	@Override
	public int getMember(String memId) {
		int count = 0;
		
		try {
			count = 
				(int)smc.queryForObject("chat_member.getMemberCount", memId);
			
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		} 
		return count;
	}

	// 주어진 password가 있는지 여부를 나타내는 메서드
		@Override
		public int getMemberPw(String memPw) {
			int count = 0;
			
			try {
				count = 
					(int)smc.queryForObject("chat_member.getMemberCountPw", memPw);
				
			} catch (SQLException e) {
				count = 0;
				e.printStackTrace();
			} 
			return count;
		}
	
	// 주어진 파라미터 값들을 이용하여 회원을 검색하는 메서드
	@Override
	public List<MemberVO> searchMember(String col, String value, String op) {
		// 검색 결과를 저장할 List형 객체 생성
		List<MemberVO> memList = null;
//		try {
//		} catch (SQLException e) {
//			memList = null;
//			e.printStackTrace();
//		} 
		return memList;
	}

	// 주어진 Map형 파라미터 값을 이용하여 회원을 검색하는 메서드2
	@Override
	public MemberVO searchMember(Map<String, String> param) {
		// 검색 결과를 저장할 List형 객체 생성
		MemberVO member_vo = null;
		
		try {
			member_vo = (MemberVO)smc.queryForObject("chat_member.getFindMember", param);
		} catch (SQLException e) {
			member_vo = null;
			e.printStackTrace();
		} 
		return member_vo;
	}
}


package mvc.service;

import java.util.List;
import java.util.Map;

import mvc.dao.IMemberDao;
import mvc.dao.MemberDao;
import mvc.vo.MemberVO;

public class MemberService implements IMemberService {
	IMemberDao memDao;
	
	/* Singleton : 자기자신 참조값을 가질 변수 선언 */
	private static MemberService service = new MemberService();

	/* Singleton : 생성자를 private으로 설정 */
	private MemberService(){
		memDao = MemberDao.getInstance();
	}
	
	/* Singleton : 참조값을 반환하는 getInstance() 작성 */
	public static MemberService getInstance(){
		if(service == null){
			service = new MemberService();
		}
		return service;
	}
	
	@Override
	public Object insertMember(MemberVO memVO) {
		return memDao.insertMember(memVO);
	}

	@Override
	public int deleteMember(String memId) {
		return memDao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVO) {
		return memDao.updateMember(memVO);
	}

	@Override
	public List<MemberVO> getMemberList() {
		return memDao.getMemberList();
	}

	@Override
	public int getMember(String memId) {
		return memDao.getMember(memId);
	}

	@Override
	public List<MemberVO> searchMember(String col, String value, String op) {
		return memDao.searchMember(col, value, op);
	}


	@Override
	public List<MemberVO> searchMember(Map<String, String> param) {
		return memDao.searchMember(param);
	}

}

package mvc.service;

import java.util.List;

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
	public int insertMember(MemberVO memVO) {
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

}

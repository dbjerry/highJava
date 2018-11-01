package mystudent.service;

import java.util.List;

import mystudent.dao.IStudentDao;
import mystudent.dao.StudentDao;
import mystudent.vo.StudentVO;

public class StudentService implements IStudentService {
	IStudentDao stdDao;
	
	/* Singleton : 자기자신 참조값을 가질 변수 선언 */
	private static StudentService service = new StudentService();

	/* Singleton : 생성자를 private으로 설정 */
	private StudentService(){
		stdDao = StudentDao.getInstance();
	}
	
	/* Singleton : 참조값을 반환하는 getInstance() 작성 */
	public static StudentService getInstance(){
		if(service == null){
			service = new StudentService();
		}
		return service;
	}

	@Override
	public Object insertStd(StudentVO stdVO) {
		return stdDao.insertStd(stdVO);
	}

	@Override
	public List<StudentVO> getStdList() {
		return stdDao.getStdList();
	}

	@Override
	public int getStd(String stdId) {
		return stdDao.getStd(stdId);
	}
	
}

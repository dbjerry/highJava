package mystudent.dao;

import java.util.List;
import java.util.Map;

import mvc.member.vo.MemberVO;
import mystudent.vo.StudentVO;

public interface IStudentDao {
	
	/**
	 * StudentVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param stdVO insert할 자료가 저장된 StudentVO객체
	 * @return	DB작업이 성공하면 1이상의 정수값이 반환되고
	 * 			실패면 0이 반환된다.
	 */
	public Object insertStd(StudentVO stdVO);
	
	/**
	 * DB의 mystudent테이블의 전체 레코드를 가져와서
	 * List에 담아 반환하는 메서드
	 * @return StudentVO객체를 담고 있는 List객체
	 */
	public List<StudentVO> getStdList();
	
	/**
	 * 주어진 학생 name이 존재하는 여부를 알아내는 메서드
	 * @param memId 검색할 학생 name
	 * @return 해당 학생 name이 있으면 1, 없으면 0
	 */
	public int getStd(String stdId);
	
}

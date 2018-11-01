package mystudent.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import mystudent.vo.StudentVO;

public class StudentDao implements IStudentDao {
	
	private static StudentDao dao = new StudentDao();
	
	private SqlMapClient smc;  // ibatis를 실행할 때 필요한 객체 변수
	
	
	// Dao생성자에서 ibatis 실행용 객체를 생성해 준다.
	private StudentDao(){
		
		try {
			Reader rd = 
				Resources.getResourceAsReader("sqlMapConfig.xml");
			
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close(); // Reader객체 닫기
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static StudentDao getInstance(){
		if(dao==null){
			dao = new StudentDao();
		}
		return dao;
	}
	

	@Override
	public Object insertStd(StudentVO stdVo) {
		Object obj = "";
		
		try {
			obj = smc.insert("mystudent.insertstd", stdVo);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return obj;
	}

	@Override
	public List<StudentVO> getStdList() {
		// 데이터를 저장할 List객체 변수 선언
		List<StudentVO> stdList = null;
		
		try {
			stdList = smc.queryForList("mystudent.selectAllstd");
		} catch (SQLException e) {
			stdList = null;
			e.printStackTrace();
		} 
		return stdList;
	}

	@Override
	public int getStd(String stdId) {
		int count = 0;
		
		try {
			count = 
				(int)smc.queryForObject("mystudent.getStdCount", stdId);
			
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		} 
		return count;
	}

}


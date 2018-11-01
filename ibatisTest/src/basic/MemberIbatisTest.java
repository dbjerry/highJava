package basic;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class MemberIbatisTest {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		/* ibatis를 이용하여 DB자료를 처리하는 작업 순서 */
		
		/* 1. ibatis의 환경설정 파일을 읽어와 실행시킨다. */
		try {

			/* 1-1. xml문서 읽어오기 */
			Reader rd = Resources.getResourceAsReader("sqlMapConfig.xml");
			
			/*
			 * 1-2. 위에서 읽어온 Reader객체를 이용하여 실제
			 * 		작업을 수행할 객체를 생성한다. 
			 */
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			//위에서 만든 SqlMapClient객체변수인 smc가 작업할 객체변수가 된다.
			
			rd.close();
			
			
			/*
			 * 2. 실행할 SQL문에 맞는 쿼리문을 호출하여 원하는 작업을 수행한다.
			 */
			while(true){
				System.out.println();
				System.out.println("1. insert  2. delete  3. update  4. allList  5. select  0. OFF");
				System.out.print("입력 >> ");
			
				int choice = scan.nextInt();
				switch(choice){
			
				/* 2-1. insert 작업 */
				case 1:
					System.out.println("insert작업 시작");
					
					/* 저장할 데이터를 구성하여 VO객체에 담는다. */
					System.out.print("회원ID : ");
					String id = scan.next();
					System.out.print("회원이름 : ");
					String name = scan.next();
					System.out.print("회원전화 : ");
					String tel = scan.next();
					System.out.print("회원주소 : ");
					String addr = scan.next();
					
					MemberVO memberVo = new MemberVO();
					memberVo.setMem_id(id);
					memberVo.setMem_name(name);
					memberVo.setMem_tel(tel);
					memberVo.setMem_addr(addr);
					
					/*
					 * SqlMapClient객체 변수를 이용하여 해당 쿼리문을 실행한다.
					 * 형식) smc.insert("namespace값.id값, parameterClass);
					 *		반환값 : 성공하면 null반환, 실패하면 Error객체
					 */
					Object obj = smc.insert("memberTest.insertMember", memberVo);
					
					if(obj == null){
						System.out.println("insert 성공");
					}else{
						System.out.println("insert 실패");
					}
				break;
				
				/* 2-2. delete 작업 */
				case 2:
					System.out.println("delete작업 시작");
					System.out.print("삭제할 회원ID 입력 : ");
					id = scan.next();
					
					int cnt = smc.delete("memberTest.deleteMember", id);
					if(cnt > 0){
						System.out.println("delete 성공");
					}else{
						System.out.println("delete 실패");
					}
				break;
				
				/* 2-3. update 작업 ㅡ> 성공한 레코드 수 */
				case 3:
					System.out.print("회원ID : ");
					id = scan.next();
					System.out.print("새로운 회원이름 : ");
					name = scan.next();
					System.out.print("새로운 회원전화 : ");
					tel = scan.next();
					System.out.print("새로운 회원주소 : ");
					addr = scan.next();
					
					memberVo = new MemberVO();
					memberVo.setMem_id(id);
					memberVo.setMem_name(name);
					memberVo.setMem_tel(tel);
					memberVo.setMem_addr(addr);
					
					/* update의 반환값 ㅡ> 성공한 레코드 수 */
					cnt = smc.update("memberTest.updateMember", memberVo);
					
					if(cnt > 0){
						System.out.println("update 성공");
					}else{
						System.out.println("update 실패");
					}
				break;
					
				/*
				 * 2-4-1. select 작업
				 * select한 결과가 여러개일 경우
				 */
				case 4:
					List<MemberVO> memberList = smc.queryForList("memberTest.selectAllMember");
					/*
					 * select의 처리 결과가 여러개일 경우에는 queryForList()를 이용.
					 * 이 메서드는 여러개의 레코드 각각을 VO에 담은 후
					 * VO데이터들을 자동으로 List에 추가해준다.
					 */
					
					for(MemberVO memVo : memberList){
						System.out.println();
						System.out.println("id : " + memVo.getMem_id());
						System.out.println("name : " + memVo.getMem_name());
						System.out.println("tel : " + memVo.getMem_tel());
						System.out.println("addr : " + memVo.getMem_addr());
					}
				break;
					
				/*
				 * 2-4-2. select 작업
				 * select한 결과가 1개일 경우
				 */
				case 5:
					System.out.println("select작업 시작");
					
					System.out.print("검색할 회원ID 입력 >> ");
					id = scan.next();
					
					/* select한 결과가 1개일 경우에는 queryForObject()를 사용 */
					memberVo = (MemberVO)smc.queryForObject("memberTest.selectMember", id);
					
					System.out.println();
					System.out.println("id : " + memberVo.getMem_id());
					System.out.println("name : " + memberVo.getMem_name());
					System.out.println("tel : " + memberVo.getMem_tel());
					System.out.println("addr : " + memberVo.getMem_addr());
					
				break;
				
				case 0:
					System.exit(1);
				}
			}
		} catch (IOException e) {
			// TODO: handle exception
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

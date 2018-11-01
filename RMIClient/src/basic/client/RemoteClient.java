package basic.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import basic.inf.RemoteInterface;
import basic.vo.FileInfoVO;
import basic.vo.TestVO;

/*
	클라이언트쪽의 프로젝트에도 Interface가 있는 패키지 구조와
	VO객체가 있는 패키지 구조가 똑같이 있어야 한다.
*/
public class RemoteClient {

	public static void main(String[] args) {
		try {
			// 서버에서 제공하는 서비스를 찾기 위해 Registry객체 생성
			// 서버의 IP주소와 포트번호를 지정한다.
			Registry reg = LocateRegistry.getRegistry("localhost", 8888);
			
			// 서버쪽에서 등록한 '객체의Alias'를 이용하여
			// 사용할 객체를 구한다.
			RemoteInterface inf = (RemoteInterface) reg.lookup("server");
			
			
			// 이제부터는 불러온 객체의 메서드를 호출해서 사용할 수 있다.
			int a = inf.doRmotePrint("안녕하세요");
			System.out.println("반환값 : " + a);
			System.out.println("------------------------");
			
			
			ArrayList<String> list = new ArrayList<>();
			
			list.add("복숭아");
			list.add("포도");
			list.add("딸기");
			list.add("대추");
			inf.doPrintList(list);
			System.out.println("doPrintList()메서드 호출 끝.");
			
			TestVO vo = new TestVO();
			vo.setTestId("홍길동");
			vo.setTestNum(1234);
			inf.doPrintVo(vo);
			System.out.println("doPrintVo()메서드 호출 끝...");
			
			// 파일 전송 예제 시작 ----------
			
			// 전송할 파일 정보를 갖는 File객체 생성
			File file = new File("d:/D_Other/국화.jpg");
			
			if(!file.exists()) {
				System.out.println("전송할 파일이 없습니다.");
				return;
			}
			
			// FileInfoVO객체를 구성하여 전송한다.
			int len = (int)file.length();  // 파일 크기
			FileInputStream fin = new FileInputStream(file);
			byte[] data = new byte[len];
			
			fin.read(data);  // 파일 내용을 읽어서 byte배열에 저장
			
			// FileInfoVO객체를 생성해서 내용을 구성한다.
			FileInfoVO fVo = new FileInfoVO();
			
			fVo.setFileName(file.getName());
			fVo.setFileData(data);
			
			// 서버에 파일을 저장할 메서드를 호출한다.
			inf.setFile(fVo);
			
			System.out.println("파일 전송 끝...");
			// 파일 전송 예제 끝...
		
			
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

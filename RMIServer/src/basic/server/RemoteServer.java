package basic.server;
/*
	RMI에서 메서드를 제공하는 객체는 UnicastRemoteObject를 상속하고
	작성해 놓은 인터페이스를 구현하여 작성한다.
*/

import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import basic.inf.RemoteInterface;
import basic.vo.FileInfoVO;
import basic.vo.TestVO;

public class RemoteServer extends UnicastRemoteObject implements RemoteInterface {

	// 생성자도 RemoteException을 throws해야 한다.
	public RemoteServer() throws RemoteException {}

	public static void main(String[] args) {
		try {
			// 클라이언트가 RMI기술을 사용하여 이 객체에서 제공하는
			// 메서드를 사용할 수 있는 환경 설정하기
			
			// 1) 인터페이스를 구현한 객체 생성
			RemoteInterface inf = new RemoteServer();
			
			// 2) 클라이언트가 구현한 객체를 찾을 수 있도록 하는
			//    Registry 객체를 생성한다. (포트번호 지정)
			//    포트번호를 생략하면 기본값이 1099가 된다.
			Registry reg = LocateRegistry.createRegistry(8888);
			
			// 3) 생성된 Registry에 서비스를 제공할 객체를 등록한다.
			//   형식) rebind("객체의Alias", 객체변수)
			//        객체의 Alias는 클라이언트에서 찾을 이름이 된다.
			reg.rebind("server", inf); 
			
			System.out.println("서버가 준비되었습니다.");
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		

	}

	// 메서드들 구현하기
	
	// 메서드의 매개변수 값들은 클라이언트에서 보내오는 데이터고
	// 반환값은 서버에서 메서드를 처리한 결과를 클라이언트로
	// 보내는 값이 된다.
	@Override
	public int doRmotePrint(String str) throws RemoteException {
		System.out.println("클라이언트에서 보내온 내용 : " + str);
		System.out.println("doRemotePrint()메서드를 마칩니다.");
		
		return 100;
	}

	@Override
	public void doPrintList(ArrayList<String> list) throws RemoteException {
		System.out.println("List값 출력 시작...");
		for(String str : list) {
			System.out.println(str);
		}
		System.out.println("List값 출력 끝...");
		
	}

	@Override
	public void doPrintVo(TestVO vo) throws RemoteException {
		System.out.println("TestVO의 값 출력");
		System.out.println("ID : " + vo.getTestId());
		System.out.println("Num : " + vo.getTestNum());
		System.out.println("TestVO 출력 끝...");
		
	}
	
	
	// 클라이언트가 보내온 파일 정보를 이용하여
	// 서버쪽에 그 파일을 저장하는 메서드
	@Override
	public void setFile(FileInfoVO fileVo) throws RemoteException {
		FileOutputStream fout = null;
		String dir = "d:/D_Other/temp/";  // 저장할 폴더 지정
		
		try {
			// 저장할 폴더와 파일명을 연결하여 FileOutputStream객체 생성
			fout = new FileOutputStream(dir + fileVo.getFileName());
			
			// 클라이언트에서 전달한 파일데이터(byte[])를
			// 저장한다.
			fout.write(fileVo.getFileData());
			
			fout.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("파일 저장 완료...");
		
	}

}










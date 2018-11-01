package basic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 지정된 디렉토리에 포함된 파일과 디렉토리 목록 보기 예제
 */
public class FileTest02 {
	
	public void start(){
		File testFile = new File("C:/1_ddit/D_Other");
		displayFileList(testFile);
	}
	
	/**
	 * 지정된 디렉토리에 포함된 파일과 디렉토리 목록을 보여주는 메서드
	 * 이 메서드는 '디렉토리'정보를 가리키는 File객체를 매개변수로 받는다.
	 */
	public void displayFileList(File dir){
		System.out.println("[" + dir.getAbsolutePath() + "] 디렉토리 내용");
		
		File[] files = dir.listFiles();
		
		/* 목록 배열 중 디렉토리가 들어있는 첨자를 저장할 리스트 선언 */
		List<Integer> subList = new ArrayList<Integer>();
		
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd a HH:mm");
		
		for(int i = 0; i < files.length; i++){
			String fileName = files[i].getName();
			
			String attr = "";	//	파일의 속성(읽기, 쓰기, 히든, 디렉토리 구분)
			String size = "";	//	파일 용량을 나타내는 변수
			
			if(files[i].isDirectory()){
				attr = "<DIR>";
				subList.add(i);
			}else{
				size = files[i].length() + "";
				attr = files[i].canRead() ? "R" : "";
				attr += files[i].canWrite() ? "W" : "";
				attr += files[i].isHidden() ? "H" : "";
			}
			System.out.printf("%s %5s %12s %s\n",
					df.format(new Date(files[i].lastModified())), attr, size,
					fileName);
		
		}// for
		
		int dirNum = subList.size();	//	폴더 안에 있는 디렉토리 개수
		int fileNum = files.length - dirNum;	//	파일 개수
		
		System.out.println(fileNum + "개의 파일, " + dirNum + "개 디렉토리");
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		System.out.println();
		
		
		for(int i = 0; i < subList.size(); i++){
			displayFileList(files[subList.get(i)]);	//	재귀호출
		}
	}
	
	public static void main(String[] args) {
		new FileTest02().start();
	}
}


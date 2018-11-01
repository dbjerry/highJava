package basic;

import java.io.File;

public class FileTest01 {
	public static void main(String[] args) {
		/**
		 *	File객체 만들기 연습
		 * 
		 *	1.	new File(String파일 또는 경로명)
		 * 		디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의
		 * 		구분 문자는 '￦'를 사용하거나 '/'를 사용할 수 있다.
		 */
		File file = new File("C:/1_ddit/D_Other/test01.txt");
		
		System.out.println(file.getName() + "은 ");
		System.out.println("파일? : " + file.isFile());
		System.out.println("디렉토리 ? : " + file.isDirectory());
		
		
		File file2 = new File("C:/1_ddit/D_Other");
		
		System.out.println(file2.getName() + "은 ");
		System.out.println("파일? : " + file2.isFile());
		System.out.println("디렉토리 ? : " + file2.isDirectory());
		System.out.println();
		
		
		/**
		 *	2.	new File(File parent, String child)
		 * 		parent디렉토리 안에 있는 child파일을 나타낸다.
		 */
		File file3 = new File(file2, "test01.txt");
		
		System.out.println(file3.getName() + "의 크기 : " + file3.length() + " bytes");
		System.out.println();
		
		
		/**
		 *	3.	new File(String parent, String child)
		 *		parent디렉토리 안에 있는 child파일을 나타낸다.
		 */
		File file4 = new File("C:/1_ddit/D_Other", "test01.txt");
		
		System.out.println(file4.getName() + "의\nPath : " + file4.getPath());
		System.out.println("AbsolutePath : " + file4.getAbsolutePath());
		System.out.println();
		
		
		File file5 = new File(".", "test01.txt");
		
		System.out.println(file5.getName() + "의\nPath : " + file5.getPath());
		System.out.println("AbsolutePath : " + file5.getAbsolutePath());
		System.out.println();
		
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		
		/**
		 * 디렉토리(폴더) 만들기
		 * 
		 * mkdir()	파일 객체의 경로 중 마지막 위치의 디렉토리를 만든다.
		 * 			중간의 경로가 모두 미리 만들어져 있어야 한다.
		 * 			디렉토리 만들기가 성공하면 true, 실패하면 false 반환
		 * 
		 * mkdirs()	중간의 경로가 없으면 부족한 경로도 새롭게 생성 후
		 * 			마지막 위치의 디렉토리를 만든다.
		 */
		
		/* mkdir() */
		File file6 = new File("C:/1_ddit/D_Other/Test");
		
		if(!file6.exists()){	//	해당 디렉토리가 있는지 검사
			if(file6.mkdir()){	//	만들기 성공이면 true, 실패하면 false
				System.out.println(file6.getName() + "만들기 성공!");
			}else{
				System.out.println(file6.getName() + "만들기 실패.");
			}
		}
		System.out.println();
		
		
		/* mkdirs() */
		File file7 = new File("C:/1_ddit/D_Other/temp/java/src");
		
		if(file7.mkdirs()){	//	만들기 성공이면 true, 실패하면 false
			System.out.println(file7.getName() + "만들기 성공!");
		}else{
			System.out.println(file7.getName() + "만들기 실패.");
		}
		
		
		
		
		
		
	}
}

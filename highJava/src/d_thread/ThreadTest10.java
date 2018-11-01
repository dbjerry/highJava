package d_thread;
/**
 * 3개의 Thread가 각각 알파벳을 A-Z까지 출력하는데
 * 출력을 끝낸 순서대로 결과를 나타내는 프로그램 작성하기
 */
public class ThreadTest10 {
	
	/* 쓰레드의 이름을 출력이 끝난 순서대로 저장하는 변수 */
	public static String strRank = "";
	
	public static void main(String[] args) {
		DisplayCharacter[] dis = new DisplayCharacter[]{
				new DisplayCharacter("김지태"),
				new DisplayCharacter("박진"),
				new DisplayCharacter("박찬배")
		};
		
		/* Thread Start */
		for (int i = 0; i < dis.length; i++) {
			dis[i].start();
		}
		
		/* 모든 Thread가 끝날 때까지 기다린다. */
		for(DisplayCharacter display : dis){
			try{
				display.join();
			}catch(Exception e){}
		}
		System.out.println();
		System.out.println("경기결과");
		System.out.println("순위 : " + strRank);
	}
}


/* A-Z까지 출력하는 Thread */
class DisplayCharacter extends Thread{
	private String name;	//	이름이 저장될 변수

	/* 생성자 */
	public DisplayCharacter(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		for(char c = 'A'; c <= 'Z'; c++){
			System.out.println(name + "의 출력 문자 : " + c);
			try{
				Thread.sleep((int)(Math.random()*(500-201+1
						)+201));
			}catch(Exception e){
				
			}
		}
		System.out.println(name + "출력 끝.");
		ThreadTest10.strRank += name + " ";
	}
	
}

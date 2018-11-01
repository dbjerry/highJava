package programmers;

public class MusicBox {
	/* 3개의 메소드를 가지고 있다. */
	public synchronized void playMusicA(){
		for (int i = 0; i < 10; i++) {
			System.out.println("신나는 음악");
			
			try{
				Thread.sleep((int)(Math.random()*1000));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public synchronized void playMusicB(){
		for (int i = 0; i < 10; i++) {
			System.out.println("슬픈 음악");
			
			try{
				Thread.sleep((int)(Math.random()*1000));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public void playMusicC(){
		for (int i = 0; i < 10; i++) {
			synchronized (this) {
				System.out.println("카페 음악");
			}
			
			try{
				Thread.sleep((int)(Math.random()*1000));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}

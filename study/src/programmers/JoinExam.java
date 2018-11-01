package programmers;

public class JoinExam {
	public static void main(String[] args) {
		MyThread5 thread = new MyThread5();
		thread.start();
		
		System.out.println("시작");
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("종료");
		
		
	}
}

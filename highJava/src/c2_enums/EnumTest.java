package c2_enums;
/**
 * 열거형	ㅡ>	서로 관련있는 상수들의 집합
 * 
 * <열거형 객체 사용하기>
 * - name()		ㅡ>	열거형 상수의 이름을 문자열로 반환한다.
 * - ordinal()	ㅡ>	열거형 상수의 정의된 순서값을 반환한다.
 * 					(0부터 시작)
 * - valueOf("enum상수명")	ㅡ>	지정된 열거형에서 '열거형상수명'과
 * 								일치하는 열거형 상수를 반환한다.
 * 								'enum명.상수명'과 같은 역할을 수행한다.
 * 
 * <열거형 객체 선언 방법>
 * enum 열거형명 { 상수명1, 상수명2, ...};
 * 
 */
public class EnumTest {
	
//	final int RED = 1;
//	final int BLUE = 2;
	
//	final int SEOUL = 1;
//	final int DAEJEON = 2;
	
	// City 열거형 객체 선언
	public enum City {SEOUL, BUSAN, DAEGU, KWANGJU, DAEJEON};
	
	// Color 열거형 객체 선언
	public enum Color {RED, BLUE, GREEN, YELLOW, ORANGE, PINK, WHITE, BLACK};
	
	// 상수에 다른 데이터값을 정해줄 경우의 열거형 만들기
	/* 열거형에 멤버 추가하기
	 *  */
	public enum Season {
		
		SPRING("3to5"),
		SUMMER("6to8"),
		FALL("9to11"),
		WINTER("12to2");
		
		/*	데이터값이 저장될 멤버변수 선언	*/
		private String span;
		
		/* 열거형 생성자를 만든다	<ㅡ	생성자는 묵시적으로 private이다. */
		Season(String span){
			this.span = span;
		}
		
		/**
		 * 지정한 데이터값을 외부에서 사용할 메서드 만들기
		 * getter메서드를 만든다.
		 */
		public String getSpan(){
			return span;
		}
	}
	
	public static void main(String[] args) {
		
		// City 열거형에서 '서울' 상수값 가져오기
		City city1 = City.SEOUL;	//ㅡㅡㅡㅡㅡ	열거형명.상수명
		System.out.println("city1 : " + city1.name());
		System.out.println("ordinal : " + city1.ordinal());
		System.out.println();
		
		// City 열거형에서 '대구' 상수값 가져오기
		City city2 = City.valueOf("DAEGU");
		System.out.println("city2 : " + city2.name());
		System.out.println("ordinal : " + city2.ordinal());
		System.out.println();
		
		
		// if(City.SEOUL == Color.RED){}	<ㅡ	error
		
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		
		Season ss = Season.valueOf("SPRING");
		System.out.println("name : " + ss.name());
		System.out.println("ordinal : " + ss.ordinal());
		System.out.println("span : " + ss.getSpan());
		System.out.println();
		
		/**
		 * 열거형 자료 전체를 가져와 처리하기
		 * 열거형명.values()	ㅡ>	상수값들을 배열로 가져온다.
		 */
		for(Season time : Season.values()){
			System.out.println(time + " : " + time.getSpan());
		}
		
		System.out.println();
		
		for(City temp : City.values()){
			System.out.println(temp + " : " + temp.ordinal());
		}
	}
}

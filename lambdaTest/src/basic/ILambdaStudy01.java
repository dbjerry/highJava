package basic;

@FunctionalInterface
public interface ILambdaStudy01 {
	
	/**
	 * no return, no parameter
	 */
	public void study();
	
}

@FunctionalInterface
interface ILambdaStudy02 {
	
	/**
	 * no return, 문자열을 받는 메서드
	 * @param a 문자열을 받는 parameter, 자료형 String
	 */
	public void study(String a);
	
}

@FunctionalInterface
interface ILambdaStudy03 {
	
	/**
	 * 2 parameter, 문자열을 받는 메서드
	 * @param a 문자열 인자를 받는 매개변수, 자료형 String
	 * @param b 문자열 인자를 받는 매개변수, 자료형 String
	 * @return 문자열을 return(반환값)
	 */
	public String study(String a, String b);
	
}

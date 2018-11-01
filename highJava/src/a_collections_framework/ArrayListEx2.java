package a_collections_framework;

import java.util.ArrayList;
import java.util.List;

public class ArrayListEx2 {
	public static void main(String[] args) {
		
		// 자르고자 하는 글자의 개수를 지정한다.
		final int LIMIT = 10;
		
		String source = "0123456789abcdefghijABCDEFGHIJ!@#$%^&*()ZZZ";
		
		int length = source.length();
		
		List list = new ArrayList(length/LIMIT + 10);	//	<ㅡ 크기를 약간 여유있게 잡는다.
		/*
		 * 생성할 때 지정한 크기보다 더 많은 객체를 저장하면 자동적으로
		 * 크기가 늘어나기는 하지만 이 과정에서 처리시간이 많이 소요되기 때문이다.
		 */
		
		for(int  i = 0; i < length; i += LIMIT){
			if(i+LIMIT < length){
				list.add(source.substring(i, i+LIMIT));
			}else{
				list.add(source.substring(i));
			}
		}
		
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i));
		}
	}
}

package a_collections_framework;

import java.util.LinkedList;

public class StackQueueTest {

	public static void main(String[] args) {

		/*
		   Stack --> LIFO 자료구조

		   Queue --> FIFO 자료구조
		   
		   LinkedList를 이용하여 Stack과 queue를 처리할 수 있다.

		   Stack의 명령어

		   1. 자료 입력 : push(입력할 값)

		   2. 자료 출력 : pop() -> 자료를 꺼내온 후 꺼낸 자료를 Stack에서 삭제 
		*/

		LinkedList<String> stack = new LinkedList<String>();

		stack.push("박진");
		stack.push("박찬배");
		stack.push("김지탠");
		stack.push("오줌싸개");

		System.out.println("현재 stack값 : " + stack);


		String data = stack.pop();
		System.out.println("꺼내온 자료 : " + data);
		System.out.println("현재 stack값 : " + stack);

		
		System.out.println("꺼내온 자료 2: " + stack.pop());
		System.out.println("현재 stack값 : " + stack);


		stack.push("성춘향");
		System.out.println("현재 stack값 2 : " + stack);
		System.out.println("───────────────────────────────────────────────");

		
		/*
		   queue의 명령

		   1. 자료 입력 : offer(입력할 값)
		   2. 자료 출력 : poll() -> 자료를 꺼내온 후 꺼내온 자료흫 queue에서 삭제
		*/

		LinkedList<String> queue = new LinkedList<String>();


		queue.offer("박진");
		queue.offer("박찬배");
		queue.offer("김지태");
		queue.offer("오줌싸개");

		System.out.println("현재 queue값 : " + queue);

		
		data = queue.poll();
		System.out.println("꺼내온 자료 : " + data);
		System.out.println("현재 queue값 : " + queue);

		
		System.out.println("꺼내온 자료 2: " + queue.pop());
		System.out.println("현재 queue값 : " + queue);

		
		queue.offer("성춘향");
		System.out.println("현재 queue값 2 : " + queue);

	}
}
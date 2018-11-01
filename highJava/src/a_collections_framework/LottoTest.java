package a_collections_framework;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LottoTest {
	public static void main(String[] args) {
		
		System.out.println("┌────────────────┬────────────────┐");
		System.out.println("│                │     [NEWS]     │");
		System.out.println("│    WELCOME     │   184회차    2등     │");
		System.out.println("│    TO LOTTO    │   256회차    2등     │");
		System.out.println("│    WORLD!      │   319회차    1등     │");
		System.out.println("│                │                │");
		System.out.println("└────────────────┴────────────────┘\n\n");
		
		Scanner scan = new Scanner(System.in);
		
		while(true){
			
			System.out.print("구매하실 게임의 수량을 적어주세요 > ");
			int number = scan.nextInt();
			
			System.out.print("보유하신 금액을 게임의 수량에 맞게 적어주세요 > ");
			int money = scan.nextInt();
			
			int total = number * 1000;
			
			if(money-total<0){
				System.out.println("보유하신 금액과 설정하신 게임수를 다시 확인해주세요.");
				continue;
			}else{
				money -= total;
				System.out.println("총" + number + "게임을 구매하셨습니다.\n구매 후 남은 잔액 : " + money + "입니다.");
			}
		
			Set<Integer> lotto = new HashSet<Integer>();
			for(int i = 0; i < number; i++){
				while(lotto.size() < 6){
					int num = (int)(Math.random()*45+1);
					lotto.add(num);
				}
				System.out.println(lotto);
				lotto.clear();
			}
			break;
		}
	}
}

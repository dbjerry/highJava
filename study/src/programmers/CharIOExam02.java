package programmers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class CharIOExam02 {
	public static void main(String[] args) {
		
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try{
			
			br = new BufferedReader(new FileReader("src/programmers/CharIOExam02.java"));
			pw = new PrintWriter(new FileWriter("test.txt"));
//			pw = new PrintWriter("test.txt");
			String line = null;
			
			while((line = br.readLine()) != null){
				pw.println(line);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			pw.close();
			try{
				br.close();
			}catch(Exception e){}
			
		}
	}
}

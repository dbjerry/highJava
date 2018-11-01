package a_collections_framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdk.nashorn.internal.ir.SetSplitState;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.CompareGenerator;

/*
 *문제)	학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를
 *		멤버변수로 갖는 Student클래스를 만든다.
 *	    
 *		생성자에서는 학번, 이름 국어점수, 영어점수, 수학점수만
 *		매개변수로 받아서 초기화한다.
 *	    
 *		이 Student객체들은 List에 저장하여 관리한다.
 *		List에 저장된 데이터들을 학번의 오름차순으로 정렬하여
 *		출력하는 부분과 총점의 역순으로 정렬하는 부분을
 *		프로그램하시오
 *		(총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
 *	    
 *		(학번의 오름차순 정렬 기능은 Student에 기본 기능으로
 *		구현하고 총점의 역순 정렬은 외부 정렬 기준으로 처리하시오.)
 */
public class StudentTest{
	
	//등수를 구하는 메서드
	public void setRanking(List<Student> stList) {
		for(Student std : stList){
			int rank = 1;
			for(Student std2 : stList){
				if(std.getTotalScore() < std2.getTotalScore()){
					rank++;
				}
			}
			std.setRank(rank);
		}
	}
	
	public static void main(String[] args) {
		
		StudentTest stdTest = new StudentTest();
		ArrayList<Student> studentList = new ArrayList<Student>();
		
		studentList.add(new Student(180420301, "유민지", 76, 54, 43));
		studentList.add(new Student(180420302, "이소형", 79, 62, 79));
		studentList.add(new Student(180420303, "변찬우", 84, 71, 57));
		studentList.add(new Student(180420304, "김지태", 79, 36, 19));
		studentList.add(new Student(180420305, "박   진", 94, 79, 87));
		studentList.add(new Student(180420306, "박찬배", 89, 45, 66));
		studentList.add(new Student(180420307, "김효민", 86, 38, 24));
		studentList.add(new Student(180420308, "김종훈", 82, 80, 89));
		studentList.add(new Student(180420309, "임규승", 74, 77, 55));
		studentList.add(new Student(180420310, "박동주", 73, 51, 49));
		
		// 등수를 구하는 메서드 호출
		stdTest.setRanking(studentList);
		
		Collections.shuffle(studentList);
		System.out.println("데이터를 셔플하였습니다.");
		for(Student student : studentList){
			System.out.println(student);
		}
		
		Collections.sort(studentList);
		System.out.println("\n학번을 기준으로 오름차순 정렬하였습니다.");
		for(Student student : studentList){
			System.out.println(student);
		}
		
		Collections.sort(studentList, new Descending());
		
		System.out.println("\n총점을 기준으로 내림차순 정렬하였습니다.\n(총점이 같을 시 학번이 기준되어 내림차순 정렬합니다.)");
		for(Student student : studentList){
			System.out.println(student);
		}
		
	}
}


class Student implements Comparable<Student>{
	
	private int studentNum;
	private String studentName;
	private int korScore;
	private int engScore;
	private int mathScore;
	private int totalScore = korScore + engScore + mathScore;
	private int rank;
	
	public Student(int studentNum, String studentName, int korScore, int engScore, int mathScore) {
		super();
		this.studentNum = studentNum;
		this.studentName = studentName;
		this.korScore = korScore;
		this.engScore = engScore;
		this.mathScore = mathScore;
		this.totalScore = korScore + engScore + mathScore;
	}
	
	public int getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getKorScore() {
		return korScore;
	}

	public void setKorScore(int korScore) {
		this.korScore = korScore;
	}

	public int getEngScore() {
		return engScore;
	}

	public void setEngScore(int engScore) {
		this.engScore = engScore;
	}

	public int getMathScore() {
		return mathScore;
	}

	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}

	public int getTotalScore() {
		return totalScore;

	
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank){
		this.rank = rank;
	}
	
	@Override
	public int compareTo(Student student) {
		return this.studentNum - ((Student)student).studentNum;
	}

	@Override
	public String toString() {
		return "Student [studentNum=" + studentNum + ", studentName="
				+ studentName + ", totalScore="
				+ totalScore + ", rank=" + rank + "]";
	}
}


class Descending implements Comparator<Student>{

	@Override
	public int compare(Student student1, Student student2) {
		Integer value = new Integer(student1.getTotalScore()).compareTo(student2.getTotalScore()) * -1;
		if(value == 0){
			return new Integer(student1.getStudentNum()).compareTo(student2.getStudentNum()) * -1;
		}
		return value;
	}
}


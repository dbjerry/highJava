package a_collections_framework;

import java.util.HashSet;
import java.util.Set;

public class EqualHashcodeTest {
	public static void main(String[] args) {
		
		Person person1 = new Person();
		person1.setId(1);
		person1.setName("김지태");
		
		Person person2 = new Person();
		person2.setId(1);
		person2.setName("김지태");
		
		System.out.println(person1.equals(person2));
		
		
		Set<Person> set = new HashSet<Person>();
		set.add(person1);
		set.add(person2);
		System.out.println(set.size());
		
		System.out.println(person1);
		System.out.println(person2);
	}
}

/**
	-	equals()는 두 객체의 내용이 같은지 비교 (동등성)
	-	hashCode()는 두 객체가 같은 객체인지 비교 (동일성)
	
	->	HashSet, HashTable, HashMap과 같은 객체들을 사용하는 경우
		객체의 의미상의 동등성을 비교하기 위해서 hashCode()를 호출한다.
		
		
	-	equals()와 hashCode()에 관한 규칙
	
	1.	두 객체가 같으면 equals()를 호출했을 때 true를 반환해야 한다.
		즉, a, b가 같으면 a.equals(b)와 b.equals(a) 둘 다 true여야 한다.
	
	2.	두 객체가 같으면 반드시 같은 hashCode()를 가져야 한다.
	
	3.	두 객체의 hashCode()가 같다고 해서 반드시 같은 것은 아니다.
		하지만 두 객체가 같으면 두 hashCode()는 반드시 같아야 한다.
	
	4.	equals()를 Override하면 반드시 hashCode()도 Override해야 한다.
	
	5.	a.equals(b)가 true이면 a.hashCode() == b.hashCode()도 성립한다.
		하지만 a.hashCode() == b.hashCode() 이 성립해도 a.equals(b)가
		반드시 true인 것은 아니다.
		
	-	hashCode()에서 사용하는 '해싱 알고리즘'에서는 서로 다른
		객체들에 대해 같은 hashCode값을 만들어 낼 수 있기 때문에
		객체가 같지 않더라도 hashCode는 같을 수 있다.
 */


class Person{
	
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	
	// id값과 name값이 모두 같으면 true로 처리하기
	/*
	@Override
	public boolean equals(Object obj) {
		if(obj==null){		// null 여부 검사
			return false;
		}
		
		// 같은 종류의 class인지 검사
		if(this.getClass()!=obj.getClass()){
			return false;
		}
		
		if(this==obj){
			return true;
		}
		
		Person temp = (Person) obj;
		if(this.name==null && temp.name!=null){
			return false;
		}
		
		if(this.id==temp.id && this.name.equals(temp.name)){
			return true;
		}
		
		return false;
	}
	*/
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
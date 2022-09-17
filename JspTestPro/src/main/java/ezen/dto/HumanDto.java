package ezen.dto;

public class HumanDto {

	private String name = "";
	private int age = 0;
	private String welcomeSay = "";
	
	public HumanDto() {
		super();
	}

	public HumanDto(String name, int age, String welcomeSay) {
		super();
		this.name = name;
		this.age = age;
		this.welcomeSay = welcomeSay;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getWelcomeSay() {
		return welcomeSay;
	}

	public void setWelcomeSay(String welcomeSay) {
		this.welcomeSay = welcomeSay;
	}

	@Override
	public String toString() {
		return "HumanDto [name=" + name + ", age=" + age + ", welcomeSay=" + welcomeSay + "]";
	}

}

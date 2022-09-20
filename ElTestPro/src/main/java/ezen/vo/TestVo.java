package ezen.vo;

public class TestVo {

	private String title = "";
	private int num = 0;
	
	public TestVo() {
		super();
	}

	public TestVo(String title, int num) {
		super();
		this.title = title;
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "TestVo [title=" + title + ", num=" + num + "]";
	}
	
}

package ezen.vo;

public class ClassVo {

	private String className = "";
	private int classNum = 0;
	
	public ClassVo() {
		super();
	}

	public ClassVo(String className, int classNum) {
		super();
		this.className = className;
		this.classNum = classNum;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getClassNum() {
		return classNum;
	}

	public void setClassNum(int classNum) {
		this.classNum = classNum;
	}

	@Override
	public String toString() {
		return "ClassVo [className=" + className + ", classNum=" + classNum + "]";
	}

	
}

package spms.dto;

import java.util.Date;

//MVC패턴중 MODEL(9.16)
public class MemberDto {

	//변수 생성(DB와 연동할 변수, 즉 테이블의 속성 만큼 생성)
	//테이블 속성과 순서를 반드시 맞춰서 작성하자 
	private int no = 0;
	private String name = "";
	private String email = "";
	private String password = "";
	private Date createDate = null;
	private Date modifiedDate = null;
	
	//우클릭 -> source -> generate construct using field 전체 해제(디폴트 생성자)
	//단축키 alt+shift+s에서 선택
	public MemberDto() {
		super();
	}

	//우클릭 -> source -> generate construct using field (select all)
	public MemberDto(int no, String name, String email
			, String password, Date createDate, Date modifiedDate) {
		super();
		this.no = no;
		this.name = name;
		this.email = email;
		this.password = password;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
	}
		
	public MemberDto(int no, String name, String email, Date createDate) {
		super();
		this.no = no;
		this.name = name;
		this.email = email;
		this.createDate = createDate;
	}

	//우클릭 -> source -> getter/setter 전체 선택
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	//우클릭 -> source -> toString() 
	@Override
	public String toString() {
		return "MemberDto [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", createDate=" + createDate + ", modifiedDate=" + modifiedDate + "]";
	}
	
	
}

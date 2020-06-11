package edu.spring.ex04.domain;

public class MemberVO {
	private String userid;
	private String password;
	private String email;
	private String active; // 계정의 활성화 유무 - 휴면계정처리
	
	// 생성자
	public MemberVO() {}
	public MemberVO(String userid, String password, String email, String active) {
		super();
		this.userid = userid;
		this.password = password;
		this.email = email;
		this.active = active;
	}
	// end 생성자
	
	// getter, setter
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	// end getter, setter
	
	@Override
	public String toString() {
		String str = "아이디 : " + userid + "\n"
				+ "비밀번호 : " + password + "\n"
				+ "이메일 : " + email + "\n"
				+ "계정활성화 유/무 : " + active;
		return str;
	} // end toString()
	
} // end MemberVO
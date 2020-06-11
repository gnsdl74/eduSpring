package edu.spring.ex03.domain;

import java.util.Date;

public class BoardVO {
	private int bno;
	private String title;
	private String content;
	private String userid;
	private Date regdate;
	private int replycnt;
	
	// 생성자
	public BoardVO() {}
	public BoardVO(int bno, String title, String content, String userid, Date regdate, int replycnt) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.userid = userid;
		this.regdate = regdate;
		this.replycnt = replycnt;
	}
	// end 생성자
	
	// getter, setter
	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	public int getReplycnt() {
		return replycnt;
	}
	
	public void setReplycnt(int replycnt) {
		this.replycnt = replycnt;
	}
	// end getter, setter
	
	@Override
	public String toString() {
		String str = "번호 : " + bno + "\n"
				+ "제목 : " + title + "\n"
				+ "내용 : " + content + "\n"
				+ "아이디 : " + userid + "\n"
				+ "작성시간 : " + regdate + "\n"
				+ "댓글갯수 : " + replycnt;
		return str;
	} // end toString()
	
} // end BoardVO

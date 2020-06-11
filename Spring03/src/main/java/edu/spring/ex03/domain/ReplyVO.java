package edu.spring.ex03.domain;

import java.util.Date;

public class ReplyVO {
	private int rno;
	private int bno;
	private String content;
	private String replyid;
	private Date regdate;
	
	// 생성자
	public ReplyVO() {}
	public ReplyVO(int rno, int bno, String content, String replyid, Date regdate) {
		super();
		this.rno = rno;
		this.bno = bno;
		this.content = content;
		this.replyid = replyid;
		this.regdate = regdate;
	}
	// end 생성자
	
	// getter, setter
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReplyid() {
		return replyid;
	}
	public void setReplyid(String replyid) {
		this.replyid = replyid;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	// end getter, setter
	
	@Override
	public String toString() {
		String str = "댓글 번호 : " + rno + "\n"
				+ "게시글 번호 : " + bno + "\n"
				+ "댓글 내용 : " + content + "\n"
				+ "댓글 작성자 아이디 : " + replyid + "\n"
				+ "댓글 작성 시간 : " + regdate;
		return str;
	} // end toString()
	
} // end ReplyVO
package edu.spring.ex01.domain;

public class ProductVO {
	// 멤버 변수, 필드, 프로퍼티
	private String pname;
	private int price;
	
	// 생성자
	public ProductVO() {}
	public ProductVO(String pname, int price) {
		super();
		this.pname = pname;
		this.price = price;
	}
	// end 생성자
	
	// getter, setter
	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	// end getter, setter
	
} // end ProductVO

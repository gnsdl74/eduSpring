package edu.spring.ex02.service;

import java.util.List;

import edu.spring.ex02.domain.MemberVO;

public interface MemberService {
	
	int create(MemberVO vo);
	List<MemberVO> read();
	MemberVO read(String userid);
	int update(MemberVO vo);
	int delete(String userid);
	int deregister(String userid);
	
} // end interface MemberService







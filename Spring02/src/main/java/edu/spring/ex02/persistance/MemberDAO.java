package edu.spring.ex02.persistance;

import java.util.List;

import edu.spring.ex02.domain.MemberVO;

public interface MemberDAO {

	public abstract List<MemberVO> select();
	public abstract MemberVO select(String userid);
	public abstract int insert(MemberVO vo);
	public abstract int update(MemberVO vo);
	public abstract int delete(String userid);
	public abstract int deregister(String userid); // 휴면계정설정
	
} // end interface MemberDAO








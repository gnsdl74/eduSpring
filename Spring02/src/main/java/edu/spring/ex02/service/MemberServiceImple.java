package edu.spring.ex02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.ex02.domain.MemberVO;
import edu.spring.ex02.persistance.MemberDAO;

@Service
public class MemberServiceImple implements MemberService {

	@Autowired
	private MemberDAO dao;
	
	@Override
	public int create(MemberVO vo) {
		return dao.insert(vo);
	}

	@Override
	public List<MemberVO> read() {
		return dao.select();
	}

	@Override
	public MemberVO read(String userid) {
		return dao.select(userid);
	}

	@Override
	public int update(MemberVO vo) {
		return dao.update(vo);
	}

	@Override
	public int delete(String userid) {
		return dao.delete(userid);
	}

	@Override
	public int deregister(String userid) {
		return dao.deregister(userid);
	}
} // end class MemberServiceImple










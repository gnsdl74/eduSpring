package edu.spring.ex04.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.ex04.domain.MemberVO;
import edu.spring.ex04.persistance.MemberDAO;

@Service
public class MemberServiceImple implements MemberService {
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImple.class);
	
	@Autowired
	private MemberDAO memberDao;

	@Override
	public MemberVO loginCheck(MemberVO vo) {
		logger.info("loginCheck() 호출");
		return memberDao.loginCheck(vo);
	} // loginCheck()
	
	
} // end class MemberServiceImple










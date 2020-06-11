package edu.spring.ex04.persistance;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.ex04.domain.MemberVO;

@Repository
public class MemberDAOImple implements MemberDAO {

	private static final String NAMESPACE =
			"edu.spring.ex04.MemberMapper";
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImple.class);
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberVO loginCheck(MemberVO vo) {
		logger.info("loginCheck() 호출");
		logger.info(vo.getUserid() +", "+ vo.getPassword());
		
		return sqlSession.selectOne(NAMESPACE + ".login", vo);
	} // end loginCheck()
	
	
} // end class MemberDAOImple
















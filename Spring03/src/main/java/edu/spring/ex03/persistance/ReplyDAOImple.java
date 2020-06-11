package edu.spring.ex03.persistance;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.ex03.domain.ReplyVO;

@Repository
public class ReplyDAOImple implements ReplyDAO {
	private static final Logger logger = LoggerFactory.getLogger(ReplyDAOImple.class);
	private static final String NAMESPACE = "edu.spring.ex03.ReplyMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(ReplyVO vo) {
		logger.info("reply_dao_insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	} // end insert()

	@Override
	public List<ReplyVO> select(int bno) {
		logger.info("reply_dao_select() 호출");
		return sqlSession.selectList(NAMESPACE + ".select_all_by_bno", bno);
	} // end select()

	@Override
	public int update(ReplyVO vo) {
		logger.info("reply_dao_update() 호출");
		return sqlSession.update(NAMESPACE + ".update", vo);
	} // end update()

	@Override
	public int delete(int rno) {
		logger.info("reply_dao_delete() 호출");
		return sqlSession.delete(NAMESPACE + ".delete", rno);
	} // end delete()

} // end ReplyDAOImple

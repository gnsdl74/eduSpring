package edu.spring.ex03.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.ex03.domain.ReplyVO;
import edu.spring.ex03.persistance.BoardDAO;
import edu.spring.ex03.persistance.ReplyDAO;

@Service
public class ReplyServiceImple implements ReplyService {
	private static final Logger logger = LoggerFactory.getLogger(ReplyServiceImple.class);
	
	@Autowired
	private ReplyDAO replyDao;
	@Autowired
	private BoardDAO boardDao;
	
	// @Transactional
	// - 두 개의 데이터베이스 변경이 생길 때
	//   위의 내용이 실행되었고, 아래 내용이 에러가 발생하면
	//   위의 내용은 rollback 되어야 한다.
	//   이러한 기능을 해주는게 Transactional 어노테이션이다.
	@Override
	// DAO에서는 메소드 하나에 DB와 관련 쿼리가 하나씩만 있어야함
	// 그러나 Service에서는 그렇지 않음.
	public int create(ReplyVO vo) throws Exception {
		logger.info("reply_service_insert() 호출");
		
		replyDao.insert(vo);
		logger.info("댓글 입력 성공");
		
		boardDao.updateReplycnt(1, vo.getBno());
		logger.info("게시판 댓글 개수 업데이트 성공");
		return 1;
	} // end create()

	@Override
	public List<ReplyVO> read(int bno) {
		logger.info("reply_service_select() 호출");
		return replyDao.select(bno);
	} // end read()

	@Override
	public int update(ReplyVO vo) {
		logger.info("reply_service_update() 호출");
		return replyDao.update(vo);
	} // end update()
	
	@Transactional
	@Override
	public int delete(int rno, int bno) throws Exception {
		logger.info("reply_service_delete() 호출");
		replyDao.delete(rno);
		logger.info("댓글 삭제 성공");
		
		boardDao.updateReplycnt(-1, bno);
		logger.info("게시판 댓글 개수 업데이트 성공");
		return 1;
	} // end delete()

} // end ReplyServiceImple

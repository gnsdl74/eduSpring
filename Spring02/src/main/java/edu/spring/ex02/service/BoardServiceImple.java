package edu.spring.ex02.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.ex02.domain.BoardVO;
import edu.spring.ex02.pageutil.PageCriteria;
import edu.spring.ex02.persistance.BoardDAO;

@Service // @Component
// 스프링 프레임워크에게 서비스 계층(Service/Business layer)의
// 콤포넌트임을 알려주는 어노테이션
// root-context.xml에 bean으로 등록해야함
// Service는 DAO를 가져다 사용하는 객체
public class BoardServiceImple implements BoardService {
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImple.class);
	
	@Autowired
	// 다형성(BoardDAOImple 대신 BoardDAO를 연동)
	private BoardDAO dao;
	
	@Override
	public List<BoardVO> read() {
		logger.info("read_all() 호출");
		return dao.select();
	} // end read_all()

	@Override
	public BoardVO read(int bno) {
		logger.info("read_by_bno() 호출 : bno = " + bno);
		return dao.select(bno);
	} // end read_by_bno()

	@Override
	public int create(BoardVO vo) {
		logger.info("create() 호출");
		return dao.insert(vo);
	} // end create()

	@Override
	public int update(BoardVO vo) {
		logger.info("update() 호출");
		return dao.update(vo);
	} // end update()

	@Override
	public int delete(int bno) {
		logger.info("delete() 호출");
		return dao.delete(bno);
	} // end delete()

	@Override
	// 전체 게시글 수를 가져오는 메소드
	public int getTotalNumsOfRecords() {
		logger.info("getTotalNumsOfRecords() 호출");
		return dao.getTotalNumsOfRecords();
	} // end getTotalNumsOfRecords()

	@Override
	// 페이징 처리한 게시글을 가져오는 read메소드
	public List<BoardVO> read(PageCriteria criteria) {
		logger.info("read_paging() 호출");
		return dao.select(criteria);
	} // end read_paging()

} // end BoardServiceImple

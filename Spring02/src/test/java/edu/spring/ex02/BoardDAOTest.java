package edu.spring.ex02;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.spring.ex02.domain.BoardVO;
import edu.spring.ex02.pageutil.PageCriteria;
import edu.spring.ex02.persistance.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
		locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" }
)
public class BoardDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
	
	@Autowired
	private BoardDAO dao;
	
	@Test
	public void testDAO() {
//		testSelectAll();
//		testSelectByBno();
//		testInsert();
//		testUpdate();
//		testDelete();
//		testTotalCount();
		testPaging();
	} // testDAO()
	
	public void testSelectAll() {
		List<BoardVO> list = dao.select();
		for(BoardVO vo : list) {
			logger.info(vo.getBno() + " : " + vo.getTitle());
		}
	} // end testSelectAll()
	
	public void testSelectByBno() {
		BoardVO vo = dao.select(4);
		if(vo != null) {
			logger.info(vo.getBno() + " : " + vo.getTitle());
		} else {
			logger.info("검색 결과 없음");
		}
	} // end testSelectByBno()
	
	public void testInsert() {
		BoardVO vo = new BoardVO(0, "MC.마이콜", "난 또치다 마이콜 나와라 오바", "ddochi", null);
		int result = dao.insert(vo);
		if(result == 1) {
			logger.info("insert 성공");
		} else {
			logger.info("insert 실패");
		}
	} // end testInsert()
	
	public void testUpdate() {
		BoardVO vo = new BoardVO(3, "gogildong임", "애송이", "", null);
		int result = dao.update(vo);
		if(result == 1) {
			logger.info("update 성공");
		} else {
			logger.info("update 실패");
		}
	} // end testUpdate()
	
	public void testDelete() {
		int result = dao.delete(1);
		if(result == 1) {
			logger.info("delete 성공");
		} else {
			logger.info("delete 실패");
		}
	} // end testDelete()
	
	public void testTotalCount() {
		int result = dao.getTotalNumsOfRecords();
		logger.info("# of records : " + result);
	} // end testTotalCount()
	
	public void testPaging() {
		PageCriteria c = new PageCriteria(1, 10);
		List<BoardVO> list = dao.select(c);
		for(BoardVO vo : list) {
			logger.info(vo.getBno() + " : " + vo.getContent());
		}
	} // end testPaging()
	
} // end BoardDAOTest

package edu.spring.ex02;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
		locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" }
)
// spring 하위에 있는 xml 설정파일을 모두 포함
public class SqlSessionTest {
	private static final Logger logger = LoggerFactory.getLogger(SqlSessionTest.class);
	// board-mapper의 namespace와 아래의 NAMESPACE가 같아야함
	private static final String NAMESPACE = "edu.spring.ex02.BoardMapper";

	@Autowired
	private SqlSession sqlSession;

	@Test
	public void testSelectAll() {
		PageCriteria c = new PageCriteria(1, 5);
		List<BoardVO> list = sqlSession.selectList(NAMESPACE + ".paging", c);
		for(BoardVO vo : list) {
			logger.info(vo.getBno() + " : " + vo.getTitle());
		}
		
	} // end testSelectAll()

} // end SqlSessionTest

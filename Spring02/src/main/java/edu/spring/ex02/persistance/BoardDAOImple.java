package edu.spring.ex02.persistance;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.ex02.domain.BoardVO;
import edu.spring.ex02.pageutil.PageCriteria;

@Repository // @Component과 동일
// - 영속 계층(Persistance Layer)의 DB 관련 기능을 담당
// - Spring component bean으로 등록함
// - 스프링 프레임워크가 bean을 생성하기 위해서는
//   root-context.xml에 bean으로 등록해야함
// - <context:component-scan ... />
// Service의 호출을 받아 DB와의 연동을 실행
public class BoardDAOImple implements BoardDAO {
	// board-mapper.xml의 namespace와 같아야함. 같지않으면 해당 mapper의 쿼리문의 호출이 불가능
	private static final String NAMESPACE = "edu.spring.ex02.BoardMapper";
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImple.class);
	
	// MyBatis의 SqlSession을 사용하기 위해서
	// 스프링 프레임워크가 생성한 bean을 주입(injection)받음
	@Autowired
	private SqlSession sqlSession;
	
	// 게시판의 전체 게시물을 출력하기 위한 쿼리문
	@Override
	public List<BoardVO> select() {
		logger.info("select_all() 호출");
		return sqlSession.selectList(NAMESPACE + ".select_all");
		// .select_all : board-mapper.xml의 id와 매칭
	} // end select_all()
	
	// 특정 게시글의 데이터를 가져오기 위한 쿼리문
	@Override
	public BoardVO select(int bno) {
		logger.info("select_by_bno() 호출 : bno = " + bno);
		return sqlSession.selectOne(NAMESPACE + ".select_by_bno", bno);
	} // end select_by_bno()
	
	// 게시글을 검색할 때 이용하는 쿼리문
	// userid로 검색해서 일치하는 게시글을 가져오는 쿼리문
	// 쿼리문에서 '%'는 모든 문자 대체
	// %userid% : userid를 포함한 것을 모두 찾음
	@Override
	public List<BoardVO> select(String userid) {
		logger.info("select_by_userid() 호출 : userid = " + userid);
		userid = "%" + userid + "%";
		return sqlSession.selectList(NAMESPACE + ".select_by_userid", userid);
	} // end select_by_userid()
	
	// title 또는 content를 keyword로 검색해서 해당 게시글을 가져오는 쿼리문
	@Override
	public List<BoardVO> selectByTitleOrContent(String keyword) {
		logger.info("selectByTitleOrContent() 호출 : keyword = " + keyword);
		keyword = "%" + keyword + "%";
		return sqlSession.selectList(NAMESPACE + ".select_by_title_content", keyword);
	} // end selectByTitleOrContent()
	
	@Override
	public int insert(BoardVO vo) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	} // end insert()

	@Override
	public int update(BoardVO vo) {
		logger.info("update() 호출");
		return sqlSession.update(NAMESPACE + ".update", vo);
	} // end update()

	@Override
	public int delete(int bno) {
		logger.info("delete() 호출");
		return sqlSession.delete(NAMESPACE + ".delete", bno);
	} // end delete()

	@Override
	// 전체 게시글의 개수 가져오기(확인)
	public int getTotalNumsOfRecords() {
		logger.info("getTotalNumsOfRecords() 호출");
		return sqlSession.selectOne(NAMESPACE + ".total_count");
	} // end getTotalNumsOfRecords()

	@Override
	// 페이지마다 정해진 개수의 게시물을 가져오는 select문
	public List<BoardVO> select(PageCriteria criteria) {
		logger.info("select_paging() 호출 : page = " + criteria.getPage());
		return sqlSession.selectList(NAMESPACE + ".paging", criteria);
	} // end select_paging()

} // end BoardDAOImple

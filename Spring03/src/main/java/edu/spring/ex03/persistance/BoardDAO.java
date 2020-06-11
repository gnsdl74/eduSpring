package edu.spring.ex03.persistance;

import java.util.List;

import edu.spring.ex03.domain.BoardVO;
import edu.spring.ex03.pageutil.PageCriteria;

public interface BoardDAO {
	public abstract List<BoardVO> select(); // 전체 게시글 select
	public abstract	BoardVO select(int bno); // 해당 게시글의 세부정보 select
	public abstract List<BoardVO> select(String userid); // userid로 게시글 검색
	public abstract List<BoardVO> selectByTitleOrContent(String keyword); // keyword로 게시글 검색
	public abstract int insert(BoardVO vo); // 게시글 작성
	public abstract int update(BoardVO vo); // 게시글 수정
	public abstract int delete(int bno); // 게시글 삭제
	public abstract int getTotalNumsOfRecords(); // 총 게시글의 개수
	public abstract List<BoardVO> select(PageCriteria criteria); // 현재 페이지에 표시할 게시글 수
	public abstract int updateReplycnt(int amount, int bno); // 해당 게시글의 댓글 개수 변경
} // end BoardDAO

package edu.spring.ex02.persistance;

import java.util.List;

import edu.spring.ex02.domain.BoardVO;
import edu.spring.ex02.pageutil.PageCriteria;

public interface BoardDAO {
	public abstract List<BoardVO> select();
	public abstract	BoardVO select(int bno);
	public abstract List<BoardVO> select(String userid);
	public abstract List<BoardVO> selectByTitleOrContent(String keyword);
	public abstract int insert(BoardVO vo);
	public abstract int update(BoardVO vo);
	public abstract int delete(int bno);
	public abstract int getTotalNumsOfRecords();
	public abstract List<BoardVO> select(PageCriteria criteria);
} // end BoardDAO

package edu.spring.ex02.service;

import java.util.List;

import edu.spring.ex02.domain.BoardVO;
import edu.spring.ex02.pageutil.PageCriteria;

// CRUD(CREATE, READ, UPDATE, DELETE)
public interface BoardService {
	public abstract List<BoardVO> read();
	public abstract BoardVO read(int bno);
	public abstract int create(BoardVO vo);
	public abstract int update(BoardVO vo);
	public abstract int delete(int bno);
	public abstract int getTotalNumsOfRecords();
	public abstract List<BoardVO> read(PageCriteria criteria);
	
} // end BoardService

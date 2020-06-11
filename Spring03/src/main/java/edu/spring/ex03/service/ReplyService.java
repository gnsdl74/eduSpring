package edu.spring.ex03.service;

import java.util.List;

import edu.spring.ex03.domain.ReplyVO;

public interface ReplyService {
	int create(ReplyVO vo) throws Exception;
	List<ReplyVO> read(int bno);
	int update(ReplyVO vo);
	int delete(int rno, int bno) throws Exception;
	
} // end ReplyService

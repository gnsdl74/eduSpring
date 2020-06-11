package edu.spring.ex03.persistance;

import java.util.List;

import edu.spring.ex03.domain.ReplyVO;

public interface ReplyDAO {
	int insert(ReplyVO vo);
	List<ReplyVO> select(int bno);
	int update(ReplyVO vo);
	int delete(int rno);
} // end ReplyDAO

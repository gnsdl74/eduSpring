package edu.spring.ex03.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.ex03.domain.ReplyVO;
import edu.spring.ex03.service.ReplyService;
// RESTful API를 사용해서 Restcontroller를 사용할 때
// view없이 데이터 이동을 확인하려면 POSTMAN tool을 이용할 것


// RequestMapping
// HTTP Method : GET, POST, PUT, DELETE / PATCH(일부수정)
// /replies (POST) : 댓글 추가(insert)
// /replies/all/숫자 (GET) : 해당 글번호(bno)의 모든 댓글 검색(select)
// /replies/숫자 (PUT) : 해당 댓글 번호(rno)의 내용을 수정(update)
// /replies/숫자 (DELETE) : 해당 댓글 번호(rno)의 댓글 삭제(delete)
@RestController
@RequestMapping(value = "/replies")
public class ReplyRESTController {
	private static final Logger logger = LoggerFactory.getLogger(ReplyRESTController.class);

	@Autowired
	private ReplyService replyService;

	// GetMapping, PoestMapping 어노테이션
	// spring 4버전 이상부터 지원
	// @GetMapping => @RequestMapping(value = "", method = RequestMethod.GET)
	// @PostMapping => @RequestMapping(value = "", method = RequestMethod.POST)

	/* RESTful 댓글 추가 메소드 */
	@PostMapping
	// post방식으로 url을 전달받음
	// @RequestBody 는 @ModelAttribute처럼 작동한다.
	// 그러나 String형태로 받아온 JSON 데이터를 자동 변환한다는 점에서 큰 차이를 보인다.
	// 즉, String으로 보내진 reply.jsp의 obj(Http 요청 몸체)를
	// @RequestBody가 JSON형태로 자동 변환 후 자바 객체(ReplyVo)와 매핑시켜줌.
	public ResponseEntity<Integer> createReply(
			@RequestBody ReplyVO vo) {
		// @RequestBody
		// - 클라이언트에서 전송받은 json 데이터를 자바 객체로 변환해주는 annotation
		logger.info("createReply() 호출");
		logger.info(vo.toString());
		try {
			int result = replyService.create(vo);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(0, HttpStatus.OK);
		}

		// 해당 result를 ajax를 실행했던 reply.jsp로 return
		// HttpStatus로 Response Header를 개발자가 조절가능.
	} // end createReply()

	/* RESTful 댓글 가져오기 메소드 */
	@GetMapping(value = "/all/{no}")
	public ResponseEntity<List<ReplyVO>> readReplies(
			@PathVariable("no") int bno) {
		logger.info("readReply() 호출");
		List<ReplyVO> list = replyService.read(bno);

		return new ResponseEntity<List<ReplyVO>>(list, HttpStatus.OK);
	} // end readReplies()

	@PutMapping(value = "/{no}")
	public ResponseEntity<String> updateReply(
			@PathVariable("no") int rno, @RequestBody ReplyVO vo) {
		logger.info("updateReply() 호출");
		vo.setRno(rno);
		int result = replyService.update(vo);
		ResponseEntity<String> entity = null;
		if (result == 1) {
			entity = new ResponseEntity<String>("success", HttpStatus.OK); 
		} else {
			entity = new ResponseEntity<String>("fail", HttpStatus.OK);
		}
		return entity;
	} // end updateReply()

	@DeleteMapping(value = "/{no}")
	public ResponseEntity<String> deleteReply(
			@PathVariable("no") int rno, @RequestBody ReplyVO vo) {
		logger.info("deleteReply() 호출");
		logger.info("rno : " + rno + ", bno : " + vo.getBno());
		try {
			replyService.delete(rno, vo.getBno());
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		}
	} // end deleteReply()

} // end ReplyRESTController

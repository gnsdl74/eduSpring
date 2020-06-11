package edu.spring.ex02.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.spring.ex02.domain.BoardVO;
import edu.spring.ex02.pageutil.PageCriteria;
import edu.spring.ex02.pageutil.PageMaker;
import edu.spring.ex02.service.BoardService;

@Controller
// @Controller는 servlet-context.xml에서 관리
@RequestMapping(value = "/board") // url : ex02/board/
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model, Integer page, Integer perPage) {
		logger.info("list() 호출");
		// Paging 처리 이전 코드
//		List<BoardVO> list = boardService.read();
//		model.addAttribute("boardList", list);

		// Paging 처리 이후 코드
		// PageCriteria를 통해 현재 페이지 번호와 한 페이지에 보여줄 게시물 개수를 확인 후 model로 전달
		// PageMaker에 PageCriteria 및 TotalCount를 set하고 해당 데이터로 페이지 링크를 계산
		// 계산된 pageMaker를 model로 전달
		PageCriteria c = new PageCriteria();
		if (page != null) {
			c.setPage(page);
		}
		if (perPage != null) {
			c.setNumsPerPage(perPage);
		}

		List<BoardVO> list = boardService.read(c);
		model.addAttribute("boardList", list);

		PageMaker maker = new PageMaker();
		maker.setCriteria(c);
		maker.setTotalCount(boardService.getTotalNumsOfRecords());
		maker.setPageData();
		model.addAttribute("pageMaker", maker);

	} // end list()

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void register() {
		logger.info("registerGET() 호출");
	} // end register()

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPost(BoardVO vo, RedirectAttributes reAttr) {
		// RedirectAttributes
		// - 재경로 위치에 속성을 전송하는 객체
		logger.info("registerPost() 호출");
		logger.info("제목 : " + vo.getTitle());
		logger.info("내용 : " + vo.getContent());
		logger.info("작성자 : " + vo.getUserid());
		int result = boardService.create(vo);
		if (result == 1) { // DB insert 성공
			reAttr.addFlashAttribute("insert_result", "success");
		} else { // DB insert 실패
			reAttr.addFlashAttribute("insert_result", "fail");
		}
		// addAttribute() 메소드 : url 뒤에 전달한 값이 붙으며, 리프레쉬해도 값이 남아있음
		// addFlashAttribute() 메소드
		// : url 뒤에 붙지 않으며, 일회성으로 리프레쉬하면 값이 사라짐, 한번에 1개만 사용가능(한번에 Map형태로 여러 값을 전달)

		return "redirect:/board/list"; // /board/list 경로로 이동

	} // end registerPost()

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	// @ModelAttribute를 통해 사용자가 보던 페이지가 저장되어 있는 객체 c를 criteria의 이름으로 전송
	public void detail(int bno, Model model, @ModelAttribute("criteria") PageCriteria c) {
		logger.info("detail() 호출");
		BoardVO vo = boardService.read(bno);
		System.out.println(vo);
		if (vo != null) {
			model.addAttribute("boardVO", vo);
		}
	} // end detail()
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void update(int bno, Model model, @ModelAttribute("criteria") PageCriteria c) {
		logger.info("update() 호출 bno = " + bno);
		BoardVO vo = boardService.read(bno);
		model.addAttribute("boardVO", vo);
	} // end update()
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updatePOST(BoardVO vo, PageCriteria criteria) {
		logger.info("updatePOST() 호출 ㅣ bno = " + vo.getBno());
		int result = boardService.update(vo);
		
		return "redirect:/board/list?page="
				+ criteria.getPage()
				+ "&perPage="
				+ criteria.getNumsPerPage();
	} // end updatePOST()
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(int bno) {
		logger.info("delete() 호출 : bno = " + bno);
		int result = boardService.delete(bno);
		return "redirect:/board/list";
	} // end delete()

} // end BoardController

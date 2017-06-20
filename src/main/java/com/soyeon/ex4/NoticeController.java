package com.soyeon.ex4;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.soyeon.board.BoardDTO;
import com.soyeon.notice.NoticeDTO;
import com.soyeon.notice.NoticeServiceImpl;
import com.soyeon.util.ListInfo;

@Controller
@RequestMapping(value="/board/**")
public class NoticeController {
	
	@Inject /* inject는 타입으로 찾는다. */
	private NoticeServiceImpl noticeService;

	@RequestMapping(value="noticeList", method=RequestMethod.GET)
	public String noticeList(Model model, ListInfo listInfo) throws Exception {
		System.out.println(listInfo.getCurPage());
		
		List<BoardDTO> ar = noticeService.boardList(listInfo);
		model.addAttribute("board", "notice");
		model.addAttribute("list", ar);
		model.addAttribute("listInfo", listInfo);
		
		return "/board/boardList";
	}
	
	@RequestMapping(value="noticeView", method=RequestMethod.GET)
	public String noticeView(Integer num, Model model) throws Exception {
		model.addAttribute("board", "notice");
		BoardDTO boardDTO = noticeService.boardView(num);
		model.addAttribute("view", boardDTO);
		
		return "/board/boardView";
	}
	
	@RequestMapping(value="noticeUpdate",method=RequestMethod.GET)
	public String noticeUpdate(Integer num, Model model) throws Exception {
		model.addAttribute("board", "notice");
		BoardDTO boardDTO = noticeService.boardView(num);
		model.addAttribute("view", boardDTO);
		model.addAttribute("path", "Update");
		
		return "/board/boardWrite";
	}
	
	@RequestMapping(value="noticeUpdate",method=RequestMethod.POST)
	public String noticeUpdate(NoticeDTO noticeDTO, RedirectAttributes rd) throws Exception {
		rd.addFlashAttribute("board", "notice");
		int result = noticeService.boardUpdate(noticeDTO);
		String message = "update fail";
		
		if(result > 0) {
			message = "update success";
		}
		rd.addFlashAttribute("message",message);
		
		return "redirect:/board/noticeList";
	}
	
	@RequestMapping(value="noticeWrite", method=RequestMethod.GET)
	public String noticeWrite(Model model) {
		model.addAttribute("board", "notice");
		model.addAttribute("path","Write");
		
		return "/board/boardWrite";
	}
	
	@RequestMapping(value="noticeWrite", method=RequestMethod.POST)
	public String noticeWrite(NoticeDTO noticeDTO, RedirectAttributes rd) throws Exception {
		rd.addFlashAttribute("board", "notice");
		int result = noticeService.boardWrite(noticeDTO);
		
		String message = "fail";
		
		if(result > 0) {
			message = "success";
		}
		rd.addFlashAttribute("message", message);		//redirect로 보낼 때 값을 전송하려면
		
		return "redirect:/board/noticeList";
	}
	
	@RequestMapping(value="noticeDelete", method=RequestMethod.GET)
	public String noticeDelete(Integer num, RedirectAttributes rd) throws Exception {
		rd.addFlashAttribute("board", "notice");
		int result = noticeService.boardDelete(num);
		
		String message = "delete fail";
		
		if(result > 0) {
			message = "delete success";
		}
		rd.addFlashAttribute("message",message);
		
		return "redirect:/board/noticeList";
	}
}
package com.soyeon.ex4;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.soyeon.board.BoardDTO;
import com.soyeon.freeboard.FreeboardDTO;
import com.soyeon.freeboard.FreeboardServiceImpl;
import com.soyeon.util.ListInfo;

@Controller
@RequestMapping(value="/board/**")
public class FreeboardController {

	@Inject
	private FreeboardServiceImpl freeboardService;
	
	@RequestMapping(value="freeboardList")
	public String freeboardList(Model model, ListInfo listInfo) throws Exception {
		List<BoardDTO> ar = freeboardService.boardList(listInfo);
		
		model.addAttribute("board", "freeboard");
		model.addAttribute("list", ar);
		model.addAttribute("listInfo", listInfo);
		
		return "/board/boardList";
	}
	
	@RequestMapping(value="freeboardView")
	public String freeboardView(int num, Model model) throws Exception {
		FreeboardDTO freeboardDTO = (FreeboardDTO)freeboardService.boardView(num);
		model.addAttribute("board", "freeboard");
		model.addAttribute("view", freeboardDTO);
		
		return "/board/boardView";
	}
	@RequestMapping(value="freeboardWrite", method=RequestMethod.GET)
	public String freeboardWrite(Model model) throws Exception {
		model.addAttribute("board", "freeboard");
		model.addAttribute("path", "Write");
		
		return "/board/boardWrite";
	}
	@RequestMapping(value="freeboardWrite", method=RequestMethod.POST)
	public String freeboardWrite(BoardDTO boardDTO, RedirectAttributes rd) throws Exception {
		int result = freeboardService.boardWrite(boardDTO);
		rd.addFlashAttribute("board", "freeboard");
		
		if(result > 0) {
			rd.addFlashAttribute("message", "글 쓰기 성공");
		} else {
			rd.addFlashAttribute("message", "글 쓰기 실패");
		}
		
		return "redirect:/board/freeboardList";
	}
	@RequestMapping(value="freeboardUpdate", method=RequestMethod.GET)
	public String freeboardUpdate(int num, Model model) throws Exception {
		FreeboardDTO freeboardDTO = (FreeboardDTO)freeboardService.boardView(num);
		model.addAttribute("board", "freeboard");
		model.addAttribute("path", "Update");
		model.addAttribute("view", freeboardDTO);
		
		return "/board/boardWrite";
	}
	@RequestMapping(value="freeboardUpdate", method=RequestMethod.POST)
	public String freeboardUpdate(BoardDTO boardDTO, RedirectAttributes rd) throws Exception {
		int result = freeboardService.boardUpdate(boardDTO);
		
		if(result > 0) {
			rd.addFlashAttribute("message", "수정 성공");
		} else {
			rd.addFlashAttribute("message", "수정 실패");
		}
		
		rd.addFlashAttribute("board", "freeboard");
		rd.addFlashAttribute("path", "Update");
		
		return "redirect:/board/freeboardList";
	}
	
	@RequestMapping(value="freeboardDelete", method=RequestMethod.GET)
	public String noticeDelete(Integer num, RedirectAttributes rd) throws Exception {
		rd.addFlashAttribute("board", "notice");
		int result = freeboardService.boardDelete(num);
		
		String message = "delete fail";
		
		if(result > 0) {
			message = "delete success";
		}
		rd.addFlashAttribute("message",message);
		
		return "redirect:/board/freeboardList";
	}
}
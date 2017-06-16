package com.soyeon.ex4;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soyeon.board.BoardDTO;
import com.soyeon.freeboard.FreeboardServiceImpl;

@Controller
@RequestMapping(value="/freeboard/**")
public class FreeboardController {

	@Inject
	private FreeboardServiceImpl freeboardService;
	
	@RequestMapping(value="freeboardList")
	public String freeboardList(@RequestParam(defaultValue="1")Integer curPage, Model model) throws Exception {
		List<BoardDTO> ar = freeboardService.boardList(curPage);
		model.addAttribute("kind", "freeboard");
		model.addAttribute("list", ar);
		
		return "/board/boardList";
	}
}
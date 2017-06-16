package com.soyeon.freeboard;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.soyeon.board.BoardDTO;
import com.soyeon.util.PageMaker;
import com.soyeon.util.RowMaker;

@Service
public class FreeboardServiceImpl {

	@Inject
	private FreeboardDAO freeboardDAO;
	
	public List<BoardDTO> boardList(Integer curPage) throws Exception {
		PageMaker pageMaker = new PageMaker(10, curPage);
		RowMaker rowMaker = pageMaker.getRowMaker("", "");
		
		return freeboardDAO.boardList(rowMaker);
	}
	
	public BoardDTO boardView(int num) throws Exception {
		
		return freeboardDAO.boardView(num);
	}
	
}
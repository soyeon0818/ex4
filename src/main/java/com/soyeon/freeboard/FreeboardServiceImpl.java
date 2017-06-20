package com.soyeon.freeboard;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.soyeon.board.BoardDTO;
import com.soyeon.board.BoardService;
import com.soyeon.util.PageMaker;
import com.soyeon.util.RowMaker;

@Service
public class FreeboardServiceImpl implements BoardService{

	@Inject
	private FreeboardDAO freeboardDAO;
	
	@Override
	public List<BoardDTO> boardList(int curPage) throws Exception {
		PageMaker pageMaker = new PageMaker(10, curPage);
		RowMaker rowMaker = pageMaker.getRowMaker("", "");
		
		return freeboardDAO.boardList(rowMaker);
	}
	
	public BoardDTO boardView(int num) throws Exception {
		return freeboardDAO.boardView(num);
	}
	
	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		return freeboardDAO.boardUpdate(boardDTO);
	}

	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		return freeboardDAO.boardWrite(boardDTO);
	}

	@Override
	public int boardDelete(int num) throws Exception {
		return freeboardDAO.boardDelete(num);
	}	
}
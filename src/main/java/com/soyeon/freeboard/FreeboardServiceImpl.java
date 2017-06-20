package com.soyeon.freeboard;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.soyeon.board.BoardDTO;
import com.soyeon.board.BoardService;
import com.soyeon.util.ListInfo;

@Service
public class FreeboardServiceImpl implements BoardService{

	@Inject
	private FreeboardDAO freeboardDAO;
	
	@Override
	public List<BoardDTO> boardList(ListInfo listInfo) throws Exception {
		int result = freeboardDAO.boardCount(listInfo);
		listInfo.makePage(result);
		listInfo.setRow();
		
		return freeboardDAO.boardList(listInfo);
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
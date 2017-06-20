package com.soyeon.board;

import java.util.List;

import com.soyeon.util.ListInfo;

public interface BoardService {
	
	public BoardDTO boardView(int num) throws Exception;
	
	public List<BoardDTO> boardList(ListInfo listInfo) throws Exception;
	
	public int boardUpdate(BoardDTO boardDTO) throws Exception;
	
	public int boardWrite(BoardDTO boardDTO) throws Exception;
	
	public int boardDelete(int num) throws Exception;
}
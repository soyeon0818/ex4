package com.soyeon.notice;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.soyeon.board.BoardDTO;
import com.soyeon.board.BoardService;
import com.soyeon.util.ListInfo;

@Service
// NoticeService noticeService = new NoticeService();
public class NoticeServiceImpl implements BoardService {
	
	@Inject
	private NoticeDAO noticeDAO;
	
	@Override
	public BoardDTO boardView(int num) throws Exception {
		return (NoticeDTO) noticeDAO.boardView(num);
	}

	@Override
	public List<BoardDTO> boardList(ListInfo listInfo) throws Exception {
		int result = noticeDAO.boardCount(listInfo);
		listInfo.makePage(result);
		listInfo.setRow();
	
		return noticeDAO.boardList(listInfo);
	}

	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		return noticeDAO.boardUpdate(boardDTO);
	}

	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		return noticeDAO.boardWrite(boardDTO);
	}

	@Override
	public int boardDelete(int num) throws Exception {
		return noticeDAO.boardDelete(num);
	}
}
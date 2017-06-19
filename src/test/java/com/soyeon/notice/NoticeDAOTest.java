package com.soyeon.notice;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import com.soyeon.board.BoardDTO;
import com.soyeon.util.PageMaker;


public class NoticeDAOTest extends MyAbstractTest {

	@Inject
	private NoticeDAO noticeDAO;
	
	@Test
	public void countTest() throws Exception {
		int count = noticeDAO.boardCount();
		
		assertNotEquals(0, count);
	}
	
	@Test
	public void connectionTest() throws Exception {
		PageMaker pageMaker = new PageMaker(10, 1);
		List<BoardDTO> ar = noticeDAO.boardList(pageMaker.getRowMaker(null, null));
		
		assertNotEquals(0, ar.size());
	}
}
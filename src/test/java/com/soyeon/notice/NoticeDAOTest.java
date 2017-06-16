package com.soyeon.notice;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import com.soyeon.board.BoardDTO;
import com.soyeon.util.PageMaker;
import com.soyeon.util.RowMaker;


public class NoticeDAOTest extends MyAbstractTest {

	@Inject
	private NoticeDAO noticeDAO;
	
	@Test
	public void test() throws Exception {
		PageMaker pageMaker = new PageMaker(10, 1);
		RowMaker rowMaker = pageMaker.getRowMaker("", "");
		
		List<BoardDTO> ar = noticeDAO.boardList(rowMaker);
		
		Assert.assertEquals(0,ar.size());
	}
	
	//@Test
	public void test2() throws Exception {
		int result = noticeDAO.boardDelete(466);
		assertEquals(1, result);
	}
}
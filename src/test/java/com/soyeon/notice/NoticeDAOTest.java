package com.soyeon.notice;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import com.soyeon.board.BoardDTO;
import com.soyeon.freeboard.FreeboardDAO;
import com.soyeon.util.ListInfo;
import com.soyeon.util.PageMaker;
import com.soyeon.util.RowMaker;


public class NoticeDAOTest extends MyAbstractTest {

	@Inject
	private NoticeDAO noticeDAO;
	
	@Test
	public void connectionTest() throws Exception {
		ListInfo listInfo = new ListInfo();
		listInfo.setFind("zz");
		listInfo.setSearch("writer");
		int count = noticeDAO.boardCount(listInfo);
		
		System.out.println(count);
		
		assertNotEquals(0, count);
	}
}
package com.soyeon.notice;
import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.soyeon.board.BoardDAO;
import com.soyeon.board.BoardDTO;
import com.soyeon.util.ListInfo;

@Repository
// NoticeDAO noticeDAO = new NoticeDAO();
public class NoticeDAO implements BoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	private static final String NAMESPACE = "NoticeMapper.";
	
	@Override
	public List<BoardDTO> boardList(ListInfo listInfo) throws Exception {
		return sqlSession.selectList(NAMESPACE + "list", listInfo);
	}

	@Override
	public BoardDTO boardView(int num) throws Exception {
		return sqlSession.selectOne(NAMESPACE + "view", num);
	}

	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		return sqlSession.insert(NAMESPACE + "write", boardDTO);
	}

	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		return sqlSession.update(NAMESPACE + "update", boardDTO);
	}

	@Override
	public int boardDelete(int num) throws Exception {
		return sqlSession.delete(NAMESPACE + "delete", num);
	}

	@Override
	public int boardCount(ListInfo listInfo) throws Exception {
		return sqlSession.selectOne(NAMESPACE + "count", listInfo);
	}

	@Override
	public void boardHit(int num) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
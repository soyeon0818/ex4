package com.soyeon.freeboard;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.soyeon.board.BoardDAO;
import com.soyeon.board.BoardDTO;
import com.soyeon.util.ListInfo;

@Repository
public class FreeboardDAO implements BoardDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String NAMESPACE = "FreeboardMapper.";

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
		return sqlSession.selectOne(NAMESPACE + "count");
	}

	@Override
	public void boardHit(int num) throws Exception {
	}
}
package com.soyeon.freeboard;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.soyeon.board.BoardDAO;
import com.soyeon.board.BoardDTO;
import com.soyeon.util.RowMaker;

@Repository
public class FreeboardDAO implements BoardDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String NAMESPACE = "FreeboardMapper.";

	@Override
	public List<BoardDTO> boardList(RowMaker rowMaker) throws Exception {
		return sqlSession.selectList(NAMESPACE + "list", rowMaker);
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int boardCount() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void boardHit(int num) throws Exception {
		// TODO Auto-generated method stub
		
	}
}

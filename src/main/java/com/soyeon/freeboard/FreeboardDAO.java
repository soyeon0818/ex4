package com.soyeon.freeboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.soyeon.board.BoardDAO;
import com.soyeon.board.BoardDTO;
import com.soyeon.util.RowMaker;

@Repository
public class FreeboardDAO implements BoardDAO {

	@Inject
	private DataSource dataSource;
	
	

	@Override
	public List<BoardDTO> boardList(RowMaker rowMaker) throws Exception {
		List<BoardDTO> ar = new ArrayList<BoardDTO>();
		PreparedStatement st = null;
		Connection con = dataSource.getConnection();
		ResultSet rs = null;
		
		String sql = "select * from "
				+ "(select rownum R, F.* from "
				+ "(select * from FREEBOARD) F)"
				+ "where R between ? and ?";
		
		st = con.prepareStatement(sql);
		st.setInt(1, rowMaker.getStartRow());
		st.setInt(2, rowMaker.getLastRow());
		rs = st.executeQuery();
		
		while(rs.next()) {
			FreeboardDTO freeboardDTO = new FreeboardDTO();
			
			freeboardDTO.setNum(rs.getInt("num"));
			freeboardDTO.setWriter(rs.getString("writer"));
			freeboardDTO.setTitle(rs.getString("title"));
			freeboardDTO.setContents(rs.getString("contents"));
			freeboardDTO.setReg_date(rs.getDate("reg_date"));
			freeboardDTO.setHit(rs.getInt("hit"));
			freeboardDTO.setRef(rs.getInt("ref"));
			freeboardDTO.setStep(rs.getShort("step"));
			freeboardDTO.setDepth(rs.getInt("depth"));
			
			ar.add(freeboardDTO);
		}
		return ar;
	}

	@Override
	public BoardDTO boardView(int num) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
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

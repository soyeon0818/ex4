package com.soyeon.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.soyeon.board.BoardDAO;
import com.soyeon.board.BoardDTO;
import com.soyeon.util.DBConnect;
import com.soyeon.util.RowMaker;

@Repository
// NoticeDAO noticeDAO = new NoticeDAO();
public class NoticeDAO implements BoardDAO {
	
	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<BoardDTO> boardList(RowMaker rowMaker) throws Exception {
		List<BoardDTO> ar = new ArrayList<BoardDTO>();
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		String sql = "select * from "
				+ "(select rownum R, N.* from "
				+ "(select * from notice order by num desc) N) "
				+ "where R between ? and ?";
		
		st = con.prepareStatement(sql);
		
		st.setInt(1, rowMaker.getStartRow());
		st.setInt(2, rowMaker.getLastRow());
		
		rs = st.executeQuery();
		
		while(rs.next()) {
			NoticeDTO noticeDTO = new NoticeDTO();
			
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setContents(rs.getString("contents"));
			noticeDTO.setReg_date(rs.getDate("reg_date"));
			noticeDTO.setHit(rs.getInt("hit"));
			
			ar.add(noticeDTO);
		}
		DBConnect.disConnect(rs, st, con);
		
		return ar;
	}

	@Override
	public BoardDTO boardView(int num) throws Exception {
		NoticeDTO noticeDTO = new NoticeDTO();
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		String sql = "select * from NOTICE where NUM = ?";
		
		st = con.prepareStatement(sql);
		st.setInt(1, num);
		rs = st.executeQuery();
		
		if(rs.next()) {
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setContents(rs.getString("contents"));
			noticeDTO.setReg_date(rs.getDate("reg_date"));
			noticeDTO.setHit(rs.getInt("hit"));
		} else {
			noticeDTO = null;
		}
		
		DBConnect.disConnect(rs, st, con);
		
		return noticeDTO;
	}

	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		int result = 0;
		
		String sql = "insert into NOTICE (NUM,WRITER,TITLE,CONTENTS,REG_DATE,HIT) values (NOTICE_SEQ.nextval,?,?,?,SYSDATE,0)";
		
		st = con.prepareStatement(sql);
		
		st.setString(1, boardDTO.getWriter());
		st.setString(2, boardDTO.getTitle());
		st.setString(3, boardDTO.getContents());
		
		result = st.executeUpdate();
		
		DBConnect.disConnect(st, con);
		
		return result;
	}

	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		int result = 0;
		
		String sql = "update NOTICE set TITLE=?, CONTENTS=?, REG_DATE=SYSDATE where NUM=?";
		
		st = con.prepareStatement(sql);
		
		st.setString(1, boardDTO.getTitle());
		st.setString(2, boardDTO.getContents());
		st.setInt(3, boardDTO.getNum());
		
		result = st.executeUpdate();
		
		DBConnect.disConnect(st, con);
		
		return result;
	}

	@Override
	public int boardDelete(int num) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		int result = 0;
		
		String sql = "delete from NOTICE where NUM=?";
		
		st = con.prepareStatement(sql);
		st.setInt(1, num);
		result = st.executeUpdate();
		
		DBConnect.disConnect(st, con);
		
		return result;
	}

	@Override
	public int boardCount() throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		int result = 0;
		
		String sql = "select nvl(count(NUM), 0) from NOTICE";
		
		st = con.prepareStatement(sql);
		rs = st.executeQuery();
		rs.next();
		
		result = rs.getInt(1);
		
		DBConnect.disConnect(rs, st, con);
		return result;
	}

	@Override
	public void boardHit(int num) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
package com.company.Model2.board;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.company.Model2.common.JDBCUtil;

public class BoardDAO {
	// 데이터베이스 연결관련 변수 선언
	Connection conn = null;
	// Connection - 데이터베이스와 연결하는 객체입니다.
	PreparedStatement pstmt = null;
	// 객체를 생성할 때 인자값으로 실행할 SQL문을 지정하는데,
	// 값을 동적으로 지정해야할 때 ? 기호로 대체할 수 있습니다.
	ResultSet rs = null;
	// 결과값을 저장할 수 있다.
	// 저장된 값을 한 행 단위로 불러올 수 있다.
	// 한 행에서 값을 가져올 때는 타입을 지정해 불러올 수 있다.

	// 전체 게시글 목록 조회 메소드 구현
	public List<BoardDO> getBoardList(String searchField, String searchText) {
		System.out.println("===> getBoardList처리");

		List<BoardDO> boardList = new ArrayList<BoardDO>();

		try {
			conn = JDBCUtil.getConnection();

			String where = "";

			// [중요]
			if (searchField != null && searchText != null) {
				where = "where " + searchField + " like '%" + searchText + "%'";
			}
			// 전체 게시글 목록 조회인 경우
			String Condition_SQL = "SELECT * FROM BOARD " + where + " order by seq desc";
		
			pstmt = conn.prepareStatement(Condition_SQL);
			rs = pstmt.executeQuery();

			// 검색한 레코드가 하나 이상이기 떄문에
			while (rs.next()) {
				BoardDO board = new BoardDO();

				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));

				boardList.add(board);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return boardList;
	}

	public void insertBoard(BoardDO boardDO) {
		System.out.println("===> insertBoard() 기능 처리됨!");

		try {
			conn = JDBCUtil.getConnection();

			String BOARD_INSERT = "insert into board(seq, title, writer, content)" + " values((select nvl(max(seq),0)+1"
					+ " from board),?,?,?)";

			pstmt = conn.prepareStatement(BOARD_INSERT);
			pstmt.setString(1, boardDO.getTitle());
			pstmt.setString(2, boardDO.getWriter());
			pstmt.setString(3, boardDO.getContent());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}

	public BoardDO getBoard(BoardDO boardDO) {
		System.out.println("===> getBoard() 기능 처리");

		BoardDO board = null;

		try {
			conn = JDBCUtil.getConnection();
			String UPDATE_CNT = "update board set cnt=cnt+1 where seq=?";
			
			pstmt = conn.prepareStatement(UPDATE_CNT);
			pstmt.setInt(1, boardDO.getSeq());
			pstmt.executeUpdate();
			
			String BOARD_GET = "select * from board where seq=?";
			pstmt = conn.prepareStatement(BOARD_GET);
			pstmt.setInt(1, boardDO.getSeq());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new BoardDO();
				
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return board;
	}
	
	public void updateBoard(BoardDO boardDO) {
		System.out.println("===> updateBoard() 기능 처리됨!");

		try {
			conn = JDBCUtil.getConnection();

			String UPDATE_BOARD = "update board set title=?, writer=?, content=? where seq=?";

			pstmt = conn.prepareStatement(UPDATE_BOARD);
			pstmt.setString(1, boardDO.getTitle());
			pstmt.setString(2, boardDO.getWriter());
			pstmt.setString(3, boardDO.getContent());
			pstmt.setInt(4, boardDO.getSeq());
			pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	public void deleteBoard(BoardDO boardDO) {
		System.out.println("===> delBoard() 기능 처리");

		try {
			conn = JDBCUtil.getConnection();
			String DELETE_BOARD = "DELETE FROM board WHERE seq=?";
			pstmt = conn.prepareStatement(DELETE_BOARD);
			pstmt.setInt(1, boardDO.getSeq());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
		
	}
	
}
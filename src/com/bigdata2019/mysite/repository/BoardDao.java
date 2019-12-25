package com.bigdata2019.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bigdata2019.mysite.vo.BoardVo;
import com.bigdata2019.mysite.vo.UserVo;

public class BoardDao {

	@SuppressWarnings("finally")
	public Boolean insert(BoardVo bVo, UserVo uVo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// no, title, contents, reg_date, hit, g_no, o_no, depth, user_no
			// String sql = "insert into board values(null, ?, ?, now(), 0, 0, 0, 0, (select
			// no from user where name ='" + uVo.getName() + "'))";
			String sql = "insert into board values(null, ?, ?, now(), 0, 0, 0, 0, " + uVo.getNo() + ")";
			pstmt = conn.prepareStatement(sql);

			// Execute SQL Statment
			pstmt.setString(1, bVo.getTitle());
			pstmt.setString(2, bVo.getContents());

			int count = pstmt.executeUpdate();
			result = (count == 1);

		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load driver: " + e);
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		} finally {
			// remove resources
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
	}

	public BoardVo find(Long no) {
		BoardVo result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select title, contents from board where no=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = new BoardVo();
				result.setTitle(rs.getString(1));
				result.setContents(rs.getString(2));
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load driver: " + e);
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public List<BoardVo> search(String keyword) {
		List<BoardVo> result = new ArrayList<BoardVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			String sql = "select no, title, contents, reg_date, hit from board where title like '%" + keyword + "%'";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContents(rs.getString(3));
				vo.setRegDate(rs.getString(4));
				vo.setHit(rs.getInt(5));

				result.add(vo);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load driver: " + e);
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public List<BoardVo> findAll() {
		List<BoardVo> result = new ArrayList<BoardVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			String sql = "select no, title, contents, date_format(reg_date, '%Y-%c-%d %h:%i:%s'), hit from board order by no desc";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContents(rs.getString(3));
				vo.setRegDate(rs.getString(4));
				vo.setHit(rs.getInt(5));

				result.add(vo);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load driver: " + e);
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	public Boolean delete(Long no) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = " delete from board where no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			int count = pstmt.executeUpdate();
			result = (count == 1);

		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load Driver: " + e);
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/webdb?characterEncoding=utf8";
		Connection conn = DriverManager.getConnection(url, "webdb", "webdb");

		return conn;
	}

}

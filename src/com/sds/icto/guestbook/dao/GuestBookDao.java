package com.sds.icto.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sds.icto.guestbook.vo.GuestBookVo;

public class GuestBookDao {
	private Connection getConnection() throws ClassNotFoundException,
			SQLException {

		Connection con = null;

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
		con = DriverManager.getConnection(dburl, "webdb", "webdb");

		return con;
	}

	public void guestBookInsert(GuestBookVo vo) throws ClassNotFoundException,
			SQLException {

		Connection conn = getConnection();
		String sql = "insert into guestbook values(GUESTBOOK_SEQ.nextval,?,?,?,sysdate)";
		PreparedStatement st = conn.prepareStatement(sql);

		st.setString(1, vo.getName());
		int pass = Integer.parseInt(vo.getPassword());
		st.setInt(2, pass);
		st.setString(3, vo.getMessage());
		st.executeUpdate();

		if (st != null) {
			st.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public List<GuestBookVo> fetch() throws ClassNotFoundException, SQLException {

		List<GuestBookVo> list = new ArrayList<GuestBookVo>();

		Connection conn = getConnection();
		Statement st = null;
		ResultSet rs = null;

		String sql = "select * from guestbook";

		st = conn.createStatement();
		rs = st.executeQuery(sql);

		while (rs.next()) {
			int num = rs.getInt(1);
			String name = rs.getString(2);
			String content = rs.getString(4);
			String date = rs.getString(5);

			GuestBookVo vo = new GuestBookVo();
			vo.setNo(num);
			vo.setName(name);
			vo.setMessage(content);
			vo.setDate(date);

			list.add(vo);
		
		}
		if (st != null) {
			st.close();
		}
		if (conn != null) {
			conn.close();
		}
		if (rs!=null){
			rs.close(); 
		}
		
		return list;
	}

	public void guestBookDelete(String no,String password) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		String sql = "delete from guestbook where no=? and password=?";
		PreparedStatement st = conn.prepareStatement(sql);

		st.setString(1, no);
		st.setString(2, password);
		st.executeUpdate();
		
		if (st != null) {
			st.close();
		}
		if (conn != null) {
			conn.close();
		}
	}
}

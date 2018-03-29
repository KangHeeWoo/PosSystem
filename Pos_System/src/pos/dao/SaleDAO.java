package pos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pos.jdbc.DBConnection;

import pos.vo.SaleVo;

public class SaleDAO {
	public int insert(SaleVo vo) {
		Connection con = null;
		PreparedStatement st = null;

		try {
			con = DBConnection.getConn();
			String sql = "insert into sale(m_num, sa_price, sa_point, sa_date) values (?, ?, ?, sysdate)";
			st = con.prepareStatement(sql);
			st.setInt(1, vo.getmNum());
			st.setInt(2, vo.getSaPrice());
			st.setInt(3, vo.getSaPoint());

			return st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			DBConnection.closeConn(con, st, null);
		}
	}

	public int delete(int num) {
		Connection con = null;
		PreparedStatement st = null;

		try {
			con = DBConnection.getConn();
			String sql = "delete from sale where sa_num = ?";
			st = con.prepareStatement(sql);
			st.setInt(1, num);

			return st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			DBConnection.closeConn(con, st, null);
		}
	}

	public ArrayList<SaleVo> search(String date1, String date2) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			con = DBConnection.getConn();
			String sql = "select * from sale where sa_date between to_date(?, 'yyyy-mm-dd') "
					+ "and to_date(?, 'yyyy-mm-dd')";
			st = con.prepareStatement(sql);
			st.setString(1, date1);
			st.setString(2, date2);
			rs = st.executeQuery();

			ArrayList<SaleVo> voList = new ArrayList<>();

			while (rs.next()) {
				voList.add(new SaleVo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDate(5)));
			}

			return voList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBConnection.closeConn(con, st, rs);
		}
	}
	
	public int search() {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			con = DBConnection.getConn();
			String sql = "select max(sa_num) from sale";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();

			if(rs.next()) {
				return rs.getInt(1);
			}else {
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			DBConnection.closeConn(con, st, rs);
		}
	}

	public ArrayList<SaleVo> showAll() {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			con = DBConnection.getConn();
			String sql = "select * from sale";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();

			ArrayList<SaleVo> voList = new ArrayList<>();

			while (rs.next()) {
				voList.add(new SaleVo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDate(5)));
			}

			return voList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBConnection.closeConn(con, st, rs);
		}
	}
}
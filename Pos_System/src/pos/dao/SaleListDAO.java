package pos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pos.jdbc.DBConnection;
import pos.vo.SaleListVo;

public class SaleListDAO {
	public int insert(SaleListVo vo) {
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			con = DBConnection.getConn();
			String sql = "insert into salelist(sa_num, p_num, sl_cnt) values (?,?,?)";
			st = con.prepareStatement(sql);
			st.setInt(1, vo.getSaNum());
			st.setInt(2, vo.getpNum());
			st.setInt(3, vo.getSlCnt());
						
			return st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			DBConnection.closeConn(con, st, null);
		}
	}
	
	public ArrayList<SaleListVo> search(int num) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<SaleListVo> voList = new ArrayList<>();
		
		try {
			con = DBConnection.getConn();
			String sql = "select * from salelist where sa_num = ?";
			st = con.prepareStatement(sql);
			st.setInt(1, num);
			rs = st.executeQuery();
			
			while(rs.next()) {
				voList.add(new SaleListVo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
			}
			
			return voList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBConnection.closeConn(con, st, rs);
		}
	}
	
	public ArrayList<SaleListVo> showAll(){
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<SaleListVo> voList = new ArrayList<>();
		
		try {
			con = DBConnection.getConn();
			String sql = "select * from salelist";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			
			while(rs.next()) {
				voList.add(new SaleListVo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
			}
			
			return voList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBConnection.closeConn(con, st, rs);
		}
	}
}

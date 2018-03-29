package pos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pos.jdbc.DBConnection;
import pos.vo.WarehouseVo;

public class WarehouseDAO {
	public int insert(WarehouseVo vo) {
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			con = DBConnection.getConn();
			String sql = "insert into warehouse(p_num, w_cnt, w_price, w_date) values(?,?,?,sysdate)";
			st = con.prepareStatement(sql);
			st.setInt(1, vo.getpNum());
			st.setInt(2, vo.getwCnt());
			st.setInt(3, vo.getwPrice());
						
			return st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			DBConnection.closeConn(con, st, null);
		}
	}

	public int update(WarehouseVo vo) {
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			con = DBConnection.getConn();
			String sql = "update warehouse set p_num = ?, w_cnt = ?, w_price = ? where w_num = ?";
			st = con.prepareStatement(sql);
			st.setInt(1, vo.getpNum());
			st.setInt(2, vo.getwCnt());
			st.setInt(3, vo.getwPrice());
			st.setInt(4, vo.getwNum());
						
			return st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			DBConnection.closeConn(con, st, null);
		}
	}

	public int delete(int num) {
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			con = DBConnection.getConn();
			String sql = "delete from warehouse where w_num = ?";
			st = con.prepareStatement(sql);
			st.setInt(1, num);
						
			return st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			DBConnection.closeConn(con, st, null);
		}
	}
	
	public WarehouseVo search(int num) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			con = DBConnection.getConn();
			String sql = "select * from warehouse where w_num = ?";
			st = con.prepareStatement(sql);
			st.setInt(1, num);
			rs = st.executeQuery();
			
			if(rs.next()) {
				return new WarehouseVo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDate(5));
			}else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBConnection.closeConn(con, st, rs);
		}
	}
	
	public ArrayList<WarehouseVo> showAll(){
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<WarehouseVo> wareList = new ArrayList<>();
		
		try {
			con = DBConnection.getConn();
			String sql = "select * from warehouse";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			
			while(rs.next()) {
				wareList.add(new WarehouseVo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDate(5)));
			}
			
			return wareList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBConnection.closeConn(con, st, rs);
		}
	}
}

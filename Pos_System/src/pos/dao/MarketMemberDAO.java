package pos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pos.jdbc.DBConnection;
import pos.vo.MarketMemberVo;

public class MarketMemberDAO {
	public int insert(MarketMemberVo vo) {
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			con = DBConnection.getConn();
			String sql = "insert into market_member(m_name, m_phone, m_addr, m_point, m_hiredate) "
					+ "values (?,?,?,0,sysdate)";
			st = con.prepareStatement(sql);
			st.setString(1, vo.getmName());
			st.setString(2, vo.getmPhone());
			st.setString(3, vo.getmAddr());
						
			return st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			DBConnection.closeConn(con, st, null);
		}
	}

	public int update(MarketMemberVo vo) {
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			con = DBConnection.getConn();
			String sql = "update market_member set m_name = ?, m_phone = ?, m_addr = ?, m_point = ? "
					+ "where m_num = ?";
			st = con.prepareStatement(sql);
			st.setString(1, vo.getmName());
			st.setString(2, vo.getmPhone());
			st.setString(3, vo.getmAddr());
			st.setInt(4, vo.getmPoint());
			st.setInt(5, vo.getmNum());
						
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
			String sql = "delete from market_member where m_num = ?";
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
	
	public MarketMemberVo search(int num) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			con = DBConnection.getConn();
			String sql = "select * from market_member where m_num = ?";
			st = con.prepareStatement(sql);
			st.setInt(1, num);
			rs = st.executeQuery();
			
			if(rs.next()) {
				return new MarketMemberVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDate(6));
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
	
	public ArrayList<MarketMemberVo> showAll(){
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<MarketMemberVo> memList = new ArrayList<>();
		
		try {
			con = DBConnection.getConn();
			String sql = "select * from market_member";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			
			while(rs.next()) {
				memList.add(new MarketMemberVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDate(6)));
			}
			
			return memList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBConnection.closeConn(con, st, rs);
		}
	}
}

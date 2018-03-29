package pos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pos.jdbc.DBConnection;
import pos.vo.ProductVo;

public class ProductDAO {
	public int insert(ProductVo vo) {
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			con = DBConnection.getConn();
			String sql = "insert into product(p_name, p_price, s_stock) values (?,?,0)";
			st = con.prepareStatement(sql);
			st.setString(1, vo.getpName());
			st.setInt(2, vo.getpPrice());
						
			return st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			DBConnection.closeConn(con, st, null);
		}
	}

	public int update(ProductVo vo) {
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			con = DBConnection.getConn();
			String sql = "update product set p_name = ?, p_price = ?, s_stock = ? where p_num = ?";
			st = con.prepareStatement(sql);
			st.setString(1, vo.getpName());
			st.setInt(2, vo.getpPrice());
			st.setInt(3, vo.getpStock());
			st.setInt(4, vo.getpNum());
						
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
			String sql = "delete from product where p_num = ?";
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
	
	public ProductVo search(int num) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			con = DBConnection.getConn();
			String sql = "select * from product where p_num = ?";
			st = con.prepareStatement(sql);
			st.setInt(1, num);
			rs = st.executeQuery();
			
			if(rs.next()) {
				return new ProductVo(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
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
	
	public ArrayList<ProductVo> showAll(){
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<ProductVo> proList = new ArrayList<>();
		
		try {
			con = DBConnection.getConn();
			String sql = "select * from product";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			
			while(rs.next()) {
				proList.add(new ProductVo(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
			}
			
			return proList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBConnection.closeConn(con, st, rs);
		}
	}
}

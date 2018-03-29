package pos.jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {
	public static Connection getConn() throws SQLException {
		Connection con = null;

		try {
			Class.forName("oracle.jdbc.OracleDriver");

			// jdbc.properties 파일에서 정보 읽어오기
			Properties pro = new Properties();
			pro.load(new FileReader("jdbc.properties"));

			String url = pro.getProperty("url");
			String user = pro.getProperty("user");
			String pwd = pro.getProperty("password");

			con = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;
	}
	
	public static void closeConn(Connection con, Statement st, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package pro;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class dbconnection {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/sb_c_2021_2nd_t";
		String user = "sb";
		String password = "1234";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("드라이버 로딩 성공");
			
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("연결 성공");
			
			String sql = String.format("select id from article");
			
			//쿼리 실행
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			//여러 행 데이터 읽기
			while( rs.next() ) {
				System.out.printf( "%s %n", rs.getString(1));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("에러: " + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {} 
			if(stmt!=null) try {stmt.close();} catch(SQLException e) {} //stmt close
		}
	}
}

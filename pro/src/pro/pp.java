package pro;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class pp {
	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/sb_c_2021_2nd_t";
		String user = "sb";
		String password = "1234";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("드라이버 로딩 성공");
			
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("연결 성공");
			
			String URL = "https://cafe.naver.com/nikego/" ;
			org.jsoup.Connection conn2 = Jsoup.connect(URL);
			System.out.println(URL);
			
			Document document = conn2.get();
			
			Elements parsingDivs = document.getElementsByClass("parsingDiv"); // class가 parsingDiv인 element 찾기
			Element parsingDiv = parsingDivs.get(0);
			
			Element parsingTitle = parsingDiv.getElementById("title_area"); // id가 parsingTitle인 element 찾기
			Element partsingContents = parsingDiv.getElementById("se-main-containe"); // id가 parsingContents인 element 찾기
			
			String title = parsingTitle.getElementsByTag("h3").get(0).text(); // 첫 번째 h3태그의 text값 찾기
			String contents = partsingContents.getElementsByTag("<div").get(0).text(); // 첫 번째 p태그의 text값 찾기
			
			System.out.println("파싱한 제목: " + title);
			System.out.println("파싱한 내용: " + contents);
			
			String sql = "INSERT INTO TESTTABLE (BODY) VALUES (?)";
			
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            
            pstmt.executeUpdate();
            System.out.println(sql);
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(pstmt!=null) {pstmt.close();}            
            }catch(Exception e) {
                e.printStackTrace();
            }
            try {
                if(conn!=null) {conn.close();}
            }catch(Exception e) {
                e.printStackTrace();
            }
        }

	}
	
}
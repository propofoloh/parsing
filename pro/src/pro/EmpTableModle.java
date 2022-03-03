package pro;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class EmpTableModle {
	public static void main(String[] args) {
		
		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

		String url = "jdbc:mysql://ls-a8f0ab1c2fff58b463fa2f5db70ef70f92ff71ea.cur6vfnjyk64.ap-northeast-2.rds.amazonaws.com:3306/sb_c_2021_2nd_t";
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
			
			for(long i=1131969L; i<=1131970L; i ++) {
			String URL = "http://web.humoruniv.com/board/humor/read.html?table=pds&pg=0&number=" + i;
			org.jsoup.Connection conn2 = Jsoup.connect(URL);
			
			System.out.println(URL); // URL 출력
			
			Document document = conn2.get();
			
			// System.out.println(document.text()); //페이지 전체 출
			
			
			//제목 class는 . / id는 #
			Element ele = document.select("span#ai_cm_title").get(0);
			String title = ele.text();	
			System.out.println(ele.text());    
			
			//제목
			Element ele2 = document.select("div.body_editor").get(0);
			String contents = ele2.html();	
			System.out.println(ele2.html());    

			String sql = "INSERT INTO article (id, regDate, updateDate, memberId, boardId, title, body, hitCount, goodReactionPoint, badReactionPoint) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
            pstmt = conn.prepareStatement(sql);
            pstmt.setTimestamp(1, date);
            pstmt.setTimestamp(2, date);
            pstmt.setString(3, "1");
            pstmt.setString(4, "2");
            pstmt.setString(5, title);
            pstmt.setString(6, contents);
            pstmt.setString(7, "100");
            pstmt.setString(8, "100");
            pstmt.setString(9, "0");
            
            pstmt.executeUpdate();
            System.out.println(sql);
			}
		} catch (HttpStatusException e) {
			e.printStackTrace();
			System.out.println("HttpStatusException 발생");
		} catch (IOException e) {
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
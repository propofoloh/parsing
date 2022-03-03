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

public class ppUI {
	public static void main(String[] args) throws IOException{
		
		String url = "https://www.fmkorea.com/best/4336920916";
        
    	Document doc = Jsoup.connect(url).get();
	System.out.println(doc.text());
	
	Elements ele = doc.select("span.np_18px_span");
	System.out.println(ele.text());    
	
}
}
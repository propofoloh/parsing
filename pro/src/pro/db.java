package pro;

public class db {
	public static void main(String[] args) {
		
		System.out.println("시작");
		try {
			//익셉션으로 가지않으면 동적 클래스 로딩
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("드라이버 로딩이 완료되었습니다.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("에러"+e.getMessage());
			
		}
		//연결을 하기 위해서는 url(ip, port, database) / id / password가 필요하다.
		System.out.println("끝 ");
	}
}

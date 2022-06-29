package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DAO {

	//Oracle DB 정보
	private String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
	private String oracleUrl= "jdbc:oracle:thin:@localhost:1521:xe";
	private String connectedId= "kbb";
	private String connectedPwd = "kbb";
	
	//모든 자식 클래스에서 공통으로 사용할 필드
	protected Connection conn;
	protected Statement stmt;
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	
	public DAO() {
		//dbConfig();
	}	//한번만 실행시키고자한다면 생성자 안에서 디비컨피그 해줘도됨 
	
	//DB에 접속하는 메소드
	public void connect() {
		//dbConfig(); ->커넥트 실행될때마다, 정보를 가지고오는 디비컨피그 실행되게 => 생성자에서 한번만 주는걸로 위에 바꿈
		
		try {
			Class.forName(jdbcDriver);
			
			conn = DriverManager.getConnection(oracleUrl, connectedId, connectedPwd);
			
		} catch(ClassNotFoundException e) {	//classNot found 익셉션과 sql 익셉션은 따로 관리하는 것이 좋음
			System.out.println("jdbc driver 로딩 실패");
		} catch(SQLException e) {
			System.out.println("DB 연결 실패");
		} 
		
	}
	
/*	
	//DB 정보를 가져오는 메소드 -> 원래는 접속,해제 메소드 두개만 있으면 되는데 -> properties 파일로 관리하기때문에 필요한 것
	private void dbConfig() {
		String resource = "config/db.properties";
		Properties properties = new Properties();
		
		try {
			String filePath = ClassLoader.getSystemClassLoader().getResource(resource).getPath();
			properties.load(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		jdbcDriver = properties.getProperty("driver");
		oracleUrl = properties.getProperty("url");
		connectedId = properties.getProperty("id");
		connectedPwd = properties.getProperty("password");
	}
*/
	//DB 접속을 해제하는 메소드
	public void disconnect() {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
			
		} catch(SQLException e) {	//catch구문 안에 비워놓지 않기!
			e.printStackTrace();
		}
	}
	
	
	
}

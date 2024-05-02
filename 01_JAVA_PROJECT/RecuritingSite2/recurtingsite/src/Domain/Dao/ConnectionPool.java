package Domain.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionPool {

	protected String url ="jdbc:mysql://localhost:3306/recurtingsite";
	protected String id = "root";
	protected String pw = "1234";
	
	protected Connection conn =null;
	protected PreparedStatement pstmt = null;
	protected ResultSet rs = null;
	
	// 싱글톤 패턴 시작 (객체 생성 단일화)
	private static ConnectionPool instance;
	public static ConnectionPool getInstance() throws Exception {
		if(instance==null)
			instance=new ConnectionPool();
		return instance;
	}
	//싱글톤 패턴 끝
	
	private ConnectionPool() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url,id,pw);
		System.out.println("ConnectionPool's DB Connected...");
	}

	public Connection getConnetion() {
		return conn;
	}
}

package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/*
*//**
 * 工具类
 * @author 疯人愿
 *
 *//*
*/
public class JDBC {
	//自定义方法提供一个JDBC和数据库的正确连接
	public static Connection getConnection() throws Exception{
		Connection conn = null;
		Properties dbProperties = new Properties();
		try {
			dbProperties.load(JDBC.class.getClassLoader().getResourceAsStream("db.properties"));
			String dirverClassName = dbProperties.getProperty("jdbc.driverClassName");
			String url = dbProperties.getProperty("jdbc.url");
			String username = dbProperties.getProperty("jdbc.username");
			String password = dbProperties.getProperty("jdbc.password");
			Class.forName(dirverClassName);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
public static void closeConnection(Connection conn) {
	if (conn != null) {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
public static void main(String[] args) throws Exception {
	Connection conn = JDBC.getConnection();
	try {
		boolean flag = conn.isClosed();
		System.out.println(flag);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}

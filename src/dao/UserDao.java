package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pojo.User;
import utils.JDBC;

/** conn就是数据库和jdbc的连接
 * Connection conn = JDBC.getCnnection();
 * 编写sql语句
 * insert into t_user values(用户信息)
 * 根据xonn，生成一个预编译对象
 * PrepareStatement ps = conn.PrepareStatement(sql);
 * 给这个预编译对象赋值
 * ps.setString("uname");
 * 更新数据表
 * ps.executeUpDate();

 * 数据访问层
 * @author 疯人愿
 *
 */
public class UserDao {
	//实现前端页面的数据插入到t_user表中
	public void insertUser(User user) throws Exception{
		//1.获取JDBC和数据库的连接
		Connection conn = JDBC.getConnection();
		//2.编写sql语句
		String sql = "insert into t_user(phone,uname,upwd,email,role) values(?,?,?,?,?)";
		//3.根据连接，生成一个预编译对象
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			// 4.给预编译对象赋值
			ps.setString(1,user.getPhone());
			ps.setString(2,user.getUname());
			ps.setString(3,user.getUpwd());
			ps.setString(4,user.getEmail());
			ps.setInt(5,user.getRole());
			//5.执行sql语句
			ps.executeUpdate();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally {
			JDBC.closeConnection(conn);
		}
	}
	//登录功能，查询t_user
	public User selsectByLogin(String uname,String upwd,int role) throws Exception  {
		User user = null;
		//1.获取JDBC和数据库的连接（桥梁Connection对象）
		Connection conn = JDBC.getConnection();
		//2.编写sql语句
		String sql = "select * from t_user where uname=? and upwd=? and role=?";
		//3.根据连接生成预编译对象
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			//4.对预编译对象赋值
			ps.setString(1, uname);
			ps.setString(2, upwd);
			ps.setInt(3, role);
			//5.执行查询语句，获取到结果集--ResultSet
			ResultSet rs = ps.executeQuery();
			//6.处理结果集--把数据封装成user对象，通过返回user对象得到结果集
			if (rs.next()) {
				user = new User();
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setRole(rs.getInt("role"));
				user.setUname(rs.getString("uname"));
				user.setUpwd(rs.getString("upwd"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	//ajax异步校验注册用户名
	public User ajaxCheckUname(String uname,int role) throws Exception{
		User user = null;
		//1、获取数据库的连接
		Connection connection = JDBC.getConnection();
		//2、编写SQL语句
		String sql = "select * from t_user where uname = ? and role = ?";
	
		try {
			//3、获取语句预编译对象
			PreparedStatement ps = connection.prepareStatement(sql);
			//4、为预编译对象赋初值
			ps.setString(1, uname);
			ps.setInt(2, role);
			//5、执行sql语句
			ResultSet rs = ps.executeQuery();
			//6、将查询的结果集封装到user对象中
			if(rs.next()) {
				user = new User();
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setRole(rs.getInt("role"));
				user.setUname(rs.getString("uname"));
				user.setUpwd(rs.getString("upwd"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBC.closeConnection(connection);
		}
		return user;
	}
	//使用ajax校验邮箱是否存在
	public User ajaxCheckEmail(String email) throws Exception {
		User user = null;
		//1、获得数据库连接
		Connection connection = JDBC.getConnection();
		//2、编写sql语句
		String sql = "select * from t_user where email = ?";
		//3、通过连接获取一个预编译对象
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			//4、为预编译对象赋初值
			ps.setString(1, email);
			//5、执行sql语句
			ResultSet rs = ps.executeQuery();
			//6、将结果集封装到user对象中
			if(rs.next()) {
				user = new User();
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setRole(rs.getInt("role"));
				user.setUname(rs.getString("uname"));
				user.setUpwd(rs.getString("upwd"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBC.closeConnection(connection);
		}
		return user;
	}
	//使用ajax校验手机号是否存在
	public User ajaxCheckPhone(String phone) throws Exception {
		User user = null;
		//1、获得数据库连接
		Connection connection = JDBC.getConnection();
		//2、编写sql语句
		String sql = "select * from t_user where phone = ?";
		//3、获得预编译对象
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			//4、为预编译对象赋初值
			ps.setString(1, phone);
			//5、执行sql语句
			ResultSet rs = ps.executeQuery();
			//6、将得到的结果集封装到user对象中
			if(rs.next()) {
				user = new User();
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setRole(rs.getInt("role"));
				user.setUname(rs.getString("uname"));
				user.setUpwd(rs.getString("upwd"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBC.closeConnection(connection);
		}
		
		
		return user;
	}
	//使用ajax校验用户密码
		public User ajaxCheckUpwd(String upwd,String phone,int role) throws Exception {
			User user = null;
			//1、获得数据库连接
			Connection connection = JDBC.getConnection();
			//2、编写sql语句
			String sql = "select * from t_user where phone = ? and upwd = ? and role = ?";
			//3、获得预编译对象
			try {
				PreparedStatement ps = connection.prepareStatement(sql);
				//4、为预编译对象赋初值
				ps.setString(1, phone);
				ps.setString(2, upwd);
				ps.setInt(3, role);
				//5、执行sql语句
				ResultSet rs = ps.executeQuery();
				//6、将得到的结果集封装到user对象中
				while (rs.next()) {
					user = new User();
					user.setEmail(rs.getString("email"));
					user.setPhone(rs.getString("phone"));
					user.setRole(rs.getInt("role"));
					user.setUname(rs.getString("uname"));
					user.setUpwd(rs.getString("upwd"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBC.closeConnection(connection);
			}
			return user;
		}
	//使用ajax修改用户密码
	public void updateUpwd(User user,String npwd) throws Exception {
		//1、获得数据库连接
		Connection connection = JDBC.getConnection();
		//2、编写sql语句
		String sql = "update t_user set upwd = ? where phone = ?";
		//3、获得预编译对象
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			//4、为预编译对象赋初值
			ps.setString(1, npwd);
			ps.setString(2, user.getPhone());
			//5、执行sql语句
			int row = ps.executeUpdate();
			System.out.println("修改用户密码受影响的行数"+row);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBC.closeConnection(connection);
			}
	}
}

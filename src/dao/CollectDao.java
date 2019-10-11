package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pojo.Book;
import pojo.Collect;
import pojo.User;
import utils.JDBC;

public class CollectDao {
	// 判断当前收藏夹是否已收藏
	public Collect selectByCollect(Collect collect) throws Exception {
		// 1、获取数据库连接
		Connection connection = JDBC.getConnection();
		// 2、编写sql语句
		String sql = "select * from t_collect where uid = ? and book = ?";
		// 3、获得一个预编译对象
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			// 4、为预编译对象赋初值
			ps.setString(1, collect.getUid());
			ps.setString(2, collect.getBook());
			// 5、执行SQL语句
			ResultSet rs = ps.executeQuery();
			// 6、将返回的结果集封装到collect对象中
			while(rs.next()) {
				collect = new Collect();
				collect.setRid(rs.getInt("rid"));
				collect.setUid(rs.getString("uid"));
				collect.setBook(rs.getString("book"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// 7、关闭数据库连接
			JDBC.closeConnection(connection);
		}
		if (collect.getRid() == null) {
			return null;
		}
		return collect;
	}

	// ajax收藏商品
	public void insert(Collect collect) throws Exception {

		// 1、获取数据库连接
		Connection connection = JDBC.getConnection();
		// 2、编写sql语句
		String sql = "insert into t_collect(uid,book) values(?,?)";
		// 3、获得一个预编译对象
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			// 4、为预编译对象赋初值
			ps.setString(1, collect.getUid());
			ps.setString(2, collect.getBook());
			// 5、执行SQL语句
			int row = ps.executeUpdate();
			System.out.println("添加收藏时受影响的行数：" + row);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 6、关闭数据库连接
			JDBC.closeConnection(connection);
		}
	}

	// ajax取消商品
	public void delete(Collect collect) throws Exception {
		// 1、获取数据库连接
				Connection connection = JDBC.getConnection();
				// 2、编写sql语句
				String sql = "delete from t_collect where uid = ? and book = ?";
				// 3、获得一个预编译对象
				try {
					PreparedStatement ps = connection.prepareStatement(sql);
					// 4、为预编译对象赋初值
					ps.setString(1, collect.getUid());
					ps.setString(2, collect.getBook());
					// 5、执行SQL语句
					int row = ps.executeUpdate();
					System.out.println("添加收藏时受影响的行数：" + row);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					// 6、关闭数据库连接
					JDBC.closeConnection(connection);
				}
			}

	// 根据用户对象的id，查询收藏夹的内容
	public ArrayList<Book> selectByCollect(User user) throws Exception {
		ArrayList<Book> list = new ArrayList<>();
		//1、获取数据库连接
		Connection connection = JDBC.getConnection();
		//2、编写sql语句
		String sql = "select * from t_book where isbn in(select book from t_collect where uid = ?)";		
		//3、获得一个预编译对象
		PreparedStatement ps = connection.prepareStatement(sql);
		//4、为预编译对象赋初值
		ps.setString(1, user.getPhone());
		//5、执行SQL语句
		ResultSet rs = ps.executeQuery();
		//6、封装结果集到book对象中
		while(rs.next()) {
			Book book = new Book();
			book.setAuthor(rs.getString("author"));
			book.setEdition(rs.getInt("edition"));
			book.setForm(rs.getString("form"));
			book.setFormat(rs.getString("format"));
			book.setIsbn(rs.getString("isbn"));
			book.setPackaging(rs.getString("packaging"));
			book.setPages(rs.getInt("pages"));
			book.setPress(rs.getString("press"));
			book.setPrice(rs.getDouble("price"));
			book.setPublished(rs.getDate("published"));
			book.setTitle(rs.getString("title"));
			book.setWords(rs.getInt("words"));
			list.add(book);
		}
		return list;
	}
	
}

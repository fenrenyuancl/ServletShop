package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.Book;
import utils.JDBC;

public class BookDao {
	//查询全部书籍，并封装到list集合
	public List<Book> selectAllBook() throws Exception{
		List<Book> list = new ArrayList<Book>();
		//1、获取数据库的连接
		Connection conn = JDBC.getConnection();
		//2、编写sql语句
		String sql = "select * from t_book";
		//3、获取语句预编译对象
		PreparedStatement ps = conn.prepareStatement(sql);
		//4、执行查询结果
		ResultSet rs = ps.executeQuery();
		//5、循环遍历结果集
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
	//页面详情
	public Book selectDetailByIsbn(String isbn) throws Exception  {
		Book book = null;
		//1、获取数据库的连接
				Connection conn = JDBC.getConnection();
				//2、编写sql语句
				String sql = "select * from t_book where isbn =?";
				//3、获取语句预编译对象
				PreparedStatement ps = conn.prepareStatement(sql);
				//4、为预编译对象赋值
				ps.setString(1, isbn);
				//5、执行查询结果
			
				try {
					ResultSet rs = ps.executeQuery();
					//6、封装结果集到book对象中
					while(rs.next()) {
						book = new Book();
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
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		return book;
		
	}
}

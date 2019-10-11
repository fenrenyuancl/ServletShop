package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pojo.Address;
import pojo.Book;
import pojo.Cart;
import pojo.CartAndBook;
import utils.JDBC;

public class OrderDao {
	//购物车下单，然后生成订单列表
	public ArrayList<CartAndBook> selectAllCartAndBook(String rid) throws Exception {
		ArrayList<CartAndBook> list = new ArrayList<CartAndBook>();
		//1、获取数据库的连接
				Connection connection = JDBC.getConnection();
				//2、编写sql语句
				String sql = "select b.*,c.*from t_cart c inner join t_book b on c.book = b.isbn where c.rid IN ("+rid+")";
				//3、获取语句预编译对象
				try {
					PreparedStatement ps = connection.prepareStatement(sql);
					//4、为语句对象赋值
					//5、执行SQL语句
					ResultSet rs = ps.executeQuery();
					//循环遍历结果
					while(rs.next()) {
						Cart cart = new Cart();
						cart.setCount(rs.getInt("count"));
						cart.setRid(rs.getInt("rid"));
						cart.setUid(rs.getString("uid"));
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
						CartAndBook cartAndBook = new CartAndBook();
						cartAndBook.setBook(book);
						cartAndBook.setCart(cart);
						list.add(cartAndBook);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return list;
	}
}

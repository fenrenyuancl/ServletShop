package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.Book;
import pojo.Cart;
import pojo.CartAndBook;
import pojo.User;
import utils.JDBC;

public class CartDao {
	//ajax添加购物车
	public void insert(Cart cart) throws Exception  {
		//1、获取数据库连接
		Connection connection = JDBC.getConnection();
		//2、编写sql语句
		String sql = "insert into t_cart(uid,book,count) values(?,?,?)";		
		//3、获得一个预编译对象
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			//4、为预编译对象赋初值
			ps.setString(1, cart.getUid());
			ps.setString(2, cart.getBook());
			ps.setInt(3, cart.getCount());
			//5、执行sql语句
			int row = ps.executeUpdate();
			System.out.println("受影响的行数："+row);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBC.closeConnection(connection);
		}
	}
	//ajax删除购物车
		public void deleteByRid(int rid) throws Exception  {
			//1、获取数据库连接
			Connection connection = JDBC.getConnection();
			//2、编写sql语句
			String sql = "delete from t_cart where rid = ?";		
			//3、获得一个预编译对象
			try {
				PreparedStatement ps = connection.prepareStatement(sql);
				//4、为预编译对象赋初值
				ps.setInt(1, rid);
				//5、执行sql语句
				int row = ps.executeUpdate();
				System.out.println("删除购物车时受影响的行数："+row);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBC.closeConnection(connection);
			}
		}
		//ajax更新购物车数量
		public void updateCartByRid(int rid,int num) throws Exception  {
			//1、获取数据库连接
			Connection connection = JDBC.getConnection();
			//2、编写sql语句
			String sql = "update t_cart set count=? where rid = ?";		
			//3、获得一个预编译对象
			try {
				PreparedStatement ps = connection.prepareStatement(sql);
				//4、为预编译对象赋初值
				ps.setInt(1, num);
				ps.setInt(2, rid);
				//5、执行sql语句
				int row = ps.executeUpdate();
				System.out.println("更新购物车数量时受影响的行数："+row);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						JDBC.closeConnection(connection);
					}
				}
	//查询所有的购物车信息
	public List<CartAndBook> selectAllCartAndBook(User user) throws Exception{
		List<CartAndBook> list = new ArrayList<CartAndBook>();
		//1、获取数据库连接
		Connection connection = JDBC.getConnection();
		//2、编写sql语句
		String sql = "select b.*,c.* from t_cart c inner join t_book b on c.book = b.isbn where c.uid = ?";		
		//3、获得一个预编译对象
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			//4、为预编译对象赋初值
			ps.setString(1, user.getPhone());
			//5、执行sql语句
			ResultSet rs = ps.executeQuery();
			//6、遍历结果集
			while(rs.next()) {
				Cart cart = new Cart();
				cart.setBook(rs.getString("book"));
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
		}finally {
			JDBC.closeConnection(connection);
		}
		return list;
	}
}

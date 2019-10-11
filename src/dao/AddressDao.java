package dao;





import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pojo.Address;
import pojo.User;
import utils.JDBC;

public class AddressDao {
	//添加收货地址
	public void insert(Address address) throws Exception  {
		//1、获取数据库的连接
		Connection connection = JDBC.getConnection();
		//2、编写sql语句
		String sql = "insert into t_address(uid,address,added,receiver,rPhone) values(?,?,?,?,?)";
		//3、获取语句预编译对象
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			//4、为预编译对象赋值
			ps.setString(1, address.getUid());
			ps.setString(2, address.getAddress());
			ps.setDate(3, new java.sql.Date(address.getAdded().getTime()));
			ps.setString(4, address.getReceiver());
			ps.setString(5, address.getrPhone());
			//5、执行查询结果
			int row = ps.executeUpdate();
			System.out.println("添加地址时受影响的行数"+row);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBC.closeConnection(connection);
		}
	}
	//查询订单
	public ArrayList<Address> selectAddressByUser(User user) throws Exception{
		ArrayList<Address> list = new ArrayList<Address>();
		//1、获取数据库的连接
		Connection connection = JDBC.getConnection();
		//2、编写sql语句
		String sql = "select * from t_address where uid = ?";
		//3、获取语句预编译对象
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			//4、为语句对象赋值
			ps.setString(1, user.getPhone());
			//5、执行SQL语句
			ResultSet rs = ps.executeQuery();
			//循环遍历结果
			while(rs.next()) {
				Address address = new Address();
				address.setAdded(rs.getDate("added"));
				address.setAddress(rs.getString("address"));
				address.setReceiver(rs.getString("receiver"));
				address.setRid(rs.getInt("rid"));
				address.setrPhone(rs.getString("rPhone"));
				address.setUid(rs.getString("uid"));
				list.add(address);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}

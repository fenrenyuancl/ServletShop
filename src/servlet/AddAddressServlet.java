package servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AddressDao;
import pojo.Address;
import pojo.User;

public class AddAddressServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取页面中的信息
		String receiver = request.getParameter("receiver");
		String address = request.getParameter("address");
		String receiverPhone = request.getParameter("receiverPhone");
		//获取user对象
		User user = (User) request.getSession().getAttribute("user");
		String uid = user.getPhone();
		//数据封装
		Address add =new Address();
		add.setAdded(new Date());
		add.setAddress(address);
		add.setReceiver(receiverPhone);
		add.setrPhone(receiverPhone);
		add.setUid(uid);
		//调用dao层
		AddressDao addressDao = new AddressDao();
		try {
			addressDao.insert(add);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			//ajax返回浏览器结果
			Writer out = response.getWriter();
			out.write("yes");
			out.close();
	}
}

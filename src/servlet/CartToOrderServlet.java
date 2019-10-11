package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AddressDao;
import dao.OrderDao;
import pojo.Address;
import pojo.CartAndBook;
import pojo.User;

public class CartToOrderServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取页面中的数据
		String rid = request.getParameter("rid");
		//获取登录用户的信息
		User user = (User) request.getSession().getAttribute("user");
		//调用dao
		AddressDao addressDao = new AddressDao();
		try {
			ArrayList<Address> addressList = addressDao.selectAddressByUser(user);
			request.setAttribute("addressList", addressList);
			//生成订单列表
			OrderDao orderDao = new OrderDao();
			ArrayList<CartAndBook> orderList = orderDao.selectAllCartAndBook(rid);
			request.setAttribute("orderList", orderList);
			//页面跳转
			request.getRequestDispatcher("order-confirm.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

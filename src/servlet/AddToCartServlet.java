package servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;
import pojo.Cart;
import pojo.User;

public class AddToCartServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取当前用户信息
		User user = (User) request.getSession().getAttribute("user");
		//获取页面出入的商品数量和商品标号
		String product = request.getParameter("product");
		int count = Integer.parseInt(request.getParameter("count"));
		//2、把数据封装到cart购物车对象中
		Cart cart = new Cart();
		cart.setBook(product);
		cart.setCount(count);
		cart.setUid(user.getPhone());
		//调用dao层方法，插入到数据库
		CartDao cartDao = new CartDao();
		try {
			cartDao.insert(cart);
			//3、ajax返回对象
			Writer out = response.getWriter();
			out.write("yes");
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

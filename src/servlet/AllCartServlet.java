package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;
import pojo.CartAndBook;
import pojo.User;

public class AllCartServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取当前用户信息
		User user = (User) request.getSession().getAttribute("user");
		//2、调用dao层方法
		CartDao cartDao = new CartDao();
		
		try {
			ArrayList<CartAndBook> list = (ArrayList<CartAndBook>) cartDao.selectAllCartAndBook(user);
			//封装数据
			request.setAttribute("cartAndBooks", list);
			//3、页面跳转
			request.getRequestDispatcher("cart.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

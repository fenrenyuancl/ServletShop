package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CollectDao;
import pojo.Book;
import pojo.User;

public class AllCollectServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取用户登陆的信息
		User user = (User) request.getSession().getAttribute("user");
		//2、调用dao层方法
		CollectDao collectDao = new CollectDao();
		try {
			ArrayList<Book> list = collectDao.selectByCollect(user);
			//封装数据
			request.setAttribute("books", list);
			//3、页面跳转
			request.getRequestDispatcher("collect.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

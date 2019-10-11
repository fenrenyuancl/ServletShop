package servlet;
/**
 * 登录功能
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import pojo.User;

public class UserLoginServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//1.获取登陆页面发送的数据
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("upwd");
		int role = 0;
	//2.根据获取到的数据作为条件从t_user表中查询
		UserDao userDao = new UserDao();
		User user = new User();
		try {
			user=userDao.selsectByLogin(uname, upwd, role);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//3.根据查询结果进行页面跳转（该用户已经注册、查无此人）
		if (user != null) {
			//页面跳转--商品首页--index.html
			//把真实存在的信息user对象封装到request
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("AllBookServlet").forward(request, response);
		} else {
			//页面跳转--登陆页面
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}

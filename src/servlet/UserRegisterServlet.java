package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import pojo.User;

/**
 * 自定义类，用来处理注册功能的业务逻辑
 * 
 * @author 疯人愿
 *
 */
public class UserRegisterServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.获取前端页面发送的信息
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("upwd");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		int role = 0;
		
		// 把前端页面过去到的数据，封装成user对象
		User user = new User();
		user.setUname(uname);
		user.setUpwd(upwd);
		user.setPhone(phone);
		user.setEmail(email);
		user.setRole(role);
		// 2.处理这些信息（把数据插入到t_user表中）
		UserDao userDao = new UserDao();
		try {
			userDao.insertUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		// 3.页面跳转-->登陆页面
		response.sendRedirect("login.html");
	}
}

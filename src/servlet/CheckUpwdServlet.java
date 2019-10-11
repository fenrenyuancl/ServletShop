package servlet;

import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import pojo.User;

public class CheckUpwdServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取用户信息
		User user = (User) request.getSession().getAttribute("user");
		//获取页面的信息
		String upwd = request.getParameter("upwd");
		//2、调用dao层
		UserDao userDao = new UserDao();
		try {
			User result = userDao.ajaxCheckUpwd(upwd, user.getPhone(), user.getRole());
			//3、定义输出流信息到前端页面
			Writer out = response.getWriter();
			//4、判断是否存在该用户
			if (result == null) {
				out.write("yes");
			} else {
				out.write("no");
			}
			//5、关闭输出流
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

package servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import pojo.User;

public class CheckEmailServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获得页面参数
		String email = request.getParameter("email");
		//2、调用dao层
		UserDao userDao = new UserDao();
		try {
			User user = userDao.ajaxCheckEmail(email);
			//3、定义输出流信息到前端页面
			Writer out = response.getWriter();
			//4、判断是否存在该邮箱
			if (user == null) {
				out.write("no");
			} else {
				out.write("yes");
			}
			//5、关闭输出流
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import dao.UserDao;
import pojo.User;

public class CheckUnameServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取数据请求
		String uname = request.getParameter("uname");
		//2、调用dao层
		UserDao userDao = new UserDao();
		try {
			User user = userDao.ajaxCheckUname(uname, 0);
			//3、定义输出流信息到前端页面
			Writer out = response.getWriter();
			//4、判断是否存在该用户
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

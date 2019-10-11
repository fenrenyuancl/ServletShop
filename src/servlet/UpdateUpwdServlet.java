package servlet;



import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import pojo.User;

public class UpdateUpwdServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取用户信息
		User user = (User) request.getSession().getAttribute("user");//旧用户
		String npwd = request.getParameter("npwd");//新密码
		//2、调用dao层
		UserDao userDao = new UserDao();
		try {
			userDao.updateUpwd(user, npwd);
			//3、ajax返回
			Writer out = response.getWriter();
			out.write("yes");
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}		
}

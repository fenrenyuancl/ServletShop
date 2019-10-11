package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;

public class DeleteCartServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取页面数据
		String rid = request.getParameter("rid");
		//2、调用dao曾的方法
		CartDao cartDao = new CartDao();
		try {
			cartDao.deleteByRid(Integer.parseInt(rid));
			//3、返回ajax的结果
			request.getRequestDispatcher("allCartServlet").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

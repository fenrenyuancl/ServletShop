package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;

public class UpdateCartNumServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取页面中的数据
		String rid = request.getParameter("rid");
		String num = request.getParameter("num");
		//调用dao层
		CartDao cartDao = new CartDao();
		try {
			cartDao.updateCartByRid(Integer.parseInt(rid), Integer.parseInt(num));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.CollectDao;
import pojo.Book;
import pojo.Collect;
import pojo.User;

public class DetailBookServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取页面中的数据
		String isbn = request.getParameter("isbn");
		//2、调用dao层
		BookDao bookDao = new BookDao();
		try {
			Book book = bookDao.selectDetailByIsbn(isbn);
			//3、把book对象封装到request对象中
			request.setAttribute("book", book);
					//获取用户收藏夹功能	--开始	
					//获取当前用户信息
					User user = (User) request.getSession().getAttribute("user");
					//获取页面出入的商品数量和商品标号
					String product = request.getParameter("isbn");
					//封装数据
					Collect target = new Collect();
					target.setBook(product);
					target.setUid(user.getPhone());
					//查询数据库
					CollectDao collectDao = new CollectDao();
					Collect result = collectDao.selectByCollect(target);
					//设计在页面中显示的图片编号
					if (result != null) {
						request.setAttribute("isCollect", "2");
					} else {
						request.setAttribute("isCollect", "");
					}//获取用户收藏家功能--结束
			//4、页面的跳转
			request.getRequestDispatcher("detail.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

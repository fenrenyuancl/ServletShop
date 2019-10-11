package servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CollectDao;
import pojo.Collect;

public class CollectYesOrNoServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取前端数据
		String uid = request.getParameter("uid");
		String isbn = request.getParameter("isbn");
		//封装数据
		Collect target = new Collect();
		target.setUid(uid);
		target.setBook(isbn);
		//2、调用dao层方法
		CollectDao collectDao = new CollectDao();
		try {
			Collect result = collectDao.selectByCollect(target);
			//判断是否收藏该商品
			if (result != null) {//已经收藏了该商品
				collectDao.delete(target);//取消收藏商品
				request.setAttribute("isCollect", "");
			} else {//没有收藏该商品
				collectDao.insert(target);//收藏商品
				request.setAttribute("isCollect", "2");
			}
			//3、定义输出流，在页面显示结果
			Writer out = response.getWriter();
			out.write("yes");
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

<%@page import="pojo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="pojo.Book"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>我的书城首页</title>
<link href="../css/item.food.css" rel="stylesheet" />
<link href="../css/header.css" rel="stylesheet" />
<link href="../css/footer.css" rel="stylesheet" />
<link href="../css/slide.css" rel="stylesheet" />
</head>
<body>
	<!-- 页面顶部-->
	<header id="top">
		<div id="logo" class="lf">
			<img src="../img/header/shop.png" alt="logo" />
		</div>
		<div id="top_input" class="lf">
			<input id="input" type="text" placeholder="请输入您要搜索的内容" /> <a
				href="../page/search.html?title=Java" class="rt"><img
				id="search" src="../img/header/search.png" alt="搜索" /></a>
		</div>
		<%
   		User user = (User)request.getSession().getAttribute("user");
   		%>
		<span><h1>
				欢迎[<%=user.getUname()%>]登录
			</h1></span>
			<%@ include file="menu.jsp"%>
	</header>
	<!-- nav主导航-->
	<div>&nbsp;</div>
	<!-- banner部分-->
	<div class="ck-slide">
		<ul class="ck-slide-wrapper">
			<li><a href="detail.html"><img
					src="../img/banner/banner1.png" alt=""></a></li>
			<li style="display: none"><a href="detail.html"><img
					src="../img/banner/banner2.png" alt=""></a></li>
			<li style="display: none"><a href="detail.html"><img
					src="../img/banner/banner3.png" alt=""></a></li>
			<li style="display: none"><a href="detail.html"><img
					src="../img/banner/banner4.png" alt=""></a></li>
			<li style="display: none"><a href="detail.html"><img
					src="../img/banner/banner5.png" alt=""></a></li>
		</ul>
		<a href="javascript:;" class="ctrl-slide ck-prev">上一张</a> <a
			href="javascript:;" class="ctrl-slide ck-next">下一张</a>
		<div class="ck-slidebox">
			<div class="slideWrap">
				<ul class="dot-wrap">
					<li class="current"><em>1</em></li>
					<li><em>2</em></li>
					<li><em>3</em></li>
					<li><em>4</em></li>
					<li><em>5</em></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- 特推部分-->

	<!--商品-->
	<div class="store">
		<div class="store_top">
			<img src="../img/banner/icon.png" alt="" /> 热搜排行
		</div>
		<%
    ArrayList<Book> books = (ArrayList<Book>)request.getAttribute("books");
    System.out.println(books.size());
    for(int i=0;i<books.size();i++){
      Book book = books.get(i);
      if (i % 4 == 0) {
    %>
		<div class="store_content">
			<%
      }
      %>
			<div>
				<a href="detailBookServlet?isbn=<%=book.getIsbn()%>"><img
					src="../img/goods/<%=book.getIsbn() %>/index.jpg" alt="" /></a>
				<p class="one"><%=book.getTitle() %></p>
				<p class="two">
					<span> 价格：<span>￥<%=book.getPrice() %></span>
					</span>
				</p>
				<p class="three">
					<span> 出版社：<span><%=book.getPress() %></span>
					</span>
				</p>
			</div>
			<%
		if (i % 4 == 3) {
		%>
		</div>
		<%	
		}
		%>
		<%
	}
	%>
	</div>
		<%@ include file="menu-footer.jsp"%>
	<script src="../js/jquery-3.1.1.min.js"></script>
	<script src="../js/index.js"></script>
	<script src="../js/slide.js"></script>
	<script>
    $('.ck-slide').ckSlide({
        autoPlay: true,//默认为不自动播放，需要时请以此设置
        dir: 'x',//默认效果淡隐淡出，x为水平移动，y 为垂直滚动
        interval:3000//默认间隔2000毫秒
    });
</script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="pojo.CartAndBook" %>
<%@ page import="pojo.Cart" %>
<%@ page import="pojo.Book" %>
<%@ page import="pojo.Order" %>
<%@ page import="pojo.Address" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>确认订单</title>
    <link href="../css/order.confirm.css" rel="stylesheet"/>
    <link href="../css/header.css" rel="stylesheet"/>
    <link href="../css/footer.css" rel="stylesheet"/>
</head>
<body>
<!-- 页面顶部-->
<header id="top">
    <div id="logo" class="lf">
        <img class="animated jello" src="../img/header/shop.png" alt="logo"/>
    </div>
    <div id="top_input" class="lf">
        <input id="input" type="text" placeholder="请输入您要搜索的内容"/>

        <a href="../page/search.html?title=Java" class="rt"><img id="search" src="../img/header/search.png" alt="搜索"/></a>
    </div>
    <%@ include file="menu.jsp"%>
</header>
<div id="navlist">
    <ul>
        <li class="navlist_blue_left"></li>
        <li class="navlist_blue">确认订单信息</li>
        <li class="navlist_blue_arro"><canvas id="canvas_blue" width="20" height="38"></canvas></li>
        <li class="navlist_gray">支付订单<b></b></li>
        <li class="navlist_gray_arro"><canvas id="canvas_gray" width="20" height="38"></canvas></li>
        <li class="navlist_gray">支付完成<b></b></li>
        <li class="navlist_gray_right"></li>
    </ul>
</div>
<!--订单确认-->
<div id="container" class="clear">
    <!--收货地址-->
    <div class="adress_choice">
        <p>选择收货地址</p>
<%
ArrayList<Address> alist = (ArrayList<Address>)request.getAttribute("addressList");
for (int i = 0; i <  alist.size() ; i++) {
	Address address = alist.get(i);
%>
        <div id="addresId1">
			<i class="user_choice">
				<input type="hidden" name="aid" value="<%=address.getRid()%>">
                <input type="radio" name="address" value="<%=address.getAddress()+address.getrPhone() %>" checked />
            </i>
            <i class="address_name">
                <%=address.getReceiver() %>
            </i>
            <i class="user_address">
               <%=address.getAddress()+address.getrPhone() %>
            </i>
        </div>
<%
}
%>
        <a href="set.html">
            更多地址 &gt;&gt;
        </a>
    </div>
    <!-- 商品信息-->
    <form name="" method="post" action="#">
        <div class="product_confirm">
            <p>确认商品信息</p>
            <ul class="item_title">
                <li class="p_info">商品信息</li>
                <li class="p_price">单价(元)</li>
                <li class="p_count">数量</li>
                <li class="p_tPrice">金额</li>
            </ul>
            
<%
	ArrayList<CartAndBook> list = (ArrayList<CartAndBook>)request.getAttribute("orderList");
		for (int i = 0; i <  list.size() ; i++) {
			CartAndBook cartbook = list.get(i);
			Book book = cartbook.getBook();
			Cart cart = cartbook.getCart();
	%>
	<ul class="item_detail">
               <li class="p_info">
                   <b><img src="../img/goods/<%=book.getIsbn() %>/detail1.jpg"/></b>

                   <b class="product_name lf">
                       <%=book.getTitle() %>
                   </b>
                   <br/>
               <span class="product_color ">
                     出版社：<%=book.getPress() %>
               </span>
               </li>
               <li class="p_price">
                   <i>达内专属价</i>
                   <br/>
                   <span class="pro_price">￥<span class="ppp_price"><%=book.getPrice() %></span></span>
               </li>
               <li class="p_count">X<span><%=cart.getCount() %></span></li>
               <li class="p_tPrice">￥<span>
               <%=cart.getCount()*book.getPrice() %></span></li>
           
 	</ul>
 <% 
		}
	%>
	
        </div>
    </form>
    <!-- 结算条-->
    <div id="count_bar">
        <span class="go_cart"><a href="#" >&lt;&lt;返回购物车</a></span>
        <span class="count_bar_info">已选<b  id="count"> 0 </b>件商品&nbsp;&nbsp;合计(不含运费):<b class="zj"></b> <input type="hidden" name="Payment" value=""/>元</span>
        <span class="go_pay">确认并付款</span>
    </div>
</div>
<%@ include file="menu-footer.jsp"%>
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/index.js"></script>
<script>
    var html=0;
    var total=0;
    $(function(){
        $(".item_detail").each(function() {
            html+=1;
            var p=parseFloat($(this).children('.p_price').children('.pro_price').children('.ppp_price').html());
            var sl=parseFloat($(this).children('.p_count').children('span').html());
            var xj=parseFloat(p*sl).toFixed(2);
            $(this).children('.p_tPrice').children('span').html(xj);
            total+=xj-0;
        })
        $("#count").html(html)-0;
        $('.zj').html(total.toFixed(2))-0;
        var input_zj=parseFloat($('.zj').html()).toFixed(2);
        $('#payment').val(input_zj);
    });

    $(".go_pay").click(function () {
        location.href = "payment.html";
    });

    var canvas=document.getElementById("canvas_gray");
    var cxt=canvas.getContext("2d");
    var gray = cxt.createLinearGradient (10, 0, 10, 38);
    gray.addColorStop(0, '#f5f4f5');
    gray.addColorStop(1, '#e6e6e5');
    cxt.beginPath();
    cxt.fillStyle = gray;
    cxt.moveTo(20,19);
    cxt.lineTo(0,38);
    cxt.lineTo(0,0);
    cxt.fill();
    cxt.closePath();

    canvas=document.getElementById("canvas_blue");
    cxt=canvas.getContext("2d");
    var blue = cxt.createLinearGradient (10, 0, 10, 38);
    blue.addColorStop(0, '#27b0f6');
    blue.addColorStop(1, '#0aa1ed');
    cxt.beginPath();
    cxt.fillStyle = blue;
    cxt.moveTo(20,19);
    cxt.lineTo(0,38);
    cxt.lineTo(0,0);
    cxt.fill();
    cxt.closePath();
</script>
</body>
</html>

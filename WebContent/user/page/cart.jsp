<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="pojo.CartAndBook" %>
<%@ page import="pojo.Cart" %>
<%@ page import="pojo.Book" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的商城购物车</title>
    <link href="../css/header.css" rel="stylesheet" />
    <link href="../css/footer.css" rel="stylesheet" />
    <link href="../css/cart.css" rel="stylesheet" />
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
<!-- nav主导航-->

<div class="modal" style="display:none">
    <div class="modal_dialog">
        <div class="modal_header">
            删除提醒
        </div>
        <div class="modal_information">
            <img src="../img/model/model_img2.png" alt=""/>
            <span>确定删除您的这个宝贝吗？</span>
        </div>
        <div class="yes"><span>删除</span></div>
        <div class="no"><span>不删除</span></div>
    </div>
</div>
<div class="modalNo" style="display:none">
    <div class="modal_dialog">
        <div class="modal_header">
            删除提示
            <img src="../img/model/model_img1.png" alt="" class="rt close"/>
        </div>
        <div class="modal_information">
            <img src="../img/model/model_img2.png" alt=""/>
            <span>请选择商品</span>
        </div>
    </div>
</div>
<div class="big">
    <form  name="" action="" method="post">
    <section id="section" >
        <div id="title">
            <b>购物车</b>
            <p>
                已选<span class="total color">0</span>件商品<span class="interval"></span>合计(不含运费):<span class="totalPrices color susum">0.00</span><span class="unit color">元</span>
            </p>
        </div>
        <div id="box" >
            <div id="content_box">
                <div class="imfor_top">
                    <div class="check_top">
                        <div class="all">
                            <span class="normal">
                                <img src="../img/cart/product_normal.png" alt=""/>
                            </span>  <input type="hidden" name="" value="">全选
                        </div>
                    </div>
                    <div class="pudc_top">商品</div>
                    <div class="pices_top">单价(元)</div>
                    <div class="num_top">数量</div>
                    <div class="totle_top">金额</div>
                    <div class="del_top">操作</div>
                </div>
	<%
	ArrayList<CartAndBook> list = (ArrayList<CartAndBook>)request.getAttribute("cartAndBooks");
		for (int i = 0; i <  list.size() ; i++) {
			CartAndBook cartbook = list.get(i);
			Book book = cartbook.getBook();
			Cart cart = cartbook.getCart();
			
	%>
	 			<div class="imfor">
                    <div class="check">
                        <div class="Each">
                            <span class="normal">
                                <img src="../img/cart/product_normal.png" alt=""/>
                            </span>
                            <input type="hidden" name="" value="">
                        </div>
                    </div>
                    <div class="pudc">
                        <div class="pudc_information" id="<%=cart.getRid()%>">
                            <img src="../img/goods/<%=book.getIsbn() %>/detail1.jpg" class="lf"/>
                            <input type="hidden" name="" value="">
                        <span class="des lf">
                         <%=book.getIsbn() %>
                              <input type="hidden" name="" value="">
                        </span>
                            <p class="col lf"><span>作者：</span><span class="color_des"><%=book.getAuthor() %> <input type="hidden" name="" value=""></span></p>
                        </div>
                    </div>
                    <div class="pices">
                        <p class="pices_des"></p>
                        <p class="pices_information"><b>￥</b><span><%=book.getPrice() %><input type="hidden" name="" value=""></span></p>
                    </div>
                    <div class="num"><span class="reduc">&nbsp;-&nbsp;</span><input type="text" value="<%=cart.getCount() %>" ><span class="add">&nbsp;+&nbsp;</span></div>
                    <div class="totle">
                        <span>￥</span>
                        <span class="totle_information"><%=book.getPrice()*cart.getCount() %></span>
                    </div>
                    <div class="del">
                        <a href="javascript:;" class="del_d">删除</a>
                    </div>
                </div>
	<%		
		}
	%>
            </div>
            <div class="foot">
                <div class="foot_check">
                    <div class="all">
                        <span class="normal">
                                <img src="../img/cart/product_normal.png" alt=""/>
                            </span>  <input type="hidden" name="" value="">全选
                    </div>
                </div>
                <div class="foot_cash" id="go-buy">提交订单</div>
                <div class="foot_tol"><span>合计(不含运费):</span><span  class="foot_pices susumOne">0.00</span><span class='foot_des'>元</span></div>
                <div class="foot_selected">
                    已选<span class="totalOne color">0</span>件商品
                </div>
            </div>
        </div>
    </section>
    <div class="none" style="display: none">
      <p class="none_title">购物车</p>
      <div class="none_top"></div>
      <div class="none_content">
        <img src="../img/30.png" alt="" class="lf"/>
        <p class="lf">您的购物车竟然还是空哒( ⊙ o ⊙ )</p>
        <span class="lf">赶快去下单吧！</span>
        <a href="#" class="lf">去购物>></a>
      </div>
    </div>
    </form>
</div>
<%@ include file="menu-footer.jsp"%>
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/cart.js"></script>
<script src="../js/index.js"></script>
<script>
//去结算
var str=[];
$('.foot_cash').click(function(){
  $('.Each>span').each(function(){
    if($(this).hasClass('true')){
      var id=$(this).parent().parent().next().children('.pudc_information').attr('id');
//			var num=$(this).parent().parent().siblings('.num').children('input').val();
      str.push(id);
      alert(str);
    }
  });
  
  console.log(str);
  if(str.length>0){
     var url = 'cartToOrderServlet?itemIds='+str;//'cartToOrder.action?itemIds='+str;
     window.location.href = url;
  }
});
$(function(){
    // 显示空购物车页面
    if($(".imfor").length==0) {
        $('#section').hide();
        $('.none').show();
    }
    //小计和加减
    //加
    $(".add").each(function() {
      $(this).click(function() {
        var $multi = 0;
        var vall = $(this).prev().val();
        vall++;
        $(this).prev().val(vall);
        $multi = (parseInt(vall).toFixed(2) * parseInt($(this).parent().prev().children().eq(1).children().eq(1).text()));
        $(this).parent().next().children().eq(1).text(Math.round($multi).toFixed(2));
        amountadd();
        var id=$(this).parent().siblings('.pudc').children('.pudc_information').attr('id');
        var num=$(this).prev().val();
        $.ajax({
          type: "GET",
          url: "/day01/user/page/updateCartNumServlet",
          data: {rid:id,num:num},
          success: function(data){
          }
        });
      })
    });
    //减
    $(".reduc").each(function() {
  		$(this).click(function() {
  			var $multi1 = 0;
  			var vall1 = $(this).next().val();
  			vall1--;
  			if(vall1 <= 0) {
  				vall1 = 1;
  			}
  			$(this).next().val(vall1);
  			$multi1 = parseInt(vall1) * parseInt($(this).parent().prev().children().eq(1).children().eq(1).text());
  			$(this).parent().next().children().eq(1).text(Math.round($multi1).toFixed(2));
  			amountadd();
  			var id=$(this).parent().siblings('.pudc').children('.pudc_information').attr('id');
  			var num=$(this).next().val();
  			$.ajax({
  				type: "GET",
  				url: "/day01/user/page/updateCartNumServlet",
  				data: {rid:id,num:num},
  				success: function(data){

  				}
  			});
  		})
	   });
    // 删除当前行
    $('.del_d').click(function() {
        var id=$(this).parent().siblings('.pudc').children('.pudc_information').attr('id');
        console.log(id);
        $('.modal').fadeIn();
        $('.no').click(function(){
          $('.modal').fadeOut();
        });
        $('.yes').click(function(){
          var url ='deleteCartServlet?rid='+id;
           window.location.href=url;
        })
    });
});
</script>
</body>
</html>

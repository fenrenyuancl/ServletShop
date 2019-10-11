<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的订单 - 我的书城</title>
    <link href="../css/my.order.css" rel="stylesheet" />
    <link href="../css/header.css" rel="stylesheet" />
    <link href="../css/footer.css" rel="stylesheet" />
    <link href="../css/set.css" rel="stylesheet" />
</head>
<body>
<div id="header">
    <!-- 头部-->
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
    <!-- 主导航-->

    <!-- 主导航-->
    <nav id="nav1">
        <!-- 我的订单导航栏-->
        <div id="nav_order" class="lf">
            <ul>
                <li></li>
            </ul>
        </div>
    </nav>
</div>
</div>
<!--我的订单内容区域 #container-->
<div id="container" class="clearfix">
    <!-- 左边栏-->
    <div id="leftsidebar_box" class="lf">
        <div class="line"></div>
        <dl class="my_order">
            <dt onClick="changeImage()">帐号管理<img src="../img/myOrder/myOrder1.png"></dt>
            <dd class="first_dd"><a href="../page/password-change.jsp">修改密码</a></dd>
            <dd><a href="../page/address-add.jsp">添加地址</a></dd>
        </dl>

    </div>
    <!-- 右边栏-->
    <div class="rightsidebar_box rt" >
        <div class="order_empty">
         <div id="cover" class="rt">
      <form id="fm-add" action="addAddressServlet"  method="post" name="form1">
            <div class="txt">
                <p>添加地址
                    <span>
                        <a href="../page/index.jsp">继续购物</a>
                    </span>
                </p>
                <div class="text">
                    <input type="text" placeholder="收件人" name="receiver" id="receiver" required>
                    <span></span>
                </div>
                <div class="text">
                    <input type="text" placeholder="地址" id="address" name="address" required minlength="6" maxlength="25">
                    <span></span>
                </div>
				<div class="text">
                    <input type="text" placeholder="联系电话" id="receiverPhone" name="receiverPhone" required minlength="11" maxlength="11">
                    <span></span>
                </div>
                <input class="button_login" type="submit" value="添加" id="bt-add" />
            </div>
        </form>
        </div>
        </div>
    </div>
</div>

<%@ include file="menu-footer.jsp"%>
</body>
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/index.js"></script>
<script src="../js/jquery.page.js"></script>
<script src="../js/order.js"></script>
<script type="text/javascript">
$('#bt-add').click(function(){
    //读取用户的输入——表单序列化
    var inputData = $('#fm-add').serialize();
	  console.log(">>>"+inputData);
    //异步提交请求
    $.ajax({
    	async: true,
        type: 'POST',
        url: '/day01/user/page/addAddressAddServlet',
        data: inputData,
        success: function(txt, msg, xhr){
        	// alert("*"+txt+"*");
            if(txt=='yes'){
                alert("添加成功！");
                $('#fm-add').get(0).reset();
            }else{ //修改失败
            	alert("添加失败！");
            }
        }
    });
});
</script>
</html>

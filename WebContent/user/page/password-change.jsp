<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="pojo.User" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的订单 - 我的商城</title>
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
    <nav id="nav1">
        <!-- 我的订单导航栏-->
        <div id="nav_order" class="lf">
            <ul>
                <li></li>
            </ul>
        </div>
    </nav>
</div>
<!--我的订单内容区域 #container-->
<div id="container" class="clearfix">
    <!-- 左边栏-->
    <div id="leftsidebar_box" class="lf">
        <div class="line"></div>
        <dl class="my_order">
            <dt onClick="changeImage()">帐号管理<img src="../img/myOrder/myOrder1.png"></dt>
            <dd class="first_dd"><a href="../page/password-change.html">修改密码</a></dd>
            <dd><a href="../page/address-add.html">添加地址</a></dd>
        </dl>
    </div>
    <!-- 右边栏-->
    <div class="rightsidebar_box rt" >
        <div class="order_empty">
         <div id="cover" class="rt">
      <form name="form1" id="fm-changep" method="post" action="">
            <div class="txt">
                <p>修改密码
                    <span>
                        <a href="../page/index.html">继续购物</a>
                    </span>
                </p>
                <input name="uname" id="uname" type="hidden" value="user01" />
                <div class="text">
                    <input type="password" placeholder="请输入当前密码" name="upwd" id="upwd" required>
                    <div id="accountCheck"></div>
                </div>
                <div class="text">
                    <input type="password" placeholder="请输入新密码" name="npwd" id="npwd" required minlength="6" maxlength="15">
                </div>
				<div class="text">
                    <input type="password" placeholder="请确认新密码" name="cpwd" id="cpwd" required minlength="6" maxlength="15">
                    <div id="pwdValidate"></div>
                </div>
                <div class="text">
	                <input class="button_login" type="button" value="提交" id="bt-changep" />
	                <div id="changeFail"></div>
                </div>
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
	// 验证用户名密码
	$('#npwd').focus(function(){
		var uname = $("#uname").val();
		var upwd = $("#upwd").val();
		console.log(">>>"+uname);
	    if (upwd == null || upwd == "") {
	        $("#accountCheck").text("原密码不能为空！");
	        $("#accountCheck").css("color","red");
	        return false;
	    }
	    $.ajax({
	        type:"POST",
	        url:"/day01/user/page/checkUpwdServlet",
	        data:"uname="+uname+"&upwd="+upwd,
	        success:function(msg){
	        	console.log(">>>"+msg);
	            if(msg == "yes"){
	                $("#accountCheck").text("");
	            }else if(msg == "no"){
	                $("#accountCheck").text("原密码错误");
	                $("#accountCheck").css("color","red");
	            }
	        }
	    });
	});
	//验证新密码和确认密码是否一致
	$("#cpwd").blur(function(){
		var npwd = $("#npwd").val();
		var cpwd = $("#cpwd").val();
		if(npwd==cpwd){
			$("#pwdValidate").text("");
        }else{
            $("#pwdValidate").text("新密码不一致");
            $("#pwdValidate").css("color","red");
	        return false;
		}
	});
	$('#bt-changep').click(function(){
        //读取用户的输入——表单序列化
        var inputData = $('#fm-changep').serialize();
		    // console.log(">>>"+inputData);
        //异步提交请求
        $.ajax({
        	async: true,
            type: 'POST',
            url: '/day01/user/page/updateUpwdServlet',
            data: inputData,
            success: function(txt, msg, xhr){
            	// alert("*"+txt+"*");
                if(txt=='yes'){  //修改成功
                    window.location.href =  "../page/login.jsp";
                }else{ //修改失败
                    $('#changeFail').html('修改失败！');
                    $("#changeFail").css("color","red");
                }
            }
        });
    });
</script>
</html>

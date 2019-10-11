<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的商城登陆页面</title>
    <link href="../css/header.css" rel="stylesheet"/>
    <link href="../css/footer.css" rel="stylesheet"/>
    <link href="../css/animate.css" rel="stylesheet"/>
    <link href="../css/login.css" rel="stylesheet"/>
</head>
<body>
<!-- 页面顶部-->
<header id="top">
    <div class="top">
        <img src="../img/header/shop.png" alt=""/>
        <span>欢迎登录</span>
    </div>
</header>
<div id="container">
    <div id="cover" class="rt">
        <form id="fm-login" name="form1" method="post" action="userLogin" >
            <div class="txt">
                <p>登录我的商城
                    <span>
                        <a href="regist.jsp">新用户注册</a>
                    </span>
                </p>
                <div class="text">
                    <input type="text" placeholder="请输入您的用户名" name="uname" id="uname" required>
                    <span></span>
                </div>
                <div class="text">
                    <input type="password" id="upwd" placeholder="请输入您的密码" name="upwd" required minlength="6" maxlength="15">
                    <span></span>
                </div>
                <div class="chose">

                </div>
                <input class="button_login" type="submit" value="登录" id="bt-login" />
            </div>
        </form>
    </div>
</div>
<!--错误提示-->
<div id="showResult"></div>
<%@ include file="menu-footer.jsp"%>
<script src="../js/jquery-3.1.1.min.js"></script>
</body>
</html>
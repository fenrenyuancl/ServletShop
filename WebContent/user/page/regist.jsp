<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的商城注册页面</title>
    <link href="../css/header.css" rel="stylesheet"/>
    <link href="../css/footer.css" rel="stylesheet"/>
    <link href="../css/animate.css" rel="stylesheet"/>
    <link href="../css/login.css" rel="stylesheet" />
</head>
<body>
<!--模态框-->
<div class="modal">
  <div class="modal_header">
    提示信息
  </div>
  <div class="modal_content">
    恭喜您已成功注册我的书城！
  </div>
</div>
<!-- 页面顶部-->
<header id="top">
  <div class="top">
    <img src="../img/header/shop.png" alt=""/>
    <span>欢迎注册</span>
  </div>
</header>
<div class="parent">
  <div class="container">
    <div class="panel rt">
      <form id="fm-register" method="post" action="userRegist">
        <div class="txt">
          <p id="newuser">新用户注册
            <span>
              <a href="login.jsp" id="dlogin">直接登录</a>
            </span>
          </p>
        </div>
        <div class="form-group">
          <label for="uname">用户名：</label>
          <input autocomplete required minlength="6" maxlength="9" type="text" placeholder="请输入用户名" autofocus name="uname" id="uname"/>
          <span id="unameError" class="msg-default">用户名长度在6到9位之间</span>
        </div>
        <div class="form-group">
          <label for="upassword">登录密码：</label>
          <input required type="password" minlength="6" maxlength="12" placeholder="请输入密码" name="upwd" autofocus id="upwd"/>
          <span id="upwdError" class="msg-default hidden">密码长度在6到12位之间</span>
        </div>
        <div class="form-group">	
          <label for="age">年龄：</label>
          <input id="age" name="age" placeholder="请输入您的年龄" pattern="(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$" required type="text" />
        </div>
        <div class="form-group">
          <label></label>
          <input type="button" value="提交注册信息" id="bt-register"/>
        </div>
      </form>
    </div>
  </div>
</div>

<%@ include file="menu-footer.jsp"%>
<script src="../js/jquery-3.1.1.min.js"></script>
<script>
	$('#bt-register').click(function(){
		var lengths=0;
		$('.form-group').each(function(){
		  if($(this).find('span').hasClass('msg-success')){
		    lengths++;
		  }
		});
		if(lengths==4){
	        $('#fm-register').submit();
	  }
	}); 
  /*1.对用户名进行验证*/
  $('#uname').blur(function(){
    if(this.validity.valueMissing){
      this.nextElementSibling.innerHTML = '用户名不能为空';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('用户名不能为空');
    }else if(this.validity.tooShort){
      this.nextElementSibling.innerHTML = '用户名不能少于6位';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('用户名不能少于6位');
    }else {
      this.nextElementSibling.innerHTML = '用户名格式正确';
      this.nextElementSibling.className = 'msg-success';
      this.setCustomValidity('');
      var data = $("#uname").val();
      if(!data){   //用户没有输入任何内容
        return;
      }
      /*发起异步请求，询问服务器用户名是否已经存在*/
      $.ajax({
      type:'post',
      data:'uname='+data,
      url:'/day01/user/page/checkUnameServlet',
      success:function(data){
            console.log('开始处理响应数据');
            // alert("*"+data+"*");
            if(data=='yes'){
              alert('该用户名已被占用');
            }
      }
    });
    }
  });
  $('#uname').focus(function(){
    this.nextElementSibling.innerHTML = '用户名长度在6到9位之间';
    this.nextElementSibling.className = 'msg-default';
  });
  /*2.对密码进行验证*/
  $('#upwd').blur(function(){
    if(this.validity.valueMissing){
      this.nextElementSibling.innerHTML = '密码不能为空';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('密码不能为空');
    }else if(this.validity.tooShort){
      this.nextElementSibling.innerHTML = '密码长度在尽量别少于6位';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('密码长度在尽量别少于6位');
    }else {
      this.nextElementSibling.innerHTML = '密码格式正确';
      this.nextElementSibling.className = 'msg-success';
      this.setCustomValidity('');
    }
  });
  $('#upwd').focus(function(){
    this.nextElementSibling.innerHTML = '密码长度在6到12位之间';
    this.nextElementSibling.className = 'msg-default';
  });
  /*3.对邮箱地址进行验证*/
  $('#email').blur(function(){
    if(this.validity.valueMissing){
      this.nextElementSibling.innerHTML = '邮箱不能为空';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('邮箱不能为空');
    }else if(this.validity.typeMismatch){
      this.nextElementSibling.innerHTML = '邮箱格式不正确';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('邮箱格式不正确');
    }else {
      this.nextElementSibling.innerHTML = '邮箱格式正确';
      this.nextElementSibling.className = 'msg-success';
      this.setCustomValidity('');
    //  var data =document.getElementById("email").value;
      var data = $("#email").val();
      if(!data){   //用户没有输入任何内容
        return;
      }
      /*发起异步请求，询问服务器邮箱是否已经存在*/
      $.ajax({
      type:'get',
      data:'email='+data,
      url:'/day01/user/page/checkEmailServlet',
      success:function(data){
            console.log('开始处理响应数据');
            // alert("*"+data+"*");
            if(data=='yes'){
             alert('该邮箱已被占用');
            }
      }
    });
    }
  });
  $('#email').focus(function(){
    this.nextElementSibling.innerHTML = '请输入合法的邮箱地址';
    this.nextElementSibling.className = 'msg-default';
  });
  /*4.对手机号进行验证*/
  $('#phone').blur(function(){
    if(this.validity.valueMissing){
      this.nextElementSibling.innerHTML = '手机号不能为空';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('手机号不能为空');
    }else if(this.validity.patternMismatch){
      this.nextElementSibling.innerHTML = '手机号格式不正确';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('手机号格式不正确');
    }else {
      this.nextElementSibling.innerHTML = '手机号格式正确';
      this.nextElementSibling.className = 'msg-success';
      this.setCustomValidity('');
      var data = $("#phone").val();
      if(!data){   //用户没有输入任何内容
        return;
      }
      /*发起异步请求，询问服务器手机号是否已经存在*/
      $.ajax({
      type:'get',
      data:'phone='+data,
      url:'/day01/user/page/checkPhoneServlet',
      success:function(data){
            console.log('开始处理响应数据');
            // alert("*"+data+"*");
            if(data=='yes'){
             alert('该号码已被占用');
            }
      }
    });
    }
  });
  $('#phone').focus(function(){
    this.nextElementSibling.innerHTML = '请输入合法的手机号';
    this.nextElementSibling.className = 'msg-default';
  });
  </script>
</body>
</html>

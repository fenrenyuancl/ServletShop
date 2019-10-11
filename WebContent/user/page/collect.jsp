<%@page import="java.util.ArrayList"%>
<%@page import="pojo.Book"%>
<%@page import="dao.CollectDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的商城收藏夹</title>
    <link href="../css/header.css" rel="stylesheet" />
    <link href="../css/my.collect.css" rel="stylesheet" />
    <link href="../css/footer.css" rel="stylesheet" />
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

<div class="modalAdd" style="display:none">
    <div class="modal_dialog">
        <div class="modal_header">
            添加提示
            <img src="../img/model/model_img1.png" alt="" class="rt close"/>
        </div>
        <div class="modal_information">
            <img src="../img/model/model_img2.png" alt=""/>
            <span>请选择商品</span>

        </div>

    </div>
</div>

<div class="big">
    <form  name="" action="" method="post" >
        <section id="section" >
            <div id="title">
                <b>收藏商品</b>
            </div>
            <div id="box" >

		        <div id="content_box" >
<%
	ArrayList<Book> books = (ArrayList<Book>)request.getAttribute("books");
	for(int i = 0; i<books.size()&&books != null;i++){
		Book book = books.get(i);
	}
%>
                   <div class="lf" id="d1">
                        <div class="img">
                            <a href="../page/detail.html">
                            	<img src="../img/goods/9787115435101/collect.jpg" alt=""/>
                            </a>
                        </div>
                        <div class="describe">
                            <p>Python数据科学指南</p>
                            <span class="price"><b>￥</b><span class="priceContent">66.2</span></span>
                            <span class="addCart"><a href="../page/detail.html">查看</a></span>
                        </div>
                    </div>

                    <div class="lf" id="d2">
                        <div class="img">
                            <a href="detail.action?isbn=9787111545682">
                            	<img src="../img/goods/9787111545682/collect.jpg" alt=""/>
                            </a>
                        </div>
                        <div class="describe">
                            <p>Linux运维最佳实践</p>
                            <span class="price"><b>￥</b><span class="priceContent">56.5</span></span>
                            <span class="addCart"><a href="detail.action?isbn=9787111545682">查看</a></span>
                        </div>
                    </div>

                    <div class="lf" id="d3">
                        <div class="img">
                            <a href="detail.action?isbn=9787115335500">
                            	<img src="../img/goods/9787115335500/collect.jpg" alt=""/>
                            </a>
                        </div>
                        <div class="describe">
                            <p>深入浅出Node.js</p>
                            <span class="price"><b>￥</b><span class="priceContent">57.3</span></span>
                            <span class="addCart"><a href="detail.action?isbn=9787115335500">查看</a></span>
                        </div>
                    </div>

                    <div class="lf" id="d4">
                        <div class="img">
                            <a href="detail.action?isbn=9787111532644">
                            	<img src="../img/goods/9787111532644/collect.jpg" alt=""/>
                            </a>
                        </div>
                        <div class="describe">
                            <p>架构即未来</p>
                            <span class="price"><b>￥</b><span class="priceContent">81.0</span></span>
                            <span class="addCart"><a href="detail.action?isbn=9787111532644">查看</a></span>
                        </div>
                    </div>

                    <div class="lf" id="d5">
                        <div class="img">
                            <a href="detail.action?isbn=9787111563891">
                            	<img src="../img/goods/9787111563891/collect.jpg" alt=""/>
                            </a>
                        </div>
                        <div class="describe">
                            <p>面向机器智能的TensorFlow实践</p>
                            <span class="price"><b>￥</b><span class="priceContent">56.5</span></span>
                            <span class="addCart"><a href="detail.action?isbn=9787111563891">查看</a></span>
                        </div>
                    </div>

			    </div>
                </div>
        </section>

    </form>
    <div class="none" style="display: none">
        <div class="none_content">
            <img src="../img/model/model_img3.png" alt="" class="lf"/>
            <p class="lf">您的收藏夹竟然还是空哒( ⊙ o ⊙ )</p>
            <span class="lf">赶快去收藏商品吧！</span>
            <a href="#" class="lf">去购物>></a>
        </div>

    </div>
</div>
<%@ include file="menu-footer.jsp"%>

<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/collect.js"></script>
<script>
    //搜索下拉
    $('.seek').focus(function(){

        if($(this).hasClass('clickhover')){

            $(this).removeClass('clickhover');
            $(this).find('.seek_content').hide();
            $(this).find('img').attr('src','../img/header/header_normal.png');

        }else{
            $(this).addClass('clickhover');
            $(this).find('.seek_content').show();
            $(this).find('img').attr('src','../img/header/header_true.png');
        }
    })
    $('.seek_content>div').click(function(){
        $('.seek').removeClass('clickhover');
        var text=$(this).html();
        $('.seek span').html(text);
        $(this).parent().hide();
        $('.seek').find('img').attr('src','../img/header/header_normal.png');
        $('.seek').blur();

    })

    $('.seek').blur(function(){

        $('.seek').removeClass('clickhover');
        $('.seek_content').hide();

        $('.seek').find('img').attr('src','../img/header/header_normal.png');
        console.log(1);
    })
    
</script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Frame left</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript" src="../js/jquery-2.2.3.min.js"></script>
    <link href="../css/sys/common_style_blue.css" rel="stylesheet" type="text/css" />
	<!-- 内容总宽度为 3px边框 * 2 + 155px内容 = 161px; -->
<style type="text/css">

html{
height: 100%;
}
body {
	background: none repeat scroll 0 0 #D8EDFC;
	margin: 0;
	padding: 0;
}
#Menu {
    margin: 0;
    padding: 0;
    width: 155px;
	background: none repeat scroll 0 0 #D8EBF7;
    list-style: none outside none;
	
	margin-left: 3px;
	border-top: 3px solid #4891C6;
}
#Menu .level1 {
 color: #005790;
    font-weight: bold;
    padding-bottom: 1px;
	  cursor: pointer;
}
#Menu .level1 .level1Style {
  background: url("../images/sys/img/menu_btn_bg.gif") no-repeat scroll 0 0 transparent;
    height: 23px;
    padding-left: 20px;
    padding-top: 5px;
    width: 150px;
	margin-bottom: -4px
}
#Menu .level1 .level1Style .Icon {
	margin-top: -2px;
}
#Menu .level1 .MenuLevel2 {
 background: none repeat scroll 0 0 #D8EBF7;
    list-style: none outside none;
    margin: 0;
    padding: 0;
}
#Menu .level1 .MenuLevel2 .level2Style{
	color: #005790;
    font-weight: normal;
	border-top: 1px solid #EFF6FB;
	height: 25px;
	padding-left: 43px;
	padding-top: 5px;
	width: 150px;
	background-image:url(${pageContext.request.contextPath }/images/sys/img/menu_arrow_single.gif);
	background-color: #8EC4E9;
	background-repeat: no-repeat;
	background-position: 29px center;
}
</style>
</head>
<body>
	
    <ul id="Menu">
	    <li class="level1">
            <div onClick="menuClick(this);" class="level1Style">
				<img src="../images/sys/func20001.gif" class="Icon" /> 
				系统菜单
			</div>
            <ul class="MenuLevel2">
            	<li class="level2 level2Style">
                    <a target="right" href="${pageContext.request.contextPath }/sys/apartment.action?method=list">客房管理</a>
				</li>
                <li class="level2 level2Style">
                	<a target="right" href="${pageContext.request.contextPath }/sys/foodType.action?method=list">菜系管理</a>
				</li>
                <li class="level2 level2Style">
                	<a target="right" href="${pageContext.request.contextPath }/sys/food.action?method=list">菜品管理</a>
				</li>
                <li class="level2 level2Style">
                	<a target="right" href="${pageContext.request.contextPath }/sys/order.action?method=list">订单管理</a>
				</li>
            </ul>
        </li>
    </ul>
</body>
</html>
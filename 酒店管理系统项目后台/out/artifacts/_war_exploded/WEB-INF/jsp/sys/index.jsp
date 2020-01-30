<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Welcome</title>
</head>
<frameset rows="100px,*,19px" framespacing="0" border="0" frameborder="0">
  <frame src="${ctx}/public/top.jsp" scrolling="no" noresize >
  <frameset cols="178px,*">
    <frame noresize src="${ctx}/public/left.jsp" scrolling="yes" />
    <frame noresize name="right" src="${ctx}/public/right.jsp" scrolling="yes" />
  </frameset>
  <frame noresize name="status_bar" scrolling="no" src="${ctx}/public/bottom.jsp" />
</frameset>
<noframes>
  <body>
  你的浏览器不支持框架布局，推荐你使用<a href="http://www.firefox.com.cn/download/" style="text-decoration: none;">火狐浏览器</a>,
  <a href="http://www.google.cn/intl/zh-CN/chrome/" style="text-decoration: none;">谷歌浏览器</a>
  </body>
</noframes>
</html>
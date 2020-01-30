<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <!-- 包含公共的JSP代码片段 -->
    <title>Ascott豪华大酒店</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="../js/jquery-2.2.3.min.js"></script>
    <link href="../css/sys/common_style_blue.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="../css/sys/index_1.css" />

    <script type="text/javascript">

        window.onload = function () {
            var disabled = "${disabled}";
            var disabledSelect = document.getElementById("disabled");
            var options = disabledSelect.options;
            $.each(options, function (i, option) {
                $(option).attr("selected",option.value == disabled)
            });
        }

        function disabledChange(obj) {
            var keyword = $("#keyword").val();
            var disabled = obj.value;

            window.location = "${pageContext.request.contextPath}/sys/foodType.action?method=list&keyword="+keyword+"&disabled=" + disabled;
        }
    </script>
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
    <div id="TitleArea_Head"></div>
    <div id="TitleArea_Title">
        <div id="TitleArea_Title_Content">
            <img border="0" width="13" height="13"
                 src="../images/sys/title_arrow.gif" /> 菜系列表
        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>
<!-- 过滤条件 -->
<div id="QueryArea">
    <form action="${pageContext.request.contextPath }/sys/foodType.action" method="get" target="right">
        <input type="hidden" name="method" value="list">
        <input type="text" id="keyword" name="keyword" value="${keyword}" placeholder="请输入菜系名称">
        <select name="disabled" id="disabled" onchange="disabledChange(this)">
            <option value="">全部</option>
            <option value="0">未删</option>
            <option value="1">已删</option>
        </select>
        <input type="submit" value="搜索">
        <a href="${pageContext.request.contextPath }/sys/foodType.action?method=addPage" target="right"><input type="button" value="添加"></a>
    </form>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" style="text-align: center;" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
        <tr align="center" valign="middle" id="TableTitle">
            <td>编号</td>
            <td>菜系名称</td>
            <td>创建时间</td>
            <td>是否已删除</td>
            <td>操作</td>
        </tr>
        </thead>
        <!--显示数据列表 -->
        <tbody id="TableData">
        <c:choose>
            <c:when test="${not empty foodTypes}">
                <c:forEach items="${foodTypes}" var="foodType" varStatus="status">
                    <tr>
                        <td>${status.index}</td>
                        <td>${foodType.typeName}</td>
                        <td><fmt:formatDate value="${foodType.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>
                            <c:if test="${foodType.disabled == 0}">
                                未删
                            </c:if>
                            <c:if test="${foodType.disabled == 1}">
                                已删
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${foodType.disabled == 0}">
                                <a href="${pageContext.request.contextPath }/sys/foodType.action?id=${foodType.id}&method=viewUpdate" class="FunctionButton">更新</a>
                                <a href="${pageContext.request.contextPath }/sys/foodType.action?id=${foodType.id}&method=update&disabled=1" class="FunctionButton">删除</a>
                            </c:if>
                            <c:if test="${foodType.disabled == 1}">
                                <a href="${pageContext.request.contextPath }/sys/foodType.action?id=${foodType.id}&method=update&disabled=0" class="FunctionButton">激活</a>
                            </c:if>

                        </td>
                    </tr>
                </c:forEach>
            </c:when>
        </c:choose>

        <!--<tr>
            <td colspan="3" style="text-align: center;">没有你要找的数据，请先保存记录再查看！</td>
        </tr>-->

        </tbody>
    </table>
</div>
</body>
</html>

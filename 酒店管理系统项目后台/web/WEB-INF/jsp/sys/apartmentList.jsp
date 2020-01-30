<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!-- 包含公共的JSP代码片段 -->
    <title>Ascott豪华大酒店</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.2.3.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/sys/common_style_blue.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sys/index_1.css" />
    <script type="text/javascript">

        window.onload = function () {
            var apartmentStatus = "${apartmentStatus}";

            var apartmentStatusSelect = document.getElementById("apartmentStatus");
            var options = apartmentStatusSelect.options;

            $.each(options, function (i, option) {
               $(option).attr("selected",option.value == apartmentStatus)
            });

            var disabled = "${disabled}";
            var disabledSelect = document.getElementById("disabled");
            var options = disabledSelect.options;
            $.each(options, function (i, option) {
                $(option).attr("selected",option.value == disabled)
            });
        }

        function apartmentStatusChange(obj) {
            var keyword = $("#keyword").val();
            var apartmentStatus = obj.value;
            var disabled = $("#disabled option:selected").val();

            window.location.href = "${pageContext.request.contextPath}/sys/apartment.action?method=list&keyword="+keyword+"&apartmentStatus="+apartmentStatus+"&disabled=" + disabled;
        }

        function disabledChange(obj) {
            var keyword = $("#keyword").val();
            var apartmentStatus = $("#apartmentStatus option:selected").val();
            var disabled = obj.value;

            window.location = "${pageContext.request.contextPath}/sys/apartment.action?method=list&keyword="+keyword+"&apartmentStatus="+apartmentStatus+"&disabled=" + disabled;
        }

    </script>
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
    <div id="TitleArea_Head"></div>
    <div id="TitleArea_Title">
        <div id="TitleArea_Title_Content">
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/images/sys/title_arrow.gif"/> 客房列表
        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>


<!-- 过滤条件 -->
<div id="QueryArea">
    <form action="${pageContext.request.contextPath}/sys/apartment.action" method="get"  target="right">
        <input type="hidden" name="method" value="list">
        <input type="text" id="keyword" name="keyword" value="${keyword}" placeholder="请输入客房名称" >
        <select name="apartmentStatus" id="apartmentStatus" onchange="apartmentStatusChange(this)">
            <option value="">全部</option>
            <option value="0">未使用</option>
            <option value="1">正在使用</option>
        </select>
        <select name="disabled" id="disabled" onchange="disabledChange(this)">
            <option value="">全部</option>
            <option value="0">未删</option>
            <option value="1">已删</option>
        </select>
        <input type="submit" value="搜索">
        <!-- 其他功能超链接 -->
        <a href="${pageContext.request.contextPath}/sys/apartment.action?method=addPage" target="right"><input type="button" value="添加"></a>
    </form>
</div>


<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
        <tr align="center" valign="middle" id="TableTitle">
            <td>编号</td>
            <td>房名</td>
            <td>客房使用状态</td>
            <td>客人启用时间</td>
            <td>客房是否删除</td>
            <td>操作</td>
        </tr>
        </thead>
        <!--显示数据列表 -->
        <tbody id="TableData">
        <c:if test="${not empty apartments}">
            <c:forEach items="${apartments}" var="apartment" varStatus="status">
                <tr class="TableDetail1">
                    <td align="center">${status.index}&nbsp;</td>
                    <td align="center">${apartment.apartment_name}&nbsp;</td>
                    <td align="center">
                        <c:if test="${apartment.apartment_status == 0}">
                            未使用
                        </c:if>
                        <c:if test="${apartment.apartment_status == 1}">
                            正在使用
                        </c:if>
                    </td>
                    <td align="center"><fmt:formatDate value="${apartment.create_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td align="center">
                        <c:if test="${apartment.disabled == 0}">
                            未删
                        </c:if>
                        <c:if test="${apartment.disabled == 1}">
                            已删
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${apartment.disabled == 1}">
                            <a href="${pageContext.request.contextPath }/sys/apartment.action?method=update&id=${apartment.id}&disabled=0"
                               class="FunctionButton"	target="right">激活</a>
                        </c:if>
                        <c:if test="${apartment.disabled == 0}">
                            <a href="${pageContext.request.contextPath }/sys/apartment.action?method=update&id=${apartment.id}&disabled=1"
                               class="FunctionButton"  target="right">删除</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

</div>
</body>
</html>

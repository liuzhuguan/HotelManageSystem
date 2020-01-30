<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<script type="text/javascript">

    function updateSubmit() {
        var foodTypeName = $("#foodTypeName").val();
        if(foodTypeName != null && foodTypeName != ""){
            //没有重复返回true  保存菜系名称
            jQuery.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/sys/foodType.action?&method=updateSubmit",
                data: "foodTypeName="+foodTypeName + "&id=" + "${foodType.id}" ,
                dataType:"text",
                async:false,
                success: function(msg){
                    if(msg != null && msg == "success" && foodTypeName != "${foodType.typeName}"){
                        window.location.href = "${pageContext.request.contextPath}/sys/foodType.action?method=list";
                    }else if(msg != null && msg == "fail"){
                        $("#message").html("不对，重改");
                        $("#foodTypeName").val("");
                    }
                },error:function(){
                    alert("数据加载异常");
                }
            })
        }else{
            $("#message").html("请输入菜系名称！");
        }
    }
</script>
<head>
    <!-- 包含公共的JSP代码片段 -->
    <title>Ascott豪华大酒店</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="../js/jquery-2.2.3.min.js"></script>
    <link href="../css/sys/common_style_blue.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="../css/sys/index_1.css" />
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
    <div id="TitleArea_Head"></div>
    <div id="TitleArea_Title">
        <div id="TitleArea_Title_Content">
            <img border="0" width="13" height="13" src="../images/sys/title_arrow.gif"/>  更新菜系
        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <!-- 表单内容 -->
    <form action="/sys/foodType?method=update" method="post">

        <!-- 本段标题（分段标题） -->
        <div class="ItemBlock_Title">
            <img width="4" height="7" border="0" src="../images/sys/item_point.gif"> 菜系信息&nbsp;
        </div>
        <!-- 本段表单字段 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <div class="ItemBlock2">
                    <table cellpadding="0" cellspacing="0" class="mainForm">
                        <tr>
                            <td width="80px">菜系名称</td>
                            <td>
                                <input type="text" onblur="ajaxFoodTypeName()" value="" id="foodTypeName" name="foodTypeName" class="InputStyle" /> *
                                <label color="red" id="message"></label>
                                <input type="hidden" id="id" name="id" value="${foodType.id}"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>

        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="button"  onclick="updateSubmit()" value="修改" class="FunctionButtonInput">
            <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
        </div>
    </form>

</div>




</body>
</html>

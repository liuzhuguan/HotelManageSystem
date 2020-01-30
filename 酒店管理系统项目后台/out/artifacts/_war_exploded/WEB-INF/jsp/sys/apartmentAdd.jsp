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

        function  addSubmit(){
            var apartmentName = $("#apartmentName").val();

            if(apartmentName != null && apartmentName != ""){
                $.ajax({
                    type: "POST",
                    url: "${ctx}/sys/apartment.action?method=addSubmit",
                    data: "apartmentName="+apartmentName,
                    success: function(msg){
                        //alert("msg:"+msg.trim()+"typeof:"+typeof msg);
                        if(msg.trim() == "success"){
                            $("#message").html("保存成功！");
                            $("#apartmentName").val("");
                        }else{
                            $("#message").html("当前客房已经存在，请重新输入！");
                            $("#apartmentName").val("");
                        }
                    }
                });


            }else{
                $("#message").html("请输入菜系名称！");
            }




        }

    </script>
</head>
<body>


<!-- 页面标题 -->
<div id="TitleArea">
    <div id="TitleArea_Head"></div>
    <div id="TitleArea_Title">
        <div id="TitleArea_Title_Content">
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/images/sys/title_arrow.gif"/>  添加客房
        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>


<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <!-- 表单内容 -->
    <form action="" method="post">
        <!-- 本段标题（分段标题） -->
        <div class="ItemBlock_Title">
            <img width="4" height="7" border="0" src="${pageContext.request.contextPath}/images/sys/item_point.gif"> 客房信息&nbsp;
        </div>
        <!-- 本段表单字段 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <div class="ItemBlock2">
                    <table cellpadding="0" cellspacing="0" class="mainForm">
                        <tr>
                            <td width="80px">客房名字</td>
                            <td>
                                <input type="text" id="apartmentName"  name="apartmentName" class="InputStyle"/>*
                                <label color="red" id="message"></label>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>

        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="button" onclick="addSubmit()" value="添加" class="FunctionButtonInput">
            <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
        </div>
    </form>

</div>

</body>
</html>

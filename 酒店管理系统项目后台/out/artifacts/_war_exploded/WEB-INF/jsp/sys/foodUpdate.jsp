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
            var foodTypeId = "${food.foodTypeId}";
            var foodTypeIdSelect = document.getElementById("foodTypeId");
            var options = foodTypeIdSelect.options;
            $.each(options, function (i, option) {
                $(option).attr("selected",option.value == foodTypeId)
            });
        }

        function ajaxFoodName() {
            var flag = true;
            $("#message").html("");
            var foodName = $("#foodName").val();
            var beginFoodName = "${food.foodName}"
            if(foodName == null || foodName == "") {
                $("#message").html("不能为空");
                flag = false;
            } else if (foodName != beginFoodName) {
                //没有重复返回true  保存菜系名称
                jQuery.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/sys/food.action?&method=ajaxFoodName",
                    data: "foodName="+foodName,
                    dataType:"text",
                    async:false,
                    success: function(msg){
                        if(msg != null && msg == "fail"){
                            $("#message").html("菜名称已存在，建议取名坤");
                            $("#foodName").val("");
                            flag = false;
                        }
                    },error:function(){
                        alert("数据加载异常");
                        flag = false;
                    }
                })
            }
            return flag;
        }

        function ajaxPrice() {
            var flag = true;
            $("#message").html("");
            var price = $("#price").val();
            if(price == null || price == "") {
                $("#message").html("请输入价格");
                flag = false;
            } else if(!/^(([1-9]\d*[.]\d\d?)|([0][.]\d\d?)|([1-9]\d*)|0)$/.test(price)){
                $("#message").html("请输入正确的菜品价格！");
                $("#price").val("");
                flag = false;
            }
            return flag;
        }

        function ajaxNum() {
            var  flag = true;
            $("#message").html("");
            var discount = $("#discount").val();
            if(discount != null || discount != "") {
                if(!/^(0([.]\d{1,2})?|1([.]0{1,2})?)$/.test(discount)){
                    $("#message").html("最多两位小数");
                    $("#discount").val("");
                    flag =false;
                }
            }
            return flag;
        }

        function addSubmitTest(){
            //再次进行校验
            if(!ajaxFoodName() || !ajaxPrice() || !ajaxNum()){
                return false;
            }

            //获取的是上传文件的本地路径  C:\fakepath\木瓜牛奶.jpg
            var imgName = $("#img").val();

            if(imgName != null && imgName != "" ){
                //找到最后.的索引值
                var extStar = imgName.lastIndexOf(".");
                //获取 .jpg  改为小写
                var  ext = imgName.substring(extStar,imgName.length).toLowerCase();
                if(ext.match(/.png|.jpg|.gif|.jpeg|.bmp/i) == null){
                    //表明用户上传的文件不符合要求
                    $("#message").html("只能上传png,jpg,gif,jpeg,bmp格式的菜品图片");
                    return false;
                }
            }
            //提交表单
            document.getElementById("form").submit();
        }
    </script>
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
    <div id="TitleArea_Head"></div>
    <div id="TitleArea_Title">
        <div id="TitleArea_Title_Content">
            <img border="0" width="13" height="13" src="../images/sys/title_arrow.gif"/> 更新新菜品
        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <!-- 表单内容 -->
    <form id="form" action="${pageContext.request.contextPath}/sys/food.action" method="post" enctype="multipart/form-data">
        <input type="hidden" name="method" value="updateSubmit">
        <!-- 本段标题（分段标题） -->
        <div class="ItemBlock_Title">
            <img width="4" height="7" border="0" src="../images/sys/item_point.gif"> 菜品信息&nbsp;
        </div>
        <!-- 本段表单字段 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <div class="ItemBlock2">
                    <table cellpadding="0" cellspacing="0" class="mainForm">
                        <tr>
                            <td width="80px">菜系</td>
                            <td>
                                <select id="foodTypeId" name="foodTypeId"  style="width:150px">
                                    <option value="">全部</option>
                                    <c:forEach items="${foodTypes}" var="foodType">
                                        <option value="${foodType.id}">${foodType.typeName}</option>
                                    </c:forEach>
                                </select>
                                *<input type="hidden" name="id" value="${food.id}" />
                                <label color="red" id="message"></label>
                            </td>
                        </tr>
                        <tr>
                            <td width="80px">菜名</td>
                            <td><input type="text" id="foodName" name="foodName" onblur="ajaxFoodName()" class="InputStyle" value="${food.foodName}"/> *</td>
                        </tr>
                        <tr>
                            <td>价格</td>
                            <td><input type="text" placeholder="最多两位小数"  onblur="ajaxPrice(this)" id="price" name="price" class="InputStyle"
                                       value="${food.price}"/> *</td>
                        </tr>
                        <tr>
                            <td>折扣</td>
                            <td><input type="text" id="discount" name="discount" class="InputStyle" placeholder="0-1之间如0.8" onblur="ajaxNum(this)"
                                       value="${food.discount}"/></td>
                        </tr>

                        <tr>
                            <td>简介</td>
                            <td><textarea name="remark" class="TextareaStyle">${food.remark}</textarea></td>
                        </tr>
                        <tr>
                            <td width="80px">菜品图片</td>
                            <td>
                                <input type="file" name="img" id="img" /> *
                            </td>
                        </tr>
                        <tr>
                            <td  colspan="2">
                                <img id="preview" src="${ctx}/upload/food/${food.img}"
                                     width=200px height=100px style="diplay:none" />
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>


        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="button" onclick="addSubmitTest()" value="添加" class="FunctionButtonInput">
            <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
        </div>
    </form>
</div>
</body>
</html>

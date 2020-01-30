<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Ascott豪华大酒店</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="../js/jquery-2.2.3.min.js"></script>
    <script type="text/javascript" src="../js/regex.js"></script>
    <link href="../css/sys/login.css" rel="stylesheet" type="text/css" />
    <link href="../css/sys/common_style_blue.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript">
        if(window.location != parent.window.location){
            //将当前页面作为最顶级页面
            parent.window.location = window.location;

        }

        function formOnfocus(id) {
            $("#"+ id +"_error").html("");
            switch (id) {
                case "loginname":
                    $("#loginname_message").html("请输入用户名");
                    break;
                case "phone":
                    $("#phone_message").html("请输入手机号");
                    break;
                case "email":
                    $("#email_message").html("请输入邮箱");
                    break;
                case "passWord":
                    $("#passWord_message").html("请输入密码");
                    break;
                case "okPassWord":
                    $("#okPassWord_message").html("请再次输入密码");
                    break;
            }

        }

        function formOnblur(id) {
            var flag = false;
            $("#"+ id +"_message").html("");
            if (id == "loginname") {
                var fieldValue = document.getElementById(id).value;
                if (fieldValue == null  ||  fieldValue == "") {
                    $("#loginname_error").html("用户名不能为空");
                } else {
                    jQuery.ajax({
                        type: "POST",
                        url: "${ctx}/sys/register.do?method=ajaxLoginName",
                        data: "loginname="+fieldValue,
                        dataType:"text",
                        async:false,
                        success: function(msg){
                            if(msg){
                                $("#loginname_error").html(msg);
                                //$("#loginname").val("");
                            }else{
                                flag = true;
                            }
                        },error:function(){
                            alert("数据加载异常");
                        }
                    })
                }
            } else if (id == "phone") {
                var fieldValue = document.getElementById(id).value;
                if (fieldValue == null  ||  fieldValue == "") {
                    $("#phone_error").html("手机号不能为空");
                } else {
                    if (!checkPhone(fieldValue)) {
                        $("#phone_error").html("手机号不对");
                    } else {
                        flag = true;
                    }
                }
            } else if (id == "email") {
                var fieldValue = document.getElementById(id).value;
                if (fieldValue == null || fieldValue == "") {
                    $("#email_error").html("邮箱不能为空");
                } else {
                    if (!checkEmail(fieldValue)) {
                        $("#email_error").html("邮箱都输不对，别注册了");
                    } else {
                        flag = true;
                    }
                }
            }else if (id == "passWord") {
                var fieldValue = document.getElementById(id).value;
                if (fieldValue == null || fieldValue == "") {
                    $("#passWord_error").html("密码不能为空");
                } else {
                    if (!checkPassword(fieldValue)) {
                        $("#passWord_error").html("看吧，密码都输不对");
                    } else {
                        flag = true;
                    }
                }
            } else if (id == "okPassWord") {
                var fieldValue = document.getElementById(id).value;
                if (fieldValue == null || fieldValue == "") {
                    $("#okPassWord_error").html("密码不一致");
                } else if (fieldValue != $("#passWord").val()) {
                    $("#okPassWord_error").html("密码不一致");
                } else {
                    flag = true;
                }
            }
            return flag;
        }

        function onRegister() {
            var ids = ["loginname","phone","email","passWord","okPassWord"];

            for ( var i = 0 ; i < ids.length ; i++) {
                if (!formOnblur(ids[i])) {
                    return false;
                }
            }
            document.getElementById("registerform").submit();
        }

    </script>
</head>

<body>

<!-- 上部 -->
<div id="Head_1">
    <!-- 标题 -->
    <div id="Head_1_Logo">
        <b style="font-family: '黑体'">Ascott注册</b>
    </div>
    <div id="Head_1_FunctionButton">
        <a  href="${pageContext.request.contextPath }/sys/login.do"  style="color: white;">
            登录
        </a>
    </div>
</div>
<div class="logo_box">
    <form action="${pageContext.request.contextPath }/sys/register.do" method="post" id="registerform" class="forms">
        <input type="hidden"  name="method" value="submitTable">
        <center> <font color="red"></font></center>
        <div class="input_outer">
            <span class="u_user"></span>
            <input name="loginname" id="loginname" autocomplete="off"  onfocus="formOnfocus(this.id);" onblur="formOnblur(this.id);" class="text"   placeholder="请输入用户名" style="color: black !important;ba" type="text">
            <div id="loginname_message" style="clear:both;color: black;"></div>
            <label id="loginname_error" style="color: black;"></label>
        </div>

        <div class="input_outer">
            <span class="u_phone"></span>
            <input name="phone" placeholder="手机号码" autocomplete="off" id="phone" onfocus="formOnfocus(this.id);" onblur="formOnblur(this.id);" class="text"   placeholder="请输入用户名" style="color: black !important" type="text">
            <div id="phone_message" style="clear:both;color: black;"></div>
            <label id="phone_error" style="color: black;"></label>
        </div>

        <div class="input_outer">
            <span class="u_email"></span>
            <input name="email" placeholder="email"  id="email" autocomplete="off" onfocus="formOnfocus(this.id);" onblur="formOnblur(this.id);" class="text"   placeholder="请输入用户名" style="color: black !important" type="text">
            <div id="email_message" style="clear:both;color: black;"></div>
            <label id="email_error" style="color: black;"></label>
        </div>

        <div class="input_outer">
            <span class="u_pass"></span>
            <input name="passWord" placeholder="密码" type="password" autocomplete="off"  id="passWord" onfocus="formOnfocus(this.id);" onblur="formOnblur(this.id);" class="text"   placeholder="请输入用户名" style="color: black !important" type="text">
            <div id="passWord_message" style="clear:both;color: black;"></div>
            <label id="passWord_error" style="color: black;"></label>
        </div>

        <div class="input_outer">
            <span class="u_pass"></span>
            <input name="okPassWord" placeholder="确认密码" type="password"  autocomplete="off"  id="okPassWord" onfocus="formOnfocus(this.id);" onblur="formOnblur(this.id);" class="text"   placeholder="请输入用户名" style="color: black !important" type="text">
            <div id="okPassWord_message" style="clear:both;color: black;"></div>
            <label id="okPassWord_error" style="color: black;"></label>
        </div>

        <div class="mb2">
            <a class="act-but submit" href="javascript:;" onclick="onRegister()"  style="color: black">注册</a>
        </div>

    </form>
</div>
</body>
</html>
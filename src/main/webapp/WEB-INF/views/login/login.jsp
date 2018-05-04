<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<script type="text/javascript" src="./resources/js/jquery-1.11.0.min.js"></script>

    <script type="text/javascript">
   	$(function() {  	
       	getid(document.loginFrm);
       	loginCls.loginInit();
       	loginCls.loginFn();
        loginCls.loginMessage();
        
    });
   	
 // 로그인 클래스
   	var loginCls	= {
   		$loginBtn	: $,
   		$id			: $,
   		$password	: $,
   		$checkId	: $,
   		
   		loginInit	: function() {
   			this.$loginBtn	= $("#loginBtn");
   			this.$id		= $("#id");
   			this.$password	= $("#password");
   			this.$checkId	= $('#checkId');
   		},
   		
   		loginFn		: function() {
   			var that	= this;
   			
   			this.$loginBtn.click(function(e) {
   				e.preventDefault();
   				
   				if(that.$checkId.val()){
   					//alert("true");
   					saveid(document.loginFrm);
   				}
   				
   				if ($.trim(that.$id.val()) === "") {
   					alert("아이디는 필수 입력 값입니다.");
   					
   					that.$id.focus();
   					
   					return;
   				}
   				
   				if ($.trim(that.$password.val()) === "") {
   					alert("비밀번호는 필수 입력 값입니다.");
   					
   					that.$password.focus();
   					
   					return;
   				}
   				
   				var emailRegax	= /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
   				
   				if (!emailRegax.test(that.$id.val().toLowerCase())) {
   					alert("아이디가 유효하지 않습니다.");
   					
   					return;
   				}
   				
   				
   				
   				$("#loginFrm").submit();
   			});
   		},
   		loginMessage	: function() {
   			
   		 	var message = "${message}";
   		 	
   		 	if(message !== ""){
   		 		alert(message); 
   		 	}
  			
   		}
   		
   	}
   	

	// 쿠키저장   	
   	var saveid		= function(form) {
	 
   		//alert(form.checkId.checked);
   		
   		var expdate	= new Date();
   		
   		if (form.checkId.checked) {
   			expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 * 30);
   		} else {
   			expdate.setTime(expdate.getTime() - 1);
   		}
   		
   		setCookie("saveid", form.id.value, expdate);
   	}
   	// 쿠키 값 세팅
   	var setCookie	= function(name, value, expires) {
   		// 아스키코드 인코딩
   		document.cookie	= name + "=" + escape(value) + "; path=/; expires=" + expires.toGMTString();

   	}
   	// 쿠키 값 가져오기
   	var getid		= function(form) {
   		
   		var cookieValue	= getCookie("saveid");

   		form.id.value	= cookieValue;
   		
   		if (cookieValue !== "") {
   			form.checkId.parentNode.classList.add("checked");
   		}
   	}
   	var getCookie	= function(name) {
   		var search	= name + "=";
   		
   		if (document.cookie.length > 0) {
   			offset	= document.cookie.indexOf(search);
   			// offset 의 디폴트 값은 -1
   			if (offset !== -1) {
   				offset	+= search.length;
   				
   				end	= document.cookie.length;
   				
   				return unescape(document.cookie.substring(offset, end));
   			}
   		}
   		
   		return "";
   	}
    </script>
    
<link rel="stylesheet" href="./resources/css/login.css" type="text/css">

<title>Login</title>
</head>
<body>
  <div class="login-wrapp">
    <div class="login-top">
      <a href="#">
        <img src="./resources/img/img_avatar.png">
      </a>
    </div>
    <div class="login-box">
      <form name="loginFrm" id="loginFrm" action="<c:url value='actionLogin.do'/>" method="post">
        <h1 class="login-title">LOGIN</h1>
        <ul class="login-form">
          <li>
            <strong class="login-guide">EMAIL</strong>
            <input type="email" name="id" id="id" placeholder="email 형식의 ID를 입력하세요." maxlength="30">
            <!-- <input type="text" placeholder="example@regyu.com" name="uname" required> -->
          </li>
          <li>
            <strong class="login-guide">PASSWORD</strong>

            <input type="password" name="password" id="password" placeholder="비밀번호를 입력하세요." autocomplete="new-password"/>
            <!-- <input type="password" placeholder="****" name="psw" required> -->
          </li>
        </ul>
        
        <button type="submit" id="loginBtn">Login</button>
        
        <label class="login-guide">
          <input type="checkbox" name="checkId" id="checkId" checked="checked" onclick="saveid(document.loginFrm);">
			Remember me
        </label>
        
        <a href="#" class="login-guide"> Forgot password</a>
      </form>
    </div>
  </div>
</body>
   
</html>
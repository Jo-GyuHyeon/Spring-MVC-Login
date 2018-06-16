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
        
        resetCls.resetInit();
     	resetCls.resetInitFn();
     	
    });
   	
	// 비밀번호 초기화를 위한 클래스
   	var resetCls = {
		
		$btnResetPwdForm	: $,
		$modalResetPwd		: $,
		$mask				: $,
		$btnResetProc		: $,
		$btnResetClose		: $,
		$resetId			: $,
 			
		resetInit	: function() {
			this.$btnResetPwdForm = $('#btnResetPwdForm');
			this.$modalResetPwd = $('#modalResetPwd');
			this.$mask = $('#mask');
			this.$btnResetProc = $('#btnResetProc');
			this.$btnResetClose = $('#btnResetClose');
			this.$resetId = $('#resetId');
		},
		
		resetInitFn	: function() {
			var that = this;
			this.$btnResetPwdForm.click(function() {
				that.$mask.css("opacity", "0.9").css("z-index","10000").show();
				
				that.$modalResetPwd.css("z-index","11000").show();
				
			})
			
			this.$btnResetClose.click(function() {
				that.$mask.hide();
				
				that.$modalResetPwd.hide();
				
			})
			
			this.$btnResetProc.click(function() {
				
				
			/* 	if(!validateEmailChk(that.$resetId,"아이디")){
					return;
				}
				if(!validateEmailChk(that.$resetId.val())){
					return;
				} */
				
				var controllerUrl	= "resetPwd.do",
				param				= {"id" : that.$resetId.val()};
				
				$.ajax({
					url		:	controllerUrl,
					data	: param,
					type	: "post",
					
					success: function(data){
						
						if(data === "success"){
							alert("비밀번호 초기화 안내 메일이 발송되었습니다.");
							location.reload();
						}
						
					}
				});
				
			})
		}
   	}
	
	var validateEmptyChk = function(target, msg){
		var val = $.trim(target.val());
		
		if(val === ""){
			alert(msg + "은/는 필수입력 값입니다.");
			
			target.focus();
			
			return false;
		}
		
		return true;
	}
	
   	var validateEmailChk = function(email){
   		var emailRegax	= /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			
			if (!emailRegax.test(email.toLowerCase())) {
				alert("아이디가 유효하지 않습니다.");
				
				return;
			}
		
		return true;
	}
	
   	
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
   				
   				if(!validateEmptyChk(that.$id,"아이디")){
   					return;
   				}
   				
   				if(!validateEmptyChk(that.$password,"비밀번호")){
   					return;
   				}
   				
   				if(!validateEmailChk(that.$id.val())){
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
<style>
		#mask            {position:absolute; width:100%; height:100%; left:0; top:0; background-color:#000; display:none;}
		#modalResetPwd      {position:absolute; top:50%; margin-top:-150px; width:100%; display:none;}
</style>
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
        
        <a href="#" class="login-guide" id ="btnResetPwdForm"> Forgot password</a>
      </form>
    </div>
  </div>
  
  <div id="mask"></div>
	<div style="display: none" id="modalResetPwd">
	    <div class="login-wrapp">
		    <div class="login-box" style="margin-top:10px">
		        <div class="login-title" style="font-size: 17px;">비밀번호 초기화</div>
		        <div class="login-guide">
		            <div class="login-form">
		                <label>아이디</label>
		                <input type="text" id="resetId" placeholder="아이디(이메일)를 입력하세요." class="" maxlength="50" required="required" />
		            </div>
		        </div>
	            <button id="btnResetProc" class="" style="margin-bottom:10px">초기화 메일 발송</button>
	            <button id="btnResetClose" class="" style="margin-bottom:10px">닫기</button>
		    </div>
	    </div>
	</div>
</body>
   
</html>
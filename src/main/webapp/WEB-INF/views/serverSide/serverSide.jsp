<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<link rel="stylesheet" href="./resources/css/register.css">
	
	<script type="text/javascript" src="./resources/js/jquery-1.11.0.min.js"></script>
	
<title>회원가입</title>
	<script type="text/javascript">

  	var commonCl = {
		$serverSideVO 	: null,
		
		validInit 		: function(){
			this.$serverSideVO = $("#annoServerSideVO");
			
			//this.$serverSideVO = $("#serverSideVO");
		},
		
		vaildFn 		: function() {
			
			//this.$serverSideVO.attr("action", "annoServerSideValidChk.do");
			/* if (typeof(grecaptcha) !== 'undefined') {
				
				if (grecaptcha.getResponse() === "") {
					alert("스팸방지코드(Captcha Code)가 틀렸습니다.");
					
					return;
				}
			} */
			
			this.$serverSideVO.attr("action", "annoServerSideValidChk.do");
			//this.$serverSideVO.attr("action", "serverSideValidChk.do");
			
			this.$serverSideVO.submit();
		},
		
		checkEsangMu	: function() {
			this.$serverSideVO.attr("action", "serverSideInsert.do");
			
			this.$serverSideVO.submit();
		}
	} 

	
	$(function() {
		commonCl.validInit();

        $("#registBtn").click(function() {        	
        	commonCl.vaildFn();
        });
         
/*         <c:if test="${eSangMu == 'Y'}">
        	commonCl.checkEsangMu();
        </c:if>  */
	})
	</script>
</head>
<body>
    <div class="register-wrapp">
        <%-- <form:form commandName="serverSideVO"> --%>
         <form:form commandName="annoServerSideVO">
            <h2 class="register-title">Register Form</h2>
            <p class="register-title">Please fill in this form to create an account.</p>

            <ul class="input-container">
                <li>	
	                <div>
	                    <i class="fa fa-envelope icon"></i>
	                    <form:input path="mberId" class="input-field" type="email" placeholder="Email" name="email"/>
	                </div>
                	<form:errors path="mberId" />
                </li>
                <li>
	                <div>
	                <i class="fa fa-key icon"></i>
	                    <form:input path="password" class="input-field" type="password" placeholder="Password" name="psw"/>
	                </div>
               		<form:errors path="password" />
                </li>
                <li>
	                <div>
	                 	<i class="fa fa-lock icon"></i>
	                    <form:input path="passwordConfirm" class="input-field" type="password" placeholder="passwordConfirm" name="pswcfm"/>
	                </div>
                   <form:errors path="passwordConfirm" />
                </li>
                <li>
	                <div>
	                	<i class="fa fa-user icon"></i>
	                    <form:input path="mberNm" class="input-field" type="text" placeholder="Username" name="usrnm"/>
	                </div>
                    <form:errors path="mberNm" />
                </li>
                <li>
	                <div>
	                	<i class="fa fa-volume-control-phone icon"></i>
	                    <form:input path="phoneNumber" class="input-field" type="text" placeholder="PhoneNumber" name="phone"/>
	                </div>
                    <form:errors path="phoneNumber" />
                </li>
            </ul>
            
            <input type="button" id="registBtn" class="btn" value="Register" />
        </form:form>
    </div>

</body>
</html>
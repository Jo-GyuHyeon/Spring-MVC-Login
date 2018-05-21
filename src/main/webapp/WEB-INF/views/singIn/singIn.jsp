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
	
	<script type="text/javascript" src="./resources/js/jquery-1.11.0.min.js"></script>
	
	<script src='https://www.google.com/recaptcha/api.js'></script>
	
	<script type="text/javascript">

  	var commonCl = {
		$singInVO 	: null,
		$phone		: null,
		
		validInit 		: function(){
			//this.$singInVO = $("#annosingInVO");
			
			this.$singInVO = $("#singInVO");
			this.$phone = $('#phone');
		},
		
		vaildFn 		: function() {
			
			//this.$singInVO.attr("action", "annosingInValidChk.do");
<<<<<<< HEAD
			 if (typeof(grecaptcha) !== 'undefined') {
=======
/* 			  if (typeof(grecaptcha) !== 'undefined') {
>>>>>>> origin/dec_valid
				
				if (grecaptcha.getResponse() === "") {
					alert("스팸방지코드(Captcha Code)가 틀렸습니다.");
					
					return;
				}
			}   */
			
			//this.$singInVO.attr("action", "annosingInValidChk.do");
			this.$singInVO.attr("action", "singInValidChk.do");
			
			this.$singInVO.submit();
		},
		
		checkEsangMu	: function() {
			this.$singInVO.attr("action", "singInInsert.do");
			
			this.$singInVO.submit();
		},
		
		autoHyphen		: function() {
			$('#phone').keyup(function() {
				
		        var val = this.value.replace(/\D/g, '');
		        var newVal = '';
		       	var cnt = 2;
		        
		        if(val.length > 2 && val.substr(0, 2)=='02'){
		        	//ex) 02-
		        	newVal += val.substr(0, 2) + '-';
		        	val = val.substr(2);
		        	cnt --;
	        	}

	       	   	for(i=0; val.length > 3 && i<cnt ;i++){
	         	   newVal += val.substr(0, 3) + '-';
	         	   val = val.substr(3);
	         	   
	               if(val.length === 8){
	               	val= val.substr(0, 4) + '-' +val.substr(4) ;
	               	break;
	               }
	            }

		      	newVal += val;
		        this.value = newVal;
		        
		    });
		}
	} 
	
	$(function() {
		commonCl.validInit();
		commonCl.autoHyphen();

        $("#registBtn").click(function() {        	
        	commonCl.vaildFn();
        });
         
        <c:if test="${eSangMu == 'Y'}">
       		alert("이상무 ");
        	commonCl.checkEsangMu();
        </c:if>  
	})
	</script>
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<link rel="stylesheet" href="./resources/css/register.css" type="text/css">
	<!-- css는 script 뒤에 link 한다. chrome 에서 적용안되는 버그가 발생하기 때문이다. -->
	<title>회원가입</title>
</head>
<body>
    <div class="register-wrapp">
        <form:form commandName="singInVO">
         <%-- <form:form commandName="annosingInVO"> --%>
            <h2 class="register-title">Register Form</h2>
            <p class="register-title">Please fill in this form to create a Singup.</p>

            <ul class="input-container">
                <li>	
	                <div>
	                    <i class="fa fa-envelope icon"></i>
	                    <form:input path="mberId" class="input-field" type="email" placeholder="Email" name="email"/>
	                </div>
                	<form:errors path="mberId" class="errors-field"/>
                </li>
                <li>
	                <div>
	                <i class="fa fa-key icon"></i>
	                    <form:input path="password" class="input-field" type="password" placeholder="Password" name="psw"/>
	                </div>
               		<form:errors path="password" class="errors-field"/>
                </li>
                <li>
	                <div>
	                 	<i class="fa fa-lock icon"></i>
	                    <form:input path="passwordConfirm" class="input-field" type="password" placeholder="passwordConfirm" name="pswcfm"/>
	                </div>
                   <form:errors path="passwordConfirm" class="errors-field"/>
                </li>
                <li>
	                <div>
	                	<i class="fa fa-user icon"></i>
	                    <form:input path="mberNm" class="input-field" type="text" placeholder="Username" name="usrnm"/>
	                </div>
                    <form:errors path="mberNm" class="errors-field"/>
                </li>
                <li>
	                <div>
	                	<i class="fa fa-volume-control-phone icon"></i>
	                    <form:input path="phoneNumber" class="input-field" type="text" maxlength="13" placeholder="PhoneNumber" name="phone" id="phone"/>
	                </div>
                    <form:errors path="phoneNumber" class="errors-field"/>
                </li>
                
                <div class="g-recaptcha" data-sitekey="6LdVrlYUAAAAAIxJQgUDwRMD3qvGxaBSvk3lhjsC"></div>
                
            </ul>
            
            <input type="button" id="registBtn" class="btn" value="Register" />
        </form:form>
    </div>

</body>
</html>
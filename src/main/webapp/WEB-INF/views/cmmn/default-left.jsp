<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript">
	
	var left = 
	{
		pageSubmitFn : function(pageName) {
			var $frm = $("#frm");
			
			$("#pageName").val(pageName);
			
			$frm.attr("action", pageName + ".do");

			$frm.submit();
		}
	}
	
	$(function(){	
		var pageName = "<c:out value="${param.pageName}"/>";
		
		$("[prop=menu]").removeClass("active");
		
		$("#"+pageName).addClass("active");
	});
</script>

<form id="frm" name="frm">
	<input type="hidden" id="pageName"  	name="pageName" />
</form>
	<div class="sidebar" data-color="orange" data-image="resources/bootstrap/img/full-screen-image.jpg">
        <!--
            Tip 1: you can change the color of the sidebar using: data-color="blue | azure | green | orange | red | purple"
            Tip 2: you can also add an image using data-image tag
        -->
        <div class="logo">
            <a href="http://www.creative-tim.com" class="logo-text">
                ReGyu TEAM
            </a>
        </div>
		<div class="logo logo-mini">
			<a href="http://www.creative-tim.com" class="logo-text">
				Ct
			</a>
		</div>
    	<div class="sidebar-wrapper">
            <div class="user">
                <div class="photo">
                    <img src="resources/bootstrap/img/default-avatar.png" />
                </div>
                <div class="info">
                    <a data-toggle="collapse" href="#collapseExample" class="collapsed">
                        ReGyu
                        <b class="caret"></b>
                    </a>
                    <div class="collapse" id="collapseExample">
                        <ul class="nav">
                            <li><a href="#">My Profile</a></li>
                            <li><a href="#">Edit Profile</a></li>
                            <li><a href="#">Settings</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <ul class="nav">
                <li prop="menu" class="active" id="main">
                    <a href="#" onclick="javascript:left.pageSubmitFn('main')">
                        <i class="pe-7s-graph"></i>
                        <p>HOME</p>
                    </a>
                </li>
                <li>
                    <a data-toggle="collapse" href="#componentsExamples"  aria-expanded="true">
                        <i class="pe-7s-plugin"></i>
                        <p>로그인 및 회원가입
                           <b class="caret"></b>
                        </p>
                    </a>
                    <div class="collapse in" id="componentsExamples">
                        <ul class="nav">
                        	<c:choose>
                            	<c:when test="${loginVO == null }">
                            		<li prop="menu" class="active" id="serverSide">
		                            	<!-- <a href="#" onclick="javascript:left.pageSubmitFn('annosingIn')">서버 사이드 회원가입</a> -->
		                            	<a href="#" onclick="javascript:left.pageSubmitFn('singIn')">서버 사이드 회원가입</a>
		                            </li>
		                            <li prop="menu" class="active" id="egovLoginUsr">
		                            	<a href="#" onclick="javascript:left.pageSubmitFn('login')">로그인</a>
		                            </li>
                            	</c:when>
                            	<c:otherwise>
                            		<li prop="menu" class="active" id="socialNaverSync">
		                            	<a href="#" onclick="javascript:left.pageSubmitFn('socialNaverSync')">네이버아이디연동</a>
		                            </li>
		                            <li prop="menu" class="active" id="logoutGo">
		                            	<a href="logout.do" onclick="javascript:left.pageSubmitFn('logoutGo')">로그아웃</a>
		                            </li>
                            	</c:otherwise>
                            </c:choose>
                        </ul>
                    </div>
                </li>
            </ul>
    	</div>
    </div>
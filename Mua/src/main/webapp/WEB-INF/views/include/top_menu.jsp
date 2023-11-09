<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%int login_state = Integer.parseInt((String)session.getAttribute("login_state"));%>
<c:set var='root' value='${pageContext.request.contextPath}/' />
<script src="${root}script/login.js"></script>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
  	<a class="navbar-brand" href="#">Mua</a>
  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    	<span class="navbar-toggler-icon"></span>
  	</button>
  	<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    	<div class="navbar-nav">
      		<a class="nav-link active" href="#">Home <span class="sr-only">(current)</span></a>
      		<a class="nav-link active" href="#">Features</a>
     		<a class="nav-link active" href="#">Pricing</a>
      		<a class="nav-link disabled">Disabled</a>
    	</div>
  	</div>
  	<button class="btn btn-outline-success my-2 my-sm-0 btn_blank" onclick="location.href='#'" id="login">로그인</button>
  	<button class="btn btn-outline-success my-2 my-sm-0 btn_blank" onclick="location.href='#'" id="register">회원가입</button>
  	<button class="btn btn-outline-success my-2 my-sm-0 btn_blank" onclick="location.href='#'" id="user_modify">정보수정</button>
  	<button class="btn btn-outline-success my-2 my-sm-0 btn_blank" onclick="location.href='#'" id="logout">로그아웃</button>
</nav>

<%
if(login_state == -1){
	%>
	<script>
		login_function();
	</script>
	<%
}else{
	%>
	<script>
		logout_function();
	</script>
	<%
}
%>
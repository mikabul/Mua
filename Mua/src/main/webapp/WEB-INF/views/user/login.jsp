<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>

	<form:form modelAttribute = "tempLoginUserBean" method = "post" action = "${root }user/login_pro">
	
		<div class="form_input">
			<form:label path = "user_id">아이디</form:label>
			<form:input path="user_id"/>
			<form:errors path = "user_id" style = "color:red;"/>
		</div>
		
		<div class="form_input">
			<form:label path = "user_pw">비밀번호</form:label>
			<form:input path="user_pw"/>
			<form:errors path = "user_pw" style = "color:red;"/>
		</div>
		
		<form:button type = "submit">로그인</form:button>
		
	</form:form>

</body>
</html>
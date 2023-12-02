<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	
	<form:form action = "${root }user/modify_pro" method="post" modelAttribute ="modifyUserBean">
		
		
		<div class="input-box">
		<form:label path = "user_name">이름 : </form:label>
		<form:input path = "user_name"></form:input>
		<form:errors path = "user_name"></form:errors>
		</div>
		
		<div class="input-box">
		<form:label path = "user_email">이메일 : </form:label>
		<form:input path = "user_email" readonly = "true"></form:input>
		<form:errors path = "user_email"></form:errors>
		</div>
		
		<div class="input-box">
		<form:label path = "user_address">주소 : </form:label>
		<form:input path = "user_address" readonly = "true"></form:input>
		<form:errors path = "user_address"></form:errors>
		</div>
		
		<div class="input-box">
		<form:label path = "user_tel">전화번호 : </form:label>
		<form:input path = "user_tel" readonly = "true"></form:input>
		<form:errors path = "user_tel"></form:errors>
		</div>
		
		<form:button type ="submit">정보수정</form:button>
		
	</form:form>
	
</body>
</html>
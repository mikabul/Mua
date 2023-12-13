<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mua</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<link rel="stylesheet" href="../style/login.css" />
</head>
<body>
	
	<div class="wrapper">
		<form:form modelAttribute="tempAdminDto" method="post" action="${root }admin/login_pro">

			<h1>Login</h1>

			<div class="input-box">
				<form:input path="admin_id" placeholder="ID" />
				<i class='bx bxs-user'></i>
			</div>

			<div class="input-box">
				<form:password path="admin_pw" placeholder="PW" />
				<i class='bx bxs-lock-alt'></i>
			</div>

			<form:button type="submit" class="btn">Login</form:button>

		</form:form>
	</div>

</body>
</html>
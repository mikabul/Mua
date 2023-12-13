<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<link rel="stylesheet" href="../style/login.css" />
</head>
<body>
	
	<div class="wrapper">
		<form:form modelAttribute="tempLoginUserBean" method="post"
			action="${root }user/login_pro">

			<h1>Login</h1>

			<div class="input-box">
				<form:input path="user_id" placeholder="UserID" />
				<div class="error-wrapper">
					<form:errors path="user_id" class="error-box" />
				</div>
				<i class='bx bxs-user'></i>
			</div>

			<div class="input-box">
				<form:password path="user_pw" placeholder="UserPW" />
				<div class="error-wrapper">
					<form:errors path="user_pw" class="error-box" />
				</div>
				<i class='bx bxs-lock-alt'></i>
			</div>

			<div class="remember-forgot">
				<label><input type="checkbox">Remember Me</label>
				<a href="${root }user/getGoogleAuthUrl">Forgot password?</a>
			</div>

			<form:button type="submit" class="btn">Login</form:button>

			<div class="register-link">
				<p>
					Don't have an account? <a href="${root }user/register">Register</a>
				</p>
			</div>
		</form:form>
	</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
<link rel="stylesheet" href="../style/login.css" />
</head>
<body>
	<!-- 상단 메뉴 부분 -->
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	
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

			<form:button type="submit" class="btn">Login</form:button>

			<div class="googleSignUp">
				<button type ="button" onclick="location.href='${root}user/getGoogleAuthUrl'">
				</button>
			</div>
			
			<div class="remember-forgot">
				<a href="${root }user/modifyPassword">Forgot password?</a>
			</div>
			
			<div class="register-link">
				<p>
					Don't have an account? <a href="${root }user/register">Register</a>
				</p>
			</div>
		</form:form>
	</div>
	
	
</body>
</html>
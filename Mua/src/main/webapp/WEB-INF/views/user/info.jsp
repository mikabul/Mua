<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
<link rel="stylesheet" href="../style/info.css" />
</head>
<body>
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	
	<div class="wrapper">
	
		<form:form action="${root }user/modify" method="post" modelAttribute="infoUserBean">
			
			<h1>MyAccount</h1>
			<!-- //유저아이디, 이름, 이메일, 집주소, 전화번호, 생일, 생성일 -->
			<div class="input-box">
				<form:input path = "user_id" placeholder ="User ID" readonly = true/>
				<i class='bx bxs-user'></i>
			</div>
			<div class="input-box">			
				<form:input path = "user_name" placeholder = "User Name"  readonly = true/>
				<i class='bx bx-edit-alt'></i>
			</div>
			<div class="input-box">			
				<form:input path = "user_email" placeholder = "User Email"  readonly = true/>
				<i class='bx bx-envelope'></i>
			</div>
			<div class="input-box">			
				<form:input path = "user_address" placeholder="User address"  readonly = true/>
				<i class='bx bx-home'></i>
			</div>
			<div class="input-box">			
				<form:input path = "user_tel" placeholder="User Tel" readonly = true/>
				<i class='bx bxs-phone'></i>
			</div>
			<div class="input-box">			
				<form:input path = "user_birthday" placeholder="User Birthday" readonly = true/>
			</div>
			<div class="input-box">			
				<form:input path = "user_registdate" placeholder = "User RegistDate" readonly = true/>
			</div>
			
			<div class="row justify-content-center">
				<form:button class="btn btn-outline-dark" type="button" onclick="location.href='${root }main'" style="margin-right:20px; width:100px">메인</form:button>
				<form:button class="btn btn-outline-dark" type="submit" style="margin-left:20px; width:100px;">수정하기</form:button>
			</div>
		</form:form>
	</div>

</body>
</html>
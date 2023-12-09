<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<%
request.setCharacterEncoding("utf-8");
%>
<script>
	function checkUserIDExist(){
		var user_id=$("#user_id").val()
		
		if(user_id.length == 0){
			alert("아이디를 입력하세요.")
			return
		}
		
		$.ajax({
			url: '${root}user/checkUserIdExist/' + user_id,
			type: 'get',
			dataType: 'text',
			success: function(result){
				if(result.trim() == 'true'){
					alert('사용할 수 있는 아이디 입니다.');
					$("#userIdExit").val('true');
				}else {
					alert('사용할 수 없는 아이디입니다.');
					$("#userIdExit").val('false');
				}
			}
		});//ajax_END
	}//checkUserIDExist_END
	
	//사용자 아이디란에 입력하면 무조건 false
	function resetUserIdExist(){
		$("#userIdExit").val('false')
	}
</script>
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
<link rel="stylesheet" href="../style/register.css" />
</head>
<body>
	<div class="wrapper">
		<form:form action="${root }user/register_pro" method="post"
			modelAttribute="registerUserBean">
			<form:hidden path="userIdExit" />

			<h1>Register</h1>

			<div class="input-box">
				<form:input path="user_name" placeholder="User Name" />
				<div class="error-wrapper">
					<form:errors path="user_name" class="error-box" />
				</div>
				<i class='bx bx-edit-alt'></i>
			</div>
			
			<div class="input-box-id">
				<form:input path="user_id" placeholder="User Id" onkeypress="resetUserIdExist()" />
					<button type="button" class="idbtn"
						onclick="checkUserIDExist()">Duplication</button>
				<div class="error-wrapperID">
					<form:errors path="user_id" class="error-boxID"/>
				</div>
				<i class='bx bxs-user'></i>
			</div>
			
			<div class="input-box">
				<form:password path="user_pw" placeholder="Password" />
				<div class="error-wrapper">
					<form:errors path="user_pw" class="error-box" />
				</div>
				<i class='bx bxs-lock-alt'></i>
			</div>
			<div class="input-box">
				<form:password path="user_pw2" placeholder="Password Check" />
				<div class="error-wrapper">
					<form:errors path="user_pw2" class="error-box" />
				</div>
				<i class='bx bxs-lock-alt'></i>
			</div>
			<div class="input-box">
				<form:input path="user_email" placeholder="User Email" />
				<div class="error-wrapper">
					<form:errors path="user_email" class="error-box" />
				</div>
				<i class='bx bx-envelope'></i>
			</div>
			<div class="input-box">
				<form:input path="user_tel" placeholder="User Tel" />
				<div class="error-wrapper">
					<form:errors path="user_tel" class="error-box" />
				</div>
				<i class='bx bxs-phone' ></i>
			</div>
			<div class="input-box">
				<form:input path="user_address" placeholder="User Address" />
				<div class="error-wrapper">
					<form:errors path="user_address" class="error-box" />
				</div>
				<i class='bx bx-home' ></i>
			</div>
			<div class="form-group">
				<div class="text-right">
					<form:button type="submit" class="btn">Register</form:button>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>






























<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>

	var myVar = true;

	$(document).ready(function(){
		$('#changeBtn').click(function(){
			
			if(myVar){
				myVar = false;
				$('.infoID').addClass('non');
				$('.info').removeClass('non');
			}
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
<link rel="stylesheet" href="../style/modifyPassword.css" />
<script>

	var myVar = true;

	$(document).ready(function(){
		$('#changeBtn').click(function(){
			$('.wrapper').css('height', '300px');
			var user_email=$("#user_email").val();
			
			if(myVar){
				myVar = false;
				$('.infoID').addClass('non');
				$('.info').removeClass('non');
			}
			
			$.ajax({
				url: '${root}user/modifyCertificationCode/' + user_email,
				dataType : 'text',
				success: function(data){
					console.log("data : "+data);
					code = data;
					$("#authCode1").val(code);
					alert('인증코드가 발송되었습니다.');
				}
			});
		   // 기본적으로 submit 버튼을 비활성화 상태로 설정
		    $('form button[type="submit"]').prop('disabled', true);

		    // authCode2의 값이 변경될 때마다 체크하여 버튼 활성화/비활성화
		    $('form input[name="authCode2"]').on('input', function() {
		        var authCode2Value = $(this).val();

		        // authCode2 값이 존재하는지 확인하여 submit 버튼 상태 변경
		        if (authCode2Value.trim() !== '') {
		            $('form button[type="submit"]').prop('disabled', false);
		        } else {
		            $('form button[type="submit"]').prop('disabled', true);
		        }
		    });
		});
	});
</script>
	
<style>
	.non{
		
		display:none;
		
	}
</style>
	
</head>
<body>

	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	<div class="wrapper">
	<form:form action="${root }user/modify_pro" method="post"
		modelAttribute="modifyUserBean">
		<form:hidden path="authCode1"/>
		<form:hidden path="user_id"/>
		<form:hidden path="user_email"/>
		<form:hidden path="user_birthday"/>
		<form:hidden path="user_registdate"/>
		
		<h1>User Modify</h1>
		<hr />
			<div class="input-box infoID">
				<form:input path="user_name" placeholder = "수정할 이름" value=""></form:input>
				
				<form:errors path="user_name"></form:errors>
				
				<i class='bx bxs-user'></i>
			</div>
			
			<div class="input-box infoID">
				<form:input path="user_address" placeholder = "수정할 주소" value=""></form:input>
				<form:errors path="user_address"></form:errors>
				<i class='bx bx-home'></i>
			</div>

			<div class="input-box infoID">
				<form:input path="user_tel" placeholder = "수정할 전화번호" value=""></form:input>
				<form:errors path="user_tel"></form:errors>
				<i class='bx bxs-phone'></i>
			</div>

			<button type="button" id = "changeBtn" class="btn infoID" style="width:80%; color:#303080; margin-left:31px;">정보수정</button>
		
		<div class="input-box info non">
				<form:input path="authCode2" type="text" placeholder="Certification Code"/>
				<form:errors path = "authCode2"/>
				<i class='bx bx-envelope'></i>
			<form:button type="submit" class="btn" style=" width: 80%; margin-top: 50px; height: 40px; margin-left: 33px;">확인</form:button>
		</div>

		<div class="info non">
			<div class="input-box">				
				<form:label path="user_id">아이디 : </form:label>
				<form:input path="user_id"></form:input>
			</div>
			<div class="input-box">
				<form:label path="user_pw">비밀번호 : </form:label>
				<form:password path="user_pw"></form:password>
			</div>

			<form:button type="submit">확인</form:button>
		</div>

	</form:form>
	</div>
</body>
</html>
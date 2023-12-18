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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="../style/modifyPassword.css" />
<script>

	var myVar = true;
	
    $(document).ready(function(){
      $('#changeBtn').prop('disabled', true); // 페이지 로드 시 버튼 비활성화

      $('#user_email').on('input', function() {
        var emailValue = $(this).val().trim();

        if (emailValue === '') {
          $('#changeBtn').prop('disabled', true); // 입력값이 없으면 버튼 비활성화
        } else {
          $('#changeBtn').prop('disabled', false); // 입력값이 있으면 버튼 활성화
        }
      });
    });
	
	$(document).ready(function(){
		$('#changeBtn').click(function(){
			if(myVar){
				myVar = false;
				$('.infoID').addClass('non');
				$('.info').removeClass('non');
			}
			var user_email=$("#user_email").val();
			
			$.ajax({
				url: '${root}user/modifyCertificationCode/' + user_email,
				dataType : 'text',
				success: function(data){
					console.log("data : "+data);
					code = data;
					$("#authCode1").val(code);
					console.log($("#authCode1"))
					alert('인증코드가 발송되었습니다.');

				}
			});
		});
	});
</script>
	
	
</head>
<body>
	<!-- 상단 메뉴 부분 -->
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	<div class="wrapper">
		<form:form action="${root }user/modifyPassword_pro" method="post" modelAttribute="modifyPasswordBean">
			<form:hidden path="authCode1"/>
			<form:hidden path="user_id" value = "123"/>
			<form:hidden path="user_address"/>
			<form:hidden path="user_tel"/>
			<form:hidden path="user_name"/>
			<form:hidden path="user_birthday"/>
			<form:hidden path="user_registdate"/>
			
			<h1>Change Password</h1>
			
			<div class="input-box">
				<form:input path="user_email" placeholder = "Enter your Email" style="width:70%"></form:input>
				<form:errors path="user_email"></form:errors>
				<button type="button" id = "changeBtn" class= "btn" style="color:#303080">인증요청</button>
			</div>
			
			
				<div class="input-box">
					<form:input path="user_pw" type="text" placeholder="New Password"/>
					<form:errors path="user_pw"/>
				</div>
				<div class="input-box">
					<form:input path="user_pw2" type="text" placeholder="Check Password"/>
					<form:errors path="user_pw2"/>
				</div>
				
				<div class="input-box">
					<form:input path="authCode2" type="text" placeholder="Certification Code"/>
					<form:errors path = "authCode2"/>
				</div>
				<form:button type="submit" class="btn" style="width: 80%; justify-content: center; margin-left: 30px; height: 40px; color: #333;">확인</form:button>
			
		</form:form>
	</div>
</body>
</html>
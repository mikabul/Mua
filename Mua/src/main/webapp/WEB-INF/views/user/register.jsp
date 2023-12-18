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
<%
request.setCharacterEncoding("utf-8");
%>
<script>
	//중복체크
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
	
	// 이메일 중복체크
	function checkUserEmailExit(){
		var user_email=$("#user_email").val();
		
		if(user_email.length == 0){
			alert("이메일을 입력해주세요")
			return
		}
		
		$.ajax({
			url: '${root}user/checkUserEmailExit/' + user_email,
			type: 'get',
			dataType: 'text',
			success: function(result){
				if(result.trim() == 'true'){
					$.ajax({
						
						type:'get',
						url : '${root}user/checkCertificationCode/' + user_email,
						dataType : 'text',
						success: function(data){
							console.log("data : "+data);
							code = data;
							$("#authCode1").val(code);
							alert('사용할 수 있는 이메일 입니다. 인증번호가 발송되었습니다.');
						}
						
					});	
					$("#userEmailExit").val('true');
				}else {
					alert('사용할 수 없는 이메일 입니다.');
					$("#userEmailExit").val('false');
				}
			}
		});//ajax_END
	}
	
	//사용자 아이디란에 입력하면 무조건 false
	function resetUserIdExist(){
		$("#userIdExit").val('false')
	}
	
	function resetUserEmailExit(){
		$("#userEmailExit").val('false')
	}
	
	//사용자가 입력한 비밀번호2 값 받아서 일치여부 확인
	function checkPasswordEquals(){
		var password = document.getElementById("user_pw").value;
		var password2 = document.getElementById("user_pw2").value;
		
		var pw2Element = document.querySelector('.checkPWEquals');
		
		if(password === password2){
			pw2Element.textContent = "비밀번호가 일치합니다."
		}else{
			pw2Element.textContent = "비밀번호가 일치하지 않습니다."
		}
		
	}
	
	//사용자가 입력한 비밀번호 실시간으로 받아서 검증
	function checkPasswordSecGrade(){
		var password = document.getElementById("user_pw").value;
		var gradeElement = document.querySelector('.checkOutGrade');
		
		if(password.length === 0){
			gradeElement.textContent = "보안 등급 : ";
			return;
		}
		
		var grade = calculatePasswordGrade(password);
		
		switch(grade){
		
		case "weak":
			gradeElement.textContent = "보안 등급 : 낮음";
			break;
		case "medium":
			gradeElement.textContent = "보안 등급 : 중간";
			break;
		case "strong":
			gradeElement.textContent = "보안 등급 : 높음";
			break;
		case "very strong":
			gradeElement.textContent = "보안 등급 : 매우 높음";
			break;
		default:
			gradeElement.textContent = "보안 등급 : "
		}
		
	}
	
    function calculatePasswordGrade(password) {
        var grade = "weak";

        if (password.length < 8) {
            return grade;
        }

        var hasUpperCase = /[A-Z]/.test(password);
        var hasLowerCase = /[a-z]/.test(password);
        var hasNumber = /\d/.test(password);
        var hasSpecialChars = /[!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?]/.test(password);

        if (hasUpperCase && hasLowerCase && hasNumber && hasSpecialChars) {
            grade = "very strong";
        } else if (hasUpperCase && hasLowerCase && hasNumber || (hasSpecialChars && hasLowerCase && hasNumber)) {
            grade = "strong";
        } else if ((hasUpperCase && hasLowerCase) || (hasNumber && hasSpecialChars)) {
            grade = "medium";
        }

        return grade;
    }
    
</script>
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
<link rel="stylesheet" href="../style/register.css" />
</head>
<body>
	<!-- 상단 메뉴 부분 -->
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	
	
	<div class="wrapper" style="margin-top:100px;">
		<form:form action="${root }user/register_pro" method="post" modelAttribute="registerUserBean">
			<form:hidden path="userIdExit" />
			<form:hidden path="userEmailExit" />
			<form:hidden path="authCode1"/>
			<h1>Register</h1>

			<div class="input-box">
				<form:input path="user_name" placeholder="User Name" />
					<form:errors path="user_name" class="error-box" />
				<i class='bx bx-edit-alt'></i>
			</div>

			<div class="input-box-id">
				<form:input path="user_id" placeholder="User Id"
					onkeypress="resetUserIdExist()" />
				<button type="button" class="idbtn" onclick="checkUserIDExist()">Duplicate</button>
					<form:errors path="user_id" class="error-boxID" />
				<i class='bx bxs-user'></i>
			</div>

			<div class="input-box-email">
				<form:input path="user_email" placeholder="User Email"
					onkeypress="resetUserEmailExit()"/>
				<button type="button" class="emailbtn"
					onclick="checkUserEmailExit()">Duplicate</button>

					<form:errors path="user_email" class="error-boxID" />
				<i class='bx bx-envelope'></i>
			</div>
			
			<div class="input-box" style="margin-bottom:20px;">
				<form:input path = "authCode2" type = "text" placeholder="Certification Code"/>
					<form:errors path = "authCode2" class ="error-box"/>
				<i class='bx bx-envelope'></i>
			</div>
			
			<div class="checkOutGrade"
				style="margin-bottom: -30px; margin-left: 10px">보안 등급 :</div>
			<div class="input-box">
				<form:input path="user_pw" placeholder="Password"
					oninput="checkPasswordSecGrade(); checkPasswordEquals();" />
					<form:errors path="user_pw" class="error-box" />
				<i class='bx bxs-lock-alt'></i>
			</div>

			<div class="input-box">
				<form:input path="user_pw2" placeholder="Password Check"  oninput="checkPasswordEquals()"/>
				<div class="checkPWEquals"
					style="margin-bottom: -50px; margin-left: 10px" >비밀번호가 일치하지
					않습니다.</div>
				<i class='bx bxs-lock-alt'></i>
			</div>

			<div class="input-box">
				<form:input path="user_tel" placeholder="User Tel" />
					<form:errors path="user_tel" class="error-box" />
				<i class='bx bxs-phone'></i>
			</div>

			<div class="input-box">
				<form:input path="user_address" placeholder="User Address" />
					<form:errors path="user_address" class="error-box" />
				<i class='bx bx-home'></i>
			</div>
			<div class="input-box-date">
				<form:input path="user_birthday" type="date" placeholder = "User Birthday"/>
					<form:errors path="user_birthday" class="error-box" />
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

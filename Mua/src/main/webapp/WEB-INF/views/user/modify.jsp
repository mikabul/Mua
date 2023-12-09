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

	<form:form action="${root }user/modify_pro" method="post"
		modelAttribute="modifyUserBean">

		<div class="infoID">
			<div class="input-box">
				<form:label path="user_name">이름 : </form:label>
				<form:input path="user_name"></form:input>
				<form:errors path="user_name"></form:errors>
			</div>

			<div class="input-box">
				<form:label path="user_email">이메일 : </form:label>
				<form:input path="user_email" readonly="true"></form:input>
				<form:errors path="user_email"></form:errors>
			</div>

			<div class="input-box">
				<form:label path="user_address">주소 : </form:label>
				<form:input path="user_address" readonly="true"></form:input>
				<form:errors path="user_address"></form:errors>
			</div>

			<div class="input-box">
				<form:label path="user_tel">전화번호 : </form:label>
				<form:input path="user_tel" readonly="true"></form:input>
				<form:errors path="user_tel"></form:errors>
			</div>

			<div class="input-box">
				<form:label path="user_birthday" type = "date">생년월일 : </form:label>
				<form:input path="user_birthday" readonly="true"></form:input>
				<form:errors path="user_birthday"></form:errors>
			</div>

			<div class="input-box">
				<form:label path="user_registdate" type="date">가입일자 : </form:label>
				<form:input path="user_registdate" readonly="true"></form:input>
				<form:errors path="user_registdate"></form:errors>
			</div>

			<button type="button" id = "changeBtn">정보수정</button>
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
</body>
</html>
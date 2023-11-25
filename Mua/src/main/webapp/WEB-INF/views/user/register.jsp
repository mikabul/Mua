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
<% request.setCharacterEncoding("utf-8"); %>
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
</head>
<body>

	<form:form action="${root }user/register_pro" method="post"
		modelAttribute="registerUserBean">
		<form:hidden path="userIdExit" />
		<div class="form-group">
			<form:label path="user_name">이름</form:label>
			<form:input path="user_name" class="form-control" />
			<form:errors path="user_name" style="color:red" />
		</div>
		<div class="form-group">
			<form:label path="user_id">아이디</form:label>
			<div class="input-group">
				<form:input path="user_id" class="form-control"
					onkeypress="resetUserIdExist()" />
				<div class="input-group-append">
					<button type="button" class="btn btn-primary"
						onclick="checkUserIDExist()">중복확인</button>
				</div>
			</div>
			<form:errors path="user_id" style="color:red" />
		</div>
		<div class="form-group">
			<form:label path="user_pw">비밀번호</form:label>
			<form:password path="user_pw" class="form-control" />
			<form:errors path="user_pw" style="color:red" />
		</div>
		<div class="form-group">
			<form:label path="user_pw2">비밀번호 확인</form:label>
			<form:password path="user_pw2" class="form-control" />
			<form:errors path="user_pw2" style="color:red" />
		</div>
		<div class="form-group">
			<form:label path="user_email">이메일</form:label>
			<form:input path="user_email" class="form-control" />
			<form:errors path="user_email" style="color:red" />
		</div>
		<div class="form-group">
			<form:label path="user_tel">전화번호</form:label>
			<form:input path="user_tel" class="form-control" />
			<form:errors path="user_tel" style="color:red" />
		</div>
		<div class="form-group">
			<form:label path="user_address">주소</form:label>
			<form:input path="user_address" class="form-control" />
			<form:errors path="user_address" style="color:red" />
		</div>
		<div class="form-group">
			<div class="text-right">
				<form:button type="submit" class="btn btn-primary">회원가입</form:button>
			</div>
		</div>
	</form:form>

</body>
</html>






























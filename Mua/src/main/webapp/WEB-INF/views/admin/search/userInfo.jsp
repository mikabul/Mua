<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
<!-- 부트 스트랩 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="styleSheet" href="${root}style/searchBar.css">
<link rel="styleSheet" href="${root}style/any_main.css">

<script>

var on = true;
$(document).ready(function(){
	$.ajax({
		url: '${root}admin/search/ajax/checkBaned',
		type: 'GET',
		data:{
			user_num: ${infoUserBean.user_num}
		},
		success: function(data){
			if(data.trim() === 'true'){
				$('#banish-form').removeClass('none');		
			}else{
				$('#deny-form').removeClass('none');
			}
		}
	});
});
</script>

<style>
	.none{
		display:hidden;
	}
</style>

</head>
<body>
	<header>
		<c:import url="/WEB-INF/views/admin/include/top.jsp" />
	</header>
	<section style="width: 70%; margin: 30px 15%">
		<form:form modelAttribute="infoUserBean"
			action="${root}admin/search/modifyUser">
			<hr />
			<div class="form-group">
				<!-- //이름 이메일 전화번호 생성일 번호 -->
				<div class="form-row justify-content-center">
					<div class="col-5" style="margin-right: 30px;">
						<form:label path="user_num" style="margin-top:20px;">유저 번호</form:label>
						<form:input path="user_num" class="form-control" readonly="true"></form:input>
					</div>
					<div></div>
					<div class="col-5" style="margin-left: 30px;">
						<form:label path="user_email" style="margin-top:20px;">유저 이메일</form:label>
						<form:input path="user_email" class="form-control" readonly="true"></form:input>
					</div>
				</div>
				<div class="form-row justify-content-center">
					<div class="col-5" style="margin-right: 30px;">
						<form:label path="user_registdate" style="margin-top:20px;">유저 생성일</form:label>
						<form:input path="user_registdate" class="form-control"
							readonly="true"></form:input>
					</div>
					<div></div>
					<div class="col-5" style="margin-left: 30px;">
						<form:label path="user_tel" style="margin-top:20px;">유저 전화번호</form:label>
						<form:input path="user_tel" class="form-control" readonly="true"></form:input>
					</div>
				</div>

				<div class="container">
					<div class="row justify-content-center">
						<div class="col-md-5">
							<form:label path="user_name" style="margin-top: 20px;">유저 이름</form:label>
							<form:input path="user_name" class="form-control" />
							<form:errors path="user_name"></form:errors>
						</div>
						<div class="col-md-2">
							<button type="submit" class="btn btn-dark" style="margin-bottom:-111px;">수정</button>
						</div>
					</div>
				</div>
				<hr />
			</div>
		</form:form>

		<div id="banish-form" class = "none">
			<form action="${root }admin/search/banish" method="post" id ="banishedUser">
				<div class="form-group">
					<div class="form-row justify-content-center">
						<input class = "form-control col-3" type="text" name ="user_num1" value="${infoUserBean.user_num}" readonly/>
						<input class = "form-control col-3" type="text" name ="admin_num1" value="${loginAdminDto.admin_num}" readonly/>
						<input class = "form-control col-3" type="date" name ="end_date"/>
					</div>
					
					<div class="form-row justify-content-center" style="margin-top:20px;">
						<button type="submit" class="btn btn-dark col-2">차단</button>
					</div>
				</div>
			</form>
		</div>
		<div id="deny-form" class = "none">
			<form action="${root }admin/search/banish-deny" id = "deny">
				<div class="form-group">
					<div class="form-row justify-content-center" style="margin-top:20px;">
						<input class = "form-control" type="text" name="user_num2" value="${infoUserBean.user_num}" style="display:hidden"/>
						<button type="submit" class="btn btn-dark col-2" style="margin-top:20px;">차단해제</button>
					</div>
				</div>
			</form>
		</div>
	</section>
</body>
</html>
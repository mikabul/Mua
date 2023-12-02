<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
<link rel="styleSheet" href="../style/info.css">
</head>
<body>
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
		<div class="wrapper">
			<div class="row justify-content-center">
				<form:form modelAttribute = "infoUserBean" action = "${root }main" method = "get">
					<div class="input-box">
					<form:label path = "user_id">좋아하는 노래 : </form:label>
					<c:out value = "despacito"> </c:out>
					</div>
					
					<div class="input-box">
					<form:label path = "user_id">아이디 : </form:label>
					<form:input path = "user_id" readonly = "true"></form:input>
					</div>
					
					<div class="input-box">
					<form:label path = "user_name">이름 : </form:label>
					<form:input path = "user_name" readonly = "true"></form:input>
					</div>
					
					<div class="input-box">
					<form:label path = "user_email">이메일 : </form:label>
					<form:input path = "user_email" readonly = "true"></form:input>
					</div>
					
					<div class="input-box">
					<form:label path = "user_address">주소 : </form:label>
					<form:input path = "user_address" readonly = "true"></form:input>
					</div>
					
					<div class="input-box">
					<form:label path = "user_tel">전화번호 : </form:label>
					<form:input path = "user_tel" readonly = "true"></form:input>
					</div>
					
					<div>
						<form:button type = "submit">메인 페이지</form:button>
						<form:button type="button" onclick = "location.href='${root}user/modify'">회원정보 수정</form:button>
					</div>
				</form:form>
			</div>
		</div>
</body>
</html>
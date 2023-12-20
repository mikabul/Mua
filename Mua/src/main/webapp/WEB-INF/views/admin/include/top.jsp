<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
<div>
	<div>
		<div class="text-center">
			<div>
				<p>현재 접속자 : ${loginAdminDto.admin_num} 번 관리자</p>
			</div>
			<div>
				<button type="button" class="btn btn-outline-primary" onclick="location.href='${root}admin/main'">
					메인으로
				</button>
				<button type="button" class="btn btn-outline-danger" onclick="location.href='${root}admin/logout'">
					로그아웃
				</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>
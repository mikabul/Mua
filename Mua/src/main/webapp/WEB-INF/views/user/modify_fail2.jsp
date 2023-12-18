<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<script>
	alert("인증코드가 틀렸습니다. 혹은 비밀번호가 일치하지않습니다.")
	location.href="${root}user/modifyPassword"
</script>
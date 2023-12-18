<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value='${pageContext.request.contextPath}/' />

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<footer style="position: static; bottom: 0; width: 100%;">
	<div class="container-fluid bg-dark text-white"
		style="margin-top: 50px; padding-top: 30px; padding-bottom: 30px; width: 100%;">
		<div class="container">
			<p style="text-align: left">http://www.softSoldesk.co.kr</p>
			<p>게시판 예제</p>
			<p>사업자번호 : 000-111-222</p>
			<p id="adminPage" style="width: 5px; height: 5px; background: red"></p>
		</div>
	</div>
</footer>

<script>
	$('#adminPage').on('dblclick', function(){
		location.href='${root}admin/login';
	});
</script>
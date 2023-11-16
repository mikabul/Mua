<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
int login_state = Integer.parseInt((String) session.getAttribute("login_state"));
%>
<c:set var='root' value='${pageContext.request.contextPath}/' />
<script src="${root}script/top_menu.js"></script>
<script>
	var show = false;
	function show_menu() {
		show = show_hide_menu(show);
	}
</script>
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Nanum+Myeongjo&display=swap')
	;

.navbar_font {
	font-family: 'Gowun Dodum', sans-serif;
	font-family: 'Nanum Myeongjo', serif;
	background-color: #e3f2fd;
}

.btn_left {
	border: none;
	text-align: left;
	position: absolute;
	left: 3%;
}
</style>
<div style="position: fixed; width: 100%; height: 90px; z-index: 100; background-color: white;">
	<div>
		<nav class="nav nav-pills nav-fill">
			<button type="button"
				class="btn btn-outline-secondary btn-sm btn_left"
				onclick="show_menu()">
				<img src="./images/menu-line.png" alt="menu-line.png" />
			</button>
			<a class="nav-link" href="#"> 메뉴1 </a> <a class="nav-link" href="#">
				메뉴2 </a> <a class="nav-link" href="#"> 메뉴3 </a>
		</nav>
	</div>

	<div>
		<ul class="nav justify-content-center">
			<li class="nav-item"><a class="nav-link active" href="#">Active</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
			<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
			<li class="nav-item"><a class="nav-link disabled">Disabled</a></li>
		</ul>
	</div>

	<div>
		<div class="nav_p">
			<nav class="nav nav_hide nav_under" id="under_menu"></nav>
		</div>
	</div>
</div>
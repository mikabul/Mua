<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value='${pageContext.request.contextPath}/' />
<script src="${root}script/top_menu.js"></script>
<c:set var='user_num' />
<link rel="styleSheet" href="${root }style/top_menu.css">
</style>
<div style="position: fixed; width: 100%; height: 90px; z-index: 100; background-color: #d4f0f0; top: 0px;">
	<div>
		<nav class="nav nav-pills nav-fill" style="align-items: center">
			<!-- 삼선 버튼 -->
			<div class="nav-link">
				<button class="btn btn-outline-secondary btn_left" type="button"
					data-toggle="collapse" data-target="#collapseExample"
					aria-expanded="false" aria-controls="collapseExample">
					<img src="${root }images/menu-line.png" alt="" />
				</button>
			</div>
			<!-- 로고 / 메인 페이지로 이동할 수 있도록 연결-->
			<a class="nav-link" href="${root }main" style="margin: 0; padding: 0;">
				<img src="${root}/images/logo.png" alt="" style="width: auto; height: 90px;"/>
			</a>
			<!-- 로그인 회원가입 -->
			<div class="nav-link">
				<div style="text-align: right; margin-right: 3%;">
					<c:choose>
					<c:when test="${loginUserBean.userLogin == true }">
						<button type="button" class="btn btn-outline-primary" onclick = "location.href='${root}user/info'">회원정보</button>
						<button type="button" class="btn btn-outline-primary" onclick = "location.href='${root}user/logout'">로그아웃</button>
					</c:when>
					<c:when test="${loginUserBean.userLogin == false }">
						<button type="button" class="btn btn-outline-primary" onclick="location.href='${root}user/login'">로그인</button>
						<button type="button" class="btn btn-outline-primary" onclick="location.href='${root}user/register'">회원가입</button>
					</c:when>
				</c:choose>
				</div>
			</div>
		</nav>
	</div>
	<!-- 추가 메뉴(삼선 버튼) -->
	<div class="collapse" id="collapseExample" style="margin-top: 0.1px;">
		<div class="card card-body">
			<ul class="nav nav-pills nav-fill" style="width: 100%;">
				<!-- 페이지 연결 -->
				<li class="nav-item"><a class="nav-link" href="${root }chart/top100"> 차트 </a></li>
				<li class="nav-item"><a class="nav-link" href="${root }chart/newchart"> 최신곡 </a></li>
				<li class="nav-item"><a class="nav-link" href="${root }chart/genre"> 장르별 노래 탐색 </a></li>
				<li class="nav-item"><a class="nav-link" href="#"> 콘서트 </a></li>
			</ul>
		</div>
	</div>

</div>
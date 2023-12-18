<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<!-- 내부 스타일 -->
<style>
	.btn {
		margin: 1px 5px;
	}
</style>
</head>
<body>
	
	<header>
		<c:import url="/WEB-INF/views/admin/include/top.jsp" />
	</header>
	<section style="margin-top: 30px;">
		<div class="container">
			<div class="row justify-content-center">
				<div class="btn-group">
					<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-expanded="false"> 노래 관리 </button>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="${root}admin/search/songName">노래 이름 검색</a>
						<div class="dropdown-divider"></div>
						<form action="${root}admin/search/songInfo" method="POST">
							<input type="text" class="form-control" name="song_id" placeholder="아이디 입력..." required="required"/>
							<button type="submit" class="btn btn-success" style="margin: 3px 0px; width: 100%;">노래 아이디 검색</button>
						</form>
						<a class="dropdown-item" href="${root}admin/search/emptyNation">국가 정보</a>
					</div>
				</div>
				<div class="btn-group">
					<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-expanded="false"> 아티스트 관리 </button>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="${root}admin/search/artistName">아티스트 이름 검색</a>
						<div class="dropdown-divider"></div>
						<form action="${root}admin/search/artistInfo" method="POST" class="text-center">
							<input type="text" class="form-control" name="artist_id" placeholder="아이디 입력..." required="required"/>
							<button type="submit" class="btn btn-success" style="margin: 3px 0px; width: 100%;">아티스트 아이디 검색</button>
						</form>
					</div>
				</div>
				<div class="btn-group">
					<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-expanded="false"> 앨범 관리 </button>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="${root}admin/search/albumName">앨범 이름 검색</a>
						<div class="dropdown-divider"></div>
						<form action="${root}admin/search/albumInfo" method="POST">
							<input type="text" class="form-control" name="album_id" placeholder="아이디 입력..." required="required"/>
							<button type="submit" class="btn btn-success" style="margin: 3px 0px; width: 100%;">앨범 아이디 검색</button>
						</form>
					</div>
				</div>
				<div class="btn-group">
					<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-expanded="false"> 유저 관리 </button>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="${root}admin/search/userName">유저 아이디 검색</a>
						<div class="dropdown-divider"></div>
						<form action="${root}admin/search/userInfo" method="POST">
							<input type="text" class="form-control" name="user_num" placeholder="아이디 입력..." required="required"/>
							<button type="submit" class="btn btn-success" style="margin: 3px 0px; width: 100%;">유저 번호 검색</button>
						</form>
					</div>
				</div>
				<button type="button" class="btn btn-success" 
				onclick="location.href='${root}admin/search/review_report'">신고 : 리뷰 관리</button>
			</div>
		</div>
	</section>
</body>
</html>
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
<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="styleSheet" href="${root}style/searchBar.css">
<link rel="styleSheet" href="${root}style/any_main.css">
</head>
<body>
	<header>
		<c:import url="/WEB-INF/views/admin/include/top.jsp" />
	</header>
	<section style="width: 70%;margin: 30px 15%">
		<form:form modelAttribute="modifySongDto"
			action="${root}admin/search/modifySong">
			<div class="text-center">
				<strong>변경 불가</strong>
			</div>
			<hr />
			<div class="form-row text-center justify-content-center">
				<div class="col-2">
					<form:label path="song_id">song_id</form:label>
					<form:input path="song_id" class="form-control" readonly="true" />
				</div>
				<div class="col-2">
					<form:label path="album_id">album_id</form:label>
					<form:input path="album_id" class="form-control" readonly="true" />
				</div>
			</div>
			<div class="text-center" style="margin-top: 30px;">변경 가능</div>
			<hr />
			<div class="form-group">
				<div class="form-row">
					<div class="col">
						<form:label path="song_name">노래 이름</form:label>
						<form:input path="song_name" class="form-control" />
						<form:errors path="song_name"></form:errors>
					</div>
					<div class="col-4">
						<form:label path="song_nation">국내? 해외?</form:label>
						<form:select path="song_nation" class="custom-select">
							<form:option value="-"> - </form:option>
							<form:option value="국내">국내</form:option>
							<form:option value="해외">해외</form:option>
						</form:select>
					</div>
				</div>
			</div>
			<div class="form-group">
				<form:label path="song_genre">장르</form:label>
				<form:input path="song_genre" class="form-control" />
				<form:errors path="song_genre"></form:errors>
			</div>
			<div class="form-group">
				<form:label path="release_date" >발매일</form:label>
				<form:input path="release_date" class="form-control" />
				<form:errors path="release_date"></form:errors>
			</div>
			<div class="form-group">
				<form:label path="lyrics" >가사 파일 이름</form:label>
				<form:input path="lyrics" class="form-control" />
				<form:errors path="lyrics"></form:errors>
			</div>
			<div class="form-group">
				<form:label path="song_thumbnail" >썸네일 파일 이름</form:label>
				<form:input path="song_thumbnail" class="form-control" />
				<form:errors path="song_thumbnail"></form:errors>
			</div>
			<div class="text-center">
				<form:button class="btn btn-outline-success">수정</form:button>
				<form:button type="button" class="btn btn-outline-warning" onclick="location.href='${root}admin/search/songInfo?song_id=${modifySongDto.song_id}'">변경 취소</form:button>
			</div>
		</form:form>
	</section>
</body>
</html>
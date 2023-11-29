<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<%
	if(session.getAttribute("chart") == null){
		response.sendRedirect("./");
	}
%>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Mua</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js"
	integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+"
	crossorigin="anonymous"></script>
<link rel="styleSheet" href="${root}/style/main.css">
</head>
<style>
.items-consertrec {
	position: relative;
	top: 700px;
	width: 100%;
	height: 800px;
}

.consertrec {
	height: 500px;
	width: 90%;
	margin: 5%;
}

#carouselExampleIndicators {
	height: 100%;
	width: 100%;
}

.carousel-inner {
	height: 100%;
	width: 100%;
}

.consert-img {
	position: absolute;
	width: 1207.8px;
	height: 500px;
}

.d-block w-100 {
	width: 300px;
	height: 300px;
	object-fit: cover;
}

.items-songrec-songchart {
	position: relative;
	top: 500px;
	width: 100%;
	height: 500px;
}

.songrec {
	float: left;
	height: 500px;
	width: 40%;
	margin: 5%;
}

.song-img-top {
	width: 300px;
	height: 300px;
	object-fit: cover;
}

.songchart {
	float: left;
	height: 420px;
	width: 40%;
	margin: 5%;
	overflow-x: hidden;
	   overflow-y: auto;
}

.flex_items {
	height: 80px;
	display: flex;
	align-items: center;
}

.a_black {
	text-decoration: none;
	color: black;
	width: 100%;
}

.list_group {
	display: flex;
	align-items: center;
	justify-content: center;
	text-align: center;
	padding: 0;
}
</style>
<body>
	<!-- 상단 메뉴 부분 -->
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	<!-- 중단 부분 -->
	<section style="position: relative; height: 2000px; width: 100%;">
	<!-- 나중에 수정필요 insert -> search -->
		<form action="${root }insert/main" method="get">
			<div class="form-row align-items-center search_form">
				<div class="col-auto my-1">
					<select class="custom-select mr-sm-2" id="inlineFormCustomSelect"
						name="search_where">
						<option value="frm_searchSong" selected>노래</option>
						<option value="frm_searchArtist">가수</option>
						<option value="frm_searchAlbum">앨범</option>
					</select>
				</div>
				<input type="text" class="form-control search_bar"
					name="search_value">
				<div class="col-auto my-1">
					<button type="submit" class="btn btn-primary">&nbsp;검색&nbsp;</button>
				</div>
			</div>
		</form>
		<div class="items-consertrec">
			<div class="consertrec">
				<h1 style="text-align: center;" class="card-title">맞춤 콘서트 추천</h1>
				<div id="carouselExampleIndicators" class="carousel slide"
					data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#carouselExampleIndicators" data-slide-to="0"
							class="active"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
					</ol>
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img src="${root }images/consert1.jpg" class="img-fluid"
								alt="..."
								style="display: block; width: 60%; height: auto; margin-left: auto; margin-right: auto;">
						</div>
						<div class="carousel-item">
							<img src="${root }images/consert2.jpg" class="img-fluid"
								alt="..."
								style="display: block; width: 60%; height: auto; margin-left: auto; margin-right: auto;">
						</div>
						<div class="carousel-item">
							<img src="${root }images/consert3.jpg" class="img-fluid"
								alt="..."
								style="display: block; width: 60%; height: auto; margin-left: auto; margin-right: auto;">
						</div>
					</div>
					<button class="carousel-control-prev" type="button"
						data-target="#carouselExampleIndicators" data-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-target="#carouselExampleIndicators" data-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</button>
				</div>

				<div class="card">
					<div class="card-body">
						<a href="#" class="btn btn-primary">추천 페이지 이동</a>
					</div>
				</div>
			</div>
		</div>

		<div class="items-songrec-songchart">
			<div class="songrec">
				<div class="card">
					<img src="${root }images/song.jpg" class="song-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">노래 추천</h5>
						<a href="#" class="btn btn-primary">추천 페이지 이동</a>
					</div>
				</div>
			</div>
			<div class="songchart">
				<div class="list-group">
					<div class="list-group-item list-group-item-action active"
						aria-current="true" disabled>
						<div style="display: flex; justify-content: space-between;">
							<div>TOP10</div>
							<div>
								<a href="${root }rank/top100?type=song"
									style="text-align: right; color: black; text-decoration: none;">더
									보기</a>
							</div>
						</div>
					</div>
					<c:set var="rank" value="1" />
					<c:forEach var="item" items="${sessionScope.chart}" end="9">
						<ul class="list-group list-group-horizontal list_group">
							<li class="list-group-item flex_items"
								style="flex: 1; justify-content: center; border-right: none;">${rank}</li>
							<c:set var="rank" value="${rank + 1}" />
							<li class="list-group-item flex_items"
								style="flex: 3; border-left: none; border-right: none;"><a
								class="a_black"
								href="${root }search/song_info?song_id=${item.song_id}">${item.song_name}</a>
							</li>
							<li class="list-group-item flex_items"
								style="flex: 4; text-align: center; border-left: none; border-right: none;">
								<c:forEach var="i" begin="0"
									end="${fn:length(item.artist_name)}">
									<a class="a_black"
										href="${root }search/artist_info?artist_id=${item.artist_id[i]}">${item.artist_name[i]}</a>
									<br />
								</c:forEach>
							</li>
							<li class="list-group-item flex_items"
								style="flex: 3; border-left: none;"><a class="a_black"
								href="${root }search/album_info?album_id=${item.album_id}">${item.album_name}</a>
							</li>
						</ul>
					</c:forEach>
				</div>
			</div>
		</div>

	</section>

	<!-- 하단 부분 -->
	<c:import url="/WEB-INF/views/include/bottom.jsp" />
</body>
</html>
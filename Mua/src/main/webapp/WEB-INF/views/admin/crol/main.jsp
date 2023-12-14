<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Mua</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
<link rel="styleSheet" href="${root}/style/main.css">
<link rel="styleSheet" href="${root}/style/searchBar.css">
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

</style>
<body>
	<!-- 상단 메뉴 부분 -->
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	<!-- 중단 부분 -->
	<section style="position: relative; height: 2000px; width: 100%;">
	<!-- 나중에 수정필요 insert -> search -->
		<form action="${root }admin/crol/search/main" method="get">
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
		<div style="position: absolute; top: 50%;">
			<button onclick="location.href='${root}admin/crol/test'">테스트</button>
		</div>
	</section>

	<!-- 하단 부분 -->
	<c:import url="/WEB-INF/views/include/bottom.jsp" />
</body>
</html>
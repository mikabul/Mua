<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mua</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<link rel="stylesheet" href="${root}style/suggest.css">
<link rel="stylesheet" href="${root}style/container.css">


<script>
	
</script>
</head>
<body>


	<c:import url="/WEB-INF/views/include/top_menu.jsp" />

	<div id="cont" class="confix">
		<div id="cont_section" class="sect">
			<h2>당신의 취향을 분석합니다.</h2>
			<hr>
			<div class="wrap">
				<div class="card_container">
					<div class="top">최근 많이 본 차트</div>
					<div class="bottom">
						<div class="name">
							<%-- 방문 횟수를 표시할 부분 --%>
							<%
							Integer top100Count = (Integer) session.getAttribute("/Mua/chart/top100");
							Integer newchartCount = (Integer) session.getAttribute("/Mua/chart/newchart");
							Integer genreCount = (Integer) session.getAttribute("/Mua/chart/genre");

							String mostVisitedPage = "";
							int maxCount = 0;

							if (top100Count != null && newchartCount != null && genreCount != null) {
								maxCount = Math.max(Math.max(top100Count, newchartCount), genreCount);
							} else if (top100Count != null && newchartCount != null) {
								maxCount = Math.max(top100Count, newchartCount);
							} else if (top100Count != null && genreCount != null) {
								maxCount = Math.max(top100Count, genreCount);
							} else if (newchartCount != null && genreCount != null) {
								maxCount = Math.max(newchartCount, genreCount);
							} else if (top100Count != null) {
								maxCount = top100Count;
							} else if (newchartCount != null) {
								maxCount = newchartCount;
							} else if (genreCount != null) {
								maxCount = genreCount;
							}

							if (maxCount == top100Count) {
								mostVisitedPage = "Top 100 페이지";
							} else if (maxCount == newchartCount) {
								mostVisitedPage = "최신곡 페이지";
							} else if (maxCount == genreCount) {
								mostVisitedPage = "장르 페이지";
							}

							out.print(mostVisitedPage);
							%>
						</div>
						<div class="count">
							(방문 횟수:
							<%=maxCount%>)
						</div>
					</div>
				</div>

				<div class="card_container">
					<div class="top">최근 좋아한 가수</div>
					<div class="bottom">
						<div class="name">${recentArtistName}</div>
						<div class="date">2022.6.18</div>
					</div>
				</div>

				<div class="card_container">
					<div class="top">내가 가장 좋아하는 장르</div>
					<div class="bottom">
						<div class="name">${genreName}</div>
						<div class="date">좋아요 7곡</div>
					</div>
				</div>
				<div class="card_container">
					<div class="top">내가 즐겨듣는 노래의 국적</div>
					<div class="bottom">
						<div class="name">${nationName}</div>
						<div class="date">좋아요 8곡</div>
					</div>
				</div>
			</div>
			<div class="button-wrap">
				<a href="${root}search/song_info?song_id=${suggestSongId}" class="button">당신을 위한 노래입니다!</a>
			</div>

			<h2>추천이 마음에 안드셨나요?</h2>
			<hr>
			<div class="wrap">
				<div class="card_container" onclick="suggestDifferent('artist')">
					<div class="top">다른 가수의 노래를 추천해주세요</div>
					<div class="bottom">
						<div class="name"></div>
						<div class="date"></div>
					</div>
				</div>
				<div class="card_container" onclick="suggestDifferent('genre')">
					<div class="top">다른 장르의 노래를 추천해주세요</div>
					<div class="bottom">
						<div class="name"></div>
						<div class="date"></div>
					</div>
				</div>
				<div class="card_container" onclick="suggestDifferent('nation')">
					<div class="top">다른 나라의 노래를 추천해주세요</div>
					<div class="bottom">
						<div class="name"></div>
						<div class="date"></div>
					</div>
				</div>
					
		</div>
	</div>

	<script>
		function suggestDifferent(condition) {
			// condition 값에 따라 다른 가수, 다른 장르, 다른 국적을 요청하는 컨트롤러에 값을 전달합니다.
			$.ajax({
				type : 'GET',
				url : '/suggest/suggestDifferent',
				data : {
					condition : condition
				},
				success : function(data) {
					// 컨트롤러로부터 받은 응답을 이용하여 페이지에 새로운 추천 노래 정보를 업데이트합니다.
					// 예를 들어, 받은 데이터를 이용하여 새로운 가수, 장르, 국적에 대한 노래 정보를 업데이트합니다.
				},
				error : function(error) {
					console.log('Error:', error);
				}
			});
		}
	</script>

	<!-- bottom -->
	<c:import url="/WEB-INF/views/include/bottom.jsp" />
</body>
</html>
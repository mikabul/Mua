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
<link rel="styleSheet" href="${root }style/main.css">
</head>
<body>
	<!-- 상단 메뉴 부분 -->
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	<!-- 중단 부분 -->
	<section style="min-height: 700px; width: 100%;">
	<!-- 검색바 -->
		<c:import url="/WEB-INF/views/include/searchBar.jsp" />
		
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
								<a href="${root }chart/top100?type=song" style="text-align: right; color: black; text-decoration: none;">
								더보기
								</a>
							</div>
						</div>
					</div>
					<c:set var="rank" value="1" />
					<c:forEach var="item" items="${chart}" end="9">
						<ul class="list-group list-group-horizontal list_group" id="item${rank}">
							<li class="list-group-item flex_items flex-1 ellipsis">
								${rank}
							</li>
							<c:set var="rank" value="${rank + 1}" />
							<li class="list-group-item flex_items flex-2 ellipsis">
							<a class="a_black" href="${root }search/song_info?song_id=${item.song_id}" title="${item.song_name}">
								${item.song_name}
							</a>
							</li>
							<li class="list-group-item flex_items flex-3 ellipsis" >
								<c:choose>
									<c:when test="${item.artist_name[0] == 'Various Artists'}">
										<span>Various Artists</span>
									</c:when>
									<c:otherwise>
										<a class="a_black" href="${root}search/artist_info?artist_id=${item.artist_id[0]}" title="${item.artist_name[0]}">
											${item.artist_name[0]}
										</a>
										<c:if test="${fn:length(item.artist_name) > 1}">
											<c:forEach var="i" begin="1" end="${fn:length(item.artist_name) - 1}">
												,
												<a class="a_black" href="${root}search/artist_info?artist_id=${item.artist_id[i]}" title="${item.artist_name[i]}">
													${item.artist_name[i]}
												</a>
											</c:forEach>
										</c:if>
									</c:otherwise>
								</c:choose>
							</li>
							<li class="list-group-item flex_items flex-4 ellipsis">
								<a class="a_black" href="${root }search/album_info?album_id=${item.album_id}" title="${item.album_name}">
									${item.album_name}
								</a>
							</li>
						</ul>
					</c:forEach>
				</div>
			</div>
		</div>

	</section>

	<!-- 하단 부분 -->
	<footer>
	<c:import url="/WEB-INF/views/include/bottom.jsp" />
	</footer>
</body>
</html>
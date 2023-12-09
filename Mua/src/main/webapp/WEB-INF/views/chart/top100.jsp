<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mua</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<link rel="stylesheet" href="${root}style/top100.css">
<script>
</script>
</head>
<body>

	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	
	<section style="margin-top: 130px;">
	<table style="margin-left: auto; margin-right: auto;">
		<colgroup>
			<!-- 순위 -->
			<col style="width: 62px"/>
			<!-- 노래 썸네일 -->
			<col style="width: 60px"/>
			<!-- 빈공간 -->
			<col style="width: 20px"/>
			<!-- 곡이름 -->
			<col style="width: 280px"/>
			<!-- 아티스트 썸네일 -->
			<col style="width: 60px"/>
			<!-- 빈공간 -->
			<col style="width: 20px"/>
			<!-- 아티스트 -->
			<col style="width: 280px"/>
			<!-- 앨범 -->
			<col style="width: 210px"/>
			<!-- 좋아요 -->
			<col style="width: 60px"/>
		</colgroup>
		<thead>
			<tr class="line" >
				<td class="top_bottom" scope="col"><div style="text-align: center">순위</div></td>
				<td class="top_bottom" scope="col"><div class="none">노래 썸네일</div></td>
				<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
				<td class="top_bottom" scope="col"><div>곡정보</div></td>
				<td class="top_bottom" scope="col"><div class="none">아티스트 썸네일</div></td>
				<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
				<td class="top_bottom" scope="col"><div>아티스트</div></td>
				<td class="top_bottom" scope="col"><div>앨범</div></td>
				<td class="top_bottom" scope="col"><div>좋아요</div></td>
			</tr>
		</thead>
		<tbody>
			<c:set var="rank" value="1" />
			<c:forEach var="item" items="${chart}">
				<tr class="line">
					<!-- 랭크 -->
					<td class="top_bottom">
						<div class="rank">
							${rank}
						</div>
					</td>
					<c:set var="rank" value="${rank+1}" />
					<!-- 노래 썸네일 -->
					<td class="top_bottom">
						<div>
							<a href="${root}search/song_info?song_id=${item.song_id}" title="${item.song_name} - 곡 정보">
								<img class="thum mouse_over_img" src="${root}images/thumbnail/song/${item.song_thumbnail}" />
							</a>
						</div>
					</td>
					<!-- 빈공간 -->
					<td class="top_bottom"></td>
					<!-- 노래 -->
					<td class="top_bottom">
						<div>
							<div class="ellipsis" style="max-width: 230px;">
								<span>
									<a href="${root}search/song_info?song_id=${item.song_id}" title="${item.song_name} - 곡 정보">${item.song_name}</a>
								</span>
							</div>
						</div>
					</td>
					<!-- 아티스트 썸네일 -->
					<td class="top_bottom">
						<!-- 캐러셀 -->
						<div id="carouselExampleSlidesOnly" class="carousel slide" data-ride="carousel">
								<div class="carousel-inner">
									<div class="carousel-item active">
										<a href="${root}search/artist_info?artist_id=${item.artist_id[0]}" title="${item.artist_name[0]} - 아티스트정보">
											<img src="${root}images/thumbnail/artist/${item.artist_thumbnail[0]}"
												class="thum" alt="${item.artist_name[0]}">
										</a>
									</div>
									<c:if test="${fn:length(item.artist_thumbnail) > 1}">
										<c:forEach var="i" begin="1" end="${fn:length(item.artist_thumbnail) - 1}">
											<div class="carousel-item">
												<a href="${root}search/artist_info?artist_id=${item.artist_id[i]}" title="${item.artist_name[i]} - 아티스트정보">
													<img src="${root}images/thumbnail/artist/${item.artist_thumbnail[i]}"
														class="thum" alt="${item.artist_name[i]}">
												</a>
											</div>
										</c:forEach>
									</c:if>
								</div>
							</div>
						</td>
					<!-- 빈공간 -->
					<td class="top_bottom"></td>
					<!-- 아티스트 -->
					<td class="top_bottom">
						<div class="ellipsis" style="max-width: 230px;">
							<a href="${root}search/artist_info?artist_id=${item.artist_id[0]}" title="${item.artist_name[0]} - 아티스트 정보">${item.artist_name[0]}</a>
							<c:if test="${fn:length(item.artist_name) > 1}">
								<c:forEach var="i" begin="1" end="${fn:length(item.artist_name)-1}">
									<span>, </span>
									<a href="${root}search/artist_info?artist_id=${item.artist_id[i]}" title="${item.artist_name[i]} - 아티스트 정보">${item.artist_name[i]}</a>
								</c:forEach>
							</c:if>
						</div>
					</td>
					<!-- 앨범 -->
					<td class="top_bottom">
						<div class="ellipsis" style="width: 170px;">
							<a href="${root}search/album_info?album_id=${item.album_id}" title="${item.album_name} - 앨범 정보">
								${item.album_name}
							</a>
						</div>
					</td>
					<!-- 좋아요 -->
					<td class="top_bottom">
						<div>${item.song_thumbup}</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</section>
	
	<!-- bottom -->
	<c:import url="/WEB-INF/views/include/bottom.jsp" />
</body>
</html>
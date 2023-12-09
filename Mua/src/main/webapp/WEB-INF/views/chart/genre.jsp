<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">
<title>Mua</title>
<link rel="stylesheet" href="${root}style/container.css">
<link rel="stylesheet" href="${root}style/top100.css">
</head>

<body>
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />

	<div id="cont" class="confix">
		<div id="cont_section" class="sect">
			<div class="page_header">
				<h2 class="title">장르별 음악</h2>
				<hr>
				<nav style="margin-bottom: 10px;">
					<div class="nav nav-tabs" id="nav-tab" role="tablist">
						<!-- ... 탭 내용 ... -->
						<button class="nav-link active" id="nav-korea-tab"
							data-toggle="tab" data-target="#nav-home" type="button"
							role="tab" aria-controls="nav-home" aria-selected="true">한국대중음악</button>
						<button class="nav-link" id="nav-pop-tab" data-toggle="tab"
							data-target="#nav-profile" type="button" role="tab"
							aria-controls="nav-profile" aria-selected="false">해외POP음악</button>
						<button class="nav-link" id="nav-other-tab" data-toggle="tab"
							data-target="#nav-contact" type="button" role="tab"
							aria-controls="nav-contact" aria-selected="false">기타인기장르</button>
					</div>
				</nav>
				<div class="tab-content" id="nav-tabContent">
					<!-- ... 탭 콘텐츠 내용 ... -->
					<div class="tab-pane fade show active" id="nav-home"
						role="tabpanel" aria-labelledby="nav-korea-tab">
						<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
							<li class="nav-item" role="presentation">
								<button class="nav-link active" id="pills-kor-ballad-tab"
									data-toggle="pill" data-target="#pills-kor-ballad"
									type="button" role="tab" aria-controls="pills-kor-ballad"
									aria-selected="true">발라드</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="pills-kor-dance-tab"
									data-toggle="pill" data-target="#pills-kor-dance" type="button"
									role="tab" aria-controls="pills-kor-dance"
									aria-selected="false">댄스</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="pills-kor-rap-tab"
									data-toggle="pill" data-target="#pills-kor-rap" type="button"
									role="tab" aria-controls="pills-kor-rap" aria-selected="false">랩/힙합</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="pills-kor-rnb-tab"
									data-toggle="pill" data-target="#pills-kor-rnb" type="button"
									role="tab" aria-controls="pills-kor-rnb" aria-selected="false">R&B</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="pills-kor-indi-tab"
									data-toggle="pill" data-target="#pills-kor-indi" type="button"
									role="tab" aria-controls="pills-kor-indi" aria-selected="false">인디</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="pills-kor-rock-tab"
									data-toggle="pill" data-target="#pills-kor-rock" type="button"
									role="tab" aria-controls="pills-kor-rock" aria-selected="false">록/메탈</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="pills-kor-trot-tab"
									data-toggle="pill" data-target="#pills-kor-trot" type="button"
									role="tab" aria-controls="pills-kor-trot" aria-selected="false">트로트</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="pills-kor-folk-tab"
									data-toggle="pill" data-target="#pills-kor-folk" type="button"
									role="tab" aria-controls="pills-kor-folk" aria-selected="false">포크/블루스</button>
							</li>
						</ul>
						<div class="tab-content" id="pills-tabContent">
							<div class="tab-pane fade show active" id="pills-kor-ballad" role="tabpanel"
								aria-labelledby="pills-kor-ballad-tab">발라드 차트
								<table style="margin-left: auto; margin-right: auto;">
									<colgroup>
										<!-- 순위 -->
										<col style="width: 62px" />
										<!-- 노래 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 곡이름 -->
										<col style="width: 280px" />
										<!-- 아티스트 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 아티스트 -->
										<col style="width: 280px" />
										<!-- 앨범 -->
										<col style="width: 210px" />
										<!-- 좋아요 -->
										<col style="width: 60px" />
									</colgroup>
									<thead>
										<tr class="line">
											<td class="top_bottom" scope="col"><div
													style="text-align: center">순위</div></td>
											<td class="top_bottom" scope="col"><div class="none">노래
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>곡정보</div></td>
											<td class="top_bottom" scope="col"><div class="none">아티스트
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>아티스트</div></td>
											<td class="top_bottom" scope="col"><div>앨범</div></td>
											<td class="top_bottom" scope="col"><div>좋아요</div></td>
										</tr>
									</thead>
									<tbody>
										<c:set var="rank" value="1" />
										<c:forEach var="item" items="${genreDataMap.kor_ballad}">
											<tr class="line">
												<!-- 랭크 -->
												<td class="top_bottom">
													<div class="rank">${rank}</div>
												</td>
												<c:set var="rank" value="${rank+1}" />
												<!-- 노래 썸네일 -->
												<td class="top_bottom">
													<div>
														<a href="${root}search/song_info?song_id=${item.song_id}"
															title="${item.song_name} - 곡 정보"> <img
															class="thum mouse_over_img"
															src="${root}images/thumbnail/song/${item.song_thumbnail}" />
														</a>
													</div>
												</td>
												<!-- 빈공간 -->
												<td class="top_bottom"></td>
												<!-- 노래 -->
												<td class="top_bottom">
													<div>
														<div class="ellipsis" style="max-width: 230px;">
															<span> <a
																href="${root}search/song_info?song_id=${item.song_id}"
																title="${item.song_name} - 곡 정보">${item.song_name}</a>
															</span>
														</div>
													</div>
												</td>
												<!-- 아티스트 썸네일 -->
												<td class="top_bottom">
													<!-- 캐러셀 -->
													<div id="carouselExampleSlidesOnly" class="carousel slide"
														data-ride="carousel">
														<div class="carousel-inner">
															<div class="carousel-item active">
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
																	title="${item.artist_name[0]} - 아티스트정보"> <img
																	src="${root}images/thumbnail/artist/${item.artist_thumbnail[0]}"
																	class="thum" alt="${item.artist_name[0]}">
																</a>
															</div>
															<c:if test="${fn:length(item.artist_thumbnail) > 1}">
																<c:forEach var="i" begin="1"
																	end="${fn:length(item.artist_thumbnail) - 1}">
																	<div class="carousel-item">
																		<a
																			href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																			title="${item.artist_name[i]} - 아티스트정보"> <img
																			src="${root}images/thumbnail/artist/${item.artist_thumbnail[i]}"
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
														<a
															href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
															title="${item.artist_name[0]} - 아티스트 정보">${item.artist_name[0]}</a>
														<c:if test="${fn:length(item.artist_name) > 1}">
															<c:forEach var="i" begin="1"
																end="${fn:length(item.artist_name)-1}">
																<span>, </span>
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																	title="${item.artist_name[i]} - 아티스트 정보">${item.artist_name[i]}</a>
															</c:forEach>
														</c:if>
													</div>
												</td>
												<!-- 앨범 -->
												<td class="top_bottom">
													<div class="ellipsis" style="width: 170px;">
														<a
															href="${root}search/album_info?album_id=${item.album_id}"
															title="${item.album_name} - 앨범 정보">
															${item.album_name} </a>
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
							</div>
							<div class="tab-pane fade" id="pills-kor-dance" role="tabpanel"
								aria-labelledby="pills-kor-dance-tab">댄스 차트
								<table style="margin-left: auto; margin-right: auto;">
									<colgroup>
										<!-- 순위 -->
										<col style="width: 62px" />
										<!-- 노래 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 곡이름 -->
										<col style="width: 280px" />
										<!-- 아티스트 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 아티스트 -->
										<col style="width: 280px" />
										<!-- 앨범 -->
										<col style="width: 210px" />
										<!-- 좋아요 -->
										<col style="width: 60px" />
									</colgroup>
									<thead>
										<tr class="line">
											<td class="top_bottom" scope="col"><div
													style="text-align: center">순위</div></td>
											<td class="top_bottom" scope="col"><div class="none">노래
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>곡정보</div></td>
											<td class="top_bottom" scope="col"><div class="none">아티스트
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>아티스트</div></td>
											<td class="top_bottom" scope="col"><div>앨범</div></td>
											<td class="top_bottom" scope="col"><div>좋아요</div></td>
										</tr>
									</thead>
									<tbody>
										<c:set var="rank" value="1" />
										<c:forEach var="item" items="${genreDataMap.kor_dance}">
											<tr class="line">
												<!-- 랭크 -->
												<td class="top_bottom">
													<div class="rank">${rank}</div>
												</td>
												<c:set var="rank" value="${rank+1}" />
												<!-- 노래 썸네일 -->
												<td class="top_bottom">
													<div>
														<a href="${root}search/song_info?song_id=${item.song_id}"
															title="${item.song_name} - 곡 정보"> <img
															class="thum mouse_over_img"
															src="${root}images/thumbnail/song/${item.song_thumbnail}" />
														</a>
													</div>
												</td>
												<!-- 빈공간 -->
												<td class="top_bottom"></td>
												<!-- 노래 -->
												<td class="top_bottom">
													<div>
														<div class="ellipsis" style="max-width: 230px;">
															<span> <a
																href="${root}search/song_info?song_id=${item.song_id}"
																title="${item.song_name} - 곡 정보">${item.song_name}</a>
															</span>
														</div>
													</div>
												</td>
												<!-- 아티스트 썸네일 -->
												<td class="top_bottom">
													<!-- 캐러셀 -->
													<div id="carouselExampleSlidesOnly" class="carousel slide"
														data-ride="carousel">
														<div class="carousel-inner">
															<div class="carousel-item active">
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
																	title="${item.artist_name[0]} - 아티스트정보"> <img
																	src="${root}images/thumbnail/artist/${item.artist_thumbnail[0]}"
																	class="thum" alt="${item.artist_name[0]}">
																</a>
															</div>
															<c:if test="${fn:length(item.artist_thumbnail) > 1}">
																<c:forEach var="i" begin="1"
																	end="${fn:length(item.artist_thumbnail) - 1}">
																	<div class="carousel-item">
																		<a
																			href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																			title="${item.artist_name[i]} - 아티스트정보"> <img
																			src="${root}images/thumbnail/artist/${item.artist_thumbnail[i]}"
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
														<a
															href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
															title="${item.artist_name[0]} - 아티스트 정보">${item.artist_name[0]}</a>
														<c:if test="${fn:length(item.artist_name) > 1}">
															<c:forEach var="i" begin="1"
																end="${fn:length(item.artist_name)-1}">
																<span>, </span>
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																	title="${item.artist_name[i]} - 아티스트 정보">${item.artist_name[i]}</a>
															</c:forEach>
														</c:if>
													</div>
												</td>
												<!-- 앨범 -->
												<td class="top_bottom">
													<div class="ellipsis" style="width: 170px;">
														<a
															href="${root}search/album_info?album_id=${item.album_id}"
															title="${item.album_name} - 앨범 정보">
															${item.album_name} </a>
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
							</div>
							<div class="tab-pane fade" id="pills-kor-rap" role="tabpanel"
								aria-labelledby="pills-kor-rap-tab">랩/힙합 차트
								<table style="margin-left: auto; margin-right: auto;">
									<colgroup>
										<!-- 순위 -->
										<col style="width: 62px" />
										<!-- 노래 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 곡이름 -->
										<col style="width: 280px" />
										<!-- 아티스트 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 아티스트 -->
										<col style="width: 280px" />
										<!-- 앨범 -->
										<col style="width: 210px" />
										<!-- 좋아요 -->
										<col style="width: 60px" />
									</colgroup>
									<thead>
										<tr class="line">
											<td class="top_bottom" scope="col"><div
													style="text-align: center">순위</div></td>
											<td class="top_bottom" scope="col"><div class="none">노래
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>곡정보</div></td>
											<td class="top_bottom" scope="col"><div class="none">아티스트
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>아티스트</div></td>
											<td class="top_bottom" scope="col"><div>앨범</div></td>
											<td class="top_bottom" scope="col"><div>좋아요</div></td>
										</tr>
									</thead>
									<tbody>
										<c:set var="rank" value="1" />
										<c:forEach var="item" items="${genreDataMap.kor_rap}">
											<tr class="line">
												<!-- 랭크 -->
												<td class="top_bottom">
													<div class="rank">${rank}</div>
												</td>
												<c:set var="rank" value="${rank+1}" />
												<!-- 노래 썸네일 -->
												<td class="top_bottom">
													<div>
														<a href="${root}search/song_info?song_id=${item.song_id}"
															title="${item.song_name} - 곡 정보"> <img
															class="thum mouse_over_img"
															src="${root}images/thumbnail/song/${item.song_thumbnail}" />
														</a>
													</div>
												</td>
												<!-- 빈공간 -->
												<td class="top_bottom"></td>
												<!-- 노래 -->
												<td class="top_bottom">
													<div>
														<div class="ellipsis" style="max-width: 230px;">
															<span> <a
																href="${root}search/song_info?song_id=${item.song_id}"
																title="${item.song_name} - 곡 정보">${item.song_name}</a>
															</span>
														</div>
													</div>
												</td>
												<!-- 아티스트 썸네일 -->
												<td class="top_bottom">
													<!-- 캐러셀 -->
													<div id="carouselExampleSlidesOnly" class="carousel slide"
														data-ride="carousel">
														<div class="carousel-inner">
															<div class="carousel-item active">
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
																	title="${item.artist_name[0]} - 아티스트정보"> <img
																	src="${root}images/thumbnail/artist/${item.artist_thumbnail[0]}"
																	class="thum" alt="${item.artist_name[0]}">
																</a>
															</div>
															<c:if test="${fn:length(item.artist_thumbnail) > 1}">
																<c:forEach var="i" begin="1"
																	end="${fn:length(item.artist_thumbnail) - 1}">
																	<div class="carousel-item">
																		<a
																			href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																			title="${item.artist_name[i]} - 아티스트정보"> <img
																			src="${root}images/thumbnail/artist/${item.artist_thumbnail[i]}"
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
														<a
															href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
															title="${item.artist_name[0]} - 아티스트 정보">${item.artist_name[0]}</a>
														<c:if test="${fn:length(item.artist_name) > 1}">
															<c:forEach var="i" begin="1"
																end="${fn:length(item.artist_name)-1}">
																<span>, </span>
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																	title="${item.artist_name[i]} - 아티스트 정보">${item.artist_name[i]}</a>
															</c:forEach>
														</c:if>
													</div>
												</td>
												<!-- 앨범 -->
												<td class="top_bottom">
													<div class="ellipsis" style="width: 170px;">
														<a
															href="${root}search/album_info?album_id=${item.album_id}"
															title="${item.album_name} - 앨범 정보">
															${item.album_name} </a>
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
							</div>
							<div class="tab-pane fade" id="pills-kor-rnb" role="tabpanel"
								aria-labelledby="pills-kor-rnb-tab">R&B 차트
								<table style="margin-left: auto; margin-right: auto;">
									<colgroup>
										<!-- 순위 -->
										<col style="width: 62px" />
										<!-- 노래 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 곡이름 -->
										<col style="width: 280px" />
										<!-- 아티스트 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 아티스트 -->
										<col style="width: 280px" />
										<!-- 앨범 -->
										<col style="width: 210px" />
										<!-- 좋아요 -->
										<col style="width: 60px" />
									</colgroup>
									<thead>
										<tr class="line">
											<td class="top_bottom" scope="col"><div
													style="text-align: center">순위</div></td>
											<td class="top_bottom" scope="col"><div class="none">노래
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>곡정보</div></td>
											<td class="top_bottom" scope="col"><div class="none">아티스트
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>아티스트</div></td>
											<td class="top_bottom" scope="col"><div>앨범</div></td>
											<td class="top_bottom" scope="col"><div>좋아요</div></td>
										</tr>
									</thead>
									<tbody>
										<c:set var="rank" value="1" />
										<c:forEach var="item" items="${genreDataMap.kor_rnb}">
											<tr class="line">
												<!-- 랭크 -->
												<td class="top_bottom">
													<div class="rank">${rank}</div>
												</td>
												<c:set var="rank" value="${rank+1}" />
												<!-- 노래 썸네일 -->
												<td class="top_bottom">
													<div>
														<a href="${root}search/song_info?song_id=${item.song_id}"
															title="${item.song_name} - 곡 정보"> <img
															class="thum mouse_over_img"
															src="${root}images/thumbnail/song/${item.song_thumbnail}" />
														</a>
													</div>
												</td>
												<!-- 빈공간 -->
												<td class="top_bottom"></td>
												<!-- 노래 -->
												<td class="top_bottom">
													<div>
														<div class="ellipsis" style="max-width: 230px;">
															<span> <a
																href="${root}search/song_info?song_id=${item.song_id}"
																title="${item.song_name} - 곡 정보">${item.song_name}</a>
															</span>
														</div>
													</div>
												</td>
												<!-- 아티스트 썸네일 -->
												<td class="top_bottom">
													<!-- 캐러셀 -->
													<div id="carouselExampleSlidesOnly" class="carousel slide"
														data-ride="carousel">
														<div class="carousel-inner">
															<div class="carousel-item active">
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
																	title="${item.artist_name[0]} - 아티스트정보"> <img
																	src="${root}images/thumbnail/artist/${item.artist_thumbnail[0]}"
																	class="thum" alt="${item.artist_name[0]}">
																</a>
															</div>
															<c:if test="${fn:length(item.artist_thumbnail) > 1}">
																<c:forEach var="i" begin="1"
																	end="${fn:length(item.artist_thumbnail) - 1}">
																	<div class="carousel-item">
																		<a
																			href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																			title="${item.artist_name[i]} - 아티스트정보"> <img
																			src="${root}images/thumbnail/artist/${item.artist_thumbnail[i]}"
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
														<a
															href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
															title="${item.artist_name[0]} - 아티스트 정보">${item.artist_name[0]}</a>
														<c:if test="${fn:length(item.artist_name) > 1}">
															<c:forEach var="i" begin="1"
																end="${fn:length(item.artist_name)-1}">
																<span>, </span>
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																	title="${item.artist_name[i]} - 아티스트 정보">${item.artist_name[i]}</a>
															</c:forEach>
														</c:if>
													</div>
												</td>
												<!-- 앨범 -->
												<td class="top_bottom">
													<div class="ellipsis" style="width: 170px;">
														<a
															href="${root}search/album_info?album_id=${item.album_id}"
															title="${item.album_name} - 앨범 정보">
															${item.album_name} </a>
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
							</div>
							<div class="tab-pane fade" id="pills-kor-indi" role="tabpanel"
								aria-labelledby="pills-kor-indi-tab">인디 차트
								<table style="margin-left: auto; margin-right: auto;">
									<colgroup>
										<!-- 순위 -->
										<col style="width: 62px" />
										<!-- 노래 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 곡이름 -->
										<col style="width: 280px" />
										<!-- 아티스트 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 아티스트 -->
										<col style="width: 280px" />
										<!-- 앨범 -->
										<col style="width: 210px" />
										<!-- 좋아요 -->
										<col style="width: 60px" />
									</colgroup>
									<thead>
										<tr class="line">
											<td class="top_bottom" scope="col"><div
													style="text-align: center">순위</div></td>
											<td class="top_bottom" scope="col"><div class="none">노래
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>곡정보</div></td>
											<td class="top_bottom" scope="col"><div class="none">아티스트
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>아티스트</div></td>
											<td class="top_bottom" scope="col"><div>앨범</div></td>
											<td class="top_bottom" scope="col"><div>좋아요</div></td>
										</tr>
									</thead>
									<tbody>
										<c:set var="rank" value="1" />
										<c:forEach var="item" items="${genreDataMap.kor_indi}">
											<tr class="line">
												<!-- 랭크 -->
												<td class="top_bottom">
													<div class="rank">${rank}</div>
												</td>
												<c:set var="rank" value="${rank+1}" />
												<!-- 노래 썸네일 -->
												<td class="top_bottom">
													<div>
														<a href="${root}search/song_info?song_id=${item.song_id}"
															title="${item.song_name} - 곡 정보"> <img
															class="thum mouse_over_img"
															src="${root}images/thumbnail/song/${item.song_thumbnail}" />
														</a>
													</div>
												</td>
												<!-- 빈공간 -->
												<td class="top_bottom"></td>
												<!-- 노래 -->
												<td class="top_bottom">
													<div>
														<div class="ellipsis" style="max-width: 230px;">
															<span> <a
																href="${root}search/song_info?song_id=${item.song_id}"
																title="${item.song_name} - 곡 정보">${item.song_name}</a>
															</span>
														</div>
													</div>
												</td>
												<!-- 아티스트 썸네일 -->
												<td class="top_bottom">
													<!-- 캐러셀 -->
													<div id="carouselExampleSlidesOnly" class="carousel slide"
														data-ride="carousel">
														<div class="carousel-inner">
															<div class="carousel-item active">
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
																	title="${item.artist_name[0]} - 아티스트정보"> <img
																	src="${root}images/thumbnail/artist/${item.artist_thumbnail[0]}"
																	class="thum" alt="${item.artist_name[0]}">
																</a>
															</div>
															<c:if test="${fn:length(item.artist_thumbnail) > 1}">
																<c:forEach var="i" begin="1"
																	end="${fn:length(item.artist_thumbnail) - 1}">
																	<div class="carousel-item">
																		<a
																			href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																			title="${item.artist_name[i]} - 아티스트정보"> <img
																			src="${root}images/thumbnail/artist/${item.artist_thumbnail[i]}"
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
														<a
															href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
															title="${item.artist_name[0]} - 아티스트 정보">${item.artist_name[0]}</a>
														<c:if test="${fn:length(item.artist_name) > 1}">
															<c:forEach var="i" begin="1"
																end="${fn:length(item.artist_name)-1}">
																<span>, </span>
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																	title="${item.artist_name[i]} - 아티스트 정보">${item.artist_name[i]}</a>
															</c:forEach>
														</c:if>
													</div>
												</td>
												<!-- 앨범 -->
												<td class="top_bottom">
													<div class="ellipsis" style="width: 170px;">
														<a
															href="${root}search/album_info?album_id=${item.album_id}"
															title="${item.album_name} - 앨범 정보">
															${item.album_name} </a>
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
							</div>
							<div class="tab-pane fade" id="pills-kor-rock" role="tabpanel"
								aria-labelledby="pills-kor-rock-tab">록/메탈 차트
								<table style="margin-left: auto; margin-right: auto;">
									<colgroup>
										<!-- 순위 -->
										<col style="width: 62px" />
										<!-- 노래 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 곡이름 -->
										<col style="width: 280px" />
										<!-- 아티스트 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 아티스트 -->
										<col style="width: 280px" />
										<!-- 앨범 -->
										<col style="width: 210px" />
										<!-- 좋아요 -->
										<col style="width: 60px" />
									</colgroup>
									<thead>
										<tr class="line">
											<td class="top_bottom" scope="col"><div
													style="text-align: center">순위</div></td>
											<td class="top_bottom" scope="col"><div class="none">노래
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>곡정보</div></td>
											<td class="top_bottom" scope="col"><div class="none">아티스트
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>아티스트</div></td>
											<td class="top_bottom" scope="col"><div>앨범</div></td>
											<td class="top_bottom" scope="col"><div>좋아요</div></td>
										</tr>
									</thead>
									<tbody>
										<c:set var="rank" value="1" />
										<c:forEach var="item" items="${genreDataMap.kor_rock}">
											<tr class="line">
												<!-- 랭크 -->
												<td class="top_bottom">
													<div class="rank">${rank}</div>
												</td>
												<c:set var="rank" value="${rank+1}" />
												<!-- 노래 썸네일 -->
												<td class="top_bottom">
													<div>
														<a href="${root}search/song_info?song_id=${item.song_id}"
															title="${item.song_name} - 곡 정보"> <img
															class="thum mouse_over_img"
															src="${root}images/thumbnail/song/${item.song_thumbnail}" />
														</a>
													</div>
												</td>
												<!-- 빈공간 -->
												<td class="top_bottom"></td>
												<!-- 노래 -->
												<td class="top_bottom">
													<div>
														<div class="ellipsis" style="max-width: 230px;">
															<span> <a
																href="${root}search/song_info?song_id=${item.song_id}"
																title="${item.song_name} - 곡 정보">${item.song_name}</a>
															</span>
														</div>
													</div>
												</td>
												<!-- 아티스트 썸네일 -->
												<td class="top_bottom">
													<!-- 캐러셀 -->
													<div id="carouselExampleSlidesOnly" class="carousel slide"
														data-ride="carousel">
														<div class="carousel-inner">
															<div class="carousel-item active">
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
																	title="${item.artist_name[0]} - 아티스트정보"> <img
																	src="${root}images/thumbnail/artist/${item.artist_thumbnail[0]}"
																	class="thum" alt="${item.artist_name[0]}">
																</a>
															</div>
															<c:if test="${fn:length(item.artist_thumbnail) > 1}">
																<c:forEach var="i" begin="1"
																	end="${fn:length(item.artist_thumbnail) - 1}">
																	<div class="carousel-item">
																		<a
																			href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																			title="${item.artist_name[i]} - 아티스트정보"> <img
																			src="${root}images/thumbnail/artist/${item.artist_thumbnail[i]}"
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
														<a
															href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
															title="${item.artist_name[0]} - 아티스트 정보">${item.artist_name[0]}</a>
														<c:if test="${fn:length(item.artist_name) > 1}">
															<c:forEach var="i" begin="1"
																end="${fn:length(item.artist_name)-1}">
																<span>, </span>
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																	title="${item.artist_name[i]} - 아티스트 정보">${item.artist_name[i]}</a>
															</c:forEach>
														</c:if>
													</div>
												</td>
												<!-- 앨범 -->
												<td class="top_bottom">
													<div class="ellipsis" style="width: 170px;">
														<a
															href="${root}search/album_info?album_id=${item.album_id}"
															title="${item.album_name} - 앨범 정보">
															${item.album_name} </a>
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
							</div>
							<div class="tab-pane fade" id="pills-kor-trot" role="tabpanel"
								aria-labelledby="pills-kor-trot-tab">트로트 차트
								<table style="margin-left: auto; margin-right: auto;">
									<colgroup>
										<!-- 순위 -->
										<col style="width: 62px" />
										<!-- 노래 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 곡이름 -->
										<col style="width: 280px" />
										<!-- 아티스트 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 아티스트 -->
										<col style="width: 280px" />
										<!-- 앨범 -->
										<col style="width: 210px" />
										<!-- 좋아요 -->
										<col style="width: 60px" />
									</colgroup>
									<thead>
										<tr class="line">
											<td class="top_bottom" scope="col"><div
													style="text-align: center">순위</div></td>
											<td class="top_bottom" scope="col"><div class="none">노래
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>곡정보</div></td>
											<td class="top_bottom" scope="col"><div class="none">아티스트
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>아티스트</div></td>
											<td class="top_bottom" scope="col"><div>앨범</div></td>
											<td class="top_bottom" scope="col"><div>좋아요</div></td>
										</tr>
									</thead>
									<tbody>
										<c:set var="rank" value="1" />
										<c:forEach var="item" items="${genreDataMap.kor_trot}">
											<tr class="line">
												<!-- 랭크 -->
												<td class="top_bottom">
													<div class="rank">${rank}</div>
												</td>
												<c:set var="rank" value="${rank+1}" />
												<!-- 노래 썸네일 -->
												<td class="top_bottom">
													<div>
														<a href="${root}search/song_info?song_id=${item.song_id}"
															title="${item.song_name} - 곡 정보"> <img
															class="thum mouse_over_img"
															src="${root}images/thumbnail/song/${item.song_thumbnail}" />
														</a>
													</div>
												</td>
												<!-- 빈공간 -->
												<td class="top_bottom"></td>
												<!-- 노래 -->
												<td class="top_bottom">
													<div>
														<div class="ellipsis" style="max-width: 230px;">
															<span> <a
																href="${root}search/song_info?song_id=${item.song_id}"
																title="${item.song_name} - 곡 정보">${item.song_name}</a>
															</span>
														</div>
													</div>
												</td>
												<!-- 아티스트 썸네일 -->
												<td class="top_bottom">
													<!-- 캐러셀 -->
													<div id="carouselExampleSlidesOnly" class="carousel slide"
														data-ride="carousel">
														<div class="carousel-inner">
															<div class="carousel-item active">
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
																	title="${item.artist_name[0]} - 아티스트정보"> <img
																	src="${root}images/thumbnail/artist/${item.artist_thumbnail[0]}"
																	class="thum" alt="${item.artist_name[0]}">
																</a>
															</div>
															<c:if test="${fn:length(item.artist_thumbnail) > 1}">
																<c:forEach var="i" begin="1"
																	end="${fn:length(item.artist_thumbnail) - 1}">
																	<div class="carousel-item">
																		<a
																			href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																			title="${item.artist_name[i]} - 아티스트정보"> <img
																			src="${root}images/thumbnail/artist/${item.artist_thumbnail[i]}"
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
														<a
															href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
															title="${item.artist_name[0]} - 아티스트 정보">${item.artist_name[0]}</a>
														<c:if test="${fn:length(item.artist_name) > 1}">
															<c:forEach var="i" begin="1"
																end="${fn:length(item.artist_name)-1}">
																<span>, </span>
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																	title="${item.artist_name[i]} - 아티스트 정보">${item.artist_name[i]}</a>
															</c:forEach>
														</c:if>
													</div>
												</td>
												<!-- 앨범 -->
												<td class="top_bottom">
													<div class="ellipsis" style="width: 170px;">
														<a
															href="${root}search/album_info?album_id=${item.album_id}"
															title="${item.album_name} - 앨범 정보">
															${item.album_name} </a>
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
							</div>
							<div class="tab-pane fade" id="pills-kor-folk" role="tabpanel"
								aria-labelledby="pills-kor-folk-tab">포크 차트
								<table style="margin-left: auto; margin-right: auto;">
									<colgroup>
										<!-- 순위 -->
										<col style="width: 62px" />
										<!-- 노래 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 곡이름 -->
										<col style="width: 280px" />
										<!-- 아티스트 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 아티스트 -->
										<col style="width: 280px" />
										<!-- 앨범 -->
										<col style="width: 210px" />
										<!-- 좋아요 -->
										<col style="width: 60px" />
									</colgroup>
									<thead>
										<tr class="line">
											<td class="top_bottom" scope="col"><div
													style="text-align: center">순위</div></td>
											<td class="top_bottom" scope="col"><div class="none">노래
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>곡정보</div></td>
											<td class="top_bottom" scope="col"><div class="none">아티스트
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>아티스트</div></td>
											<td class="top_bottom" scope="col"><div>앨범</div></td>
											<td class="top_bottom" scope="col"><div>좋아요</div></td>
										</tr>
									</thead>
									<tbody>
										<c:set var="rank" value="1" />
										<c:forEach var="item" items="${genreDataMap.kor_folk}">
											<tr class="line">
												<!-- 랭크 -->
												<td class="top_bottom">
													<div class="rank">${rank}</div>
												</td>
												<c:set var="rank" value="${rank+1}" />
												<!-- 노래 썸네일 -->
												<td class="top_bottom">
													<div>
														<a href="${root}search/song_info?song_id=${item.song_id}"
															title="${item.song_name} - 곡 정보"> <img
															class="thum mouse_over_img"
															src="${root}images/thumbnail/song/${item.song_thumbnail}" />
														</a>
													</div>
												</td>
												<!-- 빈공간 -->
												<td class="top_bottom"></td>
												<!-- 노래 -->
												<td class="top_bottom">
													<div>
														<div class="ellipsis" style="max-width: 230px;">
															<span> <a
																href="${root}search/song_info?song_id=${item.song_id}"
																title="${item.song_name} - 곡 정보">${item.song_name}</a>
															</span>
														</div>
													</div>
												</td>
												<!-- 아티스트 썸네일 -->
												<td class="top_bottom">
													<!-- 캐러셀 -->
													<div id="carouselExampleSlidesOnly" class="carousel slide"
														data-ride="carousel">
														<div class="carousel-inner">
															<div class="carousel-item active">
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
																	title="${item.artist_name[0]} - 아티스트정보"> <img
																	src="${root}images/thumbnail/artist/${item.artist_thumbnail[0]}"
																	class="thum" alt="${item.artist_name[0]}">
																</a>
															</div>
															<c:if test="${fn:length(item.artist_thumbnail) > 1}">
																<c:forEach var="i" begin="1"
																	end="${fn:length(item.artist_thumbnail) - 1}">
																	<div class="carousel-item">
																		<a
																			href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																			title="${item.artist_name[i]} - 아티스트정보"> <img
																			src="${root}images/thumbnail/artist/${item.artist_thumbnail[i]}"
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
														<a
															href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
															title="${item.artist_name[0]} - 아티스트 정보">${item.artist_name[0]}</a>
														<c:if test="${fn:length(item.artist_name) > 1}">
															<c:forEach var="i" begin="1"
																end="${fn:length(item.artist_name)-1}">
																<span>, </span>
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																	title="${item.artist_name[i]} - 아티스트 정보">${item.artist_name[i]}</a>
															</c:forEach>
														</c:if>
													</div>
												</td>
												<!-- 앨범 -->
												<td class="top_bottom">
													<div class="ellipsis" style="width: 170px;">
														<a
															href="${root}search/album_info?album_id=${item.album_id}"
															title="${item.album_name} - 앨범 정보">
															${item.album_name} </a>
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
							</div>
						</div>
					</div>
					<div class="tab-pane fade" id="nav-profile" role="tabpanel"
						aria-labelledby="nav-pop-tab">
						<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
							<li class="nav-item" role="presentation">
								<button class="nav-link active" id="pills-pop-tab"
									data-toggle="pill" data-target="#pills-pop" type="button"
									role="tab" aria-controls="pills-pop" aria-selected="true">POP</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="pills-elec-tab" data-toggle="pill"
									data-target="#pills-elec" type="button" role="tab"
									aria-controls="pills-elec" aria-selected="false">일렉트로니카</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="pills-pop-rap-tab"
									data-toggle="pill" data-target="#pills-pop-rap" type="button"
									role="tab" aria-controls="pills-pop-rap" aria-selected="false">랩/힙합</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="pills-pop-rnb-tab"
									data-toggle="pill" data-target="#pills-pop-rnb" type="button"
									role="tab" aria-controls="pills-pop-rnb" aria-selected="false">R&B</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="pills-pop-rock-tab"
									data-toggle="pill" data-target="#pills-pop-rock" type="button"
									role="tab" aria-controls="pills-pop-rock" aria-selected="false">록/메탈</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="pills-pop-fork-tab"
									data-toggle="pill" data-target="#pills-pop-fork" type="button"
									role="tab" aria-controls="pills-pop-fork" aria-selected="false">포크/블루스</button>
							</li>
						</ul>
						<div class="tab-content" id="pills-tabContent">
							<div class="tab-pane fade show active" id="pills-pop"
								role="tabpanel" aria-labelledby="pills-pop-tab">POP 차트
								<table style="margin-left: auto; margin-right: auto;">
									<colgroup>
										<!-- 순위 -->
										<col style="width: 62px" />
										<!-- 노래 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 곡이름 -->
										<col style="width: 280px" />
										<!-- 아티스트 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 아티스트 -->
										<col style="width: 280px" />
										<!-- 앨범 -->
										<col style="width: 210px" />
										<!-- 좋아요 -->
										<col style="width: 60px" />
									</colgroup>
									<thead>
										<tr class="line">
											<td class="top_bottom" scope="col"><div
													style="text-align: center">순위</div></td>
											<td class="top_bottom" scope="col"><div class="none">노래
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>곡정보</div></td>
											<td class="top_bottom" scope="col"><div class="none">아티스트
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>아티스트</div></td>
											<td class="top_bottom" scope="col"><div>앨범</div></td>
											<td class="top_bottom" scope="col"><div>좋아요</div></td>
										</tr>
									</thead>
									<tbody>
										<c:set var="rank" value="1" />
										<c:forEach var="item" items="${genreDataMap.pop}">
											<tr class="line">
												<!-- 랭크 -->
												<td class="top_bottom">
													<div class="rank">${rank}</div>
												</td>
												<c:set var="rank" value="${rank+1}" />
												<!-- 노래 썸네일 -->
												<td class="top_bottom">
													<div>
														<a href="${root}search/song_info?song_id=${item.song_id}"
															title="${item.song_name} - 곡 정보"> <img
															class="thum mouse_over_img"
															src="${root}images/thumbnail/song/${item.song_thumbnail}" />
														</a>
													</div>
												</td>
												<!-- 빈공간 -->
												<td class="top_bottom"></td>
												<!-- 노래 -->
												<td class="top_bottom">
													<div>
														<div class="ellipsis" style="max-width: 230px;">
															<span> <a
																href="${root}search/song_info?song_id=${item.song_id}"
																title="${item.song_name} - 곡 정보">${item.song_name}</a>
															</span>
														</div>
													</div>
												</td>
												<!-- 아티스트 썸네일 -->
												<td class="top_bottom">
													<!-- 캐러셀 -->
													<div id="carouselExampleSlidesOnly" class="carousel slide"
														data-ride="carousel">
														<div class="carousel-inner">
															<div class="carousel-item active">
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
																	title="${item.artist_name[0]} - 아티스트정보"> <img
																	src="${root}images/thumbnail/artist/${item.artist_thumbnail[0]}"
																	class="thum" alt="${item.artist_name[0]}">
																</a>
															</div>
															<c:if test="${fn:length(item.artist_thumbnail) > 1}">
																<c:forEach var="i" begin="1"
																	end="${fn:length(item.artist_thumbnail) - 1}">
																	<div class="carousel-item">
																		<a
																			href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																			title="${item.artist_name[i]} - 아티스트정보"> <img
																			src="${root}images/thumbnail/artist/${item.artist_thumbnail[i]}"
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
														<a
															href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
															title="${item.artist_name[0]} - 아티스트 정보">${item.artist_name[0]}</a>
														<c:if test="${fn:length(item.artist_name) > 1}">
															<c:forEach var="i" begin="1"
																end="${fn:length(item.artist_name)-1}">
																<span>, </span>
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																	title="${item.artist_name[i]} - 아티스트 정보">${item.artist_name[i]}</a>
															</c:forEach>
														</c:if>
													</div>
												</td>
												<!-- 앨범 -->
												<td class="top_bottom">
													<div class="ellipsis" style="width: 170px;">
														<a
															href="${root}search/album_info?album_id=${item.album_id}"
															title="${item.album_name} - 앨범 정보">
															${item.album_name} </a>
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
							</div>
							<div class="tab-pane fade" id="pills-elec" role="tabpanel"
								aria-labelledby="pills-elec-tab">일렉트로니카 차트
								<table style="margin-left: auto; margin-right: auto;">
									<colgroup>
										<!-- 순위 -->
										<col style="width: 62px" />
										<!-- 노래 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 곡이름 -->
										<col style="width: 280px" />
										<!-- 아티스트 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 아티스트 -->
										<col style="width: 280px" />
										<!-- 앨범 -->
										<col style="width: 210px" />
										<!-- 좋아요 -->
										<col style="width: 60px" />
									</colgroup>
									<thead>
										<tr class="line">
											<td class="top_bottom" scope="col"><div
													style="text-align: center">순위</div></td>
											<td class="top_bottom" scope="col"><div class="none">노래
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>곡정보</div></td>
											<td class="top_bottom" scope="col"><div class="none">아티스트
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>아티스트</div></td>
											<td class="top_bottom" scope="col"><div>앨범</div></td>
											<td class="top_bottom" scope="col"><div>좋아요</div></td>
										</tr>
									</thead>
									<tbody>
										<c:set var="rank" value="1" />
										<c:forEach var="item" items="${genreDataMap.pop_elec}">
											<tr class="line">
												<!-- 랭크 -->
												<td class="top_bottom">
													<div class="rank">${rank}</div>
												</td>
												<c:set var="rank" value="${rank+1}" />
												<!-- 노래 썸네일 -->
												<td class="top_bottom">
													<div>
														<a href="${root}search/song_info?song_id=${item.song_id}"
															title="${item.song_name} - 곡 정보"> <img
															class="thum mouse_over_img"
															src="${root}images/thumbnail/song/${item.song_thumbnail}" />
														</a>
													</div>
												</td>
												<!-- 빈공간 -->
												<td class="top_bottom"></td>
												<!-- 노래 -->
												<td class="top_bottom">
													<div>
														<div class="ellipsis" style="max-width: 230px;">
															<span> <a
																href="${root}search/song_info?song_id=${item.song_id}"
																title="${item.song_name} - 곡 정보">${item.song_name}</a>
															</span>
														</div>
													</div>
												</td>
												<!-- 아티스트 썸네일 -->
												<td class="top_bottom">
													<!-- 캐러셀 -->
													<div id="carouselExampleSlidesOnly" class="carousel slide"
														data-ride="carousel">
														<div class="carousel-inner">
															<div class="carousel-item active">
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
																	title="${item.artist_name[0]} - 아티스트정보"> <img
																	src="${root}images/thumbnail/artist/${item.artist_thumbnail[0]}"
																	class="thum" alt="${item.artist_name[0]}">
																</a>
															</div>
															<c:if test="${fn:length(item.artist_thumbnail) > 1}">
																<c:forEach var="i" begin="1"
																	end="${fn:length(item.artist_thumbnail) - 1}">
																	<div class="carousel-item">
																		<a
																			href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																			title="${item.artist_name[i]} - 아티스트정보"> <img
																			src="${root}images/thumbnail/artist/${item.artist_thumbnail[i]}"
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
														<a
															href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
															title="${item.artist_name[0]} - 아티스트 정보">${item.artist_name[0]}</a>
														<c:if test="${fn:length(item.artist_name) > 1}">
															<c:forEach var="i" begin="1"
																end="${fn:length(item.artist_name)-1}">
																<span>, </span>
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																	title="${item.artist_name[i]} - 아티스트 정보">${item.artist_name[i]}</a>
															</c:forEach>
														</c:if>
													</div>
												</td>
												<!-- 앨범 -->
												<td class="top_bottom">
													<div class="ellipsis" style="width: 170px;">
														<a
															href="${root}search/album_info?album_id=${item.album_id}"
															title="${item.album_name} - 앨범 정보">
															${item.album_name} </a>
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
							</div>
							<div class="tab-pane fade" id="pills-pop-rap" role="tabpanel"
								aria-labelledby="pills-pop-rap-tab">랩/힙합 차트
								<table style="margin-left: auto; margin-right: auto;">
									<colgroup>
										<!-- 순위 -->
										<col style="width: 62px" />
										<!-- 노래 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 곡이름 -->
										<col style="width: 280px" />
										<!-- 아티스트 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 아티스트 -->
										<col style="width: 280px" />
										<!-- 앨범 -->
										<col style="width: 210px" />
										<!-- 좋아요 -->
										<col style="width: 60px" />
									</colgroup>
									<thead>
										<tr class="line">
											<td class="top_bottom" scope="col"><div
													style="text-align: center">순위</div></td>
											<td class="top_bottom" scope="col"><div class="none">노래
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>곡정보</div></td>
											<td class="top_bottom" scope="col"><div class="none">아티스트
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>아티스트</div></td>
											<td class="top_bottom" scope="col"><div>앨범</div></td>
											<td class="top_bottom" scope="col"><div>좋아요</div></td>
										</tr>
									</thead>
									<tbody>
										<c:set var="rank" value="1" />
										<c:forEach var="item" items="${genreDataMap.pop_rap}">
											<tr class="line">
												<!-- 랭크 -->
												<td class="top_bottom">
													<div class="rank">${rank}</div>
												</td>
												<c:set var="rank" value="${rank+1}" />
												<!-- 노래 썸네일 -->
												<td class="top_bottom">
													<div>
														<a href="${root}search/song_info?song_id=${item.song_id}"
															title="${item.song_name} - 곡 정보"> <img
															class="thum mouse_over_img"
															src="${root}images/thumbnail/song/${item.song_thumbnail}" />
														</a>
													</div>
												</td>
												<!-- 빈공간 -->
												<td class="top_bottom"></td>
												<!-- 노래 -->
												<td class="top_bottom">
													<div>
														<div class="ellipsis" style="max-width: 230px;">
															<span> <a
																href="${root}search/song_info?song_id=${item.song_id}"
																title="${item.song_name} - 곡 정보">${item.song_name}</a>
															</span>
														</div>
													</div>
												</td>
												<!-- 아티스트 썸네일 -->
												<td class="top_bottom">
													<!-- 캐러셀 -->
													<div id="carouselExampleSlidesOnly" class="carousel slide"
														data-ride="carousel">
														<div class="carousel-inner">
															<div class="carousel-item active">
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
																	title="${item.artist_name[0]} - 아티스트정보"> <img
																	src="${root}images/thumbnail/artist/${item.artist_thumbnail[0]}"
																	class="thum" alt="${item.artist_name[0]}">
																</a>
															</div>
															<c:if test="${fn:length(item.artist_thumbnail) > 1}">
																<c:forEach var="i" begin="1"
																	end="${fn:length(item.artist_thumbnail) - 1}">
																	<div class="carousel-item">
																		<a
																			href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																			title="${item.artist_name[i]} - 아티스트정보"> <img
																			src="${root}images/thumbnail/artist/${item.artist_thumbnail[i]}"
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
														<a
															href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
															title="${item.artist_name[0]} - 아티스트 정보">${item.artist_name[0]}</a>
														<c:if test="${fn:length(item.artist_name) > 1}">
															<c:forEach var="i" begin="1"
																end="${fn:length(item.artist_name)-1}">
																<span>, </span>
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																	title="${item.artist_name[i]} - 아티스트 정보">${item.artist_name[i]}</a>
															</c:forEach>
														</c:if>
													</div>
												</td>
												<!-- 앨범 -->
												<td class="top_bottom">
													<div class="ellipsis" style="width: 170px;">
														<a
															href="${root}search/album_info?album_id=${item.album_id}"
															title="${item.album_name} - 앨범 정보">
															${item.album_name} </a>
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
							</div>
							<div class="tab-pane fade" id="pills-pop-rnb" role="tabpanel"
								aria-labelledby="pills-pop-rnb-tab">R&B 차트
								<table style="margin-left: auto; margin-right: auto;">
									<colgroup>
										<!-- 순위 -->
										<col style="width: 62px" />
										<!-- 노래 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 곡이름 -->
										<col style="width: 280px" />
										<!-- 아티스트 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 아티스트 -->
										<col style="width: 280px" />
										<!-- 앨범 -->
										<col style="width: 210px" />
										<!-- 좋아요 -->
										<col style="width: 60px" />
									</colgroup>
									<thead>
										<tr class="line">
											<td class="top_bottom" scope="col"><div
													style="text-align: center">순위</div></td>
											<td class="top_bottom" scope="col"><div class="none">노래
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>곡정보</div></td>
											<td class="top_bottom" scope="col"><div class="none">아티스트
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>아티스트</div></td>
											<td class="top_bottom" scope="col"><div>앨범</div></td>
											<td class="top_bottom" scope="col"><div>좋아요</div></td>
										</tr>
									</thead>
									<tbody>
										<c:set var="rank" value="1" />
										<c:forEach var="item" items="${genreDataMap.pop_rnb}">
											<tr class="line">
												<!-- 랭크 -->
												<td class="top_bottom">
													<div class="rank">${rank}</div>
												</td>
												<c:set var="rank" value="${rank+1}" />
												<!-- 노래 썸네일 -->
												<td class="top_bottom">
													<div>
														<a href="${root}search/song_info?song_id=${item.song_id}"
															title="${item.song_name} - 곡 정보"> <img
															class="thum mouse_over_img"
															src="${root}images/thumbnail/song/${item.song_thumbnail}" />
														</a>
													</div>
												</td>
												<!-- 빈공간 -->
												<td class="top_bottom"></td>
												<!-- 노래 -->
												<td class="top_bottom">
													<div>
														<div class="ellipsis" style="max-width: 230px;">
															<span> <a
																href="${root}search/song_info?song_id=${item.song_id}"
																title="${item.song_name} - 곡 정보">${item.song_name}</a>
															</span>
														</div>
													</div>
												</td>
												<!-- 아티스트 썸네일 -->
												<td class="top_bottom">
													<!-- 캐러셀 -->
													<div id="carouselExampleSlidesOnly" class="carousel slide"
														data-ride="carousel">
														<div class="carousel-inner">
															<div class="carousel-item active">
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
																	title="${item.artist_name[0]} - 아티스트정보"> <img
																	src="${root}images/thumbnail/artist/${item.artist_thumbnail[0]}"
																	class="thum" alt="${item.artist_name[0]}">
																</a>
															</div>
															<c:if test="${fn:length(item.artist_thumbnail) > 1}">
																<c:forEach var="i" begin="1"
																	end="${fn:length(item.artist_thumbnail) - 1}">
																	<div class="carousel-item">
																		<a
																			href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																			title="${item.artist_name[i]} - 아티스트정보"> <img
																			src="${root}images/thumbnail/artist/${item.artist_thumbnail[i]}"
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
														<a
															href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
															title="${item.artist_name[0]} - 아티스트 정보">${item.artist_name[0]}</a>
														<c:if test="${fn:length(item.artist_name) > 1}">
															<c:forEach var="i" begin="1"
																end="${fn:length(item.artist_name)-1}">
																<span>, </span>
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																	title="${item.artist_name[i]} - 아티스트 정보">${item.artist_name[i]}</a>
															</c:forEach>
														</c:if>
													</div>
												</td>
												<!-- 앨범 -->
												<td class="top_bottom">
													<div class="ellipsis" style="width: 170px;">
														<a
															href="${root}search/album_info?album_id=${item.album_id}"
															title="${item.album_name} - 앨범 정보">
															${item.album_name} </a>
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
							</div>
							<div class="tab-pane fade" id="pills-pop-rock" role="tabpanel"
								aria-labelledby="pills-pop-rock-tab">록/메탈 차트
								<table style="margin-left: auto; margin-right: auto;">
									<colgroup>
										<!-- 순위 -->
										<col style="width: 62px" />
										<!-- 노래 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 곡이름 -->
										<col style="width: 280px" />
										<!-- 아티스트 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 아티스트 -->
										<col style="width: 280px" />
										<!-- 앨범 -->
										<col style="width: 210px" />
										<!-- 좋아요 -->
										<col style="width: 60px" />
									</colgroup>
									<thead>
										<tr class="line">
											<td class="top_bottom" scope="col"><div
													style="text-align: center">순위</div></td>
											<td class="top_bottom" scope="col"><div class="none">노래
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>곡정보</div></td>
											<td class="top_bottom" scope="col"><div class="none">아티스트
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>아티스트</div></td>
											<td class="top_bottom" scope="col"><div>앨범</div></td>
											<td class="top_bottom" scope="col"><div>좋아요</div></td>
										</tr>
									</thead>
									<tbody>
										<c:set var="rank" value="1" />
										<c:forEach var="item" items="${genreDataMap.pop_rock}">
											<tr class="line">
												<!-- 랭크 -->
												<td class="top_bottom">
													<div class="rank">${rank}</div>
												</td>
												<c:set var="rank" value="${rank+1}" />
												<!-- 노래 썸네일 -->
												<td class="top_bottom">
													<div>
														<a href="${root}search/song_info?song_id=${item.song_id}"
															title="${item.song_name} - 곡 정보"> <img
															class="thum mouse_over_img"
															src="${root}images/thumbnail/song/${item.song_thumbnail}" />
														</a>
													</div>
												</td>
												<!-- 빈공간 -->
												<td class="top_bottom"></td>
												<!-- 노래 -->
												<td class="top_bottom">
													<div>
														<div class="ellipsis" style="max-width: 230px;">
															<span> <a
																href="${root}search/song_info?song_id=${item.song_id}"
																title="${item.song_name} - 곡 정보">${item.song_name}</a>
															</span>
														</div>
													</div>
												</td>
												<!-- 아티스트 썸네일 -->
												<td class="top_bottom">
													<!-- 캐러셀 -->
													<div id="carouselExampleSlidesOnly" class="carousel slide"
														data-ride="carousel">
														<div class="carousel-inner">
															<div class="carousel-item active">
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
																	title="${item.artist_name[0]} - 아티스트정보"> <img
																	src="${root}images/thumbnail/artist/${item.artist_thumbnail[0]}"
																	class="thum" alt="${item.artist_name[0]}">
																</a>
															</div>
															<c:if test="${fn:length(item.artist_thumbnail) > 1}">
																<c:forEach var="i" begin="1"
																	end="${fn:length(item.artist_thumbnail) - 1}">
																	<div class="carousel-item">
																		<a
																			href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																			title="${item.artist_name[i]} - 아티스트정보"> <img
																			src="${root}images/thumbnail/artist/${item.artist_thumbnail[i]}"
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
														<a
															href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
															title="${item.artist_name[0]} - 아티스트 정보">${item.artist_name[0]}</a>
														<c:if test="${fn:length(item.artist_name) > 1}">
															<c:forEach var="i" begin="1"
																end="${fn:length(item.artist_name)-1}">
																<span>, </span>
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																	title="${item.artist_name[i]} - 아티스트 정보">${item.artist_name[i]}</a>
															</c:forEach>
														</c:if>
													</div>
												</td>
												<!-- 앨범 -->
												<td class="top_bottom">
													<div class="ellipsis" style="width: 170px;">
														<a
															href="${root}search/album_info?album_id=${item.album_id}"
															title="${item.album_name} - 앨범 정보">
															${item.album_name} </a>
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
							</div>
							<div class="tab-pane fade" id="pills-pop-fork" role="tabpanel"
								aria-labelledby="pills-pop-fork-tab">포크/블루스 차트
								<table style="margin-left: auto; margin-right: auto;">
									<colgroup>
										<!-- 순위 -->
										<col style="width: 62px" />
										<!-- 노래 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 곡이름 -->
										<col style="width: 280px" />
										<!-- 아티스트 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 아티스트 -->
										<col style="width: 280px" />
										<!-- 앨범 -->
										<col style="width: 210px" />
										<!-- 좋아요 -->
										<col style="width: 60px" />
									</colgroup>
									<thead>
										<tr class="line">
											<td class="top_bottom" scope="col"><div
													style="text-align: center">순위</div></td>
											<td class="top_bottom" scope="col"><div class="none">노래
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>곡정보</div></td>
											<td class="top_bottom" scope="col"><div class="none">아티스트
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>아티스트</div></td>
											<td class="top_bottom" scope="col"><div>앨범</div></td>
											<td class="top_bottom" scope="col"><div>좋아요</div></td>
										</tr>
									</thead>
									<tbody>
										<c:set var="rank" value="1" />
										<c:forEach var="item" items="${genreDataMap.pop_folk}">
											<tr class="line">
												<!-- 랭크 -->
												<td class="top_bottom">
													<div class="rank">${rank}</div>
												</td>
												<c:set var="rank" value="${rank+1}" />
												<!-- 노래 썸네일 -->
												<td class="top_bottom">
													<div>
														<a href="${root}search/song_info?song_id=${item.song_id}"
															title="${item.song_name} - 곡 정보"> <img
															class="thum mouse_over_img"
															src="${root}images/thumbnail/song/${item.song_thumbnail}" />
														</a>
													</div>
												</td>
												<!-- 빈공간 -->
												<td class="top_bottom"></td>
												<!-- 노래 -->
												<td class="top_bottom">
													<div>
														<div class="ellipsis" style="max-width: 230px;">
															<span> <a
																href="${root}search/song_info?song_id=${item.song_id}"
																title="${item.song_name} - 곡 정보">${item.song_name}</a>
															</span>
														</div>
													</div>
												</td>
												<!-- 아티스트 썸네일 -->
												<td class="top_bottom">
													<!-- 캐러셀 -->
													<div id="carouselExampleSlidesOnly" class="carousel slide"
														data-ride="carousel">
														<div class="carousel-inner">
															<div class="carousel-item active">
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
																	title="${item.artist_name[0]} - 아티스트정보"> <img
																	src="${root}images/thumbnail/artist/${item.artist_thumbnail[0]}"
																	class="thum" alt="${item.artist_name[0]}">
																</a>
															</div>
															<c:if test="${fn:length(item.artist_thumbnail) > 1}">
																<c:forEach var="i" begin="1"
																	end="${fn:length(item.artist_thumbnail) - 1}">
																	<div class="carousel-item">
																		<a
																			href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																			title="${item.artist_name[i]} - 아티스트정보"> <img
																			src="${root}images/thumbnail/artist/${item.artist_thumbnail[i]}"
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
														<a
															href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
															title="${item.artist_name[0]} - 아티스트 정보">${item.artist_name[0]}</a>
														<c:if test="${fn:length(item.artist_name) > 1}">
															<c:forEach var="i" begin="1"
																end="${fn:length(item.artist_name)-1}">
																<span>, </span>
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																	title="${item.artist_name[i]} - 아티스트 정보">${item.artist_name[i]}</a>
															</c:forEach>
														</c:if>
													</div>
												</td>
												<!-- 앨범 -->
												<td class="top_bottom">
													<div class="ellipsis" style="width: 170px;">
														<a
															href="${root}search/album_info?album_id=${item.album_id}"
															title="${item.album_name} - 앨범 정보">
															${item.album_name} </a>
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
							</div>
						</div>
					</div>
					<div class="tab-pane fade" id="nav-contact" role="tabpanel"
						aria-labelledby="nav-other-tab">
						<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
							<li class="nav-item" role="presentation">
								<button class="nav-link active" id="pills-ost-tab"
									data-toggle="pill" data-target="#pills-ost" type="button"
									role="tab" aria-controls="pills-ost" aria-selected="true">OST</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="pills-classic-tab"
									data-toggle="pill" data-target="#pills-classic" type="button"
									role="tab" aria-controls="pills-classic" aria-selected="false">클래식</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="pills-jazz-tab" data-toggle="pill"
									data-target="#pills-jazz" type="button" role="tab"
									aria-controls="pills-jazz" aria-selected="false">재즈</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="pills-jpop-tab" data-toggle="pill"
									data-target="#pills-jpop" type="button" role="tab"
									aria-controls="pills-jpop" aria-selected="false">JPOP</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="pills-ccm-tab" data-toggle="pill"
									data-target="#pills-ccm" type="button" role="tab"
									aria-controls="pills-ccm" aria-selected="false">CCM</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="pills-traditional-tab"
									data-toggle="pill" data-target="#pills-traditional"
									type="button" role="tab" aria-controls="pills-traditional"
									aria-selected="false">국악</button>
							</li>
						</ul>
						<div class="tab-content" id="pills-tabContent">
							<div class="tab-pane fade show active" id="pills-ost"
								role="tabpanel" aria-labelledby="pills-ost-tab">OST 차트
								<table style="margin-left: auto; margin-right: auto;">
									<colgroup>
										<!-- 순위 -->
										<col style="width: 62px" />
										<!-- 노래 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 곡이름 -->
										<col style="width: 280px" />
										<!-- 아티스트 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 아티스트 -->
										<col style="width: 280px" />
										<!-- 앨범 -->
										<col style="width: 210px" />
										<!-- 좋아요 -->
										<col style="width: 60px" />
									</colgroup>
									<thead>
										<tr class="line">
											<td class="top_bottom" scope="col"><div
													style="text-align: center">순위</div></td>
											<td class="top_bottom" scope="col"><div class="none">노래
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>곡정보</div></td>
											<td class="top_bottom" scope="col"><div class="none">아티스트
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>아티스트</div></td>
											<td class="top_bottom" scope="col"><div>앨범</div></td>
											<td class="top_bottom" scope="col"><div>좋아요</div></td>
										</tr>
									</thead>
									<tbody>
										<c:set var="rank" value="1" />
										<c:forEach var="item" items="${genreDataMap.ost}">
											<tr class="line">
												<!-- 랭크 -->
												<td class="top_bottom">
													<div class="rank">${rank}</div>
												</td>
												<c:set var="rank" value="${rank+1}" />
												<!-- 노래 썸네일 -->
												<td class="top_bottom">
													<div>
														<a href="${root}search/song_info?song_id=${item.song_id}"
															title="${item.song_name} - 곡 정보"> <img
															class="thum mouse_over_img"
															src="${root}images/thumbnail/song/${item.song_thumbnail}" />
														</a>
													</div>
												</td>
												<!-- 빈공간 -->
												<td class="top_bottom"></td>
												<!-- 노래 -->
												<td class="top_bottom">
													<div>
														<div class="ellipsis" style="max-width: 230px;">
															<span> <a
																href="${root}search/song_info?song_id=${item.song_id}"
																title="${item.song_name} - 곡 정보">${item.song_name}</a>
															</span>
														</div>
													</div>
												</td>
												<!-- 아티스트 썸네일 -->
												<td class="top_bottom">
													<!-- 캐러셀 -->
													<div id="carouselExampleSlidesOnly" class="carousel slide"
														data-ride="carousel">
														<div class="carousel-inner">
															<div class="carousel-item active">
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
																	title="${item.artist_name[0]} - 아티스트정보"> <img
																	src="${root}images/thumbnail/artist/${item.artist_thumbnail[0]}"
																	class="thum" alt="${item.artist_name[0]}">
																</a>
															</div>
															<c:if test="${fn:length(item.artist_thumbnail) > 1}">
																<c:forEach var="i" begin="1"
																	end="${fn:length(item.artist_thumbnail) - 1}">
																	<div class="carousel-item">
																		<a
																			href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																			title="${item.artist_name[i]} - 아티스트정보"> <img
																			src="${root}images/thumbnail/artist/${item.artist_thumbnail[i]}"
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
														<a
															href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
															title="${item.artist_name[0]} - 아티스트 정보">${item.artist_name[0]}</a>
														<c:if test="${fn:length(item.artist_name) > 1}">
															<c:forEach var="i" begin="1"
																end="${fn:length(item.artist_name)-1}">
																<span>, </span>
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																	title="${item.artist_name[i]} - 아티스트 정보">${item.artist_name[i]}</a>
															</c:forEach>
														</c:if>
													</div>
												</td>
												<!-- 앨범 -->
												<td class="top_bottom">
													<div class="ellipsis" style="width: 170px;">
														<a
															href="${root}search/album_info?album_id=${item.album_id}"
															title="${item.album_name} - 앨범 정보">
															${item.album_name} </a>
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
							</div>
							<div class="tab-pane fade" id="pills-classic" role="tabpanel"
								aria-labelledby="pills-classic-tab">클래식 차트
								<table style="margin-left: auto; margin-right: auto;">
									<colgroup>
										<!-- 순위 -->
										<col style="width: 62px" />
										<!-- 노래 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 곡이름 -->
										<col style="width: 280px" />
										<!-- 아티스트 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 아티스트 -->
										<col style="width: 280px" />
										<!-- 앨범 -->
										<col style="width: 210px" />
										<!-- 좋아요 -->
										<col style="width: 60px" />
									</colgroup>
									<thead>
										<tr class="line">
											<td class="top_bottom" scope="col"><div
													style="text-align: center">순위</div></td>
											<td class="top_bottom" scope="col"><div class="none">노래
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>곡정보</div></td>
											<td class="top_bottom" scope="col"><div class="none">아티스트
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>아티스트</div></td>
											<td class="top_bottom" scope="col"><div>앨범</div></td>
											<td class="top_bottom" scope="col"><div>좋아요</div></td>
										</tr>
									</thead>
									<tbody>
										<c:set var="rank" value="1" />
										<c:forEach var="item" items="${genreDataMap.classic}">
											<tr class="line">
												<!-- 랭크 -->
												<td class="top_bottom">
													<div class="rank">${rank}</div>
												</td>
												<c:set var="rank" value="${rank+1}" />
												<!-- 노래 썸네일 -->
												<td class="top_bottom">
													<div>
														<a href="${root}search/song_info?song_id=${item.song_id}"
															title="${item.song_name} - 곡 정보"> <img
															class="thum mouse_over_img"
															src="${root}images/thumbnail/song/${item.song_thumbnail}" />
														</a>
													</div>
												</td>
												<!-- 빈공간 -->
												<td class="top_bottom"></td>
												<!-- 노래 -->
												<td class="top_bottom">
													<div>
														<div class="ellipsis" style="max-width: 230px;">
															<span> <a
																href="${root}search/song_info?song_id=${item.song_id}"
																title="${item.song_name} - 곡 정보">${item.song_name}</a>
															</span>
														</div>
													</div>
												</td>
												<!-- 아티스트 썸네일 -->
												<td class="top_bottom">
													<!-- 캐러셀 -->
													<div id="carouselExampleSlidesOnly" class="carousel slide"
														data-ride="carousel">
														<div class="carousel-inner">
															<div class="carousel-item active">
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
																	title="${item.artist_name[0]} - 아티스트정보"> <img
																	src="${root}images/thumbnail/artist/${item.artist_thumbnail[0]}"
																	class="thum" alt="${item.artist_name[0]}">
																</a>
															</div>
															<c:if test="${fn:length(item.artist_thumbnail) > 1}">
																<c:forEach var="i" begin="1"
																	end="${fn:length(item.artist_thumbnail) - 1}">
																	<div class="carousel-item">
																		<a
																			href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																			title="${item.artist_name[i]} - 아티스트정보"> <img
																			src="${root}images/thumbnail/artist/${item.artist_thumbnail[i]}"
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
														<a
															href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
															title="${item.artist_name[0]} - 아티스트 정보">${item.artist_name[0]}</a>
														<c:if test="${fn:length(item.artist_name) > 1}">
															<c:forEach var="i" begin="1"
																end="${fn:length(item.artist_name)-1}">
																<span>, </span>
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																	title="${item.artist_name[i]} - 아티스트 정보">${item.artist_name[i]}</a>
															</c:forEach>
														</c:if>
													</div>
												</td>
												<!-- 앨범 -->
												<td class="top_bottom">
													<div class="ellipsis" style="width: 170px;">
														<a
															href="${root}search/album_info?album_id=${item.album_id}"
															title="${item.album_name} - 앨범 정보">
															${item.album_name} </a>
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
							</div>
							<div class="tab-pane fade" id="pills-jazz" role="tabpanel"
								aria-labelledby="pills-jazz-tab">재즈 차트
								<table style="margin-left: auto; margin-right: auto;">
									<colgroup>
										<!-- 순위 -->
										<col style="width: 62px" />
										<!-- 노래 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 곡이름 -->
										<col style="width: 280px" />
										<!-- 아티스트 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 아티스트 -->
										<col style="width: 280px" />
										<!-- 앨범 -->
										<col style="width: 210px" />
										<!-- 좋아요 -->
										<col style="width: 60px" />
									</colgroup>
									<thead>
										<tr class="line">
											<td class="top_bottom" scope="col"><div
													style="text-align: center">순위</div></td>
											<td class="top_bottom" scope="col"><div class="none">노래
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>곡정보</div></td>
											<td class="top_bottom" scope="col"><div class="none">아티스트
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>아티스트</div></td>
											<td class="top_bottom" scope="col"><div>앨범</div></td>
											<td class="top_bottom" scope="col"><div>좋아요</div></td>
										</tr>
									</thead>
									<tbody>
										<c:set var="rank" value="1" />
										<c:forEach var="item" items="${genreDataMap.jazz}">
											<tr class="line">
												<!-- 랭크 -->
												<td class="top_bottom">
													<div class="rank">${rank}</div>
												</td>
												<c:set var="rank" value="${rank+1}" />
												<!-- 노래 썸네일 -->
												<td class="top_bottom">
													<div>
														<a href="${root}search/song_info?song_id=${item.song_id}"
															title="${item.song_name} - 곡 정보"> <img
															class="thum mouse_over_img"
															src="${root}images/thumbnail/song/${item.song_thumbnail}" />
														</a>
													</div>
												</td>
												<!-- 빈공간 -->
												<td class="top_bottom"></td>
												<!-- 노래 -->
												<td class="top_bottom">
													<div>
														<div class="ellipsis" style="max-width: 230px;">
															<span> <a
																href="${root}search/song_info?song_id=${item.song_id}"
																title="${item.song_name} - 곡 정보">${item.song_name}</a>
															</span>
														</div>
													</div>
												</td>
												<!-- 아티스트 썸네일 -->
												<td class="top_bottom">
													<!-- 캐러셀 -->
													<div id="carouselExampleSlidesOnly" class="carousel slide"
														data-ride="carousel">
														<div class="carousel-inner">
															<div class="carousel-item active">
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
																	title="${item.artist_name[0]} - 아티스트정보"> <img
																	src="${root}images/thumbnail/artist/${item.artist_thumbnail[0]}"
																	class="thum" alt="${item.artist_name[0]}">
																</a>
															</div>
															<c:if test="${fn:length(item.artist_thumbnail) > 1}">
																<c:forEach var="i" begin="1"
																	end="${fn:length(item.artist_thumbnail) - 1}">
																	<div class="carousel-item">
																		<a
																			href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																			title="${item.artist_name[i]} - 아티스트정보"> <img
																			src="${root}images/thumbnail/artist/${item.artist_thumbnail[i]}"
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
														<a
															href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
															title="${item.artist_name[0]} - 아티스트 정보">${item.artist_name[0]}</a>
														<c:if test="${fn:length(item.artist_name) > 1}">
															<c:forEach var="i" begin="1"
																end="${fn:length(item.artist_name)-1}">
																<span>, </span>
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																	title="${item.artist_name[i]} - 아티스트 정보">${item.artist_name[i]}</a>
															</c:forEach>
														</c:if>
													</div>
												</td>
												<!-- 앨범 -->
												<td class="top_bottom">
													<div class="ellipsis" style="width: 170px;">
														<a
															href="${root}search/album_info?album_id=${item.album_id}"
															title="${item.album_name} - 앨범 정보">
															${item.album_name} </a>
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
							</div>
							<div class="tab-pane fade" id="pills-jpop" role="tabpanel"
								aria-labelledby="pills-jpop-tab">JPOP 차트
								<table style="margin-left: auto; margin-right: auto;">
									<colgroup>
										<!-- 순위 -->
										<col style="width: 62px" />
										<!-- 노래 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 곡이름 -->
										<col style="width: 280px" />
										<!-- 아티스트 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 아티스트 -->
										<col style="width: 280px" />
										<!-- 앨범 -->
										<col style="width: 210px" />
										<!-- 좋아요 -->
										<col style="width: 60px" />
									</colgroup>
									<thead>
										<tr class="line">
											<td class="top_bottom" scope="col"><div
													style="text-align: center">순위</div></td>
											<td class="top_bottom" scope="col"><div class="none">노래
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>곡정보</div></td>
											<td class="top_bottom" scope="col"><div class="none">아티스트
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>아티스트</div></td>
											<td class="top_bottom" scope="col"><div>앨범</div></td>
											<td class="top_bottom" scope="col"><div>좋아요</div></td>
										</tr>
									</thead>
									<tbody>
										<c:set var="rank" value="1" />
										<c:forEach var="item" items="${genreDataMap.jpop}">
											<tr class="line">
												<!-- 랭크 -->
												<td class="top_bottom">
													<div class="rank">${rank}</div>
												</td>
												<c:set var="rank" value="${rank+1}" />
												<!-- 노래 썸네일 -->
												<td class="top_bottom">
													<div>
														<a href="${root}search/song_info?song_id=${item.song_id}"
															title="${item.song_name} - 곡 정보"> <img
															class="thum mouse_over_img"
															src="${root}images/thumbnail/song/${item.song_thumbnail}" />
														</a>
													</div>
												</td>
												<!-- 빈공간 -->
												<td class="top_bottom"></td>
												<!-- 노래 -->
												<td class="top_bottom">
													<div>
														<div class="ellipsis" style="max-width: 230px;">
															<span> <a
																href="${root}search/song_info?song_id=${item.song_id}"
																title="${item.song_name} - 곡 정보">${item.song_name}</a>
															</span>
														</div>
													</div>
												</td>
												<!-- 아티스트 썸네일 -->
												<td class="top_bottom">
													<!-- 캐러셀 -->
													<div id="carouselExampleSlidesOnly" class="carousel slide"
														data-ride="carousel">
														<div class="carousel-inner">
															<div class="carousel-item active">
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
																	title="${item.artist_name[0]} - 아티스트정보"> <img
																	src="${root}images/thumbnail/artist/${item.artist_thumbnail[0]}"
																	class="thum" alt="${item.artist_name[0]}">
																</a>
															</div>
															<c:if test="${fn:length(item.artist_thumbnail) > 1}">
																<c:forEach var="i" begin="1"
																	end="${fn:length(item.artist_thumbnail) - 1}">
																	<div class="carousel-item">
																		<a
																			href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																			title="${item.artist_name[i]} - 아티스트정보"> <img
																			src="${root}images/thumbnail/artist/${item.artist_thumbnail[i]}"
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
														<a
															href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
															title="${item.artist_name[0]} - 아티스트 정보">${item.artist_name[0]}</a>
														<c:if test="${fn:length(item.artist_name) > 1}">
															<c:forEach var="i" begin="1"
																end="${fn:length(item.artist_name)-1}">
																<span>, </span>
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																	title="${item.artist_name[i]} - 아티스트 정보">${item.artist_name[i]}</a>
															</c:forEach>
														</c:if>
													</div>
												</td>
												<!-- 앨범 -->
												<td class="top_bottom">
													<div class="ellipsis" style="width: 170px;">
														<a
															href="${root}search/album_info?album_id=${item.album_id}"
															title="${item.album_name} - 앨범 정보">
															${item.album_name} </a>
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
							</div>
							<div class="tab-pane fade" id="pills-ccm" role="tabpanel"
								aria-labelledby="pills-ccm-tab">CCM 차트
								<table style="margin-left: auto; margin-right: auto;">
									<colgroup>
										<!-- 순위 -->
										<col style="width: 62px" />
										<!-- 노래 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 곡이름 -->
										<col style="width: 280px" />
										<!-- 아티스트 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 아티스트 -->
										<col style="width: 280px" />
										<!-- 앨범 -->
										<col style="width: 210px" />
										<!-- 좋아요 -->
										<col style="width: 60px" />
									</colgroup>
									<thead>
										<tr class="line">
											<td class="top_bottom" scope="col"><div
													style="text-align: center">순위</div></td>
											<td class="top_bottom" scope="col"><div class="none">노래
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>곡정보</div></td>
											<td class="top_bottom" scope="col"><div class="none">아티스트
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>아티스트</div></td>
											<td class="top_bottom" scope="col"><div>앨범</div></td>
											<td class="top_bottom" scope="col"><div>좋아요</div></td>
										</tr>
									</thead>
									<tbody>
										<c:set var="rank" value="1" />
										<c:forEach var="item" items="${genreDataMap.ccm}">
											<tr class="line">
												<!-- 랭크 -->
												<td class="top_bottom">
													<div class="rank">${rank}</div>
												</td>
												<c:set var="rank" value="${rank+1}" />
												<!-- 노래 썸네일 -->
												<td class="top_bottom">
													<div>
														<a href="${root}search/song_info?song_id=${item.song_id}"
															title="${item.song_name} - 곡 정보"> <img
															class="thum mouse_over_img"
															src="${root}images/thumbnail/song/${item.song_thumbnail}" />
														</a>
													</div>
												</td>
												<!-- 빈공간 -->
												<td class="top_bottom"></td>
												<!-- 노래 -->
												<td class="top_bottom">
													<div>
														<div class="ellipsis" style="max-width: 230px;">
															<span> <a
																href="${root}search/song_info?song_id=${item.song_id}"
																title="${item.song_name} - 곡 정보">${item.song_name}</a>
															</span>
														</div>
													</div>
												</td>
												<!-- 아티스트 썸네일 -->
												<td class="top_bottom">
													<!-- 캐러셀 -->
													<div id="carouselExampleSlidesOnly" class="carousel slide"
														data-ride="carousel">
														<div class="carousel-inner">
															<div class="carousel-item active">
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
																	title="${item.artist_name[0]} - 아티스트정보"> <img
																	src="${root}images/thumbnail/artist/${item.artist_thumbnail[0]}"
																	class="thum" alt="${item.artist_name[0]}">
																</a>
															</div>
															<c:if test="${fn:length(item.artist_thumbnail) > 1}">
																<c:forEach var="i" begin="1"
																	end="${fn:length(item.artist_thumbnail) - 1}">
																	<div class="carousel-item">
																		<a
																			href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																			title="${item.artist_name[i]} - 아티스트정보"> <img
																			src="${root}images/thumbnail/artist/${item.artist_thumbnail[i]}"
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
														<a
															href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
															title="${item.artist_name[0]} - 아티스트 정보">${item.artist_name[0]}</a>
														<c:if test="${fn:length(item.artist_name) > 1}">
															<c:forEach var="i" begin="1"
																end="${fn:length(item.artist_name)-1}">
																<span>, </span>
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																	title="${item.artist_name[i]} - 아티스트 정보">${item.artist_name[i]}</a>
															</c:forEach>
														</c:if>
													</div>
												</td>
												<!-- 앨범 -->
												<td class="top_bottom">
													<div class="ellipsis" style="width: 170px;">
														<a
															href="${root}search/album_info?album_id=${item.album_id}"
															title="${item.album_name} - 앨범 정보">
															${item.album_name} </a>
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
							</div>
							<div class="tab-pane fade" id="pills-traditional" role="tabpanel"
								aria-labelledby="pills-traditional-tab">국악 차트
								<table style="margin-left: auto; margin-right: auto;">
									<colgroup>
										<!-- 순위 -->
										<col style="width: 62px" />
										<!-- 노래 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 곡이름 -->
										<col style="width: 280px" />
										<!-- 아티스트 썸네일 -->
										<col style="width: 60px" />
										<!-- 빈공간 -->
										<col style="width: 20px" />
										<!-- 아티스트 -->
										<col style="width: 280px" />
										<!-- 앨범 -->
										<col style="width: 210px" />
										<!-- 좋아요 -->
										<col style="width: 60px" />
									</colgroup>
									<thead>
										<tr class="line">
											<td class="top_bottom" scope="col"><div
													style="text-align: center">순위</div></td>
											<td class="top_bottom" scope="col"><div class="none">노래
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>곡정보</div></td>
											<td class="top_bottom" scope="col"><div class="none">아티스트
													썸네일</div></td>
											<td class="top_bottom" scope="col"><div class="none">빈공간</div></td>
											<td class="top_bottom" scope="col"><div>아티스트</div></td>
											<td class="top_bottom" scope="col"><div>앨범</div></td>
											<td class="top_bottom" scope="col"><div>좋아요</div></td>
										</tr>
									</thead>
									<tbody>
										<c:set var="rank" value="1" />
										<c:forEach var="item" items="${genreDataMap.traditional}">
											<tr class="line">
												<!-- 랭크 -->
												<td class="top_bottom">
													<div class="rank">${rank}</div>
												</td>
												<c:set var="rank" value="${rank+1}" />
												<!-- 노래 썸네일 -->
												<td class="top_bottom">
													<div>
														<a href="${root}search/song_info?song_id=${item.song_id}"
															title="${item.song_name} - 곡 정보"> <img
															class="thum mouse_over_img"
															src="${root}images/thumbnail/song/${item.song_thumbnail}" />
														</a>
													</div>
												</td>
												<!-- 빈공간 -->
												<td class="top_bottom"></td>
												<!-- 노래 -->
												<td class="top_bottom">
													<div>
														<div class="ellipsis" style="max-width: 230px;">
															<span> <a
																href="${root}search/song_info?song_id=${item.song_id}"
																title="${item.song_name} - 곡 정보">${item.song_name}</a>
															</span>
														</div>
													</div>
												</td>
												<!-- 아티스트 썸네일 -->
												<td class="top_bottom">
													<!-- 캐러셀 -->
													<div id="carouselExampleSlidesOnly" class="carousel slide"
														data-ride="carousel">
														<div class="carousel-inner">
															<div class="carousel-item active">
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
																	title="${item.artist_name[0]} - 아티스트정보"> <img
																	src="${root}images/thumbnail/artist/${item.artist_thumbnail[0]}"
																	class="thum" alt="${item.artist_name[0]}">
																</a>
															</div>
															<c:if test="${fn:length(item.artist_thumbnail) > 1}">
																<c:forEach var="i" begin="1"
																	end="${fn:length(item.artist_thumbnail) - 1}">
																	<div class="carousel-item">
																		<a
																			href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																			title="${item.artist_name[i]} - 아티스트정보"> <img
																			src="${root}images/thumbnail/artist/${item.artist_thumbnail[i]}"
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
														<a
															href="${root}search/artist_info?artist_id=${item.artist_id[0]}"
															title="${item.artist_name[0]} - 아티스트 정보">${item.artist_name[0]}</a>
														<c:if test="${fn:length(item.artist_name) > 1}">
															<c:forEach var="i" begin="1"
																end="${fn:length(item.artist_name)-1}">
																<span>, </span>
																<a
																	href="${root}search/artist_info?artist_id=${item.artist_id[i]}"
																	title="${item.artist_name[i]} - 아티스트 정보">${item.artist_name[i]}</a>
															</c:forEach>
														</c:if>
													</div>
												</td>
												<!-- 앨범 -->
												<td class="top_bottom">
													<div class="ellipsis" style="width: 170px;">
														<a
															href="${root}search/album_info?album_id=${item.album_id}"
															title="${item.album_name} - 앨범 정보">
															${item.album_name} </a>
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
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
		crossorigin="anonymous"></script>
		
	<c:import url="/WEB-INF/views/include/bottom.jsp" />
</body>
</html>

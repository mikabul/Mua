<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var='root' value='${pageContext.request.contextPath}/' />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mua</title>
<!-- 부트 스트랩 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
<!-- css -->
<link rel="styleSheet" href="${root}style/any_main.css">
</head>
<body>

	<!-- 상단 부분 -->
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	<!-- 중단 부분 -->
	<section style="margin-top: 300px; min-height: 700px;">
	<!-- 검색바 -->
	<c:import url="/WEB-INF/views/include/searchBar.jsp" />
	
		<div>
			<div style="min-height: 500px;">
				<table style="margin: auto;">
					<colgroup>
						<!-- 번호 -->
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
						<tr>
							<th scope="col">번호</th>
							<th scope="col"><span class="none">노래 썸네일</span></th>
							<th scope="col"><span class="none">빈공간</span></th>
							<th scope="col">노래</th>
							<th scope="col"><span class="none">아티스트 썸네일</span></th>
							<th scope="col"><span class="none">빈공간</span></th>
							<th scope="col">아티스트</th>
							<th scope="col">앨범</th>
							<th scope="col">좋아요</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${index == 0}">
								<c:set var="num" value="1" />
							</c:when>
							<c:otherwise>
								<c:set var="num" value="${index}" />
							</c:otherwise>
						</c:choose>
						<c:forEach var="item" items="${songList}">
							<tr class="line">
								<!-- 번호 -->
								<td class="top_bottom">
									<div class="rank">${num}</div>
								</td>
								<c:set var="num" value="${num+1}" />
								<!-- 노래 썸네일 -->
								<td class="top_bottom">
									<div>
										<a href="${root}search/song_info?song_id=${item.songDto.song_id}" title="${item.songDto.song_name} - 곡 정보">
											<img class="thum mouse_over_img" src="${root}images/thumbnail/song/${item.songDto.song_thumbnail}" />
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
												<a href="${root}search/song_info?song_id=${item.songDto.song_id}" title="${item.songDto.song_name} - 곡 정보">
													${item.songDto.song_name}
												</a>
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
												<a href="${root}search/artist_info?artist_id=${item.artistList[0].artist_id}" title="${item.artistList[0].artist_name} - 아티스트정보">
													<img src="${root}images/thumbnail/artist/${item.artistList[0].artist_thumbnail}" class="thum" alt="${item.artistList[0].artist_name}">
												</a>
											</div>
											<c:if test="${fn:length(item.artistList) > 1}">
												<c:forEach var="i" begin="1" end="${fn:length(item.artistList) - 1}">
													<div class="carousel-item">
														<a href="${root}search/artist_info?artist_id=${item.artistList[i].artist_id}" title="${item.artistList[i].artist_name} - 아티스트정보">
															<img src="${root}images/thumbnail/artist/${item.artistList[i].artist_thumbnail}" class="thum" alt="${item.artistList[i].artist_name}">
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
										<a href="${root}search/artist_info?artist_id=${item.artistList[0].artist_id}" title="${item.artistList[0].artist_name} - 아티스트 정보">
											${item.artistList[0].artist_name}
										</a>
										<c:if test="${fn:length(item.artistList) > 1}">
											<c:forEach var="i" begin="1" end="${fn:length(item.artistList) - 1}">
												<span>, </span>
												<a href="${root}search/artist_info?artist_id=${item.artistList[i].artist_id}" title="${item.artistList[i].artist_name} - 아티스트 정보">
													${item.artistList[i].artist_name}
												</a>
											</c:forEach>
										</c:if>
									</div>
								</td>
								<!-- 앨범 -->
								<td class="top_bottom">
									<div class="ellipsis" style="width: 170px;">
										<a href="${root}search/album_info?album_id=${item.songDto.album_id}"
											title="${item.songDto.album_name} - 앨범 정보">
											${item.songDto.album_name}
										</a>
									</div>
								</td>
								<!-- 좋아요 -->
								<td class="top_bottom">
									<div>${item.songDto.song_thumbup}</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- 페이지 버튼 -->
			<div class="container" style="margin-top: 15px;">
			    <div class="row justify-content-center">
			        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
			            <div class="btn-group mr-2" role="group" aria-label="First group">
			            	<c:choose>
			            		<c:when test="${page - 10 < 1}">
			            			<button type="button" class="btn btn-secondary disabled">10개</button>
			            		</c:when>
			            		<c:otherwise>
			            			<button type="button" class="btn btn-secondary"
			            				onclick="location.href='${root}search/main?type=${type}&index=${index - (10 * maxView)}&maxView=${maxView}&str=${str}'">
			            			10개
			            			</button>
			            		</c:otherwise>
			            	</c:choose>
			            	<c:choose>
			            		<c:when test="${page - 1 < 1}">
			            			<button type="button" class="btn btn-secondary disabled">이전페이지</button>
			            		</c:when>
			            		<c:otherwise>
			            			<button type="button" class="btn btn-secondary"
			            				onclick="location.href='${root}search/main?type=${type}&index=${index - maxView}&maxView=${maxView}&str=${str}'">
			            			이전페이지
			            			</button>
			            		</c:otherwise>
			            	</c:choose>
			            </div>
			            <div class="btn-group mr-2" role="group" aria-label="Second group">
			                <c:set var="pageIndex" value="${maxView}" />
			                <c:forEach var="item" items="${loadPage}">
			                	<c:choose>
			                		<c:when test="${item == page}">
			                			<button type="button" class="btn btn-secondary disabled">
						               		${item}
						               	</button>
			                		</c:when>
			                		<c:otherwise>
					                	<button type="button" class="btn btn-secondary"
						               	onclick="location.href='${root}search/main?type=${type}&index=${pageIndex * (item - 1) + 1}&maxView=${maxView}&str=${str}'">
						               		${item}
						               	</button>
			                		</c:otherwise>
			                	</c:choose>
			                </c:forEach>
			            </div>
			            <div class="btn-group" role="group" aria-label="Third group">
			            	<c:choose>
			            		<c:when test="${page + 1 > maxPage}">
			            			<button type="button" class="btn btn-secondary disabled">이전페이지</button>
			            		</c:when>
			            		<c:otherwise>
			            			<button type="button" class="btn btn-secondary"
			            				onclick="location.href='${root}search/main?type=${type}&index=${index + maxView}&maxView=${maxView}&str=${str}'">
			            			다음페이지
			            			</button>
			            		</c:otherwise>
			            	</c:choose>
			                <c:choose>
			            		<c:when test="${page + 10 > maxPage}">
			            			<button type="button" class="btn btn-secondary disabled">10개</button>
			            		</c:when>
			            		<c:otherwise>
			            			<button type="button" class="btn btn-secondary"
			            				onclick="location.href='${root}search/main?type=${type}&index=${index + (10 * maxView)}&maxView=${maxView}&str=${str}'">
			            			10개
			            			</button>
			            		</c:otherwise>
			            	</c:choose>
			            </div>
			        </div>
			    </div>
			</div>
		</div>
	</section>
	
	<c:import url="/WEB-INF/views/include/bottom.jsp" />
	
</body>
</html>
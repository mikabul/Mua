<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var='root' value='${pageContext.request.contextPath}/' />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 부트 스트랩 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
<!-- css -->
<link rel="styleSheet" href="${root}style/any_main.css">
</head>
<body>

	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	<c:import url="/WEB-INF/views/include/searchBar.jsp" />

	<section style="width: 100%; min-height: 700px; margin-top: 300px;">
		<div>
			<div style="width: 70%; margin-right: 15%; margin-left: 15%;">
				<div class="row row-cols-1 row-cols-md-4" style="top: 200px;">
					<c:forEach var="item" items="${albumList}">
						<div class="col mb-4">
							<div class="card">
								<a href="${root}search/album_info?album_id=${item.albumDto.album_id}" title="${item.albumDto.album_name} - 엘범 정보">
									<img src="${root}images/thumbnail/album/${item.albumDto.album_thumbnail}"
										 class="card-img-top" alt="${item.albumDto.album_thumbnail}">
								</a>
								<div class="card-body">
									<h5 class="card-title ellipsis">
										<a href="${root}search/album_info?album_id=${item.albumDto.album_id}" title="${item.albumDto.album_name}">${item.albumDto.album_name}</a>
									</h5>
									<p class="ellipsis">
										<c:choose>
											<c:when test="${item.artistList[0].artist_name == 'Various Artists'}">
												${item.artistList[0].artist_name}
											</c:when>
											<c:otherwise>
												<a href="${root}search/artist_info?artist_id=${item.artistList[0].artist_id}" title="${item.artistList[0].artist_name}">
													${item.artistList[0].artist_name}
												</a>
												<c:if test="${fn:length(item.artistList) > 1}">
													<c:forEach var="i" begin="1" end="${fn:length(item.artistList) - 1}">
														<span>, </span>
														<a href="${root}search/artist_info?artist_id=${item.artistList[i].artist_id}" title="${item.artistList[i].artist_name}">
															${item.artistList[i].artist_name}
														</a>
													</c:forEach>
												</c:if>
											</c:otherwise>
										</c:choose>
									</p>
									<p>${item.albumDto.release_date}</p>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
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
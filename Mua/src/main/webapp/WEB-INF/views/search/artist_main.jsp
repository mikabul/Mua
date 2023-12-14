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
	
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	<c:import url="/WEB-INF/views/include/searchBar.jsp" />

	<section style="width: 100%; min-height: 700px; margin-top: 300px;">
		<div>
			<div style="width: 70%; margin-right: 15%; margin-left: 15%;">
				<div class="row row-cols-1 row-cols-md-4" style="top: 200px;">
					<c:forEach var="item" items="${artistList}">
						<div class="col mb-4">
							<div class="card">
								<a href="${root}search/artist_info?artist_id=${item.artist_id}" title="${item.artist_name} - 아티스트 정보">
									<img src="${root}images/thumbnail/artist/${item.artist_thumbnail}"
										 class="card-img-top" alt="${item.artist_name}">
								</a>
								<div class="card-body">
									<h5 class="card-title ellipsis">
										<a href="${root}search/artist_info?artist_id=${item.artist_id}" title="${item.artist_name}">${item.artist_name}</a>
									</h5>
									<p class="ellipsis">${item.member}</p>
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
							<button type="button" class="btn btn-secondary">10개</button>
							<button type="button" class="btn btn-secondary">이전페이지</button>
						</div>
						<div class="btn-group mr-2" role="group" aria-label="Second group">
							<c:set var="pageIndex" value="${maxView}" />
							<c:forEach var="item" items="${loadPage}">
								<button type="button" class="btn btn-secondary"
									onclick="location.href='${root}search/main?type=${type}&index=${pageIndex * (item - 1) + 1}&maxView=${maxView}&str=${str}'">
										${item}
							</button>
							</c:forEach>
						</div>
						<div class="btn-group" role="group" aria-label="Third group">
							<button type="button" class="btn btn-secondary">다음페이지</button>
							<button type="button" class="btn btn-secondary">10개</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<c:import url="/WEB-INF/views/include/bottom.jsp" />
</body>
</html>
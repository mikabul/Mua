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
<body>
	<header>
		<c:import url="/WEB-INF/views/admin/include/top.jsp" />
	</header>
	<section style="margin-top: 150px;">
		<div class="container">
			<form action="${root }admin/crol/search/main" method="get">
				<div class="form-row justify-content-center">
					<div class="col-auto">
						<select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="search_where">
							<option value="frm_searchSong" selected>노래</option>
							<option value="frm_searchArtist">가수</option>
							<option value="frm_searchAlbum">앨범</option>
						</select>
					</div>
					<input type="text" class="form-control col-4"
						name="search_value">
					<div class="col-auto">
						<button type="submit" class="btn btn-primary">&nbsp;검색&nbsp;</button>
					</div>
				</div>
			</form>
		</div>
		<div class="container" style="margin-top: 70px;">
			<div class="text-center">
				<strong>장르별</strong>
			</div>
			<div style="border: 1px solid #DDDEDF; margin-top: 10px">
				<div class="container" style="margin-top: 30px;">
					<div class="text-center">
						<strong>K-POP</strong>
						<hr>
					</div>
				</div>
				<div class="container">
					<div class="col-group">
						<div class="row">
							<div class="col">
								<button class="btn btn-success btn-block" onclick="location.href='${root}admin/crol/genre?genreCode=GN0100'">발라드</button>
							</div>	
							<div class="col">
								<button class="btn btn-success btn-block" onclick="location.href='${root}admin/crol/genre?genreCode=GN0200'">댄스</button>
							</div>
							<div class="col">
								<button class="btn btn-success btn-block" onclick="location.href='${root}admin/crol/genre?genreCode=GN0300'">랩</button>
							</div>
							<div class="col">
								<button class="btn btn-success btn-block" onclick="location.href='${root}admin/crol/genre?genreCode=GN0400'">R&B</button>
							</div>
							<div class="col">
								<button class="btn btn-success btn-block" onclick="location.href='${root}admin/crol/genre?genreCode=GN0500'">인디</button>
							</div>
							<div class="col">
								<button class="btn btn-success btn-block" onclick="location.href='${root}admin/crol/genre?genreCode=GN0600'">락</button>
							</div>
							<div class="col">
								<button class="btn btn-success btn-block" onclick="location.href='${root}admin/crol/genre?genreCode=GN0700'">트로트</button>
							</div>
							<div class="col">
								<button class="btn btn-success btn-block" onclick="location.href='${root}admin/crol/genre?genreCode=GN0800'">포크</button>
							</div>
						</div>
					</div>
				</div>
				<div class="container" style="margin-top: 30px;">
					<div class="text-center">
						<strong>POP</strong>
						<hr>
					</div>
				</div>
				<div class="container">
					<div class="col=group">
						<div class="row">
							<div class="col">
								<button class="btn btn-success btn-block" onclick="location.href='${root}admin/crol/genre?genreCode=GN0900'">팝</button>
							</div>	
							<div class="col">
								<button class="btn btn-success btn-block" onclick="location.href='${root}admin/crol/genre?genreCode=GN1000'">락</button>
							</div>
							<div class="col">
								<button class="btn btn-success btn-block" onclick="location.href='${root}admin/crol/genre?genreCode=GN1100'">일렉</button>
							</div>
							<div class="col">
								<button class="btn btn-success btn-block" onclick="location.href='${root}admin/crol/genre?genreCode=GN1200'">랩</button>
							</div>
							<div class="col">
								<button class="btn btn-success btn-block" onclick="location.href='${root}admin/crol/genre?genreCode=GN1300'">R&B</button>
							</div>
							<div class="col">
								<button class="btn btn-success btn-block" onclick="location.href='${root}admin/crol/genre?genreCode=GN1400'">포크</button>
							</div>
						</div>
					</div>
				</div>
				<div class="container" style="margin-top: 30px;">
					<div class="text-center">
						<strong>그 외 장르</strong>
						<hr>
					</div>
				</div>
				<div class="container" style="margin-bottom: 30px;">
					<div class="col=group">
						<div class="row">
							<div class="col">
								<button class="btn btn-success btn-block" onclick="location.href='${root}admin/crol/genre?genreCode=GN1500'">ost</button>
							</div>	
							<div class="col">
								<button class="btn btn-success btn-block" onclick="location.href='${root}admin/crol/genre?genreCode=GN1600'">클래식</button>
							</div>
							<div class="col">
								<button class="btn btn-success btn-block" onclick="location.href='${root}admin/crol/genre?genreCode=GN1700'">재즈</button>
							</div>
							<div class="col">
								<button class="btn btn-success btn-block" onclick="location.href='${root}admin/crol/genre?genreCode=GN1900'">J-POP</button>
							</div>
							<div class="col">
								<button class="btn btn-success btn-block" onclick="location.href='${root}admin/crol/genre?genreCode=GN2100'">ccm</button>
							</div>
							<div class="col">
								<button class="btn btn-success btn-block" onclick="location.href='${root}admin/crol/genre?genreCode=GN2400'">국악</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>
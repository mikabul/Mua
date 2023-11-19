<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
<link rel="styleSheet" href="./style/main.css">
</head>
<style>
.items-consertrec{
	position: relative;
	top: 700px;
	width: 100%;
	height: 800px;
}

.consertrec{
	height:500px;
	width:90%;
	margin:5%;
}

#carouselExampleIndicators{
	height: 100%;
	width: 100%;
}

.carousel-inner{
	height: 100%;
	width: 100%;
}

.consert-img{
	position: absolute;
	width: 1207.8px;
	height: 500px;
}
.d-block w-100{
	width: 300px;
  	height: 300px;
	object-fit: cover;
}

.items-songrec-songchart{
	position: relative;
	top: 500px;
	width: 100%;
	height: 500px;
}

.songrec{
	float:left;
	height:500px;
	width:40%;
	margin: 5%;
}
.song-img-top{
	width: 300px;
  	height: 300px;
	object-fit: cover;
}
.songchart{
	float:left;
	height:420px;
	width: 40%;
	margin: 5%;
	overflow-x:hidden;  
	overflow-y:auto;
}

</style>
<body>
	<!-- 상단 메뉴 부분 -->
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	<!-- 중단 부분 -->
	<section style="position: relative; height: 2000px; width: 100%;">
		<form action="#" method="post">
			<div class="form-row align-items-center search_form">
				<div class="col-auto my-1">
					<select class="custom-select mr-sm-2" id="inlineFormCustomSelect">
						<option value="song" selected>노래</option>
						<option value="artist">가수</option>
						<option value="album">앨범</option>
					</select>
				</div>
				<input type="text" class="form-control search_bar">
				<div class="col-auto my-1">
					<button type="submit" class="btn btn-primary">&nbsp;검색&nbsp;</button>
				</div>
			</div>
			
			<div class="items-consertrec">
				<div class="consertrec">
					<h1 style ="text-align: center;" class="card-title">맞춤 콘서트 추천</h5>
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
									<img src="./images/consert1.jpg" class="img-fluid" alt="..."
										style="display:block;width:60%;height:auto;margin-left:auto;margin-right:auto;">
								</div>
								<div class="carousel-item">
									<img src="./images/consert2.jpg" class="img-fluid" alt="..."
										style="display:block;width:60%;height:auto;margin-left:auto;margin-right:auto;">
								</div>
								<div class="carousel-item">
									<img src="./images/consert3.jpg" class="img-fluid" alt="..."
										style="display:block;width:60%;height:auto;margin-left:auto;margin-right:auto;">
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
    						<a href="#" class="btn btn-primary" >추천 페이지 이동</a>
  						</div>
					</div>
				</div>
			</div>

			<div class="items-songrec-songchart">
				<div class="songrec">
					<div class="card">
							<img src="./images/song.jpg" class="song-img-top" alt="...">  						
							<div class="card-body">
    						<h5 class="card-title">노래 추천</h5>
    						<a href="#" class="btn btn-primary">추천 페이지 이동</a>
  						</div>
					</div>
				</div>
				<div class="songchart">
					<div class="list-group">
						<a class="list-group-item list-group-item-action active" aria-current="true" disabled> TOP10 </a> 
						<a href="" class="list-group-item list-group-item-action">노래1</a> 
						<a href="" class="list-group-item list-group-item-action">노래2</a> 
						<a href="" class="list-group-item list-group-item-action">노래3</a>
						<a href="" class="list-group-item list-group-item-action">노래4</a>
						<a href="" class="list-group-item list-group-item-action">노래5</a>
						<a href="" class="list-group-item list-group-item-action">노래6</a>
						<a href="" class="list-group-item list-group-item-action">노래7</a>
						<a href="" class="list-group-item list-group-item-action">노래8</a>
						<a href="" class="list-group-item list-group-item-action">노래9</a>
						<a href="" class="list-group-item list-group-item-action">노래10</a>
					</div>
				</div>
			</div>
		</form>

		<div class="flex-container"></div>
	</section>

	<!-- 하단 부분 -->
	<c:import url="/WEB-INF/views/include/bottom.jsp" />
</body>
</html>
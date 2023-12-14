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
<!-- flickity -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flickity/2.2.2/flickity.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/flickity/2.2.2/flickity.pkgd.min.js"></script>
<!-- css -->
<link rel="stylesheet" href="${root}style/any_info.css">
</head>
<body>

	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	
	<section style="margin-top: 120px;">
		<div class="main_info"  style="border: 1px solid #EFEFEF">
			<div class="list_group">
				<!-- 썸네일 -->
				<div id="thumbnail">
					<img src="${root}images/thumbnail/artist/${artistDto.artist_thumbnail}" class="thumbnail"/>
				</div>
				<!-- 아티스트 정보 -->
				<div>
					<div id="song_info">
						<div class="song_name"> ${artistDto.artist_name}</div>
						<div>
							<div class="align_left member">
								<span>
									${artistDto.member}
								</span>
							</div>
						</div>
					</div>

					<div id="more_info">
						<table style="text-align: left;">
							<tr>
								<td>데뷔일</td>
								<td class="td_padding">${artistDto.artist_date}</td>
							</tr>
							<tr>
								<td>유형</td>
								<td class="td_padding">${artistDto.artist_type}</td>
							</tr>
							<tr>
								<td>소속사</td>
								<td class="td_padding">${artistDto.artist_agency}</td>
							</tr>
							<tr>
								<td>국적</td>
								<td class="td_padding">${artistDto.artist_nation}</td>
							</tr>
						</table>
						<div class="thumbup">
							<button id="thumbupBtn" user_num="${loginUserBean.user_num}" artist_id="${artistDto.artist_id}" infoType="artist">
								
							</button>
							<span id="thumbupCount">${artistDto.artist_thumbup}</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 앨범? 노래? -->
		<div>
			<div style="text-align: center; margin-top : 40px;">
				<p> <h5>앨범</h5> </p>
				<hr style="width: 50%"/>
			</div>
			<div class="main-carousel" style="margin: 10px 25%; text-align: center; width: 50%;">
				<!-- 슬라이드들 -->
				<c:forEach var="item" items="${albumList}">
					<div class="carousel-cell-artist other_song">
						<div class="carousel-cell-item-artist">
							<div class="card" style="width: 200;">
								<a href="${root}search/album_info?album_id=${item.album_id}" title="${item.album_name} - 앨범 정보">
									<img class="mini-thumbnail"
										src="${root}images/thumbnail/album/${item.album_thumbnail}"
										class="card-img-top" alt="${item.album_name}">
								</a>
								<div class="card-body">
									<h5 class="card-title ellipsis">
										<a href="${root}search/album_info?album_id=${item.album_id}" title="${item.album_name}">
											${item.album_name}
										</a>
									</h5>
									<p class="card-text ellipsis">${item.artist_name}</p>
									<p class="card-text ellipsis">${item.release_date}</p>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>

	<c:import url="/WEB-INF/views/include/bottom.jsp" />
</body>
<script>
	var $carousel = new Flickity('.main-carousel',{
		cellAlign: 'left',
	    contain: true,
	    pageDots: true,
	    wrapAround: true,
	    prevNextButtons: true,
	    freeScroll: false,
	    draggable: false,
	    groupCells: 4
	});

	var id = '';
	var user_num = '';
	var infoType = '';
	var thumbupBtn = '';
	
	$(document).ready(function(){
		thumbupBtn = document.querySelector('#thumbupBtn');
		id = thumbupBtn.getAttribute('artist_id');
		user_num = thumbupBtn.getAttribute('user_num');
		infoType = thumbupBtn.getAttribute('infoType');
		
		if(user_num != 0){
			$.ajax({
				url : '${root}getThumbup',
				type : 'GET',
				data : {
					id: id,
					user_num: user_num,
					infoType: infoType
				},
				success: function(result){
					var icon = '<img src="${root}images/thumbup/'
						+	result + '"/>';
					$('#thumbupBtn').html(icon)
				},
				error: function(){
					alert('실패');
				}
			});
		} else {
			var icon = '<img src="${root}images/thumbup/heart-regula.png"/>';
			$('#thumbupBtn').html(icon)
		}
		
		thumbupBtn.addEventListener('click', function(){
			if(user_num == 0){
				alert('로그인 후 이용해주세요.');
				location.href='${root}user/login';
			} else {
				$.ajax({
					url : '${root}thumbup',
					type : 'GET',
					dataType : 'json',
					data : {
						id: id,
						user_num: user_num,
						infoType: infoType
					},
					success: function(result){
						var icon = '<img src="${root}images/thumbup/'
								+	result.icon + '"/>';
						var thumbupCount = result.thumbupCount;
						
						$("#thumbupBtn").html(icon);
						$("#thumbupCount").html(thumbupCount);
					}
				});
			}
		});
	});
</script>
</html>
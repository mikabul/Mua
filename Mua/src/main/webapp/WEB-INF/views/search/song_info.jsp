<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<%
request.setCharacterEncoding("utf-8");
%>
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
<style>

</style>
</head>
<body>

	<c:import url="/WEB-INF/views/include/top_menu.jsp" />

	<section style="margin-top: 120px;">
		<div class="main_info">
			<div class="list_group">
				<!-- 썸네일 -->
				<div id="thumbnail">
					<img src="${root}images/thumbnail/song/${infoSongDto.song_thumbnail}" />
				</div>
				<!-- 곡 정보 -->
				<div>
					<div id="song_info">
						<div class="song_name"> ${infoSongDto.song_name}</div>
						<div class="list_group">
							<div class="align_left" id="artist">
								<span><a href="${root}search/artist_info?artist_id=${artistList[0].artist_id}">${artistList[0].artist_name}</a></span>
								<c:if test="${fn:length(artistList) > 1}">
									<c:forEach var="i" begin="1" end="${fn:length(artistList) - 1}">
										<span class="artist">, <a href="${root}search/artist_info?artist_id=${artistList[i].artist_id}">${artistList[i].artist_name}</a></span>
									</c:forEach>
								</c:if>
							</div>
							<div id="dropdown"></div>
						</div>
					</div>

					<div id="more_info">
						<table style="text-align: left;">
							<tr>
								<td>앨범</td>
								<td class="td_padding">
									<a href="${root}search/album_info?album_id=${infoSongDto.album_id}">${infoSongDto.album_name}</a>
								</td>
							</tr>
							<tr>
								<td>발매일</td>
								<td class="td_padding">${infoSongDto.release_date}</td>
							</tr>
							<tr>
								<td>장르</td>
								<td class="td_padding">${infoSongDto.song_genre}</td>
							</tr>
						</table>
						<div style="margin-top: 30px;">
							<button id="thumbupBtn" user_num="${loginUserBean.user_num}" song_id="${infoSongDto.song_id}" infoType="song">
								
							</button>
							<span id="thumbupCount">${infoSongDto.song_thumbup}</span>
						</div>
					</div>
				</div>

			</div>
			<!-- 가사 부분 -->
			<div style="margin-top: 20px;">
				<div class="lyric" id="lyric"></div>
				<div>
					<div id="lyricBtn">
						<button id="lyricOn">▽펼치기</button>
					</div>
				</div>
			</div>
			<div class="main-carousel" style="margin-top: 10px; text-align: center;">
				<!-- 슬라이드들 -->
				<c:forEach var="item" items="${songList}">
					<div class="carousel-cell other_song" href="${item.song_id}">
						<div class="carousel-cell-item">
							<div class="card" style="width: 200;">
								<img class="mini-thumbnail"
									src="${root}images/thumbnail/song/${item.song_thumbnail}"
									class="card-img-top" alt="${item.song_name}">
								<div class="card-body">
									<h5 class="card-title ellipsis">${item.song_name}</h5>
									<p class="card-text ellipsis">${item.artist_name}</p>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<!-- 리뷰 -->
		
	</section>

	<c:import url="/WEB-INF/views/include/bottom.jsp" />
	
<script>
	
	var on = true;
	// 가사를 불러옴
	if (${infoSongDto.lyrics != '-'}) {
		
		$.ajax({
		    type: 'GET',
		    url: "${root}getLyric",
		    data: {
		        file_name: '${infoSongDto.lyrics}'
		    },
		    success: function(res){
		    	// 가사
		    	var htmlLyric = '<div>';
		    	
		    	htmlLyric += '가사 <br><hr>';
		    	htmlLyric += '</div>';
		    	htmlLyric += res;
		        $("#lyric").html(htmlLyric);
		    }
		});
	
	} else{
		$("#lyric").html('<div>가사가 존재하지 않습니다.</div>');
		$("#lyric").addClass(' none-lyric');
		$("#lyricOn").addClass(' none');
	}
	
	// 가사, 펼치기 접기
	$('#lyricOn').click(function(){
		if(on){
			on = false;
			$('#lyric').addClass('on');
			$('#lyricOn').text('△접기');
		} else {
			on = true;
			$('#lyric').removeClass('on');
			$('#lyricOn').text('▽펼치기');
		}
	});
	
	var $carousel = new Flickity('.main-carousel',{
		cellAlign: 'center',
	    contain: false,
	    pageDots: false,
	    wrapAround: true,
	    prevNextButtons: false,
	    freeScroll: true
	});
	
	// 다른 노래 클릭시 내용 전환
	$('div.other_song').on('dblclick', function(event){
		event.preventDefault();
		
		var href = $(this).attr('href');
		
		$.ajax({
			url: '${root}changeSong_info',
			type: 'GET',
			data:{
				song_id: href
			},
			success: function(data){
				var thumbnail_name = data.infoSongDto.song_thumbnail;

				$('#lyricOn').removeClass('none');
				$("#lyric").removeClass('none-lyric');
				
				var thumbnail = '<img src="${root}images/thumbnail/song/';
				thumbnail += thumbnail_name + '" />';
				
				var infoSongDto = data.infoSongDto;
				var artistList = data.artistList;
				var lyric = data.lyric;
				// 노래 이름
				var songInfoHTML = '<div class="song_name">'
								+ infoSongDto.song_name
								+ '</div>';
				// 아티스트 이름
				songInfoHTML += '<div class="align_left"><span>'
							+ '<a href="${root}search/artist_info?artsit_id='
							+ artistList[0].artist_id + '">'
							+ artistList[0].artist_name + '</a></span>';
				
				if(artistList.length > 1){
					for(var i = 1; i < artistList.length; i++){
						songInfoHTML += '<span>, <a href="${root}search/artist_info?artist_id='
										+ artistList[i].artist_id + '">'
										+ artistList[i].artist_name + '</a></span>';
					}
				}
				
				songInfoHTML += '</div>';
				
				// 앨범이름, 발매일, 장르
				var moreInfoHTML = '<table class="align_left">'
									// 앨범이름
									+ '<tr><td>앨범</td><td>'
									+ '<a href="${root}search/album_info?album_id='
									+ infoSongDto.album_id + '">'
									+ infoSongDto.album_name + '</a></td></tr>'
									// 발매일
									+ '<tr><td>발매일</td><td>'
									+ infoSongDto.release_date + '</td></tr>'
									// 장르
									+ '<tr><td>장르</td><td>'
									+ infoSongDto.song_genre + '</td></tr>';
									
				$('#thumbnail').html(thumbnail);
				$('#song_info').html(songInfoHTML);
				$('#more_info').html(moreInfoHTML);
				
				// 가사
				if(lyric != "-"){
					var lyricHTML = '<div>'
    					+ '가사 <br><hr>'
    					+ '</div>'
    					+ lyric;
			
					$("#lyric").html(lyricHTML);
					
				} else {
					$("#lyric").html('<div>가사가 존재하지 않습니다.</div>');
					$("#lyric").addClass(' none-lyric');
					$("#lyricOn").addClass(' none');
				}
				
		        // 가사가 펼쳐져 있다면 접기
		        if(!on){
		        	on = true;
					$('#lyric').removeClass('on');
					$('#lyricOn').text('▽펼치기');
				}
			},
			error: function(){
				alert('실패');
			}
		}); //ajax_END
	});
	
	// 가수 길이에 따라 버튼 추가
	var artist = "";
	var artistList = JSON.parse('${artistListJson}');
	for(var i = 0; i < artistList.length ; i++){
		artist += artistList[i].artist_name;
	}

	if(artist.length > 35){
		$('#artist').addClass('ellipsis');
		
		var dropdown = '<span class="btn-group dropright">'
			+ '<button class="btn btn-outline-dark btn-sm dropdown-toggle" style="border: none" type="button" data-toggle="dropdown" aria-expanded="false">'
			+ ''
			+ '</button><div class="dropdown-menu">';
		
		for(var i = 0; i < artistList.length ; i++){
			dropdown += '<a class="dropdown-item" '
				+ 'href="${root}search/artist_info?artist_id='
				+ artistList[i].artist_id + '" style="font-size: 14px">'
				+ artistList[i].artist_name
				+ '</a>';
		}
		
		dropdown += '</div></span>';
		$('#dropdown').html(dropdown);
	}
	
	var id = '';
	var user_num = '';
	var infoType = '';
	var thumbupBtn = '';
	
	$(document).ready(function(){
		thumbupBtn = document.querySelector('#thumbupBtn');
		id = thumbupBtn.getAttribute('song_id');
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

</body>
</html>
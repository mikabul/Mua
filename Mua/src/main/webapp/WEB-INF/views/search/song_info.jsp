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
<title>Insert title here</title>
<!-- 부트 스트랩 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
<!-- ajax -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<!-- flickity -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flickity/2.2.2/flickity.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/flickity/2.2.2/flickity.pkgd.min.js"></script>
<!-- css -->
<link rel="stylesheet" href="${root}style/song_info.css">
<script>

var on = true;

$(document).ready(function(){
	
	// 가사를 불러옴
	if (${infoSongDto.lyrics != '-'}) {
		
		$.ajax({
		    type: 'GET', // 'get'이 아닌 'GET'으로 수정
		    url: "${root}getLyric",
		    data: {
		        file_name: '${infoSongDto.lyrics}'
		    },
		    success: function(res){
		    	var htmlLyric = '<div>';
		    	
		    	htmlLyric += '가사 <br><hr>';
		    	htmlLyric += '</div>';
		    	htmlLyric += res;
		        $("#lyric").html(htmlLyric);
		    },
		    error: function(){
		        alert('실패');
		    }
		});
	
	} else{
		$("#lyric").html('<div>가사가 존재하지 않습니다.</div>');
	}
	
	// 가사, 펼치기 접기
	$('#lyricOn').click(function(){
		if(on){
			on = false;
			$('#lyric').addClass('on');
			$('#lyricOn').text('접기');
		} else {
			on = true;
			$('#lyric').removeClass('on');
			$('#lyricOn').text('펼치기');
		}
	});
	
	// flickity
	var $carousel = $('.main-carousel').flickity({
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
				
				// 가사
				var lyricHTML = '<div>'
		    					+ '가사 <br><hr>'
		    					+ '</div>'
		    					+ lyric;
					
				$('#thumbnail').html(thumbnail);
				$('#song_info').html(songInfoHTML);
				$('#more_info').html(moreInfoHTML);
		        $("#lyric").html(lyricHTML);
		        
		        // 가사가 펼쳐져 있다면 접기
		        if(!on){
		        	on = true;
					$('#lyric').removeClass('on');
					$('#lyricOn').text('펼치기');
				}
			},
			error: function(){
				alert('실패');
			}
		}); //ajax_END
	});
});
	
</script>
<style>
.list_group {
	display: flex;
	justify-content: center;
	text-align: center;
	padding: 0;
}
</style>
</head>
<body>

	<c:import url="/WEB-INF/views/include/top_menu.jsp" />

	<section style="margin-top: 120px;">
		<div class="main_info">
			<div class="list_group" style="border: 1px solid black">
				<!-- 썸네일 -->
				<div id="thumbnail">
					<img src="${root}images/thumbnail/song/${infoSongDto.song_thumbnail}" />
				</div>
				<!-- 공백 -->
				<div style="width: 30px;"></div>
				<!-- 곡 정보 -->
				<div>
					<div id="song_info" style="margin-top: 5px;">
						<div class="song_name"> ${infoSongDto.song_name}</div>
						<div class="align_left">
							<span><a href="${root}search/artist_info?artist_id=${artistList[0].artist_id}">${artistList[0].artist_name}</a></span>
							<c:if test="${fn:length(artistList) > 1}">
								<c:forEach var="i" begin="1" end="${fn:length(artistList) - 1}">
									<span>, <a href="${root}search/artist_info?artist_id=${artistList[i].artist_id}">${artistList[i].artist_name}</a></span>
								</c:forEach>
							</c:if>
						</div>
					</div>

					<div id="more_info">
						<table style="text-align: left;">
							<tr>
								<td>앨범</td>
								<td>${infoSongDto.album_name}</td>
							</tr>
							<tr>
								<td>발매일</td>
								<td>${infoSongDto.release_date}</td>
							</tr>
							<tr>
								<td>장르</td>
								<td>${infoSongDto.song_genre}</td>
							</tr>
						</table>
					</div>
				</div>

			</div>
			<!-- 가사 부분 -->
			<div>
				<div class="lyric" id="lyric"></div>
				<div style="text-align: center;">
					<button id="lyricOn">펼치기</button>
				</div>
			</div>
			<div class="main-carousel">
				<!-- 슬라이드들 -->
				<c:forEach var="item" items="${songList}">
					<div class="carousel-cell other_song" href="${item.song_id}">
						<div class="carousel-cell-item">
							<div style="text-align: center; margin-top: 15px;">
								<img src="${root}images/thumbnail/song/${item.song_thumbnail}" style="width: 80px; height: 80px;"/>
							</div>
							<div>
								<p class="ellipsis">${item.song_name}</p>
								<p class="ellipsis">${item.artist_name}</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>

	<c:import url="/WEB-INF/views/include/bottom.jsp" />

</body>
</html>
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
					<img src="${root}images/thumbnail/album/${albumDto.album_thumbnail}" class="thumbnail"/>
				</div>
				<!-- 아티스트 정보 -->
				<div>
					<div id="song_info">
						<div class="song_name"> ${albumDto.album_name}</div>
						<div class="list_group">
							<div class="align_left" id="artist">
								<c:choose>
									<c:when test="${artistList[0].artist_name == 'Various Artists'}">
										<span>${artistList[0].artist_name}</span>
									</c:when>
									<c:otherwise>
										<span>
											<a href="${root}search/artist_info?artist_id=${artistList[0].artist_id}">
												${artistList[0].artist_name}
											</a>
										</span>
										<c:if test="${fn:length(artistList) > 1}">
											<c:forEach var="i" begin="1" end="${fn:length(artistList) - 1}">
												<span>, <a href="${root}search/artist_info?artist_id=${artistList[i].artist_id}">
													${artistList[i].artist_name}
												</a>
												</span>
											</c:forEach>
										</c:if>
									</c:otherwise>
								</c:choose>
							</div>
							<div id="dropdown"></div>
						</div>
					</div>

					<div id="more_info">
						<table style="text-align: left;">
							<tr>
								<td>발매일</td>
								<td class="td_padding">${albumDto.release_date}</td>
							</tr>
							<tr>
								<td>장르</td>
								<td class="td_padding">${albumDto.album_genre}</td>
							</tr>
							<tr>
								<td>발매사</td>
								<td class="td_padding">${albumDto.album_publisher}</td>
							</tr>
							<tr>
								<td>기획사</td>
								<td class="td_padding">${albumDto.album_agency}</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!-- 노래 -->
		<div class="main_info" style="margin-top: 10px;">
			<p>노래</p>
			<hr/>
		</div>
		<div class="main_info" style="margin-top: 10px;">
			<div>
				<table style="margin: 0 auto; width: 100%;">
					<colgroup>
						<!-- 번호 -->
						<col style="width: 10%"/>
						<!-- 노래이름 -->
						<col style="width: 30%"/>
						<!-- 아티스트 이름 -->
						<col style="width: 30%"/>
						<!-- 앨범 이름 -->
						<col style="width: 30%"/>
					</colgroup>
					<thead>
						<tr>
							<th scope="col">번호</th>
							<th scope="col">노래</th>
							<th scope="col">아티스트</th>
							<th scope="col">앨범</th>
						</tr>
					</thead>
					<tbody id="songList">
						<c:set var="num" value="1" />
						<c:forEach var="item" items="${searchResultList}">
							<tr class="line">
								<td class="top_bottom rank">${num}</td>
								<c:set var="num" value="${num + 1}" />
								<td class="top_bottom">
									<a href="${root}search/song_info?song_id=${item.songDto.song_id}">
										${item.songDto.song_name}
									</a>
								</td>
								<td class="top_bottom ellipsis">
									<c:choose>
										<c:when test="${item.artistList[0].artist_name == 'Various Artists'}">
											<span>${item.artistList[0].artist_name}</span>
										</c:when>
										<c:otherwise>
											<span>
												<a href="${root}search/artist_info?artist_id=${item.artistList[0].artist_id}">
													${item.artistList[0].artist_name}
												</a>
											</span>
											<c:if test="${fn:length(item.artistList) > 1}">
												<c:forEach var="i" begin="1" end="${fn:length(item.artistList) - 1}">
													<span>, </span>
													<span>
														<a href="${root}search/artist_info?artist_id=${item.artistList[i].artist_id}">
															${item.artistList[i].artist_name}
														</a>
													</span>
												</c:forEach>
											</c:if>
										</c:otherwise>
									</c:choose>
								</td>
								<td class="top_bottom">
									${item.songDto.album_name}
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="container" style="margin-top: 15px;">
					<div class="row justify-content-center">
						<div class="btn-group mr-2" role="group" aria-label="Second group">
							<div>
								<c:set var="pageIndex" value="${maxView}" />
								<c:forEach var="item" items="${loadPage}">
									<button type="button" class="btn btn-secondary pageButton" 
										index="${pageIndex * (item - 1) + 1}" maxView="${maxView * item}" album_id="${albumDto.album_id}">
										${item}
									</button>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<c:import url="/WEB-INF/views/include/bottom.jsp" />
</body>
<script>
	var buttons = document.querySelectorAll('.pageButton');
	
	buttons.forEach(button =>{
		button.addEventListener('click', function(event){
			var index = parseInt(this.getAttribute('index'), 10);
			var maxView = this.getAttribute('maxView');
			var album_id = this.getAttribute('album_id');
			
			$.ajax({
				url: "${root}/changeSongList",
				type: "GET",
				dataType: "json",
				data:{
					index: index,
					maxView: maxView,
					album_id: album_id
				},
				success: function(result){
					var songList = '';
					
					for(var i = 0; i < result.length; i++){
						// 번호와 노래
						songList += '<tr class="line"><td class="top_bottom rank">' + (i+index) + '</td><td class="top_bottom">'
								+ 	'<a href="${root}search/song_info?song_id=' + result[i].songDto.song_id
								+ 	'" title="' + result[i].songDto.song_name + '">'
								+ 	result[i].songDto.song_name + "</a></td>";
						// 아티스트
						songList += '<td class="top_bottom ellipsis">';
						if(result[i].artistList[0].artist_name == 'Various Artists'){
							songList += '<span>Various Artists<span>';
						} else {
							songList += '<span><a href="${root}/artist_info?artist_id=' + result[i].artistList[0].artist_id + '" >'
									+	result[i].artistList[0].artist_name + '</a><span>';
							if(result[i].artistList.length > 1){
								for(var artist of result[i].artistList){
									songList += '<span>, <a href="${root}/artist_info?artist_id=' + artist.artist_id + '" >'
											+	artist.artist_name + '</a></span>';
								}
							}
						}
						songList += '</td>';
						
						//앨범
						songList += '<td class="top_bottom">' + result[i].songDto.album_name + '</td></tr>';
					}
					
					$('#songList').html(songList);
				}
			});
		});
	});
	
	// 가수 길이에 따라 버튼 추가
	var artist = "";
	var artistList = JSON.parse('${artistListJson}');
	for(var i = 0; i < artistList.length ; i++){
		artist += artistList[i].artist_name;
	}

	if(artist.length > 40){
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
</script>
</html>
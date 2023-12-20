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
<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- 부트 스트랩 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
<!-- flickity -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flickity/2.2.2/flickity.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/flickity/2.2.2/flickity.pkgd.min.js"></script>
<!-- css -->
<link rel="stylesheet" href="${root}style/any_info.css">
<!-- 별점 -->
<style>
@import url(//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css);
       .rate { display: inline-block;border: 0;margin-right: 15px;}
.rate > input {display: none;}
.rate > label {float: right;color: #ddd}
.rate > label:before {display: inline-block;font-size: 1rem;padding: .3rem .2rem;margin: 0;cursor: pointer;font-family: FontAwesome;content: "\f005 ";}
.rate .half:before {content: "\f089 "; position: absolute;padding-right: 0;}
.rate input:checked ~ label, 
.rate label:hover,.rate label:hover ~ label { color: #f73c32 !important;  } 
.rate input:checked + .rate label:hover,
.rate input input:checked ~ label:hover,
.rate input:checked ~ .rate label:hover ~ label,  
.rate label:hover ~ input:checked ~ label { color: #f73c32 !important;  } 
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
							<button id="thumbupBtn">
								
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
		<div class="container">
			<div>
				<table style="margin: 20px auto;">
					<colgroup>
						<!-- 리뷰번호 -->
						<col style="width: 100px;"/>
						<!-- 작성자 -->
						<col style="width: 200px;" />
						<!-- 리뷰내용 -->
						<col style="width: 330px;" />
						<!-- 별점 -->
						<col style="width: 80px;" />
						<!-- 작성일 -->
						<col style="width: 50px;" />
						<!-- 신고 -->
						<col style="width: 130px;" />
					</colgroup>
					<thead>
						<tr class="line">
							<th scope="col" class="rank">번호</th>
							<th scope="col" class="rank">작성자</th>
							<th scope="col" class="rank">리뷰</th>
							<th scope="col" class="rank">별점</th>
							<th scope="col" class="rank">작성일</th>
							<th scope="col"><span class="none">신고</span></th>
						</tr>
					</thead>
					<tbody id="review">
						
					</tbody>
				</table>
			</div>
		</div>
		<div class="container">
			<div>
				<div class="row justify-content-center">
			        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
			        	<div id="preBtn"></div>
			        	<div id="reviewPageBtn"></div>
			        	<div id="postBtn"></div>
			        </div>
			    </div>
			</div>
		</div>
		<div class="text-center" style="margin-top: 13px;">
			<hr />
		</div>
		<div class="container" style="margin-top: 13px;width: 50%">
			<form:form modelAttribute="userReviewDto" action="">
				<form:hidden path="review_num"/>
				<form:hidden path="user_num" value="${loginUserBean.user_num}"/>
				<form:hidden path="type_id" value="${infoSongDto.song_id}"/>
				<form:hidden path="flag" value="song"/>
				<form:hidden path="suggestion" value="0"/>
				<div class="text-center">
					<fieldset class="rate">
						<input type="radio" class="review_point" id="rating10" name="review_point" value="5.0"><label for="rating10" title="5점"></label>
						<input type="radio" class="review_point" id="rating9" name="review_point" value="4.5"><label class="half" for="rating9" title="4.5점"></label>
						<input type="radio" class="review_point" id="rating8" name="review_point" value="4.0"><label for="rating8" title="4점"></label>
						<input type="radio" class="review_point" id="rating7" name="review_point" value="3.5"><label class="half" for="rating7" title="3.5점"></label>
						<input type="radio" class="review_point" id="rating6" name="review_point" value="3.0"><label for="rating6" title="3점"></label>
						<input type="radio" class="review_point" id="rating5" name="review_point" value="2.5"><label class="half" for="rating5" title="2.5점"></label>
						<input type="radio" class="review_point" id="rating4" name="review_point" value="2.0"><label for="rating4" title="2점"></label>
						<input type="radio" class="review_point" id="rating3" name="review_point" value="1.5"><label class="half" for="rating3" title="1.5점"></label>
						<input type="radio" class="review_point" id="rating2" name="review_point" value="1.0"><label for="rating2" title="1점"></label>
						<input type="radio" class="review_point" id="rating1" name="review_point" value="0.5"><label class="half" for="rating1" title="0.5점"></label>
					</fieldset>
				</div>
				<div class="form-group row justify-content-center">
					<div style="width: 470px;">
						<form:textarea path="review_content" class="form-control" 
						placeholder="내용을 입력해주세요..." required="true" rows="4" wrap="on" />
						<div id="submitBtn" class="text-center" style="margin-top: 13px;"></div>
					</div>
				</div>
			</form:form>
		</div>
	</section>
	<div class="none" id="hiddenValue" flag="song" type_id="${infoSongDto.song_id}"></div>
	
	<c:import url="/WEB-INF/views/include/bottom.jsp" />

<script>
var on = true;
// 가사를 불러옴
if (${infoSongDto.lyrics != '-'}) {
	
	fetch('${root}getLyric?file_name=${infoSongDto.lyrics}',
			{
				method: 'GET'
			})
    .then(response => {
        if (response.ok) {
            return response.text();
        }
        throw new Error('네트워크 응답이 문제가 있습니다.');
    })
    .then(res => {
        // 가사 처리
        let htmlLyric = '<div>';
        htmlLyric += '가사 <br><hr>';
        htmlLyric += '</div>';
        htmlLyric += res;

        // HTML 업데이트
        document.getElementById('lyric').innerHTML = htmlLyric;
    })
    .catch(error => {
        console.error('Fetch 작업에 문제가 있습니다:', error);
        // 여기서 에러를 처리합니다
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
	
	fetch('${root}changeSong_info?song_id=' + href,
			{
				method: 'GET'
			})
	.then(response => {
		if(response.ok){
			return response.json();
		}
	})
    .then(data => {
            // 성공 시 현재 URL 가져오기
            const currentUrl = window.location.href;

            // URL에서 song_id 값 변경
            const newSongId = href; // 변경할 ID
            const updatedUrl = currentUrl.replace(/(song_id=)\d+/, '$1' + newSongId);

            // URL 변경 (새로운 페이지 로드)
            window.history.replaceState(null, null, updatedUrl);
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
			// 아티스트
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
			
			moreInfoHTML += '<div style="margin-top: 30px;">'
						+ 	'<button id="thumbupBtn"></button>'
						+ 	'<span id="thumbupCount">${infoSongDto.song_thumbup}</span>'
						+ 	'</div>';
			
			$('#thumbnail').html(thumbnail);
			$('#song_info').html(songInfoHTML);
			$('#more_info').html(moreInfoHTML);
			getThumbup();
			
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
        
    })
    .catch(error => {
        console.error('Fetch 작업에 문제가 있습니다:', error);
        alert('실패');
        // 여기서 에러를 처리합니다
    });
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

//=========== 좋아요 ===========
var id = '';
var user_num = '';
var infoType = '';
var thumbupBtn = '';
var isLogin = ${loginUserBean.userLogin};

function getThumbup(){
	thumbupBtn = document.querySelector('#thumbupBtn');
	id = ${infoSongDto.song_id};
	user_num = ${loginUserBean.user_num};
	infoType = 'song';
	
	if(isLogin){
		fetch('${root}getThumbup?id=' + id + '&user_num=' + user_num + '&infoType=' + infoType,
				{
					method: 'GET'
				})
	    .then(response => {
	        if (response.ok) {
	            return response.text();
	        }
	        throw new Error('네트워크 응답이 문제가 있습니다.');
	    })
	    .then(result => {
	        // 결과 처리
	        const icon = '<img src="${root}images/thumbup/' + result +'"/>';

	        // DOM 업데이트
	        document.getElementById('thumbupBtn').innerHTML = icon;
	    })
	    .catch(error => {
	        console.error('Fetch 작업에 문제가 있습니다:', error);
	        alert('실패');
	        // 여기서 에러를 처리합니다
	    });
	} else {
		var icon = '<img src="${root}images/thumbup/heart-regula.png"/>';
		$('#thumbupBtn').html(icon)
	}
	
	thumbupBtn.addEventListener('click', function(){
		if(!isLogin){
			alert('로그인 후 이용해주세요.');
			location.href='${root}user/login';
		} else {
			fetch('${root}thumbup?id=' + id + '&user_num=' + user_num + '&infoType=' + infoType, 
					{
				 		method: 'GET',
				   		headers: {
				        'Content-Type': 'application/json'
				    	}
					})
		    .then(response => {
		        if (response.ok) {
		            return response.json();
		        }
		        throw new Error('네트워크 응답이 문제가 있습니다.');
		    })
		    .then(result => {
		        // 결과 처리
		        const icon = '<img src="${root}images/thumbup/' + result.icon + '"/>';
		        const thumbupCount = result.thumbupCount;

		        // DOM 업데이트
		        document.getElementById('thumbupBtn').innerHTML = icon;
		        document.getElementById('thumbupCount').innerHTML = thumbupCount;
		    })
		    .catch(error => {
		        console.error('Fetch 작업에 문제가 있습니다:', error);
		        // 여기서 에러를 처리합니다
		    });
		}
	});
};
getThumbup();

//============ 리뷰 ============
var type_id = '';
var flag = '';

$(document).ready(function(){
	
	thumbupBtn = document.querySelector('#hiddenValue');
	type_id = thumbupBtn.getAttribute('type_id');
	flag = thumbupBtn.getAttribute('flag');
	console.log('type_id : ' + type_id);
	console.log('flag : ' + flag);
	
		
	fetch('${root}getFirstReview?type_id=' + type_id + '&flag=' + flag, {
		method: 'GET'
	})
    .then(response => {
        if (response.ok) {
            return response.json();
        }
        throw new Error('네트워크 응답이 문제가 있습니다.');
    })
    .then(result => {
        // 결과 처리 - getReviewList 함수 호출
        getReviewList(result);

        // 인덱스 추출 및 btnAddEvent 함수 호출
        const index = parseInt(result.index, 10);
        btnAddEvent(index);
    })
    .catch(error => {
        console.error('Fetch 작업에 문제가 있습니다:', error);
        // 여기서 에러를 처리합니다
    });
});
	
	function getReviewList(result){
		var review = "";
		var reviewPageBtns = '';
		
		var reviewList = result.reviewList;
		var loadPage = result.loadPage;
		var page = parseInt(result.page, 10);
		var index = parseInt(result.index, 10);
		var maxView = parseInt(result.maxView, 10);
		var maxPage = parseInt(result.maxPage, 10);
		
		for(var i = 0; i < reviewList.length; i++){
			review += '<tr class="line">'
					+ '<td class="top_bottom rank">' + (index+i) + '</td>'
					+ '<td class="top_bottom rank">' + reviewList[i].user_name + '</td>'
					+ '<td class="top_bottom">' + reviewList[i].review_content + '</td>'
					+ '<td class="top_bottom rank">' + reviewList[i].review_point + '</td>'
					+ '<td class="top_bottom rank">' + reviewList[i].review_date + '</td>'
					+ '<td class="rank"><button type="button" class="declaration btn btn-outline-danger btn-sm reportBtn" '
					+ 'review_num="' + reviewList[i].review_num + '" report_user_num="' + reviewList[i].user_num
					+ '"> 신고 </button></td>'
					+ '</tr>';
		}
		$("#review").html(review);
		getPrePostBtn(page, maxPage, index, maxView);
		getReviewBtn(loadPage, index, maxView);
	};
	
	function getPrePostBtn(page, maxPage, index, maxView){
		
		var preBtn = '<div class="btn-group mr-2" role="group" aria-label="First group">';
		
		if(page <= 10){
			preBtn += '<button type="button" class="btn btn-secondary disabled">10개</button>';
		} else {
			preBtn += '<button type="button" class="btn btn-secondary pageBtn" '
					+ 'index="' + (index - (maxView * 10)) + '">10개</button>';
		}
		
		if(page <= 1){
			preBtn += '<button type="button" class="btn btn-secondary disabled">이전페이지</button>';
		}else {
			preBtn += '<button type="button" class="btn btn-secondary pageBtn" '
					+ 'index="' + (index - maxView) + '">이전페이지</button>';
		}
		
		preBtn += '</div>';
		$('#preBtn').html(preBtn);
		
		var postBtn = '<div class="btn-group mr-2" role="group" aria-label="Third group">';
		
		if(page >= maxPage){
			postBtn += '<button type="button" class="btn btn-secondary disabled">다음페이지</button>';
		}else{
			postBtn += '<button type="button" class="btn btn-secondary pageBtn" '
					+	'index="' + (index + maxView) + '">다음페이지</button>\n';
		}
		
		if(page + 10 > maxPage){
			postBtn += '<button type="button" class="btn btn-secondary disabled">10개</button>';
		}else{
			postBtn += '<button type="button" class="btn btn-secondary pageBtn" '
					+	'index="' + (index + (maxView * 10)) + '">10개</button>';
		}
		postBtn += '</div>';
		
		$('#postBtn').html(postBtn);
	};
	
	function getReviewBtn(loadPage, index, maxView){
		
		var reviewPageBtn = '<div class="btn-group mr-2" role="group" aria-label="Second group">';
		
		for(var i = 0; i < loadPage.length; i++){
			var btnPage = parseInt(loadPage[i], 10);
			reviewPageBtn += '<button type="button" class="btn btn-secondary pageBtn" '
						+ 'index="' + ((btnPage - 1) * maxView + 1) + '">'
						+ btnPage + '</button>';
		}
		reviewPageBtn += '</div>';
		
		$('#reviewPageBtn').html(reviewPageBtn);
	};
	
	function btnAddEvent(index){
		
		var pageBtns = document.querySelectorAll('.pageBtn');
		
		pageBtns.forEach(pageBtn => {
			pageBtn.addEventListener('click', clickHandler);
			var btnIndex = pageBtn.getAttribute('index');
			if(index == btnIndex){
				pageBtn.classList.add('disabled');
			}
		})
		
		var reportBtns = document.querySelectorAll('.reportBtn');
		
		reportBtns.forEach(reportBtn => {
			reportBtn.addEventListener('click', clickReport);
		})
	};
	
	// 리뷰 페이지 버튼 클릭
	function clickHandler(){
		this.removeEventListener('click', clickHandler);
		var index = this.getAttribute('index');
		
		fetch('${root}getPageReview?index=' + index + '&type_id=' + type_id + '&flag=' + flag, {
		    method: 'GET'
		})
		    .then(response => {
		        if (response.ok) {
		            return response.json();
		        }
		        throw new Error('네트워크 응답이 문제가 있습니다.');
		    })
		    .then(result => {
		        // 결과 처리 - getReviewList과 btnAddEvent 함수 호출
		        getReviewList(result);
		        btnAddEvent(index);
		    })
		    .catch(error => {
		        console.error('Fetch 작업에 문제가 있습니다:', error);
		        // 여기서 에러를 처리합니다
		    });
	};
	
	// 리뷰 신고 버튼 클릭
	function clickReport(){
		this.removeEventListener('click', clickReport);
		var review_num = this.getAttribute('review_num');
		var report_user_num = this.getAttribute('report_user_num');
		var user_num = ${loginUserBean.user_num};
		var isLogin = ${loginUserBean.userLogin};
		
		if(isLogin){
			
			// Fetch 요청 보내기
			fetch('${root}reviewReport?review_num=' + review_num + '&report_user_num=' + report_user_num +'&user_num=' + user_num, {
				method: 'POST'
			})
				.then(response => {
			        if (response.ok) {
			            return response.text(); // 혹은 response.json() 등으로 데이터를 추출할 수 있습니다.
			        }
			        throw new Error('네트워크 응답이 문제가 있습니다.');
			    })
				.then(result => {
			    	console.log('result : ' + result);
			        if (result == 'true') {
			            alert('신고가 완료되었습니다.');
			        } else {
			            alert('이미 신고된 리뷰입니다.');
			        }
			    })
			    .catch(error => {
			        console.error('Fetch 작업에 문제가 있습니다:', error);
			        // 여기서 에러를 처리합니다
			    });
		} else {
			alert('로그인이 필요한 서비스 입니다.');
			location.href='${root}user/login';
		}
	}
//================ 리뷰 등록 수정 ===================
	$(document).ready(function(){
		var review_num = $('#review_num').val();
		var submitBtn = '<button type="submit" class="btn btn-success" >';
		if(review_num == 0 || !isLogin){
			submitBtn += '리뷰 등록';
		} else {
			submitBtn += '리뷰 수정' + '</button><button type="button" class="btn btn-danger" id="deleteReview">'
						+ '리뷰 삭제';
		}
		submitBtn += '</button>';
		$('#submitBtn').html(submitBtn);
		
		var review_point = ${userReviewDto.review_point};
		var reviewRadios = document.querySelectorAll('.review_point');
		reviewRadios.forEach(radio => {
			var radioVal = radio.getAttribute('value');
			if(radioVal == review_point){
				radio.checked = true;
			}
		})
		
		document.getElementById('userReviewDto').onsubmit = function() {
		    var yourCondition = true; // 상황에 따른 조건
		    if (!isLogin) {
		    	this.method = 'POST';
		        this.action = '${root}user/login';
		        alert('로그인이 필요한 서비스입니다.');
		    } else if(review_num == 0){
		    	this.method = 'POST';
		        this.action = '${root}search/insertReview';
		    } else {
		    	this.method = 'POST';
		    	this.action = '${root}search/rewriteReview';
		    }
		};
		
		$('#deleteReview').on('click',function(){
			fetch('${root}reviewDelete?flag=' + flag + '&type_id=' + type_id + '&user_num=' + user_num + '&review_num=' + review_num, {
				  method: 'POST'
				})
				  .then(response => {
				    if (response.ok) {
				      return response.json();
				    } else {
				      throw new Error('Network response was not ok.');
				    }
				  })
				  .then(result => {
						location.reload();
				  })
				  .catch(error => {
				    console.error('There was a problem with the fetch operation:', error);
				    console.log('flag: ' + flag);
				    console.log('type_id: ' + type_id);
				    console.log('user_num: ' + user_num);
				    console.log('user_num: ' + ${loginUserBean.user_num});
				    console.log('review_num: ' + review_num);
				  });
		});
	});
</script>

</body>
</html>
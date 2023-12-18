<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
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
<!-- jquery -->

<link rel="styleSheet" href="${root}style/any_main.css">
</head>
<body>
<<<<<<< HEAD
	<header>
		<c:import url="/WEB-INF/views/include/top_menu.jsp" />	
	</header>
	
	<section style="margin-top: 150px;">
		<!-- 첫번째 항목 -->
		<ul class="nav nav-tabs" id="myTab" role="tablist">
			<li class="nav-item" role="presentation">
				<button class="nav-link fTabs active" id="K-POP-tab" data-toggle="tab" data-target="#K-POP" type="button" role="tab" aria-controls="K-POP" aria-selected="true">국내</button>
			</li>
			<li class="nav-item" role="presentation">
				<button class="nav-link fTabs" id="POP-tab" data-toggle="tab" data-target="#POP" type="button" role="tab" aria-controls="POP" aria-selected="false">해외</button>
			</li>
			<li class="nav-item" role="presentation">
				<button class="nav-link fTabs" id="other-tab" data-toggle="tab" data-target="#other" type="button" role="tab" aria-controls="other" aria-selected="false">그 외</button>
			</li>
		</ul>
		<!-- 첫 번째 항목의 하위 항목 -->
		<div class="tab-content" id="myTabContent">
			<!-- 국내 -->
			<div class="tab-pane fade show active" id="K-POP" role="tabpanel" aria-labelledby="K-POP-tab">
				<ul class="nav nav-tabs" id="myTab" role="tablist">
					<li class="nav-item" role="presentation">
						<button class="nav-link sTabs active" id="ballad-tab" data-toggle="tab" data-target="#ballad" type="button" role="tab" aria-controls="발라드" aria-selected="true">발라드</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link sTabs" id="dance-tab" data-toggle="tab" data-target="#dance" type="button" role="tab" aria-controls="댄스" aria-selected="false">댄스</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link sTabs" id="rap-tab" data-toggle="tab" data-target="#rap" type="button" role="tab" aria-controls="랩/힙합" aria-selected="false">랩</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link sTabs" id="rnb-tab" data-toggle="tab" data-target="#rnb" type="button" role="tab" aria-controls="R&B/Soul" aria-selected="false">R&B</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link sTabs" id="indi-tab" data-toggle="tab" data-target="#indi" type="button" role="tab" aria-controls="인디음악" aria-selected="false">인디</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link sTabs" id="rock-tab" data-toggle="tab" data-target="#rock" type="button" role="tab" aria-controls="록/메탈" aria-selected="false">락</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link sTabs" id="trot-tab" data-toggle="tab" data-target="#trot" type="button" role="tab" aria-controls="트로트" aria-selected="false">트로트</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link sTabs" id="folk-tab" data-toggle="tab" data-target="#folk" type="button" role="tab" aria-controls="포크/블루스" aria-selected="false">포크</button>
					</li>
				</ul>
			</div>
			<!-- 해외 -->
			<div class="tab-pane fade" id="POP" role="tabpanel" aria-labelledby="POP-tab">
				<ul class="nav nav-tabs" id="myTab" role="tablist">
					<li class="nav-item" role="presentation">
						<button class="nav-link sTabs active" id="pop-tab" data-toggle="tab" data-target="#pop" type="button" role="tab" aria-controls="POP" aria-selected="true">팝</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link sTabs" id="rock-tab" data-toggle="tab" data-target="#rock" type="button" role="tab" aria-controls="록/메탈" aria-selected="false">락</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link sTabs" id="elec-tab" data-toggle="tab" data-target="#elec" type="button" role="tab" aria-controls="일렉트로니카" aria-selected="false">일렉</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link sTabs" id="rap-tab" data-toggle="tab" data-target="#rap" type="button" role="tab" aria-controls="랩/힙합" aria-selected="false">랩</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link sTabs" id="rnb-tab" data-toggle="tab" data-target="#rnb" type="button" role="tab" aria-controls="R&B/Soul" aria-selected="false">R&B</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link sTabs" id="folk-tab" data-toggle="tab" data-target="#folk" type="button" role="tab" aria-controls="포크" aria-selected="false">포크</button>
					</li>
				</ul>
			</div>
			<!-- 그 외 -->
			<div class="tab-pane fade" id="other" role="tabpanel" aria-labelledby="other-tab">
				<ul class="nav nav-tabs" id="myTab" role="tablist">
					<li class="nav-item" role="presentation">
						<button class="nav-link sTabs active" id="ost-tab" data-toggle="tab" data-target="#ballad" type="button" role="tab" aria-controls="OST" aria-selected="true">ost</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link sTabs" id="classic-tab" data-toggle="tab" data-target="#dance" type="button" role="tab" aria-controls="클래식" aria-selected="false">클래식</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link sTabs" id="jazz-tab" data-toggle="tab" data-target="#rap" type="button" role="tab" aria-controls="재즈" aria-selected="false">재즈</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link sTabs" id="j-pop-tab" data-toggle="tab" data-target="#rnb" type="button" role="tab" aria-controls="J-POP" aria-selected="false">J-POP</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link sTabs" id="ccm-tab" data-toggle="tab" data-target="#indi" type="button" role="tab" aria-controls="CCM" aria-selected="false">CCM</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link sTabs" id="traditional-tab" data-toggle="tab" data-target="#rock" type="button" role="tab" aria-controls="국악" aria-selected="false">국악</button>
					</li>
				</ul>

			</div>
		</div>
		
		<table class="none" id="resultTable" style="margin: 0 auto; margin-top: 60px;">
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
					<th scope="col" class="rank">번호</th>
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
			<tbody id="result">
				
			</tbody>
		</table>
		<div id="pageBtn" class="row justify-content-center" style="margin-top: 13px;"></div>
	</section>
	
	<footer>
		<c:import url="/WEB-INF/views/include/bottom.jsp" />
	</footer>
</body>
<script>
	
	// 선택된 버튼의 값을 저장하기위한 변수
	var fTabValue = '';
	var sTabValue = '';
	var sTabs;
	var index = 1;
	var endIndex = 20;
	
	$(document).ready(function(){
		
		fTabValue = document.querySelector('.fTabs.active').getAttribute('aria-controls');
		sTabValue = document.querySelector('#' + fTabValue + ' .sTabs.active').getAttribute('aria-controls');
		
		var fTabs = document.querySelectorAll('.fTabs');
		sTabs = document.querySelectorAll('.sTabs');
		
		// 첫 번째 버튼
		fTabs.forEach(fTab => {
			
			// 각 버튼들의 이벤트 추가
			fTab.addEventListener('click', function(){
				
				fTabValue = this.getAttribute('aria-controls');
				
				var sTabsActive = document.querySelector('#' + fTabValue + ' .sTabs.active');
				sTabValue = sTabsActive.getAttribute('aria-controls');
				
				console.log(fTabValue + sTabValue);
				index = 1;
				endIndex = 20;
				getList();
				
			});
		})
		
		sTabs.forEach(sTab => {
			
			sTab.addEventListener('click', function(){
				
				sTabValue = this.getAttribute('aria-controls');
				console.log(fTabValue + sTabValue);
				index = 1;
				endIndex = 20;
				getList();
			});
			
		})
		
		console.log(fTabValue + sTabValue);
		getList();
	});
	
	// ajax통신
	function getList() {
    fetch('${root}genreSong?fTabValue=' + fTabValue + '&sTabValue=' + sTabValue + '&index=' + index + '&endIndex=' + endIndex)
        .then(response => {
            if (!response.ok) {
                throw new Error('잘못된 요청');
            }
            return response.json();
        })
        .then(result => {
        	if(result.resultList.length == 0){
        		$('#resultTable').addClass('none');
        		$('#pageBtn').addClass('none');
        	} else {
        		$('#resultTable').removeClass('none');
        		$('#pageBtn').removeClass('none');
        		insertView(result);
        	}
        })
        .catch(error => {
            alert(error);
        });
	}
	
	// 화면을 구성하는 부분
	function insertView(result){
		
		var resultList =  result.resultList;
		var loadPage = result.loadPage;
		var maxView = parseInt(result.maxView, 10);
		var page = parseInt(result.page, 10);
		
		var resultHtml = '';
		var pageBtn;
		
		for(var i = 0; i < resultList.length; i++){
			// 노래
			resultHtml += '<tr class="line">'
						+ '<td class="top_bottom rank">' + (index + i) + '</td>'
						+ '<td class="top_bottom"><img class="thum mouse_over_img" src="${root}images/thumbnail/song/' + resultList[i].songDto.song_thumbnail + '"></td>'
						+ '<td class="top_bottom"></td>'
						+ '<td class="top_bottom ellipsis"><div class="ellipsis" style="max-width: 230px"><a href="${root}search/song_info?song_id=' + resultList[i].songDto.song_id + '">'
						+ resultList[i].songDto.song_name + '</a></div></td>';
						
			//아티스트 썸네일			
			resultHtml += '<td class="top_bottom"><div id="carouselExampleSlidesOnly" class="carousel slide" data-ride="carousel">'
						+ '<div class="carousel-inner">'
						+ '<div class="carousel-item active">'
						+ '<a href="${root}search/artist_info?artist_id=' + resultList[i].artistList[0].artist_id + '" '
						+ 'title="' + resultList[i].artistList[0].artist_name +' - 정보" >'
						+ '<img class="thum" src="${root}images/thumbnail/artist/' + resultList[i].artistList[0].artist_thumbnail + '"></a>'
						+ '</div>';
			if(resultList[i].artistList.length > 1){
				for(var j = 1;  j < resultList[i].artistList.length; j++){
					resultHtml += '<div class="carousel-item">'
								+ '<a href="${root}search/artist_info?artist_id=' + resultList[i].artistList[j].artist_id + '" '
								+ 'title="' + resultList[i].artistList[j].artist_name + ' - 정보" >'
								+ '<img class="thum" src="${root}images/thumbnail/artist/' + resultList[i].artistList[j].artist_thumbnail + '"></a>'
								+ '</div>';
				}
			}
			// 아티스트 이름
			resultHtml += '</div></div><td class="top_bottom"></td>'
						+ '<td class="top_bottom"><div class="ellipsis" style="max-width: 230px"><a href="${root}search/artist_info?artist_id=' + resultList[i].artistList[0].artist_id + '" title="' + resultList[i].artistList[0].artist_name + ' - 정보">'
						+ resultList[i].artistList[0].artist_name + '</a>';
						
			if(resultList[i].artistList.length > 1){
				for(var j = 1;  j < resultList[i].artistList.length; j++){
					resultHtml += ', '
								+ '<a href="${root}search/artist_info?artist_id=' + resultList[i].artistList[j].artist_id + '" title="' + resultList[i].artistList[j].artist_name + ' - 정보">'
								+ resultList[i].artistList[j].artist_name
								+ '</a>'
				}
			}
			resultHtml += '</div></td>'
			// 앨범
			resultHtml += '<td class="top_bottom"><div class="ellipsis" style="max-width: 170px;">'
						+ '<a href="${root}search/album_info?album_id=' + resultList[i].songDto.album_id + '" title="' + resultList[i].songDto.album_name + ' - 정보" >'
						+ resultList[i].songDto.album_name + '</a></div></td>';
						
			//좋아요
			resultHtml += '<td class="top_bottom text-center">'
						+ resultList[i].songDto.song_thumbup
						+ '</td></tr>';
		}
		
		$('#result').html(resultHtml);
		$('.carousel').carousel();
		
		// 버튼 구성
		var pageBtn = "";
		// 버튼
		for(var i = 0; i < loadPage.length; i++){
			pageBtn += '<button type="button" class="btn btn-outline-success pageBtns" index="' + ((loadPage[i] - 1) * maxView + 1) + '" '
					+	'endIndex="' + (loadPage[i] * maxView) + '" >'
					+	loadPage[i] + '</button>';
		}
		$('#pageBtn').html(pageBtn);
		
		var pageBtns = document.querySelectorAll('.pageBtns');
		pageBtns.forEach(btn => {
			btn.addEventListener('click', pageBtnEvent);
		})
	}
	
	function pageBtnEvent(){
		this.removeEventListener('click', pageBtnEvent);
		index = parseInt(this.getAttribute('index'), 10);
		endIndex = parseInt(this.getAttribute('endIndex'), 10);
		getList();
	}
	
</script>
</html>

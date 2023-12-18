<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
				<form:hidden path="type_id" value="${albumDto.album_id}"/>
				<form:hidden path="flag" value="album"/>
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
						placeholder="내용을 입력해주세요..." required="true" rows="4"/>
						<div id="submitBtn" class="text-center" style="margin-top: 13px;"></div>
					</div>
				</div>
			</form:form>
		</div>
	</section>
	<div class="none" id="hiddenValue" flag="album" type_id="${albumDto.album_id}"></div>
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
	
	//============ 리뷰 ============
	var type_id = '';
	var flag = '';

	$(document).ready(function(){
		
		thumbupBtn = document.querySelector('#hiddenValue');
		type_id = thumbupBtn.getAttribute('type_id');
		flag = thumbupBtn.getAttribute('flag');
			
			$.ajax({
				url: '${root}getFirstReview',
				type: 'GET',
				data: {
					type_id: type_id,
					flag: flag
				},
				success: function(result){
					getReviewList(result);
					
					var index = parseInt(result.index, 10);
					btnAddEvent(index);
				}
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
		
		function clickHandler(){
			this.removeEventListener('click', clickHandler);
			var index = this.getAttribute('index');
			
			$.ajax({
				url: '${root}getPageReview',
				type: 'GET',
				data: {
					index: index,
					type_id: type_id,
					flag: flag
				},
				success: function(result){
					getReviewList(result);
					btnAddEvent(index);
				}
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
			$.ajax({
				url: '${root}reviewReport',
				type: 'POST',
				data: {
					review_num: review_num,
					report_user_num: report_user_num,
					user_num: user_num
				},
				success: function(result){
					if(result == 'true'){
						alert('신고가 완료되었습니다.');
						location.reload();
					} else {
						alert('이미 신고 한 리뷰 입니다.');
						location.reload();
					}
				}
			})
		} else {
			alert('로그인이 필요한 서비스 입니다.');
			location.href='${root}user/login';
		}
	}
	//================ 리뷰 등록 수정 ===================
	$(document).ready(function(){
		var review_num = $('#review_num').val();
		var submitBtn = '<button type="submit" class="btn btn-success" >';
		var isLogin = ${loginUserBean.userLogin};
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
			$.ajax({
				url: '${root}reviewDelete',
				type: 'POST',
				data: {
					flag: flag,
					type_id: type_id,
					user_num: ${loginUserBean.user_num},
					review_num: review_num
				},
				success: function(result){
					
					if(result == 'true'){
						alert('삭제를 완료했습니다.');
						location.reload();
					} else {
						alert('삭제에 실패하였습니다.');
					}
				},
				error: function(response){
					console.log(response);
					console.log('flag : ' + flag);
					console.log('type_id : ' + type_id);
					console.log('user_num : ' + user_num);
					console.log('user_num : ' + ${loginUserBean.user_num});
					console.log('review_num : ' + review_num);
				}
			});
		});
	});
</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
<!-- 부트 스트랩 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="styleSheet" href="${root}style/searchBar.css">
<link rel="styleSheet" href="${root}style/any_main.css">
</head>
<body>
	<header>
		<c:import url="/WEB-INF/views/admin/include/top.jsp" />
	</header>
	<section style="margin-top: 130px;">
		<div class="form-row align-items-center search_form" style="margin: 70px 0">
			<div class="col-auto my-1">
			</div>
			<input type="text" class="form-control search_bar" name="str" id="searchBar" onkeyup="getResultOnKey()">
			<div class="col-auto my-1">
			</div>
		</div>
		<div>
			<table style="margin: 0 auto;">
				<colgroup>
					<col style="width: 100px"/>
					<col style="width: 360px"/>
					<col style="width: 200px"/>
					<col style="width: 100px"/>
					<col style="width: 100px"/>
				</colgroup>
				<thead>
					<tr class="">
						<th class="text-center" scope="col">ID</th>
						<th scope="col">아티스트이름</th>
						<th scope="col">데뷔일</th>
						<th scope="col">국가</th>
						<th scope="col" class="text-center">활동유형</th>
					</tr>
				</thead>
				<tbody id="searchResult">
					
				</tbody>
			</table>
			<div class="container">
				<div class="row justify-content-center" id="pageBtn">
					
				</div>
			</div>
		</div>
	</section>
</body>
<script>

	var index = 1;
	var endIndex = 20;
	
	$(document).ready(function(){
		getResult();
	});
	
	// 검색
	function getResult(){
		str = $('#searchBar').val();
		$.ajax({
			url: "${root}admin/search/ajax/artist",
			type: 'GET',
			dataType: 'json',
			data:{
				str: str,
				index: index,
				endIndex: endIndex
			},
			success: function(result){
				inputResult(result);
			}
		});
	}
	
	function getResultOnKey(){
		str = $('#searchBar').val();
		index = 1;
		endIndex = 20;
		$.ajax({
			url: "${root}admin/search/ajax/artist",
			type: 'GET',
			dataType: 'json',
			data:{
				str: str,
				index: index,
				endIndex: endIndex
			},
			success: function(result){
				inputResult(result);
				console.log('index :' + index);
				console.log('endIndex :' + endIndex);
			}
		});
	}
	
	// 화면에 출력
	function inputResult(result){
		
		var artistList = result.artistList;
		var maxView = parseInt(result.maxView, 10);
		var loadPage = result.loadPage;
		var page = parseInt(result.page, 10);
		
		// 검색 내용
		var searchResult = "";
		for(var i = 0; i < artistList.length; i++){
			searchResult += '<tr class="line">'
						+	'<td class="top_bottom text-center">'
						+	artistList[i].artist_id
						+	'</td>'
						+	'<td class="top_bottom"><div class="ellipsis" style="max-width: 350px;" >'
						+	'<a href="${root}admin/search/artistInfo?artist_id=' + artistList[i].artist_id + '">' + artistList[i].artist_name
						+	'</a></div></td>'
						+	'<td class="top_bottom">'
						+	artistList[i].artist_date
						+	'</td>'
						+	'<td class="top_bottom"><div class="ellipsis" style="max-width: 900px;" >'
						+	artistList[i].artist_nation
						+	'</div></td>'
						+	'<td class="top_bottom text-center">'
						+	artistList[i].artist_type
						+	'</td></tr>';
		}
		
		$("#searchResult").html(searchResult);
		
		var pageBtn = "";
		// 버튼
		for(var i = 0; i < loadPage.length; i++){
			if(page == loadPage[i]){
				pageBtn += '<button type="button" class="btn btn-secondary pageBtns" index="' + ((loadPage[i] - 1) * maxView + 1) + '" '
				+	'endIndex="' + (loadPage[i] * maxView) + '" >'
				+	loadPage[i] + '</button>';
			} else {
				pageBtn += '<button type="button" class="btn btn-outline-secondary pageBtns" index="' + ((loadPage[i] - 1) * maxView + 1) + '" '
				+	'endIndex="' + (loadPage[i] * maxView) + '" >'
				+	loadPage[i] + '</button>';
			}
		}
		$('#pageBtn').html(pageBtn);
		
		var pageBtns = document.querySelectorAll('.pageBtns');
		pageBtns.forEach(btn => {
			btn.addEventListener('click', pageBtnEvent);
		})
	}
	
	function pageBtnEvent(){
		this.removeEventListener('click', pageBtnEvent);
		index = this.getAttribute('index');
		endIndex = this.getAttribute('endIndex');
		getResult();
	}
</script>
</html>
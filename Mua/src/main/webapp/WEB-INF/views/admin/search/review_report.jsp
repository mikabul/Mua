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
	<div>
		<div style="min-height: 500px;">
			<table style="margin: auto;">
				<colgroup>
					<!-- 신고 번호 -->
					<col style="width: 150px" />
					<!-- 리뷰 번호 -->
					<col style="width: 150px" />
					<!-- 플래그 -->
					<col style="width: 100px" />
					<!-- 작성자 ID -->
					<col style="width: 150px" />
					<!-- 내용 -->
					<col style="width: 420px" />
					<!-- 버튼 -->
					<col style="width: 200px">
				</colgroup>
				<thead>
					<tr>
						<th scope="col" class="text-center">신고 번호</th>
						<th scope="col" class="text-center">리뷰 번호</th>
						<th scope="col" class="text-center">플래그</th>
						<th scope="col" class="text-center">작성자 ID</th>
						<th scope="col" class="text-center">내용</th>
						<th scope="col" style="display: none">버튼</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${reviewList}">
						<tr class="line">
							<!-- 신고 번호 -->
							<td class="top_bottom text-center">
								<div class="rank">${item.report_num}</div>
							</td>
							
							<!-- 리뷰 번호 -->
							<td class="top_bottom text-center">
								<div>
									${item.review_num}
								</div>
							</td>
							<!-- 플래그 -->
							<td class="top_bottom text-center">
								<div>
									${item.flag}
								</div>
							</td>
							<!-- 작성자 ID -->
							<td class="top_bottom text-center">
								<div>
									${item.user_id}
								</div>
							</td>
							<!-- 내용 -->
							<td class="top_bottom">
								<div style="width: 410px; height: auto; margin: 0 5px; white-space: pre-wrap">
									${item.review_content}
								</div>
							</td>
							<!-- 버튼 -->
							<td class="top_bottom">
								<div>
									<button class="btn btn-warning btn-sm deleteBtn" review_num="${item.review_num}" report_num="${item.report_num}" >리뷰 삭제</button>
									<button class="btn btn-success btn-sm passBtn" report_num="${item.report_num}">신고 삭제</button>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>

<script>
	$(document).ready(function(){
		
		var deleteBtns = document.querySelectorAll('.deleteBtn');
		var passBtns = document.querySelectorAll('.passBtn');
		
		deleteBtns.forEach(deleteBtn => {
			deleteBtn.addEventListener('click', deleteBtnEvent);
		})
		
		passBtns.forEach(passBtn => {
			passBtn.addEventListener('click', passBtnEvent);
		})
		
	});
	
	function deleteBtnEvent(element){
		var review_num = this.getAttribute('review_num');
		var report_num = this.getAttribute('report_num');
		
		location.href='${root}admin/search/deleteReview?review_num=' + review_num + '&report_num=' + report_num;
	};
	
	function passBtnEvent(element){
		var report_num = this.getAttribute('report_num');
		
		location.href='${root}admin/search/passReview?report_num=' + report_num;
	};
</script>

</html>
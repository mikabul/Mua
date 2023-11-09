<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mua</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
<link rel="styleSheet" href="./style/main.css">
<style>
.search_bar{
	width: 30%;
}

.search_form{
	display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    top: 30%;
    width: 100%
}
</style>
</head>
<body>
	<!-- 상단 메뉴 부분 -->
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	<!-- 중단 부분 -->
	<section style="position: absolute; height: 30%; width: 100%;">
		<form>
  			<div class="form-row align-items-center search_form">
    			<div class="col-auto my-1">
      				<label class="mr-sm-2 sr-only" for="inlineFormCustomSelect">Preference</label>
      				<select class="custom-select mr-sm-2" id="inlineFormCustomSelect">
        				<option value="song" selected>노래</option>
        				<option value="artist">가수</option>
        				<option value="album">앨범</option>
     				</select>
    			</div>
    			<input type="text"class="form-control search_bar">
    			<div class="col-auto my-1">
      				<button type="submit" class="btn btn-primary">&nbsp;검색&nbsp;</button>
    			</div>
  			</div>
		</form>
	</section>
</body>
</html>
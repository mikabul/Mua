<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<link rel="styleSheet" href="${root}style/searchBar.css">

	<form action="${root}search/main" method="get" >
		<input type="hidden" name="index" value="0">
		<input type="hidden" name="maxView" value="20">
		<div class="form-row align-items-center search_form" style="margin: 70px 0">
			<div class="col-auto my-1">
				<select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="type">
					<option value="song" id="song">노래</option>
					<option value="artist" id="artist">가수</option>
					<option value="album" id="album">앨범</option>
				</select>
			</div>
			<input type="text" class="form-control search_bar" name="str" value="${str}">
			<div class="col-auto my-1">
				<button type="submit" class="btn btn-primary">&nbsp;검색&nbsp;</button>
			</div>
		</div>
	</form>
	
<script>
	
	const urlString = window.location.search;
	const urlParams = new URLSearchParams(urlString);
	const paramValue = urlParams.get('type');
	
	const select = document.getElementById(paramValue);
	select.selected = true;
	
</script>

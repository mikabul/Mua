<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mua</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script>
	<% if(session.getAttribute("chart")==null){ %>
		$.ajax({
			url: '${root}chart/getChart',
			type: 'get'
		}); //ajax_END
		location.href='${root}rank/top100';
	<%}%>
</script>
</head>
<body>
	<table>
	<tr>
		<th>노래</th>
		<th>가수</th>
		<th>앨범</th>
	</tr>
		<c:forEach var="item" items="${sessionScope.chart}">
			<tr>
				<td><a href="${root}search/song_info?song_id=${item.song_id}">${item.song_name}</a></td>
				<td>
					<c:forEach var="i" begin="0" end="${fn:length(item.artist_name)}">
						<a href="${root}search/artist_info?artist_id=${item.artist_id[i]}">${item.artist_name[i]}</a>
					</c:forEach>
				</td>
				<td><a href="${root}search/album_info?album_id=${item.album_id}">${item.album_name }</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value='${pageContext.request.contextPath}/' />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<table style="margin: auto; text-align: center;">
			<tr>
				<th>노래</th>
				<th>아티스트</th>
				<th>앨범</th>
			</tr>
			<tbody>
				<c:forEach var="item" items="${arrayResultDTOs}">
					<tr>
						<td><a href="${root}search/SongInfo?song_id=${item.songDTO.song_id}">${item.songDTO.song_name}</a></td>
						<td>
							<c:if test="${item.artistDTO.artist_id!=-1}">
								<a href="${root}search/ArtistInfo?song_id=${item.artistDTO.artist_id}">${item.artistDTO.artist_name}</a>
							</c:if>
							<c:if test="${item.artistDTO.artist_id==-1}">
								${item.artistDTO.artist_name}
							</c:if>
						</td>
						<td><a href="${root}search/AlnumInfo?song_id=${item.albumDTO.album_id}">${item.albumDTO.album_name}</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
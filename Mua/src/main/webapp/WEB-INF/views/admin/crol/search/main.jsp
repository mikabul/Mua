<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value='${pageContext.request.contextPath}/' />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mua</title>
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
						<td><a href="${root}admin/crol/search/insert_song?song_id=${item.songDto.song_id}">${item.songDto.song_name}</a></td>
						<td>
							<c:if test="${item.artistDto.artist_id!=-1}">
								<a href="${root}search/ArtistInfo?artist_id=${item.artistDto.artist_id}">${item.artistDto.artist_name}</a>
							</c:if>
							<c:if test="${item.artistDto.artist_id==-1}">
								${item.artistDto.artist_name}
							</c:if>
						</td>
						<td><a href="${root}search/AlnumInfo?album_id=${item.albumDto.album_id}">${item.albumDto.album_name}</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
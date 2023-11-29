<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table style="border: 1px solid black">
		<tr>
			<th style="width: 200px;">노래</th>
			<td>${infoSongDTO.song_name}</td>
		</tr>
		
		<tr>
			<th style="width: 200px;">발매일</th>
			<td>${infoSongDTO.release_date }</td>
		</tr>
		<tr>
			<th style="width: 200px;">장르</th>
			<td>${infoSongDTO.song_genre}</td>
		</tr>
		<tr>
			<th style="width: 200px;">가사</th>
			<td>${infoSongDTO.lyrics}</td>
		</tr>
		<tr>
			<th style="width: 200px;">썸네일</th>
			<td>${infoSongDTO.song_thumbnail}</td>
		</tr>
		<tr>
			<th style="width: 200px;">아티스트</th>
			<td>${infoSongDTO.artist_name}</td>
		</tr>
		<tr>
			<th style="width: 200px;">앨범</th>
			<td>${infoSongDTO.album_name}</td>
		</tr>
	</table>

</body>
</html>
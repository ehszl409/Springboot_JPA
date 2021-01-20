<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>디테일 페이지</h1>
	<hr />
	<table border=1>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>내용</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="post" items="${posts}">
		<tr>
			<td>${post.id}</td>
			<td>${post.title}</td>
			<td>${post.content}</td>
			<td>${post.readCount}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page errorPage="error.jsp"%>
<%@ page import="com.company.Model2.board.BoardDAO"%>
<%@ page import="com.company.Model2.board.BoardDO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	BoardDO board = (BoardDO)session.getAttribute("board");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세 보기 페이지</title>
</head>
<body>
	<h1>게시글 상세보기</h1>
	<a href="logout.do">로그아웃</a>
	<hr>
	<form name="displayForm" method="POST" action="updateBoard.do">
		<input type="hidden" name="seq" value="${board.seq}" />
		<table>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title"
					value="${board.title}"/></td>
			</tr>

			<tr>
				<td>작성자</td>
				<td><input type="text" name="writer"
					value="${board.writer}" /></td>
			</tr>

			<tr>
				<td>내용</td>
				<td><textarea name="content" rows="10" cols="40">
					${board.content}</textarea></td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${board.cnt}</td>
			</tr>

			<tr>
				<td>등록일</td>
				<td>${board.regDate}</td>
			</tr>

			<tr>
				<td><input type="submit" value="글 수정" /></td>
			</tr>
		</table>
		<a href="deleteBoard.do?seq=${board.seq}">글 삭제</a>
		<a href="getBoardList.do">전체 게시글 목록 보기</a>
	</form>
</body>
</html>
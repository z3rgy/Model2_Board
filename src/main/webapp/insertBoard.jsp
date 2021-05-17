<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 입력 폼</title>
</head>
<style>
#div_box {
	margin: 0;
	position: absolute;
	top: 40%;
	left: 50%;
	-ms-transform: translate(-50%, -50%);
	transform: translate(-50%, -50%);
	text-align: center;
}
</style>
<body>
	<div id="div_box">
		<h1>게시글 등록</h1>
		<a href="logout.do">로그아웃</a>
		<hr>

		<form name="insertForm" method="POST" action="insertBoard.do">

			<table>
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" /></td>
				</tr>

				<tr>
					<td>작성자</td>
					<td><input type="text" name="writer" /></td>
				</tr>

				<tr>
					<td>내용</td>
					<td><textarea name="content" rows="10" cols="40"></textarea>
					</td>
				</tr>

				<tr>
					<td><input type="submit" value="글 등록" /></td>
				</tr>
			</table>

		</form>
		<a href="getBoardList.do">전체 게시글 목록 보기</a>
	</div>
	<hr>

</body>
</html>
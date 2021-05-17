<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: "Noto Sans KR", sans-serif;
}
.login_id {
  margin-top: 20px;
  width: 80%;
}
.login_id input {
  width: 100%;
  height: 50px;
  border-radius: 30px;
  margin-top: 10px;
  padding: 0px 20px;
  border: 1px solid lightgray;
  outline: none;
}
.wrap {
	width: 100%;
	height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
	background: rgba(0, 0, 0, 0.1);
}
.login {
	width: 30%;
	height: 600px;
	background: white;
	border-radius: 20px;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
}
h2 {
	color: tomato;
	font-size: 2em;
}
.login_pw {
	margin-top: 20px;
	width: 80%;
}
.login_pw input {
	width: 100%;
	height: 50px;
	border-radius: 30px;
	margin-top: 10px;
	padding: 0px 20px;
	border: 1px solid lightgray;
	outline: none;
}
.submit {
	margin-top: 50px;
	width: 80%;
}
.submit input {
	width: 100%;
	height: 50px;
	border: 0;
	outline: none;
	border-radius: 40px;
	background: linear-gradient(to left, rgb(255, 77, 46), rgb(255, 155, 47));
	color: white;
	font-size: 1.2em;
	letter-spacing: 2px;
}
</style>
</head>
<body>
	<div class="wrap">
		<div class="login">
			<h2>Log-in</h2>
			<form name="LoginForm" method="POST" action="login.do">
				
				<div class="login_id">
					<h4>아이디</h4>
					<input type="text" name="id" placeholder="아이디">
				</div>
				<div class="login_pw">
					<h4>패스워드</h4>
					<input type="password" name="password" placeholder="패스워드">
				</div>
				<div class="submit">
					<input type="submit" name="로그인" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>
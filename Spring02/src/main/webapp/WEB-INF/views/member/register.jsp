<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Member</title>
</head>
<body>

<h1>회원 가입 페이지</h1>
<form action="register" method="post">
	<input type="text" name="userid" placeholder="USER ID"
		required />
	<br/>
	<input type="password" name="password" placeholder="PASSWORD"
		required />
	<br/>
	<input type="email" name="email" placeholder="EMAIL"
		required />
	<br/>
	<input type="submit" value="회원 가입" />
</form>

</body>
</html>









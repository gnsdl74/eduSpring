<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Member</title>
</head>
<body>

<h1>회원 관리 메인 페이지</h1>
<ul>
	<li><a href="register">신규 회원 가입</a></li>
</ul>

<hr/>
<table>
	<thead>
		<tr>
			<th>ID</th>
			<th>PASSWORD</th>
			<th>EMAIL</th>
			<th>ACTIVE</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="member" items="${memberList}">
			<tr>
				<td><a href="detail?userid=${member.userid}">${member.userid}</a></td>
				<td>${member.password}</td>
				<td>${member.email}</td>
				<td>${member.active}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

</body>
</html>







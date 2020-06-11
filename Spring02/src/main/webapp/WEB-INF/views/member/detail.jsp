<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Member</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>

<h1>회원 정보 상세 페이지</h1>
<form id="frm">
	<label for="userid">아이디</label>
	<input type="text" id="userid" name="userid" 
		value="${member.userid}" readonly />
	<br/>
	<label for="pwd">비밀번호</label>
	<input type="password" id="pwd" name="password" 
		value="${member.password}" required />
	<br/>
	<label for="email">이메일</label>
	<input type="email" id="email" name="email" 
		value="${member.email}" required />
	<br/>
	<label for="activie">활동여부</label>
	<input type="text" id="active" name="active" 
		value="${member.active}" readonly />
	<br/>
</form>

<button type="submit" id="btn_update">회원 정보 수정</button>
<button type="submit" id="btn_delete">회원 정보 삭제</button>
<button type="submit" id="btn_deregister">회원 탈퇴</button>

<script>
$(document).ready(function() {
	var frm = $('#frm');
	
	$('#btn_update').click(function() {
		frm.attr('action', 'update');
		frm.attr('method', 'post');
		frm.submit();
	});
	
	$('#btn_delete').click(function() {
		frm.attr('action', 'delete');
		frm.attr('method', 'post');
		frm.submit();
	});
	
	$('#btn_deregister').click(function() {
		frm.attr('action', 'deregister');
		frm.attr('method', 'post');
		frm.submit();
	});
});
</script>

</body>
</html>












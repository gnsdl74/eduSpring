<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Board</title>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
	<h1>게시판 메인 페이지</h1>
	<c:if test="${empty login_id }">
		<button id="btn_login" type="button">로그인</button>
	</c:if>
	<c:if test="${not empty login_id }">
		<button id="btn_logout" type="button">로그아웃</button>
	</c:if>
	<hr>
	<h2>게시판 글 목록</h2>
	<table>

	</table>
	
	<hr>
	<a href="register">새 글 작성</a>
	<a href="test?data1=한글&data2=abc123def">로그인이 필요한 메뉴</a>
	
	<script type="text/javascript">
    $(document).ready(function() {
      $('#btn_login').click(function() {
        // 상대 경로 : 현재 위치를 기준으로 상대적으로 찾아가는 경로
        // location = "../member/login";
      
      	// 절대 경로 : context root부터 모든 경로
      	// location = "/ex04/member/login";
      
      	// 로그인 후에 다시 게시판 메인(list.jsp) 페이지로 돌아올 수 있도록 하는 코드
      	// 메소드 안에서 사용되는 location.href : 이동하기 전 페이지의 전체 경로를 가져옴.(getUrl)
      	var target = encodeURI('/ex04/member/login?url=' + location.href);
      	console.log(target);
      	// location 객체의 default 속성은 location.href
      	// location = url 로 작성하면 location.href = url 과 같다.
      	location = target;
      }); // end btn_login click()

      $('#btn_logout').click(function() {
        location = "../member/logout";
      }); // end btn_logout click()
    }); // end document
  </script>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>File Upload</title>
</head>
<body>
	<h1>파일 업로드 폼</h1>
	<h2>단일 파일 업로드</h2>
	<form action="upload" method="post" enctype="multipart/form-data">
		<input type="file" name="file" />
		<input type="submit" value="업로드" />
	</form>
	
	<h2>다중 파일 업로드</h2>
	<form action="upload2" method="post" enctype="multipart/form-data">
		<input type="file" name="files" multiple />
		<input type="submit" value="업로드" />
	</form>
	
	<script type="text/javascript">
		$(document).ready(function() {
		  // 문제점 : 파일을 올리지않았을 때도 저장됨
		  if(${not empty savedFile}) {
		    alert('${savedFile} 저장 성공');
		  }
		}); // end document
	</script>
</body>
</html>
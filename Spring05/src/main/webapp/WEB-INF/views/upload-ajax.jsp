<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.file-drop {
	width: 100%;
	height: 100px;
	border: 1px solid grey;
}
</style>
</head>
<body>
	<h1>Ajax를 이용한 파일 업로드</h1>
	<div class="file-drop"></div>
	<div class="upload-list"></div>
	
	<script type="text/javascript">
	$(document).ready(function() {
	  // 파일을 끌어다 놓을 때(drag & drop)
	  // 브라우저가 파일을 자동으로 열어주는 기능을 막음
	  $('.file-drop').on('dragenter dragover', function(event) {
	    event.preventDefault();
	  }); // end .file-drop on(dragenter dragover)
	  
	  // 파일을 해당 div에 drop했을 때의 이벤트
	  $('.file-drop').on('drop', function(event) {
	    // drop 이벤트의 기본 동작을 막음
	    event.preventDefault();
	    
	    console.log('drop 테스트');
	    
	    // Ajax를 이용해서 서버로 파일을 업로드
	    // multipart/form-data 타입으로 파일을 업로드하는 객체
	    var formData = new FormData();
	    
	    // drop한 파일에 대한 정보
	    var files = event.originalEvent.dataTransfer.files;
	    var i = 0;
	    for (i = 0; i < files.length; i++) {
	      console.log(files[i]);
	      // 서버로 보낼 form-data 작성
	      formData.append("files", files[i]);
	    }
	    
	    $.ajax({
	      type : "post",
	      url : "/ex05/upload-ajax",
	      data : formData,
	      processData : false,
		  contentType : false,
		  /*
		  	form의 enctype 속성을
		  	기본값인 "application/x-www-form-urlencoded"을 사용하지 않고
		  	"multipart/form-data"를 사용하기 위해 (contentType : false)를 지정
		  	contentType으로 "multipart/form-data"을 지정하는 것은 formData가 자동으로 지정
		  */
		  success : function(data) {
		    alert(data);
		    // 하나의 파일만 썸네일 가능. 여러개 파일을 적용시 수정 필요.
		    var str = $('.upload-list').html();
		    str += '<div>'
		    	+ '<img src="display?fileName='
		    	+ data
		    	+ '" />'
		    	+ data
		    	+ '</div>';
		    $('.upload-list').html(str);	
		  }
	    }); // end ajax()
	    
	  }); // end .file-drop on(drop)
	}); // end document
	</script>

</body>
</html>
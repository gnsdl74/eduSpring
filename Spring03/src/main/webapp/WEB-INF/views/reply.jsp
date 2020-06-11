<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reply</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<!-- cdnjs는 Date형태를 포맷하기 위한 js라이브러리 moment()함수를 이용하여 포매팅한다. -->
</head>
<body>
	<h1>댓글 페이지</h1>
	<div>
		<input type="text" id="replyid" placeholder="아이디 입력"> <input
			type="text" id="content" placeholder="댓글 입력">
		<button type="button" id="btn_add">작성</button>
	</div>

	<hr>
	<div>
		<div id="replies"></div>
	</div>

	<script type="text/javascript">
    $(document).ready(function() {
      var bno = 3; // 게시판 번호 값
      
      // 댓글 입력 기능
      $("#btn_add").click(function() {
        // 댓글 아이디, 댓글 내용의 값을 가져와서
        // get 방식으로 전송
        // url : "localhost:8080/Web10_MVC/replies/add"
        // data : 게시판 번호, 댓글 내용, 댓글 아이디 

        // **content와 replyid는 null값을 처리해야함** 
        var content = $("#content").val(); // 댓글 내용 값
        var replyid = $("#replyid").val(); // 댓글 아이디 값
        var obj = {
              "bno" : bno,
              "content" : content,
              "replyid" : replyid
            }; // JSON 형태로 포장
                
         // $.ajax로 송신
         // RESTful api 중 POST 로 사용하겠다.
         // => "X-HTTP-Method-Override" : "POST"
         // => HTTP Method 중 POST 로 전송하겠다.
         // obj는 ReplyVO를 JSON 형태로 변환하여 보낸 것
            $.ajax({
              type : "post",
              url : "/ex03/replies",
              headers : {
              	"Content-Type" : "application/json",
              	"X-HTTP-Method-Override" : "POST"
              },
              data : JSON.stringify(obj),
              success : function(result) { // ReplyRESTController에서 받아온 result값
                console.log(result);
                if (result == 1) { 
                  // 현재는 result값으로만 입력성공을 판단.
                  // status를 이용하여 앞에서 HttpStatus를 받아와서 둘 모두 만족하는 조건을 설정할 수도 있음.
                  alert("댓글 입력 성공");
                  $("#content").val("");
                  $("#replyid").val("");
                  getAllReplies(); // 메소드 호출
                }
              }
            }); // end ajax()
      }); // end btn_add click()

      getAllReplies(); // 메소드 호출

      // 게시판의 댓글 전체 가져오기 (board-detail로 접근했을때 실행)
      function getAllReplies() {
        var url = "/ex03/replies/all/" + bno; // 절대경로, 해당 url는 RESTful로 쓸때만 가능
        // var url = "replies/all?bno=" + bno; - 상대경로

        getJSON*url, callback() {}) 형태
        $.getJSON(url, // JSON 데이터를 GET방식으로 보냄
          function(jsonData) {
          console.log(jsonData);
          var list = ""; // JSON 데이터에서 데이터를 꺼내 태그 + 데이터 형식으로 저장할 변수

          $(jsonData).each( // jsonData를 사용하는 each 반복문
          function() {
            var date = new Date(this.regdate); // this : JSON Data
            // head의 cdnjs의 라이브러리 함수를 이용하여 Date를 포맷.
            var dateConvert = moment(date).format('YYYY-MM-DD HH:mm:ss');
            console.log("댓글 번호 : " + this.rno);
            list += '<div class="reply_item">' 
            	+ '<pre>'
            	+ '<input type="hidden" id="rno" value="' + this.rno + '"/><br>'
            	+ '<input type="hidden" id="reply_id" value="' + this.replyid + '"/><br>'
            	+ this.replyid
            	+ '&nbsp;&nbsp;'
            	+ '<input type="text" id="reply_content" value="' + this.content + '"/>'
            	+ '&nbsp;&nbsp;'
				+ dateConvert
            	+ '&nbsp;&nbsp;'
            	+ '<button class="btn_update" type="button">수정</button>'
            	+ '&nbsp'
            	+ '<button class="btn_delete" type="button">삭제</button>'
            	+ '</pre>' + '</div>';
          }); // end each()
          $("#replies").html(list);
        } // end callback()
        ); // end getJSON
      } // end getAllReplies()
	
      // 수정 버튼 클릭하면 선택된 댓글 수정 [.on()은 이벤트처리 메소드]
      $('#replies').on('click', '.reply_item .btn_update', function() {
        // 해당 버튼의 rno, content 값을 읽음
        // prevAll() : this 형제요소 중 이전에 있는 전체 형제요소를 선택 
        var rno = $(this).prevAll('#rno').val(); // 이전의 전체 형제요소 중 id가 rno인 요소를 선택
        var content = $(this).prevAll('#reply_content').val(); // 이전의 전체 형제요소 중 id가 reply_content인 요소를 선택
        console.log("선택된 댓글 번호 : " + rno + ", 댓글 내용 : " + content);
        
        // ajax 요청(update)
        $.ajax({
          type : 'put',
          url : '/ex03/replies/' + rno,
          headers : {
          	"Content-Type" : "application/json",
          	"X-HTTP-Method-Override" : "PUT"
          },
          data : JSON.stringify({
            'content' : content
          }),
          success : function(result) {
            if (result == 'success') { // 댓글 수정에 성공하면
              alert('댓글 수정 성공');
              getAllReplies(); // 다시 전체 댓글 정보를 가져오기
            }
          }
        }); // end ajax
      }); // end btn_update.click()
      
      // 삭제버튼을 클릭하면 선택한 댓글 삭제
      $('#replies').on('click', '.reply_item .btn_delete', function() {
        // 해당 버튼의 rno, content 값을 읽음
        // prevAll() : this 형제요소 중 이전에 있는 전체 형제요소를 선택 
        var rno = $(this).prevAll('#rno').val(); // 이전의 전체 형제요소 중 id가 rno인 요소를 선택
        console.log("선택된 댓글 번호 : " + rno);
        
        // ajax 요청 - ajax의 $.get(url, 추가 데이터, callback) 메소드 사용 - RESTful 사용할 경우 (type : delete) 사용
        $.ajax({
          type : "delete",
          url : '/ex03/replies/' + rno,
          headers : {
          	"Content-Type" : "application/json",
          	"X-HTTP-Method-Override" : "DELETE"
          },
          data : JSON.stringify({
            'bno' : bno
          }),
          success : function(result) {
            if (result == 'success') { // 댓글 삭제에 성공하면 (그러나, controller에서 ajax에 대한 응답[response]가 없으면 실행되지 않는다.)
              alert('댓글 삭제 성공');
              getAllReplies(); // 다시 전체 댓글 정보를 가져오기
            }
          }
        }); // end ajax
      }); // end btn_delete.click()
      
    }); // end document
  </script>
</body>
</html>
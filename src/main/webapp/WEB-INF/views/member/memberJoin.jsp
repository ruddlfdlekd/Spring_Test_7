<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	$("#btn").click(function(){
	var id = $('input[name=id]').val();	
	$.ajax({
			url:"../member/memberIdCheck",
			type:"GET",
			data:{
				id:id,
			},
			success:function(data){
				if(data.trim()=="사용 가능한 ID입니다")
					$("#IDCHECK").text("사용 가능한 ID입니다"); 
				else
					$("#IDCHECK").text("중복된 ID입니다"); 
				}
			});
		});
});
</script>
</head>
<body>
<form action="memberJoin" method="POST" enctype="multipart/form-data">
<p>ID : <input type="text" name="id"><input type="button" id="btn" value="아이디 중복확인"></p>
<div id="IDCHECK">
</div>
<p>PW : <input type="text" name="pw"></p>
<p>NAME : <input type="text" name="name"></p>
<p>E-MAIL : <input type="text" name="email"></p>
<p>PHONE : <input type="text" name="phone"></p>
<p>AGE : <input type="text" name="age"></p>
<p>JOB : <input type="text" name="job"></p>
<p>사진 : <input type="file" name="file"></p>
<button>Join</button>
</form>
</body>
</html>
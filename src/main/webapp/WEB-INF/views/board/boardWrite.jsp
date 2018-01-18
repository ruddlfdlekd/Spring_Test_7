<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	var z=0;
	$("#btn").click(function() {
		if(z<5){
			
		 $("#result").append("<p><input type='file'><span class='x'>X</span></p>");
		 /* 아니면 div에 <p><input type='file'><span class='x'>X</span></p>이걸 넣은걸 만들어서 html()로 가져와서 넣기 */
		 z++;
		}
	});	
	$("#result").on("click",".x",function(){
		$(this).parent().remove();
		z--;
		if(z<0)
		z=0;
	});
});
</script>

<style type="text/css">
	.x {
		cursor: pointer;
	}
</style>

</head>
<body>
<form name="frm" action="./${board }Write" method="POST" enctype="multipart/form-data">
<p>writer<input type="text" name="writer"></p>
<p>title<input type="text" name="title"></p>
<p>contents<textarea rows="10" cols="30" name="contents"></textarea></p>
<input id="btn" type="button" value="FileAdd">
<div id="result">
</div>
<button>Write</button>
</form>
</body>
</html>
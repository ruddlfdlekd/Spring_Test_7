<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="../resources/SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
$(function(){
	    //전역변수
	    var obj = [];              
	    //스마트에디터 프레임생성
	    nhn.husky.EZCreator.createInIFrame({
	        oAppRef: obj,
	        elPlaceHolder: "contents",
	        sSkinURI: "../resources/SE2/SmartEditor2Skin.html",
	        htParams : {
	            // 툴바 사용 여부
	            bUseToolbar : true,            
	            // 입력창 크기 조절바 사용 여부
	            bUseVerticalResizer : true,    
	            // 모드 탭(Editor | HTML | TEXT) 사용 여부
	            bUseModeChanger : true,
	        }
	    });
	    //전송버튼
	    $("#save").click(function(){
	        //id가 smarteditor인 textarea에 에디터에서 대입
	        obj.getById["contents"].exec("UPDATE_CONTENTS_FIELD", []);
	        //폼 submit
	        $("#frm").submit();
	    });
	
	var z=0;
	$("#btn").click(function() {
		if(z<5){
			
		 $("#result").append("<p><input type='file' name='f1'><span class='x'>X</span></p>");
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
<form id="frm" name="frm" action="./${board }Write" method="POST" enctype="multipart/form-data">
<p>writer<input type="text" name="writer"></p>
<p>title<input type="text" name="title"></p>
<p>contents<textarea rows="10" cols="30" name="contents" id="contents"></textarea></p>
<input id="btn" type="button" value="FileAdd">
<div id="result">
</div>	
<input type="button" value="Write" id="save">
</form>
</body>
</html>
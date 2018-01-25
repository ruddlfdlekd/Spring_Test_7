<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	var ex=$("#ex").html();
	
	$("#result").on("click",".remove",function() {
		$(this).parent().remove();
		$("#result").append(ex);
		$(".fn").val(null);
		$('#image_preview img').attr('src', "#");
	});	
	
	$("#result").on("click",".remove2",function() {
		$(this).parent().remove();
		$("#result").append(ex);
		$(".fn").val("${file.oname}");
		$('#image_preview img').attr('src', "../resources/upload/${file.fname}");
	});
	
	$("#result").on("change",".input_img",function(){
		ext = $(this).val().split('.').pop().toLowerCase();
		if($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
            resetFormElement($(this)); //폼 초기화
            window.alert('이미지 파일이 아닙니다! (gif, png, jpg, jpeg 만 업로드 가능)');
        }
	
		   file = $('.input_img').prop("files")[0];
           blobURL = window.URL.createObjectURL(file);
       		$(".fn").val($('.input_img').val().substring($('.input_img').val().lastIndexOf('\\')).substring(1));
           $('#image_preview img').attr('src', blobURL);
           $('#image_preview').slideDown(); //업로드한 이미지 미리보기 

	});
	
	$(".del").click(function() {
		var del = $(this);
		var fnum = $(this).attr("title");
		var fname = $(this).attr("id");
		var check= confirm("삭제시 기존사진이 삭제됩니다");
		if(check){
			$.ajax({
				url:"../file/fileDelete2",
				type:"GET",
				data:{
					fnum:fnum,
					fname:fname,
				},
				success:function(data){
					if(data.trim()=="true"){
					del.parent().remove();
					$("#result").append(ex);
					$(".fn").val(null);
					$(".on").val(null);
					$('#image_preview img').attr('src', "#");
					}
				}
			});
		}
		});
});

</script>
<style type="text/css">


#ex{
	display:none; 
}
.remove2{
	cursor: pointer;
}
.remove{
	cursor: pointer;
}
.del{
cursor: pointer;
}

</style>
</head>
<body>
<form action="memberUpdate" method="POST"  enctype="multipart/form-data">
<input type="hidden" name="id" value="${member.id }">
PW:<input type="text" name="pw" value="${member.pw }"><br>
NAME:<input type="text" name="name" value="${member.name }"><br>
Email:<input type="text" name="email" value="${member.email }"><br>
phone:<input type="text" name="phone" value="${member.phone }"><br>
age:<input type="text" name="age" value="${member.age }"><br>
job:<input type="text" name="job" value="${member.job }">
<div id="result">
<c:if test="${not empty file}">
<p>기존 파일 :<input type="text" class="on" value="${file.oname }" readonly="readonly"></p>
<p>현재 파일 :<input type="text" class="fn" value="${file.oname }" readonly="readonly"></p>
<p><input type="file" name="file" value="${file.oname}" class="input_img"><span class="del" title="${file.fnum }" id="${file.fname }">기존 사진 삭제</span>&emsp;&emsp;<span class="remove2">현재업로드한 사진 삭제</span></p>
</c:if>
<c:if test="${empty file}">
<p>현재 파일 :<input type="text" class="fn" value="${file.oname }" readonly="readonly"></p>
<p><input type="file" class="input_img" name="file"><span class="del" title="${file.fnum }" id="${file.fname }">기존 사진 삭제</span>&emsp;&emsp;<span class="remove2">현재업로드한 사진 삭제</span></p>
</c:if>
</div>

<div id="image_preview">
<c:if test="${not empty file}">
<img src="../resources/upload/${file.fname}" style="width:300px; height:300px;"/>
</c:if>
<c:if test="${empty file }">
<img src="#" style="width:300px; height:300px;"/>
</c:if>
</div>

<div id="ex">
<p><input type="file" name="file" class="input_img"><span class="remove">기존 사진 삭제</span>&emsp;&emsp;<span class="remove2">현재 업로드한 사진 삭제</span></p>
</div>
<button>update</button>

</form>
</body>
</html>
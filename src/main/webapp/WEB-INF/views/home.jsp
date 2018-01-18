<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<a href="notice/noticeList">NoticeList</a>
<a href="qna/qnaList">QnaList</a>
<form action="./fileUpload" method="post" enctype="multipart/form-data">
<input type="text" name="id">
<input type="file" name="f1">
<input type="file" name="f1">
<button>write</button>
</form>
</body>
</html>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
<a href="./transfer">transfer</a>
<c:if test="${empty member}">
<a href="./member/memberLogin">Login</a>
<a href="./member/memberJoin">Join</a>
</c:if>


<c:if test="${not empty member}">
<a href="./member/memberLogout">Logout</a>
<a href="./member/memberView">MyPage</a>
</c:if>
</body>
</html>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<p>ID:<input type="text" name="id" value="${member.id }" readonly="readonly"></p>
<p>PW:<input type="text" name="pw" value="${member.pw }" readonly="readonly"></p>
<p>NAME:<input type="text" name="name" value="${member.name }" readonly="readonly"></p>
<p>Email:<input type="text" name="email" value="${member.email }" readonly="readonly"></p>
<p>phone:<input type="text" name="phone" value="${member.phone }" readonly="readonly"></p>
<p>age:<input type="text" name="age" value="${member.age }" readonly="readonly"></p>
<p>job:<input type="text" name="job" value="${member.job }" readonly="readonly"></p>
<c:if test="${not empty file}">
<p><img src="../resources/upload/${file.fname}" style="width:300px; height=:400px"></p>
</c:if>
<c:if test="${empty file }">
<h1>사진이 없습니다 사진을 추가시켜주세요</h1>
</c:if>
<a href="./memberUpdate">Update</a>
<a href="./memberDelete">Delete</a>
</body>
</html>
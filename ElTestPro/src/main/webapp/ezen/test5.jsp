<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>test5</title>

<%
	pageContext.setAttribute("title", "EL 연산자");
%>

</head>

<body>
	<h1>test5</h1>
	empty 예약어는 값이 비어 있거나 null인지를 판단할 때 사용하는 연산자이다
	값이 null이면 true를 반환한다
	문자열과 배열등 객체의 크기가 0인 경우에도 true를 반환한다
	그 밖에는 false를 반환한다
	<hr>
	${empty title} title은 값이 있다<br>
	${empty title2} title2는 값이 없다<br>
	
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>		
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>test7</title>

<%
	pageContext.setAttribute("title", "EL 연산자");
%>

</head>

<body>
	<h1>test7</h1>
	
	<!-- 조건이 맞지않아 내용이 안보임 -->
	<!-- jstl 문법 core태그 c:if 조건문 문법임 -->
	<c:if test='${10 > 20}' var='result'>
		10은 20보다 크다.
	</c:if>
		결과값(false) :${result}
		<br>
		
	<!-- 조건이 맞으면 내용이 보임 -->
	<!-- 화면 하나로 다양한 화면을 생성(네이버 로그인시 아이디로그인화면 생성되는거처럼) -->
	<c:if test='${10 < 20}' var='result'>
		10은 20보다 크다. <br>
		결과값(true): ${result}
	</c:if>	
</body>

</html>
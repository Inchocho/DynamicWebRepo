<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>test6</title>

<style type="text/css">
	p{
		color: red;
	}	
</style>

<%
	pageContext.setAttribute("title", "EL 연산자");
%>

</head>

<body>
	<h1>test6</h1>
	<h2>EL표기법을 통한 연산자 표기</h2>
	<hr>
	${10 eq 11} eq : <p>equal 10은 11과 같다 : 결과 false</p> 						 
<%-- 	${10 ne 11} ne : <p>negative(부정연산자 !) 10은 11과 같지않다 : 결과 true</p>		실행되는데 에러나서 주석 --%>
	${10 lt 11} lt : <p>less than 10은 11보다 작다 : 결과 true</p>
	${10 ge 11} ge : <p>greater than or equal 10은 11보다 크거나 같다 : 결과 true</p>
	${10 gt 11} gt : <p>greater than 10은 11보다 크다 : 결과 false</p>
	${10 le 11} le : <p>less than or equal 10은 11보다 작다 : 결과 true</p>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>

</head>

<body>
	
	<%
		int myAge = (int)request.getAttribute("myKey2");
		String myName = (String)request.getAttribute("myKey");		
	%>
	
	<%=myAge%>
	<%=myName%>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Test6</title>

</head>

<body>

	<%
		String myName = (String)request.getAttribute("myKey");
		myName += " 하이";
	%>
	
	<%=myName%>
</body>

</html>
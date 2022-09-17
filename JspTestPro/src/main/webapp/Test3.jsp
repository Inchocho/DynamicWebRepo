<%@page import="ezen.dto.HumanDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Test3</title>

</head>

<body>
	
	<%
		HumanDto humanDto = new HumanDto();
	
		humanDto.setName("박성욱");
		humanDto.setAge(31);
		humanDto.setWelcomeSay("안녕");
	%>
	
	<%=humanDto.getWelcomeSay()%>
	
	<%="안녕하세요"%>
	
	
</body>

</html>
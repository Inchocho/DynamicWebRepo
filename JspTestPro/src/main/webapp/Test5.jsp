<%@page import="java.util.ArrayList"%>
<%@page import="ezen.dto.HumanDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Test5</title>

</head>

<body>
	<%
		HumanDto humanDto = new HumanDto("조현석",31,"안녕");
		ArrayList<HumanDto> humanList = new ArrayList<>();
		
		humanList.add(humanDto);
	
	%>	
	
	<%=humanList.get(0).toString()%>
</body>

</html>
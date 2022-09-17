<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Test2</title>

</head>

<body>
	<%
		String title = "";
	
		title = "글씨크기 - html 예제이다";
	
		String str = "";
						
		for(int i=1; i<=6; i++){
			str += "<h"+ i + ">" +"안녕하세요. " 
				+ "h" + i + " 크기입니다" + "</h" + i + ">" ;					
		}
		
	%>
	
	<%=title%>
	
	<%=str%>
</body>

</html>
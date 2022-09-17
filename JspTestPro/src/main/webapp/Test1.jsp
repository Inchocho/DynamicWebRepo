<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Test1</title>

</head>

<body>

	<%
		System.out.println("스크립틀릿 JSP안에서 JAVA코드를 이용");
		int num = 0;
		
		num = 10;
		
		System.out.println(num);
	%>	
	
	<%
		num = num + 5;
		
		System.out.println(num);
	%>
	
<!-- expression 화면에 출력함 (완벽할때 마지막으로 출력)-->
	<%=num%>
	
</body>

</html>
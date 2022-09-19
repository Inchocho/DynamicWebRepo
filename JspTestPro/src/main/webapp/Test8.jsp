<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>

</head>

<body>
	
	<!-- jsp에서 요청값을 든채로 다시 서블릿으로 전달 -->
	<!-- a href 와 같이 url 형태로 전송하면 GET 방식으로 전달 -->
	<!-- JAVA단 영역은 절대경로로 프로젝트명 (JspTestPro)까지 작성해야한다  -->
	<a href="/JspTestPro/ezen/test8?yourPhone=010-8282-2459&myName=박">
		버튼 누르면 servlet8로 데이터를 전달한다
	</a>
	
	<%
		String yourPhone = (String)request.getAttribute("myKey");
		String myName = (String)request.getAttribute("myKey2");
	%>
	
	<%=yourPhone%>
	<%=myName%>

</body>

</html>
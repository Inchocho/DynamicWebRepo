<%@page import="ezen.vo.TestVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>test1</title>


</head>

<body>
	<h1>test1</h1>
	<%--page스코프 -> request스코프 -> session스코프 -> application스코프 --%>
	<%--동일한 key값을 사용할경우 스코프를 반드시 명시해주자 --%>
	
	<%--생략시 default값은 request스코프--%>
	<%--requestScope.obj1임 실제로는--%>
	${obj1}	<!-- 변수선언 및 request로부터 받아온 속성(attribute)을 형변환해서 담는과정까지가 단축됨 -->
	<br>
	${testVo.title}
	
	<%
		TestVo testVo = (TestVo)request.getAttribute("testVo");
		System.out.println(testVo.getTitle());
	%>
	<br>
	${testVo.num}
	<br>
	<%--동일한 key값으로 묶인경우 page->request->session->application 스코프 순서로 가져온다--%>
	${sessionObj1}
	<br>
	${sessionScope.sessionObj1}
	<br>
	${str} 이 글 앞에 str 변수 내용이 떠야한다
</body>

</html>
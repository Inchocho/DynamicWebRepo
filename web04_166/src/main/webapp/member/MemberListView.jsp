<%@page import="java.util.ArrayList"%>
<%@page import="spms.dto.MemberDto"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!-- MVC패턴중 VIEW(9.16) -->	

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>회원목록</title>

</head>

<body>

	<jsp:include page="/Header.jsp"/>
	<h1>회원목록</h1>
	
	<p>
		<a href="./add">신규 회원 추가</a>
		<a href="../auth/login">로그인 화면</a>
	</p>	
	
	
	<!-- JSP문법임 -->	
	<!-- MemberListServlet에서 전달받은 객체 memberList의 값을 
		ArrayList<MemberDto> memberList = request.getAttribute(키값:"memberList");를 통해 담음
	-->
	
<!--  java Bean으로 대체(기존 42라인 for문위에 존재) -->
<!--  ARRAYLIST<MEMBERDTO> MEMBERLIST =  -->
<!-- (ARRAYLIST<MEMBERDTO>)REQUEST.GETATTRIBUTE("MEMBERLIST"); -->
	
<!-- taglib을 통해 java Bean도 대체함 -->	
	<c:forEach var="memberDto" items="${memberList}">
	
		${memberDto.getNo()},
		<a href='./update?no=${memberDto.getNo()}'>${memberDto.getName()}</a>,
		${memberDto.getEmail()},
		${memberDto.getCreateDate()},	
		<a href='./delete?no=${memberDto.getNo()}'>[삭제]</a><br>
	
	</c:forEach>
	<!-- 세션타는지 확인용 -->
	${sessionScope.memberDto.getEmail()}
	${sessionScope.memberDto.getName()}
	<jsp:include page="/Tail.jsp"/>
</body>

</html>
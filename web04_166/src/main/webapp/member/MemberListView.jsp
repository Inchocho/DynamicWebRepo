<%@ page import="java.util.ArrayList"%>
<%@ page import="spms.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- MVC패턴중 VIEW(9.16) -->	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>회원목록</title>

</head>

<body>

	<h1>회원목록</h1>
	
	<p>
		<a href="./add">신규 회원 추가</a>
	</p>	
	
	<!-- JSP문법임 -->	
	<!-- MemberListServlet에서 전달받은 객체 memberList의 값을 
		ArrayList<MemberDto> memberList = request.getAttribute(키값:"memberList");를 통해 담음
	-->
	
	<%
		ArrayList<MemberDto> memberList = 
			(ArrayList<MemberDto>)request.getAttribute("memberList");
		for(MemberDto memberDto : memberList){
			
	%>
	
	<%=memberDto.getNo()%>,
	<a href='./update?no=<%=memberDto.getNo()%>'><%=memberDto.getName()%></a>,
	<%=memberDto.getEmail()%>,
	<%=memberDto.getCreateDate()%>	
	<a href='./delete?no=<%=memberDto.getNo()%>'>[삭제]</a>
	<br>
	<%
		}
	%>
</body>

</html>
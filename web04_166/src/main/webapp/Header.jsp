<%@page import="spms.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<jsp:useBean 
		id="memberDto"
		scope="session"
		class="spms.dto.MemberDto"
		type="spms.dto.MemberDto"
	/>	

<%
// 	MemberDto memberDto = (MemberDto)session.getAttribute("memberDto");
%>

<div style="background-color: #00008b; color: #ffffff; height: 20px;
	padding: 5px;">
	
	<span style="float: right;">
		<%=memberDto.getName()%>
		<a style="color:white;"
			href="<%=request.getContextPath()%>/auth/logout">
			로그아웃
		</a>
	</span>
	
SPMS(Simple Project Management System)
</div>
	
<%@ page import="java.util.ArrayList"%>
<%@ page import="spms.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- MVC패턴중 VIEW(9.16) -->	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>회원정보 수정페이지</title>

<script type="text/javascript">
	function pageMoveListFnc(){
		location.href = './list';		
	}
	
</script>

</head>

<body>

	<jsp:include page="/Header.jsp"/>
	<h1>회원정보</h1>
	
		<form action='update' method='post'>
		번호:  <input type='text' name='mNo'
			 value='${memberDto.no}' readonly><br>
		이름:  <input type='text' name='name'
			 value='${memberDto.name}'><br>
		이메일: <input type='text' name='email'
			 value='${memberDto.email}'><br>
		가입일: ${requestScope.memberDto.createDate} <br>
			  <input type='submit' value='저장'>
			  <input type='reset' value='취소' onclick='pageMoveListFnc();'>
			  <input type='button' value='삭제'
			  	 onclick='location.href="./delete?no=0"'>			  			  			  
		</form>	
	
	<jsp:include page="/Tail.jsp"/>
</body>

</html>
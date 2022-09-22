<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>

<script type="text/javascript">
	function pageMoveListFnc() {
		var url = './member/list';
		location.href = url;
	}
	
	function pageMoveLoginFnc() {
		var url = './auth/login';
		location.href = url;
	}	
</script>

</head>

<body>
	<jsp:include page="/Header.jsp" />

	<h1>인덱스 페이지입니다.</h1>
	<input type="button" onclick="pageMoveListFnc()" value="목록으로 이동">
	<input type="button" onclick="pageMoveLoginFnc()" value="로그인화면으로 이동">

	<jsp:include page="/Tail.jsp" />
</body>

</html>
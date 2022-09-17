<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>회원 정보</title>
</head>
<body>
	<h1>회원정보</h1>

	<form action='add' method='post'>
		번호: <input type='text' name='mNo' value='' readonly><br>
		이름: <input type='text' name='name' value=''><br> 
		이메일: <input type='text' name='email' value=''><br> 
		가입일: <br> 
		<input type='submit' value='저장'>
		<input type='reset' value='취소' onclick='location.href="./list"'>
	</form>
</body>
</html>

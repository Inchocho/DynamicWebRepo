<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head><title>회원 등록</title></head>
<body>
<h1>회원등록</h1>

<p>
	<a href='./add'>신규 회원</a>
</p>
<form action='add' method='post'>	
이름: <input type='text' name='name'><br>
이메일: <input type='text' name='email'><br>
암호: <input type='password' name='password'><br>
<input type='submit' value='추가'>
<input type='reset' value='취소'>	
</form>
</body></html>

<!-- 홑따옴표 사용한 이유 -> 자바(서블릿)에서 사용할거라 " " 쌍따옴표 안에 넣어서 쓸거니까 
MemberAddServlet 참조 -->
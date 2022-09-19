<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>

</head>

<body>

	<!-- post는 폼을통해서 (submit) -->
	<form action="./ezen/test11" method="post">
		이름: <input type="text" name="fName" value=""><br>
		나이: <input type="text" name="fAge" value=""><br>
		성별: <input type="text" name="fGender" value=""><br>
		<input type="submit" value="데이터 전송" style="margin-top:5px">
	</form>
	
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Test10</title>

</head>

<body>
	
	<!-- form을 통한 post방식에서 input의 name이 getParamter와 동일하다 -->
	<!-- 즉 name을 통해 파라미터를 전달한다고 생각하면 된다(name이 key라고 생각) -->
	<form action="/JspTestPro/ezen/test10" method="post">
		<input type="text" name="firstTxt" value="">
		<input type="text" name="secondTxt" value="">
		
		<input type="submit" value="데이터 전송">
		
	</form>
	
</body>

</html>
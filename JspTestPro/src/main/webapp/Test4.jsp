<%@ page import="ezen.dto.HumanDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Test3</title>

</head>

<body>
	
	<%	
		//자바 생성자 다시공부해라
		//Dto에있는 변수를 직접접근하는게 아니라 getter/setter메소드를 통해서 접근한다(객체지향 정보를은닉)
		HumanDto humanDto = new HumanDto("조현석",31,"안녕하세요");
			
		String a = "이름: " + humanDto.getName() + "<br>"
				 + "나이: " + humanDto.getAge() + "<br>"
				 + "환영인사: " + humanDto.getWelcomeSay(); 
			
	%>
	
	<%=a%>
	<%=humanDto.toString()%>
	
</body>

</html>
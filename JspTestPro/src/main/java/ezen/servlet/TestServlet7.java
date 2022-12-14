package ezen.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/ezen/test7")
public class TestServlet7 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		int myAge = 0;
		
		System.out.println(req.getParameter("myAge"));
		
		//파라미터 "myAge"의 값이 null이 아니면 형변환해서 대입(null을 정수형변환시 에러)		
		if(req.getParameter("myAge") != null) {
			myAge = Integer.parseInt(req.getParameter("myAge"));
		}
		
		//그 결과 null이라서 myAge변수에 0이 담김
		//추가적인 예외처리는 나중에하자 try~catch를 통해 숫자만오게하는등
		System.out.println(myAge);
		
		//키값 "myKey"에 value로 변수 myAge의 값 +5 (즉 나이를입력하면 거기에 + 5살)
		//요청을 전달할것이기 때문에 req에 setAttribute를 통해 값을 담는다
		req.setAttribute("myKey", myAge + 5);	
		
		//그결과 key값 "myKey"를 찍어보면 0이 나옴
		System.out.println(req.getAttribute("myKey"));		
		
		//응답 text/html 타입 + 인코딩 UTF-8
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		
		//RequestDispatcher를 통해 /Test7.jsp로 객체를 응답할 URL로 전달 (요청 전달) 및 해당 URL로 이동
		//RequestDispatcher rd = request.getRequestDispatcher (responseURL);
		//responseURL은 상대경로로 작성함 대체로(즉 원래대로면 jsp파일 경로가 test폴더 하위에 있는게 좋음)
		RequestDispatcher dispatcher =
				req.getRequestDispatcher("/Test7.jsp");
		
		//Dispatcher에는 req,와 resp를 포함함(값을 들고있음 즉 req.getAttribute("myKey")를 통해 0을 꺼냄
		//전달될 page에 값을 담은체로 객체를 전달할 수 있다 
		dispatcher.include(req, resp);
	}
}

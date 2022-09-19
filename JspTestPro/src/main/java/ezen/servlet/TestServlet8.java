package ezen.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/ezen/test8")
public class TestServlet8 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String yourPhone = "";
		
		yourPhone = req.getParameter("yourPhone");
		
		String myName = "";
		
		myName = req.getParameter("myName");
		
		//jsp에서 a태그를 통해 이동하면 번호 010-8282-2459값과 이름 박을 전달받음 
		System.out.println(yourPhone);
		System.out.println(myName);
		
		/*위에 코드는 jsp에서 a태그를 통한 GET방식 요청으로 서블릿으로 데이터를 전송받은 예제*/
		
		//jsp로 값을 넘겨주기위해 HttpServletRequest 객체인 req에 key:myKey , value:yourPhone을 담음
		req.setAttribute("myKey", yourPhone);
		req.setAttribute("myKey2", myName);
		
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		
		//요청전달
		RequestDispatcher rd = 
				req.getRequestDispatcher("/Test8.jsp");
		
		rd.include(req, resp);
	}
}

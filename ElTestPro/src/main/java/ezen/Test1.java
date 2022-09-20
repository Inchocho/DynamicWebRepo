package ezen;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ezen.vo.TestVo;

@WebServlet(value="/ezen/test1")
public class Test1 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		TestVo testvo = new TestVo("vo객체", 1);
		
		req.setAttribute("testVo", testvo);

		req.setAttribute("obj1", "story1");
		//key값이 동일할경우
		req.setAttribute("sessionObj1", "이건 request에 담긴 내용이다");
		
		session.setAttribute("sessionObj1", "sessionStory1");
		
		RequestDispatcher rd 
				= req.getRequestDispatcher("./test1.jsp");
		rd.forward(req, resp);
	}
}

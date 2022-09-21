package ezen;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ezen.vo.ClassVo;

@WebServlet(value="/ezen/test4")
public class Test4 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ClassVo classVo = new ClassVo("반이름", 15);
		
		req.setAttribute("classVo", classVo);
		
		RequestDispatcher rd 
			= req.getRequestDispatcher("./test4.jsp");
		
		rd.forward(req, resp);
		
	}
}

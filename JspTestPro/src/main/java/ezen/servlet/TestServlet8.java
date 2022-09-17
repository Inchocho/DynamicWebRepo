package ezen.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/ezen/test8")
public class TestServlet8 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String myName = req.getParameter("myName");
		
		int myAge = 0;
		
		if(req.getParameter("myAge") != null) {
			myAge = Integer.parseInt((String)req.getParameter("myAge"));
		} 
		
		System.out.println(myName);
		System.out.println(myAge);
		
		req.setAttribute("myKey", myName);
		req.setAttribute("myKey2", myAge);
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		RequestDispatcher dispatcher 
			= req.getRequestDispatcher("/Test8.jsp");			
		
		dispatcher.include(req, resp);
	}

}

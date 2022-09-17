package ezen.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ezen.dto.HumanDto;

@WebServlet(value="/ezen/test6")
public class TestServlet6 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String myName = req.getParameter("myName");
		
		System.out.println(myName);
		
		req.setAttribute("myKey", myName);
		
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		
		RequestDispatcher dispatcher =
				req.getRequestDispatcher("/Test6.jsp");
		
		dispatcher.include(req, resp);
	}
}

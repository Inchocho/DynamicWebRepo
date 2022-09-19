package ezen.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/ezen/test11")
public class TestServlet11 extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String fName = req.getParameter("fName");
		int fAge = Integer.parseInt(req.getParameter("fAge"));
		String fGender = req.getParameter("fGender");
		
		System.out.println("이름: " + fName + 
				" 나이: " + fAge + " 성별: " + fGender);
	}
}

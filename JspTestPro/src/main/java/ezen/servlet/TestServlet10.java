package ezen.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/ezen/test10")
public class TestServlet10 extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req,
		HttpServletResponse resp) throws ServletException, IOException {
		
		//<input type="text" name="firstTxt" value="">
		//doPost방식에서는 name(firstTxt)이 파라미터라고 생각하면 된다
		String firstTxt = req.getParameter("firstTxt");
		
		String secondTxt = req.getParameter("secondTxt");
		
		System.out.println(firstTxt + " : " + secondTxt);
		
	}
}
